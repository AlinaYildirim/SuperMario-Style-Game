/**
 * This class creates the Player of the game
 *
 *
 * @author Alina Yildirim
 * @version 2023/08
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Player {
	/* Properties */
	private int maxDamage, minDamage; // Maximum and minimum damage
	private double x, xPos, yPos, vx, vy, sx, sy; // Coordinates, velocities, and dimensions
	private String direction, type = "archer"; // Direction the player is facing and player type
	private boolean jump, ground; // Jumping and grounded state
	private int screenX = 1900, screenY = 1000, lives; // Screen dimensions and player lives
	double health; // Player health
	private int green, black, red, grey; // Colors for collision detection
	private BufferedImage mask = null; // Image mask for collision detection
	
	/* Constructor */
	public Player(BufferedImage image){
		x = 0; // Position of background image on screen
		xPos = 100; // Player X
		yPos = 400; // Player Y
		vx = 0;
		vy = 0;
		direction = "right";
		jump = false;
		mask = image;
		green = getPixelCol(mask, 25, 25); // Platform color
		red = getPixelCol(mask, 75, 25); // Lava color
		black = getPixelCol(mask, 225, 25); // Cannons color
		grey = getPixelCol(mask, 475, 25);
		ground = false;
		lives = 1;
		health = 100;
	}
	
	/* Method to load the player image */
	public void loadImage(BufferedImage image){
		mask = image;
	}

    /////////////////////////////// PIXEL COLOUR /////////////////////////////////
    
    /* Gets the color of a pixel in the image */
    public int getPixelCol(BufferedImage image, int xx, int yy){
    	return image.getRGB(xx + (int)x, yy);
    }
    
    /* Checks for collision with top of player */
    public boolean getColTop(BufferedImage image, int xx, int yy, int col){
    	boolean b = false;
    	int c = image.getRGB(xx + (int) x, yy);
    	for (int d = 0; d < 68; d++){
    		c = image.getRGB(d + xx + (int) x, yy);
    		if (c == col){
    			b = true;
    		}
    	}
    	return b;
    }
    
    /* Checks for collision with bottom of player */
    public boolean getColBottom(BufferedImage image, int xx, int yy, int col){
    	boolean b = false;
    	int c = image.getRGB(xx + (int )x, yy + 84);
    	for (int d = 0; d < 68; d++){
    		c = image.getRGB(d + xx + (int) x, yy + 84);
    		if (c == col){
    			b = true;
    		}
    	}
    	return b;
    }
    
    /* Checks for collision with right side of player */
    public boolean getColRight(BufferedImage image, int xx, int yy, int col){
    	boolean b = false;
    	int c = image.getRGB(xx + (int) x + 68, yy + 84);
    	for (int d = 0; d < 84; d++){
    		c = image.getRGB(xx + (int) x + 68, yy + d);
    		if (c == col){
    			b = true;
    		}
    	}
    	return b;
    }
    
    /* Checks for collision with left side of player */
    public boolean getColLeft(BufferedImage image, int xx, int yy, int col){
    	boolean b = false;
    	int c = image.getRGB(xx + (int) x - 1, yy + 84);
    	for (int d = 0; d < 84; d++){
    		c = image.getRGB(xx + (int) x - 1, yy + d);
    		if (c == col){
    			b = true;
    		}
    	}
    	return b;
    }
	
	/////////////////////////////// VERTICAL MOVEMENT ///////////////////////////////
	
	/* Handles player jumping behavior */
	public void jump(){
		if (vy < 0){ // If already jumping (in motion)
			for (int i = 0; i < (int) vy * -1; i++){
				if (!getColTop(mask, (int) xPos, (int) yPos, green) && !getColTop(mask, (int) xPos, (int) yPos, black)){
					yPos -= 1; // Move up if no block or cannon on top
					ground  = false;
				}
				else{
					vy = 0;
					ground = false;
				}
			}
		}
		else{
			for (int i = 0; i < (int) vy; i++){
				if (!getColBottom(mask, (int) xPos, (int) yPos, green) && !getColBottom(mask, (int) xPos, (int) yPos, black)){
					yPos += 1; // Move down if no block or cannon below
				}
				else{
					vy = 0;
					jump = false;
					ground = true;
				}
			}
		}
		vy += 1;
		if (getColBottom(mask, (int) xPos, (int) yPos, grey)){ // If hit lava, set health to 0
			health = 0;
		}
	}
	/* Handles player falling behavior */
	public void fall(){
		for (int i = 0; i < (int) vy; i++){
			if (!getColBottom(mask, (int) xPos, (int) yPos, green) && !getColBottom(mask, (int) xPos, (int) yPos, black)){//if not on block or cannon move down
				yPos += 1;
				ground = false;
			}
			else{
				vy = 0;
				jump = false;
				ground = true;
			}
		}
		vy += 1;
		if (getColBottom(mask, (int) xPos, (int) yPos, grey)){//if hit lava set health to 0
			health = 0;
		}
	}
	//////////////////////////////HORIZONTAL MOVEMENT//////////////////////////////

	/* Handles player moving behavior */
	public void move(String d){//move method
		if (vx < 5){//if not at max speed add 0.4 to velocity
			vx += 0.4;
		}
		if (d.equals("right")){
			direction = "right";
			for (int i = 0; i < (int) vx; i++){
				if (!getColRight(mask, (int) xPos, (int) yPos, green) && !getColRight(mask, (int) xPos, (int) yPos, black)){//can move if no block to right
					if (xPos <= 890){
						xPos += 1;//(int) vx;
					}
					else if (x >= 23090){//if at edge of map
						if (xPos < 1830){ //max distance
							xPos += 1;//(int) vx;
						}
					}
					else{
						x += 1;//(int) vx;
					}
				}
			}
		}
		else{
			direction = "left";
			for (int i = 0; i < (int) vx; i++){
				if (!getColLeft(mask, (int) xPos, (int) yPos, green) && !getColLeft(mask, (int) xPos, (int) yPos, black)){//if no block to left can move left
					if (xPos >= 910){
						xPos -= 1;//(int) vx;
					}
					else if (x <= 10){
						if (xPos > 10){ //min distance
							xPos -= 1;//(int) vx;
						}
					}
					else{
						x -= 1;//(int) vx;
					}
				}
			}
		}
		if (getColBottom(mask, (int) xPos, (int) yPos, grey)){//if hit lava set health to 0
			health = 0;
		}
	}
	
	/* Handles player pace */
	public void slowDown(String d){
		if (vx > 0){
			vx -= 0.3;
		}
		if (d.equals("right")){
			direction = "right";
			if (!getColRight(mask, (int) xPos + (int) vx, (int) yPos, green) && !getColRight(mask, (int) xPos, (int) yPos, black)){//if no block to the right
				if (xPos <= 890){
					xPos += (int) vx;
				}
				else if (x >= 23090){//if at rightmost edge of map
					if (xPos < 1830){ //max distance
						xPos += (int) vx;
					}
				}
				else{
					x += (int) vx;
				}
			}
		}
		else{
			direction = "left";
			if (!getColLeft(mask, (int) xPos - (int) vx, (int) yPos, green) && !getColLeft(mask, (int) xPos, (int) yPos, black)){//if no block to the left
				if (xPos >= 910){
					xPos -= (int) vx;
				}
				else if (x <= 10){//if at leftmost edge of map
					if (xPos > 10){ //min distance
						xPos -= (int) vx;
					}
				}
				else{
					x -= (int) vx;
				}
			}
		}
		if (getColBottom(mask, (int) xPos, (int) yPos, grey)){//if hit lava health set to 0
			health = 0;
		}
	}
	////////////////////////////GETTERS AND SETTERS////////////////////////////////
	public int getX(){
		return (int) x;
	}
	
	public int getXPos(){
		return (int) xPos;
	}
	
	public void setX(double d){
		x = d;
	}
	public void setXPos(double d){
		xPos = d;
	}
	
	public int getYPos(){
		return (int) yPos;
	}
	public void setYPos(double d){
		yPos = d;
	}
		
	public int getVx(){
		return (int) vx;
	}
	
	public void setVx(int vx){
		this.vx = vx;
	}
	
	public int getVy(){
		return (int) vy;
	}
	
	public void setVy(int vy){
		this.vy = vy;
	}
	
	public void addVy(double v){
		vy += v;
	}
	
	public boolean getJump(){
		return jump;
	}
	
	public void setJump(boolean b){
		jump = b;
	}
	
	public void setGround(boolean b){
		ground = b;
	}
	
	public boolean getGround(){
		return ground;
	}
	
	public int getHealth(){
		return (int)health;
	}
	///////////////////////////////////////////////////////////////////////////////
	public void takeDamage(double d){//method for taking damage
		health -= d;
		if (health < 0){//if health would be negative set health to 0
			health = 0;
		}
	}
	public void addHealth(double i){//method for adding health
		health += i;
		if (health > 100){//if health would be greater than 100 set health to 100
			health = 100;
		}
	}
	
	public void addLife(){
		lives++;
	}
	
	public void subtractLife(){
		lives--;
	}
	
	public int getLives(){
		return lives;
	}
}