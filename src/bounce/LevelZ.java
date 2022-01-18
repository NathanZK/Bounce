package bounce;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;


public class LevelZ extends Levels {
	
	

	public LevelZ () {
    	
    	int [][] topC= { 
    			{2,1}, {21,1}, {21,7} };
    	
    	int [][] bottomC = { 
    			{2,7}, {7,7}, {11,3}, {15,7}, {21,7}
    	}; 
    	
    	topCoordinates =topC;
    	bottomCoordinates =bottomC;
    	
   
    	
    }

	@Override
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		drawBricks (23, 8, g, brickSlected );
		emptyLocation (topCoordinates, bottomCoordinates, g );
		
		spikes = new ArrayList <Rectangle> ();
		hoops = new ArrayList <Rectangle> ();
		checkLocations = new ArrayList <Rectangle> ();
		extraLives = new ArrayList <Rectangle> ();
		
		
		Bounce.bounce.commonFeatures (g);
		
	
	
         }
   

    
}
