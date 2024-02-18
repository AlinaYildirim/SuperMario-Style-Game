/**
 * Represents a block in the game.
 * 
 * @author  Alina Yildirim
 * @version 23/08
 */

 import java.awt.*;
 import java.util.ArrayList;
 
 class Block {
		 private String type;
		 private int x, y;
		 private Rectangle rect;
 
		 /**
			* Constructs a block object.
			* 
			* @param xx the x-coordinate of the block
			* @param yy the y-coordinate of the block
			* @param t  the type of block
			*/
		 public Block(int xx, int yy, String t) {
				 x = xx;
				 y = yy;
				 type = t;
				 rect = new Rectangle(x, y, 50, 50);
		 }
 
		 /**
			* Gets the bounding rectangle of the block.
			* 
			* @return the bounding rectangle
			*/
		 public Rectangle getRect() {
				 return rect;
		 }
 
		 /**
			* Gets the x-coordinate of the block.
			* 
			* @return the x-coordinate
			*/
		 public int getX() {
				 return x;
		 }
 
		 /**
			* Gets the y-coordinate of the block.
			* 
			* @return the y-coordinate
			*/
		 public int getY() {
				 return y;
		 }
 }
 