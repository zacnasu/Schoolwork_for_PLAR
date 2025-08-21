/*
 * Appendix C program.
 * CSC 360, Summer 2023
 *
 * In this example, fork & execve & pipe are used to connect the output
 * of "ls -1" to the input of "wc -l". ("wc" is the "word count" command
 * in Unix, and "-l" is the option causing the command to output only the
 * number of lines seen at the input).
 */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[]) {
    char *cmd_head[] = { "/usr/bin/gcc", "--help", 0 };
    char *cmd_tail[] = { "/usr/bin/grep", "dump", 0 };
    char *cmd_after[] = {"/usr/bin/sort", 0};
    char *envp[] = { 0 };
    int status;
    int pid_head, pid_tail, pid_after;
    int fd[2];
    int fd2[2];

    pipe(fd);
    pipe(fd2);
    
    /* After this point, we now have a pipe in the form of two file
     * descriptors. The end of the pipe from which we can read is
     * fd[0], and the end of the pipe to which we write is fd[1].
     *
     * Any child created after this point will have these file descriptors
     * for the pipe.
     */

    printf("parent: setting up piped commands...\n");
    if ((pid_head = fork()) == 0) {
        printf("child (head): re-routing plumbing; STDOUT to pipe.\n");
        dup2(fd[1], 1);
        close(fd[0]);
        execve(cmd_head[0], cmd_head, envp);
        printf("child (head): SHOULDN'T BE HERE.\n");
    }

    if ((pid_tail = fork()) == 0) {
        printf("child (tail): re-routing plumbing; pipe to STDIN.\n");
        dup2(fd[0], 0);
        close(fd[1]);
        execve(cmd_tail[0], cmd_tail, envp);
        printf("child (tail): SHOULDN'T BE HERE.\n");
    }

    if ((pid_after = fork()) == 0) {
        sleep(5);
        printf("child (after: re-routing plumbing; pipe to STDIN.\n");
        // dup2(fd2[0], 0);
        // close(fd2[1]);
        write(1, "asdfkl\nasdjfkld;asjfkl;as\n", 25);
        execve(cmd_after[0], cmd_after, envp);
        return 1;
        printf("(after): SHOULDN'T BE HERE.\n");
    }

    /* One last detail: At this point we are running within code used
     * by the parent. The parent does *not* need the pipe after the
     * children are started, but the file system does not know this
     * and so detects additional links to the open pipe. Therefore the
     * parent will close its copies of the file descriptors. (Note that
     * the file system will only close the pipe for good once all processes
     * having the pipe in the file-descriptor table have either terminated
     * or have closed the pipe.
     */
 
    close(fd[0]);
    close(fd[1]);
    close(fd2[0]);
    close(fd2[1]);

    printf("parent: waiting for child (head) to finish...\n");
    waitpid(pid_head, &status, 0);
    printf("parent: child (head) is finished.\n");

    printf("parent: waiting for child (tail) to finish...\n");
    waitpid(pid_tail, &status, 0); 
    printf("parent: child (tail) is finished.\n");

    printf("parent: waiting for child (after) to finish...\n");
    waitpid(pid_after, &status, 0); 
    printf(": child (after) is finished.\n");
}
