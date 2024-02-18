/**
 * main.java
 * This class represents the main entry point of the Super Mario Bros game.
 * It creates a JFrame window and initializes the game panel.
 * It also handles keyboard events for controlling the game.
 *
 * @author Alina Yildirim
 * @version  2023/08
 */

 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 
 public class main extends JFrame implements ActionListener, KeyListener {
		 // Instance variables
		 private Timer myTimer; // Timer for game updates
		 private GamePanel game; // Game panel
		 private int screenX = 1900; // Screen width
		 private int screenY = 1000; // Screen height
		 
		 /**
			* Constructs the main JFrame window and initializes the game.
			*/
		 public main() {
				 super("Super Mario Bros"); // Set window title
				 setSize(screenX, screenY); // Set window size
				 myTimer = new Timer(10, this); // Create timer
				 myTimer.start(); // Start timer
				 game = new GamePanel(); // Initialize game panel
				 add(game); // Add game panel to JFrame
				 addKeyListener(this); // Add key listener
				 setResizable(false); // Disable window resizing
				 setVisible(true); // Make window visible
				 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
		 }
		 
		 /**
			* Performs actions based on timer events.
			*/
		 public void actionPerformed(ActionEvent e) {
				 if (game != null) {
						 game.refresh(); // Update game state
						 game.repaint(); // Repaint game panel
				 }
		 }
		 
		 /**
			* Handles key typed events (not used).
			*/
		 public void keyTyped(KeyEvent e) {}
		 
		 /**
			* Handles key pressed events.
			*/
		 public void keyPressed(KeyEvent e) {
				 game.setKey(e.getKeyCode(), true); // Set key as pressed in game
		 }
		 
		 /**
			* Handles key released events.
			*/
		 public void keyReleased(KeyEvent e) {
				 game.setKey(e.getKeyCode(), false); // Set key as released in game
		 }
		 
		 /**
			* Main method to start the game.
			*/
		 public static void main(String[] args) {
				 new main(); // Create main object
		 }
 }
 