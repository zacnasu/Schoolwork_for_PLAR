//Zachary Nasu
//V009117908

import java.io.*;
import java.util.*;

public class TriangleSolitaireSolver {
    // this is a list of all the possible Jumps
    // that can be made on a Triangle Solitaire board
    // You should use this in the nextBoard function
    public static final Jump[] JUMPS = {
    new Jump(0,  1,  3), new Jump(0,  2,  5),
    new Jump(1,  3,  6), new Jump(1,  4,  8),
    new Jump(2,  4,  7), new Jump(2,  5,  9),
    new Jump(3,  1,  0), new Jump(3,  4,  5),  new Jump(3, 6, 10), new Jump(3, 7, 12),
    new Jump(4,  7, 11), new Jump(4,  8, 13),
    new Jump(5,  2,  0), new Jump(5,  4,  3),  new Jump(5, 8, 12), new Jump(5, 9, 14),
    new Jump(6,  3,  1), new Jump(6,  7,  8),
    new Jump(7,  4,  2), new Jump(7,  8,  9),
    new Jump(8,  4,  1), new Jump(8,  7,  6),
    new Jump(9,  5,  2), new Jump(9,  8,  7),
    new Jump(10, 6,  3), new Jump(10, 11, 12),
    new Jump(11, 7,  4), new Jump(11, 12, 13),
    new Jump(12, 7,  3), new Jump(12,  8,  5), new Jump(12, 11, 10),  new Jump(12, 13, 14),
    new Jump(13, 8,  4), new Jump(13, 12, 11),
    new Jump(14, 9,  5), new Jump(14, 13, 12)
    };
    
    
    /*
     * Purpose: creates and returns a Stack of boards that represent a solution
     *  from the given Board bd to a solved Board (1 peg),
     *  where there is only 1 Jump from one Board to the next
     *  - the stack will be empty if bd is not solveable
     * Parameters: Board bd - the starting board
     * Returns: Stack<Board> - the stack with boards to solution, empty if not solveable
     */
    public static Stack<Board> solve(Board bd){
        Stack<Board> s = new StackRefBased<Board>();
		s.push(bd);
		List<Board> list = new LinkedList<Board>();
		list.add(bd);
		try{
		while(s.size() != 0 && s.peek().getNumPegs() > 1){
			Board current = s.peek();
			current = nextBoard(list,current);
			if(current != null){
				s.push(current);
				list.add(current);
			}else{
				s.pop();
			}
		}
		}catch(StackEmptyException e){
			return null;
		}
        
        // TODO: complete the algorithm to solve the problem
        //  you will want to make use of the nextBoard function
        
        return s;
    }
    
  
    
    /*
     * Purpose:
     *   for each Jump j in JUMP:
     *      creates a new board identical to bd,
     *      checks to see if j is valid, if it is
     *          makes the Jump j on the newBoard
     *          if that new board has not been visited it is returned
     *
     *   if there is no next board, return null to indicate no nextBoard
     * Parameters:  List<Board> visited - list of Boards that have already been tried
     *              Board bd - the starting board
     * Returns: Board - the next Board to try, null if there are no possibilities after bd
     */
    public static Board nextBoard(List<Board> visited, Board bd) {
		
		for(int i =0; i<JUMPS.length; i++){
			Board temp = bd.copyBoard();
			if(temp.isValidJump(JUMPS[i])){
				temp.makeJump(JUMPS[i]);
				if(visited.contains(temp) == false){
					return temp;
				}
			}
		}
        //TODO: complete
        
        
        return null;
    }
    
}
