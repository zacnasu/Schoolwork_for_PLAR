#include <ctype.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_INPUT_LINES 100
#define MAX_INPUT_CHARS 80
#define MAX_WORD_SIZE 20
#define MAX_EXEMPT_WORDS 100
#define MAX_UNIQUE_WORDS 500

/*meant to store unique words and record the lines they occur in*/
typedef struct node{
  char word[MAX_WORD_SIZE];
  int lines[MAX_INPUT_LINES];
}node;
/*stores exempt words read from exempt file*/
char exempt_words[MAX_EXEMPT_WORDS][MAX_WORD_SIZE];
/*stores input lines from input file*/
char input[MAX_INPUT_LINES][MAX_INPUT_CHARS];
/*stores the unique words tokenized from input file*/
node unique_words[MAX_UNIQUE_WORDS];

/*function declarations*/
int insert_unique(char*unique, int linenum,int word_count);

void read_exempt(char* file);

int compare_exempt(char* word);

void read_input_file(char* file);

int read_table();

/*repurposed from wordlist.c by Dr. Zastre*/
int strcmp_wrapper(const void *a, const void *b);

void print_node(node unique, int longest);


/*
 purpose: To check if the word tokenized has already been read, if it hasn't it add its to the unique words data structure, if it has been read it adds the line number it was from to the existing node with the word already in it.
 input:takes in a tokenized word from input as well as the line number and number of unique words so far
 output:returns 1 if the tokenized word named unique has already been read, if so it adds one to the node at the arrary line number that was already there for the word; returns 0 if hasn't been read before and initializes a new node for it and adds one to the line num it occured at in the array of the node
 */
int insert_unique(char*unique, int linenum,int word_count){
    //loops through all words already read
    for(int i = 0; i<=word_count; i++){
        //compares the unique word to all other unique words
        if(strcmp(unique_words[i].word,unique)==0){
            //adds a counter in the existing data structure in the index of the line it came from
            unique_words[i].lines[linenum]++;
            //returns 1 if the word already existed in data structure
            return 1;
        }
    }
    //if it hasn't been read, make a new node for this word
    strncpy(unique_words[word_count].word, unique, MAX_WORD_SIZE);
    //adds a counter in the new data structure in the index of the line it came from
    unique_words[word_count].lines[linenum]++;
    //returns 0 to signal a new word
    return 0;
}

/*
 purpose: Takes the exempt file name and reads the file and stores each word in a global 2D array to later be compared
 input: Char* that contains the file name of the exempt file
 output:none, reads exempt file into a global 2D array
 */
void read_exempt(char* file){
    /*reads file*/
    FILE* input_file = fopen(file, "r" );
    int i = 0;
    /*reads each word into a global 2D array and stops when fgets hits the end of file and returns null*/
    while(fgets(exempt_words[i], MAX_WORD_SIZE, input_file) != NULL){
        i++;
    }
    /*records the length of the current strings*/
    int len;
    /*loops through array to check if the last character is a new line character*/
    for(int j = 0; j< MAX_EXEMPT_WORDS; j++){
        len = strlen(exempt_words[j]);
        /*to make sure we don't go out of array bounds if len == 0*/
        if(len!=0){
            if(exempt_words[j][len-1] == '\n'){
                exempt_words[j][len-1] = '\0';
            }
        }
    }
    fclose(input_file);
}
/*
 purpose:to compare the input word to each exempt word and returns 0 if it is an exempt word and returns 1 if it is not an exempt word
 input: takes in string and compares it to each exempt word
 output: returns 0 if the word is an exempt word and returns 1 if the word is not an exempt word
 */
int compare_exempt(char* word){
  for(int i = 0; i<100; i++){
    if(strcmp(word, exempt_words[i]) == 0){
      return 0;
    }
  }
  return 1;
}

/*
 purpose: reads input file and stores it in a global 2D array and takes out new line characters
 input: String with file name
 output: none, reads file into global array
 */
void read_input_file(char* file){
 FILE* input_file = fopen(file, "r" );
    int i = 0;
    /*reads file into a global 2D array*/
    while(fgets(input[i], MAX_INPUT_CHARS, input_file) != NULL){
        i++;
    }
    int len;
    for(int j = 0; j< MAX_INPUT_LINES; j++){
        len = strlen(input[j]);
        /*prevents going out of bounds*/
        if(len!=0){
            /*replaces new line character with null character*/
            if(input[j][len-1] == '\n'){
                input[j][len-1] = '\0';
            }
        }
        
    }
    fclose(input_file);
}

