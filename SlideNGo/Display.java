import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Display here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class Display  extends Actor
{

    public Display(String text)  {
        
    GreenfootImage img = new GreenfootImage(100, 30);
    img.drawString(text, 2, 20);
    this.setImage(img);
    }
    
    public void setText(String text) {
        GreenfootImage img = this.getImage();
        img.clear();
        img.drawString(text, 2, 20);
    }
}
