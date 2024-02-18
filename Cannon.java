/**
 * Represents a cannon in the game.
 * 
 * @author Alina Yildirim
 * @version 2023/08
 */

 import java.awt.*;

 class Cannon {
		 private int x, y, xPos;
 
		 /**
			* Constructs a cannon object.
			* 
			* @param x    the x-coordinate of the cannon
			* @param y    the y-coordinate of the cannon
			* @param xPos the x-position of the player
			*/
		 public Cannon(int x, int y, int xPos) {
				 this.x = x;
				 this.y = y;
				 this.xPos = xPos;
		 }
 
		 /**
			* Creates an arrow object at the cannon's position.
			* 
			* @param man the player object
			* @return the arrow object
			*/
		 public Arrow shoot(Player man) {
				 return new Arrow(10, "left", x, y + 10);
		 }
 
		 // Getter methods
		 public int getX() {
				 return x;
		 }
 
		 public int getY() {
				 return y;
		 }
 
		 public int getXPos() {
				 return xPos;
		 }
 }
 