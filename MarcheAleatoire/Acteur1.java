import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Acteur1 here.
 * 
 * @authr (your name) 
 * @version (a version number or a date)
 */
public class Acteur1  extends Mover
{
    private final static int AMPLITUDE = 45;
    private final static int SEUIL_DETECTION =30;
    
    public Acteur1(int rot) {
        this.setRotation(rot);
    }
    
    public Acteur1()
    {
        super();
    }
  
    
    /**
     * Act - do whatever the Acteur1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (canMove()) {
            move();
        
            if (atWorldEdge()) {
                turn(AMPLITUDE/2);
            }
            else
            {
                changeDirection();
            }
        }
        else
        {
            changeDirection();
        }
        
    }
    
    public void changeDirection() {
        if (randomChance(20)) {
            int calculAngle = Greenfoot.getRandomNumber(AMPLITUDE) *
                             ((Greenfoot.getRandomNumber(2)==1 ? 1 : -1));
            turn(calculAngle);
        }
    }
        
 
    public int regarde_x() {
        double angle = Math.toRadians( getRotation() );
        return (int) Math.round(Math.cos(angle) * SEUIL_DETECTION);
    }

        public int regarde_y() {
        double angle = Math.toRadians( getRotation() );
        return (int) Math.round(Math.sin(angle) * SEUIL_DETECTION);
    }

    public int x() { return getX();}
    
    public int y() { return getY();}
    
    public boolean canMove() {
    
        double angle = Math.toRadians( getRotation() );
        int x = (int) Math.round(Math.cos(angle) * SEUIL_DETECTION);
        int y = (int) Math.round(Math.sin(angle) * SEUIL_DETECTION);
        
        List<Acteur1> Acteur1InRange = getObjectsAtOffset(x,y,Acteur1.class);
        if (Acteur1InRange.isEmpty())
        {
            return true;
        }
        else 
        {
            return false;
        }

    
    }
    
    
    private boolean randomChance(int percent)
    {
        return Greenfoot.getRandomNumber(100) < percent;
    }
    
   
}
