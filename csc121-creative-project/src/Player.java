import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

/**
 * Represents a player that can move
 */
public class Player extends PApplet {
    private boolean movingRight;
    private boolean movingLeft;
    private float playerX;
    private float playerY;
    private final PImage img;  // Declare PImage variable
    private float pTop;
    private float pBottom;
    private float pRight;
    private float pLeft;
    private boolean dead;
    
    private final float playerXSpeed = 3;
    private final int playerWidth = 25;
    private final int playerHeight = 25;

    // Constructor for the player class
    Player(float playerX, PImage img) {
        this.playerX = playerX;
        this.playerY = 350;
        this.dead = false;
        this.img = img;
    }

    /**
     * Draw the player at the current position
     */
    public PApplet draw(PApplet c) {
        this.setup();
        c.fill(0, 0, 255);
        c.imageMode(CORNER);
        c.image(img, playerX, playerY, playerWidth, playerHeight);
        return c;
    }

    /**
     * Update the moving direction of the player based on the key event
     */
    public Player keyPress(KeyEvent key) {
        if (key.getKeyCode() == PApplet.RIGHT) {
            this.setMovingLeft(false);
            this.setMovingRight(true);
        } else if (key.getKeyCode() == 37) {
            this.setMovingLeft(true);
            this.setMovingRight(false);
        } /*else {
            this.setMovingLeft(false);
            this.setMovingRight(false);
        }*/
        return this;
    }

    /**
     * Reset the moving direction of the player once the appropriate key is released
     */
    public void keyReleased(KeyEvent key) {
        if (key.getKeyCode() == 39) {
            setMovingRight(false);
        } else if (key.getKeyCode() == 37) {
            setMovingLeft(false);
        }
    }

    /**
     * Update the player position based on the moving direction
     */
    public Player updatePlayer() {
        if (isMovingRight()) {
            this.setPlayerX(this.getPlayerX() + this.playerXSpeed);
        }
        if (isMovingLeft()) {
            this.setPlayerX(this.getPlayerX() - this.playerXSpeed);
        }
        return this;
    }

    /**
     * Check if a bullet is in contact with the player
     */
    public void isHit(Bullet b) {
        if (b.getbRight() < pRight && b.getbLeft() > pLeft && b.getbTop() > pTop && b.getbBottom() <= pBottom) {
            this.dead = true;
        }
    }

    // Getters and setters for various fields
    public float getPlayerX() {
        return playerX;
    }

    public void setPlayerX(float playerX) {
        this.playerX = playerX;
    }

    public int getPlayerWidth() {
        return playerWidth;
    }

    public void setPlayerWidth(int playerWidth) {
        // Not needed since playerWidth is final
    }

    public float getPlayerY() {
        return playerY;
    }

    public void setPlayerY(float playerY) {
        this.playerY = playerY;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isDead() {
        return this.dead;
    }
}