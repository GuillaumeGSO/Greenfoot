import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor
{
    private int numExit;
    private boolean exitNorth;
    private boolean exitSouth;
    private boolean exitWest;
    private boolean exitEast;
    private int resolution;
    private boolean isInitialized;
    private GreenfootImage tileImg;
    
    public Tile(boolean n, boolean e, boolean s, boolean w) {
            exitNorth=n;
            exitEast=e;
            exitSouth=s;
            exitWest=w;
            numExit=countExit();
        
        isInitialized=false;
    }
    
    public void addedToWorld(World w){
        int resolution = w.getCellSize();
        
        this.tileImg = new GreenfootImage(
                resolution,
                resolution
                );
        tileImg.setColor(Color.RED);
        
            if (!exitNorth) tileImg.fillRect ( 0, 0, (int) resolution, (int) resolution/6);
            if (!exitEast) tileImg.fillRect ( 5*(int)resolution/6, 0, (int) resolution/6, (int) resolution);
            if (!exitSouth) tileImg.fillRect ( 0, (int)5*resolution/6, (int) resolution,(int) resolution/6);
            if (!exitWest) tileImg.fillRect ( 0, 0, (int) resolution/6, (int) resolution);
        this.setImage(tileImg);
   
    }
        
    public int countExit() {
        int nb = 0;
        if (this.exitNorth) nb++;
        if (this.exitSouth) nb++;
        if (this.exitWest) nb++;
        if (this.exitEast) nb++;
    return nb;
    }
    
    public void act() {
        Player visitor = (Player)getOneObjectAtOffset(0,0,Player.class);
        GreenfootImage visitorImg;
        if (visitor!=null)
        {
            int res = getWorld().getCellSize();
            visitorImg = new GreenfootImage (res,res);
            visitorImg.setColor(greenfoot.Color.YELLOW);
            visitorImg.fillRect((int)res/6,(int)res/6,(int)4*res/6,(int)4*res/6);
            visitorImg.setTransparency(100);
            tileImg.drawImage(visitorImg,0,0);
        }
        
    }
    
    public void setInitialized() {
        this.isInitialized=true;
    }
    
    public boolean getInitialized() {
        return isInitialized;
    }
    
    public void setTileImg(GreenfootImage img) {
        this.tileImg=img;
    }
    
    public GreenfootImage getTileimg() {
        return tileImg;
    }
    
    public void setNorth(boolean dir) {
        this.exitNorth=dir;
    }
    public void setEast(boolean dir) {
        this.exitEast=dir;
    }
    public void setSouth(boolean dir) {
        this.exitSouth=dir;
    }
    public void setWest(boolean dir) {
        this.exitWest=dir;
    }
    public boolean getNorth() {
        return this.exitNorth;
    }
    
    public boolean getWest() {
        return this.exitWest;
    }
    public boolean getSouth() {
        return this.exitSouth;
    }
    public boolean getEast() {
        return this.exitEast;
    }
    
    
    public List<Tile> getVoisins() {
        List<Tile> listVoisins = this.getNeighbours(1, false, Tile.class);
        return listVoisins;
    
    }
}