# a2-morse-encode.asm
#
# For UVic CSC 230, Spring 2022
#
# Original file copyright: Mike Zastre
#

.text


main:	



# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	## Test code that calls procedure for part A
	# jal save_our_souls

	## flash_one_symbol test for part B
	# addi $a0, $zero, 0x42   # dot dot dash dot
	# jal flash_one_symbol
	
	## flash_one_symbol test for part B
	# addi $a0, $zero, 0x37   # dash dash dash
	# jal flash_one_symbol
		
	## flash_one_symbol test for part B
	# addi $a0, $zero, 0x32  	# dot dash dot
	# jal flash_one_symbol
			
	## flash_one_symbol test for part B
	# addi $a0, $zero, 0x11   # dash
	# jal flash_one_symbol	
	
	# display_message test for part C
	# la $a0, test_buffer
	# jal display_message
	
	# char_to_code test for part D
	# the letter 'P' is properly encoded as 0x46.
	#addi $a0, $zero, 'P'
	#jal char_to_code
	
	# char_to_code test for part D
	# the letter 'A' is properly encoded as 0x21
	addi $a0, $zero, 'A'
	jal char_to_code
	
	# char_to_code test for part D
	# the space' is properly encoded as 0xff
	#addi $a0, $zero, ' '
	#jal char_to_code
	
	# encode_text test for part E
	# The outcome of the procedure is here
	# immediately used by display_message
	#la $a0, message01
	#la $a1, buffer01
	#jal encode_text
	#la $a0, buffer01
	#jal display_message
	
	
	# Proper exit from the program.
	addi $v0, $zero, 10
	syscall

	
	
###########
# PROCEDURE
save_our_souls:
	addi $sp, $sp -4
	sw $ra, 0($sp)
	
	jal seven_segment_on
   	jal delay_short
   	jal seven_segment_off
   	jal delay_long
   	jal seven_segment_on
   	jal delay_short
   	jal seven_segment_off
   	jal delay_long
   	jal seven_segment_on
   	jal delay_short
   	jal seven_segment_off
   	jal delay_long
   	
   	jal seven_segment_on
   	jal delay_long
   	jal seven_segment_off
   	jal delay_long
   	jal seven_segment_on
   	jal delay_long
   	jal seven_segment_off
   	jal delay_long
   	jal seven_segment_on
   	jal delay_long
   	jal seven_segment_off
   	jal delay_long
   	
   	jal seven_segment_on
   	jal delay_short
   	jal seven_segment_off
   	jal delay_long
   	jal seven_segment_on
   	jal delay_short
   	jal seven_segment_off
   	jal delay_long
   	jal seven_segment_on
   	jal delay_short
   	jal seven_segment_off
   	jal delay_long
   	
   	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $31


# PROCEDURE
flash_one_symbol:
	addi $sp, $sp -24
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	sw $s4, 20($sp)
	
	
	
	andi $s0, $a0, 0x00f0 # length mask
	srl $s0, $s0, 4 # moves length to rightmost bits
	andi $s1, $a0, 0x0f # sequence
	addi $s2, $zero, 0 # counter
	ori $s3, $zero, 0x01 # bit mask
	
	beq $s0, 0x0f, flash_space # in case symbol is space, length will be 0xf

mask_loop:
	addi $s2, $s2, 1
	beq $s2, $s0, mask_loop_end # if length is 1, then no need to adjust mask
	sll $s3, $s3, 1 # move mask one bit to left
	beq $zero, $zero, mask_loop

mask_loop_end:
	addi $s2, $zero, 0 # counter reset

loop_sequence:
	beq $s2, $s0, end_loop
	and $s4, $s3, $s1 # checks if current bit is dash
	srl $s3, $s3, 1 # moves mask one over
	addi $s2, $s2, 1

	beq $s4, 0, dot # branches if current bit is dot
	# if not dot, then assumed dash
	jal seven_segment_on
   	jal delay_long
   	jal seven_segment_off
   	jal delay_long
   	beq $zero, $zero, loop_sequence
