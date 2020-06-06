import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Planet, collides with the Ball and has an
 * affector to pull the Ball.
 *
 * @author Jackson Roberts
 *
 * @version 2.0
 */
public class Planet extends Collider
{
    private GreenfootImage image;
    private Affector affector;

    /**
     * Creates a default planet, with an empty sprite and default values.
     */
    public Planet()
    {
        this(50, 1.0, Material.GRASS, Color.RED);
    }

    /**
     * Creates a new planet while constructing the collider superclass with relevant parameters.
     */
    public Planet(double radius, double mass, Material material, Color color)
    {
        super(radius, mass, material);
        image = new GreenfootImage((int) radius * 2, (int) radius * 2);
        image.setColor(color);
        image.fillOval(0, 0, (int) radius * 2, (int) radius * 2);

        setImage(image);
    }
}
