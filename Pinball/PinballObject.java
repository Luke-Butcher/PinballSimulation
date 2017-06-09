import java.awt.*;
import java.lang.Math;

/**
 * An object that exists in the pinball class, this is the super class for all types of pinball
 * extending the functionality of the ball object class.
 * 
 * Movement can be initiated by repeated calls to the "move" method.
 *  
 * @author 804653
 * @author Claire Ancient 
 * @version 2017.03.24
 * @see BallObject 
 */

public class PinballObject extends BallObject
{   
    protected int collisionX;
    protected int collisionY;
    protected int collisionRadius;
    protected final int leftWallPosition;
    protected final int rightWallPosition;
    protected final int topWallPosition;
    protected final int bottomWallPosition;
    protected int gapLeftX;
    protected int gapRightX;    
    protected double speedXTravel;
    protected double speedYTravel;
    protected double distance;   
  
    /**
     * Constructor for objects of class PinballObject.
     * 
     * @param xPos  The horizontal coordinate of the object.
     * @param yPos  The vertical coordinate of the object.
     * @param xVel  The horizontal speed of the object.
     * @param yVel  The vertical speed of the object.
     * @param objectColor  The color of the object.
     * @param objectRadius  The radius (in pixels) of the object.     
     * @param theMachine  The machine this object is in.
     */
    public PinballObject(int xPos, int yPos, double xVel, double yVel, Color objectColor, int objectRadius, Machine theMachine)
    {
        super(xPos, yPos, objectColor, objectRadius, theMachine);
        speedXTravel = xVel;
        speedYTravel = yVel;
        leftWallPosition = machine.getLeftWall();
        rightWallPosition = machine.getRightWall();
        topWallPosition = machine.getTopWall();
        bottomWallPosition = machine.getBottomWall();  
        gapLeftX = machine.getGapLeftX(); // The lefthand side x of the gap
        gapRightX = machine.getGapRightX(); // The righthand side x of the gap
        inHole = false;
    }

    /**
     * Move this object according to its position and speed then redraw it 
     * if the machine is still running and the object is not in a hole.
     */
    public void move()
    {
        if(machine.running && !inHole)
        {        
            //check to see if collision state is true
            if(machine.ballCollisionsOn)
            {  
                ballCollisionCheck(this);
            }
            holeCheck(this);
            bumperCollisionCheck(this);
            
            // remove from universe at the current position
            machine.erase(this);
            
            // compute new position
            currentYLocation += speedYTravel;
            currentXLocation += speedXTravel;  
            
            // move the balls away from eachother in the correct direction based on travel direction
            if (collision)
            {
                if  (speedXTravel <= 0)
                {             
                    currentXLocation += Math.abs(radius);                
                }
                if(speedXTravel >= 0)
                {                   
                    currentXLocation -= Math.abs(radius);                
                }        
                if  (speedYTravel <= 0)
                {
                    currentYLocation += Math.abs(radius);
                }                   
                if( speedYTravel >= 0)
                {
                    currentYLocation -= Math.abs(radius);
                }
            
                speedYTravel = -speedYTravel;
                speedXTravel = -speedXTravel;
                score += 5;
            }           
            //check if it has hit the left wall
            if(currentXLocation <= (leftWallPosition + radius)) 
            {
                currentXLocation = leftWallPosition + radius;
                speedXTravel = -speedXTravel; 
                score += 1;
            }
          
            if(currentXLocation >= (rightWallPosition - radius)) 
            {
                currentXLocation = rightWallPosition - radius;
                speedXTravel = -speedXTravel; 
                score += 1;
            }
           
            if(currentYLocation <= (topWallPosition + radius)) 
            {
                currentYLocation = topWallPosition + radius;
                speedYTravel = -speedYTravel; 
                score += 1;
            }        
            //  check if it has hit gap
            if(currentYLocation - radius > bottomWallPosition)
            {
                machine.running = false;
                machine.erase(this);                
            } 
            //  check if it has hit bottom walls
            if(currentYLocation >= bottomWallPosition - radius && currentXLocation - radius < gapLeftX
             || currentYLocation >= bottomWallPosition - radius && currentXLocation + radius > gapRightX)
            {                                  
                currentYLocation = bottomWallPosition - radius;
                speedYTravel = -speedYTravel; 
                score += 1;
            }             
            // draw again at new position
            if (machine.running && !inHole())
            {
                machine.draw(this); 
            }
            machine.drawMachine();                 
          }
     }
    
