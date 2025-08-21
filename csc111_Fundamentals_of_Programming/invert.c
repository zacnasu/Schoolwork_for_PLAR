/*Zachary Nasu
 * V00911790
 * invert.c*/

#include <stdio.h>
#include<ctype.h>
int main() {

    int character = 0;
    character = getchar();

    while(character != '#'){

         if(isupper(character)) {

             character = character + 32;
         }else if(islower(character)){

             character = character - 32;
         }
        printf("%c", character);
        character = getchar();

    }




    return 0;
}