import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SlideWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SliderWorld  extends World
{

    Display disp = new Display ("coucou");

    /**
     * Constructor for objects of class SlideWorld.
     * 
     */
    public SliderWorld()
    {    
        // Create a new world with 20x20 cells with a cell size of 10x10 pixels.
        super(7, 5, 60);
        addObject(new HSlider(2), 1, 1);
        addObject(new HSlider(3), 3, 5);
        GreenfootImage img = new GreenfootImage(40, 40);
    Font font = img.getFont();
    font = font.deriveFont(48);
    img.setFont(font);
    img.drawString("xxxx", 1, 1);
    addObject(disp, 20,20);
        }
    
    public Display getDisplay() {
    return disp;
    }
    
}
