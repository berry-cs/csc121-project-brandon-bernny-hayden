import processing.core.PApplet;
import processing.event.KeyEvent;

public class EndWorld implements IWorld {
    private LeaderBoard lb;
    private GameWorld gw;

    public EndWorld(GameWorld gw) {
    	this.gw = gw;
        lb = new LeaderBoard();
        lb.registerGame(gw.getScore());
    }
    
	@Override
	public IWorld update() {
		return this;
	}

	@Override
	public PApplet draw(PApplet c) {
		lb.setScore(gw.getScore()+"");
        lb.draw(c); 
        lb.setBeenThrough();
        
        return c;
	}

	@Override
	public IWorld keyPressed(KeyEvent key) {
        if (Character.toLowerCase(key.getKey()) == 'r') {
           this.gw.restart();
           return gw;
        } else {
        	return this;
        }
	}

	@Override
	public IWorld keyReleased(KeyEvent kev) {
		// TODO Auto-generated method stub
		return this;
	}

}
