import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FigureO here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FigureE  extends Figure
{
    public FigureE()
    {
        super('E',
        new int[][]
        {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 1}
        });
    } 
    public boolean goSwap() {
    //nothing to do
    return true;
    }
}
