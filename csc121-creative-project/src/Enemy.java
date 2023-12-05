import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/*
 * Represents an individual enemy object in the game, managing its position, behavior, and interactions with other game elements.
 */
public class Enemy extends PApplet {
    private float enemyX;
    private float enemyY;

    private float eBottom;
    private float eLeft;
    private float eRight;
    private boolean dead;
    private int counter;
    private Player p;

    private static PImage img;
    private static PImage explosion;
    private static final int COLLISION_CHECK_INTERVAL = 5;
    private static final float ENEMY_Y_SPEED = 1;
    private static final int ENEMY_HEIGHT = 20;
    private static final int ENEMY_WIDTH = 20;// Interval for collision check

    Enemy(float enemyX, float enemyY, PImage img, PImage explosion, Player p) {
        this.enemyX = enemyX;
        this.enemyY = enemyY;
        Enemy.img = img;
        Enemy.explosion = explosion;
        this.eBottom = (float) (enemyY + (ENEMY_HEIGHT));
        this.eLeft = (float) (enemyX - (ENEMY_WIDTH));
        this.eRight = (float) (enemyX + (ENEMY_WIDTH));
        counter = 0;
        this.dead = false;
        this.p = p;
    }

    /*
     * Draws the enemy on the screen, showing explosion if dead
     */
    public PApplet draw(PApplet c) {
        if (!dead) {
            c.fill(255, 0, 0);
            c.imageMode(CORNER);
            c.image(Enemy.img, enemyX, enemyY, ENEMY_WIDTH, ENEMY_HEIGHT);
            return c;
        } else {
            c.fill(255, 0, 0);
            c.imageMode(CORNER);
            c.image(Enemy.explosion, enemyX, enemyY, ENEMY_WIDTH, ENEMY_HEIGHT);
            return c;
        }
    }

    /*
     * Updates the position of the enemy and checks for bullet collisions
     */
    public Enemy updateEnemy(Bullets b) {
        ArrayList<Bullet> bullets = b.giveBullets();

        for (int i = 0; i < bullets.size(); i++) {
            this.isHit(bullets.get(i));
            if (this.dead) {
                return this;
            }
        }
        

        // Staggered start for each row
        int rowOffset = 5; // Adjust the offset as needed
        int row = (int) ((enemyY - rowOffset) / 30); // Assuming each row has 30 units of spacing

        // Alternating left or right direction based on the row
        int direction = row % 2 == 0 ? 1 : -1; // Alternates between 1 (right) and -1 (left)

        // Add logic for the movement pattern
        // Adjust enemyY and enemyX based on the desired pattern
        // You may use sin, cos, or other functions for a more dynamic movement

        // Example:
        float delay = row * 500; // Adjust the delay between rows as needed
        enemyY = enemyY + (ENEMY_Y_SPEED / 3);

        // Adjust enemyX based on the alternating direction
        enemyX = (float) (enemyX + direction * Math.sin((counter + delay) * 0.1) / 2);

        this.eBottom = (float) (enemyY + (ENEMY_HEIGHT));
        this.eLeft = (float) (enemyX - (ENEMY_WIDTH));
        this.eRight = (float) (enemyX + (ENEMY_WIDTH));
        counter++;

        

        return this;
    }

    /*
     * Determines if a bullet comes in contact with an enemy
     */
    public void isHit(Bullet b) {
        if ((b.getbTop() < eBottom) && ((b.getbRight() > eLeft && b.getbLeft() < eLeft) || ((b.getbLeft() < eRight) && (b.getbRight() > eRight)) || ((b.getbLeft() > eLeft) && (b.getbRight() < eRight)))) {
            
        	this.dead = true;
        }
    }

    /*
     * Determines if the current enemy is dead
     */
    public boolean isDead() {
        return this.dead;
    }
    
   

    /*
     * Checks if the game is over
     */
    public boolean gameOver() {
        return enemyY > 340 && enemyY < 360 && p.getPlayerX() + 20 > enemyX && p.getPlayerX() - 20 < enemyX;
    }
    
    public boolean offScreen() {
    	return enemyY > 400 || enemyX > 400 || enemyX < 0;
    }
    
    public float getEBottom() {
    	return this.eBottom;
    }
    
    public float getERight() {
    	return this.eRight;
    }
    
    public float getELeft() {
    	return this.eLeft;
    }
    
    
}