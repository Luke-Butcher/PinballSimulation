import java.awt.*;
/**
 * The super class for all objects that have the properties of a ball.
 * 
 * @author 804653 
 * @version 2017.03.24
 */
public class BallObject
{
    protected int currentXLocation;
    protected int currentYLocation;
    protected double speedXTravel;
    protected double speedYTravel;
    protected Color colour;
    protected int radius;
    protected Machine machine;
    protected boolean collision;
    protected int score;
    protected boolean inHole;
    
    /**
     * Constructor for objects of class ballObject.
     * 
     * @param xPos  The horizontal coordinate of the object.
     * @param yPos  The vertical coordinate of the object.
     * @param objectColor  The color of the object.
     * @param objectRadius  The radius (in pixels) of the object. 
     * @param theMachine  The machine this object is in.
     */
    public BallObject(int xPos, int yPos, Color objectColor, int objectRadius, Machine theMachine)
    {
        currentXLocation = xPos;
        currentYLocation = yPos;
        colour = objectColor;
        radius = objectRadius;
        machine = theMachine;
        collision = false;
        score = 0;
        
       
    }
    
     /**
     * Get the horizontal position of this object.
     * 
     * @return  Returns the integer value of the object on the X axis.
     */
    public int getXPosition()
    {
        return currentXLocation;
    }
    
    /**
     * Set the horizontal position of this object.
     * 
     * @param newX  The new location of the X coordinate.
     */
    public void setXPosition(int newX)
    {
        currentXLocation = newX;
    }
    
    /**
     * Get the vertical position of this object.
     * 
     * @return  Return the integer value of this object on the Y axis.
     */
    public int getYPosition()
    {
        return currentYLocation;
    }
    
    /**
     * Set the vertical position of this object.
     * 
     * @param newY  The new location of the Y coordinate.
     */
    public void setYPosition( int newY)
    {
        currentYLocation = newY;
    }
    
    /**
     * Return  The radius of this object.
     * 
     * @return  Return the integer value for the radius of this object.
     */
    public int getRadius()
    {
        return radius;
    }
    
    /**
     * Return  The diameter of this object.
     * 
     * @return  Multiply the radius by 2 and return this value as an integer 
     */
    public int getDiameter()
    {
        return 2*radius;
    }
    
    /**
     * Return object colour.
     * 
     * @return Return the colour of this object as a type Colour.
     */
    public Color getColor()
    {
        return colour;        
    }
    
    /**
     * Set the colour of this object.
     * 
     * @param newColour  The new colour of the objectv of type Color.
     */
    public void setColor(Color newColour)
    {
        colour = newColour;
    }
    
    /**
     * Set the collision state of this object.
     * 
     * @param newCollision  The new collision state of the object, true or false.
     */
    public void setCollision(boolean newCollision)
    {
        collision = newCollision;
    }
    
    /**
     * Set the radius of this object.
     * 
     * @param newRadius  The new radius of the object as an integer.
     */
    public void setRadius(int newRadius)
    {
        radius = newRadius;
    }
    
    /**
     * Set the state of this object to in the hole or not in a hole.
     * 
     * @param newInHole  Set the in hole state of the object
     */
    public void setInHole(boolean newInHole)
    {
        inHole = newInHole;
    }
    
    /**
     * The state of this object in the hole or not in a hole.
     * 
     * @return Return the state of this object truie or false in a hole.
     */
    public boolean inHole()
    {
       return inHole;
    }
    
    /**
     * Return the object score.
     * 
     * @return  Return the score for this object as a integer.
     */
    public int getScore()
    {
        return score;
    }
    
    /**
     * Set the score to this object.
     * 
     * @param newScore The new score of this object.
     */
    public void setScore(int newScore)
    {
        score = newScore;
    }    
}
