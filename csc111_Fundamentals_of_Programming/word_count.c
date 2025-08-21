/*Zachary Nasu
 * V00911790
 * word_count.c*/

#include <stdio.h>
#include <ctype.h>
int main() {

    char ch = getchar();
    int count = 1;
    int alpha = 0;
    int lines = 1;
    int word = 0;
    while(ch != '#'){

            alpha++;

        if(ch == '\n'){
            lines++;
        }
        if(count > 0 && !(isspace(ch))){
                word++;
            }

        if(isspace(ch)){
            count++;
        }else{
            count = 0;
        }


        ch = getchar();

    }
    printf("Characters: %d, Words: %d, Lines: %d", alpha, word , lines);
    return 0;
}