    /**
     * Check to see if the ball has collided with a bumper object 
     * and change the speed and score if it has done so.
     * 
     * @param the object to act on as class PinballObject.
     */
    public void bumperCollisionCheck(PinballObject pinball)
    {
        for (int i = 0; i< machine.bmprObjects.size(); i++)
        {
            collisionX = Math.abs(currentXLocation - machine.bmprObjects.get(i).getXPosition());
            collisionY = Math.abs(currentYLocation - machine.bmprObjects.get(i).getYPosition());
            collisionRadius = machine.bmprObjects.get(i).getRadius() + pinball.getRadius();

            if(Math.abs(collisionX * collisionX) + Math.abs(collisionY * collisionY) <= Math.abs(collisionRadius * collisionRadius))
            {   machine.erase(pinball);
                distance = Math.sqrt(  (collisionX * collisionX) + (collisionY * collisionY));

                speedYTravel = -speedYTravel;
                speedXTravel = -speedXTravel;
                score += 2;           
            }
        }
    }
    
    /**
     * Check to see if the ball has collided with another pinball object 
     * and set the collision state to true if it has done so.
     * 
     * @param the object to act on as class PinballObject.
     */
    public void ballCollisionCheck(PinballObject pinball)
    {   
        for(int i = 0 ; i < machine.pinballObjects.size() ; i++)
        {
            collisionX = Math.abs(currentXLocation - machine.pinballObjects.get(i).getXPosition());
            collisionY = Math.abs(currentYLocation - machine.pinballObjects.get(i).getYPosition());
            collisionRadius = pinball.getRadius() + machine.pinballObjects.get(i).getRadius();
            
             if (currentXLocation != machine.pinballObjects.get(i).getXPosition() 
                && currentYLocation != machine.pinballObjects.get(i).getYPosition() 
                || currentXLocation == machine.pinballObjects.get(i).getXPosition() 
                && currentYLocation != machine.pinballObjects.get(i).getYPosition() 
                || currentXLocation != machine.pinballObjects.get(i).getXPosition() 
                && currentYLocation == machine.pinballObjects.get(i).getYPosition())
                {
                    if(Math.abs(collisionX * collisionX) + Math.abs(collisionY * collisionY) 
                        <= Math.abs(collisionRadius * collisionRadius))
                    {   
                         if (machine.pinballObjects.get(i).inHole())
                         {                                                
                           machine.pinballObjects.get(i).setCollision(false);
                         }else if(pinball.inHole()){
                           pinball.setCollision(false);   
                         }else{
                           machine.pinballObjects.get(i).setCollision(true);
                           pinball.setCollision(true);
                           distance = Math.sqrt((collisionX * collisionX) + (collisionY * collisionY));
                    }                        
                }
            }                      
        }            
    }
     
    /**
     * Check to see if the ball has collided with a hole object 
     * and change score if it has done so also remove it
     * if the ball is small enough to fit inside the hole.
     * 
     * @param the object to act on as class PinballObject.
     */
    public void holeCheck(PinballObject pinball)
    {
        for (int i = 0; i< machine.holeObjects.size(); i++)
        {
            collisionX = Math.abs(currentXLocation - machine.holeObjects.get(i).getXPosition());
            collisionY = Math.abs(currentYLocation - machine.holeObjects.get(i).getYPosition());
            collisionRadius = machine.holeObjects.get(i).getRadius() + pinball.getRadius();
    
            if(Math.abs(collisionX * collisionX) + Math.abs(collisionY * collisionY)
                <= Math.abs(collisionRadius * collisionRadius) 
                && machine.holeObjects.get(i).getRadius() >  pinball.getRadius())
                {                                     
                   speedYTravel = 0;
                   speedXTravel = 0;
                                                             
                   machine.erase(pinball);
                   setRadius(0);
                   currentXLocation = 1;
                   currentXLocation = 1; 
                   
                   setInHole(true);
                   score = -1;                         
                }else if(Math.abs(collisionX * collisionX) + Math.abs(collisionY * collisionY) <= Math.abs(collisionRadius * collisionRadius)){
                   score = 0;
            }
        }        
    }  
}

