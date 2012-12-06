package gameLogic;

import world.GameScreen;

import com.badlogic.gdx.math.Vector2;

import entities.Background;
import entities.Player;

public class Controller {

	public GameScreen gamescreen;
	public Player p;
	public Background b;

	public Controller(GameScreen Gamescreen) {
		gamescreen = Gamescreen;
		p = gamescreen.getPlayer();
		b = gamescreen.getBackground();
	}

	public void move(boolean leftDown, boolean rightDown, boolean downDown, boolean upDown){
		if(leftDown){
			p.setSpeed(new Vector2(p.getSpeed().add(-p.getAcceleration(),0)));
		}
		if(rightDown){
			p.setSpeed(new Vector2(p.getSpeed().add(p.getAcceleration(),0)));
		}
		if(downDown){
			p.setSpeed(new Vector2(p.getSpeed().add(0,-p.getAcceleration())));
		}
		if(upDown){
			p.setSpeed(new Vector2(p.getSpeed().add(0,p.getAcceleration())));
		}
		if(p.getSpeed().x > p.getMaxSpeed()){
			p.setSpeed(new Vector2(p.getMaxSpeed(),p.getSpeed().y));
		}
		if(p.getSpeed().y > p.getMaxSpeed()){
			p.setSpeed(new Vector2(p.getSpeed().x,p.getMaxSpeed()));
		}
		if(p.getSpeed().x < -p.getMaxSpeed()){
			p.setSpeed(new Vector2(-p.getMaxSpeed(),p.getSpeed().y));
		}
		if(p.getSpeed().y < -p.getMaxSpeed()){
			p.setSpeed(new Vector2(p.getSpeed().x,-p.getMaxSpeed()));
		}
		
		p.setSpeed(new Vector2(p.getSpeed().x * p.deAcceleration, p.getSpeed().y * p.deAcceleration));
		if(!leftDown && p.getSpeed().x < 0 && p.getSpeed().x > -p.getStopSpeed()){
			p.setSpeed(new Vector2(0,p.getSpeed().y));
		}

		if(!rightDown && p.getSpeed().x > 0 && p.getSpeed().x < p.getStopSpeed()){
			p.setSpeed(new Vector2(0,p.getSpeed().y));
		}
		
		p.setPosition(p.getPosition().add(p.getSpeed()));
		
	}

}
