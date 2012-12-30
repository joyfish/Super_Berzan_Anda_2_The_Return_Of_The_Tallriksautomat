package collision;

import java.util.ArrayList;

import Demo_1.GameScreen;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import entities.Plattform;
import entities.Player;
import entities.State;
import entities.Talkzone;

public class AreaChecker {
	private Player player;
	private ArrayList<Plattform> plattformList;
	private GameScreen superGame;
	private ArrayList<Talkzone> tz;

	public AreaChecker(GameScreen SuperGame, Player play) {
		player = play;
		plattformList = new ArrayList<>();
		superGame = SuperGame;
		plattformList = superGame.getPlattforms();
		tz = superGame.getTalkzones();		
	}

	public void update() {
		for (Plattform p : plattformList) {
			boolean b = isOverlapping(p);
			if (b == true) {
				if (comesFromAbove(p)){
					player.getSpeed().y = 0.1f;
					player.state = State.Standing;					
				}
				if (comesFromBelow(p)){
					player.getSpeed().y = -0.1f;
					player.getPosition().y -= 1f;					
				}
				if (comesFromLeft(p)) {
					player.getSpeed().x = -0.1f;
					player.getPosition().x -= 10f;					
				}
				if (comesFromRight(p)) {
					player.getSpeed().x = 0.1f;
					player.getPosition().x += 10;					
				}
			} else if (player.getPosition().y > 0 && player.state == State.Running){
				player.state = State.Jumprunning;
			}
		}
		
		//for(Talkzones tz : Talkzones()){
		for (int i = 0; i < tz.size(); i++) {
			Talkzone talkzone = tz.get(i);
			if(Intersector.overlapRectangles(player.getRectangle() , talkzone.getZone())){
				talkzone.entered();
			} else {
				talkzone.exited();
			}
		}
			
		//}
		
		if(player.getPosition().y <= 0){
			player.state = State.Standing;			
		}
		
		if (!(player.state == State.Standing || player.state == State.Running)) {			
			player.gravity();
		} 
	}

	private boolean comesFromAbove(Plattform p) {
		if (player.getPosition().y < p.getPosition().y + p.getSize().y
				&& player.getPosition().y + player.lookingrightIMG.getHeight() > p
						.getPosition().y + p.getTexture().getHeight()) {
			return true;
		} else {
			return false;
		}
	}

	private boolean comesFromBelow(Plattform p) {
		if (player.getPosition().y + player.getTexture().getHeight() < p.getPosition().y + p.getSize().y
				&& player.getPosition().y + player.lookingrightIMG.getHeight() > p
						.getPosition().y) {			
			return true;
		} else {
			return false;
		}
	}

	private boolean comesFromLeft(Plattform p) {
		if (player.getPosition().x + player.lookingrightIMG.getWidth() > p.getPosition().x
				&& player.getPosition().x < p.getPosition().x) {
			return true;
		} else {
			return false;
		}
	}

	private boolean comesFromRight(Plattform p) {
		if (player.getPosition().x < p.getPosition().x + p.getSize().x && player.getPosition().x +player.lookingrightIMG.getWidth() > p.getPosition().x + p.getSize().x ) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isOverlapping(Plattform p) {
		Rectangle platty = new Rectangle(p.getPosition().x, p.getPosition().y,
				p.getSize().x, p.getSize().y);
		if (Intersector.intersectRectangles(player.getRectangle(), platty)) {			
			return true;
		} else {
			return false;
		}

	}

}
