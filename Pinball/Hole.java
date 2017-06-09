import java.awt.*;
/**
 * The class contaning the special atributes of the hole object.
 * 
 * @author 804653
 * @version 2017.03.24
 * @see BallObject
 */
public class Hole extends BallObject
{
    /**
     * Constructor for objects of class Hole.
     * 
     * @param xPos  the horizontal coordinate of the object.
     * @param yPos  the vertical coordinate of the object.
     * @param objectColor  the color of the object.
     * @param objectRadius  the radius (in pixels) of the object.
     * @param theMachine  the machine this object is in.   
     */
    public Hole(int xPos, int yPos, Color objectColor, int objectRadius , Machine theMachine)
    {
        super(xPos, yPos, Color.BLACK, objectRadius, theMachine);
        
    }    
       
    /**
     * Draw the hole object on the canvas.          
     */
    public void drawHole()
    {
      machine.draw(this);    
    }
}
