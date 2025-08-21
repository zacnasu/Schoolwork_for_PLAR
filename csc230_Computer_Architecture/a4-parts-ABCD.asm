# Skeleton file provided to students in UVic CSC 230, Spring 2022
# Original file copyright Mike Zastre, 2022

.include "a4support.asm"


.globl main

.text 

main:
# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	la $a0, FILENAME_1
	la $a1, ARRAY_A
	jal read_file_of_ints
	add $s0, $zero, $v0	# Number of integers read into the array from the file
	
	la $a0, ARRAY_A
	add $a1, $zero, $s0
	jal dump_ints_to_console
	
	
	# Part A test
	#
	
	la $a0, ARRAY_A
	la $a1, ARRAY_B
	add $a2, $zero, $s0
	jal accumulate_sum
	
	
	la $a0, ARRAY_B
	add $a1, $zero, $s0
	jal dump_ints_to_console
	
	
	# Part B test
	#
	
	la $a0, ARRAY_A
	la $a1, ARRAY_B
	add $a2, $zero, $s0
	jal accumulate_max
	
	la $a0, ARRAY_B
	add $a1, $zero, $s0
	jal dump_ints_to_console
	
	
	# Part C test
	#
	
	la $a0, ARRAY_A
	la $a1, ARRAY_B
	add $a2, $zero, $s0
	jal reverse_array
	
	la $a0, ARRAY_B
	add $a1, $zero, $s0
	jal dump_ints_to_console
	
	
	# Part D test
	la $a0, FILENAME_1
	la $a1, ARRAY_A
	jal read_file_of_ints
	add $s0, $zero, $v0
	
	la $a0, FILENAME_2
	la $a1, ARRAY_B
	jal read_file_of_ints
	# $v0 should be the same as for the previous call to read_file_of_ints
	# but no error checking is done here...
	
	la $a0, ARRAY_A
	la $a1, ARRAY_B
	la $a2, ARRAY_C
	add $a3, $zero, $s0
	jal pairwise_max
	
	la $a0, ARRAY_C
	add $a1, $zero, $s0
	jal dump_ints_to_console
	
	
	# Get outta here...
	add $v0, $zero, 10
	syscall	
	
	
# Accumulate sum: Accepts two integer arrays where the value to be
# stored at each each index in the *second* array is the sum of all
# integers from the index back to towards zero in the first
# array. The arrays are of the same size; the size is the third
# parameter.
#
accumulate_sum:
	addi $sp, $sp -24
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	sw $s4, 20($sp)
	add $s0, $zero, $zero # initialize sum to zero
sum_loop:
	beq $a2, $zero, end_sum_loop # check elements left is eq to zero
	lw $s1, 0($a0) # load next element from input array
	add $s0, $s0, $s1 # add to current sum
	sw $s0, 0($a1) # output sum to output array
	addi $a0, $a0, 4 # move forward one int
	addi $a1, $a1, 4 # move forward one int
	addi $a2, $a2, -1 # one less element in array
	beq $zero, $zero, sum_loop
end_sum_loop:
	lw $s4, 20($sp)
	lw $s3, 16($sp)
	lw $s2, 12($sp)
	lw $s1, 8($sp)
	lw $s0, 4($sp)
	lw $ra, 0($sp)
	addi $sp, $sp, 24
	jr $ra


# Accumulate max: Accepts two integer arrays where the value to be
# stored at each each index in the *second* array is the maximum
# of all integers from the index back to towards zero in the first
# array. The arrays are of the same size;  the size is the third
# parameter.
#
accumulate_max:
	addi $sp, $sp -24
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	sw $s4, 20($sp)
	add $s0, $zero, $zero # initialize current max as 0
max_loop:
	beq $a2, $zero, end_max_loop # break once all elements are gone through
	lw $s1, 0($a0) # load current element
	slt $s2, $s0, $s1 # compare if current element is more than max
	beq $s2, $zero, not_max # branch if current element is less than current max
	addi $s0, $s1, 0 # move new max $s1 into $s0
