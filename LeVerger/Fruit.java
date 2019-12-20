import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fruit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Fruit  extends Actor
{

    public Plateau plateau;
    /**
     * Act - do whatever the Fruit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        if(    Greenfoot.mouseClicked(this) 
            && this.isPickable() 
            && plateau.getFruitToPickUp() > 0){
            plateau.pickUp(this);
        
            if (plateau.getFruitToPickUp() > 0) {
                plateau.leDe.disabled();
            } else {
                plateau.leDe.enabled();
            }
        plateau.checkGain();
        }
        
        if (plateau.getLanceAnim()) {
            //cible 291,202
            int x = getX();
            int y = getY();
            int deltaX = 0;
            int deltaY = 0;
            if (x==290 && y==200) {
            plateau.removeObject(this);
            } else {
                if (x==290) {deltaX=0;} else if (x>290) {deltaX=-1;} else {deltaX=1;}
                if (y==200) {deltaY=0;} else if (y>200) {deltaY=-1;} else {deltaY=1;}
                this.setLocation(x + deltaX, y + deltaY);
            }
        }
        
    }
    
    public void addedToWorld(World world) {
        plateau = (Plateau) world; 
    }
    
    public boolean isPickable() {
        String de = getImageName(plateau.leDe.getImage());
        String fruit = getImageName(this.getImage());
        if (de.equals(fruit)) {
            //il s'agit du fruit du dé
            return true;
        } else if ("Panier".equals(de)) {
            //il s'agit du panier
            return true;
        } else {
            //il s'agit du corbeau
            return false;
        }
    }
    
    private String getImageName(GreenfootImage gfi) {
        String str = gfi.toString();
        int idDeb = str.indexOf(":")+2;
        int idPNG = str.indexOf(".png");
        return str.substring(idDeb, idPNG);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
