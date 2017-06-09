import java.awt.*;
import java.util.*;
import java.lang.Math;
/**
 * A class to control the particular changes of Pinball 3, the random direction change when contacting an edge
 * and changing speed within a given min and max value when coliding with anotehr pinball.
 * 
 * @author 804653
 * @version 2017.03.24
 * @see PinballObject
 */
public class Pinball3 extends PinballObject
{
    private ArrayList<Color> colors;
    private double newY;
    private double newX;
    private double speed;
    private double speedMin;
    private double speedMax;
    
    /**
     * Constructor for objects of class Pinball3
     * 
     * @param xPos  The horizontal coordinate of the object.
     * @param yPos  The vertical coordinate of the object.
     * @param xVel  The horizontal speed of the object.
     * @param yVel  The vertical speed of the object.
     * @param objectColor  The color of the object.
     * @param objectRadius  The radius (in pixels) of the object.
     * @param theMachine  The machine this object is in.
     */
    public Pinball3(int xPos, int yPos, double xVel, double yVel, Color objectColor, int objectRadius, Machine theMachine)
    {
        super(xPos, yPos, xVel, yVel, objectColor, objectRadius, theMachine);
        colors = new ArrayList<Color>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);        
        colors.add(Color.BLUE);        
        speed = Math.sqrt((xVel*xVel) + (yVel*yVel));
        speedMin = 5;
        speedMax = 10;        
    }
    
    /**
     * Call the move method and if hitting a wall call the chageDirection function 
     * if collison is set to true call the change speed function.
     */
    public void move()
    {
        super.move();
        // check if it has hit the leftwall
        if(currentXLocation <= (leftWallPosition + radius)) 
        {
            currentXLocation = leftWallPosition + radius;
            changeDirection();
              
            speedXTravel = -newX; 
            speedYTravel = newY; 
        }
        // check if it has hit the right wall
        if(currentXLocation >= (rightWallPosition - radius)) 
        {
            currentXLocation = rightWallPosition - radius;
            changeDirection();

            speedXTravel = -newX; 
            speedYTravel = newY;             
        }
        // check if it has hit the top wall
        if(currentYLocation <= (topWallPosition + radius)) 
        {
            currentYLocation = topWallPosition + radius ;
            changeDirection();

            speedYTravel = newY; 
            speedXTravel = newX;            
        }
         // check if it has hit the bottom wall
        if(currentYLocation >= bottomWallPosition - radius && currentXLocation - radius < gapLeftX
           || currentYLocation >= bottomWallPosition - radius && currentXLocation + radius > gapRightX)
        {                       
            currentYLocation = bottomWallPosition - radius;
            changeDirection();
            
            speedYTravel = -newY; 
            speedXTravel = newX; 
        }
        
        if(collision)
        {
           machine.erase(this);
           setSpeed(changeSpeed(this));
           
           speedYTravel = -speedYTravel; 
           speedXTravel = -speedXTravel; 
           currentYLocation += speedYTravel;
           currentXLocation += speedXTravel;
           
           this.collision = false;
        }
    }
           
    /**
     * Change the direction of the ball while maintaing its speed by changing the X value 
     * and making the Y value the differance between the speed and the newX value.
     */
    public void changeDirection()
    {     
        Random rand = new Random();   
        int xChangeRange = (int)Math.abs(this.speed);
        if (xChangeRange == 0)
        {
            xChangeRange = 1;
        }
        int xChange = rand.nextInt(xChangeRange);    
        newX = Math.abs(speedXTravel) - xChange; 
        newY = Math.abs(Math.sqrt((speed*speed)-(newX*newX)));         
    }
    
    /**
     *Change the speed of the ball while maintaing its direction by changing the X value and Y value
     * by 50% increase or decrease depending if speed is at the max limit or not.
     * 
     * @param pinball3Object the object to act on as class Pinball3.
     */
    public double changeSpeed(Pinball3 pinball3Object)
    {                 
        if(speed >= speedMax)
        {
            speedYTravel = -(speedYTravel /1.5); 
            speedXTravel = -(speedXTravel /1.5); 
         
            return speed - speedMin;
        }else{
            speedYTravel = -(speedYTravel *1.5); 
            speedXTravel = -(speedXTravel *1.5); 
                      
            return speed + speedMin;
        }
    }
        
    /**
     * Set the speed of motion for this object.
     * @param newSpeed the new speed of the object as a double.
     */
     public void setSpeed(double newSpeed)
    {
        speed = newSpeed; 
    }
}
    
