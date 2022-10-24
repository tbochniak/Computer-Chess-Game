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
        

        //Move one or 2 vacant square
        if(this.getBoard().getPiece(toRow, toColumn) == null && this.getColumn() == toColumn && (this.getPlayer() == EnumPlayer.WHITE ? toRow == this.getRow()+1 : toRow == this.getRow()-1) || this.isFirstMove() && (this.getPlayer() == EnumPlayer.WHITE ? toRow == this.getRow()+2 : toRow == this.getRow()-2)) {
            return true;
        }

        // Capturing
        else if (this.getBoard().getPiece(toRow, toColumn) != null && this.getBoard().getPiece(toRow, toColumn).getPlayer() != this.getPlayer() && (this.getPlayer() == EnumPlayer.WHITE ? toRow == this.getRow()+1 : toRow == this.getRow()-1) && Math.abs(toColumn - this.getColumn()) == 1) {
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
