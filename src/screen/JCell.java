/*
 * 
 */
package screen;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author tiago
 */
public class JCell extends JPanel{
    
    private int row, column;
    private JPiece jPiece;

    public JCell(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public JCell(JPiece jPiece) {
        this.jPiece = jPiece;
        this.row = jPiece.getPiece().getRow();
        this.column = jPiece.getPiece().getColumn();
        this.add(jPiece);
        if (jPiece.getPiece() != null && jPiece.getPiece().isSelected()) {
            this.setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
        }
        
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

    public JPiece getjPiece() {
        return jPiece;
    }

    public void setjPiece(JPiece jPiece) {
        this.jPiece = jPiece;
    }

}