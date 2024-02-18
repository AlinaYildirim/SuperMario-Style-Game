/**
 * Represents a coin in the game.
 * 
 * @author Alina Yildirim
 * @version 2023/08
 */

 import java.awt.*;

 public class Coin {
		 private int x, y, newY;
		 private double frame;
		 private boolean picked;
 
		 /**
			* Constructs a coin object.
			* 
			* @param xx the initial x-coordinate of the coin
			* @param yy the initial y-coordinate of the coin
			*/
		 public Coin(int xx, int yy) {
				 x = xx;
				 y = yy;
				 newY = yy;
				 frame = 0;
				 picked = false;
		 }
 
		 /**
			* Decreases the y-coordinate of the coin.
			*/
		 public void addNewY() {
				 newY -= 2;
		 }
 
		 /**
			* Sets the coin as picked.
			*/
		 public void setPickedTrue() {
				 picked = true;
		 }
 
		 /**
			* Increases the frame for coin graphics.
			*/
		 public void increaseFrame() {
				 if (frame > 9) {
						 frame = 0;
				 } else {
						 frame += 0.1;
				 }
		 }
 
		 /**
			* Speeds up the frame for coin graphics.
			*/
		 public void speedFrame() {
				 if (frame > 9) {
						 frame = 0;
				 } else {
						 frame += 0.6;
				 }
		 }
 
		 /**
			* Gets the current frame of the coin.
			* 
			* @return the current frame
			*/
		 public int getFrame() {
				 return (int) frame;
		 }
 
		 /**
			* Checks if the coin is picked.
			* 
			* @return true if the coin is picked, false otherwise
			*/
		 public boolean getPicked() {
				 return picked;
		 }
 
		 /**
			* Gets the new y-coordinate of the coin.
			* 
			* @return the new y-coordinate
			*/
		 public int getNewY() {
				 return newY;
		 }
 
		 /**
			* Gets the x-coordinate of the coin.
			* 
			* @return the x-coordinate
			*/
		 public int getX() {
				 return x;
		 }
 
		 /**
			* Gets the y-coordinate of the coin.
			* 
			* @return the y-coordinate
			*/
		 public int getY() {
				 return y;
		 }
 }
 