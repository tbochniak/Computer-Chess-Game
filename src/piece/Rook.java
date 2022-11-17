/*
 * 
 */
package piece;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tiago
 */
public class Rook extends Piece{

    public Rook(EnumPlayer player, int row, int column) {
        super(player, row, column, (player == EnumPlayer.WHITE ? "src/figs/wRook.png" : "src/figs/bRook.png"), 5);
    }
    public Rook(Piece piece) {
        super(piece);
    }

    @Override
    public boolean isValidMovement(int toRow, int toColumn, Board board) {
        
        //position out of the board
        if(!(toRow >= 0 && toRow < 8 && toColumn >= 0 && toColumn < 8)) {
            return false;
        }
        
        else if ((toColumn == this.getColumn() || toRow == this.getRow()) && (board.getPiece(toRow, toColumn) == null || board.getPiece(toRow, toColumn).getPlayer() != this.getPlayer())) {
            if (toRow > this.getRow()) {
                for (int i = 1; i < toRow - this.getRow(); i++) {
                    if (!(board.getPiece(this.getRow()+i, this.getColumn()) == null)) {
                        return false;
                    }
                }
            }
            
            else if (toRow < this.getRow()) {
                for (int i = 1; i < this.getRow() - toRow; i++) {
                    if (!(board.getPiece(this.getRow()-i, this.getColumn()) == null)) {
                        return false;
                    }  
                }
            }
            
            else if(toColumn > this.getColumn()) {
                for (int i = 1; i < toColumn - this.getColumn(); i++) {
                    if (!(board.getPiece(this.getRow(), this.getColumn()+i) == null)) {
                        return false;
                    }  
                }
            }           

            else {
                for (int i = 1; i < this.getColumn() - toColumn; i++) {
                    if (!(board.getPiece(this.getRow(), this.getColumn()-i) == null)) {
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
        
        //forward
        for (int row = this.getRow() + 1; row < 8; row ++) {
            if(!this.isValidMovement(row, this.getColumn(), board)) {
                break;
            }
            ArrayList<Integer> aux = new ArrayList();
            aux.add(row);
            aux.add(this.getColumn());
            moves.add(aux);
        }
        
        //backward 
        for (int row = this.getRow() - 1; row >= 0; row --) {
            if(!this.isValidMovement(row, this.getColumn(), board)) {
                break;
            }
            ArrayList<Integer> aux = new ArrayList();
            aux.add(row);
            aux.add(this.getColumn());
            moves.add(aux);
        }
        
        //left side 
        for (int column = this.getColumn() + 1; column < 8; column ++) {
            if(!this.isValidMovement(this.getRow(), column, board)) {
                break;
            }
            ArrayList<Integer> aux = new ArrayList();
            aux.add(this.getRow());
            aux.add(column);
            moves.add(aux);
        }
        
        //right side
        for (int column = this.getColumn() - 1; column >= 0; column --) {
            if(!this.isValidMovement(this.getRow(), column, board)) {
                break;
            }
            ArrayList<Integer> aux = new ArrayList();
            aux.add(this.getRow());
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
