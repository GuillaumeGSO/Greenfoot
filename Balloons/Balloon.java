import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * A balloon flows from bottom to top.
 * 
 * @author Poul Henriksen
 */
public class Balloon extends Actor
{
     
    public Balloon (){
        super();
        }
    
    
    public Balloon (GreenfootImage image) {
        super();
        setImage(image);
    }
    
      
    public void act() 
    {
        setLocation(getX(), getY() -1);
        if (getY() == 0) {
            getWorld().removeObject(this);
            //((BalloonWorld) getWorld()).gameOver();
        }
    }    
    
    /**
     * Pop this balloon.
     */
    public void pop() 
    {
        Greenfoot.playSound("pop.wav");
        ((BalloonWorld) getWorld()).countPop();
        getWorld().removeObject(this);
    }
}
