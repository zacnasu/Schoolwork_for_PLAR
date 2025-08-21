.data

bob:
	.word 212
	
connie:
	.word 40122
	
.text
	addi $10, $0, 0x10010000
	lw $10, 0($10)
	addi $11, $0, 0x10010000
	lw $11, 4($11)
	add $12, $10, $11
	# Store the sum of integer
	# at 'bob' and integer at
	# 'connie' into register
	# $12 -- and without using
	# bob or connie directly
	# in a 'lw' instruction
	# (ie must use register and
	# an offset of zero).
	
