/*
 * 
 */
package piece;

import java.util.ArrayList;


/**
 *
 * @author tiago
 */
public class Queen extends Piece{
    
    public Queen(EnumPlayer player, int row, int column) {
        super(player, row, column, (player == EnumPlayer.WHITE ? "src/figs/wQueen.png" : "src/figs/bQueen.png"), 10);
    }

    @Override
    public boolean isValidMovement(int toRow, int toColumn) {
        
        //position out of the board
        if(!(toRow >= 0 && toRow < 8 && toColumn >= 0 && toColumn < 8)) {
            return false;
        }
        return (new Bishop(this.getPlayer(), this.getRow(), this.getColumn(), this.getBoard())).isValidMovement(toRow, toColumn) || (new Rook(this.getPlayer(), this.getRow(), this.getColumn(), this.getBoard())).isValidMovement(toRow, toColumn);
    }

    @Override
    public ArrayList<ArrayList<Integer>> possibleMoves() {
       ArrayList<ArrayList<Integer>> movesB = new ArrayList<>();
       ArrayList<ArrayList<Integer>> movesR = new ArrayList<>();
       
       movesB = (new Bishop(this.getPlayer(), this.getRow(), this.getColumn(), this.getBoard())).possibleMoves();
       movesR = (new Rook(this.getPlayer(), this.getRow(), this.getColumn(), this.getBoard())).possibleMoves();
       
       movesB.addAll(movesR);
       
       return movesB;
    }

}