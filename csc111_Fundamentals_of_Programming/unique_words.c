#include <stdio.h>
#include <ctype.h>
#include <string.h>
//Zachary Nasu
//V00911790

typedef struct data{
    char string[100];
    int size;
}data;
int isbird(int c){
    if (isalpha(c) || c == '-' || c == '\'')
        return 1;
    else
        return 0;
}
void clear(data* array){
    for( int y = 0; y<100;y++ ){
        array->string[y] = '\n';
    }
}

int main() {
    int ch;
    int prev = ' ';
    int index = 0;
    FILE* input_file= fopen("input_text.txt", "r");
    if(input_file == NULL){
        return 0;
    }
    data word[10000];
    int num_words = 0;
    clear(word[num_words].string);

    ch= fgetc(input_file);
    while(ch!=EOF){
        ch = tolower(ch);
        if(!(isbird(prev))&&isbird(ch)){
            index = 0;
            word[num_words].string[index] =ch;
        }else if(isbird(prev)&&isbird(ch)){
            word[num_words].string[++index] = ch;
        }else if(isbird(prev)&&!(isbird(ch))){
            word[num_words].string[++index] = '\0';
            word[num_words].size = index;
            num_words++;
            clear(word[num_words].string);
        }
        prev = ch;


        ch=fgetc(input_file);
    }
    if(index!=0) {
        word[num_words].string[++index] = '\0';
        word[num_words].size = index;
        num_words++;
    }
    fclose(input_file);
    int trip;
    for(int k = 0; k<num_words; k++){
        trip = 0;
        for(int j = 0; j<k; j++){
            if(strcmp(word[k].string,word[j].string) == 0){
                trip = 1;
            }
        }
        if(trip == 0) {
            printf("%s\n", word[k].string);
        }
    }
    return 0;
}