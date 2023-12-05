import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;

/*
 * Represents a list of bullets that will appear on the 'canvas'
 */
public class Bullets {
    private ArrayList<Bullet> bullets;
    private static PImage img;

    private static final int SPACE_KEY_CODE = 32; // Key code for the spacebar

    public Bullets(PImage img) {
        bullets = new ArrayList<>();
        Bullets.img = img;
    }

    /*
     * Draws the ArrayList of Bullets on the screen
     */
    public void draw(PApplet c) {
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            bullet.draw(c);
            if (bullet.isOffscreen()) {
                bullets.remove(i);
            }
        }
    }

    /*
     * Updates all the bullets in the ArrayList to a new y position
     */
    public void updateBullets(Enemies e) {
    	
      
        
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet curBullet = bullets.get(i);

            curBullet.update(e);


            // Remove dead bullets
            if (curBullet.isDead()) {
                bullets.remove(i);
            }
            
            
        }
        }
        
        	
    

    /*
     * If the spacebar is pressed, it will add a new bullet to the ArrayList at the player's x position
     */
    public void addBullets(Player p, KeyEvent key) {
        if (key.getKeyCode() == SPACE_KEY_CODE) {
            Bullet newBullet = new Bullet(p.getPlayerX() + p.getPlayerWidth() / 2, p.getPlayerY(), img);
            bullets.add(newBullet);
        }
    }

    /*
     * Returns the ArrayList of bullets
     */
    public ArrayList<Bullet> giveBullets() {
        return new ArrayList<>(this.bullets);
    }
}


