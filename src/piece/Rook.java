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
