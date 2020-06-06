import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

/**
 * A variation of an actor that maintains precise location (using doubles for the co-ordinates
 * instead of ints). It also maintains a current velocity in form of a velocity vector.
 *
 * This is a variation of the SmoothMover class presented ealier in the book (version 2.0).
 * This version implements wrap-around movement: when the actor moves out of the world at one
 * side, it enters it again at the opposite edge.
 *
 * This has also now been modified to have methods for generating a vector between points,
 * and working with affectors and gravitational forces, as well as collisions between objects
 *
 * @author Poul Henriksen
 * @author Michael Kölling
 *
 * @author Jackson Roberts
 * @author Emerson Coskey
 *
 * @version 3.5
 */
public abstract class SmoothMover extends Actor
{
    private Vector velocity;

    private double exactX;
    private double exactY;

    public SmoothMover()
    {
        this(new Vector());
    }

    /**
     * Create new Mover initialised with given velocity.
     */
    public SmoothMover(Vector velocity)
    {
        this.velocity = velocity;
    }

    /**
     * Move in the direction of the velocity vector. This simulates movement in one
     * time unit (dt==1). Wrap around to the opposite edge of the screen if moving out of the world.
     */
    public void move()
    {
        exactX = exactX + velocity.getX();
        exactY = exactY + velocity.getY();
        if (exactX >= getWorld().getWidth()) {
            exactX = 0;
        }
        if (exactX < 0) {
            exactX = getWorld().getWidth() - 1;
        }
        if (exactY >= getWorld().getHeight()) {
            exactY = 0;
        }
        if (exactY < 0) {
            exactY = getWorld().getHeight() - 1;
        }
        super.setLocation((int) exactX, (int) exactY);
    }

    /**
     * Set the location using exact (double) co-ordinates.
     */
    public void setLocation(double x, double y)
    {
        exactX = x;
        exactY = y;
        super.setLocation((int) x, (int) y);
    }

    /**
     * Set the location of this actor. Redefinition of the standard Greenfoot
     * method to make sure the exact co-ordinates are updated in sync.
     */
    public void setLocation(int x, int y)
    {
        exactX = x;
        exactY = y;
        super.setLocation(x, y);
    }

    /**
     * Return the exact x-coordinate (as a double).
     */
    public double getExactX()
    {
        return exactX;
    }

    /**
     * Return the exact y-coordinate (as a double).
     */
    public double getExactY()
    {
        return exactY;
    }

    /**
     * Modify velocity by adding another velocity vector.
     */
    public void addToVelocity(Vector boost)
    {
        velocity.add(boost);
    }

    public void subtractFromVelocity(Vector other)
    {
        velocity.subtract(other);
    }

    /**
     * Accelerate the speed of this mover by the given factor. (Factors less than 1 will
     * decelerate.) The direction remains unchanged.
     */
    public void accelerate(double factor)
    {
        velocity.scale(factor);
        if (velocity.getLength() < 0.15)
        {
            velocity.setNeutral();
        }
    }

    /**
     * Return the speed of this actor.
     */
    public double getSpeed()
    {
        return velocity.getLength();
    }

    /**
     * Revert velocity horizontally.
     */
    public void invertHorizontalVelocity()
    {
        velocity.revertHorizontal();
    }

    /**
     * Revert velocity vertically.
     */
    public void invertVerticalVelocity()
    {
        velocity.revertVertical();
    }

    /**
     * Return the current speed.
     */
    public Vector getVelocity()
    {
        return velocity.copy();
    }

    /**
     * Moves to the position of the mouse.
     */
    public void moveToMouse()
    {
      MouseInfo m = Greenfoot.getMouseInfo();
      if (m != null)
      {
          setLocation(m.getX(), m.getY());
      }
    }

    /**
     * Returns a vector between two specified points.
     */
    public Vector vectorBetweenPoints (double x1, double y1, double x2, double y2)
    {
        double dx = x2 - x1;
        double dy = y2 - y1;

        return new Vector(dx, dy);
    }

    /**
     * Returns the relevant (touching) affectors around a given SmoothMover.
     */
    public List getRelevantAffectors(SmoothMover actor)
    {
        List<Affector> allAffectors = getWorld().getObjects(Affector.class);
        List<Affector> relevantAffectors = new ArrayList<Affector>();
        for (Affector affector : allAffectors)
        {
            switch (affector.getType())
            {
                case "radial":
                    Vector vectToAffector = vectorBetweenPoints(getExactX(), getExactY(), affector.getExactX(), affector.getExactY());
                    double dist = vectToAffector.getLength();
                    if (dist <= affector.getRadius()) relevantAffectors.add(affector);
                    break;
                case "linear":
                    if (getIntersectingObjects(Affector.class).contains(affector)) relevantAffectors.add(affector);
                    break;
                default:
                    System.out.println("something has gone horribly wrong, the affectors are not right :(");
                    break;
            }
        }
        return relevantAffectors;
    }

