import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gameover here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gameover  extends Actor
{
    /**
     * Act - do whatever the Gameover wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if ("enter".equals(Greenfoot.getKey()))
        {
            ((KataminoWorld)getWorld()).startTheGame();
            getWorld().removeObject(this);
        }
    }    
}
