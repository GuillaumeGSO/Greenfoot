import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class StarTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player  extends Actor
{
    

    public Player() {
        super();
         }
         
    public void addedToWorld(World w) {
       
        int res = w.getCellSize();
        GreenfootImage img = new GreenfootImage (res,res);
        img.setColor(Color.GREEN);
        img.fillRect((int)res/6,(int)res/6,(int)4*res/6,(int)4*res/6);
        setImage(img);
    }
    
    public void act() 
    {
        int x=getX();
        int y=getY();
        String key = Greenfoot.getKey();
        if ("up".equals(key) && canMove(key)) setLocation(x, y-1); 
        if ("down".equals(key) && canMove(key)) setLocation(x, y+1); 
        if ("right".equals(key)&& canMove(key)) setLocation(x+1, y);
        if ("left".equals(key)&& canMove(key)) setLocation(x-1, y);
         
        if (checkIfExit()) {
            //Greenfoot.stop();
            //getWorld().paintAll();
        }
            
        }
    
    
    public boolean canMove(String key) {
                
        Tile tile = (Tile)getOneObjectAtOffset(0,0,Tile.class);
        if ("up".equals(key)) return tile.getNorth();
        if ("down".equals(key)) return tile.getSouth();
        if ("left".equals(key)) return tile.getWest();
        if ("right".equals(key)) return tile.getEast();
        
        return false;
    }
    
    public boolean checkIfExit() {
        Exit exit = (Exit)getOneObjectAtOffset(0,0,Exit.class);
        if (exit!=null) 
        {
            getWorld().removeObject(exit);
            return true;
        }
        return false;

    }
}
