package bounce;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;


public class Bounce implements ActionListener { 

	//Instantiated Classes
	protected JFrame frame;
	protected Levels level;
	private Features features;
	private BounceBall bounceBall;
	private Controls controls;
	protected static Bounce bounce;
	protected Timer timer;

	protected static int ballPositionX =120, ballPositionY=90;
	
	protected int Scale =1;
	private final int WIDTH = 1120, HEIGHT = 480;
	protected static int degree=0, rotateSpeed =15;
	protected static int score=0, lightP=0, lifeP=0, checkP=0;
	protected static int life=3, pImage =0, moveSpike=0; 
	protected static boolean reverseDirection=false;
	protected int numLights;

	protected static boolean fail=false, levelComplete= false;
	protected static boolean up=false;
	protected static boolean pause = false, ballDrawn =false;
	protected Menu menu;
	
	
	protected Color rectColor = new Color (225, 225, 225, 0);


	protected Bounce () {  

		bounceBall = new BounceBall (120, 90);
		features = new Features ();
		controls = new Controls ();
		menu = new Menu ();

		initializeFrame ();
	}


	public static void main(String[] args) throws  InterruptedException {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					bounce = new Bounce ();	

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*
	 * Initializes the contents of the frame
	 */
	protected void initializeFrame (){

		timer = new Timer(15, this);

		frame = new JFrame("Bounce");
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(controls);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension (WIDTH, HEIGHT));

		frame.getContentPane().add(menu.mainPane);
		frame.getContentPane().add(menu.levelPaused);
		frame.getContentPane().add(menu.levelComplete);
		frame.getContentPane().add(menu.levelFailed);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		level = new LevelZ ();

		frame.getContentPane().add(level);
		menu.mainPane.setVisible(true);
	//	timer.start();

	}
	/*
	 * Initializes the changing values in the game
	 */
	protected void initializeGameValues (int ballPositionX, int ballPositionY  ) {

	
		life =3;
        moveSpike=0;
		bounceBall = new BounceBall (ballPositionX,ballPositionY);

		level.checkPoint =0;
		level.xPosition = level.checkPoint;

		lightP=0;
		lifeP =0;
		checkP=0;
		score=0;
		pImage =0;

		levelComplete =false;

		for (int i=0; i <level.light.size(); i++) {
			level.light.set(i, true); 
		}

		for (int i=0; i <level.checks.size(); i++) {
			level.checks.set(i, false); 
		}
		
		for (int i=0; i <level.lives.size(); i++) {

			level.lives.set (i, true);

		}

	}
	/*
	 * Paints the ball, score and the game progress
	 */

	protected void commonFeatures(Graphics g) {

		if (menu.mainPane.isVisible()) {

			Bounce.bounce.frame.setExtendedState(JFrame.NORMAL);
			Levels.SCALE =1;
			Controls.maximized = false;

			panelRelocate(Controls.xP = 350, Controls.yP = 30 );
		}

		ballDrawn (g);
		bounceBall.drawSmallBall(g);
		features.drawSmallHoop(g,numLights);
       
		g.setColor(Color.WHITE);
		g.setFont(new Font("Lucida Calligraphy", Font.BOLD, (int) (30*Levels.SCALE)));
		g.drawString("X" + String.valueOf(life), (int) (70*Levels.SCALE), (int) (45*Levels.SCALE));
		g.drawString (String.valueOf(score),(int) ((WIDTH-180)*Levels.SCALE-(Levels.SCALE-1)*600), (int) (45*Levels.SCALE));
		
		g.setColor(rectColor);
		g.fillRect(0, 0, (int) (WIDTH*Levels.SCALE), (int) (HEIGHT*Levels.SCALE));
	}

	/*
	 * Prevents the mainPane from being maximized
	 * Makes the frame not resizable after the ball passes a certain region
	 */
	public boolean notResizable () {

	    if (menu.mainPane.isVisible()) {
			return false;
		}
	    else if (bounceBall.ball.x <=505 ) {
			return true;
		}
		else {
			return false;
		}


	}

	/*
	 * Draws a popped ball or a normal ball
	 */
	protected void ballDrawn (Graphics g) {
	
		if (fail) { 
			 
			bounceBall.popBall (g);
			ballDrawn=true;}
		else { 
			  
			bounceBall.drawBall(g, degree); }
		
	}

	/*
	 * Relocates the positions of the panels
	 */
	protected void panelRelocate(int xP, int yP) {

		int width = 400;
		int height = 400;
		menu.levelPaused.setBounds(xP, yP, width, height);
		menu.levelComplete.setBounds(xP, yP, width, height);
		menu.levelFailed.setBounds(xP, yP, width, height);
	}

	/*
	 * Shows Level Status - Paused, Failed or Complete
	 */
	protected void levelStatus() {
		if (pause) {
		    rectColor = new Color (225, 225, 225, 120);
			menu.levelPaused.setVisible(true);
			timer.stop();
		}
		
		if (!pause) {
			 rectColor = new Color (225, 225, 225, 0);	
		}

		if (life==0) {
			rectColor = new Color (225, 225, 225, 120);
			menu.levelFailed.setVisible(true);
			timer.stop();

		}

		if (levelComplete) {
			rectColor = new Color (225, 225, 225, 120);
			menu.levelComplete.setVisible(true);
			timer.stop();
		}
	}
	/*
	 * Makes the scene move based on key entered
	 */
	private void sceneMotion() 
	{
		if (Controls.right) {
			level.move(true, bounceBall.ball);
			degree+=rotateSpeed;
		}
		if (Controls.left) {
			level.move(false, bounceBall.ball);
			degree-=rotateSpeed;

		}
	}
	/*
	 * Executes interaction of the ball with spikes
	 */
	private void hitSpike () {

		for (Rectangle spike: level.spikes) {
			if (bounceBall.ball1.intersects(spike)) {

				fail =true;
				if (ballDrawn) {
					try {
					   Thread.sleep(500);
					} catch (InterruptedException e) {
					}
					ballDrawn=false;
					fail=false;
					life--;
					level.xPosition =level.checkPoint;
					bounceBall.ball.x=bounceBall.initialBallLocation[0];
                 
					up=true;	
					
				}
				
			break;	
			}
		}

	}
	/*
	 * Executes interaction of the ball with different features
	 */
	private void featuresInteraction(boolean play) {
		if (play) {hitSpike ();}
		level.hoop(bounceBall.ball1, bounceBall); 
		level.checkLight(bounceBall.ball1, bounceBall);
		level.checkLife(bounceBall.ball1);
		scoreKeeping();
	}
	/*
	 * Controls the motion of the ball and the terrain
	 */
	private void ballMotion () {

		sceneMotion();
		bounceBall.moveBall ( up, level.topCoordinates, level.bottomCoordinates, level.xPosition);

		if ( bounceBall.ball1.x>=level.passageRect.x && numLights==0) {
			levelComplete =true;
		}

		if (numLights ==0) {
			pImage++;
		}


	}
	/*
	 * Keeps Score
	 */
	private void scoreKeeping () {
		int count=0;
		for (boolean l: level.light) {
			if (!l) {count++;}
		}
		lightP= count;
		numLights= level.light.size() - count;
		score = (lightP+lifeP+checkP)*500;
	}
	/*
	 * Puts the featuresInteraction, ball motion and repaint methods into one method 
	 */
	private void gameStart () {

		featuresInteraction (controls.play);
		ballMotion(); 
		levelStatus();
		level.repaint();
		
		if (reverseDirection) {
			moveSpike -=3;
		}
		else 
		{
		moveSpike +=3;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		gameStart ();
	}

}
