// Arrow
// Author: Alina Yildirim
// Version: 2023/08

import java.awt.*;

class Arrow {
    private int damage, x, y, width = 50, height = 25;
    private String direction;

    /**
     * Constructs an arrow object.
     * 
     * @param damage    the damage inflicted by the arrow
     * @param direction the direction in which the arrow is facing ("left" or "right")
     * @param x         the initial x-coordinate of the arrow
     * @param y         the initial y-coordinate of the arrow
     */
    public Arrow(int damage, String direction, int x, int y) {
        this.damage = damage;
        this.direction = direction;
        this.x = x;
        this.y = y + 10; // add 10 to move bullet down so standing on cannon doesn't collide
    }

    /**
     * Moves the arrow based on its direction.
     */
    public void move() {
        if (direction.equals("left")) {
            x -= 10;
        }
        if (direction.equals("right")) {
            x += 10;
        }
    }

    /**
     * Gets the x-coordinate of the arrow.
     * 
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the arrow.
     * 
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the bounding rectangle of the arrow.
     * 
     * @param man the player object
     * @return the bounding rectangle
     */
    public Rectangle getRect(Player man) {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Gets the real bounding rectangle of the arrow (adjusted for player position).
     * 
     * @param man the player object
     * @return the real bounding rectangle
     */
    public Rectangle getRealRect(Player man) {
        return new Rectangle(x - man.getXPos(), y, width, height);
    }

    /**
     * Gets the damage inflicted by the arrow.
     * 
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }
}
