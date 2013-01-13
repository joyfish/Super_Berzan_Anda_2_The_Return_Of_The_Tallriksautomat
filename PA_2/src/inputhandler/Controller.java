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
	
	public Controller(GameScreen Gamescreen) {
		gamescreen = Gamescreen;
		p = gamescreen.getPlayer();
		b = gamescreen.getBackground();
		talkzoneList = b.getTalkzones();
		painter = gamescreen.getPainter();
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
			if (upDown) {
				p.jump();
				p.state = State.Jumping;
			}
		}

		if (p.state == State.Jumping) {
			if (leftDown) {
				p.setSpeed(new Vector2(p.getSpeed()
						.add(-p.getAcceleration(), 0)));
			}
			if (rightDown) {
				p.setSpeed(new Vector2(p.getSpeed().add(p.getAcceleration(), 0)));
			}
			if(downDown){
				p.extraGravity();
			}
		}
		if(p.state == State.Jumprunning){
			if(downDown){
				p.extraGravity();
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

		/**
		 * detta f�r gubben att flytta p� sig men inte n�r han st�r n�ra en kant
		 * (borde g� att g�ra finare men har inte hittat ngn feature med det �N)
		 */

		if (dontmoveL) {
			/* omhastigheten �r negativ (r�r sig �t v�nster */
			if (p.getSpeed().x < 0) {
				p.setPosition(p.getPosition().add(
						new Vector2(0, p.getSpeed().y)));
			} else {/* annars g�s som vanligt */
				p.setPosition(p.getPosition().add(p.getSpeed()));
			}
		} else if (dontmoveR) {
			if (p.getSpeed().x > 0) {
				p.setPosition(p.getPosition().add(
						new Vector2(0, p.getSpeed().y)));
			} else {
				p.setPosition(p.getPosition().add(p.getSpeed()));
			}
		} else {
			p.setPosition(p.getPosition().add(p.getSpeed()));
		}
	}
}
