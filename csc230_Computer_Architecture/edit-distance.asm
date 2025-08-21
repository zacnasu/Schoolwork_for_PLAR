# UVic CSC 230, Spring 2022
# Assignment #1, part B
# (Starter code copyright 2022 Mike Zastre)

# Determine the edit distance of values in registers $12 and $13
# Store this distance in register $20


.text

start:
	lw $12, testcase1_a  # STUDENTS MAY MODIFY THE TESTCASE GIVEN IN THIS LINE
	lw $13, testcase1_b  # STUDENTS MAY MODIFY THE TESTCASE GIVEN IN THIS LINE
	
# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

# Your work here.
	addi $11, $0, 32	# max amount of loops
	addi $21, $0, 0x1	# bitmask	
	add $22, $0, $0		# loop counter
	xor $18, $12, $13	# bitwise xor
loop1:
	beq $22, $11, exit
	and $19, $18, $21 
	addi $22, $22, 1 
	sll $21, $21, 1    # moves bit mask 1 bit to the left
	beqz $19 loop1	   # continue if mask brings up nothing, else loop back up
	addi $20, $20, 1   
	b loop1

# ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
# STUDENTS MAY MODIFY CODE ABOVE

# The three lines of code below will eventually be
# explained in a bit more detail in CSC 230. In
# essence, MARS provides something similar to the
# system-call interface provided by many operating
# systems -- and one very important task an OS
# must do is to stop/terminate a running job. In
# essence, the code below causes MARS to stop your
# program in a safe way. (And believe you me --
# throughout the term there will be times when you
# write programs that do *not* end safely because
# of a bug (or three!).

exit:
	add $2, $0, 10
	syscall
		

.data

# Note: These test cases are not exhaustive. The teaching team
# will use other test cases when evaluating student submissions
# for this part of the assignment.

# testcase1: edit distance is 32
testcase1_a:
	.word	0x00000000
testcase1_b:
	.word   0xffffffff
	    

# testcase2: edit distance is 11
testcase2_a:
	.word	0xfacefade
testcase2_b:
	.word   0xdeadbeef
	
	
# testcase3: edit distance is 0
testcase3_a:
	.word	0xaaaa5555
testcase3_b:
	.word   0xaaaa5555
	
	
# testcase4: edit distance is 20
testcase4_a:
	.word	0xc6c6c6c6
testcase4_b:
	.word   0x51515151
