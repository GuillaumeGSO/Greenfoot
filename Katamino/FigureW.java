import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FigureI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FigureW  extends Figure
{
    public FigureW()
    {
        super('W',
        new int[][]
        {
            {0, 1},
            {0, 1}
        }
        );
    }
    
    public boolean goSwap() {
        //nothing to do
    return true;
    }
}
