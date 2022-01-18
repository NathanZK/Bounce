package bounce;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Features extends JPanel {
 
 protected boolean passageOpen =false;


   /*
    * Draws spikes on specified location
    */
  protected void drawSpike (int spikeX, int spikeY, Graphics g, int xPosition) {
	  
	   int initialSX = (int) ((spikeX*60+xPosition+10)*Levels.SCALE);
	   int finalSX = (int) ((spikeX*60+xPosition+50)*Levels.SCALE);
	   
	   int initialSY = (int) ((spikeY*60)*Levels.SCALE);
	   int finalSY = (int) ((spikeY*60+60)*Levels.SCALE);
	   
	   g.drawImage(ImageFiles.spiky, initialSX, initialSY , finalSX , finalSY , 
			       0, 0, ImageFiles.spiky.getWidth(), ImageFiles.spiky.getHeight(), null);
	   
	   
   }
   
   /*
    * Draws small hoop displayed at the top
    */
   protected void drawSmallHoop (Graphics g, int count) {

	   for (int i=0; i<count; i++) {
	   
	   int initialHX = (int) ((120+30*i)*Levels.SCALE);
	   int finalHX = (int) ((120+30*i+20)*Levels.SCALE);
	   
	   int initialHY = (int) ((20)*Levels.SCALE);
	   int finalHY = (int) ((50)*Levels.SCALE);
	   
	   g.drawImage(ImageFiles.VhoopON, initialHX, initialHY , finalHX , finalHY , 
			   0, 0, ImageFiles.VhoopON.getWidth(), ImageFiles.VhoopON.getHeight(), null);
	   
	   }
   }
     
   /*
    * Draws closed passage and draws open Passage when all the hoops are passed  
    */
   protected void drawPassage (Graphics g, int numLights, int PassageX, int PassageY, int xPosition, int num) {
    	 
	   int initialPX = (int) ((PassageX*60+xPosition)*Levels.SCALE);
	   int finalPX = (int) ((PassageX*60+xPosition+120)*Levels.SCALE);
	   
	   int initialPY = (int) ((PassageY*60)*Levels.SCALE);
	   int finalPY = (int) ((PassageY*60+120)*Levels.SCALE);
	     
	   if (num>12) { num=12; passageOpen=true;}
	   if (num<=12) {
	   g.drawImage(ImageFiles.passageI[num], initialPX, initialPY , finalPX , finalPY , 
			   0, 0, ImageFiles.passageI[num].getWidth(), ImageFiles.passageI[num].getHeight(), null);
	   }
    	 
     }
      
   /*
    * Draws vertical hoop
    */
   protected void drawYHoop (int hoopX, int hoopY, Graphics g, int xPosition, boolean hoopOn) {
	  
	   int initialHX = (int) ((hoopX*60+15+xPosition)*Levels.SCALE);
	   int finalHX = (int) ((hoopX*60+15+xPosition+30)*Levels.SCALE);
	   
	   int initialHY = (int) ((hoopY*60+15)*Levels.SCALE);
	   int finalHY = (int) ((hoopY*60+15+90)*Levels.SCALE);
	   
	   if (hoopOn) {
		    
	   g.drawImage(ImageFiles.VhoopON, initialHX, initialHY , finalHX , finalHY , 
			   0, 0, ImageFiles.VhoopON.getWidth(), ImageFiles.VhoopON.getHeight(), null);
	   }
	   else {
	   g.drawImage(ImageFiles.VhoopOFF, initialHX, initialHY , finalHX , finalHY , 
			   0, 0, ImageFiles.VhoopOFF.getWidth(), ImageFiles.VhoopOFF.getHeight(), null);
	   }
	      
   }
   
   /*
    * Draws a hoizontal hoop
    */
   protected void drawXHoop (int hoopX, int hoopY, Graphics g, int xPosition, boolean hoopOn) {
	   
	   int initialHX = (int) ((hoopX*60+15+xPosition)*Levels.SCALE);
	   int finalHX = (int) ((hoopX*60+15+xPosition+90)*Levels.SCALE);
	   
	   int initialHY = (int) ((hoopY*60+15)*Levels.SCALE);
	   int finalHY = (int) ((hoopY*60+15+30)*Levels.SCALE);
   
   if (hoopOn) {
	   
	   g.drawImage(ImageFiles.HhoopON, initialHX, initialHY , finalHX , finalHY , 
			   0, 0, ImageFiles.HhoopON.getWidth(), ImageFiles.HhoopON.getHeight(), null);
	   }
	   else {
		
		   g.drawImage(ImageFiles.HhoopOFF, initialHX, initialHY , finalHX , finalHY , 
				   0, 0, ImageFiles.HhoopOFF.getWidth(), ImageFiles.HhoopOFF.getHeight(), null);
	   }
   }
   /*
    * Creates CheckPoints
    */
   protected void CheckPoint (int checkX, int checkY, Graphics g, int xPosition, boolean check) {
	   
	   int initialHX = (int) ((checkX*60+xPosition)*Levels.SCALE);
	   int finalHX = (int) ((checkX*60+xPosition+60)*Levels.SCALE);
	   
	   int initialHY = (int) ((checkY*60)*Levels.SCALE);
	   int finalHY = (int) ((checkY*60+60)*Levels.SCALE);
	   
	   if (check) {
		   g.drawImage(ImageFiles.checked, initialHX, initialHY , finalHX , finalHY , 
				   0, 0, ImageFiles.checked.getWidth(), ImageFiles.checked.getHeight(), null);
       }
	   else {
		   g.drawImage(ImageFiles.checkpoint, initialHX, initialHY , finalHX , finalHY , 
				   0, 0, ImageFiles.checkpoint.getWidth(), ImageFiles.checkpoint.getHeight(), null);
	   }
	
   }
   /*
    * Creates extraLife
    */
   
   protected void extraLife(int lifeX, int lifeY, Graphics g, int xPosition, boolean capture) {
	   
	   int initialHX = (int) ((lifeX*60+xPosition)*Levels.SCALE);
	   int finalHX = (int) ((lifeX*60+xPosition+60)*Levels.SCALE);
	   
	   int initialHY = (int) ((lifeY*60)*Levels.SCALE);
	   int finalHY = (int) ((lifeY*60+60)*Levels.SCALE);
	 
	   if(capture) {
		   g.drawImage(ImageFiles.extraLife, initialHX, initialHY , finalHX , finalHY , 
				   0, 0, ImageFiles.extraLife.getWidth(), ImageFiles.extraLife.getHeight(), null);
	   }
   }
   /*
    * Draws the moving Spikes or the ball Spikes at the Spcified Location
    */
   
   protected void movingSpike (int spikeX, int spikeY, Graphics g, int xPosition, int limitTY, int limitBY, int moveSpike) {
	  
	   int initialSX = (int) ((spikeX*60+xPosition)*Levels.SCALE);
	   int finalSX = (int) ((spikeX*60+xPosition+120)*Levels.SCALE);
	   
	   int limitSpikeTY = (int) ((limitTY*60)*Levels.SCALE);
	   int limitSpikeBY = (int) ((limitBY*60)*Levels.SCALE);
	   
	   int initialSY = (int) ((spikeY*60)*Levels.SCALE+moveSpike);
	   if (initialSY<=limitSpikeTY) {
		
		   Bounce.reverseDirection =false;
		   }
	   int finalSY = (int) ((spikeY*60+120)*Levels.SCALE+moveSpike);
	   if (finalSY>=limitSpikeBY) {
		
		 Bounce.reverseDirection =true;
	   }
	   
	   g.drawImage(ImageFiles.movingSpike, initialSX, initialSY , finalSX , finalSY , 
			       0, 0, ImageFiles.movingSpike.getWidth(), ImageFiles.movingSpike.getHeight(), null);
	   
	   
   }
   
 
  
}
