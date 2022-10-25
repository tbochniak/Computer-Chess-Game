/*
 * 
 */
package screen;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import piece.Board;
import piece.Piece;

/**
 *
 * @author tiago
 */
public class JBoard extends JPanel implements MouseListener{
    
    private Board board;
        
    public JBoard(Board board) {
        this.board = board;
        this.drawBoard();
    }
    
        public void drawBoard() {
        
        this.removeAll();
        this.setLayout(new GridLayout(8,8));
        
        JCell jCell;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                
                Piece piece = this.board.getPiece(i, j);
                if (piece == null) {
                    jCell = new JCell(i, j);
                }
                else {
                    jCell = new JCell(new JPiece(piece));
                }
                if ((i+j)%2 == 0) {
                    jCell.setBackground(new Color(210,180,140));
                }
                else {
                    jCell.setBackground(new Color(253,245,230));
                }
                this.add(jCell);
                //Criar outra classe depois
                jCell.addMouseListener(this);
            }
        }
        this.revalidate();
    }
    

    // Getters and setters
    public Board getBoard() {
        return this.board;
    }
    
    // MouseListener
    @Override
    public void mouseClicked(MouseEvent me) {
        JCell jCell = (JCell) me.getSource();
        this.board.play(jCell);
        this.drawBoard();
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
}
