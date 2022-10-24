/*
 * 
 */
package piece;

import java.util.ArrayList;

/**
 *
 * @author tiago
 */
public class Bishop extends Piece{

    public Bishop(EnumPlayer player, int row, int column) {
        super(player, row, column, (player == EnumPlayer.WHITE ? "src/figs/wBishop.png" : "src/figs/bBishop.png"));
    }
    
    public Bishop(EnumPlayer player, int row, int column, Board board) {
        super(player, row, column, (player == EnumPlayer.WHITE ? "src/figs/wBishop.png" : "src/figs/bBishop.png"));
        this.setBoard(board);
    }    

    @Override
    public boolean isValidMovement(int toRow, int toColumn) {
        if (Math.abs(toColumn - this.getColumn()) == Math.abs(toRow - this.getRow()) && (this.getBoard().getPiece(toRow, toColumn) == null || this.getBoard().getPiece(toRow, toColumn).getPlayer() != this.getPlayer())) {
            if (toRow > this.getRow() && toColumn > this.getColumn()) {
                for (int i = 1; i < toRow - this.getRow(); i++) {
                    if (!(this.getBoard().getPiece(this.getRow()+i, this.getColumn()+i) == null)) {
                        return false;
                    }
                }
            }
            
            else if (toRow > this.getRow() && toColumn < this.getColumn()) {
                for (int i = 1; i < toRow - this.getRow(); i++) {
                    if (!(this.getBoard().getPiece(this.getRow()+i, this.getColumn()-i) == null)) {
                        return false;
                    }  
                }
            }
            
            else if(toRow < this.getRow() && toColumn > this.getColumn()) {
                for (int i = 1; i < this.getRow() - toRow; i++) {
                    if (!(this.getBoard().getPiece(this.getRow()-i, this.getColumn()+i) == null)) {
                        return false;
                    }  
                }
            }           

            else {
                for (int i = 1; i < this.getRow() - toRow; i++) {
                    if (!(this.getBoard().getPiece(this.getRow()-i, this.getColumn()-i) == null)) {
                        return false;
                    }  
                }
            }
            
            //
            return true;
        }
        
        else {
            return false;
        }
    }

    @Override
    public ArrayList<Integer[]> possibleMoves() {
        ArrayList<Integer[]> moves = new ArrayList<>();
        return moves;
    }
}
