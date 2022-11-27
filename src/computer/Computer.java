/*
 * 
 */
package computer;

import java.util.ArrayList;
import piece.Board;
import piece.EnumPlayer;
import piece.Piece;
import screen.JChess;

/**
 *
 * @author tiago
 */

public class Computer {
    
    private final EnumPlayer computerPlayer;

    public Computer(EnumPlayer computerPlayer) {
        this.computerPlayer = computerPlayer;
    }

    
    public void playComputer(Board board) {
        
        if (board.getPlayer() == this.computerPlayer) {
            int[] move = minimax(3, board);
            board.movePiece(board.getPiece(move[1], move[2]), move[3], move[4]);
            JChess.print = true;
        }
    }
    
    private int evaluationFunction(Board board) {
        int e = 0;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (board.getPiece(i, j) != null && board.getPiece(i, j).getPlayer() == this.getComputerPlayer()) {
                    e += board.getPiece(i, j).getValuePiece();
                }
                
                else if (board.getPiece(i, j) != null && board.getPiece(i, j).getPlayer() != this.getComputerPlayer()) {
                    e -= board.getPiece(i, j).getValuePiece();
                }
            }
        }
        
        return e;
    }
    
    private int[] minimax(int depth, Board board) {
        ArrayList<int[]> resultList = new ArrayList<>();
        Board boardClone = new Board(board);
        int row, column, evaluate;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardClone.getPiece(i, j) != null && boardClone.getPiece(i, j).getPlayer() == boardClone.getPlayer()) {
                    Piece piece = board.getPiece(i, j);
                    ArrayList<ArrayList<Integer>> possibleMoves = piece.possibleMoves(board);
                    
                    if (!possibleMoves.isEmpty()) {
                        for (ArrayList<Integer> pos : possibleMoves) {
                            row = pos.get(0);
                            column = pos.get(1);
                            if (new Board(board).isValidMovementBoard(piece, row, column)) {
                                if (depth == 0) {
                                    evaluate = evaluationFunction(movePieceClone(boardClone, piece, row, column));
                                }
                                
                                else {
                                    evaluate = minimax (depth - 1, movePieceClone(boardClone, piece, row, column))[0];
                                }
                                int[] result = new int[5];
                                result[0] = evaluate;
                                result[1] = i;
                                result[2] = j;
                                result[3] = row;
                                result[4] = column;
                                resultList.add(result);
                            }
                        }
                    }
                }
            }
        }
        if (depth % 2 == 1) {
            return findMax(resultList);
        }
        else {
            return findMin(resultList);
        }
    }    
    
    private int[] findMin(ArrayList<int[]> list) {
        int min = 500, ind = 0;
        
        for(int i = 0; i < list.size(); i++) {
            if (list.get(i)[0] < min) {
                min = list.get(i)[0];
                ind = i;
            }
        }
        if (list.isEmpty()) {
            int [] result = new int[5];
            return result;
        }
        return list.get(ind);
    }
   
    private int[] findMax(ArrayList<int[]> list) {
        int max = -500, ind = 0;
        
        for(int i = 0; i < list.size(); i++) {
            if (list.get(i)[0] > max) {
                max = list.get(i)[0];
                ind = i;
            }
        }
        
        if (list.isEmpty()) {
            int [] result = new int[5];
            return result;
        }
        return list.get(ind);
    }
    
    private Board movePieceClone(Board board, Piece piece, int row, int column) {
        Board boardClone = new Board(board);
        boardClone.movePiece(piece.clone(), row, column);
        return boardClone;
    }
    
    //Getter and Setters 
    public EnumPlayer getComputerPlayer() {
        return computerPlayer;
    }
    
    public EnumPlayer getPlayer() {
        return computerPlayer;
    }
    
    
 
}
