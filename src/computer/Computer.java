/*
 * 
 */
package computer;

import java.util.ArrayList;
import piece.Board;
import piece.EnumPlayer;
import piece.Piece;

/**
 *
 * @author tiago
 */
public class Computer {
    
    private final EnumPlayer computerPlayer;

    public Computer(EnumPlayer computerPlayer) {
        this.computerPlayer = computerPlayer;
    }

    
    public void playComputer(Board board) {
            if (board.getPlayer() == this.computerPlayer) {
                System.out.println("Jogada feita");
                board.movePiece(board.getPiece(6, 7), 5, 7);
            }
            
            //board.invertPlayer();            
        

        
    }
    

    public ArrayList<ArrayList<Integer>> possibleMoves(Board board) {
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        
        
        return moves;
    }
    
    //Getter and Setters 
    public EnumPlayer getComputerPlayer() {
        return computerPlayer;
    }
    
    public EnumPlayer getPlayer() {
        return computerPlayer;
    }
    
    
 
}
