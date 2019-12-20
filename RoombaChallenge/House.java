import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)



/**
 * This is your house. Or at least somebody's twisted, 
 * dirty-floored collection of rooms. Your robot's job is to clean it...
 *
 * @author David Adamson (adapted from Michael Kolling's Earth class)
 * @version 1.0
 */
public class House extends World
{
    public static final int RESOLUTION = 1;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int WALL_LEVEL = 128*3;
    public static final int CLEAN_LEVEL = 224*3;
    public static final int WHITE_LEVEL = 255*3;

    public static final int SCORE_DISPLAY_TIME = 240;
    public static final int ALL_DONE_THRESHOLD = 98;
    private static final int TIMER_PERIOD = 6000;

    private Robot robot;
    private int currentMap;
    private long floorPixels = 1;
    private long cleanPixels = 0;
    
    private GreenfootImage buffer = null;
    
    //the robot's starting location is given for each map.
    private int[][] mapData = {{400, 300},{400, 300}, {150, 300}, {400, 300},{300, 250}};

    private int[] scores;
    
    /**
     * Create a new world. 
     */
    public House()
    {
        super(WIDTH / RESOLUTION, HEIGHT / RESOLUTION, RESOLUTION);
        currentMap = 0;
        scores = new int[mapData.length];    // one score for each map
        showMap(currentMap);
    }
    
    /**
     * Return true, if the specified coordinate shows wall.
     * (Wall is defined as the darkest color range.)
     */
    public boolean isWall(int x, int y)
    {
        GreenfootImage map = getBackground();
        if(x < 0 || y < 0 || x >= map.getWidth() || y >= map.getHeight())
            return true;
        Color col = map.getColorAt(x, y);
        return col.getBlue()+col.getGreen()+col.getRed() < WALL_LEVEL;
    }
    
    /**
     * Jump to the given map number (1..n).
     */
    public void jumpToMap(int map)
    {
        clearWorld();
        currentMap = map-1;
        showMap(currentMap);
    }
    
    /**
     * Set up the start scene.
     */
    private void showMap(int mapNo)
    {
        clearWorld();
        robot = new Roomba();
        GreenfootImage map = new GreenfootImage("map" + mapNo + ".jpg");
        setBackground(map);
        Counter mapTitle = new Counter(robot.getAuthorName() + " - Map ", mapNo+1);
        addObject(mapTitle, 400, 20);
        int[] roboData = mapData[mapNo];
        addObject(robot, roboData[0], roboData[1]);
        robot.setRotation((int)(Math.random()*360));
        
        updateCoverage();
        addObject(new GameTimer(), 400, 40);
        addObject(new ScoreCounter(), 400, 560);
    }
    
    /**
     * Game is over. Stop running, display score.
     */
    public void mapFinished(int time)
    {
        GreenfootImage backBuffer = getBackground();
        displayScore(time);
        wait(SCORE_DISPLAY_TIME);
        clearWorld();
        currentMap++;
        if(currentMap < mapData.length) {
            showMap(currentMap);
        }
        else {
            buffer = backBuffer;
            displayFinalScore();
            Greenfoot.stop();
        }
    }

    /**
     * calculate the score as the cleaned percentage of the cleanable area.
     */
    private void displayScore(int time)
    {
        scores[currentMap] = getScore() + time;
        ScoreBoard board = new ScoreBoard(robot.getAuthorName(), "Map " + (currentMap+1), "Score: ", currentMap, scores);
        if(buffer == null)
            buffer = getBackground();
        setBackground(colorFloor(buffer));
        addObject(board, getWidth() / 2, getHeight() / 2);
    }
    
    public int getScore()
    {
        return (int)(cleanPixels*100 / floorPixels);
    }
 
    
    private void updateCoverage()
    {
        
        long possible = 0;
        long clean = 0;
        GreenfootImage floor = getBackground();
        for(int x = 0; x < floor.getWidth(); x++)
        {
            for(int y = 0; y < floor.getHeight(); y++)
            {
                Color speck = floor.getColorAt(x, y);
                int brightness = speck.getRed() + speck.getGreen() + speck.getBlue();
                if(brightness > WALL_LEVEL)
                {
                    possible++;
                }
                
                if(brightness >= CLEAN_LEVEL)
                {
                    clean++;
                }
            }
        }
        floorPixels = possible;
        cleanPixels = clean;
    }
    
    /**clean a given spot on the floor, and update the clean-floor tally**/
     public void clean(int myX, int myY, int r, int shine)
    {
        
        GreenfootImage floor = getBackground();
       
        
        for(int x = -r; x < r; x++)
        {
            for(int y = -r; y < r; y++)
            {
                if(x*x + y*y < r*r && myX+x >=0 && myY+y >= 0 && myX+x < floor.getWidth() && myY+y < floor.getHeight())
                {
                    Color speck = floor.getColorAt(myX+x, myY+y);
                    int before = speck.getRed() + speck.getGreen() + speck.getBlue();
                    int after = before;
                    
                    if(before > WALL_LEVEL && before < WHITE_LEVEL)
                    {
                        int red = (int)(Math.min(speck.getRed() + shine, 255));
                        int green = (int)(Math.min(speck.getGreen() + shine, 255));
                        int blue = (int)(Math.min(speck.getBlue() + shine, 255));
                        after = red+green+blue;
                        floor.setColorAt(myX+x, myY+y, new Color(red, green, blue));
                    }
                    
                    if(before <= CLEAN_LEVEL && after > CLEAN_LEVEL)
                    {
                        cleanPixels++;
                    }
                }
            }
        }
    }
    
    /**
     * unambiguously mark the clean and unclean floor areas.
     */
    private GreenfootImage colorFloor(GreenfootImage floor)
    {
        floor = new GreenfootImage(floor);
        
        for(int x = 0; x < floor.getWidth(); x++)
            for(int y = 0; y < floor.getHeight(); y++)
            {
                Color speck = floor.getColorAt(x, y);
                int brightness = speck.getRed() + speck.getGreen() + speck.getBlue();
                if(brightness <= WALL_LEVEL)
                {
                    speck = Color.BLACK;
                }
                else if(brightness > CLEAN_LEVEL)
                {
                    speck = Color.BLUE;
                }
                else
                {
                    speck = Color.ORANGE;
                }
                floor.setColorAt(x, y, speck);
            }
            return floor;
    }
    private void displayFinalScore()
    {
        ScoreBoard board = new ScoreBoard(robot.getAuthorName(), "Final Score", "Total: ", scores);
        addObject(board, getWidth() / 2, getHeight() / 2);
    }
    
    private void clearWorld()
    {
        buffer = null;
        removeObjects(getObjects(null));
    }
    
    private void wait(int time)
    {
        for (int i = 0; i < time; i++) {
            Greenfoot.delay(1);
        }
    }
    
    public void started()
    {
        if(null != buffer)
        {
            setBackground(buffer);
            buffer = null;
        }
    }
    
    public void stopped()
    {
        if(null == buffer)
        {   
            buffer = getBackground();
        }
        setBackground(colorFloor(buffer));
    }
    
}