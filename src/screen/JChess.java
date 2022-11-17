/*
 * 
 */
package screen;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import piece.Board;
import computer.Computer;
import piece.EnumPlayer;

/**
 *
 * @author tiago
 */
public class JChess extends JFrame{
    
    private static Board board;
    private static JBoard jBoard;
    
    public JChess (EnumPlayer computerPlayer) {
        setTitle("Jogo de Xadrez");
        this.setLayout(new BorderLayout());
        this.board = new Board(computerPlayer);
        this.jBoard = new JBoard(board);
        this.add(jBoard, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack();
        this.setVisible(true);
    }
    
    public static void main(String args[]) {
        
        Object[] opcoes = {"Brancas", "Pretas"};
        int option = JOptionPane.showOptionDialog( null , "Escolha a cor das peças" , "Jogo de Xadrez" , JOptionPane.YES_NO_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE ,null , opcoes , null);
        Computer computer = new Computer(option == 1 ? EnumPlayer.WHITE : EnumPlayer.BLACK);
        JChess jChessGame = new JChess(computer.getPlayer());
        
        while (true) {
            computer.playComputer(board);
        }
        
        

    }

    
    
}
