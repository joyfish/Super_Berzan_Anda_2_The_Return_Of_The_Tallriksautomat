package entities;

import java.util.ArrayList;

import Demo_1.Lists;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	public static float maxSpeed = 5f;
	public static float acceleration = 0.15f;
	public static float deAcceleration = 0.98f;
	public static float stopSpeed = 1.5f;
	public static Vector2 Gravity = new Vector2(0, -0.1f);;
	public static float maxJumpSpeed = 6f;
	public static float jumpCooldown = 40;
	private Vector2 offset;
	private Vector2 position;
	private Vector2 speed;
	public State state;
	private Sprite sprite;
	private float ticker = 0;
	private boolean jumpReady = true;
	private boolean looksLeft = false;
	public Rectangle rectangle;
	private Boolean falling;
	public Boolean atBorder = false;
	public boolean atLeftBorder = false;
	public boolean atRightBorder = false;
	public int health = 5;
	public int healthTicker;
	private int currentIndex = 0;
	private ArrayList<Texture> runnerTexture;
	
	public Player() {
		speed = new Vector2(0, 0);
		position = new Vector2(120, 40);
		state = State.Jumping;
		falling = true;
		runnerTexture = Lists.getTextures();
		sprite = new Sprite(runnerTexture.get(currentIndex));
		offset = new Vector2(0, 0);
		rectangle = new Rectangle(0, 0, sprite.getWidth(), sprite.getHeight());
		healthTicker = 0;		
	}

	public void act() {
		// Gör en viss animation utifrån vilket state han är i.
		// state control
		switch (state) {
		case Running:
			falling = false;
			speed.y = 0;
			nextAnimation();
			break;
		case Standing:
			falling = false;
			speed.x *= 0.5f;
			speed.y = 0;
			break;
		case Jumping:
			falling = true;
			speed.x *= 0.99;
			break;
		case Jumprunning:
			nextAnimation();
			falling = true;
			break;
		}

		// Jump cooldown
		if (jumpReady == false) {
			ticker++;
		}
		if (ticker > jumpCooldown) {
			ticker = 0;
			jumpReady = true;
		}

		// position += speed
		position.y += speed.y;
		position.x += speed.x;
						
		if (healthTicker > 0) {
			healthTicker--;
		}
		
		if(position.x - offset.x < 0){
			offset.x = position.x;
		}
		
		if (atLeftBorder) {
			position.x -= speed.x;
		} else if (atRightBorder) {
			position.x -= speed.x;
		}
	}

	public void setOffset(Vector2 Offset2) {
		offset = Offset2;
	}

	
	
	public void gravity() {
		if (isStanding()) {
			speed.y = 0;
		} else {
			speed.add(Gravity);
		}
	}

	private void nextAnimation(){
		if(currentIndex >= 26){
			currentIndex = 0;
		}		
		sprite.setTexture(runnerTexture.get(currentIndex));
		currentIndex++;
	}
	
	public void setState(State s) {
		state = s;
	}

	public Rectangle getScreenRextangle(){
		return new Rectangle(position.x,position.y,rectangle.width,rectangle.height);
	}
	
	public Rectangle getRectangle() {
		rectangle.x = position.x + offset.x;
		rectangle.y = position.y + offset.y;
		return rectangle;
	}

	public void damage() {
		if (healthTicker < 1) {			
			health--;
			healthTicker = 250;
		}
	}

	public void setSpeed(Vector2 newSpeed) {
		speed = newSpeed;
	}

	public void setPosition(Vector2 newPosition) {
		position = newPosition;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getSpeed() {
		return speed;
	}

	public float getMaxJumpSpeed() {
		return maxJumpSpeed;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public float getDeceleration() {
		return deAcceleration;
	}

	public void setLeftSprite() {
		looksLeft = true;
	}

	public void setRightSprite() {
		looksLeft = false;
	}

	public void jump() {
		if (jumpReady) {
			setSpeed(new Vector2(getSpeed().add(0, 6f))); // 5.5f
			jumpReady = false;
		}

	}

	public void extraGravity() {
		if (falling) {
			getSpeed().add(0, -1);
		}
	}

	public boolean isFalling() {
		return falling;
	}

	public boolean isStanding() {
		return !falling;
	}

	public Sprite getSprite() {		
		if(looksLeft && sprite.isFlipX() == false){
			sprite.flip(true, false);
			looksLeft = false;
		} else if (looksLeft == false && sprite.isFlipX()){
			sprite.flip(true, false);
		}
		sprite.setX(position.x);
		sprite.setY(position.y);
		return sprite;
	}

	public int getHealth() {
		return health;
	}
	
	public boolean isDamageReady(){
		if(healthTicker < 1){
			return true;
		} else {
		return false;
		}
	}

}
