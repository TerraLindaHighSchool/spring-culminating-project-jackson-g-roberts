
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Parent class for all objects that need to collide with
 * each other in a rigid body physics system.
 *
 * @author Emerson Coskey
 *
 * @version 0.1
 */
public abstract class Collider extends SmoothMover
{
    private final double RADIUS;
    private final double MASS;
    private final Material MATERIAL;
    private final double RESTITUTION;
    private final double INV_MASS;

    /**
     * Default Collider object, with default radius and mass, and material GRASS.
     */
    public Collider()
    {
        this(1.0, 1.0, Material.GRASS);
    }

    /**
     * Creates a collider with specified radius, mass, and material.
     */
    public Collider(double radius, double mass, Material material)
    {
        this.RADIUS = radius;
        this.MASS = mass;
        this.MATERIAL = material;
        this.RESTITUTION = materialToRestitution(material);
        if(mass == 0)
        {
            this.INV_MASS = 0;
        } else
        {
            this.INV_MASS = 1.0 / MASS;
        }
    }

    /**
     * Returns the radius of the Collider.
     */
    public double getRadius()
    {
        return RADIUS;
    }

    /**
     * Returns the material of the Collider.
     */
    public Material getMaterial()
    {
        return MATERIAL;
    }

    /**
     * Returns the mass of the Collider.
     */
    public double getMass()
    {
        return MASS;
    }

    /**
     * Returns of restitution of the Collider.
     */
    public double getRestitution()
    {
        return RESTITUTION;
    }

    /**
     * Returns the inverse mass of the Collider.
     */
    public double getInvMass()
    {
        return INV_MASS;
    }

    /**
     * Generates the restitution of the Collider based on material.
     */
    private static double materialToRestitution(Material material)
    {
        switch(material)
        {
            case GRASS:
                return 0.4;
            case STONE:
                return 0.8;
            case RUBBER:
                return 1.5;
            case GOLFBALL:
                return 0.7;
            default:
                return 1.0;
        }
    }
}
