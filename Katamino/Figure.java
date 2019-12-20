import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Figure here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Figure extends Actor
{
   
//figure parameters
    private int size, center;
    private int[][] matrix;
    private char color;
    private int rotation;
    private int posX, posY;
    private Piece[] pieces;
    
    static InputController inputSwap, inputRotate;
    
    static void initControllers()
    {
        if (inputRotate!=null) return;
        inputRotate = new InputController("up");
        inputSwap = new InputController("space");
        
    }
   
    public Figure(char color, int[][] matrix)
    {
        initControllers();
        center = (size-1)/2;
        posX = 0;
        posY = 0;
        rotation = 0;
        this.color = color;
        this.matrix = matrix;      
    }
    
    public void addedToWorld(World world)
    {
        setImage(new GreenfootImage(1, 1));
        super.addedToWorld(world);
        initPieces();
    }
        
    /**
     * Act - do whatever the Figure wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act()
    {
        if(Greenfoot.mouseClicked(this)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            setLocation(mouse.getX(), mouse.getY());
        }
        if (getMyWorld().isFinished())
            return;
        if (inputRotate.pressed2())
            if (rotate()) inputRotate.freeze();
        if (inputSwap.pressed2())
            if (goSwap()) inputSwap.freeze();


    }
    
    public void setLocation(int x, int y)
    {
        posX = x; posY = y;
        if (pieces!=null)
            updatePieces();
        super.setLocation(posX-center, posY-center);
    }
    
    
    
    
    private void initPieces()
    {
        size = this.matrix.length;
        int cnt = 0;
        for (int i=0; i<size; i++)
            for (int j=0; j<size; j++)
                if (!isEmpty(i, j, rotation)) cnt++;
        this.pieces = new Piece[cnt];
        int k = 0;
        for (int i=0; i<size; i++)
            for (int j=0; j<size; j++)
                if (!isEmpty(i, j, rotation))
                {
                   pieces[k] = new Piece(color, this);
                   getWorld().addObject(pieces[k], posX+i-center, posY+j-center);
                   k++;
                }
    }
    
    private void updatePieces()
    {
        int k = 0;
        for (int i=0; i<size; i++)
            for (int j=0; j<size; j++)
                if (!isEmpty(i, j, rotation))
                {
                   pieces[k].setLocation(posX+i-center, posY+j-center);
                   k++;
                }
    }
//     public void freePieces()
//     {
//         for (Piece p: pieces)
//             getMyWorld().addExistentPiece(p);
//     }
        
    private KataminoWorld getMyWorld()
    {
        return (KataminoWorld)getWorld();
    }
    
    private boolean isEmpty(int x, int y, int rotation)
    {
        for (int i=0; i<rotation; i++)
        {
            int x1 = y, y1 = size-x-1;
            x = x1; y = y1;
        }
        if (x<0||y<0||x>=size||y>=size)
            return true;
        return matrix[y][x]==0;
    }
    
//     public boolean check(int x, int y, int rotation)
//     {
//         x-=center;
//         y-=center;
//         for (int i=0; i<size; i++)
//             for (int j=0; j<size; j++)
//                 if (!isEmpty(i, j, rotation)&&!getMyWorld().isEmpty(x+i, y+j))
//                     return false;
//         return true;
//     }  
//     
//     public boolean canRotate()
//     {
//         return check(posX, posY, (rotation+1)%maxRot);
//     }
    
    public abstract boolean goSwap();
    
    public boolean rotate()
    {
//        if (canRotate())
//        {    
        rotation =  (rotation+1);//
        updatePieces();
        return true;
//        } 
//        return false;
    }
    
    
    
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
static class InputController
    {
        String key;
        int countdown;
        int cooldown;
        public InputController(String key)
        {
            this(key, 5);
        }
        public InputController(String key, int cooldown)
        {
            this.key = key;
            this.cooldown = cooldown;
        }
        public boolean pressed()
        {
            if (countdown==0)
            {
                if (Greenfoot.isKeyDown(key))
                    return true;
            } else if (!Greenfoot.isKeyDown(key))
                countdown = 0;
            return false;
        }
        
        public boolean pressed2()
        {
            update();
            return pressed();
        }
        
        public void freeze()
        {
            countdown = cooldown;
        }
        
        public void update()
        {
            if (countdown>0)
                countdown--;
        }
    }    
}
