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
        super(player, row, column, (player == EnumPlayer.WHITE ? "src/figs/wPawn.png" : "src/figs/bPawn.png"), 1);
    }

    @Override
    public boolean isValidMovement(int toRow, int toColumn) {
        
        //position out of the board
        if(!(toRow >= 0 && toRow < 8 && toColumn >= 0 && toColumn < 8)) {
            return false;
        }
        //Move one vacant square
        else if (this.getBoard().getPiece(toRow, toColumn) == null && this.getColumn() == toColumn && (this.getPlayer() == EnumPlayer.WHITE ? toRow == this.getRow()+1 : toRow == this.getRow()-1)) {
            return true;
        }
        //Move two vacant square
        else if ((this.getPlayer() == EnumPlayer.WHITE ? this.getRow() == 1 : this.getRow() == 6)&& this.getBoard().getPiece(toRow, toColumn) == null && (this.getPlayer() == EnumPlayer.WHITE ? this.getBoard().getPiece(toRow-1, toColumn) : this.getBoard().getPiece(toRow+1, toColumn)) == null && this.getColumn() == toColumn && (this.getPlayer() == EnumPlayer.WHITE ? toRow == this.getRow()+2 : toRow == this.getRow()-2)) {
            this.setEnPassant(true);
            return true;
        }
        
        // Capturing
        else if (this.getBoard().getPiece(toRow, toColumn) != null && this.getBoard().getPiece(toRow, toColumn).getPlayer() != this.getPlayer() && (this.getPlayer() == EnumPlayer.WHITE ? toRow == this.getRow()+1 : toRow == this.getRow()-1) && Math.abs(toColumn - this.getColumn()) == 1) {
            return true;

        }      
        //En passant
        else if (this.getBoard().getPiece(toRow, toColumn) == null && (this.getPlayer() == EnumPlayer.WHITE ? this.getBoard().getPiece(toRow-1, toColumn) : this.getBoard().getPiece(toRow+1, toColumn)) != null && (this.getPlayer() == EnumPlayer.WHITE ? this.getBoard().getPiece(toRow-1, toColumn) : this.getBoard().getPiece(toRow+1, toColumn)).isEnPassant() && (this.getPlayer() == EnumPlayer.WHITE ? this.getRow() == 4 : this.getRow() == 3) && (this.getBoard().getnMoves() - (this.getPlayer() == EnumPlayer.WHITE ? this.getBoard().getPiece(toRow-1, toColumn) : this.getBoard().getPiece(toRow+1, toColumn)).getLastMove()) == 1) {
            return true;
        }
        
        // else 
        else {
            return false;
        }
    }
    
    @Override
    public ArrayList<ArrayList<Integer>> possibleMoves() {
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        
        //move 1 square
        if(this.isValidMovement((this.getPlayer() == EnumPlayer.WHITE ? this.getRow() + 1: this.getRow() - 1), this.getColumn())) {
            ArrayList<Integer> aux = new ArrayList();
            aux.add((this.getPlayer() == EnumPlayer.WHITE ? this.getRow() + 1: this.getRow() - 1));
            aux.add(this.getColumn());
            moves.add(aux);
        }
        
        //move 2 squares
        if(this.isValidMovement((this.getPlayer() == EnumPlayer.WHITE ? this.getRow() + 2: this.getRow() - 2), this.getColumn())) {
            ArrayList<Integer> aux = new ArrayList();
            aux.add((this.getPlayer() == EnumPlayer.WHITE ? this.getRow() + 2: this.getRow() - 2));
            aux.add(this.getColumn());
            moves.add(aux);
        }
        
        //capture 
        if(this.isValidMovement((this.getPlayer() == EnumPlayer.WHITE ? this.getRow() + 1: this.getRow() - 1), this.getColumn() + 1)) {
            ArrayList<Integer> aux = new ArrayList();
            aux.add((this.getPlayer() == EnumPlayer.WHITE ? this.getRow() + 1: this.getRow() - 1));
            aux.add(this.getColumn() +1);
            moves.add(aux);
        }
        
        //capture
        if(this.isValidMovement((this.getPlayer() == EnumPlayer.WHITE ? this.getRow() + 1: this.getRow() - 1), this.getColumn() - 1)) {
            ArrayList<Integer> aux = new ArrayList();
            aux.add((this.getPlayer() == EnumPlayer.WHITE ? this.getRow() + 1: this.getRow() - 1));
            aux.add(this.getColumn() -1);
            moves.add(aux);
        }

        
        return moves;
    }
}
