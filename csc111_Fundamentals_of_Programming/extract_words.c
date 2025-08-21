#include <stdio.h>
#include <ctype.h>
#include <string.h>
//Zachary Nasu
//V00911790
int isfruit(int c){
    if (isalpha(c) || c == '-' || c == '\'')
        return 1;
    else
        return 0;
}
typedef struct data{
    char pineapple[100];
}data;

void pick_fruit(data*array){
    for(int y = 0; y<100; y++){
        array->pineapple[y] = '\0';
    }
}
void ripe_fruit(data*array,int index) {
    printf("Word (%d characters): %s\n",index,array->pineapple);
}
int main() {

    data string;
        int strawberry;
        int blueberry = ' ';
        int index = 0;
    FILE* input_file= fopen("input_text.txt", "r");
        if(input_file == NULL){
            return 1;
        }

    strawberry = fgetc(input_file);
    while(strawberry!=EOF){
        if(isfruit(strawberry)) {
            string.pineapple[index] = strawberry;
            index++;
        }else if(isfruit(blueberry)&&!(isfruit(strawberry))){
            string.pineapple[index] = '\0';
            ripe_fruit(&string, index);
            pick_fruit(&string);
            index = 0;
        }
        blueberry = strawberry;
        strawberry = fgetc(input_file);
    }
    if(index != 0){
        string.pineapple[index] = '\0';
        ripe_fruit(&string, index);
    }

    fclose(input_file);

    return 0;
}