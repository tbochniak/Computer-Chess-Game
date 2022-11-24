/*
 * 
 */
package computer;

import java.util.ArrayList;
import piece.Board;
import piece.EnumPlayer;
import piece.Piece;

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
            int[] move = minimax(board);
            board.movePiece(board.getPiece(move[1], move[2]), move[3], move[4]);
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
    
    private int[] findBestMove (Board board) {
        Board boardClone = new Board(board);
        int[] bestMoveData = new int[5];
        int row, column, evaluation, score = -500;
        
        
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
                                evaluation = board.getPlayer() == this.computerPlayer? this.evaluationFunction(this.movePieceClone(board, piece, row, column)) : -this.evaluationFunction(this.movePieceClone(board, piece, row, column));
                                if (evaluation > score) {
                                    score = evaluation;
                                    bestMoveData[0] = score;
                                    bestMoveData[1] = i;
                                    bestMoveData[2] = j;
                                    bestMoveData[3] = row;
                                    bestMoveData[4] = column;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return bestMoveData;
    }
    
    private int[] findBestMove(ArrayList<int[]> listMoves, Board board) {
        int[] bestMoveData = new int[5];
        int row, column, evaluation, score = -500;
        
        for(int[] move : listMoves) {
            evaluation = this.evaluationFunction(this.movePieceClone(board, board.getPiece(move[1], move[2]), move[3], move[4]));
            if (evaluation > score) {
                score = evaluation;
                bestMoveData[0] = score;
                bestMoveData[1] = move[1];
                bestMoveData[2] = move[2];
                bestMoveData[3] = move[3];
                bestMoveData[4] = move[4];
            }
        }
        return bestMoveData;
    }
    
    private int[] minimax(Board board) {
        ArrayList<int[]> resultList = new ArrayList<>();
        Board boardClone = new Board(board);
        int row, column, findBestMove;
        
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
                                findBestMove = findBestMove(movePieceClone(boardClone, piece, row, column))[0];
                                int[] result = new int[5];
                                result[0] = findBestMove;
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
        
        resultList = findMin(resultList);
        
        return this.findBestMove(resultList, board);
    }
    
    
    
    private int[] minimaxOld (Board board) {
        int[] result = new int[5];
        ArrayList<ArrayList<Integer>> listResult = new ArrayList<>();
        Board boardClone = this.boardClone(board);
        int score = -1000, row, column;
        
        int scoreMin = 1000;
        int[] resultFinal = new int[5];
        
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
                                if(this.findBestMove(this.movePieceClone(boardClone, piece, row, column))[0] > score) {
                                    score = this.findBestMove(this.movePieceClone(boardClone, piece, row, column))[0];
                                    result[0] = score;
                                    result[1] = i;
                                    result[2] = j;
                                    result[3] = row;
                                    result[4] = column;
                                }
                            }
                        }
                    
                    if(score < scoreMin) {
                        scoreMin = score;
                        resultFinal[0] = result[0];
                        resultFinal[1] = result[1];
                        resultFinal[2] = result[2];
                        resultFinal[3] = result[3];
                        resultFinal[4] = result[4];
                    }
                    }
                    score = -1000;
                    scoreMin = 1000;
                }
            }
        }
        
        return resultFinal;
    }
    
    /*
    private void minimax(Board board) {
        int ind = 0;
        ArrayList<Integer> possibleScores = new ArrayList();
        ArrayList<Board> listPossibleBoards = this.searchTree(board);
        
        
        for(Board possibleBoard : listPossibleBoards) {
            possibleScores.add(scoreBestMove(possibleBoard));
        }
        
        ind = findMinInd(possibleScores);
        
        board = listPossibleBoards.get(ind);
    }
    */
    private ArrayList<int[]> findMin(ArrayList<int[]> list) {
        int min = 500;
        ArrayList<int[]> listMin = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            if (list.get(i)[0] < min) {
                min = list.get(i)[0];
            }
        }
        for (int[] i : list) {
            if(i[0] == min) {
                listMin.add(i);
            }
        }
        return listMin;
    }
    
    private Board boardClone (Board board) {
        return new Board(board);
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
