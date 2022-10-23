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

    @Override
    public boolean isValidMovement(int toRow, int toColumn) {
        return true;
    }

    @Override
    public ArrayList<Integer[]> possibleMoves() {
        ArrayList<Integer[]> moves = new ArrayList<>();
        return moves;
    }
    
}
