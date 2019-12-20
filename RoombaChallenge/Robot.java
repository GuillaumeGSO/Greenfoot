import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.util.List;


/**
 * A Robot is the base class for the floor-cleaners in this scenario. It
 * provides the basic abilities of robots in this world.
 * 
 * @author David Adamson  (adapted from Michael Kolling's Creature)
 * @version 1.0
 */
public abstract class Robot extends Actor
{
    private static final double WALKING_SPEED = 10.0;
    private static final int SHINE = 8;
    private static final int RADIUS = 24;

    private boolean moved = false;
    private boolean atWall = false;
    private int r;
    
    /**
     * Create a robot with the given cleaning radius.
     */
    public Robot()
    {
        r = RADIUS;
        setImage(getCurrentImage());
    }
   
    
    
    /**
     * Act - must be called as part of subclass act. This ensures single
     * movement in each act round.
     */
    public void act()
    {
        moved = false;
    }
    
    
    /**
     * Turn 'angle' degrees towards the right (clockwise).
     */
    public void turn(int angle)
    {
        setRotation(getRotation() + angle);
    }

    /**
     * lighten the floor beneath the robot.
     */
    private void clean()
    {
        ((House)getWorld()).clean(getX(), getY(), r, SHINE);
    }
    
    /**
     * Move forward in the current direction.
     */
    public void move()
    {
        if(moved)   // can move only once per 'act' round
            return;

        double angle = Math.toRadians( getRotation() );
        int x = (int) Math.round(getX() + Math.cos(angle) * WALKING_SPEED);
        int y = (int) Math.round(getY() + Math.sin(angle) * WALKING_SPEED);
        
        // now make sure that we are not stepping out of the world
        if(x >= getWorld().getWidth()) {
            x = getWorld().getWidth() - 1;
        }
        if(x < 0) {
            x = 0;
        }
        if(y >= getWorld().getHeight()) {
            y = getWorld().getHeight() - 1;
        }
        if(y < 0) {
            y = 0;
        }
        
        if(((House)getWorld()).isWall(x, y)) {
            atWall = true;
        }
        else {
            atWall = false;
            setLocation(x, y);
        }
            
        clean();
        moved = true;
    }
    
    private boolean isWallAt(int x, int y)
    {
        GreenfootImage floor = getWorld().getBackground();

        if(x >=0 && y>= 0 && x < floor.getWidth() && y < floor.getHeight())
        {
            Color speck = floor.getColorAt(x, y);
            int brightness = speck.getRed() + speck.getGreen() + speck.getBlue();
            return (brightness <= House.WALL_LEVEL);
        }
        else return true;
    }
    
   /**
    * return true if the robot's left side is touching a wall or is off the edge of the world.
    */
    public boolean leftBumper()
    {
        int x = getX();
        int y = getY();
        double heading = getRotation()*Math.PI/180.0;
        
        x += (int)(r*Math.cos(heading+Math.PI/2));
        y += (int)(r*Math.sin(heading+Math.PI/2));
        
        return isWallAt(x, y);
    }
    
    /**
    * return true if the robot's right side is touching a wall or is off the edge of the world.
    */
    public boolean rightBumper()
    {
        int x = getX();
        int y = getY();
        double heading = getRotation()*Math.PI/180.0;
        
        x += (int)(r*Math.cos(heading-Math.PI/2));
        y += (int)(r*Math.sin(heading-Math.PI/2));
        
        return isWallAt(x, y);
    
    }
   
   /**
    * return true if the robot's front quadrant is touching a wall or is off the edge of the world.
    */ 
    public boolean frontBumper()
    {
        if(atWorldEdge())
            return true;
        else
        {
            GreenfootImage floor = getWorld().getBackground();
            int myX = getX();
            int myY = getY();
            
            double heading = getRotation()*Math.PI/180.0;
            
            for(int i = 0; i < 8; i++)
            {
                double theta = heading - Math.PI/4 + i*Math.PI/16;
                int x = (int)(r*Math.cos(theta));
                int y = (int)(r*Math.sin(theta));
                
                if(isWallAt(myX+x, myY+y))
                    return true;
            }
            return false;
        }
    }

    
    /**
     * Return true if our sensors have detected an obstruction.
     */
    public boolean atWall()
    {
        return(atWorldEdge() || leftBumper() || rightBumper() || frontBumper());
    }

    
    /**
     * This method must be defined in subclasses. It gives subclasses the chance
     * to specify their own images.
     */
    abstract public String getCurrentImage();

    
    /**
     * Return true if we are at the edge of the world.
     */
    private boolean atWorldEdge()
    {
        if(getX() < 3 || getX() > getWorld().getWidth() - 3)
            return true;
        if(getY() < 3 || getY() > getWorld().getHeight() - 3)
            return true;
        else
            return false;
    }

    
    /**
     * Return 'true' in exactly 'percent' number of calls. That is: a call
     * randomChance(25) has a 25% chance to return true.
     */
    protected boolean randomChance(int percent)
    {
        return Greenfoot.getRandomNumber(100) < percent;
    }
    
    /**
     * Override in subclasses to attach your name to your robot creation!
     */
    public abstract String getAuthorName();
}
