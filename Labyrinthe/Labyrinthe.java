import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

public class Labyrinthe extends World
{
    //super dur
    public static final int WIDTH = 50;
    public static final int HIGH = 30;
    public static final int RESOLUTION = 12;

    //moyen
    //public static final int WIDTH = 10;
    //public static final int HIGH = 6;
    //public static final int RESOLUTION = 60;

    //super fastoche
    //public static final int WIDTH = 5;
    //public static final int HIGH = 3;
    //public static final int RESOLUTION = 120;

    public Labyrinthe()
    {    
        super(WIDTH, HIGH, RESOLUTION); 

        //insertEntreeSortie();

        //fillLabyTab();
        //fillLabyRandom();
        //paintAll();
        fillLabyAlgo();
        paintAll();
        addPlayer();
        addExit();
    }

    public void fillLabyAlgo() {
        /* ici le but est d'utiliser un algo simple de labyrinthe :
         * faire en sorte que tout le laby soit accessible.
         * On commence par la sortie : prendre au hasard
         * Pour chaque voisin pris au hasard :
         * Si le voisin n'a pas encore été visité, alors pas de mur entre cellule et ce voisin.
         * Et ainsi de suite mais de façon récursive.
         */

        initialiseAllClosedTile();

        List<Tile> allTile = getObjects(Tile.class);

        //Prendre une cellule au hasard
        Tile exitTile = allTile.get(Greenfoot.getRandomNumber(allTile.size()));

        exitTile.setInitialized();
        int test=0;

        traiteVoisins(exitTile);

    }

    public void act() {
        if (getObjects(Exit.class).size()==0) {
            addExit();
            paintAll();
        }
    }

    private void traiteVoisins(Tile cTile) {
        List<Tile> listVoisins = cTile.getVoisins();

        for (Tile unVoisin : listVoisins) {
            if (!unVoisin.getInitialized()) {
                deleteWallBetweenTile(cTile, unVoisin);
                unVoisin.setInitialized();
                traiteVoisins(unVoisin);
            }
        }

    }

    public void deleteWallBetweenTile(Tile tileA, Tile tileB) {
        if (tileA.getX()<tileB.getX()) {tileA.setEast(true);tileB.setWest(true);}
        if (tileA.getX()>tileB.getX()) {tileA.setWest(true);tileB.setEast(true);}
        if (tileA.getY()>tileB.getY()) {tileA.setNorth(true);tileB.setSouth(true);}
        if (tileA.getY()<tileB.getY()) {tileA.setSouth(true);tileB.setNorth(true);}

    }

    public int countVisited(List<Tile> aListTile) {
        int nb = 0;
        for (Tile tile : aListTile) {
            if (tile.getInitialized()) nb++;
        }
        return nb;
    }

    public void initialiseAllClosedTile() {
        for (int i=0;i<getWidth();i++) {
            for (int j=0;j<getHeight();j++) {
                addObject( new Tile(false, false, false, false), i, j);
            }
        }
    }

    private boolean getRandomDirection() {
        return (Greenfoot.getRandomNumber(2)==1 ? true : false);
    }

    public void addPlayer() {
        addObject( new Player(),
            Greenfoot.getRandomNumber((int) getWidth()/5),
            Greenfoot.getRandomNumber((int) getHeight()/3)
        );
    }

    public void addExit() {
        addObject( new Exit(),
            Greenfoot.getRandomNumber((int)(getWidth())),
            Greenfoot.getRandomNumber((int)(getHeight()))
        );

    }

    public void paintAll()
    {
        List<Tile> listTile = getObjects(Tile.class);
        for (Tile tile : listTile) {
            tile.addedToWorld(this);
        }
    }

}