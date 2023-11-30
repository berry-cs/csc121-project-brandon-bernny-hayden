import processing.core.*;
import processing.event.KeyEvent;

/**
 * Provides the scaffolding to launch a Processing application
 */
public class GameApp extends PApplet {
    private IWorld gw;
    private static PImage playerImg;
    private static PImage enemyImg;
    private static PImage bulletImg;
    private static PImage explosion;
    

    private static final int SCREEN_SIZE = 400;
    

// Adjust the delay duration in milliseconds

    public void settings() {
        this.size(SCREEN_SIZE, SCREEN_SIZE);
    }

    public void setup() {
        // Load images for player, bullets, enemies, and explosions
        playerImg = loadImage("images/Player.png");
        bulletImg = loadImage("images/Bullet.png");
        enemyImg = loadImage("images/Enemy.png");
        explosion = loadImage("images/Explosion.png");
        playerImg.mask(playerImg); // Apply mask to the player image
        
        
         

        // Initialize the game world with player, bullets, enemies, and explosions
        gw = new GameWorld( playerImg, bulletImg, enemyImg, explosion);
    }
    
    

    public void draw() {
    	
            gw = gw.update();
            gw.draw(this);
        
    }

    // Handle key press events
    public void keyPressed(KeyEvent kev) {
        gw = gw.keyPressed(kev);
    }

    // Handle key release events
    public void keyReleased(KeyEvent kev) {
        gw = gw.keyReleased(kev);
    }

    // Main method to run the Processing sketch
    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "GameApp" }, new GameApp());
    }
    
}