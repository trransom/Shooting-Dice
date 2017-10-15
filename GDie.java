package dicegame2;

import acm.graphics.GCompound;
import acm.graphics.GRect;
import acm.graphics.GOval;
import java.awt.Color;



/**
 * Creates the graphical representation of the dice that the DiceGame class calls
 * upon. 
 * @author trran
 */
public class GDie extends GCompound {
    
 private static final double DICE_SIZE = 70;  
 private Die die;
 

 public GDie(Die die, double diesize){
     
    //creates the white square of the die body
    this.die = die;
    GRect body = new GRect(diesize, diesize);
        body.setFilled(true);
        body.setFillColor(Color.white);
        add(body);
     
    //Creates the pips to go on the die by dividing the square into sevenths. 
    GOval pip1 = new GOval(DICE_SIZE / 7 * 1, DICE_SIZE / 7 * 1, 8, 8);
    pip1.setFilled(true);
    pip1.setColor(Color.BLACK);
    GOval pip2 = new GOval(DICE_SIZE / 7 * 5, DICE_SIZE / 7 * 1, 8, 8);
    pip2.setFilled(true);
    pip2.setColor(Color.BLACK);
    GOval pip3 = new GOval(DICE_SIZE / 7 * 1, DICE_SIZE / 7 * 3, 8, 8);
    pip3.setFilled(true);
    pip3.setColor(Color.BLACK);
    GOval pip4 = new GOval(DICE_SIZE / 7 * 3, DICE_SIZE / 7 * 3, 8, 8);
    pip4.setFilled(true);
    pip4.setColor(Color.BLACK);
    GOval pip5 = new GOval(DICE_SIZE / 7 * 5, DICE_SIZE / 7 * 3, 8, 8);
    pip5.setFilled(true);
    pip5.setColor(Color.BLACK);
    GOval pip6 = new GOval(DICE_SIZE / 7 * 1, DICE_SIZE / 7 * 5, 8, 8);
    pip6.setFilled(true);
    pip6.setColor(Color.BLACK);
    GOval pip7 = new GOval(DICE_SIZE / 7 * 5, DICE_SIZE / 7 * 5, 8, 8);
    pip7.setFilled(true);
    pip7.setColor(Color.BLACK);
    
    //takes the value of a number and returns the correct number of pips on the die.
    switch(die.getValue()) {
        case 1:
        default:
            add(pip4);
            break;
        
        case 2:
            add(pip1);
            add(pip7);
            break;
         
        case 3:
            add(pip1);
            add(pip4);
            add(pip7);
            break;
          
        case 4:
            add(pip1);
            add(pip2);
            add(pip6);
            add(pip7);
            break;
            
        case 5:
            add(pip1);
            add(pip2);
            add(pip4);
            add(pip6);
            add(pip7);
            break;
            
        case 6: 
            add(pip1);
            add(pip2);
            add(pip3);
            add(pip5);
            add(pip6);
            add(pip7);
            break;
    }
 }
    
    //gets the value for the die
    public Die getValue() {
        return die;
    }
}
