/*
 * 
 */
package piece;

import java.util.ArrayList;

/**
 *
 * @author tiago
 */
public class Computer {
    
    private final EnumPlayer computerPlayer;

    public Computer(EnumPlayer computerPlayer) {
        this.computerPlayer = computerPlayer;
    }

    public EnumPlayer getPlayer() {
        return computerPlayer;
    }
    

    public ArrayList<ArrayList<Integer>> possibleAttacks(Board board) {
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        
        
        return moves;
    }
 
}
