package dicegame2;

import acm.util.RandomGenerator;

/**
 *
 * @author trran
 */
public class Die {
    
    private int value;
    
    //Randomly generates a value between 1 and six and assigns it to value. 
    public Die() {
        this.value = RandomGenerator.getInstance().nextInt(1,6);
    }
    
    //Returns value
    public int getValue() {
        return value;
    }
    
    //Getter that sets value to a random number between 1 and 6, assigns that
    //number to value, and then returns value. 
    public int rollDice() {
        this.value = RandomGenerator.getInstance().nextInt(1,6);
        return value;
    }
    
}
