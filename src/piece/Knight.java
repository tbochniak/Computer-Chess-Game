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
        
        //position out of the board
        if(!(toRow >= 0 && toRow < 8 && toColumn >= 0 && toColumn < 8)) {
            return false;
        }
        return (Math.abs(toRow - this.getRow()) == 2 && Math.abs(toColumn - this.getColumn()) == 1 || Math.abs(toRow - this.getRow()) == 1 && Math.abs(toColumn - this.getColumn()) == 2) && (this.getBoard().getPiece(toRow, toColumn) == null || this.getBoard().getPiece(toRow, toColumn).getPlayer() != this.getPlayer());
    }

    @Override
    public ArrayList<ArrayList<Integer>> possibleMoves() {
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        
        int row = this.getRow();
        int column = this.getColumn();
        
        Integer [][] p = {{row+2, column+1}, {row+2, column-1}, {row+1, column+2}, {row+1, column-2}, {row-2, column+1}, {row-2, column-1}, {row-1, column+2}, {row-1, column-2}};
        
        for(Integer[] i : p) {
            row = i[0];
            column = i[1];
            
            if(this.isValidMovement(row, column)) {
                ArrayList<Integer> aux = new ArrayList();
                aux.add(row);
                aux.add(column);
                moves.add(aux);
            }
        }
        
        return moves;
    }
    
}

