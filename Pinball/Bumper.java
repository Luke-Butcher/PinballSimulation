import java.awt.*;
/**
 * Class Bumper - a class to create and maintain the specific functionality of the bumper objects.
 * 
 * @author 804653 
 * @version 2017.03.24
 * @see BallObject
 */
public class Bumper extends BallObject
{
    /**
     * Constructor for objects of class Bumper.
     * 
     * @param xPos  The horizontal coordinate of the object.
     * @param yPos  The vertical coordinate of the object.
     * @param objectColor  The colour of the object.
     * @param objectRadius  The radius (in pixels) of the object.
     * @param theMachine  The machine this object is in.
     **/
    public Bumper(int xPos, int yPos, Color objectColor, int objectRadius,Machine theMachine)
    {
        super(xPos, yPos, Color.GRAY, objectRadius, theMachine);         
    }
    
    /**
     * Draw the bumper on the canvas.
     **/
    public void drawBumper()
    {
        machine.draw(this);    
    }
}
