package bounce;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class BounceBall extends JPanel{

	protected Ellipse2D.Double ball, ball1;
	protected int ballx, bally;
	private double gravity =0.6;

	protected double [] Velocity = {0,18};
	protected double initialHeight, groundHeight =360;
	private int time=0;
	protected static int bounceCount=0;
	protected int [] initialBallLocation = {120, 90};
	protected int ballX = initialBallLocation[0],
			ballY = initialBallLocation[1];

	protected static BufferedImage ballSelected = ImageFiles.ball[0][0], 
			popBallSelected = ImageFiles.ball[0][1];

	protected double ballDiameter =60;
	double deltaX=0;
	protected int inclineY=0;
	double inclineX =0;
	protected double tanZ =0;
	
	
    /*
     * Takes two parameters that define the ball's location
     */                  
	public BounceBall  (int ballX, int ballY) {
 
		this.initialBallLocation[0] = ballX;
		this.initialBallLocation[1] = ballY;
		
		ball = new Ellipse2D.Double (ballX ,ballY, 90, 90);

		initialHeight = ball.y;

	}

	/*
	 * Draws ball and rotates it in the direction of key pressed
	 */
	public void drawBall (Graphics g, int degree) {
		
		int ballradius = (int) (60*Levels.SCALE);

		int iballX = (int) (ball.x*Levels.SCALE);
		int iballY = (int) (ball.y*Levels.SCALE);
		Image resizedBall = ballSelected.getScaledInstance(ballradius, ballradius, BufferedImage.SCALE_SMOOTH);

		AffineTransform at =  AffineTransform.getTranslateInstance(iballX, iballY);
		at.rotate(Math.toRadians(degree), ballradius/2, ballradius/2);

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(resizedBall, at, null);


		double ballD = ballDiameter*Levels.SCALE;
		ball1 = new Ellipse2D.Double (iballX ,iballY, ballD, ballD);


	}

	/*
	 * Draws a popped ball when the ball hits the spike
	 */
	public void popBall (Graphics g) {

		int iPopBX = (int)(ball.x*(Levels.SCALE));
		int fPopBX = (int) ((ball.x+60)*Levels.SCALE);

		int iPopBY = (int) (ball.y*Levels.SCALE);
		int fPopBY = (int) ((ball.y+60)*Levels.SCALE);

		g.drawImage(popBallSelected, iPopBX, iPopBY , fPopBX, fPopBY, 0, 0,ballSelected.getWidth(), ballSelected.getHeight(), null);
	}

	/*
	 * Draws the smallball at the top of the level to show the number of balls left
	 */
	public void drawSmallBall (Graphics g) {

		int iSmallBX = (int) (30*Levels.SCALE);
		int fSmallBX = (int) (65*Levels.SCALE);

		int iSmallBY = (int) (20*Levels.SCALE);
		int fSmallBY = (int) (55*Levels.SCALE);

		g.drawImage(ballSelected, iSmallBX, iSmallBY , fSmallBX, fSmallBY, 0, 0,ballSelected.getWidth(), ballSelected.getHeight(), null);

	}

	/*
	 * Makes the ball move in the vertical direction
	 */

	public void moveBall (boolean up, int[][] topCoordinates, int [][] bottomCoordinates, int xPosition ) {
		
		if (!up  &&  ball.y <360) {

		/*	for (int i=0; i <bottomCoordinates.length-1; i ++) {

				if (
						bottomCoordinates[i][1]==bottomCoordinates[i+1][1] &&
						bottomCoordinates[i+1][0]> bottomCoordinates[i][0] &&
						ball.y + 60>= bottomCoordinates [i][1]*60  &&
						ball.y + 60 <= bottomCoordinates [i][1]*60 +20&&
						ball.x +60 > bottomCoordinates [i][0]*60 + xPosition&&
						ball.x < bottomCoordinates [i+1][0]*60 + xPosition
						){
					ball.y = bottomCoordinates [i][1]*60 -60;
					initialHeight = ball.y;
					Velocity [0] =0;
					time= 0;
					groundHeight= ball.y;
					Bounce.up =false;

				}

			}*/
			bCoordinates(bottomCoordinates, xPosition);

			if (ball.y== groundHeight) {
				Velocity[0] =18;
			}
			if (ball.y <groundHeight) {
				Velocity[0] =0;
			}

			if (ball.y<groundHeight) {

				time++;
				ball.y =  (initialHeight - (Velocity [0]*time) + 0.5*gravity*time*time);
				Velocity [1] = Velocity [0] - (gravity*time);
			}

			if (
					ball.y >= 360+Velocity[1] 
					) {

				bounceCount++;
				Bounce.up =true;
				time=0;
				Velocity [0] =0.5*Math.abs(Velocity [1]);
				initialHeight= ball.y;
				ball.y =360;

			}

		}


		if (up) {

			time++;
			ball.y =  (initialHeight - (Velocity [0]*time) + 0.5*gravity*time*time);
			Velocity [1] = Velocity [0] - (gravity*time);


			for (int i=0; i <topCoordinates.length-1; i ++) {
				if (
						topCoordinates[i][1]== topCoordinates[i+1][1] && 
						ball.y-Velocity [1]*0.7<=topCoordinates[i][1]*60 && 
						topCoordinates[i+1][0] > topCoordinates[i][0] &&
						ball.x+60 > topCoordinates[i][0]*60 +xPosition && 
						ball.x < topCoordinates[i+1][0]*60 +xPosition
						) {
					time= 0;
					Velocity [0] = -Velocity [1];
					initialHeight = ball.y;     
				}

			}

			bCoordinates(bottomCoordinates, xPosition);


			if ( ball.y>=groundHeight +Velocity [1] ) {
				bounceCount++;
				time=0;
				Velocity [0] =0.5*Math.abs(Velocity [1]);
				initialHeight= ball.y;
				ball.y = groundHeight;


		   if (bounceCount==3) {
					
					ball.y = groundHeight;
					Bounce.up =false;
					Velocity [0]=18;
					time=0;
					bounceCount=0;


				}

			}

		}



		for (int i=0; i <bottomCoordinates.length-1; i ++) {
			if (groundHeight< 360 &&
					bottomCoordinates[i][1]==bottomCoordinates[i+1][1] &&
					bottomCoordinates[i+1][0]> bottomCoordinates[i][0] &&
					ball.y + 60>= bottomCoordinates [i][1]*60  &&
					ball.y + 60 <= bottomCoordinates [i][1]*60 +20&&
					!(ball.x +60 > bottomCoordinates [i][0]*60 + xPosition&&
							ball.x < bottomCoordinates [i+1][0]*60 + xPosition)
					){

				groundHeight= 360;
			}

		}
		
		for (int i=0; i <bottomCoordinates.length-1; i ++) {
			
			if (
					bottomCoordinates[i][1] > bottomCoordinates[i+1][1] &&
					bottomCoordinates[i][0] < bottomCoordinates[i+1][0]  &&
					ball.x+60  == bottomCoordinates [i][0]*60 + xPosition
					){
				inclineX = Math.abs(xPosition)+ball.x;
				inclineY = bottomCoordinates[i][1]*60-60;
				tanZ = (bottomCoordinates[i][1] - bottomCoordinates[i+1][1])/ (bottomCoordinates[i+1][0] - bottomCoordinates[i][0]);
			}
			
			if (
					bottomCoordinates[i][1] > bottomCoordinates[i+1][1] &&
					bottomCoordinates[i][0] < bottomCoordinates[i+1][0]  &&
					ball.x +60 > bottomCoordinates [i][0]*60 + xPosition &&
					ball.x +60 <= bottomCoordinates [i+1][0]*60 + xPosition
					){
				deltaX = Math.abs(xPosition)+ball.x - inclineX -20;
				ball.y = inclineY - deltaX ;
				
			}
			
			if (
					bottomCoordinates[i][1] < bottomCoordinates[i+1][1] &&
					bottomCoordinates[i][0] < bottomCoordinates[i+1][0]  &&
					ball.x+60  == bottomCoordinates [i][0]*60 + xPosition
					){
				inclineX = Math.abs(xPosition)+ball.x;
				inclineY = bottomCoordinates[i][1]*60-60;
				tanZ = (bottomCoordinates[i][1] - bottomCoordinates[i+1][1])/ (bottomCoordinates[i+1][0] - bottomCoordinates[i][0]);
			}
			
			if (
					bottomCoordinates[i][1] < bottomCoordinates[i+1][1] &&
					bottomCoordinates[i][0] < bottomCoordinates[i+1][0]  &&
					ball.x+60  > bottomCoordinates [i][0]*60 + xPosition &&
					ball.x  <= bottomCoordinates [i+1][0]*60 + xPosition
					){
				deltaX = Math.abs(xPosition)+ball.x - inclineX-40;
				ball.y = inclineY + deltaX ;
				
			}
			
			if (ball.y>=360) {
				ball.y =360;
			}
			
		}
		
		
		

     


	}

	protected void bCoordinates(int[][] bottomCoordinates, int xPosition) {
		for (int i=0; i <bottomCoordinates.length-1; i ++) {
			if (
					bottomCoordinates[i][1]==bottomCoordinates[i+1][1] &&
					bottomCoordinates[i+1][0]> bottomCoordinates[i][0] &&
					ball.y + 60>= bottomCoordinates [i][1]*60  &&
					ball.y + 60 <= bottomCoordinates [i][1]*60 +20&&
					ball.x +60 > bottomCoordinates [i][0]*60 + xPosition&&
					ball.x < bottomCoordinates [i+1][0]*60 + xPosition
					){
				ball.y = bottomCoordinates [i][1]*60 -60;
				initialHeight = ball.y;
				Velocity [0] =0;
				time= 0;
				groundHeight= ball.y;
			}


			if (
					bottomCoordinates[i][1]== bottomCoordinates[i+1][1] && 
					ball.y-Velocity [1]*0.6<=bottomCoordinates[i][1]*60 &&
					ball.y +Velocity [1]>=bottomCoordinates[i][1]*60 &&
					bottomCoordinates[i+1][0] < bottomCoordinates[i][0] &&
					ball.x+60 > bottomCoordinates[i+1][0]*60 +xPosition && 
					ball.x < bottomCoordinates[i][0]*60 +xPosition
					) {
				time= 0;
				Velocity [0] = -Velocity [1];
				initialHeight = ball.y;

			}
			

		}
	}




}
