import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class De here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class De  extends Actor
{
    public Plateau plateau;
    private final GreenfootImage IMG_DICE = new GreenfootImage("dice.png");
    private final GreenfootImage IMG_PRUNE = new GreenfootImage("plum.png");
    private final GreenfootImage IMG_POMME = new GreenfootImage("apple2.png");
    private final GreenfootImage IMG_POIRE = new GreenfootImage("pear.png");
    private final GreenfootImage IMG_CERISE = new GreenfootImage("cherries.png");
    private final GreenfootImage IMG_CORBEAU = new GreenfootImage("Corbeau.png");
    private final GreenfootImage IMG_PANIER = new GreenfootImage("Panier.png");
    /**
     * Act - do whatever the De wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()  
    {    
        if (    Greenfoot.mouseClicked(this) 
            && plateau.getFruitToPickUp() <= 0) {
                
                    plateau.setFruitToPickUp(lanceDe());
                    //cas du corbeau
                    if (plateau.getFruitToPickUp() < 0) {
                        plateau.addCorbeau();
                    }
        }
        
        if (plateau.getObjects(Fruit.class).isEmpty()){
            Greenfoot.stop();
        }
    }
    
    /**
     * retourne le nombre de fruit que l'on peut cueillir avec ce lancé
     */
    public int lanceDe() {
     
        switch (Greenfoot.getRandomNumber(6)) {
            case 0 :
                this.setImage(IMG_CORBEAU);
                return -1;
            case 1 :
                this.setImage(IMG_PANIER);
                disabled();
                return 2;
            case 2 :
                this.setImage(IMG_CERISE);
                if (plateau.getObjects(Cerise.class).isEmpty()) {
                    this.getImage().setTransparency(255);
                    return 0;
                }
                break;
            case 3 :
                this.setImage(IMG_PRUNE);
                if (plateau.getObjects(Prune.class).isEmpty()) {
                    this.getImage().setTransparency(255);
                    return 0;
                }
                break;
            case 4 :
                this.setImage(IMG_POMME);
                if (plateau.getObjects(Pomme.class).isEmpty()) {
                    this.getImage().setTransparency(255);
                    return 0;
                }
                break;
            case 5 :
                this.setImage(IMG_POIRE);
                if (plateau.getObjects(Poire.class).isEmpty()) {
                    this.getImage().setTransparency(255);
                    return 0;
                }
                break;
        }
        disabled();
        return 1;
    }
    public void addedToWorld(World world) {
        plateau = (Plateau) world;
        plateau.leDe = this;
    }
    
    public void enabled() {
        if (IMG_CORBEAU != this.getImage()) {
            this.setImage(IMG_DICE);
        }
        this.getImage().setTransparency(255);
    }
    
    public void disabled() {
        this.getImage().setTransparency(150);
    }
    
        
}
