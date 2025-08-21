.data

seven_segment:
	.byte 0x3f, 0x06, 0x5b, 0x4f, 0x66, 0x6d, 0x7d, 0x07, 0x7f, 0x6f, 0x5f, 0x7c, 0x39, 0x5e, 0x79, 0x71
	
.text
	addi $a0, $zero, 0x2b
	jal count_up_with_display
	jal display_dashes
	
finish:
	addi $v0, $zero, 10
	syscall


# $a0: Maximum counter value

count_up_with_display:
	addi $sp, $sp -20
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	addi $s2, $zero, 0x0f
	addi $s0, $zero, 0
loop:
	
	and $a1, $s2, $s0
	addi $a2, $zero, 0
	jal display_hex_digit
	
	sll $a1, $a1, 4
	and $a1, $a1, $s2
	addi $a2, $zero, 1
	jal display_hex_digit
	
	addi $s0, $s1, 1

	jal delay_400_msec
	add $s0, $s0, 1
	bne  $s0, $a0, loop


	lw $s3, 16($sp)
	lw $s2, 12($sp)
	lw $s1, 8($sp)
	lw $s0, 4($sp)
	addi $sp, $sp -20
	sw $ra, 0($sp)
	jr $ra


# $a0: hex digit to be displayed
# $a1: 0 == right display; 1 == left display

display_hex_digit:
	addi $sp, $sp -20
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	
	la $s1, 0xffff0010
	la $s2, 0xffff0011
	la $s3, seven_segment

	addi $s0, $zero, 1
	add $t0, $s3, $a1
	lb $t1, 0($t0)

	beq $a2, $s0, left_digit
	sb $t1, 0($s1)
	beq $zero, $zero, after_display
left_digit:
	sb $t1, 0($s2)
after_display:
	lw $s3, 16($sp)
	lw $s2, 12($sp)
	lw $s1, 8($sp)
	lw $s0, 4($sp)
	lw $ra, 0($sp)
	addi $sp, $sp, 20
	jr $ra
	
	
	
delay_400_msec:
	addi $sp, $sp -4
	sw $ra, 0($sp)
	
	addi $a0, $zero, 400
	addi $v0, $zero, 32
	syscall
	
	lw $ra, 0($sp)
	add $sp, $sp, 4
	jr $ra
	

display_dashes:
	jr $ra
	
