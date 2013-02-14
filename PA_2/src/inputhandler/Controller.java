package inputhandler;

import java.util.ArrayList;

import Demo_1.Background;
import Demo_1.GameScreen;
import Demo_1.Painter;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import entities.Player;
import entities.State;
import entities.Talkzone;

public class Controller {

	private GameScreen gamescreen;
	private Player p;
	private Background b;
	public Boolean dontmoveL = false, dontmoveR = false;	
	private ArrayList<Talkzone> talkzoneList;
	private Painter painter;
	public boolean playerAtLeftBorder = false;
	public boolean playerAtRightBorder = false;
	private float lastSideSpeed = 0; 
	private Vector2 offset;
	boolean flag = true;
	public Controller(GameScreen Gamescreen) {
		gamescreen = Gamescreen;		
	}
	
	public void initialize(){
		p = gamescreen.getPlayer();
		b = gamescreen.getBackground();
		talkzoneList = b.getTalkzones();
		painter = gamescreen.getPainter();
		offset = b.getOffset();
	}

	public void activateTalkzones(Rectangle playerRectangle){		
			for (int i = 0; i < talkzoneList.size(); i++) {
				Talkzone tz = talkzoneList.get(i);
				if(Intersector.overlapRectangles(tz.getZone(), playerRectangle)){
					painter.drawText(tz.getMessage());
			}
		}
	}
	
	public void move(boolean leftDown, boolean rightDown, boolean downDown,
			boolean upDown) {
		if(leftDown) p.setLeftSprite();
		if(rightDown) p.setRightSprite();
		switch(p.state) {
		case Standing:
			if(leftDown){
				p.setSpeed(new Vector2(p.getSpeed()
						.add(-Player.acceleration, 0)));
				p.state = State.Running;
			}
			if(rightDown){
				p.setSpeed(new Vector2(p.getSpeed().add(Player.acceleration, 0)));
				p.state = State.Running;
			}
			if(upDown){
				p.jump();
				p.state = State.Jumping;
			}
			break;
		case Running:
			if (upDown) {
				p.jump();
				p.state = State.Jumprunning;
			}
			break;
		case Jumping:
			if (leftDown) {
				p.setSpeed(new Vector2(p.getSpeed()
						.add(-Player.acceleration, 0)));
			}
			if (rightDown) {
				p.setSpeed(new Vector2(p.getSpeed().add(Player.acceleration, 0)));
			}
			if(downDown){
				p.extraGravity();
			}
			break;
		case Jumprunning:
			if(downDown){
				p.extraGravity();
			}
			break;						
		}
			
		
			
			
//		if (p.state == State.Standing) {
//			if (leftDown) {
//				p.setSpeed(new Vector2(p.getSpeed()
//						.add(-Player.acceleration, 0)));
//				p.state = State.Running;
//			}
//			if (rightDown) {
//				p.setSpeed(new Vector2(p.getSpeed().add(Player.acceleration, 0)));
//				p.state = State.Running;
//			}
//			if (upDown) {
//				p.jump();
//				p.state = State.Jumping;
//			}
//		}

//		if (p.state == State.Jumping) {
//			if (leftDown) {
//				p.setSpeed(new Vector2(p.getSpeed()
//						.add(-Player.acceleration, 0)));
//			}
//			if (rightDown) {
//				p.setSpeed(new Vector2(p.getSpeed().add(Player.acceleration, 0)));
//			}
//			if(downDown){
//				p.extraGravity();
//			}
//		}
		
//		if(p.state == State.Jumprunning){
//			if(downDown){
//				p.extraGravity();
//			}
//		}
		
//		if (p.state == State.Running) {
//			if (upDown) {
//				p.jump();
//				p.state = State.Jumprunning;
//			}
//		}

		//Maxspeed
		if (p.getSpeed().x > Player.maxSpeed) {
			p.setSpeed(new Vector2(Player.maxSpeed, p.getSpeed().y));
		}
		if (p.getSpeed().y > Player.maxJumpSpeed) {
			p.setSpeed(new Vector2(p.getSpeed().x, Player.maxJumpSpeed));
		}
		if (p.getSpeed().x < -Player.maxSpeed) {
			p.setSpeed(new Vector2(-Player.maxSpeed, p.getSpeed().y));
		}
		if (p.getSpeed().y < -Player.maxJumpSpeed) {
			p.setSpeed(new Vector2(p.getSpeed().x, -Player.maxJumpSpeed));
		}			
		
//		if(playerAtLeftBorder || playerAtRightBorder){
//			if(flag){
//			lastSideSpeed = p.getSpeed().x;
//			flag = false;
//			}
//			p.setSpeed(new Vector2(p.getSpeed().x - p.getSpeed().x,p.getSpeed().y));
//			offset.x += lastSideSpeed;
//		} else {
//			flag = true;
//		}
		
		
//		if (dontmoveL) {
//			/* omhastigheten �r negativ (r�r sig �t v�nster */
//			if (p.getSpeed().x < 0) {
//				p.setPosition(p.getPosition().add(
//						new Vector2(0, p.getSpeed().y)));
//			} else {/* annars g�s som vanligt */
//				p.setPosition(p.getPosition().add(p.getSpeed()));
//			}
//		} else if (dontmoveR) {
//			if (p.getSpeed().x > 0) {
//				p.setPosition(p.getPosition().add(
//						new Vector2(0, p.getSpeed().y)));
//			} else {
//				p.setPosition(p.getPosition().add(p.getSpeed()));
//			}
//		} else {
//			p.setPosition(p.getPosition().add(p.getSpeed()));
//		}
	}
}
