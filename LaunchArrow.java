import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Line drawn from the Cursor to the Ball, to illustrate
 * to the player that they're hitting the ball.
 *
 * @author Jackson Roberts
 * 
 * @version 0.6
 */
public class LaunchArrow extends Actor
{
    private int timer;
    private GreenfootImage image;

    private double mdx;
    private double mdy;
    
    /**
     * Creates a new LaunchArrow with a specified dx and dy between the
     * Cursor and Ball.
     */
    public LaunchArrow(double dx, double dy)
    {
        mdx = dx;
        mdy = dy;

        dx = Math.abs(dx);
        dy = Math.abs(dy);

        if (dx < 1) dx = 1;
        if (dy < 1) dy = 1;

        image = new GreenfootImage((int) dx, (int) dy);
        updateImage();
    }

    /**
     * Updates the image, and then removes the object if it has been alive
     * for more than one frame.
     */
    public void act()
    {
        updateImage();
        if (timer > 0)
        {
            getWorld().removeObject(this);
        } else
        {
            timer++;
        }
    }
    
    /**
     * Clears the image, and then draws a line from one corner to another, 
     * dependant on mdx and mdy.
     */
    private void updateImage()
    {
        image.clear();
        image.setColor(Color.WHITE);
        if (mdx > 0 && mdy > 0)
        {
            image.drawLine(0, 0, image.getWidth(), image.getHeight());
        } else if (mdx > 0 && mdy < 0)
        {
            image.drawLine(0, image.getHeight(), image.getWidth(), 0);
        } else if (mdx < 0 && mdy > 0)
        {
            image.drawLine(0, image.getHeight(), image.getWidth(), 0);
        } else if (mdx < 0 && mdy < 0)
        {
            image.drawLine(0, 0, image.getWidth(), image.getHeight());
        }
        setImage(image);
    }
}
