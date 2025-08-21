
	.data
ARRAY_A:
	.word	21, 210, 49, 4
ARRAY_B:
	.word	21, -314159, 0x1000, 0x7fffffff, 3, 1, 4, 1, 5, 9, 2
ARRAY_Z:
	.space	28
NEWLINE:
	.asciiz "\n"
SPACE:
	.asciiz " "
		
	
	.text  
main:	
	la $a0, ARRAY_A
	addi $a1, $zero, 4
	jal dump_array
	
	la $a0, ARRAY_B
	addi $a1, $zero, 11
	jal dump_array
	
	la $a0, ARRAY_Z
	lw $t0, 0($a0)
	addi $t0, $t0, 1
	sw $t0, 0($a0)
	addi $a1, $zero, 9
	jal dump_array
		
	addi $v0, $zero, 10
	syscall

# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	
	
dump_array:
	addi $sp, $sp, -12
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	addi $s0, $zero, 0 # initialize counter
	add $s1, $zero, $a0 # adds address in $a0 to $s1
loop:	
	lw $a0, 0($s1) # loads word previously stored in address $a1 into $a1
	addi $s1, $s1, 4 # indexes $s1 to next word to prepare for next iteration
	addi $v0, $zero, 1 # code for print int
	syscall
	addi $s0, $s0, 1 # index counter
	
	la $a0, SPACE # prints space
	addi $v0, $zero, 4
	syscall
	
	bne $a1, $s0, loop # back up to loop if counter does not match $a1
	
end_array:
	la $a0, NEWLINE # prints newline
	addi $v0, $zero, 4
	syscall
	lw $s1, 8($sp)
	lw $s0, 4($sp)
	lw $ra, 0($sp)
	addi $sp, $sp, 12
	jr $ra
	
	
	
# ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
# STUDENTS MAY MODIFY CODE ABOVE
