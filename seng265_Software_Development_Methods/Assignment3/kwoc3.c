/*
 * SENG 265 Assignment 3
 * Zachary Nasu
 * V00911790
 *
 * listy.c and listy.h altered to include dynamically
 * allocated memory for the node_t text string.
 */

 #define _GNU_SOURCE
 #include <sys/types.h>
 #include <assert.h>
 #include <stdio.h>
 #include <stdlib.h>
 #include <string.h>
 #include <ctype.h>
 #include "emalloc.h"
 #include "listy.h"


/*
 * Used for storing an array of lines from input file
 */

typedef struct line_structure{
    char* line;
}line_structure;

typedef struct input_lines{
    line_structure* list_array;
    int longest_line;
    int num_lines;
}input_lines;


/*
 * Used for storing linked list of exempt exempt words
 * from the exception file.  Uses node_t from listy.c and
 * listy.h .
 */

typedef struct exempt_words{
    node_t * head;
}exempt_words;



/*
 * Used for storing the unique words and records
 * the lines the word came from and how many times
 * it appeared in that line.
 */

typedef struct inst{
    int line;
    int rep;
    struct inst* next;
}inst;

typedef struct unique_node{
    char* word;
    inst* instances;
    struct unique_node* left;
    struct unique_node* right;
}unique_node;

typedef struct unique_BST{
    unique_node* root;
    int longest_word;
}unique_BST;


/*
 *
 * Function Signatures
 *
 */

exempt_words* read_exempt(char* file);

input_lines* read_input(char*file);

int check_exeption(exempt_words* exempt,char*word);

inst* new_instance(inst* curr,int line);

unique_node* new_unique(char*word,int line);

unique_node* tree_insert(unique_node* node,char*new_word, int line);

void string_to_upper(char*word);

unique_BST* tokenize_input(input_lines* input,exempt_words* exempt);

void println(char*word, char*input_line, int line_num,int longest_word,int rep);

void print_in_order(unique_node* curr, input_lines*input, int longest_word);

void free_tree(unique_node* node);

void free_exception(node_t* node);

void free_input(input_lines* input);




/*
 * Takes in the file name of the exception file and then reads the
 * exception words from the file. The function then returns a
 * linked list of words using the node_t structure from listy.h.
 */

exempt_words* read_exempt(char* file){

    FILE* file_ptr = fopen(file,"r");
    if(file_ptr == NULL){
        exit(1);
    }

    char* current_line = NULL;
    size_t len = 0;
    ssize_t read = 0;
    node_t* temp_node;
    char* new_word;

    exempt_words* exempt_list = (exempt_words*)emalloc(sizeof(exempt_words));
    exempt_list->head = NULL;

    //uses some code from Dr. Zastre's teaching material
    while((read=getline(&current_line, &len, file_ptr)) != -1){

        if(current_line[read-1]=='\n'){
            current_line[--read]='\0';
        }

        new_word = (char*)emalloc(read+1);
        strncpy(new_word, current_line, read+1);
        temp_node = new_node(new_word);

        exempt_list->head = add_end(exempt_list->head, temp_node);
    }

    free(current_line);
    fclose(file_ptr);
    return exempt_list;
}



/*
 * Takes in input file name and then reads the file.
 * The function stores each line in an array using
 * the input_lines and line_structure structs
 * and returns a pointer to an input_line
 */

