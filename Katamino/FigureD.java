import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FigureS here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FigureD  extends Figure
{
    public FigureD()
    {
        super('D',
        new int[][]
        {
            {0, 1, 0},
            {0, 1, 1},
            {0, 0, 1}
        });
    }
        public boolean goSwap() {
        this.setMatrix(new int[][] {
            {0, 1, 0},
            {1, 1, 0},
            {1, 0, 0}
        });
        return true;
        }
    }
