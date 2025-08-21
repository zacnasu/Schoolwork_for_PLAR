#!/usr/bin/env python3
import sys
import fileinput

def main():
    if len(sys.argv) < 3: #accounts for case where there is no exclusion file
        input = sys.argv[1]
        EX = False # stores if there exists an exclusion file
    else:#accounts for case where an exclusion file exists
        EX = True #stores if there exists an exclusion file
        if sys.argv[1] == '-e': #case where the exception file comes first
            exclusion = sys.argv[2]
            input = sys.argv[3]
        else:#case where input file comes first then exclusion file
            exclusion = sys.argv[3]
            input = sys.argv[1]
    #opens input file and returns a list where the element is an untokenized line
    input_list = read_input(input)
    #accounts for case where exclusion file exists
    if(EX == True):
        #opens exclusion file and returns a list of the exclusion words
        exclusion_list = read_exclusion(exclusion)
        #returns dictionary where key is the unique word and value is a list with the lines the word appears in, includes optional argument of the exclusion list
        unique_dictionary = unique_reader(input_list,exclusion_list)
    #accounts for case where no exclusion file is provided
    else:
        #returns dictionary where key is the unique word and value is the list with the lines the word appears in, does not include optional exclusion list
        unique_dictionary = unique_reader(input_list)
    #goes through unique dicionary and prints the desired output
    print_unique(unique_dictionary,input_list)

#purpose: to read the input file and return a list where elements are the lines from the input file
#input: takes in file name
#output: returns a list where the elements are the lines
def read_input(input_file_name):
    try:
        fileptr = open(input_file_name, 'r')
    except FileNotFoundError:
        print("Invalid Input File")
        sys.exit(1)
    #goes through file line by line and takes out newline
    input_list = [line.strip() for line in fileptr]
    fileptr.close()
    return input_list

#purpose: if an exclusion file exists, it reads the exclusion file and returns a list of the exclusion words with all words uppercased
#input: takes in exclusion file name
#output: returns list of exclusion words
def read_exclusion(exclusion_file_name):
    try:
        fileptr = open(exclusion_file_name, 'r')
    except FileNotFoundError:
        print("Invalid Exclusion File")
        sys.exit(1)
    exclusion_list = (fileptr.read()).split()
    exclusion_list = [word.upper() for word in exclusion_list]
    fileptr.close()
    return exclusion_list

#purpose: tokenizes each input file line and checks if they are a unique word. If unique, it gets added to a dictionary where the key is the word and the value is a list of which line the word appeared in
#input: takes in input list, optional exclusion list if it exists
#output: a dictionary where the keys are the unique words and the values are lists of the indices where the word appears
def unique_reader(input_list,exclusion_list = []):
    unique_dictionary = {}
    for i in range(0,len(input_list)):
        words = input_list[i].split()
        for word in words:
            word = word.upper()
            if(exclusion_list.count(word)==0):
                if(unique_dictionary.get(word,None)==None):
                    unique_dictionary[word] = [i]
                else:
                    unique_dictionary[word].append(i)
    return unique_dictionary

#purpose: takes in the unique dictionary and prints each unique word with each line it came from with the line number
#input: takes in a dictionary containing the unique words as keys and a list containing the line occurances
#output: none, prints out the unique word and the line it comes from and the line number in desired format
def print_unique(unique_dictionary,input_list):
    words = list(unique_dictionary.keys())
    words.sort()
    longest = find_longest_word(words)
    for word in words:
        #creates copy of the list of line occurances and creates new list of only unique values
        unique_lines = list(set(unique_dictionary[word].copy()))
        unique_lines.sort()
        for line_num in unique_lines:
            #accounts if the word appears more than once on the line
            if (unique_dictionary[word].count(line_num)>1):
                print("%s%s%s (%s*)"%(word,' '*(longest+2-len(word)),input_list[line_num],str(line_num+1)))
            else:
                print("%s%s%s (%s)"%(word,' '*(longest+2-len(word)),input_list[line_num],str(line_num+1)))

#purpose: to find longest word size
#input: a list of keys from the unique_dictionary dictionary
#output: an integer that contains the length of the longest key
def find_longest_word(key_list):
    longest = 0
    for key in key_list:
        if len(key)>longest:
            longest = len(key)
    return longest

if __name__ == "__main__":
    main()
