import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.List;
/**
 * Write a description of class Slider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public abstract class Slider  extends Actor
{
    private int size;

    public int getSize() {
        return this.size;
    }
    
    public void setSize(int size) {
        this.size=size;
    }
    
        
    
    /**
     * Act - do whatever the Slider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
            
    }    
    
    
    public abstract boolean canMove();
    
    public List testList() {
        //MouseInfo mouse = Greenfoot.getMouseInfo();
        return getIntersectingObjects(Slider.class);}
    
    public Actor testX() {
        return getOneObjectAtOffset(getX(), 0 , Slider.class);
    }
    
    public Actor testY() {
        return getOneObjectAtOffset( 0 , getY(), Slider.class);
    }
}
