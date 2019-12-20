import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A timer is a conter that automatically counts up as time goes on.
 * This timer also checks whether the map has been completed (either time
 * is up, or all tomatoes are gone).
 * 
 * @author M Kolling
 * @version 1.0
 */
public class GameTimer extends Counter
{
    private static final int GAME_TIME = 100;
    
    private int tick;
    
    public GameTimer()
    {
        super("Time: ");
        setValue(GAME_TIME);
        tick = 0;
    }
    
    /**
     * Count time. Notify of 'Game Over' when game time is reached.
     */
    public void act() 
    {
        tick++;
        if(tick == 33) 
        {
            decrement();
            checkFinish();
            tick = 0;
        }
    }
    
    /**
     * Check whether this map is finished. This is the case if time is up, or 
     * all tomatoes are gone.
     */
    private void checkFinish()
    {
        if(timeOut()) 
        {
            gameOver();
        }
        else if(((House)getWorld()).getScore() > House.ALL_DONE_THRESHOLD)
        {
            gameOver();
        }
        
    }
    
    /**
     * Return true if game time is up.
     */
    private boolean timeOut()
    {
        return getValue() <= 0;
    }
    
    /**
     * Notify that the level is over.
     */
    private void gameOver()
    {
        ((House)getWorld()).mapFinished(getValue());
    }
}
