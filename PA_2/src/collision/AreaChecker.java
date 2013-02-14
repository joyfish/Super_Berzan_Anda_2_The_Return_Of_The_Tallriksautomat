package collision;

import java.util.ArrayList;

import Demo_1.GameScreen;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import entities.Missile;
import entities.Plattform;
import entities.Player;
import entities.State;
import entities.Talkzone;
import entities.Thrower;

public class AreaChecker {
	private Player player;
	private ArrayList<Plattform> plattformList;
	private GameScreen superGame;
	private ArrayList<Talkzone> tz;
	private ArrayList<Thrower> throwList;
//	private ArrayList<Missile> missileList;
	
	public AreaChecker(GameScreen SuperGame) {
		plattformList = new ArrayList<>();
		superGame = SuperGame;		
	}
	
	public void initialize(){
		player = superGame.getPlayer(); 
		plattformList = superGame.getPlattforms();
		tz = superGame.getTalkzones();
		throwList = superGame.getEnemies();		
	}

	public void update() {				
		for (Plattform p : plattformList) {
			boolean b = isOverlapping(p);
			if (willOverlap(p)) {
				if (comesFromAbove(p)){
					player.getSpeed().y = 0.1f;
					player.getPosition().y = p.getPosition().y+p.getSize().y;
					player.state = State.Standing;					
				}
				if (comesFromBelow(p)){
					player.getSpeed().y = -0.1f;
					player.getPosition().y -= 1f;					
				}
				if (comesFromLeftOfPlattform(p) && (player.state != State.Standing)) {
					player.getSpeed().x = -0.1f;
					player.getPosition().x -= 5f;					
				}
				if (comesFromRightOfPlattform(p)  && (player.state != State.Standing)) {
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
		
		if(player.getPosition().y + player.getSpeed().y <= 0){
			player.state = State.Standing;
			player.getPosition().y = 0;
		}
		
		if (!(player.state == State.Standing || player.state == State.Running)) {			
			player.gravity();
		} 
	}

	private boolean isOutOfBounds(Vector2 position) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean comesFromAbove(Plattform p) {
		float plattformYandHeight = p.getPosition().y + p.getSize().y;
		if (player.getPosition().y + player.getSpeed().y < plattformYandHeight
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
	
	private boolean willOverlap(Plattform p){
		Rectangle platty = new Rectangle(p.getPosition().x,p.getPosition().y, p.getSize().x,p.getSize().y);
		Rectangle play = new Rectangle(player.getRectangle());
		play.x += player.getSpeed().x;
		play.y += player.getSpeed().y;
		if(Intersector.intersectRectangles(platty, play)){
			return true;
		} else {
			return false;
		}
		
	}

}