input_lines* read_input(char*file){

    FILE*file_ptr = fopen(file,"r");
    if(file_ptr == NULL){
        exit(1);
    }

    char*current_line = NULL;
    int lines;
    ssize_t read= 0;
    size_t max_len=0;
    line_structure* temp_ptr;
    char*new_line;

    input_lines* lines_array = (input_lines*)emalloc(sizeof(input_lines));
    lines_array->list_array = NULL;


    //uses some code from Dr. Zastre's teaching material
    for(lines = 0;(read=getline(&current_line, &max_len, file_ptr)) != -1; lines++){

        if(current_line[read-1]=='\n'){
            current_line[--read]='\0';
        }

        //+1 to make room for null character
        new_line = (char*)emalloc(read+1);
        strncpy(new_line, current_line, read+1);

        if(lines ==0 ){
            lines_array->list_array = (line_structure*)emalloc(sizeof(line_structure));

        }else{
            temp_ptr = (line_structure*)realloc(lines_array->list_array, (lines+1)*sizeof(line_structure));

            if(temp_ptr==NULL){
                printf("realloc in read_input failed, exiting");
                exit(1);
            }

            lines_array->list_array = temp_ptr;
        }

        lines_array->list_array[lines].line = new_line;
    }

    free(current_line);
    fclose(file_ptr);
    lines_array->num_lines = lines;
    lines_array->longest_line = max_len;
    return lines_array;
}



/*
 * Checks if word is in the list of exempt words.
 * returns 0 if it is in the excepton list and 1
 * if it is not.
 */

int check_exeption(exempt_words* exempt,char*word){
    if(exempt==NULL){
        return 1;
    }

    for(node_t* temp = exempt->head;temp!=NULL;temp = temp->next){

        if(strcasecmp(temp->text,word)==0){
            return 0;
        }
    }

    return 1;
}



/*
 * Recursively goes through list of instances.
 * If the line number is already there, it adds
 * a repitition. Otherwise it creates a new instance
 * and add it to the end of the list.
 */

inst* new_instance(inst* curr,int line){
    if(curr==NULL){
        inst* temp_inst = (inst*)emalloc(sizeof(inst));
        temp_inst->line = line;
        temp_inst->next = NULL;
        temp_inst-> rep = 0;
        return temp_inst;

    }else if(curr->line == line){
        curr->rep++;
        return curr;

    }else{
        curr->next = new_instance(curr->next,line);
        return curr;

    }
}



/*
 * Creates new unique_node and initializes it
 * with a word and an instance with its line.
 */

unique_node* new_unique(char*word,int line){
    unique_node* new_node = (unique_node*)emalloc(sizeof(unique_node));

    char* new_word = (char*)emalloc(sizeof(char)*(strlen(word)+1));
    strncpy(new_word,word,strlen(word)+1);

    new_node->word = new_word;
    new_node->right = NULL;
    new_node->left = NULL;

    new_node->instances = new_instance(NULL,line);

    return new_node;
}



/*
 * Recursively traverses the tree of unique_nodes and
 * checks if the word matches any word currently in the tree.
 * node. If it matches, it creates a new instance in that
 * If there are no matches, it creates a new unique_node.
 */

unique_node* tree_insert(unique_node* node,char*new_word, int line){
    if(node == NULL){
        unique_node* new_node = new_unique(new_word,line);
        return new_node;

    }else if(strcasecmp(node->word, new_word)==0){
        node->instances = new_instance(node->instances,line);

    }else if(strcasecmp(node->word, new_word)<0){
        node->right = tree_insert(node->right,new_word,line);

    }else{
        node->left = tree_insert(node->left,new_word,line);
    }
    return node;
}


/*
 * Alters every character in word to an
 * upper character if it is lowercase.
 */

void string_to_upper(char*word){
    if(word==NULL){
        return;
    }
    char* temp = word;

    while(*temp!= '\0'){
        *temp= toupper(*temp);
        temp++;
    }
}



/*
 * Takes in a structure that contains an array of exempt_words.
 * The function then tokenizes each line and compares each word
 * to the list of exempt words. If the word is unique, it then
 * inserts it into the binary search tree of unique words.
 */

