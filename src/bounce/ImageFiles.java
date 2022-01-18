package bounce;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImageFiles {
	
	/*
	 * Images used in Features Class
	 */
	static BufferedImage spiky = loadImage("glassSpike.png"),
	                     VhoopON = loadImage("h1.png"),
	                     VhoopOFF = loadImage("h2.png"),
	                     HhoopON = loadImage("h3.png"),
	                     HhoopOFF = loadImage("h4.png"),
	                     checkpoint = loadImage("play.png"),
	                     checked= loadImage("downA.png"),
	                     extraLife= loadImage("life.png"),
	                     movingSpike = loadImage ("BallSpike.png");
	                   
	/*
	 * Images used in BounceBall Class
	 */
	
	static String [][]ballImages = { 
			                         {"glass.png", "glassPOP.png"},
			                         {"Stripe1.png" , "Stripe1POP.png"},
			                         {"gray.png", "grayPOP.png"},
			                         {"purple.png", "purplePOP.png"},
		                             {"red.png", "redPOP.png"},
		                             {"red3.png", "red3POP.png"},
		                             {"red4.png", "red4POP.png"},
		                             {"blue.png", "bluePOP.png"},
		                             {"blue1.png", "blue1POP.png"},
		                             {"green.png", "greenPOP.png"},
		                             {"black1.png", "black1POP.png"},
		                             {"football.png", "footballPOP.png"},
		                             {"tennis.png", "tennisPOP.png"},
		                             {"golf.png" , "golfPOP.png"},
		                             {"gold.png" , "goldPOP.png"},
		                             {"white.png" , "whitePOP.png"},
		                           
	
	};
	static BufferedImage [][] ball= assignImage (ballImages);
	/*
	 * Images used in Levels an its subclasses
	 */
	static String [] brickImages = { "darkBrick.png",
			                         "darkBrickc.png",
			                         "darkBrickd.png",
			                         "darkBricke.png",
			                         "greenBrick1.png",
			                         "bluebrick.png",
			                         "bluebrick1.png",
			                         "bluebrick2.png",
			                         "g1.png",
			                         "g3.png",
			                         "gred.png",
			                         "gBlue2.png",
			                        
			};
	static BufferedImage [] bricks = assignImage ( brickImages);
	/*
	 * Images used to draw passageImages found in features class
	 */
	static String [] passageImages = {"opening0.png",
			                          "opening1.png",
			                          "opening2.png",
			                          "opening3.png",
			                          "opening4.png",
			                          "opening5.png",
			                          "opening6.png",
			                          "opening7.png",
			                          "opening8.png",
			                          "opening9.png",
			                          "opening10.png",
			                          "opening11.png",
			                          "opening12.png"
			
	};
	
	static BufferedImage [] passageI = assignImage (passageImages);
	/*
	 * Images used in the Display Class
	 */
	static BufferedImage playIcon = loadImage ("play1.png"),
			             playIcon2 = loadImage ("play2.png"),
	                     returnIcon = loadImage ("r.png"),
	                     backGround = loadImage ("Bounce.png"),
	                     menuIcon = loadImage ("menu.png"),
	                     retryIcon = loadImage ("retry.png"),
	                     forwardIcon = loadImage ("forward.png");
	
	/*
	 * Loads images from the res folder
	 */
	protected static BufferedImage loadImage (String filename) {
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream(filename);
        
        

		BufferedImage img = null;
		try {
			
			img= ImageIO.read(input);
		} catch (IOException e) {
		}
		return img;
	}
	
	protected static BufferedImage[][] assignImage (String [][] imageName) {
		
		BufferedImage[][] img= new BufferedImage [imageName.length][2];
		for (int i=0; i<imageName.length; i++) {
			
		img [i][0] =	loadImage (imageName[i][0]);
		img [i][1] =	loadImage (imageName[i][1]);
			
		}
		
		return img;
		
	}
	
    protected static BufferedImage[] assignImage (String [] imageName) {
		
		BufferedImage[] img= new BufferedImage [imageName.length];
		for (int i=0; i<imageName.length; i++) {	
		img [i] =	loadImage (imageName[i]);
		}
		
		return img;
		
	
		
	  }
	
	

}
