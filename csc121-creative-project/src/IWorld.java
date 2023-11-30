import processing.core.PApplet;
import processing.event.KeyEvent;

public interface IWorld {
	
    public IWorld update();
    
    PApplet draw(PApplet p);
    
    IWorld keyPressed(KeyEvent kev);

    IWorld keyReleased(KeyEvent kev);
}