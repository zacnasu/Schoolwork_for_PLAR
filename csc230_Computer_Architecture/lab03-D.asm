.text 
	# $8 : initial value for which we look for trailing zeros
	# $9 : the counter to keeps track of # of trailing zeros (result)
	# $10 :  the result of the AND with the mask
	
	ori $8, $0, 0x0  	# same as "addi $8, $0, 0xc800"
	ori $11, $0, 32		# if register has the value 0, then it will be an infinite loop
	ori $9, $0, 0		# counter
loop:
	andi $10, $8, 1
	bne $10, $0, exit
	bne $9, $11, exit	# exit before it becomes infinite loop
	addi $9, $9, 1
	sll $8, $8, 1
	b loop
	
exit:
	nop			# answer is in $9