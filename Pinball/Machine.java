import java.awt.*;
import java.util.*;

/**
 * A pinball machine.
 * 
 * @author 804653
 * @author Claire Ancient 
 * @version 2017.03.24
 */
public class Machine
{    
    private Canvas machine;
    private int topEdge = 0;
    private int leftEdge = 0;
    protected int bottomEdge;
    private int rightEdge;
    protected int lengthToGap; // The distance between the edge of the machine and the start of the gap.
    private int gapWidth = 50;
    protected Boolean running;
    protected boolean ballCollisionsOn;
    protected ArrayList<BallObject> pinballObjects;
    protected ArrayList<BallObject> bmprObjects;
    protected ArrayList<BallObject> holeObjects;
    
    /**
     * Create a machine with default name and size
     */
    public Machine()
    {
        machine = new Canvas("Pinball Demo", 600, 500);
        rightEdge = 600;
        bottomEdge = 500;
        lengthToGap = (rightEdge / 2) - gapWidth;
        pinballObjects = new ArrayList<BallObject>();
        bmprObjects = new ArrayList<BallObject>();
        holeObjects = new ArrayList<BallObject> ();
        running = true;
        drawMachine();
    }
    
    /**
     *  Create a machine with given name and size.
     *  
     *  @param name The name to give the machine.
     *  @param rightEdge The maximum x coordinate.
     *  @param bottomEdge The maximum y coordinate.
     */
     public Machine(String name, int rightEdge, int bottomEdge)
    {
        machine = new Canvas(name, rightEdge, bottomEdge);
        this.rightEdge = rightEdge;
        this.bottomEdge = bottomEdge;
        lengthToGap = (rightEdge / 2) - gapWidth;
        pinballObjects = new ArrayList<BallObject>();
        bmprObjects = new ArrayList<BallObject>();
        drawMachine();
    }

    /**
     * Erase a PinballObject from the view of the pinball machine.
     * 
     * @param pinballObj The object to be erased.
     */
    public void erase(PinballObject pinballObj)  
    {
        machine.eraseString(Integer.toString(pinballObj.score), pinballObj.getXPosition() -6, pinballObj.getYPosition() + 5);
        machine.eraseCircle(pinballObj.getXPosition() - pinballObj.getRadius(), pinballObj.getYPosition()- pinballObj.getRadius(), pinballObj.getDiameter());      
    }
    
    /**
     * Draw a PinballObject at its current position on to the view of the pinball machine.
     * 
     * @param pinballObj The object to be drawn.
     */
    public void draw(PinballObject pinballObj)
    {                
        machine.setForegroundColor(pinballObj.getColor());
        machine.fillCircle(pinballObj.getXPosition() - pinballObj.getRadius(), pinballObj.getYPosition() - pinballObj.getRadius(), pinballObj.getDiameter());
        machine.setForegroundColor(Color.BLACK);
        Font font = new Font("SCore", 4, 14);
        machine.setFont(font);
        machine.drawString(Integer.toString(pinballObj.score), pinballObj.getXPosition() -6, pinballObj.getYPosition() + 5);
    }
    
    /**
     * Draw a Bumper at its current position on to the view of the pinball machine.
     * 
     * @param bmprObj The object to be drawn.
     */
    public void draw(Bumper bmprObj)
    {
        machine.setForegroundColor(bmprObj.getColor());
        machine.fillCircle(bmprObj.getXPosition() - bmprObj.getRadius(), bmprObj.getYPosition() - bmprObj.getRadius(), bmprObj.getDiameter());
    }
    
    /**
     * Draw a Hole at its current position o nto the view of the pinball machine.
     * 
     * @param holeObj The object to be drawn.
     */
    public void draw(Hole holeObj)
    {
        machine.setForegroundColor(holeObj.getColor());
        machine.fillCircle(holeObj.getXPosition() - holeObj.getRadius(), holeObj.getYPosition() - holeObj.getRadius(), holeObj.getDiameter());
    }
    
    /**
     * Draw the edges of the pinball machine. 
     */
    public void drawMachine()
    {
        machine.setForegroundColor(Color.DARK_GRAY);

        machine.fillRectangle(0, 0, rightEdge, 10);  // top edge
        machine.fillRectangle(0, 0, 10, bottomEdge); // left edge
        machine.fillRectangle(rightEdge - 10, 0, 10, bottomEdge); // right edge
        
        machine.fillRectangle(0, bottomEdge - 10, lengthToGap, 10); // left-hand side of bottom edge
        machine.fillRectangle(rightEdge - lengthToGap, bottomEdge - 10, rightEdge, 10);     // right-hand side of bottom edge
    }
    
    /**
     * Return the edge of the left-hand wall.
     * 
     * @return Return the interior edge of the left hand wall.
     */
    public int getLeftWall()
    {
        return leftEdge + 10;
    }
        
    /**
     * Return the edge of the right-hand wall.
     * 
     * @return Return the interior edge of the right hand wall. 
     */
    public int getRightWall()
    {
        return rightEdge - 10;
    }
        
    /**
     * Return the edge of the top wall.
     * 
     * @return Return the interior edge of the top wall.     
     */
    public int getTopWall()
    {
        return topEdge + 10;
    }
        
    /**
     * Return the edge of the bottom wall.
     * 
     * @return Return the interior edge of the bottom wall.
     */
    public int getBottomWall()
    {
        return bottomEdge - 10;
    }
    
     /**
     * Return X position that is the edge of the gap on the left hand side.
     * 
     * @return Return X position of the left hand side of the gap.
     */
    public int getGapLeftX()
    {
        return lengthToGap;            
    }    
        
     /**
     * Return x position that is the edge of the gap on the right hand side.
     * 
     * @return Return X position of the right hand side of the gap.
     */
    public int getGapRightX()
    {
        return rightEdge - lengthToGap;
    }
    
    /**
     * Introduces a small delay in ball movement, for smooth running.
     */    
    public void pauseMachine()
    {
        machine.wait(50);
    }
    
    /**
     * Resets the machine back to initial view, with no pinballs.
     */
    public void resetMachine()
    {
        machine.erase();
        drawMachine();
        running = true;
    }
        
    /**
     * Set the canvas to black and then draw the final scores for each ball.
     */
    public void displayScore()
    {
        machine.setForegroundColor(Color.BLACK);
        machine.fillRectangle(0, 0, 600, 500);
        
        machine.setForegroundColor(Color.RED);
        Font font2 = new Font("Bold", 1, 24);
        machine.setFont(font2);
        machine.drawString("Scores", 250 , 100);
           
        Font font1 = new Font("notBold", 4, 14);
        for(int i = 0; i < pinballObjects.size();i++)
        { 
           
           BallObject pinballIndex = pinballObjects.get(i);
           int score = pinballIndex.getScore();
           machine.setForegroundColor(pinballIndex.getColor()); 
           machine.setFont(font1);
           machine.drawString("Pinball" + i + "     " +  Integer.toString(score), 250 , 150 + (i*20));
            
        }
    }
    
    /**
     * Set if balls will collied in the simulation or pass through eachother.
     * 
     * @param true false for enabeling collisions.
     */
    public void setBallCollisionsOn(boolean onoff)
    {
         ballCollisionsOn = onoff;
    }
}
