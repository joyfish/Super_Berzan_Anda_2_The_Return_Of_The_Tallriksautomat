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
				if (comesFromLeftOfPlattform(p) && (player.state != State.Standing || player.state != State.Running)) {
					player.getSpeed().x = -0.1f;
					player.getPosition().x -= 5f;					
				}
				if (comesFromRightOfPlattform(p)  && (player.state != State.Standing || player.state != State.Running)) {
					player.getSpeed().x = 0.1f;
					player.getPosition().x += 5f;					
				}
			} else if (player.getPosition().y > 0 && (player.state == State.Running)){
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
		float plattformYandHeight = p.getPosition().y + p.getSize().y;
		if (player.getPosition().y < plattformYandHeight
				&& player.getPosition().y  > plattformYandHeight - 10) {
			return true;
		} else {
			return false;
		}
	}

	private boolean comesFromBelow(Plattform p) {
		float playerYandHeight = player.getPosition().y + player.getTexture().getHeight();		
		if (playerYandHeight < p.getPosition().y + 10 && playerYandHeight > p.getPosition().y) {			
			return true;
		} else {
			return false;
		}
	}

	private boolean comesFromLeftOfPlattform(Plattform p) {
		float playerXandWidth = player.getPosition().x + player.lookingrightIMG.getWidth();
		if (playerXandWidth > p.getPosition().x && playerXandWidth < p.getPosition().x+10) {
			return true;
		} else {
			return false;
		}
	}

	private boolean comesFromRightOfPlattform(Plattform p) {
		float plattformXandWidth = p.getPosition().x + p.getSize().x;
		if (player.getPosition().x < plattformXandWidth && player.getPosition().x > p.getPosition().x + p.getSize().x-10 ) {
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
