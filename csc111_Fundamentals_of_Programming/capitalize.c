/*Zachary Nasu
 * V00911790
 * capitalize.c*/

#include <stdio.h>
#include <ctype.h>
int main() {

    int ch = getchar();
    int count = 1;
    while(ch != '#'){

        if(count > 0 && islower(ch)){
           ch = toupper(ch);
        }
        if(isalpha(ch)){
            count = 0;
        }
        if(isspace(ch)){
            count++;
        }
     printf("%c",ch);
    ch = getchar();


}
    return 0;
}