/*
 * 
 */
package piece;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import screen.JBoard;
import screen.JCell;
import screen.JChess;

/**
 *
 * @author tiago
 */
public class Board {
    
    // Atributtes
    private Piece[][] pieces;
    private Piece selected;
    private EnumPlayer player;
    private final EnumPlayer computerPlayer;
    private int nMoves;
    
    // Contructor 
    public Board(EnumPlayer computerPlayer) {
        
        this.pieces = new Piece[8][8];
        this.selected = null;
        this.player = EnumPlayer.WHITE;
        this.computerPlayer = computerPlayer;
        
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
        //Double click
        if (piece.isSelected()) {
            piece.setSelected(false);
            this.selected = null;
        }
        //Click on another piece of mine
        else if (this.selected != null) {
            this.selected.setSelected(false);
            piece.setSelected(true);
            this.selected = piece;
        }
        //Select the clicked piece
        else {
            piece.setSelected(true);
            this.selected = piece;            
        }
        
    }
    
    public void play(JCell jCell) {
        int row = jCell.getRow();
        int column = jCell.getColumn();
        Piece piece = this.getPiece(row, column);
        
        //Select my piece
        if (piece != null && piece.getPlayer().equals(this.player)) {
            this.selectPiece(piece);
        }
        
        //Move my piece
        else if (this.selected != null) {
            this.movePiece(this.selected, row, column);
        }
    }
   
    public void movePiece(Piece piece, int toRow, int toColumn) {
        
        if (piece.isValidMovement(toRow, toColumn) && isValidMovementBoard(piece, toRow, toColumn)) {
            
            if(this.isCapture(piece, toRow, toColumn)) {
                this.capturePiece(piece, toRow, toColumn);
            }
            
            else if (this.isCastle(piece, toRow, toColumn)) {
                this.castleMove(piece, toRow, toColumn);
            }
            
            if(this.isPromotion(piece, toRow, toColumn)) {
                this.promotionPawn(piece, toRow, toColumn);
                piece = this.pieces[piece.getRow()][piece.getColumn()];
            }
            
            this.pieces[piece.getRow()][piece.getColumn()] = null;
            
            piece.setRow(toRow);
            piece.setColumn(toColumn);
            this.setPiece(piece);
            piece.setLastMove(this.nMoves);
            piece.setFirstMove(false);
            this.selectPiece(piece);
            this.invertPlayer();
            this.nMoves ++;
        }
    }
    
    public boolean isValidMovementBoard(Piece piece, int toRow, int toColumn) {
        
        Piece[][] piecesClone = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            piecesClone[i] = this.pieces[i].clone();
        }
            
        if(this.isCapture(piece, toRow, toColumn)) {
            this.capturePiece(piece, toRow, toColumn);
        }
            
        else if (this.isCastle(piece, toRow, toColumn)) {
            this.castleMove(piece, toRow, toColumn);
        }
            
        if(this.isPromotion(piece, toRow, toColumn)) {
            this.promotionPawn(piece, toRow, toColumn);
            piece = piecesClone[piece.getRow()][piece.getColumn()];
        }
            
        piecesClone[piece.getRow()][piece.getColumn()] = null;   
        piecesClone[toRow][toColumn] = piece;    
        
