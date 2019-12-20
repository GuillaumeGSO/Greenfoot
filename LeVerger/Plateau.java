import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Write a description of class Plateau here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plateau  extends World
{

    public final static int HSIZE = 600;
    public final static int VSIZE = 400;

    public De leDe;
    private int fruitToPickUp;
    private List<Corbeau> listCorbeau = new ArrayList<Corbeau>();
    private boolean lanceAnim;

    /**
     * Constructor for objects of class Plateau.
     * 
     */
    public Plateau()
    {    
        super(HSIZE, VSIZE, 1);

        addObject(new Pomme(), 163, 20);
        addObject(new Pomme(), 108, 23);
        addObject(new Pomme(), 220, 47);
        addObject(new Pomme(), 120, 73);
        addObject(new Pomme(), 141, 131);

        addObject(new Cerise(), 465, 268);
        addObject(new Cerise(), 396, 332);
        addObject(new Cerise(), 489, 339);
        addObject(new Cerise(), 432, 378);
        addObject(new Cerise(), 550, 341);

        addObject(new Poire(), 133, 262);
        addObject(new Poire(), 76, 342);
        addObject(new Poire(), 175, 321);
        addObject(new Poire(), 130, 371);
        addObject(new Poire(), 229, 363);

        addObject(new Prune(), 388, 26);
        addObject(new Prune(), 473, 28);
        addObject(new Prune(), 437, 78);
        addObject(new Prune(), 538, 58);
        addObject(new Prune(), 474, 136);

        addObject(new De(), 49, 206);

        addObject(new Corbeau1(), 249, 160);
        addObject(new Corbeau2(), 313, 160);
        addObject(new Corbeau3(), 380, 160);
        addObject(new Corbeau4(), 249, 224);
        addObject(new Corbeau5(), 313, 224);
        addObject(new Corbeau6(), 380, 224);

        initCorbeau();
        listCorbeau = this.getObjects(Corbeau.class);

        prepare();
    }

    public void pickUp(Fruit fruit) {
        this.removeObject(fruit);
        this.fruitToPickUp--;
    }

    public int getFruitToPickUp() {
        return fruitToPickUp;
    }

    public void setFruitToPickUp(int nb) {
        this.fruitToPickUp = nb;
    }

    public boolean addCorbeau() {

        Collections.shuffle(listCorbeau);
        listCorbeau.get(0).getImage().setTransparency(255);
        listCorbeau.remove(0);
        checkGain();
        if (listCorbeau.size()>0) {
            return true;
        } else {
            return false;
        }

    }

    public void initCorbeau() {
        Corbeau corb;
        for (Object item : this.getObjects(Corbeau.class)) {
            corb=(Corbeau) item;
            corb.getImage().setTransparency(0);
        }
    }

    public void checkGain() {
        GreenfootSound son;
        if (this.getObjects(Fruit.class).size() == 0)
        {
            //c'est gagné
            addObject(new Panneau(),313, 326);
            son = new GreenfootSound("applause.wav");
            son.play();
        }
        if (this.listCorbeau.size() == 0)
        {
            //c'est perdu
            son = new GreenfootSound("corbeau.wav");
            son.play();
            lanceAnim = true;
        }
    }

    public boolean getLanceAnim() {
        return lanceAnim;
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
    }
}





















