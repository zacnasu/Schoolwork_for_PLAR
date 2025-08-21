	.data
KEYBOARD_EVENT_PENDING:
	.word	0x0
KEYBOARD_EVENT:
	.word   0x0
KEYBOARD_COUNTS:
	.space  128
NEWLINE:
	.asciiz "\n"
SPACE:
	.asciiz " "
	
	
	.eqv 	LETTER_a 97
	.eqv	LETTER_b 98
	.eqv	LETTER_c 99
	.eqv 	LETTER_D 100
	.eqv 	LETTER_space 32
	
	
	.text  
main:
# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	la $s0, 0xffff0000	# control register for MMIO Simulator "Receiver"
	lb $s1, 0($s0)
	ori $s1, $s1, 0x02	# Set bit 1 to enable "Receiver" interrupts (i.e., keyboard)
	sb $s1, 0($s0)

check_for_event:
	la $s5, KEYBOARD_EVENT_PENDING
	lw $s6, 0($s5)
	beq $s6, $zero, check_for_event # checks for pending keyboard event, true if non-zero
	la $s7, KEYBOARD_EVENT
	lw $s2, 0($s7) # loads keybarod event into $s2
	beq $s2, LETTER_a, is_a # checks if keyboard event is a
	beq $s2, LETTER_b, is_b # checks if keyboard event is b
	beq $s2, LETTER_c, is_c # checks if keyboard event is c
	beq $s2, LETTER_D, is_d # checks if keyboard event is d
	beq $s2, LETTER_space, is_space # checks if keyboard event is a space
	addi $s6, $zero, 0 # sets KEYBOARD_EVENT back to 0
	sw $s6, 0($s5)
	beq $zero, $zero, check_for_event

is_a:
	la $s3, KEYBOARD_COUNTS
	lw $s4, 0($s3)
	addi $s4, $s4, 1 # indexes first word in KEYBOARD_COUNTS
	sw $s4, 0($s3)
	addi $s6, $zero, 0 # sets KEYBOARD_EVENT back to 0
	sw $s6, 0($s5)
	beq $zero, $zero, check_for_event

is_b:
	la $s3, KEYBOARD_COUNTS
	lw $s4, 4($s3)
	addi $s4, $s4, 1 # indexes second word in KEYBOARD_COUNTS
	sw $s4, 4($s3)
	addi $s6, $zero, 0 # sets KEYBOARD_EVENT back to 0
	sw $s6, 0($s5)
	beq $zero, $zero, check_for_event

is_c:
	la $s3, KEYBOARD_COUNTS
	lw $s4, 8($s3)
	addi $s4, $s4, 1 # indexes third word in KEYBOARD_COUNTS
	sw $s4, 8($s3)
	addi $s6, $zero, 0 # sets KEYBOARD_EVENT back to 0
	sw $s6, 0($s5)
	beq $zero, $zero, check_for_event

is_d:
	la $s3, KEYBOARD_COUNTS
	lw $s4, 12($s3)
	addi $s4, $s4, 1 # indexes fourth word in KEYBOARD_COUNTS
	sw $s4, 12($s3)
	addi $s6, $zero, 0 # sets KEYBOARD_EVENT back to 0
	sw $s6, 0($s5)
	beq $zero, $zero, check_for_event


# prints out each of the four words in KEYBOARD_COUNTS with a space in between
is_space:
	la $s3, KEYBOARD_COUNTS

	lw $a0, 0($s3)
	addi $v0, $zero, 1
	syscall
	
	la $a0, SPACE
	addi $v0, $zero, 4
	syscall
	
	lw $a0, 4($s3)
	addi $v0, $zero, 1
	syscall
	
	la $a0, SPACE
	addi $v0, $zero, 4
	syscall
	
	lw $a0, 8($s3)
	addi $v0, $zero, 1
	syscall

	la $a0, SPACE
	addi $v0, $zero, 4
	syscall
	
	lw $a0, 12($s3)
	addi $v0, $zero, 1
	syscall

	la $a0, NEWLINE
	addi $v0, $zero, 4
	syscall
	
	addi $s6, $zero, 0
	sw $s6, 0($s5)
	beq $zero, $zero, check_for_event

	.kdata

	.ktext 0x80000180
#   ** parts of code taken from lab **
__kernel_entry:

	mfc0 $k0, $13		# $13 is the "cause" register in Coproc0
	andi $k1, $k0, 0x7c	# bits 2 to 6 are the ExcCode field (0 for interrupts)
	srl  $k1, $k1, 2	# shift ExcCode bits for easier comparison
	beq $zero, $k1, __is_interrupt
	
__is_exception:
	beq $zero, $zero, __exit_exception
	
__is_interrupt:
	andi $k1, $k0, 0x0100	# examine bit 8
	bne $k1, $zero, __is_keyboard_interrupt	 # if bit 8 set, then we have a keyboard interrupt.
	
	beq $zero, $zero, __exit_exception	# otherwise, we return exit kernel
	
__is_keyboard_interrupt:
	la $t6, KEYBOARD_EVENT_PENDING
	lw $t7, 0($t6)
	addi $t7, $t7, 1 # changes keybaord pending to 1 instead of 0
	sw $t7, 0($t6)
	la $k0, 0xffff0004
	la $t6, KEYBOARD_EVENT
	lw $t7, 0($k0) # loads keyboard event from $k0
	sw $t7, 0($t6) # saves keyboard event into KEYBOARD_EVENT
	
	beq $zero, $zero, __exit_exception	
__exit_exception:
	eret
	
# ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
# STUDENTS MAY MODIFY CODE ABOVE

	
