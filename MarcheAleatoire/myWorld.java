import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class myWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class myWorld  extends World
{
    public static final int LARGEUR = 700;
    public static final int HAUTEUR = 400;
    public static final int RESOLUTION = 1;
    
    public static final int NB_ACTEUR=10;
    /**
     * Constructor for objects of class myWorld.
     * 
     */
    public myWorld()
    {    
        // Create a new world with 20x20 cells with a cell size of 10x10 pixels.
        super(LARGEUR/RESOLUTION, HAUTEUR/RESOLUTION, RESOLUTION);
        for (int i=0;i<NB_ACTEUR;i++) {
            int x =Greenfoot.getRandomNumber(getWidth()-10);
            int y =Greenfoot.getRandomNumber(getHeight()-10);
            addObject(new Acteur1(Greenfoot.getRandomNumber(360)),x,y);
        }
        
        
    }
}
