/* getstats.c 
 *
 * CSC 360, Summer 2023
 *
 * - If run without an argument, dumps information about the PC to STDOUT.
 *
 * - If run with a process number created by the current user, 
 *   dumps information about that process to STDOUT.
 *
 * Please change the following before submission:
 *
 * Author: Zachary Nasu
 */


#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * Note: You are permitted, and even encouraged, to add other
 * support functions in order to reduce duplication of code, or
 * to increase the clarity of your solution, or both.
 */

int print_process_info(char * process_num) {
    FILE *fp;
    char str[256];
    char proc_str[50] = "/proc/";
    strcat(strcat(proc_str, process_num), "/status");
    fp = fopen(proc_str ,"r");
    char* cmp_str_name = "Name:";
    char* cmp_str_threads = "Threads:";
    char* cmp_str_vcs = "voluntary_ctxt_switches:";
    char* cmp_str_nvcs = "voluntary_ctxt_switches:";
    int vcs = 0;
    int nvcs = 0;
    char tmp[50];
    char filename[50];
    
    if(fp != NULL){
        printf("Process number: %s\n", process_num);
        while(fgets(str, 256, fp)!= NULL){
            //filename
            if(memcmp(str, cmp_str_name, strlen(cmp_str_name)) == 0){
                printf("%s", str);
                sscanf(str, "%s %s", tmp, filename);
                if(strlen(filename)>0){
                    printf("Filename (if any): %s\n", filename);
                }
            }
            //threads
            if(memcmp(str, cmp_str_threads, strlen(cmp_str_threads)) == 0){
                printf("%s", str);
            }
            
            //total context switches
            if(memcmp(str, cmp_str_vcs, strlen(cmp_str_vcs)) == 0){
                sscanf(str, "%s %d", tmp, &vcs);
            }
            if(memcmp(str, cmp_str_nvcs, strlen(cmp_str_nvcs)) == 0){
                sscanf(str, "%s %d", tmp, &nvcs);
            }
        }
        printf("Total context switches: %d\n", vcs+nvcs);
        fclose(fp);
    } else {
        printf("Process number %s not found.\n", process_num);
    }
    return 0;
} 


int print_full_info() {
    char str[256];
    //cpu info
    FILE *fp = fopen("/proc/cpuinfo","r");
    char* cmp_str_model = "model name";
    int model_printed = 0;
    char* cmp_str_cores = "cpu cores";
    if(fp != NULL) {
       while( fgets (str, 64, fp)!= NULL ) {
          if(memcmp(str, cmp_str_model, strlen(cmp_str_model)) == 0 && model_printed == 0){
              printf("%s", str);
              model_printed = 1;
          }
          if(memcmp(str, cmp_str_cores, strlen(cmp_str_cores)) == 0){
              printf("%s", str);
              break;
          }
       }
        fclose(fp);
    }
    
    //version
    FILE* fp2 = fopen("/proc/version", "r");
    if(fp2 != NULL){
        if(fgets(str, 256, fp2)!=NULL){
            printf("%s", str);
        }
        fclose(fp2);
    }
    
    //memory info
    FILE* fp3 = fopen("/proc/meminfo","r");
    char* cmp_str_mem = "MemTotal:";
    if(fp3 != NULL) {
       while( fgets (str, 64, fp3)!= NULL ) {
          if(memcmp(str, cmp_str_mem, strlen(cmp_str_mem)) == 0){
              printf("%s", str);
              break;
          }
       }
        fclose(fp3);
    }
    
    //uptime
    double uptime1;
    double uptime2;
    FILE* fp4 = fopen("/proc/uptime","r");
    if(fp4 != NULL){
        if(fgets(str, 64, fp3) != NULL){
            sscanf(str, "%lg %lg", &uptime1, &uptime2);
            int days = (int) uptime1/(24*3600);
            int hours = (int) uptime1 % (24*3600)/3600;
            uptime1 = (int) uptime1 % 3600;
            int minutes = (int) uptime1 /60;
            uptime1 = (int) uptime1 % 60;
            int seconds = uptime1;
            printf("Uptime: %d days, %d hours, %d minutes, %d seconds\n", days, hours, minutes, seconds);
        }
    }
    
    return 0;
}


int main(int argc, char ** argv) {  
    if (argc == 1) {
        return print_full_info();
    } else {
        return print_process_info(argv[1]);
        
    }
}

