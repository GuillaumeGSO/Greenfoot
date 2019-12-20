import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/** 
 *
 * A world of balloons.
 * 
 * Balloons will be created and flow from the bottom to the top of the screen.
 * 
 * @author Poul Henriksen
 */
public class BalloonWorld extends World 
{

    Counter counter = new Counter("Score: ");

    public static final int L = 600;
    public static final int H = 400;

    private List<GreenfootImage> listeImage = new ArrayList();
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */   
    public BalloonWorld()
    {    
                
        super(L, H, 1);
        
        // Make sure actors are painted in the correct order.
        setPaintOrder(ScoreBoard.class, Explosion.class, Bomb.class, Dart.class, Balloon.class, Counter.class);
       
        listeImage.add(new GreenfootImage("apple1.png"));
        listeImage.add(new GreenfootImage("bananas.png"));
        listeImage.add(new GreenfootImage("apple2.png"));
        listeImage.add(new GreenfootImage("cherries.png"));
        listeImage.add(new GreenfootImage("grapes.png"));
        listeImage.add(new GreenfootImage("lemon.png"));
        listeImage.add(new GreenfootImage("orange.png"));
        listeImage.add(new GreenfootImage("pear.png"));
        listeImage.add(new GreenfootImage("strawberry.png"));
        listeImage.add(new GreenfootImage("strawberry2.png"));
        listeImage.add(new GreenfootImage("pumpkin.png"));
        listeImage.add(new GreenfootImage("plum.png"));
        
        listeImage.add(new GreenfootImage("adrien1.png"));
        listeImage.add(new GreenfootImage("adrien2.png"));

        // Add the initial actors
        populate();
    }
    
    public GreenfootImage getMyRandomImage()
    {     
        GreenfootImage image;
        
        image = listeImage.get(
                                Greenfoot.getRandomNumber(
                                                            listeImage.size()
                                                            )
                               );
        if (image == null) 
        {
            return new GreenfootImage("pear.png");
        }
        else {
        return image;
        }
    }
     
    public void essai()
    {
        counter.add(20);
    }
    
    
    
    /**
     * Creates balloons at the bottom of the world.
     */
    public void act() 
    {
        if(Greenfoot.getRandomNumber(100) < 3) {
            addObject(new Balloon(getMyRandomImage()), Greenfoot.getRandomNumber(L), H);   
        }
    }

    
    /**
     * Count one popped balloon.
     */
    public void countPop()
    {
        counter.add(20);
    }

    /**
     * Called when game is up. Stop running and display score.
     */
    public void gameOver() 
    {
        addObject(new ScoreBoard(counter.getValue()), getWidth()/2, getHeight()/2);
        Greenfoot.playSound("buzz.wav");
        Greenfoot.stop();
    }

    /**
     * Populate the world with bombs and a crosshair.
     */
    private void populate()
    {
        addObject(new Bomb(), L/3, H-30);
        addObject(new Bomb(), L/3-50, H-30);
        addObject(new Bomb(), L/3+50, H-30);
        addObject(new Dart(), L/2 , H/2);
        
        addObject(counter, 100, H-30);
    }  
    
}
