/*
 * 
 */
package piece;

import java.util.ArrayList;

/**
 *
 * @author tiago
 */
public class Pawn extends Piece{

    public Pawn(EnumPlayer player, int row, int column) {
        super(player, row, column, (player == EnumPlayer.WHITE ? "src/figs/wPawn.png" : "src/figs/bPawn.png"));
    }

    @Override
    public boolean isValidMovement(int toRow, int toColumn) {
        
        //Move one vacant square
        if(this.getBoard().getPiece(toRow, toColumn) == null && this.getColumn() == toColumn && (this.getPlayer() == EnumPlayer.WHITE ? toRow == this.getRow()+1 : toRow == this.getRow()-1)) {
            return true;
        }
        //Move two vacant square
        else if (this.getBoard().getPiece(toRow, toColumn) == null && this.isFirstMove() && this.getColumn() == toColumn && (this.getPlayer() == EnumPlayer.WHITE ? toRow == this.getRow()+2 : toRow == this.getRow()-2)) {
            return true;
        }
        // Capturing
        else if (this.getBoard().getPiece(toRow, toColumn).getPlayer() != this.getPlayer() && (this.getColumn()+1 == toColumn || this.getColumn()-1 == toColumn) && (this.getPlayer() == EnumPlayer.WHITE ? toRow == this.getRow()+1 : toRow == this.getRow()-1)) {
            return true;
        }      
        //Promotion
        //En passant
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
