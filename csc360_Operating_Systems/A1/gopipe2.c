/* gopipe.c
 *
 * CSC 360, Summer 2023
 *
 * Execute up to four instructions, piping the output of each into the
 * input of the next.
 *
 * Please change the following before submission:
 *
 * Author:
 */


/* Note: The following are the **ONLY** header files you are
 * permitted to use for this assignment! */

#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <sys/wait.h>

#include <stdio.h>

// char** commands_to_pointer(char**[80] cpy, char** dest){
//     for(int i =0; i<8; i++){
//       if(strlen(cpy[0]>1)){
//         dest[i] = cpu[0];
//       }
//     }
//     return dest;
// }

int main() {
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
   for(int i = 0; i < 4 ; i++){
       for( int j = 0; j < 8; j++){
           if(strlen(commands[i][j])>0){
               write(1, commands[i][j], strlen(commands[i][j]));
               
           }
       }
       write(1, "\n",1);
   }
       
    int pid, pid1, pid2, pid3;
    int status;
    pid = fork();
    // int executions = 0;
    if(pid==0){
        pid1 = fork();
        if(pid1 == 0){
            pid2 = fork();
            if(pid2 == 0){
                pid3 = fork();
                if(pid3 == 0){
                    // for(int i =0; i<8; i++){
                    //     if(strlen(commands[executions][i])>1){
                    //         ptr[i] = commands[executions][i];   
                    //         printf("%s\n", commands[executions][i]);
                    //     }else{
                    //         ptr[i] = NULL;
                    //     }
                    // }
                    // execve(commands[executions][0], commands[executions], NULL); //Remembewr that I have to attach null pointer to end of command list
                    // executions++;
                    printf("pid1");
                    return 0;
                }
            } else {
            waitpid(pid2, &status, 0);
            printf("pid2\n");
            return 0;
            }
        } else {
        waitpid(pid1, &status, 0);
            printf("pid3\n");
        return 0;
        }
    } else {
    waitpid(pid, &status, 0);
            printf("pid4\n");
    }
//        char *cmd_head[] = { "/bin/ls", "-1", 0 };
//     char *cmd_tail[] = { "/usr/bin/wc", "-l", 0 };
//     char *envp[] = { 0 };
//     int status;
//     int pid1, pid2, pid3, pid4;
//     char *ptr[8];
//     int fd1[2];
//     int fd2[2];
//     int fd3[2];
//     pipe(fd1);
//     pipe(fd2);
//     pipe(fd3);
    
//     fflush(stdout);
//     fflush(stdin);
    
//     // printf("here\n");
//     for(int i =0; i<8; i++){
//      if(strlen(commands[0][i])>1){
//        ptr[i] = commands[0][i];
//        printf("12%s\n", commands[0][i]);
//      }else{
//          ptr[i] = NULL;
//      }
//     }
//     // printf("here2\n");
//     if ((pid1 = fork()) == 0) {
//         // printf("hereasdf\n");
//         dup2(fd1[1], 1);
//         execve(commands[0][0], ptr, envp);
//         return 1;
//         printf("child (head): SHOULDN'T BE HERE.\n");
//     }
    
//     for(int i =0; i<8; i++){
//      if(strlen(commands[1][i])>0){
//        ptr[i] = commands[1][i];
//          printf("123%s\n", commands[1][i]);
//      }else{
//          ptr[i] = NULL;
//      }
//     }

//     if (command_num>1 && (pid2 = fork()) == 0) {
//         waitpid(pid1, &status, 0);
//         // printf("%d\n", WEXITSTATUS(status));
//         // printf("hereasdfadf\n");
//         dup2(fd1[0], 0);
//         if(command_num>2){
//             // printf("rerouting\n");
//             dup2(fd2[1], 1);
//         }
        
//         execve(commands[1][0], ptr, envp);\
//         return 1;
//         printf("child (tail): SHOULDN'T BE HERE.\n");
//     }
    
//     for(int i =0; i<8; i++){
//      if(strlen(commands[2][i])>0){
//        ptr[i] = commands[2][i];
//          printf("345%s\n", commands[2][i]);
//      }else{
//          ptr[i] = NULL;
//      }
//     }
//     if (command_num>2 && (pid3 = fork()) == 0) {
//         waitpid(pid2, &status, 0);
//         printf("%d\n", WEXITSTATUS(status));
//         dup2(fd2[0], 0);
//         // if(command_num>3){
//         //     dup2(fd3[1], 1);
//         // }
//         // printf("child three about to go\n");
//         // for(int i = 0; ptr[i]!=NULL; i++){
//         //     printf("%s\n", ptr[i]);
//         // }
//         // printf("%d\n", getppid());
//         // printf("%d %d %d\n", pid1, pid2, pid3);
//         // printf("%s\n",ptr[3]);
//         execve(commands[2][0], ptr, envp);
//         printf("child (tail): SHOULDN'T BE HERE.\n");
//     }
//     for(int i =0; i<8; i++){
//      if(strlen(commands[3][i])>1){
//        ptr[i] = commands[3][i];   
//          printf("%s\n", commands[3][i]);
//      }else{
//          ptr[i] = NULL;
//      }
//     }
//     if (command_num>3 && (pid4 = fork()) == 0) {
//         // printf("%d\n", getppid());
//         // printf("%d %d %d\n", pid1, pid2, pid3);
//         waitpid(pid3, &status, 0);
//         // printf("%d\n", WEXITSTATUS(status));
//         printf("asdfasd\n");
//         dup2(fd3[0], 0);
//         // close(fd3[1]);
//         // printf("child3 done\n");
//         // for(int i = 0; ptr[i]!=NULL; i++){
//         //     printf("%s\n", ptr[i]);
//         // }
//         // printf("%s", commands[3][0]);
//         execve(commands[3][0], ptr, envp);
//         return 1;
//         printf("child (tail): SHOULDN'T BE HERE.\n");
//     }

// //     printf("parent: waiting for child (head) to finish...\n");
// //     waitpid(pid_head, &status, 0);
// //     printf("parent: child (head) is finished.\n");

// //     printf("parent: waiting for child (tail) to finish...\n");
// //     waitpid(pid_tail, &status, 0); 
// //     printf("parent: child (tail) is finished.\n");
//     // printf("hereasdfdasfas\n");
//     // waitpid(pid1, &status, 0);
//     // printf("pid1 done\n");
//     // waitpid(pid2, &status, 0);
//     // printf("pid2 done\n");
//     // waitpid(pid3, &status, 0);
//     // // printf("pid3 done\n");
//     // waitpid(pid4, &status, 0);
//     // printf("pid4 done\n");
//     // printf("command_num %d\n", command_num);
//     if(command_num==1){
//     waitpid(pid1, &status, 0);
//     } else if(command_num==2){
//     waitpid(pid2, &status, 0);
//     }else if(command_num==3){
        
//        waitpid(pid3, &status, 0);
//     }else{
        
//     printf("pid 4 %d\n", pid4);
//     waitpid(pid4, &status, 0);
//     printf("here4\n");
//     }
    
//     close(fd1[0]);
//     close(fd1[1]);
//     close(fd2[0]);
//     close(fd2[1]);
//     close(fd3[0]);
//     close(fd3[1]);
    return 0;
}