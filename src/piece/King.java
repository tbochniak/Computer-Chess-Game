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
    public King(Piece piece) {
        super(piece);
    }

    @Override
    public boolean isValidMovement(int toRow, int toColumn, Board board) {
        //position out of the board
        if(!(toRow >= 0 && toRow < 8 && toColumn >= 0 && toColumn < 8)) {
            return false;
        }
        
        //castle
        else if(this.isFirstMove() && Math.abs(toColumn-this.getColumn()) == 2) {
            
            //castle to left side
            if(toColumn > this.getColumn() && board.getPiece(this.getRow(), 7) instanceof Rook && board.getPiece(this.getRow(), 7).isFirstMove()) {
                for (int column = this.getColumn() + 1; column < 7; column++) {
                    if(board.getPiece(this.getRow(), column) != null || !board.isValidMovementBoard(this, this.getRow(), column)) {
                        return false;
                    }
                }
                return true;
            }
            
            //castle to right side
            else if(toColumn < this.getColumn() && board.getPiece(this.getRow(), 0).isFirstMove() && board.getPiece(this.getRow(), 0) instanceof Rook) {
                for (int column = this.getColumn() - 1; column > 0; column--) {
                    if(board.getPiece(this.getRow(), column) != null || !board.isValidMovementBoard(this, this.getRow(), column)) {
                        return false;
                    }
                }
                return true;
            }
        }
        
        return (Math.abs(toRow-this.getRow()) <= 1 && Math.abs(toColumn - this.getColumn()) <= 1)&& (board.getPiece(toRow, toColumn) == null || board.getPiece(toRow, toColumn).getPlayer() != this.getPlayer());
    }

    @Override
    public ArrayList<ArrayList<Integer>> possibleMoves(Board board) {
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int row = this.getRow() + i;
                int column = this.getColumn() + j;
                if (this.isValidMovement(row, column, board)) {
                    ArrayList<Integer> aux = new ArrayList();
                    aux.add(row);
                    aux.add(column);
                    moves.add(aux);                    
                }
            }
        }
        System.out.println(moves);
        return moves;
    }

    @Override
    public ArrayList<ArrayList<Integer>> possibleAttacks(Board board) {
        return this.possibleMoves(board);
    }
}
