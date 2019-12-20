import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Piece here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Piece  extends Actor
{
    char color;
    Figure figure;
    
    public Piece(char color, Figure figure)
    {
        super();
        this.figure = figure;
        this.color = color;
    }
    
    public Piece(char color)
    {
        super();
        this.color = color;
    }
    public void addedToWorld(World world)
    {
        super.addedToWorld(world);
        setImage(getMyWorld().getImage(color));
    }

    /**
     * Act - do whatever the Piece wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {

        // Add your action code here.
    }    
    
    private KataminoWorld getMyWorld()
    {
        return (KataminoWorld)getWorld();
    }
    
    public Figure getFigure() {
        return this.figure;
    }
    
    public List<Piece> getListPieces(Figure figure) {
        List<Piece> allPieces = getMyWorld().getObjects(Piece.class);
        List<Piece> lReturn = new ArrayList<Piece>();
        for (Piece iPiece : allPieces) {
            if (figure.equals(iPiece.getFigure())) {
                lReturn.add(iPiece);
            }
        }
        return lReturn;
    }
}
