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
        super(player, row, column, (player == EnumPlayer.WHITE ? "src/figs/wBishop.png" : "src/figs/bBishop.png"), 3);
    }  
    public Bishop(Piece piece) {
        super(piece);
    }
    
    @Override
    public boolean isValidMovement(int toRow, int toColumn, Board board) {
        
        //position out of the board
        if(!(toRow >= 0 && toRow < 8 && toColumn >= 0 && toColumn < 8)) {
            return false;
        }
        
        else if (Math.abs(toColumn - this.getColumn()) == Math.abs(toRow - this.getRow()) && (board.getPiece(toRow, toColumn) == null || board.getPiece(toRow, toColumn).getPlayer() != this.getPlayer())) {
            if (toRow > this.getRow() && toColumn > this.getColumn()) {
                for (int i = 1; i < toRow - this.getRow(); i++) {
                    if (!(board.getPiece(this.getRow()+i, this.getColumn()+i) == null)) {
                        return false;
                    }
                }
            }
            
            else if (toRow > this.getRow() && toColumn < this.getColumn()) {
                for (int i = 1; i < toRow - this.getRow(); i++) {
                    if (!(board.getPiece(this.getRow()+i, this.getColumn()-i) == null)) {
                        return false;
                    }  
                }
            }
            
            else if(toRow < this.getRow() && toColumn > this.getColumn()) {
                for (int i = 1; i < this.getRow() - toRow; i++) {
                    if (!(board.getPiece(this.getRow()-i, this.getColumn()+i) == null)) {
                        return false;
                    }  
                }
            }           

            else {
                for (int i = 1; i < this.getRow() - toRow; i++) {
                    if (!(board.getPiece(this.getRow()-i, this.getColumn()-i) == null)) {
                        return false;
                    }  
                }
            }
            
            //
            return true;
        }
        
        else {
            return false;
        }
    }

    @Override
    public ArrayList<ArrayList<Integer>> possibleMoves(Board board) {
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        int column;
        //diagonal ++
        for (int row = this.getRow() + 1; row < 8; row ++) {
            column = this.getColumn() + (row - this.getRow());
            if(!this.isValidMovement(row, column, board)) {
                break;
            }
            ArrayList<Integer> aux = new ArrayList();
            aux.add(row);
            aux.add(column);
            moves.add(aux); 
        }
        
        //diagonal +-
        for (int row = this.getRow() + 1; row < 8; row ++) {
            column = this.getColumn() - (row - this.getRow());
            if(!this.isValidMovement(row, column, board)) {
                break;
            }
            ArrayList<Integer> aux = new ArrayList();
            aux.add(row);
            aux.add(column);
            moves.add(aux);
        }
        
        //diagonal -+
        for (int row = this.getRow() - 1; row >= 0; row --) {
            column = this.getColumn() - (row - this.getRow());
            if(!this.isValidMovement(row, this.getColumn() - row + this.getRow(), board)) {
                break;
            }
            ArrayList<Integer> aux = new ArrayList();
            aux.add(row);
            aux.add(column);
            moves.add(aux);
        }
        
        //diagonal --
        for (int row = this.getRow() - 1; row >= 0; row --) {
            column = this.getColumn() + (row - this.getRow());
            if(!this.isValidMovement(row, this.getColumn() + row - this.getRow(), board)) {
                break;
            }
            ArrayList<Integer> aux = new ArrayList();
            aux.add(row);
            aux.add(column);
            moves.add(aux);
        }
        
        return moves;
    }

    @Override
    public ArrayList<ArrayList<Integer>> possibleAttacks(Board board) {
        return this.possibleMoves(board);
    } 
}
