/*
CSC 360 Summer 2023 A0
Zachary Nasu
V00911790
*/

#include <stdio.h>

int main(int argc, char *argv[]){
    //If there are no inputs
    if(argc < 2){
        return -1;
    }

    char* file_name = argv[1];
    FILE *fptr = fopen(file_name, "r");

    //assumption made that word including punctuation isn't more than 64 chars
    char curr_char[64];
    int i =0;

    while(fscanf(fptr,"%s", curr_char)!= EOF){
        ++i;
        //moves up file pointer past space or newline
        fgetc(fptr);
    }
   fclose(fptr);
   //printing in desired format of ("number of words" "file name")
   printf("%d %s\n", i, file_name);
   return 0;
}