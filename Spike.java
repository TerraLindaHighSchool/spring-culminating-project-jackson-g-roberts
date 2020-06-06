import greenfoot.*;

/**
 * Spike hazard, sits on a planet and ends the game if touched by the ball.
 *
 * @author Jackson Roberts
 * @author Jack Blatcher
 * 
 * @version 1.0
 */
public class Spike extends Actor
{
    private GreenfootImage spike1;
    private GreenfootImage spike2;
    
    private int currentAnimFrame;
    private int frameCount;
    
    /**
     * Creates a new spike, and assigns and scales the images.
     */
    public Spike()
    {
        spike1 = new GreenfootImage("Spike1.png");
        spike2 = new GreenfootImage("Spike2.png");

        spike1.scale(32,32);
        spike2.scale(32,32);

        setImage(spike1);
    }

    /**
     * Checks collisions with the ball, and animates.
     */
    public void act()
    {
        checkCollisions();
        animate();
    }

    /**
     * Checks if intersecting the ball, and if so, sends to the game over screen.
     */
    public void checkCollisions()
    {
       if (!getIntersectingObjects(Ball.class).isEmpty())
       {
           getWorldOfType(Space.class).gameOver("Spike");
       }
    }

    /**
     * Animates through the 2 animation frames.
     */
    public void animate()
    {
        if(frameCount % 8 == 0)
        {
            if(currentAnimFrame >= 5)
            {
                currentAnimFrame = 0;
            } else {
                currentAnimFrame++;
            }
        }

        switch (currentAnimFrame)
        {
            case 0:
                setImage(spike1);
                break;
            case 1:
                setImage(spike2);
                break;
            default:
                setImage(spike1);
                break;
        }
        frameCount++;
    }
}
