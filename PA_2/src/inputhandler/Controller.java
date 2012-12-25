package inputhandler;

import Demo_1.Background;
import Demo_1.GameScreen;

import com.badlogic.gdx.math.Vector2;

import entities.Player;
import entities.State;

public class Controller {

	public GameScreen gamescreen;
	public Player p;
	public Background b;
	
	public Controller(GameScreen Gamescreen) {
		gamescreen = Gamescreen;
		p = gamescreen.getPlayer();
		b = gamescreen.getBackground();
	}

	public void move(boolean leftDown, boolean rightDown, boolean downDown,
			boolean upDown) {		
		 
		if (p.state == State.Standing) {
			if (leftDown) {
				p.setSpeed(new Vector2(p.getSpeed()
						.add(-p.getAcceleration(), 0)));
				p.state = State.Running;
			}
			if (rightDown) {
				p.setSpeed(new Vector2(p.getSpeed().add(p.getAcceleration(), 0)));
				p.state = State.Running;
			}
			if(upDown){
				p.jump();
				p.state = State.Jumping;
			}
		}
		
		if (p.state == State.Jumping){			
			if(leftDown){
				p.setSpeed(new Vector2(p.getSpeed().add(-p.getAcceleration(),0)));
			}
			if(rightDown){
				p.setSpeed(new Vector2(p.getSpeed().add(p.getAcceleration(),0)));
			}
		}
						
			if (p.state == State.Running) {				
				if (upDown) {
				p.jump();
				p.state = State.Jumprunning;
				}				
			} 
		
		
		

		if (p.getSpeed().x > p.getMaxSpeed()) {
			p.setSpeed(new Vector2(p.getMaxSpeed(), p.getSpeed().y));
		}
		if (p.getSpeed().y > p.getMaxJumpSpeed()) { 
			p.setSpeed(new Vector2(p.getSpeed().x, p.getMaxJumpSpeed()));
		}
		if (p.getSpeed().x < -p.getMaxSpeed()) {
			p.setSpeed(new Vector2(-p.getMaxSpeed(), p.getSpeed().y));
		}
		if (p.getSpeed().y < -p.getMaxJumpSpeed()) {
			p.setSpeed(new Vector2(p.getSpeed().x, -p.getMaxJumpSpeed()));
		}		
		
		p.setPosition(p.getPosition().add(p.getSpeed()));

	}

}
