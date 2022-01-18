package bounce;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Level2 extends Levels {
	
	/*
	 * The constructor determines the coordinates of the level
	 */

	public Level2 () {
		
		passageX= 132;
		featuresNo(6, 1, 1);
    	
    	int [][] topC= { 
    			{2,1}, {26,1}, {26,5}, {28,5}, {28,1}, {34,1}, {34,3}, {36,3}, 
    			{36,1},{66,1}, {66,5}, {68,5}, {68,1}, {87,1}, {87,5},{89,5},
    			{89,1}, {98,1}, {98,3}, {100,3}, {100,1}, {114,1}, {114,5},
    			{116,5},{116,1}, {125,1}, {125,4}, {127,4},{127,1}, {130,1},
    			{134,1}
    	};
    	
    	int [][] bottomC = { 
    			{2,7}, {15,7}, {15,3}, {24,3}, {24,5}, {22,5}, {22,4}, {17,4}, 
    			{17,7}, {30,7}, {30,3}, {32,3}, {32,7}, {41,7},{41,3}, {46,3},
    			{46,7}, {73,7}, {73,6}, {74,6}, {74,5}, {75,5}, {75,4},{76,4},
    			{76,3},{78,3}, {78,4}, {79,4}, {79,5}, {80,5}, {80,6}, {81,6},
    			{81,7}, {98,7}, {98,5}, {100,5}, {100,7}, {110,7}, {110,3}, {112,3},
    			{112,7}, {120,7}, {120,5}, {118,5}, {118,4}, {120,4}, {120,3}, {122,3},
    			{122,7}, {132,7}, {132,4}, {129,4}, {129,3}, {134,3}
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
		
		drawBricks (134, 8, g, brickSlected );
		emptyLocation (topCoordinates, bottomCoordinates, g );
		
		spikes = new ArrayList <Rectangle> ();
		hoops = new ArrayList <Rectangle> ();
		checkLocations = new ArrayList <Rectangle> ();
		extraLives = new ArrayList <Rectangle> ();
		
		
		
		int [][] spikeArray = { {13,6}, {130,6}, {101,6}, {108,6}  };
		spikes (spikeArray, g);
		
		int [][] ballspikeArray = { {2, 1, 1, 7}, 
				                    {4, 1, 1, 7}, 
				                    {49,1, 1, 7}, 
				                    {55,1, 1, 7}, 
				                    {61,1, 1, 7}, 
				                    {92,1, 1, 7},
				                    {104,1, 1,7}
				                    };
		ballspikes (ballspikeArray, g, Bounce.moveSpike);
		
		int [][] hoopArray = { {22,1}, {41,1}, {66,5}, {87,5}, {98,3}, {110,1} };
		boolean [] hoopOrientation = {true, true, true, true, true, true };
		hoops (hoopArray, hoopOrientation, g);
		
		createCheckPoint(70, 2, g, xPosition, checks.get(0));
		extraLifeLocation (18,5, g, xPosition, lives.get(0));
		
		
		Bounce.bounce.commonFeatures (g);
		
		passage (g, Bounce.bounce.numLights, passageX, 1, xPosition, Bounce.pImage);
	
	
         }
   

    
}
