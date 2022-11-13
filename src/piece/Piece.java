/*
 * Piece() is an abastract class that defines the common characteristic of all pieces
 */
package piece;

import java.util.ArrayList;

/**
 *
 * @author tiago
 */
public abstract class Piece {

    //Atributtes
    private final EnumPlayer player;
    private int row, column, lastMove;
    private final int valuePiece;
    private boolean active, selected, enPassant, firstMove;
    private final String figure;
    private Board board;
    
    
    //Constructor 
    public Piece(EnumPlayer player, int row, int column, String figure, int valuePiece) {
        
        this.player = player;
        this.row = row;
        this.column = column;
        this.figure = figure;
        this.active = true;
        this.selected = false;
        this.enPassant = false;
        this.lastMove = 0;
        this.valuePiece = valuePiece;
        this.firstMove = true;
    }
    
    public Piece(Piece piece) {
        this.player = piece.getPlayer();
        this.row = piece.getRow();
        this.column = piece.column;
        this.figure = piece.getFigure();
        this.active = true;
        this.selected = false;
        this.enPassant = false;
        this.lastMove = 0;
        this.valuePiece = piece.getValuePiece();
        this.firstMove = true;
    }

    //Public methods
    public abstract boolean isValidMovement(int toRow, int toColumn);
    
    public abstract ArrayList<ArrayList<Integer>> possibleMoves(); 
    public abstract ArrayList<ArrayList<Integer>> possibleAttacks();
    
    //getters e setters 
    public EnumPlayer getPlayer() {
        return player;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getFigure() {
        return figure;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isEnPassant() {
        return enPassant;
    }

    public void setEnPassant(boolean enPassant) {
        this.enPassant = enPassant;
    }

    public int getLastMove() {
        return lastMove;
    }

    public void setLastMove(int lastMove) {
        this.lastMove = lastMove;
    }

    public int getValuePiece() {
        return valuePiece;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean isFirstMove) {
        this.firstMove = isFirstMove;
    }
    
    public void setPieces(Piece [][] pieces) {
        this.board.setPieces(pieces);
    }
    
}