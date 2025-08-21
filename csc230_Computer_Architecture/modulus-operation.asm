# UVic CSC 230, Spring 2022
# Assignment #1, part C
# (Base code copyright 2022 Mike Zastre)

# Compute S % T, where S must be in $12, T must be in $13,
# and S % T must be in $19.

.text
start:
	lw $12, testcase3_S
	lw $13, testcase3_T

# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	
# Your code here.

	addi $15, $0, 1		# store value of 1 in 15 for comparison
	add $19, $0, $12	# assign input value into $19 (not sure if we were allowed to change value in $12)?
	beq $13, $0, mod0	# branch if modulo value is 0
loop1:
	slt $16, $19, $13	# set $16 to non-zero value if current value is less than modulo value
	beq $15, $16, exit
	sub $19, $19, $13	# subtract modulo value from current value and reassign new value in same register
	b loop1
mod0:
	addi $19, $0, -1	# assign value to -1 if modulo value is 0

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

# testcase1: 219 % 61 = 36
#
testcase1_S:
	.word	219
testcase1_T:
	.word 	61
	
# testcase2: 24156 % 77 = 55
#
testcase2_S:
	.word	24156
testcase2_T:
	.word 	77

# testcase3: 21 % 0 = -1
#
testcase3_S:
	.word	21
testcase3_T:
	.word 	0
	
# testcase4: 33 % 120 = 33
#
testcase4_S:
	.word	33
testcase4_T:
	.word 	120
	
testcaseEVALS:
	.word	391
testcaseEVALT:
	.word	0
