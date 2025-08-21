# This code assumes the use of the "Bitmap Display" tool.
#
# Tool settings must be:
#   Unit Width in Pixels: 32
#   Unit Height in Pixels: 32
#   Display Width in Pixels: 512
#   Display Height in Pixels: 512
#   Based Address for display: 0x10010000 (static data)
#
# In effect, this produces a bitmap display of 16x16 pixels.


	.include "bitmap-routines.asm"

	.data
TELL_TALE:
	.word 0x12345678 0x9abcdef0	# Helps us visually detect where our part starts in .data section
KEYBOARD_EVENT_PENDING:
	.word	0x0
KEYBOARD_EVENT:
	.word   0x0
BOX_ROW:
	.word	0x0
BOX_COLUMN:
	.word	0x0

	.eqv LETTER_a 97
	.eqv LETTER_d 100
	.eqv LETTER_w 119
	.eqv LETTER_s 115
    .eqv SPACE    32
	.eqv BOX_COLOUR 0x0099ff33
	
	.globl main
	
	.text	
main:
# STUDENTS MAY MODIFY CODE BELOW
# vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	# initialize variables

	la $s0, 0xffff0000	# control register for MMIO Simulator "Receiver"
	lb $s1, 0($s0)
	ori $s1, $s1, 0x02	# Set bit 1 to enable "Receiver" interrupts (i.e., keyboard)
	sb $s1, 0($s0)
	
	addi $s1, $zero, 0x00911790 # my stduent number
	
	# set default box in upper left hand corner
	addi $a2, $zero, BOX_COLOUR # default box colour
	la $s4, BOX_ROW # default box row
	lw $a0, 0($s4)
	la $s4, BOX_COLUMN # default box column
	lw $a1, 0($s4)
	jal draw_bitmap_box

check_for_event:
	la $s5, KEYBOARD_EVENT_PENDING
	lw $s6, 0($s5)
	beq $s6, $zero, check_for_event # keyboard event is non-zero if it is true
	la $s7, KEYBOARD_EVENT
	lw $s2, 0($s7) #load keyboard event into $s2
	beq $s2, LETTER_a, is_a # branches if keyboard event is a
	beq $s2, LETTER_d, is_d # branches if keyboard event is d
	beq $s2, LETTER_w, is_w # branches if keyboard event is w
	beq $s2, LETTER_s, is_s # branches if keyboard event is s
	beq $s2, SPACE, is_space # branches if keyboard event is a space
	addi $s6, $zero, 0
	sw $s6, 0($s5)
	beq $zero, $zero, check_for_event

is_a:
	la $s3, BOX_ROW
	lw $a0, 0($s3)
	la $s3, BOX_COLUMN # reuses $s3 as box row address is no longer needed
	lw $a1, 0($s3)
	add $s4, $a2, $zero
	addi $a2, $zero, 0x00000000
	jal draw_bitmap_box # sets previous position back to black
	addi $a1, $a1, -1 # moves position one to the left
	sw $a1, 0($s3) # saves new $a1 column position for next movement
	add $a2, $s4, $zero # restores previous colour
	jal draw_bitmap_box # draws new box
	addi $s6, $zero, 0 # sets keyboard event pending to 0 again
	sw $s6, 0($s5)
	beq $zero, $zero, check_for_event
# documentation save as is_a so just doing differences
is_d:
	la $s3, BOX_ROW
	lw $a0, 0($s3)
	la $s3, BOX_COLUMN
	lw $a1, 0($s3)
	add $s4, $a2, $zero
	addi $a2, $zero, 0x00000000
	jal draw_bitmap_box
	addi $a1, $a1, 1 # moves position one to the right
	sw $a1, 0($s3) 
	add $a2, $s4, $zero
	jal draw_bitmap_box
	addi $s6, $zero, 0
	sw $s6, 0($s5)
	beq $zero, $zero, check_for_event
# documentation save as is_a so just doing differences
is_w:
	la $s3, BOX_COLUMN
	lw $a1, 0($s3)
	la $s3, BOX_ROW # resuses $s3 as box coloum address is no longer needed
	lw $a0, 0($s3)
	add $s4, $a2, $zero
	addi $a2, $zero, 0x00000000
	jal draw_bitmap_box
	addi $a0, $a0, -1
	sw $a0, 0($s3)
	add $a2, $s4, $zero
	jal draw_bitmap_box
	addi $s6, $zero, 0 
	sw $s6, 0($s5)
	beq $zero, $zero, check_for_event
# documentation save as is_a so just doing differences
is_s:
	la $s3, BOX_COLUMN
	lw $a1, 0($s3)
	la $s3, BOX_ROW # resuses $s3 as box coloum address is no longer needed
	lw $a0, 0($s3)
	add $s4, $a2, $zero
	addi $a2, $zero, 0x00000000
	jal draw_bitmap_box
	addi $a0, $a0, 1
	sw $a0, 0($s3)
	add $a2, $s4, $zero
	jal draw_bitmap_box
	addi $s6, $zero, 0
	sw $s6, 0($s5)
	beq $zero, $zero, check_for_event

is_space:
	la $s4, BOX_ROW
	lw $a0, 0($s4)
	la $s4, BOX_COLUMN
	lw $a1, 0($s4)
	beq $a2, BOX_COLOUR, default
	addi $a2, $zero, BOX_COLOUR
	jal draw_bitmap_box
	beq $zero, $zero, end_space
default:
	add $a2, $s1, $zero
	jal draw_bitmap_box
end_space:
	addi $s6, $zero, 0 # sets keyboard event pending to 0 again
	sw $s6, 0($s5)
	beq $zero, $zero, check_for_event

	
	
	# Should never, *ever* arrive at this point
	# in the code.	

	addi $v0, $zero, 10

.data
    .eqv BOX_COLOUR_BLACK 0x00000000
.text

	addi $v0, $zero, BOX_COLOUR_BLACK
	syscall



# Draws a 4x4 pixel box in the "Bitmap Display" tool
# $a0: row of box's upper-left corner
# $a1: column of box's upper-left corner
# $a2: colour of box

draw_bitmap_box:
	addi $sp, $sp -24
	sw $ra, 0($sp)
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	sw $s2, 12($sp)
	sw $s3, 16($sp)
	sw $s4, 20($sp)
	add $s0, $a0, $zero
	add $s1, $a1, $zero
	addi $s2, $s0, 4 # sets limit for row
	addi $s3, $s1, 4 # sets limit for column
	
outer_loop: # loops through columns
	add $a0, $s0, $zero
inner_loop: # loops through rows
	jal set_pixel # sets each pixel individually, colour taken from $a2 register
	addi $a0, $a0, 1
	bne $a0, $s2, inner_loop
	addi $a1, $a1, 1
	bne $a1, $s3 outer_loop
	
	add $a0, $s0, $zero # preserves $a0 and $a1 to previous states
	add $a1, $s1, $zero
	lw $s4, 20($sp)
	lw $s3, 16($sp)
	lw $s2, 12($sp)
	lw $s1, 8($sp)
	lw $s0, 4($sp)
	lw $ra, 0($sp)
	addi $sp, $sp, 24
	jr $ra


	.kdata

	.ktext 0x80000180
#
# You can copy-and-paste some of your code from part (a)
# to provide elements of the interrupt handler.
#

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


.data

# Any additional .text area "variables" that you need can
# be added in this spot. The assembler will ensure that whatever
# directives appear here will be placed in memory following the
# data items at the top of this file.

	
# ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
# STUDENTS MAY MODIFY CODE ABOVE


.eqv BOX_COLOUR_WHITE 0x00FFFFFF
	
