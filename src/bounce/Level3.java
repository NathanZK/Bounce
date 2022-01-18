package bounce;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Level3 extends Levels {
	
	

	public Level3 () {
		
		passageX= 65;
		featuresNo(3, 0, 1);
    	
    	int [][] topC= { 
    			{2,1}, {30,1}, {30,2}, {32,2}, {32,1}, {60,1}, {60,5}, {67,5}, {67,7}
    	
    	};
    	
    	int [][] bottomC = { 
    			{2,7}, {10,7}, {10,6}, {11,6}, {11,5}, {12,5}, {12,4}, {13,4}, {13,3},
    			{15,3}, {15,4}, {14,4}, {14,5}, {13,5}, {13,6}, {12,6}, {12,7},{20,7},
    			{20,6}, {19,6}, {19,5}, {18,5}, {18,4}, {17,4}, {17,3}, {19,3}, {19,4},
    			{20,4}, {20,5}, {21,5}, {21,6}, {22, 6}, {22,7}, {27,7}, {27,5}, {25,5},
    			{25,4}, {28,4}, {28,7}, {30,7}, {30,5}, {32,5}, {32,7}, {40,7}, {40,3}, 
    			{43,3}, {43,7}
    	}; 
    	
    	topCoordinates =topC;
    	bottomCoordinates =bottomC;
    	
   
    	
    }

	@Override
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		drawBricks (67, 8, g, brickSlected );
		emptyLocation (topCoordinates, bottomCoordinates, g );
		
		spikes = new ArrayList <Rectangle> ();
		hoops = new ArrayList <Rectangle> ();
		checkLocations = new ArrayList <Rectangle> ();
		extraLives = new ArrayList <Rectangle> ();
		
		
		
		int [][] spikeArray = { {23,6}, {28,6}, {45,6}, {48,6}, {54,6} };
		spikes (spikeArray, g);
		
		int [][] ballspikeArray = { {15, 1, 1, 7},
				                    {34, 1, 1, 7},
				                    {36, 1, 1, 7},
				                    {52, 1, 1, 7}
				                    };
		ballspikes (ballspikeArray, g, Bounce.moveSpike);
		
		int [][] hoopArray = {{30,3}, {42,1}, {60,5}  };
		boolean [] hoopOrientation = {true , true, true };
		hoops (hoopArray, hoopOrientation, g);
		
		extraLifeLocation (26,5, g, xPosition, lives.get(0));
		
		Bounce.bounce.commonFeatures (g);
		
		passage (g, Bounce.bounce.numLights, passageX, 5, xPosition, Bounce.pImage);
	
	
         }
   

    
}
