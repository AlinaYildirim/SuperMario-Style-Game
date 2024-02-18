/**
 * Represents a fireball in the game.
 * 
 * @author Alina Yildirim
 * @version 2023/08
 */

 import java.awt.*;

 public class Fireball {
		 double x, y, vy;
		 int restCount;
		 double vOrg;
		 boolean rest = false;
 
		 /**
			* Constructs a fireball object.
			* 
			* @param xx   the initial x-coordinate of the fireball
			* @param yy   the initial y-coordinate of the fireball
			* @param dist the distance of the fireball
			*/
		 public Fireball(int xx, int yy, double dist) {
				 x = xx;
				 y = yy;
				 vOrg = -dist / 50;
				 vy = vOrg;
		 }
 
		 /**
			* Simulates the fireball's jump.
			*/
		 public void jump() {
				 if (!rest) {
						 vy += 0.15;
						 y += vy;
				 }
		 }
 
		 /**
			* Resets the fireball.
			*/
		 public void reset() {
				 y = 950;
				 vy = vOrg;
				 rest = true;
		 }
 
		 /**
			* Refreshes the fireball's status.
			*/
		 public void refresh() {
				 if (rest) {
						 if (restCount >= 100) {
								 rest = false;
								 restCount = 0;
						 } else {
								 restCount++;
						 }
				 }
		 }
 
		 /**
			* Checks if the fireball is resting.
			* 
			* @return true if the fireball is resting, false otherwise
			*/
		 public boolean getRest() {
				 return rest;
		 }
 
		 /**
			* Gets the x-coordinate of the fireball.
			* 
			* @return the x-coordinate
			*/
		 public int getX() {
				 return (int) x;
		 }
 
		 /**
			* Gets the y-coordinate of the fireball.
			* 
			* @return the y-coordinate
			*/
		 public int getY() {
				 return (int) y;
		 }
 }
 