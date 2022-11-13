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
        super(player, row, column, (player == EnumPlayer.WHITE ? "src/figs/wKing.png" : "src/figs/bKing.png"), 200);
    }

    @Override
    public boolean isValidMovement(int toRow, int toColumn) {
        //position out of the board
        if(!(toRow >= 0 && toRow < 8 && toColumn >= 0 && toColumn < 8)) {
            return false;
        }
        
        //castle
        else if(this.isFirstMove() && Math.abs(toColumn-this.getColumn()) == 2) {
            
            //castle to left side
            if(toColumn > this.getColumn() && this.getBoard().getPiece(this.getRow(), 7) instanceof Rook && this.getBoard().getPiece(this.getRow(), 7).isFirstMove()) {
                for (int column = this.getColumn() + 1; column < 7; column++) {
                    if(this.getBoard().getPiece(this.getRow(), column) != null) {
                        return false;
                    }
                }
                return true;
            }
            
            //castle to right side
            else if(toColumn < this.getColumn() && this.getBoard().getPiece(this.getRow(), 0).isFirstMove() && this.getBoard().getPiece(this.getRow(), 0) instanceof Rook) {
                for (int column = this.getColumn() - 1; column > 0; column--) {
                    if(this.getBoard().getPiece(this.getRow(), column) != null) {
                        return false;
                    }
                }
                return true;
            }
        }
        
        return (Math.abs(toRow-this.getRow()) <= 1 && Math.abs(toColumn - this.getColumn()) <= 1)&& (this.getBoard().getPiece(toRow, toColumn) == null || this.getBoard().getPiece(toRow, toColumn).getPlayer() != this.getPlayer());
    }

    @Override
    public ArrayList<ArrayList<Integer>> possibleMoves() {
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        return moves;
    }

    @Override
    public ArrayList<ArrayList<Integer>> possibleAttacks() {
        return this.possibleMoves();
    }
    
}
