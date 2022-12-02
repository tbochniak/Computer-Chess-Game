
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
        super(player, row, column, (player == EnumPlayer.WHITE ? "src/figs/wQueen.png" : "src/figs/bQueen.png"), 9);
    }
    public Queen(Piece piece) {
        super(piece);
    }
    
    @Override
    public boolean isValidMovement(int toRow, int toColumn, Board board) {
        
        //position out of the board
        if(!(toRow >= 0 && toRow < 8 && toColumn >= 0 && toColumn < 8)) {
            return false;
        }
        return (new Bishop(this.getPlayer(), this.getRow(), this.getColumn())).isValidMovement(toRow, toColumn, board) || (new Rook(this.getPlayer(), this.getRow(), this.getColumn())).isValidMovement(toRow, toColumn, board);
    }

    @Override
    public ArrayList<ArrayList<Integer>> possibleMoves(Board board) {
       ArrayList<ArrayList<Integer>> movesB = new ArrayList<>();
       ArrayList<ArrayList<Integer>> movesR = new ArrayList<>();
       
       movesB = (new Bishop(this.getPlayer(), this.getRow(), this.getColumn())).possibleMoves(board);
       movesR = (new Rook(this.getPlayer(), this.getRow(), this.getColumn())).possibleMoves(board);
       
       movesB.addAll(movesR);
       
       return movesB;
    }

    @Override
    public ArrayList<ArrayList<Integer>> possibleAttacks(Board board) {
        return this.possibleMoves(board);
    }
}