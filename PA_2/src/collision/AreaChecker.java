package collision;

import inputhandler.Controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import Demo_1.Background;
import Demo_1.GameScreen;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import entities.Missile;
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
			if (willOverlap(p)) {	
//				System.out.println("Entered!! Player: " + player.getPosition() + " || plattform: " + p.getPosition() + " || Offset: " + p.getOffset());
				if (comesFromAbove(p)){			
					player.getSpeed().y = 0.1f;					
					player.state = State.Standing;
					continue;
				}
				if (comesFromBelow(p)){			
					player.getSpeed().y = -0.1f;
					player.getPosition().y -= 1f;
					continue;
				}
				if (comesFromLeftOfPlattform(p) ) {			
					player.getSpeed().x = -0.1f;
					player.getPosition().x -= 1f;
					continue;
				}
				if (comesFromRightOfPlattform(p)) {			
					player.getSpeed().x = 0.1f;
					player.getPosition().x += 1f;
					continue;
				}
				
				
			} else if (player.getPosition().y > 0 && (player.state == State.Running)){
				player.state = State.Jumprunning;
			} 
		}
		
		//Talkzone activation
		for (int i = 0; i < tz.size(); i++) {
			Talkzone talkzone = tz.get(i);
			if(Intersector.overlapRectangles(player.getScreenRextangle(), talkzone.getZone())){
				talkzone.entered();
			} else {
				talkzone.exited();
			}			
		}
		
		Talkzone jacob = superGame.getJacob();
		if(Intersector.overlapRectangles(player.getScreenRextangle(), jacob.getZone())){
			jacob.entered();
		}
		
		//Ground collision
		if(player.getPosition().y + player.getSpeed().y <= 0){
			player.state = State.Standing;
			player.getPosition().y = 0;
		}
		
		//Player at Border
		Vector2 pr = player.getPosition();
		if (pr.x <= Background.BORDERLINE) {			
			controller.playerAtLeftBorder = true;			 
		}else{
			controller.playerAtLeftBorder = false;
		}
		
		if (pr.x >= Screen.width/2 - Background.BORDERLINE) {
			controller.playerAtRightBorder = true;			
		}else{
			controller.playerAtRightBorder = false;
		}			
		
		//Destroy Missiles
		for(Thrower t : throwList){
			if(t.isReady() == false){
				Missile m = t.getMissile();
				for(Plattform p: plattformList){
					if(Intersector.intersectRectangles(m.getRectangle(), p.getRectangle())){
						t.removeMissile();
						break;
					}
				}
			}
		}
		
		//Hurt player
		for(Thrower t : throwList){
			if(t.isReady() == false){
			Missile m = t.getMissile();
			Rectangle r = new Rectangle(player.getPosition().x,player.getPosition().y,player.getRectangle().width,player.getRectangle().height);
			if(Intersector.intersectRectangles(m.getRectangle(), new Rectangle(r))){
				player.damage();
				t.removeMissile();
			}
			}
		}
		
		for (Springare spr : runners) {
			Rectangle r = new Rectangle(player.getPosition().x,player.getPosition().y,player.getRectangle().width,player.getRectangle().height);
			if(Intersector.intersectRectangles(spr.getRectangle(), new Rectangle(r))){
				player.damage();				
			}
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
		if (playerYandHeight < p.getRectangle().y + 10 && playerYandHeight > p.getRectangle().y) {			
			return true;
		} else {
			return false;
		}
	}

	private boolean comesFromLeftOfPlattform(Plattform p) {
		float playerXandWidth = player.getPosition().x + player.getSprite().getWidth();
		if (playerXandWidth > p.getRectangle().x && playerXandWidth < p.getRectangle().x+10) {
			return true;
		} else {
			return false;
		}
	}

	private boolean comesFromRightOfPlattform(Plattform p) {
		float plattformXandWidth = p.getPosition().x + p.getRectangle().width;
		if (player.getRectangle().x < plattformXandWidth && player.getPosition().x > plattformXandWidth-10 ) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isOverlapping(Plattform p) {		
		if (Intersector.intersectRectangles(player.getRectangle(), p.getRectangle())) {			
			return true;
		} else {
			return false;
		}

	}
	
	private boolean willOverlap(Plattform p){
		Rectangle platty = new Rectangle(p.getRectangle());
		Rectangle play = new Rectangle(player.getPosition().x, player.getPosition().y,player.getRectangle().width,player.getRectangle().height);
		play.x += player.getSpeed().x;
		play.y += player.getSpeed().y;
		if(Intersector.intersectRectangles(platty, play)){
			return true;
		} else {
			return false;
		}
		
	}

}
