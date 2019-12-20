import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Horizontal Slider
 * 
 * @author GSO 
 * @version (dec 2010)
 */

public class HSlider  extends Slider
{
    SliderWorld world;
    HSlider(int size) {
        this.setSize(size);
    }

    public void addedToWorld(World w)
    {
        world = (SliderWorld) w;
        int wSize = w.getCellSize();
        
        GreenfootImage img = new GreenfootImage(3*wSize, wSize);
        img.setColor(Color.RED);
        img.setTransparency(100);
        //img.drawOval(0, 0, 40, 40);  
        img.fillRect(getX(), getY(), getSize()*wSize, wSize);
        setImage(img);
        
            
        this.world.getDisplay().setText(getX()*wSize + "," + getY() + "," + 2*wSize + "," +wSize);

        
    }

    /**
     * Act - do whatever the HSlider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        int y = getY();
        int x = 0;
        if(Greenfoot.mouseMoved(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            x=mouse.getX();
            if (canMove()) setLocation(x, y);
        }
        this.world.getDisplay().setText(getX() + "," + getY() );
    }    
    public boolean canMove() {
        return true;
    }
}
