import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ball, affected by gravity and collisions.
 * Hit by the player around the game.
 *
 * @author Jackson Roberts
 *
 * @version 1.7
 */
public class Ball extends Collider
{
    private static final double BALL_RADIUS = 8.0;

    private GreenfootImage image;

    /**
     * Creates a new Ball with a material in Collider, and sets and scales the
     * image to a set radius.
     */
    public Ball()
    {
        super(BALL_RADIUS, 4.0, Material.GOLFBALL);

        image = new GreenfootImage("ball.png");
        image.scale((int) BALL_RADIUS * 2, (int) BALL_RADIUS * 2);
        setImage(image);
    }

    /**
     * Applies gravitational forces and then moves based on those forces.
     */
    public void act()
    {
        applyAffectorForce();
        manageCollisions();
        move();
    }

    /**
     * Launches the Ball in a specified direction, with a specified force.
     */
    public void launch(int direction, double force)
    {
        addToVelocity(new Vector(direction, -force));
        getWorldOfType(Space.class).incrementStrokes();
    }

    /**
     * Returns the radius of the ball.
     */
    public double getRadius()
    {
        return BALL_RADIUS;
    }
}
