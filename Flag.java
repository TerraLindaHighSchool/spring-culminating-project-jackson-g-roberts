import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Flag, end goal of a level.
 *
 * @author Jack Blatcher
 * @author Jackson Roberts
 * 
 * @version 1.0
 */
public class Flag extends Actor
{
    private int score;
    private GreenfootImage flag1;
    private GreenfootImage flag2;
    private GreenfootImage flag3;
    private GreenfootImage flag4;
    private GreenfootImage flag5;

    private int currentAnimFrame;
    private int frameCount;

    /**
     * Sets the default images and scales them.
     */
    public Flag()
    {
      flag1 = new GreenfootImage("Flag1.png");
      flag2 = new GreenfootImage("Flag2.png");
      flag3 = new GreenfootImage("Flag3.png");
      flag4 = new GreenfootImage("Flag4.png");
      flag5 = new GreenfootImage("Flag5.png");

      flag1.scale(32,32);
      flag2.scale(32,32);
      flag3.scale(32,32);
      flag4.scale(32,32);
      flag5.scale(32,32);
      setImage(flag1);
    }

    /**
     * Checks collisions for intersecting the ball, and then animates.
     */
    public void act()
    {
       checkCollisions();
       animate();
    }

    /**
     * Checks if intersecting the ball, and if so, sends to the next level.
     */
    public void checkCollisions()
    {
       if (!getIntersectingObjects(Ball.class).isEmpty())
       {
           getWorldOfType(Space.class).nextLevel();
       }
    }

    /**
     * Loops through the 5 different frames of animation.
     */
    public void animate()
    {
      if(frameCount % 5 == 0)
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
            setImage(flag1);
            break;
        case 1:
            setImage (flag2);
            break;
        case 2:
            setImage (flag3);
            break;
        case 3:
            setImage (flag4);
            break;
        case 4:
            setImage (flag5);
            break;
        default:
            setImage(flag1);
            break;
      }

      frameCount++;

    }
}
