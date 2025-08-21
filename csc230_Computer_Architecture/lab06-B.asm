.text
	addi $s0, $zero, 5
loop:
	beq $s0, $zero, finish
	
	jal display_dashes
	jal delay_400_msec

	jal display_blanks
	jal delay_400_msec
	
	jal display_digits_21
	jal delay_400_msec
	
	jal display_blanks
	jal delay_400_msec
	
	addi $s0, $s0, -1
	
	beq $zero, $zero, loop
	
finish:
	addi $v0, $zero, 10
	syscall


# Use only $s0 - $s7 for computation in
# this procedure.

display_dashes:	
	
	################
	# Display dashes
	addi $sp, $sp -20
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	
	la $s0, 0xffff0011
	la $s1, 0xffff0010

	addi $s2, $zero, 0x40
	addi $s3, $zero, 0x40
	
	sb $s2, 0($s0)
	sb $s3, 0($s1)
	
	lw $s3, 16($sp)
	lw $s2, 12($sp)
	lw $s1, 8($sp)
	lw $s0, 4($sp)
	lw $ra, 0($sp)
	addi $sp, $sp, 20
	jr $ra
	
	
	
# Use only $s0 - $s7 for computation in
# this procedure.

display_blanks:
	jr $ra

		
# Use only $s0 - $s7 for computation in
# this procedure.
		
display_digits_21:
	jr $ra
	
# Nothing needs to be done for the
# delay_400_msec procedure.
	
delay_400_msec:
	addi $sp, $sp -4
	sw $ra, 0($sp)
	
	addi $a0, $zero, 400
	addi $v0, $zero, 32
	syscall
	
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra
