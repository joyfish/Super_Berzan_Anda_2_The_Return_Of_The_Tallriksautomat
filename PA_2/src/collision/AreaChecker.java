package collision;

import inputhandler.Controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import Demo_1.Background;
import Demo_1.GameScreen;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import entities.Plattform;
import entities.Player;
import entities.Springare;
import entities.State;
import entities.Talkzone;
import entities.Thrower;

public class AreaChecker {
	private Player player;
	private ArrayList<Plattform> plattformList;
	private GameScreen superGame;
	private ArrayList<Talkzone> tz;
	private ArrayList<Thrower> throwList;
	private ArrayList<Springare> runners;
	private Dimension Screen;
	private Controller controller;
	
	public AreaChecker(GameScreen SuperGame) {
		Screen = Toolkit.getDefaultToolkit().getScreenSize();
		plattformList = new ArrayList<>();
		superGame = SuperGame;		
	}
	
	public void initialize(){
		player = superGame.getPlayer(); 
		plattformList = superGame.getPlattforms();
		tz = superGame.getTalkzones();
		throwList = superGame.getThrowers();
		runners = superGame.getRunners();
		controller = superGame.getController();
	}

	public void update() {
		//Plattform collision
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
		
		for (int i = 0; i < tz.size(); i++) {
			Talkzone talkzone = tz.get(i);
			if(Intersector.overlapRectangles(player.getRectangle() , talkzone.getZone())){
				talkzone.entered();
			} else {
				talkzone.exited();
			}
		}
					
		//Ground collision
		if(player.getPosition().y + player.getSpeed().y <= 0){
			player.state = State.Standing;
			player.getPosition().y = 0;
		}
		
		//Player at Border
		Rectangle pr = player.getRectangle();
		if (pr.x <= Background.BORDERLINE) {			
			controller.playerAtLeftBorder = true;			 
		}else{
			controller.playerAtLeftBorder = false;
		}
		
		if (pr.x + pr.width >= Screen.width/2 - Background.BORDERLINE) {
			controller.playerAtRightBorder = true;			
		}else{
			controller.playerAtRightBorder = false;
		}			
		
		//Gravity
		if (!(player.state == State.Standing || player.state == State.Running)) {			
			player.gravity();
		} 
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
		float playerYandHeight = player.getPosition().y + player.getSprite().getHeight();		
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
