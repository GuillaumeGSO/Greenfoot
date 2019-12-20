import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A Roomba is a floor-cleaning robot.
 * 
 * @author Anonymous
 * @version 0.3
 */
public class Roomba extends Robot
{
    private boolean lastLeftBumper = false;
    private boolean lastRightBumper = false;
    private boolean lastFrontBumper = false;
    //Unlike Greeps, extra instance variables are permitted.
    
    /**
     * Do what a robot's gotta do.
     */
    public void act()
    {
        final int RANDOM_ANGLE = 15;
        super.act();   // do not delete! leave as first statement in act().
        
//              if (frontBumper()) {
//                  turn(Greenfoot.getRandomNumber(RANDOM_ANGLE));
//              if (frontBumper()) turn(Greenfoot.getRandomNumber(2*RANDOM_ANGLE));
//              if (frontBumper()) turn(Greenfoot.getRandomNumber(2*RANDOM_ANGLE));
//              if (frontBumper()) turn(Greenfoot.getRandomNumber(3*RANDOM_ANGLE));
//             if (frontBumper()) turn(Greenfoot.getRandomNumber(3*RANDOM_ANGLE));
//             if (frontBumper()) turn(180);
//             
//             if (frontBumper()) System.out.println("bloqué");
//             }
    System.out.println("--------");
if (frontBumper()) System.out.println("Front");
if (leftBumper()) System.out.println("Left");
if (rightBumper()) System.out.println("Right");

    if (frontBumper() && rightBumper() & leftBumper()) {
        turn(180);
    }
    else if (frontBumper() && !rightBumper() & !leftBumper()) {
        if (randomChance(50)) turn(170); else turn(-170);
    }
    else if (frontBumper() && leftBumper()) {
        if (leftBumper()==lastLeftBumper) turn(-RANDOM_ANGLE);
    } else if (frontBumper() && rightBumper()) {
        if (rightBumper()==lastRightBumper) turn(RANDOM_ANGLE);
    } 

   // if (randomChance(1)) turn(RANDOM_ANGLE);
                move();
         lastLeftBumper=leftBumper();
         lastRightBumper=rightBumper();
         lastFrontBumper=frontBumper();
         
        //check the documentation for the Robot and Actor class for additional public methods.
        //turn(int angle);
        //move();
        //boolean atWall();
        //boolean frontBumper();
        //boolean leftBumper();
        //boolean rightBumper();
        //boolean randomChance(int percent);
        
    }

    public int getAbsoluteRotation() {
        return getRotation()%360;
    }
    
    /**
     * This method specifies the name of the robot (for display on the result board).
     */
    public String getAuthorName()
    {
        return "Anonymous";  // write your name here!
    }


    /**
     * This method specifies the image we want displayed at any time. 
     * (No need to change this for the competition)
     */
    public String getCurrentImage()
    {
            return "roomba.png"; //the robot image should be 48 pixels high, "forward" is facing right.
    }
    

}