dot:
	jal seven_segment_on
   	jal delay_short
   	jal seven_segment_off
   	jal delay_long
   	beq $zero, $zero, loop_sequence

flash_space:
	jal delay_long
	jal delay_long
	jal delay_long

end_loop:
	lw $s4, 20($sp)
	lw $s3, 16($sp)
	lw $s2, 12($sp)
	lw $s1, 8($sp)
	lw $s0, 4($sp)
	lw $ra, 0($sp)
	addi $sp, $sp, 24
	jr $ra

###########
# PROCEDURE
display_message:
	addi $sp, $sp -24
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	sw $s4, 20($sp)

	addi $s0, $a0, 0 # copy address from $a0 to $s0
	lb $s1, 0($s0) # load first byte into $s1

message_loop:
	beq $s1, 0x00, end_message_loop # ends procedure when null is met
	addi $a0, $s1, 0 # adds byte to $a0 from $s1 for flash_one_symbol
	jal flash_one_symbol
	jal delay_long
	addi $s0, $s0, 1 # moves $s0 address one byte forward
	lb $s1, 0($s0)
	beq $zero, 0x0, message_loop
	
end_message_loop:
	lw $s4, 20($sp)
	lw $s3, 16($sp)
	lw $s2, 12($sp)
	lw $s1, 8($sp)
	lw $s0, 4($sp)
	lw $ra, 0($sp)
	addi $sp, $sp, 24
	jr $ra
	
	
###########
# PROCEDURE
char_to_code:
	addi $sp, $sp -24
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	sw $s4, 20($sp)
	
	la $s0, codes
	lb $s1, 0($s0)
	addi $s2, $zero, 0 # loop counter
	beq $a1, ' ', end_with_space # if space, then returns 0xff

char_loop:
	beq $s2, 26, end_with_space # if all else fails, return space
	beq $s1, $a0, char_found # if $s0 matches letter from codes
	addi $s2, $s2, 1
	addi $s0, $s0, 8 # moves address 8 bytes forward to next letter
	lb $s1, 0($s0)
	beq $zero, $zero, char_loop

end_with_space:
	ori $v0, $zero, 0x0ff
	beq $zero, $zero, end_char_to_code

char_found:
	addi $s2, $zero, 0 # reset counter variable
	addi $s3, $zero, 0

copy_bit:
	addi $s0, $s0, 1
	lb $s1, 0($s0)
	beq $s1, '-' copy_dash
	beq $s1, '.' copy_dot
	sll $s2, $s2, 4 # shift to copy as length
	or $v0, $s2, $s3
	beq $zero, $zero, end_char_to_code

copy_dot:
	addi $s2, $s2, 1
	sll $s3, $s3, 1 # shift to one bit to left but keep as 0
	beq $zero, $zero, copy_bit

copy_dash:
	addi $s2, $s2, 1
	sll $s3, $s3, 1 # shift bit one to the left for new bit
	ori $s3, $s3, 0x01 # add 1 into leftmost bit for dash
	beq $zero, $zero, copy_bit

end_char_to_code:
	lw $s4, 20($sp)
	lw $s3, 16($sp)
	lw $s2, 12($sp)
	lw $s1, 8($sp)
	lw $s0, 4($sp)
	lw $ra, 0($sp)
	addi $sp, $sp, 24
	jr $ra	


###########
# PROCEDURE
encode_text:
	addi $sp, $sp -24
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	sw $s4, 20($sp)
	add $s0, $zero, $a0
	add $s1, $zero, $a1
	
encode_loop:
	lb $a0, 0($s0) # load into a0 for char_to_code
	beq $a0, 0x00, end_with_null # end if char found is null
	jal char_to_code
	sb $v0, 0($s1) # saves returned value into next available byte in $s1 buffer
	addi $s1, $s1, 1
	addi $s0, $s0, 1
	beq $zero, $zero, encode_loop
	