/*
 purpose: Goes through the global 2D array that holds the input lines and tokenizes the array.  After tokenzing, it checks if the token is a an exempt word, calls insert_unique to check if the word has already been read and deals with both cases.  The function then returns the number of unique words and doesn't count words that were read twice
 input:nothing, reads the global 2D array that holds all the input lines
 output: number of unique words read, it inserts all the unique words into a global array of structs through the insert_unique function
 */

 int read_table(){
    /*First will initialize all the variables each node*/
    for(int k = 0; k<MAX_UNIQUE_WORDS; k++){
        memset(unique_words[k].lines,0,MAX_INPUT_LINES);
    }
    int j =0;
    char input_line[MAX_INPUT_CHARS];
    for(int i = 0; i < MAX_INPUT_LINES; i++){
        strncpy(input_line,input[i],MAX_INPUT_CHARS);
        char *read_word;
        read_word = strtok(input_line, " ");
        while (read_word!=NULL) {
            if(compare_exempt(read_word)==1){
                if(insert_unique(read_word,i,j)== 0){
                      j++;
                }
            }
            read_word = strtok (NULL, " ");
        }

    }
     return j;
 }
/*
 repurposed from wordlist.c written by Dr.Zastre.  Rewrote functionality to work with the node structure and the strcmp of the words in both nodes being compared
 */
int strcmp_wrapper(const void *a, const void *b) {
    /*casts each pointer as a node pointer*/
    node *sa = (node *)a;
    node *sb = (node *)b;
    /*returns comparison of the words in each struct*/
    return(strcmp(sa->word, sb->word));
}

/*
 purpose:goes through the unique words global array structure and finds the longest word and returns its length
 input: the unique word count
 output: the length of the longest word in the global unique words array
 */
int get_longest_word(int wordcount){
    int longest = 0;
    for(int i = 0; i<wordcount;i++){
        if(strlen(unique_words[i].word)>longest){
            longest = strlen(unique_words[i].word);
        }
    }
    return longest;
}

/*
 purpose: Goes through array in input node and finds each line the word occured in.  Then it prints out the Capitalized word and each line it appears in followed by the line numbers and astrisk if it appears more than once in the line
 input: node with unique word and the length of the longest word in the unique words structure
 output: prints out the capitalized word followed by each line it was read from and then the line number it came from with an asterisks beside if it appears more than once
*/
void print_node(node unique, int longest){
    /*to store the line that will be printed*/
    char output_line[110];
    /*goes through the array held in node*/
    for(int i = 0; i < MAX_INPUT_LINES; i++){
        /*if the node appeared in that line index, it is greater than 0, and it is 0 otherwise*/
        if(unique.lines[i] > 0){
            int j = 0;
            /*copies the unique word into the line that is to be printed and capitalizes each char*/
            while(unique.word[j] != '\0'){
                output_line[j] = toupper(unique.word[j]);
                j++;
            }
            /*puts white space from the end of the word to the index of the longest word + 2 as per specifications*/
            while(j<longest+2){
                output_line[j] = ' ';
                j++;
            }
            /*copies the line that the word was read from*/
            for(int k = 0;input[i][k]!='\0';k++,j++){
                output_line[j] = input[i][k];
            }
            /*adds the brackets with the line number*/
            output_line[j++] = ' ';
            output_line[j++] = '(';
            /*since the ith line is indexed at i-1*/
            int y = i + 1;
            /*checks if the line is a 2 digit number*/
            if(y>=10){
                output_line[j++] = y/10+'0';
            }
            output_line[j++] = y%10+'0';
            /*if the line contains more than one occurance, adds an asterisk*/
            if(unique.lines[i]>1){
                output_line[j++] = '*';
            }
            output_line[j++] = ')';
            output_line[j++] = '\0';
            /*prints line after everything is added*/
            printf("%s\n",output_line);
        }
    }
    
}

int main(int argc, char* argv[]){
    /*checks where the exception and input file are located in the command line argument array*/
    for(int k =1; k<argc; k++){
        if(strcmp(argv[k],"-e")==0){
            /*reads exempt file to grab exempt words*/
            read_exempt(argv[++k]);
        }else{
            /*reads the input file*/
             read_input_file(argv[k]);
        }
    }
    /*tokenizes the 2D array that holds the input file*/
    int wordcount = read_table();
    /*gets the longest word in the unique_words structure*/
    int longest_word = get_longest_word(wordcount);
    /*
     repurposed from wordlist.c written by Dr.Zastre. Sorts the unique_words data structure array
     */
    qsort(unique_words, wordcount, sizeof(node),strcmp_wrapper);
    /*iterates through unique words structure and prints each node*/
    for(int i = 0; i <wordcount; i++){
        print_node(unique_words[i],longest_word);
    }
    
  return 0;


}
