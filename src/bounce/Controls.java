package bounce;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Controls extends JFrame implements KeyListener{

	protected static boolean  right, left;
	protected boolean  play =true;
	private int ballNo =0, brickNo =0;
	
	protected Toolkit a = Toolkit.getDefaultToolkit();
	protected static boolean buttonsVisible =false;
    
	protected static boolean maximized = false;
	protected static int xP = 350;
	protected static int yP = 30 ;
   
	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {

		case KeyEvent.VK_UP:      Bounce.up=true;          break;
		case KeyEvent.VK_RIGHT:   right=true;              break;
		case KeyEvent.VK_LEFT:    left=true;               break;
		case KeyEvent.VK_P:       play=false;              break;
		case KeyEvent.VK_S:       play = true;             break;
		case KeyEvent.VK_ESCAPE:  System.exit(0);          break;
		case KeyEvent.VK_SPACE:   Bounce.pause = true;     break;
		case KeyEvent.VK_B:
			ballNo++;
			if (ballNo== ImageFiles.ball.length) { ballNo =0;}
			BounceBall.ballSelected = ImageFiles.ball[ballNo][0]; 
			BounceBall.popBallSelected = ImageFiles.ball [ballNo][1];
			break;
		case KeyEvent.VK_N:
			brickNo++;
			if (brickNo==ImageFiles.bricks.length) {brickNo=0;}
			Levels.brickSlected = ImageFiles.bricks[brickNo];
			break;
		case KeyEvent.VK_M:
			
			if (Bounce.bounce.notResizable()) {
			maximized =! maximized;
			if (maximized) {
				Bounce.bounce.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				Levels.SCALE =a.getScreenSize().getHeight()/480;
				xP = 500;
				yP = 120 ;
			}
			else {
				Bounce.bounce.frame.setExtendedState(JFrame.NORMAL);
				Levels.SCALE =1;
			     xP = 350;
				 yP = 30 ;
			}
			
			Bounce.bounce.panelRelocate(xP, yP);
			}
			
			break;	
		}
		
	}

	

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {

		case KeyEvent.VK_RIGHT:   right=false;              break;
		case KeyEvent.VK_LEFT:    left=false;               break;

		}
	}


}