end_with_null:
	addi $s3, $zero, 0x00 # loads null terminator into buffer
	sb $s3, 0($s1)
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

#############################################
# DO NOT MODIFY ANY OF THE CODE / LINES BELOW

###########
# PROCEDURE
seven_segment_on:
	la $t1, 0xffff0010     # location of bits for right digit
	addi $t2, $zero, 0xff  # All bits in byte are set, turning on all segments
	sb $t2, 0($t1)         # "Make it so!"
	jr $31


###########
# PROCEDURE
seven_segment_off:
	la $t1, 0xffff0010	# location of bits for right digit
	sb $zero, 0($t1)	# All bits in byte are unset, turning off all segments
	jr $31			# "Make it so!"
	

###########
# PROCEDURE
delay_long:
	add $sp, $sp, -4	# Reserve 
	sw $a0, 0($sp)
	addi $a0, $zero, 600
	addi $v0, $zero, 32
	syscall
	lw $a0, 0($sp)
	add $sp, $sp, 4
	jr $31

	
###########
# PROCEDURE			
delay_short:
	add $sp, $sp, -4
	sw $a0, 0($sp)
	addi $a0, $zero, 200
	addi $v0, $zero, 32
	syscall
	lw $a0, 0($sp)
	add $sp, $sp, 4
	jr $31




#############
# DATA MEMORY
.data
codes:
	.byte 'A', '.', '-', 0, 0, 0, 0, 0
	.byte 'B', '-', '.', '.', '.', 0, 0, 0
	.byte 'C', '-', '.', '-', '.', 0, 0, 0
	.byte 'D', '-', '.', '.', 0, 0, 0, 0
	.byte 'E', '.', 0, 0, 0, 0, 0, 0
	.byte 'F', '.', '.', '-', '.', 0, 0, 0
	.byte 'G', '-', '-', '.', 0, 0, 0, 0
	.byte 'H', '.', '.', '.', '.', 0, 0, 0
	.byte 'I', '.', '.', 0, 0, 0, 0, 0
	.byte 'J', '.', '-', '-', '-', 0, 0, 0
	.byte 'K', '-', '.', '-', 0, 0, 0, 0
	.byte 'L', '.', '-', '.', '.', 0, 0, 0
	.byte 'M', '-', '-', 0, 0, 0, 0, 0
	.byte 'N', '-', '.', 0, 0, 0, 0, 0
	.byte 'O', '-', '-', '-', 0, 0, 0, 0
	.byte 'P', '.', '-', '-', '.', 0, 0, 0
	.byte 'Q', '-', '-', '.', '-', 0, 0, 0
	.byte 'R', '.', '-', '.', 0, 0, 0, 0
	.byte 'S', '.', '.', '.', 0, 0, 0, 0
	.byte 'T', '-', 0, 0, 0, 0, 0, 0
	.byte 'U', '.', '.', '-', 0, 0, 0, 0
	.byte 'V', '.', '.', '.', '-', 0, 0, 0
	.byte 'W', '.', '-', '-', 0, 0, 0, 0
	.byte 'X', '-', '.', '.', '-', 0, 0, 0
	.byte 'Y', '-', '.', '-', '-', 0, 0, 0
	.byte 'Z', '-', '-', '.', '.', 0, 0, 0
	
message01:	.asciiz "A A A"
message02:	.asciiz "SOS"
message03:	.asciiz "WATERLOO"
message04:	.asciiz "DANCING QUEEN"
message05:	.asciiz "CHIQUITITA"
message06:	.asciiz "THE WINNER TAKES IT ALL"
message07:	.asciiz "MAMMA MIA"
message08:	.asciiz "TAKE A CHANCE ON ME"
message09:	.asciiz "KNOWING ME KNOWING YOU"
message10:	.asciiz "FERNANDO"

buffer01:	.space 128
buffer02:	.space 128
test_buffer:	.byte 0x30 0x37 0x30 0x00    # This is SOS
