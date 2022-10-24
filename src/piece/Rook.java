/*
 * 
 */
package piece;

import java.util.ArrayList;

/**
 *
 * @author tiago
 */
public class Rook extends Piece{

    public Rook(EnumPlayer player, int row, int column) {
        super(player, row, column, (player == EnumPlayer.WHITE ? "src/figs/wRook.png" : "src/figs/bRook.png"));
    }

    public Rook(EnumPlayer player, int row, int column, Board board) {
        super(player, row, column, (player == EnumPlayer.WHITE ? "src/figs/wRook.png" : "src/figs/bRook.png"));
        this.setBoard(board);
    }

    @Override
    public boolean isValidMovement(int toRow, int toColumn) {
        if ((toColumn == this.getColumn() || toRow == this.getRow()) && (this.getBoard().getPiece(toRow, toColumn) == null || this.getBoard().getPiece(toRow, toColumn).getPlayer() != this.getPlayer())) {
            if (toRow > this.getRow()) {
                for (int i = 1; i < toRow - this.getRow(); i++) {
                    if (!(this.getBoard().getPiece(this.getRow()+i, this.getColumn()) == null)) {
                        return false;
                    }
                }
            }
            
            else if (toRow < this.getRow()) {
                for (int i = 1; i < this.getRow() - toRow; i++) {
                    if (!(this.getBoard().getPiece(this.getRow()-i, this.getColumn()) == null)) {
                        return false;
                    }  
                }
            }
            
            else if(toColumn > this.getColumn()) {
                for (int i = 1; i < toColumn - this.getColumn(); i++) {
                    if (!(this.getBoard().getPiece(this.getRow(), this.getColumn()+i) == null)) {
                        return false;
                    }  
                }
            }           

            else {
                for (int i = 1; i < this.getColumn() - toColumn; i++) {
                    if (!(this.getBoard().getPiece(this.getRow(), this.getColumn()-i) == null)) {
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
