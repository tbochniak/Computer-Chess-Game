/*
 * 
 */
package piece;

import screen.JCell;

/**
 *
 * @author tiago
 */
public class Board {
    
    // Atributtes
    private Piece[][] pieces;
    private Piece selected;
    private EnumPlayer player;
    private int nMoves;
    
    // Contructor 
    public Board() {
        
        this.pieces = new Piece[8][8];
        this.selected = null;
        this.player = EnumPlayer.WHITE;
        
        Piece[] listOfPieces = new Piece[32];
        listOfPieces[0] = new Rook(EnumPlayer.WHITE, 0, 0);
        listOfPieces[1] = new Rook(EnumPlayer.WHITE, 0, 7);
        listOfPieces[2] = new Rook(EnumPlayer.BLACK, 7, 0);
        listOfPieces[3] = new Rook(EnumPlayer.BLACK, 7, 7);
        
        listOfPieces[4] = new Knight(EnumPlayer.WHITE, 0, 1);
        listOfPieces[5] = new Knight(EnumPlayer.WHITE, 0, 6);
        listOfPieces[6] = new Knight(EnumPlayer.BLACK, 7, 1);
        listOfPieces[7] = new Knight(EnumPlayer.BLACK, 7, 6);
        
        listOfPieces[8] = new Bishop(EnumPlayer.WHITE, 0, 2);
        listOfPieces[9] = new Bishop(EnumPlayer.WHITE, 0, 5);
        listOfPieces[10] = new Bishop(EnumPlayer.BLACK, 7, 2);
        listOfPieces[11] = new Bishop(EnumPlayer.BLACK, 7, 5);
        
        listOfPieces[12] = new Queen(EnumPlayer.WHITE, 0, 3);
        listOfPieces[13] = new Queen(EnumPlayer.BLACK, 7, 3);
        
        listOfPieces[14] = new King(EnumPlayer.WHITE, 0, 4);
        listOfPieces[15] = new King(EnumPlayer.BLACK, 7, 4);
        
        for (int i = 16; i < 16+8; i++) {
            listOfPieces[i] = new Pawn(EnumPlayer.WHITE, 1, i-16);
        }
        
        for (int i = 16+8; i < 32; i++) {
            listOfPieces[i] = new Pawn(EnumPlayer.BLACK, 6, i-16-8);
        }
        
        for (Piece p : listOfPieces)  {
            this.addPiece(p);
        }
        
    }
    
    // Public methods
    public void addPiece(Piece piece) {
        this.pieces[piece.getRow()][piece.getColumn()] = piece;
        piece.setBoard(this);
    } 
    
    public void selectPiece(Piece piece) {
        if (piece.isSelected()) {
            piece.setSelected(false);
            this.selected = null;
        }
        
        else if (this.selected != null) {
            this.selected.setSelected(false);
            piece.setSelected(true);
            this.selected = piece;
        }
        
        else {
            piece.setSelected(true);
            this.selected = piece;            
        }
        
    }
    
    public void play(JCell jCell) {
        int row = jCell.getRow();
        int column = jCell.getColumn();
        Piece piece = this.getPiece(row, column);
        
        
        
        if (piece != null && piece.getPlayer().equals(this.player)) {
            this.selectPiece(piece);  
            System.out.println(piece.possibleMoves());
        }
        
        else if (this.selected != null) {
            this.movePiece(this.selected, row, column);
        }
        
    }
   
    public void movePiece(Piece piece, int toRow, int toColumn) {
        if (piece.isValidMovement(toRow, toColumn)) {
            if(this.isCapture(piece, toRow, toColumn)) {
                this.capturePiece(piece, toRow, toColumn);
            }
            
            this.pieces[piece.getRow()][piece.getColumn()] = null;
            piece.setRow(toRow);
            piece.setColumn(toColumn);
            piece.setLastMove(this.nMoves);
            this.setPiece(piece);
            this.selectPiece(piece);
            this.invertPlayer();
            this.nMoves ++;
        }
    }
    
    public boolean isCapture(Piece piece, int toRow, int toColumn) {
        
        if (piece.getBoard().getPiece(toRow, toColumn) != null) {
            return true;
        }
        
        // en passant 
        else if (piece.getBoard().getPiece(toRow, toColumn) == null && (piece.getPlayer() == EnumPlayer.WHITE ? piece.getBoard().getPiece(toRow-1, toColumn) : piece.getBoard().getPiece(toRow+1, toColumn)) != null && (piece.getPlayer() == EnumPlayer.WHITE ? piece.getBoard().getPiece(toRow-1, toColumn) : piece.getBoard().getPiece(toRow+1, toColumn)).isEnPassant() && (piece.getPlayer() == EnumPlayer.WHITE ? piece.getRow() == 4 : piece.getRow() == 3) && (piece.getBoard().getnMoves() - (piece.getPlayer() == EnumPlayer.WHITE ? piece.getBoard().getPiece(toRow-1, toColumn) : piece.getBoard().getPiece(toRow+1, toColumn)).getLastMove()) == 1) {
            return true;
        }
        
        else {
            return false;
        }
    }
    
    public void capturePiece(Piece piece, int toRow, int toColumn) {
        
        if (piece.getBoard().getPiece(toRow, toColumn) != null) {
            piece.getBoard().getPiece(toRow, toColumn).setActive(false);
            
        }
        
        // en passant 
        else if (piece.getBoard().getPiece(toRow, toColumn) == null && (piece.getPlayer() == EnumPlayer.WHITE ? piece.getBoard().getPiece(toRow-1, toColumn) : piece.getBoard().getPiece(toRow+1, toColumn)) != null && (piece.getPlayer() == EnumPlayer.WHITE ? piece.getBoard().getPiece(toRow-1, toColumn) : piece.getBoard().getPiece(toRow+1, toColumn)).isEnPassant() && (piece.getPlayer() == EnumPlayer.WHITE ? piece.getRow() == 4 : piece.getRow() == 3) && (piece.getBoard().getnMoves() - (piece.getPlayer() == EnumPlayer.WHITE ? piece.getBoard().getPiece(toRow-1, toColumn) : piece.getBoard().getPiece(toRow+1, toColumn)).getLastMove()) == 1) {
            this.getPiece(piece.getPlayer() == EnumPlayer.WHITE ? toRow - 1 : toRow + 1, toColumn).setActive(false);
            this.pieces[piece.getPlayer() == EnumPlayer.WHITE ? toRow - 1 : toRow + 1][toColumn] = null;
        }
    }
    
    public void invertPlayer() {
        if (this.player.equals(EnumPlayer.WHITE)) this.player = EnumPlayer.BLACK;
        else this.player = EnumPlayer.WHITE;
    }
    
    //Getters and setters
    public Piece getPiece(int row, int column) {
        return pieces[row][column];
    }
    
    public void setPiece(Piece piece) {
        this.pieces[piece.getRow()][piece.getColumn()] = piece;
        piece.setBoard(this);
    }

    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces;
    }

    public Piece getSelected() {
        return selected;
    }

    public void setSelected(Piece selected) {
        this.selected = selected;
    }

    public EnumPlayer getPlayer() {
        return player;
    }

    public int getnMoves() {
        return nMoves;
    }
    
    
}
