
import greenfoot.*;

/**
 * Space, area for space golf to take place.
 *
 * @author Jackson Roberts
 *
 * @version 1.6
 */
public class Space extends World
{
    private int currentLevel;

    private int timeCount;
    
    private int strokes;
    
    private String tutLine1 = "Welcome to Spolf! Use the cursor and spacebar to launch!";
    private String tutLine2 = "Hitting the edge loops you over, like Pac-Man!";
    private String tutLine3 = "Spikes and Black Holes reset the game, so don't hit them!";
    private String tutLine4 = "Touch the flag to advance!";

    /**
     * Create the space and all objects within it.
     */
    public Space()
    {
        super(1200, 800, 1);
        setPaintOrder(Cursor.class, LaunchArrow.class, Ball.class, Planet.class, BlackHole.class);
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        paintStars(250);

        currentLevel = -1;
        generateTutorial();
    }
    
    public void act()
    {
        timeCount++;
        showText("Strokes: " + strokes, 75, 780);
        
        showText(tutLine1, 600, 50);
        showText(tutLine2, 600, 100);
        showText(tutLine3, 600, 525);
        showText(tutLine4, 600, 675);
    }
    
    public void generateTutorial()
    {
        for (Actor actor : getObjects(Actor.class))
        {
            removeObject(actor);
        }
        Planet planet1 = new Planet(100, 0.0, Material.STONE, Color.RED);
        addObject(planet1, 200, 400);
        Affector affector1 = new Affector(planet1, Color.RED, 300, 600);
        addObject(affector1, 200, 400);
        
        Planet planet2 = new Planet(50, 0.0, Material.STONE, Color.BLUE);
        addObject(planet2, 1000, 400);
        Affector affector2 = new Affector(planet2, Color.BLUE, 200, 500);
        addObject(affector2, 1000, 400);
        
        Flag flag = new Flag();
        addObject(flag, 1000, 335);
        
        Spike spike1 = new Spike();
        addObject(spike1, 200, 510);
        spike1.setRotation(180);
        
        BlackHole blackhole = new BlackHole(64, 1.0);
        addObject(blackhole, 600, 600);
        
        Affector bhaffector = new Affector(blackhole, Color.MAGENTA, 80, 500);
        addObject(bhaffector, 600, 600);
        
        Cursor cursor = new Cursor();
        addObject(cursor, 0, 0);
        
        Ball ball = new Ball();
        addObject(ball, 200, 250);
    }

    public void generateLevelZero()
    {
        for (Actor actor : getObjects(Actor.class))
        {
            removeObject(actor);
        }
        Planet planet1 = new Planet(100, 0.0, Material.STONE, Color.RED);
        addObject(planet1, 200, 200);
        Affector affector1 = new Affector(planet1, Color.RED, 300, 600);
        addObject(affector1, 200, 200);

        Planet planet2 = new Planet(50, 0.0, Material.STONE, Color.BLUE);
        addObject(planet2, 450, 450);
        Affector affector2 = new Affector(planet2, Color.BLUE, 200, 500);
        addObject(affector2, 450, 450);
                
        Planet planet3 = new Planet(75, 0.0, Material.STONE, Color.GREEN);
        addObject(planet3, 1000, 100);
        Affector affector3 = new Affector(planet3, Color.GREEN, 250, 600);
        addObject(affector3, 1000, 100);
                
        Planet planet4 = new Planet(50, 0.0, Material.STONE, Color.PINK);
        addObject(planet4, 900, 600);
        Affector affector4 = new Affector(planet4, Color.PINK, 200, 500);
        addObject(affector4, 900, 600);
                
        Flag flag = new Flag();
        addObject(flag, 1000, 190);
        flag.setRotation(180);

        Spike spike1 = new Spike();
        addObject(spike1, 450, 395);
        Spike spike2 = new Spike();
        addObject(spike2, 490, 410);
        spike2.setRotation(45);
        Spike spike3 = new Spike();
        addObject(spike3, 505, 450);
        spike3.setRotation(90);
        Spike spike4 = new Spike();
        addObject(spike4, 410, 490);
        spike4.setRotation(225);

        BlackHole blackhole = new BlackHole(64, 1.0);
        addObject(blackhole, 650, 650);
        
        Affector bhaffector = new Affector(blackhole, Color.MAGENTA, 80, 500);
        addObject(bhaffector, 650, 650);
        
        Cursor cursor = new Cursor();
        addObject(cursor, 0, 0);
        
        Ball ball = new Ball();
        addObject(ball, 150, 75);
    }
    
