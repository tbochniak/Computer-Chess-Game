/*
 * 
 */
package piece;

import java.util.ArrayList;

/**
 *
 * @author tiago
 */
public class Knight extends Piece{

    public Knight(EnumPlayer player, int row, int column) {
        super(player, row, column, (player == EnumPlayer.WHITE ? "src/figs/wKnight.png" : "src/figs/bKnight.png"));
    }

    @Override
    public boolean isValidMovement(int toRow, int toColumn) {
        return (Math.abs(toRow - this.getRow()) == 2 && Math.abs(toColumn - this.getColumn()) == 1 || Math.abs(toRow - this.getRow()) == 1 && Math.abs(toColumn - this.getColumn()) == 2) && (this.getBoard().getPiece(toRow, toColumn) == null || this.getBoard().getPiece(toRow, toColumn).getPlayer() != this.getPlayer());
    }

    @Override
    public ArrayList<Integer[]> possibleMoves() {
        ArrayList<Integer[]> moves = new ArrayList<>();
        return moves;
    }
    
}