not_max:
	sw $s0, 0($a1) # store current max
	addi $a0, $a0, 4 # move input one word forward
	addi $a1, $a1, 4 # move output one word forward
	addi $a2, $a2, -1 # one less element
	beq $zero, $zero, max_loop
end_max_loop:
	lw $s4, 20($sp)
	lw $s3, 16($sp)
	lw $s2, 12($sp)
	lw $s1, 8($sp)
	lw $s0, 4($sp)
	lw $ra, 0($sp)
	addi $sp, $sp, 24
	jr $ra
	
	
# Reverse: Accepts an integer array, and produces a new
# one in which the elements are copied in reverse order into
# a second array.  The arrays are of the same size; 
# the size is the third parameter.
#
reverse_array:
	addi $sp, $sp -24
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	sw $s4, 20($sp)
	addi $s2, $a2, 0 # get number of elements
	sll $s2, $s2, 2 # mult number of elements by 4 for word size
	add $a0, $a0, $s2 # start $a0 at end of array +4
rev_loop:
	addi $a0, $a0, -4 # move input array $a0 one word backwards
	beq $a2, $zero, end_rev_loop # end once all elements have been looped through
	lw $s1, 0($a0) # load element backwards from input
	sw $s1, 0($a1) # store element forward in output
	addi $a1, $a1, 4 # move output array one word forward
	addi $a2, $a2, -1 # one less element in array
	beq $zero, $zero, rev_loop
end_rev_loop:
	lw $s4, 20($sp)
	lw $s3, 16($sp)
	lw $s2, 12($sp)
	lw $s1, 8($sp)
	lw $s0, 4($sp)
	lw $ra, 0($sp)
	addi $sp, $sp, 24
	jr $ra
	
	
# Reverse: Accepts three integer arrays, with the maximum
# element at each index of the first two arrays is stored
# at that same index in the third array. The arrays are 
# of the same size; the size is the fourth parameter.
#	
pairwise_max:
	addi $sp, $sp -24
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	sw $s4, 20($sp)
pmax_loop:
	beq $a3, $zero, end_pmax_loop # break once all elements have been looped through
	lw $s0, 0($a0) # load next element from first array
	lw $s1, 0($a1) # load next element from second array
	slt $s2, $s1, $s0 # compare both elements
	beq $s2, $zero, not_first_max # branch if first element is not larger
	addi $s1, $s0, 0 # first element is larger, so copy into $s1
not_first_max:
	sw $s1, 0($a2) # store $s1 into output array as it is greater val
	addi $a0, $a0, 4 # move input1 array forward one word
	addi $a1, $a1, 4 # move input2 array forward one word
	addi $a2, $a2, 4 # move output array forward one word
	addi $a3, $a3, -1 # one less element
	beq $zero, $zero, pmax_loop
end_pmax_loop:
	lw $s4, 20($sp)
	lw $s3, 16($sp)
	lw $s2, 12($sp)
	lw $s1, 8($sp)
	lw $s0, 4($sp)
	lw $ra, 0($sp)
	addi $sp, $sp, 24
	jr $ra
	
	
	
# ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
# STUDENTS MAY MODIFY CODE ABOVE
	

.data

.eqv	MAX_ARRAY_SIZE 1024

.align 2

ARRAY_A:	.space MAX_ARRAY_SIZE
ARRAY_B:	.space MAX_ARRAY_SIZE
ARRAY_C:	.space MAX_ARRAY_SIZE

FILENAME_1:	.asciiz "integers-10-314.bin"
FILENAME_2:	.asciiz "integers-10-1592.bin"


# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv


# In this region you can add more arrays and more
# file-name strings. Make sure you use ".align 2" before
# a line for a .space directive.


# ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
# STUDENTS MAY MODIFY CODE ABOVE
