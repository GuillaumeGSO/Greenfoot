import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class TetrisWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KataminoWorld  extends World
{
    static final char[] colors = new char[]{'A','B','C','D','E','V', 'W', 'X', 'Y'};
    //, '2', '3', '4', '5', '6', '7'};

    GreenfootImage[] images;
    List<Piece> pieces;
    Random r;

    public GreenfootImage getImage(char color)
    {
        for (int i=0; i<colors.length; i++)
            if (colors[i]==color)
                return images[i];
        return null;
    }

    //     public int getFigureSpeed()
    //     {
    //         if (speed==9) return 4;
    //         return 39-4*speed;
    //     }

    public int getFigureMaxSpeed()
    {
        return 1;
    }

    /**
     * Constructor for objects of class TetrisWorld.
     * 
     */
    public KataminoWorld()
    {    
        super(10, 15, 30); 
        images = new GreenfootImage[colors.length];
        for (int i=0; i<colors.length; i++)
        {
            images[i] = new GreenfootImage(colors[i]+".png");
        }
        pieces = new ArrayList<Piece>();
        //         started = false;

        addObject(new StatusPanel(), 1, 1);

        prepare();
    }

    public void addPiece(int x, int y, char color)
    {
        Piece p = new Piece(color);
        addObject(p, x, y);
        pieces.add(p);
    }

    public void addExistentPiece(Piece p)
    {
        pieces.add(p);
    }

    public boolean isEmpty(int x, int y)
    {
        return false;
    }
    //     
    //     public boolean isEmpty(int x, int y)
    //     {
    //         if (x<0 || y<0 || x>=getWidth() || y>=getHeight())
    //             return false;
    //         for (Piece p: pieces)
    //             if (p.getX()==x && p.getY()==y)
    //                 return false;
    //         return true;
    //     }
    // //real-time    
    //     int speed;
    int score;
    Figure currentFigure;
    boolean started, finished;
    //     
    public void startTheGame()
    {
        for (Piece p: pieces)
            removeObject(p);
        pieces.clear();
        //speed = 0;
        score = 0;
        currentFigure = null;
        finished = false;
        r = new Random();
        //spawnNext();
    }
    //     
    //     public void spawnNext()
    //     {
    //         checkTheLines();
    //         
    //         if (currentFigure!=null)
    //             removeObject(currentFigure);
    //         int m = r.nextInt(7);
    //         switch (m)
    //         {
    //             case 0: currentFigure = new FigureV();
    //             break;
    //             case 1: currentFigure = new FigureW();
    //             break;
    //             case 2: currentFigure = new FigureX();
    //             break;
    //             case 3: currentFigure = new FigureY();
    //             break;
    //             case 4: currentFigure = new FigureA();
    //             break;
    //             case 5: currentFigure = new FigureB();
    //             break;
    //             case 6: currentFigure = new FigureC();
    //             break;
    //         }
    //         int x0 = (getWidth()-1)/2, y0 = 1;
    //         addObject(currentFigure, x0, y0);
    //         if (!currentFigure.check(x0, y0, 0))
    //         {
    //             currentFigure.freePieces();
    //             removeObject(currentFigure);
    //             currentFigure = null;
    //             finished = true;
    //             addObject(new Gameover(),getWidth()/2, getHeight()/2);
    //         }
    //     }
    //     
    //     public void checkTheLines()
    //     {
    //         int[] c = new int[getHeight()];
    //         for (Piece p: pieces)
    //             c[p.getY()]++;
    //         int[] cnt = new int[getHeight()+1];
    //         cnt[getHeight()] = 0;
    //         for (int i = getHeight()-1; i>=0; i--)
    //             cnt[i] = cnt[i+1] + (c[i]==getWidth()?1:0);
    //         if (cnt[0]==0) return;
    //         List<Piece> removed = new ArrayList<Piece>();
    //         for (Piece p: pieces)
    //         {
    //             if (c[p.getY()]==getWidth())
    //             {
    //                 removeObject(p);              
    //                 removed.add(p);
    //             }
    //         }
    //         for (Piece p: removed)
    //             pieces.remove(p);
    //         for (Piece p: pieces)
    //             p.setLocation(p.getX(), p.getY()+cnt[p.getY()]);
    //         switch (cnt[0])
    //         {
    //             case 1: score+=100;
    //             break;
    //             case 2: score+=300;
    //             break;
    //             case 3: score+=600;
    //             break;
    //             case 4: score+=1200;
    //             break;
    //         }
    //         speed = Math.min(9, score/1000);
    //     }

    public boolean isFinished()
    {
        return finished;
    }

    public void act()
    {
        if (!started&&!finished)
        {
            started = true;
            startTheGame();
        }
    }
    //     public int getSpeed()
    //     {
    //         return speed;
    //     }
    public int getScore()
    {
        return score;
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        FigureX figurex = new FigureX();
        addObject(figurex, 2, 3);
        FigureV figurev = new FigureV();
        addObject(figurev, 4, 3);
        FigureB figureb = new FigureB();
        addObject(figureb, 6, 3);
        FigureY figurey = new FigureY();
        addObject(figurey, 2, 7);
        FigureD figured = new FigureD();
        addObject(figured, 5, 7);
    }
}