    private void generateLevelOne()
    {
        for (Actor actor : getObjects(Actor.class))
        {
            removeObject(actor);
        }
        Planet planet1 = new Planet(100, 0.0, Material.STONE, Color.RED);
        addObject(planet1, 200, 600);
        Affector affector1 = new Affector(planet1, Color.RED, 300, 600);
        addObject(affector1, 200, 600);
        
        Planet planet2 = new Planet(50, 0.0, Material.STONE, Color.BLUE);
        addObject(planet2, 1000, 600);
        Affector affector2 = new Affector(planet2, Color.BLUE, 200, 500);
        addObject(affector2, 1000, 600);
        
        Flag flag = new Flag();
        addObject(flag, 1000, 535);
        
        Spike spike1 = new Spike();
        addObject(spike1, 1000, 660);
        spike1.setRotation(180);
        
        BlackHole blackhole = new BlackHole(64, 1.0);
        addObject(blackhole, 600, 100);
        Affector bhaffector = new Affector(blackhole, Color.MAGENTA, 80, 500);
        addObject(bhaffector, 600, 100);
        BlackHole blackhole2 = new BlackHole(64, 1.0);
        addObject(blackhole2, 600, 250);
        Affector bhaffector2 = new Affector(blackhole2, Color.MAGENTA, 80, 500);
        addObject(bhaffector2, 600, 250);
        BlackHole blackhole3 = new BlackHole(64, 1.0);
        addObject(blackhole3, 600, 400);
        Affector bhaffector3 = new Affector(blackhole3, Color.MAGENTA, 80, 500);
        addObject(bhaffector3, 600, 400);
        BlackHole blackhole4 = new BlackHole(64, 1.0);
        addObject(blackhole4, 600, 550);
        Affector bhaffector4 = new Affector(blackhole4, Color.MAGENTA, 80, 500);
        addObject(bhaffector4, 600, 550);
        BlackHole blackhole5 = new BlackHole(64, 1.0);
        addObject(blackhole5, 600, 700);
        Affector bhaffector5 = new Affector(blackhole5, Color.MAGENTA, 80, 500);
        addObject(bhaffector5, 600, 700);
        
        Cursor cursor = new Cursor();
        addObject(cursor, 0, 0);
        
        Ball ball = new Ball();
        addObject(ball, 275, 450);
    }
    
    private void generateEndGame()
    {
        for (Actor actor : getObjects(Actor.class))
        {
            removeObject(actor);
        }
        Planet planet1 = new Planet(100, 0.0, Material.STONE, Color.RED);
        addObject(planet1, 600, 400);
        Affector affector1 = new Affector(planet1, Color.RED, 300, 750);
        addObject(affector1, 600, 400);
        
        Cursor cursor = new Cursor();
        addObject(cursor, 0, 0);
        
        Ball ball = new Ball();
        addObject(ball, 600, 250);
        
        showText("You Win!", 600, 100);
        showText("You finished with " + strokes + " strokes!", 600, 125);
        showText("You finished in " + timeCount % 60 + " seconds!", 600, 150);
    }
    
    public void nextLevel()
    {
        tutLine1 = null;
        tutLine2 = null;
        tutLine3 = null;
        tutLine4 = null;
        
        currentLevel++;
        switch(currentLevel)
        {
            case -1:
                generateTutorial();
                break;
            case 0:
                generateLevelZero();
                break;
            case 1:
                generateLevelOne();
                break;
            default:
                generateEndGame();
                break;
        }
    }
    
    public void incrementStrokes()
    {
        strokes++;
    }

    private void paintStars(int count)
    {
        for (int i = 0; i < count; i++)
        {
          GreenfootImage background = getBackground();

          int x = Greenfoot.getRandomNumber(getWidth());
          int y = Greenfoot.getRandomNumber(getHeight());

          int starRed = Greenfoot.getRandomNumber(56) + 200;
          int starGreen = Greenfoot.getRandomNumber(56) + 200;
          int starBlue = Greenfoot.getRandomNumber(56) + 200;

          Color starColor = new greenfoot.Color(starRed, starGreen, starBlue);

          background.setColor(starColor);

          int starSize = Greenfoot.getRandomNumber(2) + 2;

          background.fillOval(x, y, starSize, starSize);
        }
    }

    public void gameOver(String deathType)
    {
        for (Actor actor : getObjects(Actor.class))
        {
            removeObject(actor);
        }

        showText("You Lose! Died to " + deathType, 600, 400);
        Greenfoot.stop();
    }
}
