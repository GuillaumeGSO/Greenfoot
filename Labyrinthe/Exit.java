import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Exit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Exit  extends Actor
{
    public Exit() {
        super();
    }    

    public void addedToWorld(World w) {
        int res = w.getCellSize();
        GreenfootImage img = new GreenfootImage (res,res);
        img.setColor(Color.BLUE);
        img.fillOval((int)res/6,(int)res/6,(int)4*res/6,(int)4*res/6);
        setImage(img);
    }
    
       
}
