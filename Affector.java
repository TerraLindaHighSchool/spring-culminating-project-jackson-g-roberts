import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Affector fields, that apply forces to SmoothMovers inside them.
 * Linear affectors are rectangles that apply a linear force to objects.
 * Radial affectors are circular gravity fields, like around planets.
 *
 * @author Jackson Roberts
 * @author Emerson Coskey
 *
 * @version 2.0
 */
public class Affector extends SmoothMover
{
    private Actor mParentObject;
    private String type;

    private GreenfootImage image;
    private Color mColor;
    private double mForce;

    private int mRadius;

    private int mWidth, mHeight, mDirection;

    /**
     * Creates a radial affector with a parent object, color, radius, and force.
     */
    public Affector(Actor parentObject, Color color, int radius, double force)
    {
        type = "radial";
        mParentObject = parentObject;
        mColor = color;
        mRadius = radius;
        mForce = force;
    }

    /**
     * Creates a linear affector with a parent object, color, width, height,
     * direction, and force.
     */
    public Affector(Actor parentObject, Color color, int width, int height, int direction, double force)
    {
        type = "linear";
        mParentObject = parentObject;
        mColor = color;
        mWidth = width;
        mHeight = height;
        mDirection = direction;
        mForce = force;
    }

    /**
     * Updates the image.
     */
    public void act()
    {
        updateImage();
    }

    /**
     * Returns the parent object of the affector.
     */
    public Actor getParentObject()
    {
        return mParentObject;
    }

    /**
     * Returns the type of the affector, radial or linear.
     */
    public String getType()
    {
        return type;
    }

    /**
     * Returns the force of the affector.
     */
    public double getForce()
    {
        return mForce;
    }

    /**
     * Returns the radius of a radial affector.
     */
    public int getRadius()
    {
        return mRadius;
    }

    /**
     * Returns the width of a linear affector.
     */
    public int getWidth()
    {
        return mWidth;
    }

    /**
     * Returns the height of a linear affector.
     */
    public int getHeight()
    {
        return mHeight;
    }

    /**
     * Returns the direction of a radial affector.
     */
    public int getDirection()
    {
        return mDirection;
    }

    /**
     * Updates the image of the affector. If radial, sets it to a transparent
     * circle of the specified color. If linear, sets it to a transparent 
     * rectangle of the specified color.
     */
    private void updateImage()
    {
        switch (type)
        {
            case "radial":
                image = new GreenfootImage(mRadius * 2, mRadius * 2);

                image.setColor(mColor);
                image.fillOval(0, 0, mRadius * 2, mRadius * 2);
                image.setTransparency(127);
                break;
            case "linear":
                image = new GreenfootImage(mWidth, mHeight);

                image.setColor(mColor);
                image.fillRect(0, 0, mWidth, mHeight);
                image.setTransparency(127);
                break;
        }
        setImage(image);
    }
}
