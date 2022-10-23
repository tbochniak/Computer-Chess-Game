/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import piece.Piece;

/**
 *
 * @author tiago
 */
public class JPiece extends JLabel{
    
    private Piece piece;

    JPiece(Piece piece) {
        this.piece = piece;
        this.setIcon(new ImageIcon(piece.getFigure()));
        this.setIcon(new ImageIcon(new ImageIcon(piece.getFigure()).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
}