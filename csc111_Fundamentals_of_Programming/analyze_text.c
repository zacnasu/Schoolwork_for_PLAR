#include <stdio.h>
#include <ctype.h>
#include <string.h>
//Zachary Nasu
//V00911790

typedef struct data{
    char string[100];
    int size;
    int occurrences;
}data;
int isfruit(int c){
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
    int prev=' ';
    int shortest = 100;
    int longest = 0;
    int index =0;
    FILE* input_file= fopen("input_text.txt", "r");
    data word[10000];
    int num_words = 0;
	clear(&word[num_words]);
    ch= fgetc(input_file);
    while(ch!=EOF){
        if(isalpha(ch)) {
            ch = tolower(ch);
        }
        if(!(isfruit(prev))&&isfruit(ch)){
            index = 0;
            word[num_words].string[index] =ch;
        }else if(isfruit(prev)&&isfruit(ch)){
            word[num_words].string[++index] = ch;
        }else if(isfruit(prev)&&!(isfruit(ch))){
            word[num_words].string[++index] = '\0';
            if(index>longest){
                longest = index;
            }
            if(index<shortest){
                shortest = index;
            }
            word[num_words].size = index;
            word[num_words].occurrences = 0;
            num_words++;
            clear(&word[num_words]);
            index = 0;
        }
        prev = ch;
        ch=fgetc(input_file);
    }
    if(index != 0){
        word[num_words].string[++index] = '\0';
        word[num_words].size = index;
        word[num_words].occurrences = 0;
        num_words++;
    }
    fclose(input_file);

    data unique[100];
    int p = 0;
    int trip;
    for(int k = 0; k<num_words; k++){
        trip = 0;
        for(int j = 0; j<k; j++){
            if(strcmp(word[k].string,word[j].string) == 0){
                trip = 1;
            }
        }
        if(trip == 0) {
            strcpy(unique[p].string,word[k].string);
            unique[p].size = word[k].size;
            unique[p].occurrences = word[k].occurrences;
            p++;
        }
    }
    int pop = 0;
    printf("Total words: %d\n",num_words);
    printf("Unique words: %d\n",p);
    printf("Longest words:\n");
    for( int y = 0; y<p; y++){
        if(unique[y].size==longest){
            printf("  %s\n",unique[y].string);
        }
    }
    printf("\nShortest words:\n");
    for(int r = 0; r<p ; r++){
        if(unique[r].size == shortest){
            printf("  %s\n",unique[r].string);
        }
    }


    for(int q = 0; q<p;q++){
        for(int d = 0; d<num_words;d++){
            if(strcmp(unique[q].string,word[d].string)==0){
                unique[q].occurrences++;
            }
        }
    }
    for(int g = 0; g<num_words; g++){
        if(unique[g].occurrences>pop){
            pop = unique[g].occurrences;
        }
    }
    printf("\nMost frequently occurring words:");
    for(int l = 0; l <p ; l++){
        if(unique[l].occurrences == pop){
            printf("\n  %s", unique[l].string);
        }
    }
    return 0;
}