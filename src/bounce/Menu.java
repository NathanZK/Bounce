package bounce;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Menu implements ActionListener {

	private final int WIDTH = 1120, HEIGHT = 480;
	protected  JPanel mainPane, levelComplete, levelPaused, levelFailed;

	private JButton PLAY, l1, l2, l3, back,  resume, restart, menu;
	private JButton mainMenu, retry, forward, mainMenuL, retryL;
	
	protected Toolkit a = Toolkit.getDefaultToolkit();


	public Menu() {

		buttons();

		levelPaused();
		levelPaused.setVisible(false);

		levelComplete();
		levelComplete.setVisible(false);

		levelFailed();
		levelFailed.setVisible(false);

		mainMenu();
		mainPane.setVisible(false);

	}
	/*
	 * Creates the panel that displays the main menu
	 */

	protected void mainMenu() {
		mainPane = new JPanel();
		mainPane.setSize( (WIDTH),  (HEIGHT));
		mainPane.setLayout(null);
		mainPane.setBackground(new Color (181, 181, 181));

		createButton (PLAY, WIDTH/2, HEIGHT/2-100, 300, 70, mainPane);
		createButton (l1, WIDTH/2, HEIGHT/2-100, 100, 70, mainPane);
		createButton (l2, WIDTH/2+100, HEIGHT/2-100, 100, 70, mainPane);
		createButton (l3, WIDTH/2+200, HEIGHT/2-100, 100, 70, mainPane);
		createButton (back, 450, HEIGHT-200, 60, 60, mainPane);

		PLAY.setVisible(true);

		
		URL url = getClass().getResource("/b4.gif");
		Icon icon = new ImageIcon (url);
		/*BufferedImage backGround = null;
		try {
		 backGround = ImageIO.read(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		JLabel bg = new JLabel(icon);
		
		
		bg.setBounds(0, 0, WIDTH, HEIGHT);
		mainPane.add(bg);
	}
	/*
	 * Displays level Failed panel
	 */

	protected void levelFailed() {

		levelFailed = new JPanel();
		createPanel(levelFailed);

		JLabel levelFAIL = new JLabel("LEVEL FAILED!");
		levelFAIL.setFont(new Font("Castellar", Font.BOLD, 35));
		levelFAIL.setForeground(Color.BLACK);
		levelFAIL.setBounds(50, 30, 300, 150);
		levelFailed.add(levelFAIL);

		createButton (mainMenu,  50, 250, 75, 60, levelFailed);
		createButton (retry, 150, 250, 200, 60, levelFailed);

		mainMenu.setVisible(true);
		retry.setVisible(true);
	}

	/*
	 * Displays level Paused Panel
	 */
	protected void levelPaused() {

		levelPaused = new JPanel();
		createPanel(levelPaused);

		JLabel level = new JLabel("Level1");
		level.setBounds(120, 5, 300, 150);
		levelPaused.add(level);
		level.setForeground(Color.BLACK);
		level.setFont(new Font("Castellar", Font.BOLD, 40));

		createButton (resume, 85, 150, 250, 50, levelPaused);
		createButton (restart,85, 225, 250, 50, levelPaused);
		createButton (menu   ,85, 300, 250, 50, levelPaused);

		resume.setVisible(true);
		restart.setVisible(true);
		menu.setVisible(true);
	}

	/*
	 * Displays level Complete Panel
	 */
	protected void levelComplete() {

		levelComplete = new JPanel();
		createPanel (levelComplete);

		JLabel levelCOMPLETE = new JLabel("LEVEL COMPLETE");
		levelCOMPLETE.setHorizontalAlignment(SwingConstants.CENTER);
		levelCOMPLETE.setBounds(44, 11, 294, 37);
		levelComplete.add(levelCOMPLETE);
		levelCOMPLETE.setForeground(Color.BLACK);
		levelCOMPLETE.setFont(new Font("Castellar", Font.BOLD, 30));

		createButton (mainMenuL, 15, 250, 75, 60, levelComplete);
		createButton (retryL, 95, 250, 75, 60, levelComplete);
		createButton (forward, 175, 250, 200, 60, levelComplete);

		mainMenuL.setVisible(true);
		retryL.setVisible(true);
		forward.setVisible(true);

	}

	/*
	 * Sets the background, bounds and border of a panel
	 */

	protected void createPanel(JPanel panel) {

		panel.setBackground(new Color(153, 153, 255));
		panel.setBounds( 350, 30, 400, 400);
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));

	}
	/*
	 * Creates a button with the following characteristics
	 */

	private void createButton (JButton button, int x, int y, int width, int height, JPanel panel) {

		button.setBackground(Color.blue.darker());
		button.setForeground(Color.WHITE);
		button.setBounds(x, y, width, height);
		button.setFont(new Font("Castellar", Font.BOLD,  35));
		button.addActionListener(this);
		button.setFocusable(false);
		button.setVisible(false);

		Border border = new LineBorder(Color.blue, 8);
		button.setBorder(border);

		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				button.setBackground(Color.red.darker());
				Border border = new LineBorder(Color.red, 8);
				button.setBorder(border);
			}

			public void mouseExited(MouseEvent evt) {
				button.setBackground(Color.blue.darker());
				Border border = new LineBorder(Color.blue, 8);
				button.setBorder(border);
			}
		});

		panel.add (button);
	}

	/*
	 * Initializes the buttons and sets imageIcons to some of them
	 */
	private void buttons() {

		PLAY= new JButton ("PLAY", new ImageIcon (ImageFiles.playIcon));

		l1 = new JButton ("1");
		l2 = new JButton ("2");
		l3 = new JButton ("3");

		back = new JButton (new ImageIcon (ImageFiles.returnIcon));

		mainMenu = new JButton (new ImageIcon (ImageFiles.menuIcon));
		mainMenuL = new JButton (new ImageIcon (ImageFiles.menuIcon));
		retry = new JButton (new ImageIcon (ImageFiles.retryIcon));
		retryL = new JButton (new ImageIcon (ImageFiles.retryIcon));
		forward = new JButton (new ImageIcon (ImageFiles.forwardIcon));

		resume = new JButton("RESUME");
		resume.setAlignmentX(Component.CENTER_ALIGNMENT);
		restart = new JButton("RESTART");
		menu = new JButton("MENU");

		PLAY.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				PLAY.setIcon( new ImageIcon (ImageFiles.playIcon2));
			}

			public void mouseExited(MouseEvent evt) {
				PLAY.setIcon( new ImageIcon (ImageFiles.playIcon));
			}
		});
	}
	
	/*
	 * Sets all the buttons invisible
	 */

	protected void setButtonsInvisible() {

		back.setVisible(false);
		PLAY.setVisible(false);
		l1.setVisible(false);
		l2.setVisible(false);
		l3.setVisible(false);
	}
	/*
	 * Sets all the panels invisible
	 */

	protected void setPanelsInvisible() {
		levelComplete.setVisible(false);
		levelFailed.setVisible(false);
		levelPaused.setVisible(false);
	}


	/*
	 * Defines the actionlisteners of the buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() ==PLAY) {

			setButtonsInvisible();
			back.setVisible(true);

			l1.setVisible(true);
			l2.setVisible(true);
			l3.setVisible(true);

		}

		if (e.getSource()==back) {

			setButtonsInvisible();
			PLAY.setVisible(true);
		}

		if (e.getSource()==l1) {

			Bounce.bounce.frame.getContentPane().remove(Bounce.bounce.level);
			Bounce.bounce.level = new Level1();
			Bounce.ballPositionX=120;

			Bounce.bounce.initializeGameValues(Bounce.ballPositionX, 90);
			Bounce.bounce.level.setLayout(null);
			Bounce.bounce.frame.getContentPane().add(Bounce.bounce.level);

			mainPane.setVisible(false);
			Bounce.bounce.timer.start();
			Bounce.pause = false;

		}
		if (e.getSource()==l2) {
			Bounce.bounce.frame.getContentPane().remove(Bounce.bounce.level);
			Bounce.bounce.level = new Level2();
			Bounce.ballPositionX=600;

			Bounce.bounce.initializeGameValues(Bounce.ballPositionX, 90);
			Bounce.bounce.level.setLayout(null);
			Bounce.bounce.frame.getContentPane().add(Bounce.bounce.level);

			mainPane.setVisible(false);
			Bounce.bounce.timer.start();
			Bounce.pause = false;

		}
		if (e.getSource()==l3) {
			Bounce.bounce.frame.getContentPane().remove(Bounce.bounce.level);
			Bounce.bounce.level = new Level3();
			Bounce.ballPositionX=120;

			Bounce.bounce.initializeGameValues(Bounce.ballPositionX, 90);
			Bounce.bounce.level.setLayout(null);
			Bounce.bounce.frame.getContentPane().add(Bounce.bounce.level);

			mainPane.setVisible(false);
			Bounce.bounce.timer.start();
			Bounce.pause = false;	

		}

		if (e.getSource()==resume) {
			setPanelsInvisible();
			Bounce.pause = false;
			Bounce.bounce.timer.start();
		}

		if (e.getSource()==restart  || e.getSource()==retry || e.getSource()==retryL) {

			setPanelsInvisible();
			Bounce.bounce.initializeGameValues(Bounce.ballPositionX, 90);
			Bounce.bounce.timer.start();
			Bounce.pause = false;

		}

		if (e.getSource()==menu || e.getSource()==mainMenu || e.getSource()==mainMenuL) {

			setPanelsInvisible();
			mainPane.setVisible(true);
			setButtonsInvisible();

			back.setVisible(true);
			l1.setVisible(true);
			l2.setVisible(true);
			l3.setVisible(true);
			Bounce.bounce.initializeGameValues(Bounce.ballPositionX,90);
		}

		if (e.getSource()==forward) {

			setPanelsInvisible();
			setButtonsInvisible();

			String levelName = Bounce.bounce.level.getClass().getName();
			switch (levelName) {

			case "bounce.Level1":

				Bounce.bounce.frame.getContentPane().remove(Bounce.bounce.level);
				Bounce.bounce.level = new Level2();
				Bounce.ballPositionX=600;

				Bounce.bounce.initializeGameValues(Bounce.ballPositionX, 90);
				Bounce.bounce.level.setLayout(null);
				Bounce.bounce.frame.getContentPane().add(Bounce.bounce.level);

				mainPane.setVisible(false);
				Bounce.bounce.timer.start();
				Bounce.pause = false;

				break;
				
			case "bounce.Level2":
				
				Bounce.bounce.frame.getContentPane().remove(Bounce.bounce.level);
				Bounce.bounce.level = new Level3();
				Bounce.ballPositionX=120;

				Bounce.bounce.initializeGameValues(Bounce.ballPositionX, 90);
				Bounce.bounce.level.setLayout(null);
				Bounce.bounce.frame.getContentPane().add(Bounce.bounce.level);

				mainPane.setVisible(false);
				Bounce.bounce.timer.start();
				Bounce.pause = false;	
               
				break;
			
			case "bounce.Level3":
				
				setPanelsInvisible();
				mainPane.setVisible(true);
				setButtonsInvisible();

				back.setVisible(true);
				l1.setVisible(true);
				l2.setVisible(true);
				l3.setVisible(true);
				Bounce.bounce.initializeGameValues(Bounce.ballPositionX,90);
				
				
				break;
			}

		}

	}

	
}
