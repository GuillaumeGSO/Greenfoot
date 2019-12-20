import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StatusPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StatusPanel  extends Actor
{
    /**
     * Act - do whatever the StatusPanel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static final Color textColor = new Color(0, 0, 0);
    
    public StatusPanel()
    {
    }
    
    protected void addedToWorld(World w){
        super.addedToWorld(w);
        act();
    }
    
    public void act() 
    {
        KataminoWorld world = (KataminoWorld)getWorld();
        // Add your action code here.
        setImage(new GreenfootImage(100, 40));
        GreenfootImage image = getImage();
        image.setColor(textColor);
        image.drawString("Score = "+world.getScore(), 5, 10);
        //image.drawString("Level = "+world.getSpeed(), 5, 30);
    }    
}