unique_BST* tokenize_input(input_lines* input,exempt_words* exempt){
    char temp_string[input->longest_line];
    char*read_word;

    unique_BST* unique_tree = (unique_BST*)emalloc(sizeof(unique_BST));
    unique_tree->root = NULL;
    unique_tree->longest_word = 0;


    for(int i = 0; i<input->num_lines; i++){

        strncpy(temp_string,input->list_array[i].line,input->longest_line);
        read_word = strtok(temp_string, " ");

        while (read_word!=NULL){
            if(check_exeption(exempt,read_word)==1){

                if(strlen(read_word)>unique_tree->longest_word){
                    unique_tree->longest_word = strlen(read_word);
                }

                string_to_upper(read_word);
                unique_tree->root = tree_insert(unique_tree->root,read_word,i);
            }
            read_word = strtok (NULL, " ");
        }
    }

    return unique_tree;
}



/*
 * Formats the word, input line and line number in the desired format
 * as according to assignment specifications
 */

void println(char*word, char*input_line, int line_num,int longest_word,int rep){

    //makes a string of spaces to put in between the word and input line
    int spaces = longest_word - strlen(word)+2;

    //add one for the null character
    char space_string[spaces+1];
    memset(space_string,' ',spaces);
    space_string[spaces] = '\0';

    //if there is more than one occurance on the line
    if(rep>0){
        printf("%s%s%s (%d*)\n",word,space_string,input_line,line_num+1);
    }else{
        printf("%s%s%s (%d)\n",word,space_string,input_line,line_num+1);
    }
}


/*
 * Goes through the unique words BST in an in-order traversal and calls
 * println to format the lines correctly for each instance of the word.
 */

void print_in_order(unique_node* curr, input_lines*input, int longest_word){
    if(curr == NULL){
        return;
    }
    print_in_order(curr->left,input,longest_word);

    inst* instances = curr->instances;

    while(instances!= NULL){
        println(curr->word, input->list_array[instances->line].line, instances->line, longest_word,instances->rep);
        instances = instances->next;
    }

    print_in_order(curr->right, input, longest_word);
}

/*
 * Frees the BST of unique words using a recursive DFS.
 * Frees list of instances iteratively.
 */

void free_tree(unique_node* node){

    if(node == NULL){
        return;
    }

    free_tree(node->left);
    free_tree(node->right);

    free(node->word);

    inst*curr = node->instances;
    inst*prev = NULL;

    while(curr!=NULL){
        prev = curr;
        curr = curr->next;
        free(prev);
    }

    free(node);
}



/*
 * Frees linked list of exception words recursively.
 */

void free_exception(node_t* node){
    if(node == NULL){
        return;
    }
    free_exception(node->next);
    free(node->text);
    free(node);
}



/*
 * Frees the array of input lines iteratively.
 */

void free_input(input_lines* input){
    for(int i = 0; i < input->num_lines;i++){
        free(input->list_array[i].line);
    }

    free(input->list_array);
    free(input);
}





int main(int argc, char *argv[]){

    //repurposed from kwoc3.c starter file
    char *exception_file = NULL;
    char *input_file = NULL;
    int i;
    int exception = 0;

    //repurposed from kwoc3.c starter file
    for (i = 1; i < argc; i++) {
        if (strcmp(argv[i], "-e") == 0 && i+i <= argc) {
            exception_file = argv[++i];
            exception = 1;
        }else{
            input_file = argv[i];
        }
    }

    input_lines* input = read_input(input_file);
    unique_BST* unique_tree;
    exempt_words* exempt_list = NULL;

    //reads exception file if exists
    if(exception == 1){
        exempt_list = read_exempt(exception_file);
    }

    //Reading input, comparing it to exempt list and then storing in BST
    unique_tree = tokenize_input(input,exempt_list);

    //Printing in order to match assignment specifications
    print_in_order(unique_tree->root, input,unique_tree->longest_word);

    //Freeing all dyanmically allocated data structures
    free_tree(unique_tree->root);
    free(unique_tree);

    if(exception == 1){
        free_exception(exempt_list->head);
        free(exempt_list);
    }

    free_input(input);

    exit(0);
}
