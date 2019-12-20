import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FigureI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FigureA  extends Figure
{
    public FigureA()
    {
        super('A',
        new int[][]
        {
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0}
        });
    }
    
    public boolean goSwap() {
        //nothing to do
    return true;
    }
}
