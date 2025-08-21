/* gopipe.c
 *
 * CSC 360, Summer 2023
 *
 * Execute up to four instructions, piping the output of each into the
 * input of the next.
 *
 * Please change the following before submission:
 *
 * Author: Zachary Nasu
 */


/* Note: The following are the **ONLY** header files you are
 * permitted to use for this assignment! */

#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <sys/wait.h>

int main() {
    // Tokenizing input
    char message[324];
    read(0, message, 324);
    char line_del[2] = "\n";
    char word_del[2] = " ";
    char* line_token;
    char* word_token;
    char commands_temp[4][80];
    int command_num = 0;
    char commands[4][8][80];
    for(int i = 0; i <4; i++){
        for(int j = 0; j < 8; j++){
            commands[i][j][0] = '\0';
        }
    }
    line_token = strtok(message, line_del);
    for(int i = 0; line_token != NULL; i++, command_num++) {
        strcpy(commands_temp[i], line_token);
        line_token = strtok(NULL, line_del);
    }
    for(int i = 0; i < command_num; i++){
        word_token = strtok(commands_temp[i], word_del);
        for(int j = 0; word_token!=NULL; j++){
            strcpy(commands[i][j], word_token);
            word_token = strtok(NULL, word_del);
        }
    }
    
    // Putting commands into processes
    char *envp[] = { 0 };
    int pid[command_num];
    char *ptr[8];
    int fd[command_num][2];
    for(int i = 0; i<command_num; i++){
        if(pipe(fd[i])<0){
            exit(1);
        }
    }
    for(int i = 0; i< command_num; i++){
        pid[i] = fork();
        if (pid[i] ==0){
            //closing file desciptors
            for (int j = 0; j < command_num; j++) {
                if(j!=i){
                  close(fd[j][0]);
                }
                if (j!=i+1) {
                  close(fd[j][1]);
                }
            }
            //copying commands into pointer so execve works
            for(int j =0; j<8; j++){
                if(strlen(commands[i][j])>0){
                    ptr[j] = commands[i][j];
                }else{
                    ptr[j] = NULL;
                }
            }
            dup2(fd[i][0], 0);
            //leave stdout if last one
            if(i!=command_num-1){
                dup2(fd[i+1][1], 1);
            }
            execve(ptr[0], ptr, envp);
            return 1;
        }
    }
    for (int i=0; i<command_num; i++) {
        if (i > 0) {
            close(fd[i][1]);
        }
    }
    // starting chain of pipes
    char* start = " ";
    write(fd[0][1], start, sizeof(char)*strlen(start));
    
    //wait to finish
    for (int i = 0; i < command_num; i++) {
        wait(NULL);
    }
    close(fd[0][1]);
    close(fd[command_num-1][0]);
    return 0;
}
