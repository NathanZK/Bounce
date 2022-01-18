package bounce;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public abstract class Levels extends JPanel {
	
	protected Features features = new Features ();
	protected Toolkit a = Toolkit.getDefaultToolkit();
	
	protected int xPosition, yPosition, moveSpeed;
	protected ArrayList <Rectangle> spikes, hoops, checkLocations, extraLives;
	protected List <Boolean> light = new ArrayList <Boolean> ();
	protected List <Boolean> checks = new ArrayList <Boolean> ();
	protected List <Boolean> lives = new ArrayList <Boolean> ();
	
	protected int [][] topCoordinates, bottomCoordinates;
	protected BounceBall bounceBall;
	protected int  checkPoint=0;

	protected static double SCALE =1;
	private int xBlocks;
	protected  Color bgColor;
	
	protected static int ballX = 120, ballY=90;
	
	protected static BufferedImage brickSlected= ImageFiles.bricks[0];
	
	protected Rectangle passageRect  = new Rectangle (10000,0,0,0);
	protected int passageX;

	/*
	 * The constructor sets the moveSpeed, initial xPosition and the backGround
	 */
 	protected Levels () {
		
		this.xPosition =0;
    	this.moveSpeed =12;
    	this.bgColor = new Color (84, 217, 253);
    	
	}
 	
	/*
	 * Draws bricks by taking parameters
	 */
    protected void drawBricks (int xBlocks, int yBlocks, Graphics g, BufferedImage brick) {
    	this.xBlocks = xBlocks;
	
	   for (int i=0; i<xBlocks;i++) {
		
		for (int k=0;k<yBlocks;k++) {
    
			int initialX =(int) (SCALE*(60*i+xPosition));
			int finalX = (int)(SCALE*(60*i+xPosition+60));
			
			int initialY =(int)(SCALE*(60*k));
			int finalY = (int)(SCALE*(60*k+60));
			g.drawImage(brick, initialX, initialY , finalX , finalY , 0, 0, brick.getWidth(), brick.getHeight(), null);
			
          }
	     }
	 
	//  drawCoordinates(xBlocks, yBlocks, g);
   }   
    /*
     * Draws coordinates on the bricks
     */
	private void drawCoordinates(int xBlocks, int yBlocks, Graphics g) {

			g.setFont(new Font("Tahoma", Font.BOLD, 17));
			for (int i=0; i<xBlocks; i++) {
			for (int k=0; k<yBlocks+1; k++) {
				
		      g.drawString( String.valueOf(k)  , i*60+ 30+xPosition, 60*k-20);
			  g.drawString( String.valueOf(i)  , 60*i+xPosition-35, k*60 + 20);
				}
			}
	}
   /*
    * Empties regions from the blocks based on the coordinates entered
    */
    protected void emptyLocation(int[][] topCoordinates, int[][] bottomCoordinates, Graphics g) {
    	
	 g.setColor(bgColor);
	 int coordinateSize = topCoordinates.length + bottomCoordinates.length;
	 int [] xCoordinates = new int [coordinateSize] ;
	 int [] yCoordinates = new int [coordinateSize];

	  for (int i=0; i <topCoordinates.length; i ++) { 
		   xCoordinates [i] =  topCoordinates [i][0];
		   yCoordinates [i] =  topCoordinates [i][1];
		     }
	  
	  for (int i=0; i <bottomCoordinates.length; i ++) { 
		   xCoordinates [coordinateSize-1-i]= bottomCoordinates [i][0];
		   yCoordinates [coordinateSize-1-i]= bottomCoordinates [i][1];
		     }
	  
	  
     for (int i=0; i<coordinateSize; i++) {
         xCoordinates[i]*=60;
         xCoordinates[i]+=xPosition;
         xCoordinates[i]*=SCALE;
         yCoordinates[i]*=60;
         yCoordinates[i]*=SCALE;
	 
         
    }
         g.fillPolygon(xCoordinates, yCoordinates, coordinateSize);
	  
  }
    
   /*
    * Defines the possible movements of the ball in the horizontal direction
    */
    protected void move(boolean right, Ellipse2D.Double ball) {
		int ballLimit;
		   if (features.passageOpen) {ballLimit=(int) (1120*SCALE);}
		    else {ballLimit=(int) (940*SCALE);}
		if (right) {   rightMovement(ball, ballLimit);  }
		      else {  leftMovement(ball, ballLimit);  }
		 
		
}
    /*
     * Controls the left movement of the ball
     */

	protected void leftMovement(Ellipse2D.Double ball,  int ballLimit) {
		
		int frameHalf = 0, limit=0;
		if (SCALE==1) { frameHalf =500;
		    limit= xBlocks*60-1120;
	
		}
		else {
			frameHalf = 400;
			limit = xBlocks*60-1120 + 255;
			  
		}
		
		if ((ball.x>frameHalf  && Math.abs(xPosition)<=limit)|| 
		 ( ball.x>120 && ball.x <frameHalf 
				 && Math.abs(xPosition)<=moveSpeed-12
				 )) {
			ball.x -=moveSpeed;
		}
		else {
					 xPosition+=moveSpeed;
		}
		
		   if (xPosition>=0) {
				xPosition=0;		
		}
		   
		   
		   
		   
		for (int i=0; i <bottomCoordinates.length-1; i ++) {
			if (ball.x <= bottomCoordinates[i][0]*60+xPosition &&
				ball.x >= bottomCoordinates[i][0]*60+xPosition-moveSpeed &&
				bottomCoordinates[i][0] ==bottomCoordinates[i+1][0] && 
				ball.y+60> bottomCoordinates[i][1]*60 && 
				ball.y< bottomCoordinates[i+1][1]*60
					) {
			xPosition =(int) (((bottomCoordinates[i][0]-2)*(-60))+ball.x-120);
				 
				}
			}
		
		for (int i=0; i <topCoordinates.length-1; i ++) {
			if (ball.x <= topCoordinates[i][0]*60+xPosition  &&
				ball.x >= topCoordinates[i][0]*60+xPosition-moveSpeed &&
				topCoordinates[i][0] ==topCoordinates[i+1][0] &&
				topCoordinates[i][1] >topCoordinates [i+1][1] && 
				ball.y< topCoordinates[i][1]*60
					) {
			xPosition =(int) (((topCoordinates[i][0]-2)*(-60))+ball.x-120);
				 
				}
			}
	}
  /*
   * Controls the right movement of the ball
   */

	protected void rightMovement(Ellipse2D.Double ball, int ballLimit) {
		int frameHalf = 0, limit=0;
		if (SCALE==1) { frameHalf =500;
		    limit= xBlocks*60-1120;
		}
		else {
			frameHalf = 400;
			limit = xBlocks*60-1120 + 255;
		}
		
		if (ball.x<=ballLimit-moveSpeed) {
		if (Math.abs(xPosition)>=limit || (ball.x<=frameHalf &&xPosition<=-4)) {
			ball.x +=moveSpeed;
		}
		else {
		xPosition-=moveSpeed; 
		}
		}
		
		
		for (int i=0; i <bottomCoordinates.length-1; i ++) {
		     
			if (ball.x+60 >= bottomCoordinates[i][0]*60+xPosition &&
		    	 ball.x+60 <= bottomCoordinates[i][0]*60+xPosition+moveSpeed &&
		    	 bottomCoordinates[i][0] ==bottomCoordinates[i+1][0] &&
		    	 bottomCoordinates[i][1] >bottomCoordinates [i+1][1] && 
		    	 ball.y+60> bottomCoordinates[i+1][1]*60 && 
		    	 ball.y< bottomCoordinates[i][1]*60
				) {
		xPosition =(int) (((bottomCoordinates[i][0]-3)*(-60))+ball.x-120);
			 
		   }
		}
		for (int i=0; i <topCoordinates.length-1; i ++) {
			if (ball.x+60 >= topCoordinates[i][0]*60+xPosition &&
			    ball.x+60 <= topCoordinates[i][0]*60+xPosition+moveSpeed &&
			    topCoordinates[i][0] ==topCoordinates[i+1][0] &&
			    topCoordinates[i][1] <topCoordinates [i+1][1] && 
			    ball.y< topCoordinates[i+1][1]*60
					) {
			xPosition =(int) (((topCoordinates[i][0]-3)*(-60))+ball.x-120);
				 
				}
			}
	}
    /*
     *Creates the spikes and spike Rectangles along with it. 
     *The spike rectangles are useful in determining intersection between the ball and the spikes 
     */	
    protected void spikes(int [][] spikeArray, Graphics g) {
    	
		  
		for (int i=0; i<spikeArray.length; i++) {
	         features.drawSpike(spikeArray[i][0], spikeArray[i][1], g, xPosition);
	         int sX = (int) ((spikeArray[i][0]*60+xPosition+10)*SCALE);
	 		 int sY = (int) ((spikeArray[i][1]*60)*SCALE);
	    	 spikes.add(new Rectangle (sX, sY, (int) (40*SCALE), (int) (60*SCALE)));
			}		
	}
    /*
     * Defines the motion of the moving Obstacles
     */
    protected void ballspikes(int [][] spikeArray, Graphics g, int moveSpike) {
    	
		  
		for (int i=0; i<spikeArray.length; i++) {
	        features.movingSpike(spikeArray[i][0], spikeArray[i][1], g, xPosition, spikeArray[i][2], spikeArray[i][3], moveSpike );
	         int sX = (int) ((spikeArray[i][0]*60+xPosition)*SCALE);
	 		 int sY = (int) ((spikeArray[i][1]*60)*SCALE + moveSpike);
	    	 spikes.add(new Rectangle (sX, sY, (int) (120*SCALE), (int) (120*SCALE)));
			}		
	}
  /*
   * Creates the hoop and hoop Rectangles along with it. 
   * The hoop rectangles are useful in determining intersection between the ball and the spikes 
   */
    protected void createHoop  (int hoopX, int hoopY, Graphics g, boolean orientation, boolean hoopOn) {
 	  
    	  int hX = (int) ((hoopX*60+15+xPosition)*SCALE);
 		  int hY = (int) ((hoopY*60+15)*SCALE);
 		  
 	  if (orientation) {
 		  features.drawYHoop(hoopX, hoopY, g, xPosition, hoopOn);
 		  hoops.add(new Rectangle (hX, hY, (int) (30*SCALE), (int) (90*SCALE)));  
 	  }
 	  
 	  else {
 		 features.drawXHoop(hoopX, hoopY, g, xPosition, hoopOn);
 		 hoops.add (new Rectangle (hX , hY, (int) (90*SCALE), (int) (30*SCALE)));
 	  }
 	    
   } 
   
   /*
    * Turns off the hoop light when the ball passes through it 
    * Makes the ball jump when it passes through the hoop
    */
   protected void hoop (Ellipse2D.Double ball, BounceBall bounceBall) {
 	 	
     for (int i=0; i <hoops.size(); i++) {
 	    if (ball.intersects(hoops.get(i))) {  
 	    	light.set(i, false); 
 	    	if (!Bounce.up 
 	    			&& ball.y>= hoops.get(i).getCenterY()
 	    		
 	 			   ) {	
 	 		  bounceBall.Velocity[0] =6;
 	          Bounce.up = true;
 	           
 	          
 	 	   } 
 	    
 	    }
	  }
 	 }  
    /*
     * Creates hoops
     */
    protected void hoops(int [][] hoopArray, boolean [] hoopOrientation, Graphics g) {
		  
		   for (int i=0; i<hoopArray.length; i++) {
			   createHoop (hoopArray[i][0], hoopArray[i][1], g, hoopOrientation[i], light.get(i));
		   }   
	   }
  
    /*
     * Creates checkPoints
     */
    protected void createCheckPoint (int checkX, int checkY, Graphics g, int xPosition, boolean check) {
    	
    	features.CheckPoint(checkX, checkY, g, xPosition, check);
    	checkLocations.add(new Rectangle ((int) ((checkX*60+xPosition)*SCALE) , (int) ((checkY*60)*SCALE), (int) (60*SCALE), (int) (60*SCALE)));
    }
    /*
     * Checks ball intersection with checkpoint
     */
    protected void checkLight (Ellipse2D.Double ball, BounceBall bounceBall) {
    	
    	 for (int i=0; i <checkLocations.size(); i++) {
    	 	    if (ball.intersects(checkLocations.get(i))&&!checks.get(i)) {  
    	 	     checks.set(i, true); 
    	 	    checkPoint = (int) ((xPosition/60)*60-30);
    	 	    bounceBall.initialBallLocation[0]=  (int) (ball.x-((SCALE-1)*400));
    	 	    Bounce.checkP++;
    	 	    }
    		  }
    }
     /*
      * Determines location of extra Life and creates a rectangle in that location
      */
    protected void extraLifeLocation (int lifeX, int lifeY, Graphics g, int xPosition, boolean capture) {
    	 
    	 features.extraLife(lifeX, lifeY, g, xPosition, capture);
    	 extraLives.add(new Rectangle ((int) ((lifeX*60+xPosition)*SCALE), (int) (lifeY*60*SCALE), (int) (60*SCALE), (int) (60*SCALE)));
     }
	/*
	 * Determines intersection with extra life rectangle
	 */
     protected void checkLife (Ellipse2D.Double ball ){
     	
    	 for (int i=0; i <extraLives.size(); i++) {
    	 	    if (ball.intersects(extraLives.get(i))&&lives.get(i)) {  
    	 	    	  Bounce.life++;
    	 	    	  Bounce.lifeP++;
    	 	    	lives.set (i, false);
    	 	    
    	 	    }
    		  }
    }

	/*
	 * Creates booleans based on the number of hoops, checkPoints and extraLives
	 */
     protected void featuresNo(int numLights, int checkLights, int livesNo) {
		
		for (int i=0; i<numLights; i++) {
	    light.add( true);
		}
		for (int i=0; i < checkLights; i++) {
		checks.add(false);
		}
		for (int i=0; i<livesNo; i++) {
		lives.add(true);
		}
	}
     /*
      * Creates a rectangle for the passage at the end of the level
      */

	public void passage(Graphics g, int numLights, int xP, int yP, int xPosition, int pNum) {
		
		features.drawPassage(g, numLights, xP, yP, xPosition, pNum);
		passageRect = new Rectangle ((int) ((xP*60+xPosition)*SCALE), (int) ((yP*60)*SCALE), (int) (100*SCALE), (int) (120*SCALE) );
	}
 
	
	
}

