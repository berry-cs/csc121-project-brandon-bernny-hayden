import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

/*
 * Represents a single bullet with x & y position and constant speed
 */
public class Bullet {
    private float x;
    private float y;
    private static PImage img;
    private float bTop;
    private float bBottom;
    private float bRight;
    private float bLeft;
    
    private static final float SPEED = 5; // Bullet speed
    private static final float BULLET_HITBOX_OFFSET = 1; // Constant for bullet hitbox offset


    Bullet(float x, float y, PImage img) {
        this.x = x;
        this.y = y;
        Bullet.img = img;
        setbTop(y - BULLET_HITBOX_OFFSET);
        setbBottom(y + BULLET_HITBOX_OFFSET);
        setbRight(x + BULLET_HITBOX_OFFSET);
        setbLeft(x - BULLET_HITBOX_OFFSET);
    }

    /*
     * Draws a single bullet on the 'canvas'
     */
    public void draw(PApplet c) {
        c.imageMode(PConstants.CORNER);
        c.image(img, x, y, 10, 10);
    }

    /*
     * Determines if a Bullet has flown off the screen
     */
    boolean isOffscreen() {
        return y < 0;
    }

    /*
     * Returns an updated bullet to a new y position
     */
    public Bullet update() {
        if (this.y < 400) {
            this.y -= SPEED;
            setbTop(y - BULLET_HITBOX_OFFSET);
            setbBottom(y + BULLET_HITBOX_OFFSET);
        }
        return this;
    }

    /*
     * Returns the bullet left hitbox boundary
     */
    public float getbLeft() {
        return bLeft;
    }

    /*
     * Sets the bullet left hitbox boundary
     */
    public void setbLeft(float bLeft) {
        this.bLeft = bLeft;
    }

    /*
     * Returns the bullet right hitbox boundary
     */
    public float getbRight() {
        return bRight;
    }

    /*
     * Sets the bullet right hitbox boundary
     */
    public void setbRight(float bRight) {
        this.bRight = bRight;
    }

    /*
     * Returns the bullet top hitbox boundary
     */
    public float getbTop() {
        return bTop;
    }

    /*
     * Sets the bullet top hitbox boundary
     */
    public void setbTop(float bTop) {
        this.bTop = bTop;
    }

    /*
     * Returns the bullet bottom hitbox boundary
     */
    public float getbBottom() {
        return bBottom;
    }

    /*
     * Sets the bullet bottom hitbox boundary
     */
    public void setbBottom(float bBottom) {
        this.bBottom = bBottom;
    }
    
    /*
     * Returns the x position
     */
    public float getX() {
    	return x;
    }
    
    /*
     * Returns the y position
     */
    public float getY() {
    	return y;
    }
    
    /*
     * Returns the bullet speed
     */
    public static float getSpeed() {
    	return SPEED;
    }
    
    /*
     * Returns the bullet hitbox
     */
    public static float getHitbox() {
    	return BULLET_HITBOX_OFFSET;
    }
}