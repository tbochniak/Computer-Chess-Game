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
        this.column = piece.getColumn();
        this.figure = piece.getFigure();
        this.active = piece.isActive();
        this.selected = piece.isSelected();
        this.enPassant = piece.isEnPassant();
        this.lastMove = piece.getLastMove();
        this.valuePiece = piece.getValuePiece();
        this.firstMove = piece.isFirstMove();
    }
    
    //Public methods
    public abstract boolean isValidMovement(int toRow, int toColumn, Board board);
    public abstract ArrayList<ArrayList<Integer>> possibleMoves(Board board); 
    public abstract ArrayList<ArrayList<Integer>> possibleAttacks(Board board);
    
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

    protected boolean isActive() {
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

    protected boolean isEnPassant() {
        return enPassant;
    }

    public void setEnPassant(boolean enPassant) {
        this.enPassant = enPassant;
    }

    protected int getLastMove() {
        return lastMove;
    }

    public void setLastMove(int lastMove) {
        this.lastMove = lastMove;
    }

    public int getValuePiece() {
        return valuePiece;
    }

    protected boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean isFirstMove) {
        this.firstMove = isFirstMove;
    }

    public String getFigure() {
        return figure;
    }
    
    public Piece clone(Piece this) {
        if (this instanceof Bishop) {
            return new Bishop(this);
        }
        else if (this instanceof Knight) {
            return new Knight(this);
        }
        else if (this instanceof Pawn) {
            return new Pawn(this);
        }
        else if (this instanceof Rook) {
            return new Rook(this);
        }
        else if (this instanceof Queen) {
            return new Queen(this);
        }
        else if (this instanceof King) {
            return new King(this);
        }
        else {
            return null;
        }
    }
    
    
}