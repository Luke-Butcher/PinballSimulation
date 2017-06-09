import java.awt.*;
import java.util.*;
/**
 * Creates a pinball with the unique propioties of chaging colour when it hits a wall
 * and changing its size by 10% when hitting another ball.
 * 
 * @author 804653
 * @version 2017.03.24
 * @see PinballObject
 */
public class Pinball0 extends PinballObject
{
    private ArrayList<Color> colors;
    private final int initialRadius;
    /**
     * Constructor of class Pinball0.
     **/
    public Pinball0(int xPos, int yPos, double xVel, double yVel, Color objectColor, int objectRadius, Machine theMachine)
    {
        super(xPos, yPos, xVel, yVel, objectColor, objectRadius, theMachine);
        colors = new ArrayList<Color>();
        colors.add(Color.CYAN);
        colors.add(Color.MAGENTA);        
        colors.add(Color.YELLOW);  
        initialRadius = objectRadius;               
    }
   
    /**
     * Move this object according to its position and speed and redraw using the super class 
     * then apply the specific pinball0 functions.
     **/
    public void move()
    {
        super.move();
    
        if(currentXLocation <= (leftWallPosition + radius) || currentXLocation >= (rightWallPosition - radius)
             || currentYLocation <= (topWallPosition + radius) 
             || currentYLocation >= bottomWallPosition - radius && currentXLocation - radius < gapLeftX
             || currentYLocation >= bottomWallPosition - radius && currentXLocation + radius > gapRightX)  
        {         
            super.move();
            this.setColor(colourChange(this));
        } 
        
        if(collision)
        {
           machine.erase(this);
           changeSize(this);
           this.collision = false;
        }             
    }
        
    /**
     * Change the colour of the pinball using its present colour to identify the 
     * index it is now and looping through the color arrayList.
     * 
     * @param pinball0Object  The object to act on as class Pinball0.
     **/
    public Color colourChange(Pinball0 pinball0Object)
    {    
        Color startColour = pinball0Object.getColor() ;
        int startColourindex = colors.indexOf(startColour);
        if (startColourindex < colors.size()-1)
        {
        return colors.get(startColourindex + 1);
        }else{    
         return colors.get(0);       
        }
    }       
            
    /**
     * Chanage the size of the pinball by 10% based on its initial size compared to its current size.
     * 
     *  @param pinball0Object  The object to act on as class Pinball0.
     **/
    public void changeSize(Pinball0 pinball0Object)
    {          
       int currentRadius = pinball0Object.getRadius(); 
       double newRadius = 0;
       if (currentRadius > initialRadius)
       {
           newRadius = initialRadius - (initialRadius*0.1);   
       }else if(currentRadius <= initialRadius) {
           newRadius = initialRadius + (initialRadius*0.1);
       }       
       setRadius((int)newRadius);
    }
}


