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
    private EnumPlayer player;
    private int row, column;
    private boolean active, selected, firstMove;
    private String figure;
    private Board board;
    
    
    //Constructor 
    public Piece(EnumPlayer player, int row, int column, String figure) {
        
        this.player = player;
        this.row = row;
        this.column = column;
        this.figure = figure;
        this.active = true;
        this.selected = false;
        this.firstMove = true;
        
    }

    //Public methods
    public abstract boolean isValidMovement(int toRow, int toColumn);
    
    public abstract ArrayList<Integer[]> possibleMoves(); 
    
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

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
    
    

}