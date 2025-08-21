import java.io.*;
import java.util.*;

/*
 * Class for a Triangle Solitaire Board with 15 holes
 */
public class Board {
    
    private static final int NUM_HOLES = 15; // number of holes in board
    private static final int NUM_ROWS = 5;   // number of rows in triangle
    
    private boolean[] board;    // representation of the triangle game board
    private int numPegs;        // number of pegs (true values) in this board
    
    /*
     * Purpose: create a new board of length NUM_HOLES with intial values of false
     *  initialize numPegs with given value of numPegs
     * Parameters: int numPegs - initial value for numPegs
     */
    public Board(int numPegs) {
        this.numPegs = numPegs;
        board = new boolean[NUM_HOLES];
    }
    
    /*
     * Purpose: initialize board and numPegs with given value of board and numPegs
     * Parameters: boolean[] board - a board of length NUM_HOLES
     *             int numPegs - initial value for numPegs
     */
    public Board(boolean[] board, int numPegs) {
        this.numPegs = numPegs;
        this.board = board;
    }
    
    /*
     * Purpose: initialize board with data read in from file at filepath
     *  format of the file can be found in assignment pdf
     * Parameters: String filepath - path to file
     */
    public Board(String filepath) {
        numPegs = 0;
        try {
            File infile = new File(filepath);
            Scanner instream = new Scanner(infile);
            
            board = new boolean[NUM_HOLES];
            int position = 0;
            while (instream.hasNext()) {
                boolean token = instream.next().equals("*");
                if (token)
                    numPegs++;
                board[position++] = token;
            }
            
        }
        catch (IOException ioe) {
            System.out.println("File i/o exception" + ioe);
        }
        catch (Exception e) {
            System.out.println("exception besides File i/o exception" + e);
        }
    }
    
    /*
     * Purpose: retruns the number of pegs (true values) in this Board
     * Parameters: none
     * Returns: int - the number of pegs
     */
    public int getNumPegs() {
        return numPegs;
    }
    
    /*
     * Purpose: creates a new Board with same number of pegs and
     *  and values as this Board
     * Parameters: none
     * Returns: Board - the new Board
     */
    public Board copyBoard(){
        Board newBoard = new Board(numPegs);
        for(int i=0; i<NUM_HOLES; i++)
            newBoard.board[i] = board[i];
        return newBoard;
    }
    
    /*
     * Purpose: determines whether this Board is equal to other Board
     * Parameters: Object - other
     * Returns: boolean - true if equal, false otherwise
     */
    public boolean equals(Object other) {
        if (!(other instanceof Board))
            return false;
        Board b = (Board) other;
        return Arrays.equals(this.board, b.board);
        
    }
    
    /*
     * Purpose: creates a triangle shaped String representation of this Board
     * Parameters: none
     * Returns: String - string representation
     */
    public String toString() {
        String s = "";
        int position = 0;
        for(int row=1; row<NUM_ROWS+1; row++) {
            for(int space=0; space<NUM_ROWS-row; space++)
                s += " ";
            
            for(int val=0; val<row; val++) {
                if (board[position++])
                    s += "* ";
                else
                    s += "O ";
            }
            
            s += "\n";
        }
        return s;
    }
    
    /*
     * Purpose: updates this Board to state after Jump j is made
     * Parameters: Jump j
     * Precondition: j is a valid Jump for this Board
     * Returns: nothing
     */
    public void makeJump(Jump j) {
        board[j.getFrom()] = false;
        board[j.getOver()] = false;
        board[j.getTo()] = true;
        numPegs--;
    }
    
    /*
     * Purpose: determines wheter j is a valid Jump for this Board
     * Parameters: Jump j
     * Returns: boolean - true if is valid, false otherwise
     */
    public boolean isValidJump(Jump j) {
        int from = j.getFrom();
        int over = j.getOver();
        int to   = j.getTo();
        
        if (from < 0 || from >= NUM_HOLES ||
            over < 0 || over >= NUM_HOLES ||
            to   < 0 || to   >= NUM_HOLES )
            return false;
        
        return (board[from] && board[over] && !board[to]);
    }
}
