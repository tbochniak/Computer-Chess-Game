/*
 * 
 */
package piece;

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
    
    
    
}
