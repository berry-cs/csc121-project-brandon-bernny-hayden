import java.io.*;
import processing.core.*;
import processing.event.KeyEvent;

/**
 * Represents an interactive application where a player can move and hit 
 * enemies with ships while moving and enemies firing back
 */
public class GameWorld implements IWorld {
    private Player p;
    private Bullets bullets;
    private Enemies enemies;
    private static final int TEXT_SIZE_World = 20;
    private static final int TEXT_COLOR_World = 255;
    private static final int PLAYER_START_POSITION = 200;
    private PImage playerImg;
    private PImage bulletImg;
    private PImage enemyImg;
    private PImage explosion;
    
    boolean gameOver;
    
    

    /**
     * Creates a game world given a Player object, a Bullets object, and enemy images
     */
    public GameWorld(PImage playerImg, PImage bulletImg, PImage enemyImg, PImage explosion) {
        p = new Player(PLAYER_START_POSITION, playerImg);
        bullets = new Bullets(bulletImg);
        enemies = new Enemies(enemyImg, explosion);
        gameOver = gameOver();
        this.playerImg = playerImg;
        this.bulletImg = bulletImg;
        this.enemyImg = enemyImg;
        this.explosion = explosion;
        
    }

    /**
     * Renders a picture of the Player and Bullets on the canvas
     */
    public PApplet draw(PApplet c) {
    	
            enemies.removeDead();
            c.background(0);
            p.draw(c);
            bullets.draw(c);
            enemies.draw(c);
            c.textAlign(PConstants.TOP, PConstants.RIGHT); // Set the text alignment to center
            c.textSize(TEXT_SIZE_World);
            c.fill(TEXT_COLOR_World);
            String score = enemies.getScore() + "";
            c.text(("SCORE: " + score), c.width - 90, 40);
            
        return c;
    }

    /**
     * Checks if the game is over
     */
    public boolean gameOver() {
        boolean isGameOver = enemies.gameOver();
        
        return isGameOver;
    }
    
    public String getScore() {
    	return enemies.getScore() + "";
    }

    /**
     * Produces an updated world where the player moves left or right and the bullets fly up the screen
     */
    public IWorld update() {
    	if (gameOver()) {
    		return new EndWorld(this);
    	}
    	
        enemies.updateEnemies(bullets);
        p.updatePlayer();
        bullets.updateBullets();
        
        return this;
    }


    /**
     * Produces an updated world where the player moves and adds bullets to the Bullets class
     */
    public IWorld keyPressed(KeyEvent key) {
        p = p.keyPress(key);
        bullets.addBullets(p, key);
        return this;
    }

    /**
     * Produces an updated world where the player is not allowed to move
     */
    public IWorld keyReleased(KeyEvent key) {
        p.keyReleased(key);
        return this;
    }
    
    public GameWorld restart() {
        p = new Player(PLAYER_START_POSITION, playerImg);
        bullets = new Bullets(bulletImg);
        enemies = new Enemies(enemyImg, explosion);
        return this;
    }
    
    
    
}


    
 
    

    

    

