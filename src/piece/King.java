/*
 * To change this license header, choose License Headers in Project Properties.
 */
package piece;

import java.util.ArrayList;

/**
 *
 * @author tiago
 */
public class King extends Piece{

    public King(EnumPlayer player, int row, int column) {
        super(player, row, column, (player == EnumPlayer.WHITE ? "src/figs/wKing.png" : "src/figs/bKing.png"));
    }

    @Override
    public boolean isValidMovement(int toRow, int toColumn) {
        return (Math.abs(toRow-this.getRow()) <= 1 && Math.abs(toColumn - this.getColumn()) <= 1)&& (this.getBoard().getPiece(toRow, toColumn) == null || this.getBoard().getPiece(toRow, toColumn).getPlayer() != this.getPlayer());
    }

    @Override
    public ArrayList<ArrayList<Integer>> possibleMoves() {
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        return moves;
    }
    
}