        return !this.isCheck(piecesClone, piece, toRow, toColumn);
    }
    
    
    public boolean isCapture(Piece piece, int toRow, int toColumn) {
        
        if (piece.getBoard().getPiece(toRow, toColumn) != null && piece.getBoard().getPiece(toRow, toColumn).getPlayer() != this.getPlayer()) {
            return true;
        }
        
        // en passant 
        else if (piece instanceof Pawn && piece.getBoard().getPiece(toRow, toColumn) == null && (piece.getPlayer() == EnumPlayer.WHITE ? piece.getBoard().getPiece(toRow-1, toColumn) : piece.getBoard().getPiece(toRow+1, toColumn)) != null && (piece.getPlayer() == EnumPlayer.WHITE ? piece.getBoard().getPiece(toRow-1, toColumn) : piece.getBoard().getPiece(toRow+1, toColumn)).isEnPassant() && (piece.getPlayer() == EnumPlayer.WHITE ? piece.getRow() == 4 : piece.getRow() == 3) && (piece.getBoard().getnMoves() - (piece.getPlayer() == EnumPlayer.WHITE ? piece.getBoard().getPiece(toRow-1, toColumn) : piece.getBoard().getPiece(toRow+1, toColumn)).getLastMove()) == 1) {
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
    
        
    //castle
    public boolean isCastle(Piece piece, int toRow, int toColumn) {
        return (piece instanceof King && Math.abs(piece.getColumn()- toColumn) == 2);
    }
    
    public void castleMove (Piece piece, int toRow, int toColumn) {
        if (toColumn > piece.getColumn()) {
            Piece rook = piece.getBoard().getPiece(piece.getRow(), 7);
            rook.setColumn(toColumn - 1);
            this.setPiece(rook);
            this.pieces[piece.getRow()][7] = null;
            
        }
        else {
            Piece rook = piece.getBoard().getPiece(piece.getRow(), 0);
            rook.setColumn(toColumn + 1);
            this.setPiece(rook);
            this.pieces[piece.getRow()][0] = null;            
        }
    }
    
    public boolean isPromotion(Piece piece, int toRow, int toColumn) {
        return piece instanceof Pawn && (toRow == 7 || toRow == 0);
    }
    
    public void promotionPawn (Piece piece, int toRow, int toColumn) {
        if (piece.getPlayer() == this.computerPlayer) {
            piece.setActive(false);
            this.setPiece(new Queen(this.getComputerPlayer(), piece.getRow(), piece.getColumn()));
        }
        else {
            piece.setActive(false);
            Object[] opcoes = {"Cavalo", "Bispo", "Torre", "Rainha"};
            int option = JOptionPane.showOptionDialog( null , "Escolha a sua peça" , "Jogo de Xadrez" , JOptionPane.YES_NO_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE ,null , opcoes , null);
            
            switch (option) {
                case 0:
                    this.pieces[piece.getRow()][piece.getColumn()] =  new Knight(piece.getPlayer(), piece.getRow(), piece.getColumn()); 
                    break;
                case 1:
                    this.pieces[piece.getRow()][piece.getColumn()] =  new Bishop(piece.getPlayer(), piece.getRow(), piece.getColumn()); 
                    break;
                case 2:
                    this.pieces[piece.getRow()][piece.getColumn()] =  new Rook(piece.getPlayer(), piece.getRow(), piece.getColumn()); 
                    break;
                case 3:
                    this.pieces[piece.getRow()][piece.getColumn()] =  new Queen(piece.getPlayer(), piece.getRow(), piece.getColumn()); 
                    break;
                    
            }
        }
    }
    
    public void invertPlayer() {
        if (this.player.equals(EnumPlayer.WHITE)) this.player = EnumPlayer.BLACK;
        else this.player = EnumPlayer.WHITE;
    }
    
    public boolean isCheck(Piece piece, int toRow, int toColumn) {
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        ArrayList<Integer> pKing = new ArrayList();
        
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.getPiece(i, j) != null && this.getPiece(i, j).getPlayer() != this.getPlayer()) {
                    moves.addAll(this.getPiece(i, j).possibleAttacks());
                }
                else if(this.getPiece(i, j) != null && this.getPiece(i, j) instanceof King && this.getPiece(i, j).getPlayer() == this.getPlayer()) {
                    pKing.add(i);
                    pKing.add(j);
                }
            }
        }
        return moves.contains(pKing);
    }
    
    public boolean isCheck(Piece[][] piecesClone, Piece piece, int toRow, int toColumn) {
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        ArrayList<Integer> pKing = new ArrayList();
        
        Piece[][] piecesOriginal = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            piecesOriginal[i] = this.pieces[i].clone();
        }
        
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (piecesClone[i][j] != null && piecesClone[i][j].getPlayer() != this.getPlayer()) {
                    piecesClone[i][j].setPieces(piecesClone);
                    moves.addAll(piecesClone[i][j].possibleAttacks());
                    piecesClone[i][j].setPieces(piecesOriginal);
                }
                else if(piecesClone[i][j] != null && piecesClone[i][j] instanceof King && piecesClone[i][j].getPlayer() == this.getPlayer()) {
                    pKing.add(i);
                    pKing.add(j);
                }
            }
        }
        return moves.contains(pKing);
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

    public Piece[][] getPieces() {
        return pieces;
    }

    public EnumPlayer getComputerPlayer() {
        return computerPlayer;
    }
    
}
