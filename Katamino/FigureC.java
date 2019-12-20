import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FigureT here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FigureC  extends Figure
{
    public FigureC()
    {
        super('C',
        new int[][]
        {
            {0, 1, 0},
            {0, 1, 1},
            {0, 1, 0}
        });
    }  
    
    
    public boolean goSwap() {
    //nothing to do
    return true;
    }
}
