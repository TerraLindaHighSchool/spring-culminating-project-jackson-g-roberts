import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Cursor, follows mouse, and handles drawing the LaunchArrow
 * and launching the Ball.
 *
 * @author Jackson Roberts
 * @author Jack Blatcher
 *
 * @version 1.0
 */
public class Cursor extends SmoothMover
{
    private MouseInfo mouse;

    private GreenfootImage cursorImage = new GreenfootImage("cursor.png");

    private boolean spaceDownLastFrame;

    /**
     * Instantiates a new Cursor with an image and a MouseInfo;
     */
    public Cursor()
    {
        setImage(cursorImage);
    }

    /**
     * Specifies the mouse, and then goes to the mouse and checks the keys.
     */
    public void act()
    {
       moveToMouse();
       checkKeys();
    }

    /**
     * Checks if space is down, and if so draws the arrow.
     * Then checks if space has been released, and if so launches the Ball,
     * with direction and force based on the position of the cursor
     * relative to the Ball.
     */
    private void checkKeys()
    {
        if (Greenfoot.isKeyDown("space"))
        {
            drawArrow();
        }

        if (spaceDownLastFrame && !Greenfoot.isKeyDown("space"))
        {
            Ball ball = getWorld().getObjects(Ball.class).get(0);

            Vector vectToBall = vectorBetweenPoints(getX(), getY(), ball.getExactX(), ball.getExactY());

            ball.launch(vectToBall.getDirection(), vectToBall.getLength() / 100);
        }

        spaceDownLastFrame = Greenfoot.isKeyDown("space");
    }

    /**
     * Uses the relative positions of the Ball and Cursor to calculate where
     * to spawn a LaunchArrow to put it halfway between them.
     */
    private void drawArrow()
    {
      Ball ball = getWorld().getObjects(Ball.class).get(0);

      Vector vectToBall = vectorBetweenPoints(getX(), getY(), ball.getExactX(), ball.getExactY());

      double dx = vectToBall.getX();
      double dy = vectToBall.getY();

      getWorld().addObject(new LaunchArrow(dx, dy), (int) (getX() + (dx / 2)), (int) (getY() + (dy / 2)));
    }
}
