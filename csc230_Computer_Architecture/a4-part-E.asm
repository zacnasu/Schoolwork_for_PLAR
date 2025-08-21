# Skeleton file provided to students in UVic CSC 230, Spring 2022 
# Original file copyright Mike Zastre, 2022

.include "a4support.asm"

.data

.eqv	MAX_ARRAY_SIZE 1024

.align 2
ARRAY_1:	.space MAX_ARRAY_SIZE
ARRAY_2:	.space MAX_ARRAY_SIZE
ARRAY_3:	.space MAX_ARRAY_SIZE
ARRAY_4:	.space MAX_ARRAY_SIZE
ARRAY_5:	.space MAX_ARRAY_SIZE
ARRAY_6:	.space MAX_ARRAY_SIZE
ARRAY_7:	.space MAX_ARRAY_SIZE
ARRAY_8:	.space MAX_ARRAY_SIZE

# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

FILENAME_1:	.asciiz "integers-10-314.bin"
FILENAME_2:	.asciiz "integers-10-1592.bin"

# ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
# STUDENTS MAY MODIFY CODE ABOVE



.globl main
.text 
main:

# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	la $a0, FILENAME_1
	la $a1, ARRAY_1
	jal read_file_of_ints
	add $s1, $zero, $v0
	
	la $a0, FILENAME_2
	la $a1, ARRAY_2
	jal read_file_of_ints
	add $s2, $zero, $v0
	
	# A3 = accumulate_max(A1)
	la $a0, ARRAY_1
	la $a1, ARRAY_3
	add $a2, $s1, $zero
	jal accumulate_max
	
	# A4 = accumulate_max(A2)
	la $a0, ARRAY_2
	la $a1, ARRAY_4
	add $a2, $s2, $zero
	jal accumulate_max
	
	# A5 = reverse(A3)
	la $a0, ARRAY_3
	la $a1, ARRAY_5
	add $a2, $s1, $zero
	jal reverse_array
	
	# A6 = pairwise_max(A4, A5)
	la $a0, ARRAY_4
	la $a1, ARRAY_5
	la $a2, ARRAY_6
	add $a3, $s1, $zero
	jal pairwise_max
	
	# A7 = accumulate_sum(A6)
	la $a0, ARRAY_6
	la $a1, ARRAY_7
	add $a2, $s1, $zero
	jal accumulate_sum
	
	# output to console: A7[-1]
	la $s3, ARRAY_7
	sll $s4, $s1, 2
	add $s3, $s3, $s4
	addi $s3, $s3, -4
	add $a0, $s3, $zero
	addi $a1, $zero, 1
	jal dump_ints_to_console
	
	
	
	# WRITE YOUR SOLUTION TO THE PART E PROBLEM
	# HERE...
	
	
	# Get outta here.		
	add $v0, $zero, 10
	syscall	
	

	
# COPY YOUR PROCEDURES FROM PARTS A, B, C, and D BELOW
# THIS POINT.
	
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
