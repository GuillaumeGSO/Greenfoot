import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class MyWorld here.
 * 
 * @author Guillaume
 * @version octobre 2019
 */
public class MyWorld extends World
{

    public static final int LARGEUR = 20;

    public static final int HAUTEUR = 20;

    public static boolean[][] worldGrid= new boolean[LARGEUR][HAUTEUR];

    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld()
    {    
        super(LARGEUR, HAUTEUR, 30);
        reset();
    }

    public void reset() {
        worldGrid = new boolean[LARGEUR][HAUTEUR];
        display();
    }

    public void started() {
        List<Cellule> lstCellules = getObjects(Cellule.class);
        if (lstCellules.size() > 0) {
            for (Cellule iCellule : lstCellules) {
                worldGrid[iCellule.getX()][iCellule.getY()] = true;
            }
        } else {
            worldGrid[8][8]=true;
            worldGrid[8][9]=true;
            worldGrid[8][10]=true;
            worldGrid[9][8]=true;
            worldGrid[10][8]=true;
            worldGrid[10][9]=true;
            worldGrid[10][10]=true;
            worldGrid[9][11]=true;
        }
        display();
    }

    /**
     * Ceci va servir à calculer la grille suivante puis à l'afficher à l'aide des acteurs
     */
    public void act() {
        worldGrid = grilleSuivante(worldGrid);
        //on redessine la grille
        display();

    }

    public void display() {
        for (int i=0 ; i < LARGEUR ; i++) {
            for (int j=0 ; j < HAUTEUR ; j++) {
                List<Cellule> cellules = this.getObjectsAt(i,j, Cellule.class);
                if (worldGrid[i][j]) {
                    //Ceci doit être vivant
                    if (cellules==null || cellules.size()==0) { 
                        Cellule cellule = new Cellule();
                        addObject(cellule, i, j);
                    }
                } else {
                    //Ceci doit être mort
                    this.removeObjects(cellules);
                }

            }
        }
    }

    public boolean[][] grilleSuivante(boolean[][] tab) {
        // 1. Any live cell with fewer than two live neighbours dies, as if caused by
        // underpopulation.
        // 2. Any live cell with more than three live neighbours dies, as if by
        // overcrowding.
        // 3. Any live cell with two or three live neighbours lives on to the next
        // generation.
        // 4. Any dead cell with exactly three live neighbours becomes a live cell.
        boolean[][] grille = new boolean[LARGEUR][HAUTEUR];

        for (int i = 0; i < LARGEUR; i++) {
            for (int j = 0; j < HAUTEUR; j++) {
                int nbVivantes = getNbCellulesVivantes(tab, i, j);
                if (nbVivantes < 2) {
                    // underpopulation
                    grille[i][j] = false;
                    continue;
                }
                if (nbVivantes > 3) {
                    // overcrowding
                    grille[i][j] = false;
                    continue;
                }
                // stay alive
                if (tab[i][j] && (nbVivantes == 2 || nbVivantes == 3)) {
                    grille[i][j] = true;
                    continue;
                }

                // become alive
                if (!tab[i][j] && nbVivantes == 3) {
                    grille[i][j] = true;
                    continue;
                }
            }
        }
        return grille;
    }

    public int getNbCellulesVivantes(boolean[][] tab, int x, int y) {
        int result = 0;
        // Verification nord
        if (y > 0) {
            // nord
            if (tab[x][y - 1]) {
                result++;
            }
            // nord ouest
            if (x - 1 >= 0 && tab[x - 1][y - 1]) {
                result++;
            }
            // nord est
            if (x + 1 < LARGEUR && tab[x + 1][y - 1]) {
                result++;
            }

        }

        // Verification sud
        if (y + 1 < HAUTEUR) {
            // Sud
            if (tab[x][y + 1]) {
                result++;
            }
            // sud ouest
            if (x - 1 >= 0 && tab[x - 1][y + 1]) {
                result++;
            }
            // sud est
            if (x + 1 < LARGEUR && tab[x + 1][y + 1]) {
                result++;
            }
        }

        // Vérification ouest
        if (x - 1 >= 0 && tab[x - 1][y]) {
            result++;
        }
        // Verification est
        if (x + 1 < LARGEUR && tab[x + 1][y]) {
            result++;
        }

        return result;
    }
}
