package dicegame2;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JButton;
import svu.csc213.Dialog;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 * This program creates a simplified version of the dice game 'Craps'. By
 * clicking play, the user can "roll" the dice until he or she loses or wins. At
 * the end of a turn, the "bank" is added to or subtracted from depending on the
 * course of the game.
 *
 * @author trran
 */
public class DiceGame2 extends GraphicsProgram {

    private int wager = 50;
    private int bank = 1500;
    private int point;
    private GDie die;
    private GDie DIE;
    private GLabel statusLabel;
    private GLabel wagerLabel;
    private GLabel bankLabel;

    //main play method. This is where the game action takes place with the logic 
    //that controls the dice roll. 
    public void Play() {
        
       /* List<MediaPlayer> mList = new ArrayList<MediaPlayer>();
        
        
        try {
            JFXPanel j = new JFXPanel();
            String uri = new File("04_Kensington_Chump (1).wav").toURI().toString();
            
            new MediaPlayer(new Media(uri)).play();
            MediaPlayer player = new MediaPlayer(new Media(uri));
            mList.add(player);
            boolean play = true;
            while(play){
            player.play();
            }
            
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }*/
        
        File kens = new File("04_Kensington_Chump(1).wav");
        playSound(kens);

        //Creates two new die and adds them to the screen.
        GDie die1 = new GDie(new Die(), 70);
        add(die1, RandomGenerator.getInstance().nextInt(1, getWidth() - 70),
                RandomGenerator.getInstance().nextInt(1, getHeight() - 70));
        GDie die2 = new GDie(new Die(), 70);
        add(die2, RandomGenerator.getInstance().nextInt(1, getWidth() - 70),
                RandomGenerator.getInstance().nextInt(1, getHeight() - 70));

        int total = die1.getValue().getValue() + die2.getValue().getValue();

        //switch statement that takes the number on the dice and shows a message
        //telling the player whether he or she won. 
        switch (total) {
            case 7:
            case 11:
                pause(50);
                Dialog.showMessage(this, "That's a natural. You win.");
                bank += wager;
                updateBankLabel();
                remove(die1);
                remove(die2);
                break;
            case 2:
            case 3:
            case 12:
                pause(50);
                Dialog.showMessage(this, "That's craps. You lose.");
                bank -= wager;
                updateBankLabel();
                remove(die1);
                remove(die2);
                break;
            default:
                pause(50);
                //gets the dice to continue to roll until the player rerolls
                //their number or rolls a seven.
                boolean gotPoint = true;
                point = total;
                Dialog.showMessage(this, "Your point is " + point + ". Roll again.");
                remove(die1);
                remove(die2);
                pause(500);

                //while statement that continually rolls the dice.
                while (gotPoint) {
                    die = new GDie(new Die(), 70);
                    DIE = new GDie(new Die(), 70);

                    add(die, RandomGenerator.getInstance().nextInt(1, getWidth()
                            - 50), RandomGenerator.getInstance().nextInt(1, getHeight() - 70));
                    add(DIE, RandomGenerator.getInstance().nextInt(1, getWidth()
                            - 50), RandomGenerator.getInstance().nextInt(1, getHeight() - 70));

                    int newtotal = die.getValue().getValue() + DIE.getValue().getValue();
                    if (newtotal != 7 && newtotal != point) {
                        Dialog.showMessage(this, "Roll again!");

                        remove(die);
                        remove(DIE);
                    }
                    if (newtotal == point) {
                        gotPoint = false;
                    }
                    if (newtotal == 7) {
                        gotPoint = false;
                    }

                }
                int newtotal = die.getValue().getValue() + DIE.getValue().getValue();

                if (newtotal == point) {
                    Dialog.showMessage(this, "You made your point. You win");
                    bank += wager;
                    updateBankLabel();
                    remove(die);
                    remove(DIE);
                    break;
                } else if (newtotal == 7) {
                    Dialog.showMessage(this, "That's a 7. You lose.");
                    bank -= wager;
                    updateBankLabel();
                    remove(die);
                    remove(DIE);
                    break;
                }
                break;

        }
        if (bank < 0) {
            updateStatusLabel("Ouch, tough luck.");
        }

    }

    @Override
    public void init() {
        
        

        setBackground(Color.green);

        //Buttons
        JButton playbutton = new JButton("Play");
        add(playbutton, SOUTH);
        JButton wagerbutton = new JButton("Wager");
        add(wagerbutton, SOUTH);
        JButton exitButton = new JButton("Retire");
        add(exitButton, SOUTH);
        addActionListeners();

        //Labels
        Font d = new Font("SansSerif", Font.BOLD, 64);
        statusLabel = new GLabel("Welcome!");
        statusLabel.setFont(d);
        add(statusLabel, (getWidth() - statusLabel.getWidth()) / 2.0, 300);
        pause(1000);
        wagerLabel = new GLabel("Current wager is $" + wager);
        wagerLabel.setFont("times-bold-24");
        bankLabel = new GLabel("Current bank is $" + bank);
        bankLabel.setFont("times-bold-24");
        for (int i = 0; i <= 90; i++) {
            add(wagerLabel, -5 + (i * 3), 350);
            add(bankLabel, 530 - (i * 3), 400);
            pause(10);
        }

        addActionListeners();
        File kens = new File("04_Kensington_Chump(1).wav");
        playSound(kens);
    }

    //action listeners that handle when a button is pressed. 
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Play":
                remove(statusLabel);
                Play();
                break;

            case "Wager":
                wager = Dialog.getInteger(this, "How much do you want to wager?");
                updateWagerLabel();
                break;

            case "Retire":
                Dialog.showMessage(this, "Thanks for playing!");
                System.exit(0);
                break;
        }
    }

    private void updateStatusLabel(String message) {
        statusLabel.setLabel(message);
        statusLabel.setLocation(
                (getWidth() - statusLabel.getWidth()) / 2.0,
                statusLabel.getY());
         add(statusLabel);
    }

    private void updateBankLabel() {
        bankLabel.setLabel("Current bank is $" + bank);
        bankLabel.setLocation(
                (getWidth() - bankLabel.getWidth()) / 2.0,
                bankLabel.getY());
    }

    private void updateWagerLabel() {
        wagerLabel.setLabel("Current wager is $" + wager);
        wagerLabel.setLocation(
                (getWidth() - wagerLabel.getWidth()) / 2.0,
                wagerLabel.getY());
    }
    
    
    static void playSound(File sound) {
        
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
            
            Thread.sleep(clip.getMicrosecondLength()/1000);
        } catch (Exception e){
    }
    }
       // File kens = new File("");
   /* public void playSound() {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("04_Kensington_Chump(1).wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch(Exception ex) {
        System.out.printf("Error with playing sound.");
    }
}*/

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new DiceGame2().start();
    }

}
