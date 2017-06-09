import java.awt.*;
    
   /**
    * A class to demonstrate functionality of the Pinball machine simulation.
    * 
    * @author 804653
    * @version 2017.03.24
    */
    public class Demo
    {
    private Machine machine;    
    
    /**
     * Constructor for objects of class Demo
     */
    public Demo()
    {
        machine = new Machine();        
    }
    
    /**
     * Demonstrates the walls and bumper interact with the correct pinballs 
     * displaying 3 pinballs of each type a correctly working gap and the bumper. 
     */

    public void functionality1Demo()
    {
        machine.resetMachine();  
        machine.setBallCollisionsOn(false);
    
        Pinball3 obj1 = new Pinball3(200, 250, 7, 8, Color.GREEN, 35, machine);
        Pinball3 obj2 = new Pinball3(40, 210, 10, 0, Color.RED, 20, machine);
        Pinball3 obj3 = new Pinball3(200, 380, 1, 1, Color.BLUE, 15, machine);
        Pinball0 obj4 = new Pinball0(200, 200, 8, 0, Color.CYAN, 60, machine);
        Pinball0 obj5 = new Pinball0(540, 350, 6, 10, Color.MAGENTA, 45, machine);
        Pinball0 obj6 = new Pinball0(350, 420, 10, 8, Color.YELLOW, 20, machine);
        
        Bumper bmpr1 = new Bumper(130, 100, Color.GRAY, 20, machine);
        Bumper bmpr2 = new Bumper(320, 400, Color.GRAY, 10, machine);
                
        machine.pinballObjects.add(obj1);
        machine.pinballObjects.add(obj2);
        machine.pinballObjects.add(obj3);
        machine.pinballObjects.add(obj4);
        machine.pinballObjects.add(obj5);
        machine.pinballObjects.add(obj6);
        
        machine.bmprObjects.add(bmpr1);
        machine.bmprObjects.add(bmpr2);
                
        while(machine.running)
        {    
            machine.pauseMachine();// small delay
            
            obj1.move();               
            obj2.move();
            obj3.move();                             
            obj4.move();                
            obj5.move();             
            obj6.move();   
            
            bmpr1.drawBumper();
            bmpr2.drawBumper();
            
        }   
        machine.pinballObjects.clear();
        machine.bmprObjects.clear();
    }
        
    /**
     * Demonstrate the two types of ball colliding and the correct behaviours. 
     */

    public void functionality2Demo()
    {
        machine.resetMachine();     
        machine.setBallCollisionsOn(true);
        
        Pinball3 obj1 = new Pinball3(100, 100, 0, 3, Color.BLUE, 20, machine);
        Pinball3 obj2 = new Pinball3(100, 300, 0, -3, Color.RED, 20, machine);
        Pinball0 obj3 = new Pinball0(400, 100, 0, -4, Color.CYAN, 20, machine);
        Pinball0 obj4 = new Pinball0(400, 300, 0, 4, Color.MAGENTA, 50, machine);
       
        machine.pinballObjects.add(obj1);
        machine.pinballObjects.add(obj2);
        machine.pinballObjects.add(obj3);
        machine.pinballObjects.add(obj4);
        
        for(int i = 0; i <= 600 && machine.running; i++)
        {
            machine.pauseMachine();
            
            obj1.move();
            obj2.move();            
            obj3.move();
            obj4.move();  
        }   
        
        machine.pinballObjects.clear();      
        machine.pinballObjects.clear();
        machine.bmprObjects.clear();  
    }
    
    /**
     * Demonstrates the hole object interaction. 
     */
    public void functionality3Demo()
    {        
        machine.resetMachine(); 
        machine.setBallCollisionsOn(true);
    
        Pinball0 obj1 = new Pinball0(150, 350, 0, -10, Color.CYAN, 20, machine);
        Pinball3 obj2 = new Pinball3(400, 150, -10, 0, Color.RED, 10, machine);
        
        Hole obj3 = new Hole(150, 150,Color.BLACK, 15, machine);
        
        machine.pinballObjects.add(obj1);
        machine.pinballObjects.add(obj2);
        
        machine.holeObjects.add(obj3);
        
        for(int i = 0; i <= 240  && machine.running; i++)
        {        
            machine.pauseMachine();
            
            obj3.drawHole();
            
            obj1.move();             
            obj2.move();                   
        }
        
        machine.pinballObjects.clear();
        machine.bmprObjects.clear();
        machine.holeObjects.clear();
    }
    
    /**
     * Demonstrates the scoring system. 
     */
    public void functionality4Demo()
    {    
        machine.resetMachine();  
        machine.setBallCollisionsOn(false);
            
        Pinball3 obj1 = new Pinball3(450, 70, -3, -4, Color.GREEN, 35, machine);
        Pinball3 obj2 = new Pinball3(40, 200, 5, 1, Color.RED, 20, machine);
        Pinball3 obj3 = new Pinball3(270, 50, 0, 3, Color.BLUE, 15, machine);
        Pinball0 obj4 = new Pinball0(200, 200, 3, 2, Color.CYAN, 30, machine);
        Pinball0 obj5 = new Pinball0(540, 350, 3, 2, Color.MAGENTA, 45, machine);
        Pinball0 obj6 = new Pinball0(350, 420, 5, 2, Color.YELLOW, 20, machine);
        
        Bumper bmpr1 = new Bumper(130, 100, Color.GRAY, 20, machine);
        Bumper bmpr2 = new Bumper(320, 400, Color.GRAY, 10, machine);
        
        Hole hole1 = new Hole(90, 50, Color.BLACK, 15, machine);
        Hole hole2 = new Hole(500, 150, Color.BLACK, 40, machine);
                
        machine.pinballObjects.add(obj1);
        machine.pinballObjects.add(obj2);
        machine.pinballObjects.add(obj3);
        machine.pinballObjects.add(obj4);
        machine.pinballObjects.add(obj5);
        machine.pinballObjects.add(obj6);
        
        machine.bmprObjects.add(bmpr1);
        machine.bmprObjects.add(bmpr2);
        
        machine.holeObjects.add(hole1);
        machine.holeObjects.add(hole2);              
    
        while(machine.running)
        {
            machine.pauseMachine();
            
            hole1.drawHole();
            hole2.drawHole();
        
            obj1.move();               
            obj2.move();
            obj3.move();                             
            obj4.move();                
            obj5.move();             
            obj6.move();  
                
            bmpr1.drawBumper();
            bmpr2.drawBumper();      
        }   
        
        machine.displayScore();
        machine.pinballObjects.clear();
        machine.bmprObjects.clear();
        machine.holeObjects.clear();
    }
    
    /**
     * Demonstrates all the functionality in one simulation.
     */ 
    public void fullSimulation()
    {    
        machine.resetMachine();  
        machine.setBallCollisionsOn(true);
            
        Pinball3 obj1 = new Pinball3(450, 70, 1, 1, Color.GREEN, 35, machine);
        Pinball3 obj2 = new Pinball3(40, 200, 5, 1, Color.RED, 20, machine);
        Pinball3 obj3 = new Pinball3(270, 50, 0, 3, Color.BLUE, 15, machine);
        Pinball0 obj4 = new Pinball0(200, 200, 3, 2, Color.CYAN, 30, machine);
        Pinball0 obj5 = new Pinball0(540, 350, 3, 2, Color.MAGENTA, 45, machine);
        Pinball0 obj6 = new Pinball0(360, 400, 3, 2, Color.YELLOW, 20, machine);
        
        Bumper bmpr1 = new Bumper(130, 100, Color.GRAY, 20, machine);
        Bumper bmpr2 = new Bumper(320, 400, Color.GRAY, 10, machine);
        
        Hole hole1 = new Hole(90, 50, Color.BLACK, 15, machine);
        Hole hole2 = new Hole(500, 150, Color.BLACK, 40, machine);
                
        machine.pinballObjects.add(obj1);
        machine.pinballObjects.add(obj2);
        machine.pinballObjects.add(obj3);
        machine.pinballObjects.add(obj4);
        machine.pinballObjects.add(obj5);
        machine.pinballObjects.add(obj6);
        
        machine.bmprObjects.add(bmpr1);
        machine.bmprObjects.add(bmpr2);
        
        machine.holeObjects.add(hole1);
        machine.holeObjects.add(hole2);
                
        while(machine.running)
        {
            machine.pauseMachine();
            hole1.drawHole();
            hole2.drawHole();
            
            obj1.move();               
            obj2.move();
            obj3.move();                             
            obj4.move();                
            obj5.move();             
            obj6.move(); 
                
            bmpr1.drawBumper();
            bmpr2.drawBumper();            
        }   
        
        machine.displayScore();
        machine.pinballObjects.clear();
        machine.bmprObjects.clear();
        machine.holeObjects.clear();
    }
}
    
    