    /**
     * Uses the relevant affectors to apply gravitational or linear forces
     * to a SmoothMover.
     */
    public void applyAffectorForce()
    {
        List<Affector> relevantAffectors = getRelevantAffectors(this);
        Vector affectorSum = new Vector();
        for (Affector affector : relevantAffectors)
        {
            Vector vectToAffector = vectorBetweenPoints(getExactX(), getExactY(), affector.getExactX(), affector.getExactY());

            double distToAffector = vectToAffector.getLength();
            int rotToAffector = vectToAffector.getDirection();

            if (affector.getType().equals("radial"))
            {
                affectorSum.add(new Vector(rotToAffector, (affector.getForce() / (distToAffector * distToAffector))));
            } else
            {
                affectorSum.add(new Vector(affector.getDirection(), affector.getForce()));
            }
        }
        addToVelocity(affectorSum);
    }

    /**
     * Method for determining whether two Collider circles intersect.
     */
    public CollisionData circleVsCircle(Collider a, Collider b)
    {
        double collideDistance = a.getRadius() + b.getRadius();
        if (collideDistance > vectorBetweenPoints(a.getExactX(), a.getExactY(), b.getExactX(), b.getExactY()).getLength())
        {
            return new CollisionData(a, b);
        }
        return null;
    }

    /**
     * Takes in a CollisionData, and uses it to resolve collision, making
     * sure the objects bounce apart.
     */
    public void resolveCollision(CollisionData cd)
    {
        Collider a = cd.getColliderA();
        Collider b = cd.getColliderB();
        double velAlongNormal = cd.getRelativeVelocity().getDotProduct(cd.getCollisionNormal());

        if (velAlongNormal > 0)
        {
          return;
        }

        double restitution = Math.min(a.getRestitution(), b.getRestitution());

        double impulseMagnitude = -(1 + restitution) * velAlongNormal;
        impulseMagnitude /= a.getInvMass() + b.getInvMass();

        Vector impulse = new Vector(cd.getCollisionNormal().getDirection(), impulseMagnitude);
        a.subtractFromVelocity(new Vector(impulse.getDirection(), a.getInvMass() * impulse.getLength()));
        b.addToVelocity(new Vector(impulse.getDirection(), b.getInvMass() * impulse.getLength()));
    }

    /**
     * Method for looping over all other colliders and resolving the collisions
     * of those that are intersecting.
     */
    public void manageCollisions()
    {
        List<Collider> colliders = getWorld().getObjects(Collider.class);
        colliders.remove((Collider) this);

        for (Collider other : colliders)
        {
            if (circleVsCircle((Collider) this, other) != null)
            {
                CollisionData collisionData = circleVsCircle((Collider) this, other);
                resolveCollision(collisionData);
                correctPosition(collisionData);
            }

        }
    }

    /**
     * Corrects for the error in floating point positions.
     */
    private void correctPosition(CollisionData cd)
    {
        double percent = 0.2;
        double slop = .1;
        Vector normal = cd.getCollisionNormal();
        normal.scale(Math.max(cd.getCollisionDepth() - slop, 0.0) / (cd.getColliderA().getInvMass() + cd.getColliderB().getInvMass()) * percent);
        Vector correction = normal;
        setLocation(correction.getX() + getExactX(), correction.getY() + getExactY());
    }

    /**
     * Class for storing data of an object collision.
     * 
     * @author Emerson Coskey
     * 
     * @version 1.0
     */
    private class CollisionData
    {
        private Collider a;
        private Collider b;
        private Vector relativeVelocity;
        private Vector collisionNormal;
        private double collisionDepth;

        /**
         * Creates a CollisionData with two Collider obejcts.
         */
        public CollisionData(Collider a, Collider b)
        {
            this.a = a;
            this.b = b;
            Vector rv = b.getVelocity().copy();
            rv.subtract(a.getVelocity());
            this.relativeVelocity = rv;

            Vector relativePos = vectorBetweenPoints(a.getExactX(), a.getExactY(), b.getExactX(), b.getExactY());
            this.collisionDepth =  (a.getRadius() + b.getRadius()) - relativePos.getLength();

            relativePos.setLength(1.0);
            this.collisionNormal = relativePos;
        }

        /**
         * Returns the first Collider, A.
         */
        private Collider getColliderA()
        {
            return a;
        }

        /**
         * Returns the second Collider, B.
         */
        private Collider getColliderB()
        {
            return b;
        }

        /**
         * Returns the current relative velocity between the objects.
         */
        private Vector getRelativeVelocity()
        {
            return relativeVelocity.copy();
        }

        /**
         * Returns the collision normal of the two objects.
         */
        private Vector getCollisionNormal()
        {
            return collisionNormal.copy();
        }

        /**
         * Returns the collision depth of the two objects.
         */
        private double getCollisionDepth()
        {
            return collisionDepth;
        }
    }
}
