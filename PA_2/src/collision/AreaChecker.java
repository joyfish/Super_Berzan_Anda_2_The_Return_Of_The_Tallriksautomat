package collision;

import java.util.ArrayList;

import Demo_1.GameScreen;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import entities.Plattform;
import entities.Player;
import entities.State;

public class AreaChecker {
	private Player player;
	private ArrayList<Plattform> plattformList;
	private GameScreen superGame;

	public AreaChecker(GameScreen SuperGame, Player play) {
		player = play;
		plattformList = new ArrayList<>();
		superGame = SuperGame;
		plattformList = superGame.getPlattforms();
	}

	public void update() {
		for (Plattform p : plattformList) {
			boolean b = isOverlapping(p);
			if (b == true) {
				if (comesFromAbove(p)){
					player.speed.y = 0.1f;
					player.state = State.Standing;				
				}
				if (comesFromBelow(p)){
					player.speed.y = -0.1f;
					player.position.y -= 1f;
				}
				if (comesFromLeft(p)) {
					player.speed.x = -0.1f;
					player.position.x -= 10f;
				}
				if (comesFromRight(p)) {
					player.speed.x = 0.1f;
					player.position.x += 10;
				}
			}
		}
		
		if(player.getPosition().y <= 0){
			player.state = State.Standing;
			System.out.println("wololol");
		}
		
		if (!(player.state == State.Standing || player.state == State.Running)) {			
			player.gravity();
		} 
	}

	private boolean comesFromAbove(Plattform p) {
		if (player.getPosition().y < p.getPosition().y + p.getSize().y
				&& player.getPosition().y + player.image.getHeight() > p
						.getPosition().y + p.getTexture().getHeight()) {
			return true;
		} else {
			return false;
		}
	}

	private boolean comesFromBelow(Plattform p) {
		if (player.getPosition().y + player.getTexture().getHeight() < p.getPosition().y + p.getSize().y
				&& player.getPosition().y + player.image.getHeight() > p
						.getPosition().y) {			
			return true;
		} else {
			return false;
		}
	}

	private boolean comesFromLeft(Plattform p) {
		if (player.getPosition().x + player.image.getWidth() > p.getPosition().x
				&& player.getPosition().x < p.getPosition().x) {
			return true;
		} else {
			return false;
		}
	}

	private boolean comesFromRight(Plattform p) {
		if (player.getPosition().x < p.getPosition().x + p.getSize().x && player.getPosition().x +player.image.getWidth() > p.getPosition().x + p.getSize().x ) {
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
