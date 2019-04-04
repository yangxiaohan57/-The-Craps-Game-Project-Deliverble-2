package crapsgame;

import java.util.Random;

/**
 * Craps Game - Second Deliverable
 * This is one of the Models for MVC framework.
 * This class is the game engine.
 * @author Xiaohan Yang 03/26/19
 */

public class ModelGameEngine {
    private static final Random randomNumbers = new Random();
    private int die1, die2, sum, myPoint;
    private int status; // 0 for win, 1 for lose, 2 for continue
    
    /**
     * Start the game
     * 
     */
    public void start() {
        status = 2; // continue
        //If it is the first round of the game, roll a dice and assign point
        //If the game continues form the previous round, roll a dice and check
        
        if (myPoint == 0) {
            sum = rollDice(); 
            switch ( sum ) {
                case 7:
                case 11:
                    status = 0; //Wins
                    break;
                case 2:
                case 3:
                case 12:
                    status = 1; // loses
                    break;
                default:
                    status = 2; // continue
                    myPoint = sum;
                    break;
            }
        } else {
            if( status == 2 ) {
                sum = rollDice();
            
                if ( sum == myPoint ){
                    status = 0;
                } else if ( sum == 7 ) {
                    status = 1;  
                }
            }
        }
        
        if (status == 0) {
            resetPoint();
        } else if (status == 1) {
            resetPoint();
        }
    }
    
    /**
     * Roll two dice and return the sum
     * 
     * @return the sum of two random number from 1 - 6
     */
    public int rollDice(){
        die1 = 1 + randomNumbers.nextInt( 6 );
        die2 = 1 + randomNumbers.nextInt( 6 );
        sum = die1 + die2; 
        
        return sum;
   } 
    
    /**
     * Get value of dice 1
     * 
     * @return value of dice 1
     */
    public int getDie1() {
        return die1;
    }
    
    /**
     * Get value of dice 2
     * 
     * @return value of dice 2
     */
    public int getDie2() {
        return die2;
    }
    
    /**
     * Get the sum of two dice
     * 
     * @return the sum
     */
    public int getSum() {
        return sum;
    }
    
    /**
     * Get point
     * 
     * @return current point
     */
    public int getPoint() {
        return myPoint;
    }
    
    /**
     * Get game status, which is represented by 1, 2, or 0
     * 
     * @return value of dice 1
     */
    public int getResult() {
        return status;
    }
    
    /**
     * Reset the point to 0
     * 
     */
    private void resetPoint() {
        myPoint = 0;
    }
}
