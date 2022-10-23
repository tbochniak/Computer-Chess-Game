/*
 * 
 */
package screen;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import piece.Board;
import piece.EnumPlayer;

/**
 *
 * @author tiago
 */
public class JChess extends JFrame{
    
    //private static JLabel lbvez;
    private Board board;
   // private final JPanel painelCemiterio;
    
    //private JButton btReiniciarJogo;
   // private JButton btPassaVez;
    
    //public JProgressBar pbTempo;
    
    public JChess () {
        setTitle("Jogo de Xadrez");
        this.setLayout(new BorderLayout());
       // this.pbTempo = new JProgressBar();
      //  pbTempo.setMinimum(0);
      //  pbTempo.setMaximum(10000);
        //pbTempo.setValue(0);
        //final ControlaTempo controlaTempo = new ControlaTempo(pbTempo);
        this.board = new Board();
        JBoard jBoard = new JBoard(board);
        //controlaTempo.setBoard(jBoard);
        this.add(jBoard, BorderLayout.CENTER);
       
       // JPanel pnTop = new JPanel();
       // lbvez = new JLabel("Vez de: Branco");
        //pnTop.add(lbvez);
        //this.add(pnTop, BorderLayout.NORTH);
        
        //JPanel pnLateral = new JPanel();
       // pnLateral.setLayout(new GridLayout(10,1));
       // btReiniciarJogo = new JButton("Reiniciar o Jogo");
        
        //btReiniciarJogo.addActionListener(new ActionListener() {
            //@Override
           // public void actionPerformed(ActionEvent ae) {
           // }
       // });
        
        //painelCemiterio = new JPanel();
        //painelCemiterio.setLayout(new FlowLayout());
        //this.add(painelCemiterio, BorderLayout.EAST);
       // btPassaVez = new JButton("Passa a vez"); 
        //pnLateral.add(btReiniciarJogo);
        //pnLateral.add(btPassaVez);
        //this.add(pnLateral, BorderLayout.WEST);
        //this.add(pbTempo, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack();
        this.setVisible(true);
    }
    
    public static void main(String args[]) {
        new JChess();
    }

}
