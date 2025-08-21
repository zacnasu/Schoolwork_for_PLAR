.data

nums:
	.word 1, 3, 5, -11, 22, 33, -4, 5, 0

	
.text
	la $11, nums
	addi $12, $0, 0
	
loop:
	lw $10, 0($11)
	add $12, $12, $10
	beq $0, $10, exit
	addi $11, $11, 4
	b loop

exit:
	nop
