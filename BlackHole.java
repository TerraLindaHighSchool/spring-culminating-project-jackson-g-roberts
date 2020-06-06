import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Black Hole, sucks in the ball and ends the game.
 *
 * @author Jackson Roberts
 * @author Jack Blatcher
 *
 * @version 0.7
 */
public class BlackHole extends SmoothMover
{
     private GreenfootImage blackhole1;
     private GreenfootImage blackhole2;
     private GreenfootImage blackhole3;
     private GreenfootImage blackhole4;
     private GreenfootImage blackhole5;
     private GreenfootImage blackhole6;
     private GreenfootImage blackhole7;
     private GreenfootImage blackhole8;
     private GreenfootImage blackhole9;
     private GreenfootImage blackhole10;
     private GreenfootImage blackhole11;

     private int currentAnimFrame;
     private int frameCount;
     private Affector affector;

     private double mRadius, mMass;

     /**
      * Default constructor, assigns radius and mass default values.
      */
     public BlackHole()
     {
       this(50, 1.0);
     }

     /**
      * Standard constructor, takes in radius and mass, and initalizes and scales images.
      */
     public BlackHole(double radius, double mass)
     {
       blackhole1 = new GreenfootImage("blackhole1.png");
       blackhole2 = new GreenfootImage("blackhole2.png");
       blackhole3 = new GreenfootImage("blackhole3.png");
       blackhole4 = new GreenfootImage("blackhole4.png");
       blackhole5 = new GreenfootImage("blackhole5.png");
       blackhole6 = new GreenfootImage("blackhole6.png");
       blackhole7 = new GreenfootImage("blackhole7.png");
       blackhole8 = new GreenfootImage("blackhole8.png");
       blackhole9 = new GreenfootImage("blackhole9.png");
       blackhole10 = new GreenfootImage("blackhole10.png");
       blackhole11 = new GreenfootImage("blackhole11.png");

       mRadius = radius;
       mMass = mass;

       blackhole1.scale(128,128);
       blackhole2.scale(128,128);
       blackhole3.scale(128,128);
       blackhole4.scale(128,128);
       blackhole5.scale(128,128);
       blackhole6.scale(128,128);
       blackhole7.scale(128,128);
       blackhole8.scale(128,128);
       blackhole9.scale(128,128);
       blackhole10.scale(128,128);
       blackhole11.scale(128,128);
       setImage(blackhole1);
     }

     /**
      * Checks collisions for touching the ball, and then animates.
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
            getWorldOfType(Space.class).gameOver("Black Hole");
        }
     }

     /**
      * Loops through the 11 different animation frames.
      */
     public void animate()
     {
       if(frameCount % 8 == 0)
       {
         if(currentAnimFrame >= 10)
         {
           currentAnimFrame = 0;
         } else {
             currentAnimFrame++;
         }
       }

       switch (currentAnimFrame)
       {
         case 0:
             setImage(blackhole1);
             break;
         case 1:
             setImage (blackhole2);
             break;
         case 2:
             setImage (blackhole3);
             break;
         case 3:
             setImage (blackhole4);
             break;
         case 4:
             setImage (blackhole5);
             break;
         case 5:
             setImage (blackhole6);
             break;
         case 6:
             setImage (blackhole7);
             break;
         case 7:
             setImage (blackhole8);
             break;
         case 8:
             setImage (blackhole9);
             break;
         case 9:
             setImage (blackhole10);
             break;
         case 10:
             setImage (blackhole11);
             break;
         default:
             setImage(blackhole1);
             break;
       }

       frameCount++;

     }
}
