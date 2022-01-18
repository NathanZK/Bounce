package bounce;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class Level1 extends Levels {
	
	/*
	 * The constructor determines the coordinates of the level
	 */

	
	public Level1 () {
		
		passageX= 110;
    	featuresNo(6, 1, 1);
    	
    	int [][] topC= { 
    			{2,1}, {5,1}, {5,5}, {11,5}, {11,1}, {21,1}, {21,5}, {24,5},
    	        {24,1}, {33,1}, {33,2}, {35,2}, {35,1}, {44,1}, {44,5}, {46,5},
    	        {46,2}, {47,2}, {47,1}, {65,1}, {65,2}, {66,2}, {66,5},{68,5}, 
    	        {68,1}, {74,1}, {74,5}, {76,5}, {76,1}, {82,1}, {82,5}, {84,5}, 
    	        {84,1}, {99,1}, {99,5}, {101,5}, {101,1}, {110,1}, {110,5}, {112,5} };
    	
    	int [][] bottomC = { 
    			{2,7}, {14,7}, {14,3}, {16,3}, {16,7}, {26,7}, {26,4}, {30,4}, 
    			{30,5}, {28,5}, {28,7}, {33,7}, {33,5}, {32,5}, {32,4}, {36,4}, 
    			{36,5}, {35,5}, {35,7}, {40,7}, {40,5}, {38,5}, {38,4}, {42,4}, 
    			{42,7}, {70,7}, {70,3}, {72,3}, {72,7}, {78,7}, {78,3}, {80,3}, 
    			{80,7}, {86,7}, {86,3}, {88,3}, {88,7}, {92,7}, {92,5}, {94,5}, 
    			{94,6}, {95,6}, {95,4}, {97,4}, {97,7}, {103,7}, {103,4}, {105,4}, 
    			{105,6},{106,6}, {106,5}, {108,5}, {108,7}, {112,7}
    	}; 
    	
    	topCoordinates =topC;
    	bottomCoordinates =bottomC;
  	
    }
	
	/*
	 * Methods of the superclass are used to draw the features of the level
	 */

	@Override
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		drawBricks (112, 8, g, brickSlected );
		emptyLocation (topCoordinates, bottomCoordinates, g );
		
		spikes = new ArrayList <Rectangle> ();
		hoops = new ArrayList <Rectangle> ();
		checkLocations = new ArrayList <Rectangle> ();
		extraLives = new ArrayList <Rectangle> ();
		
	
		
		int [][] spikeArray = { {17,6}, {30,6}, {36,6}, {51,6}, {54,6},
				                {58,6}, {62,6}, {94,5}, {105, 5}  };
		spikes (spikeArray, g);
		 	
		int [][] hoopArray = { {9,5}, {33,2}, {74,5}, {82,5}, {97,4}, {101, 4}};
		boolean [] hoopOrientation = { true, true, true , true, false, false };
		hoops (hoopArray, hoopOrientation, g);
		
		createCheckPoint(56, 3, g, xPosition, checks.get(0));
		
		extraLifeLocation (79,1, g, xPosition, lives.get(0));
		
		Bounce.bounce.commonFeatures (g);

		passage (g, Bounce.bounce.numLights, passageX, 5, xPosition, Bounce.pImage);
	 
		
		
         }
   

    
}
