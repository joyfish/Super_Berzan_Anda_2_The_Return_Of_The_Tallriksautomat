package entities;

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
	public Texture lookingrightIMG,servanster;
	public Rectangle rectangle;	
	private Boolean falling;
	public Boolean atBorder = false;
	public boolean atLeftBorder = false;
	public boolean atRightBorder = false;
	
	public Player() {
		speed = new Vector2(0, 0);
		position = new Vector2(120, 40);
		lookingrightIMG = new Texture(Gdx.files.internal("gubbeconsept2.png"));
		servanster = new Texture(Gdx.files.internal("gubbeconsept1.png"));
		state = State.Jumping;
		falling = true;
		sprite = new Sprite(lookingrightIMG);
		offset = new Vector2(0,0);
		rectangle = new Rectangle(0,0,sprite.getWidth(),sprite.getHeight());
	}

	public void act() {
		// Gör en viss animation utifrån vilket state han är i.
		//state control
		switch(state) {
		case Running:
			falling = false;
			speed.y = 0;			
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
			falling = true;
			break;
	
		}
		
		//Jump cooldown
		if(jumpReady == false){
			ticker++;
		} 
		if (ticker > jumpCooldown){
			ticker = 0;
			jumpReady = true;
		}	
		
		//position += speed
		position.y += speed.y;
		position.x += speed.x;
		
		if(atLeftBorder){
			position.x -= speed.x;			
		}else if(atRightBorder){
			position.x -= speed.x;			
		}
	}

	public void setOffset(Vector2 Offset2){
		offset = Offset2;
	}
	
	public void gravity() {
		if (isStanding()) {
			speed.y = 0;
		} else {
			speed.add(Gravity);
		}
	}

	public void setState(State s) {
		state = s;
	}

	public Rectangle getRectangle() {
		rectangle.x = position.x + offset.x;
		rectangle.y = position.y + offset.y;
		return rectangle;
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

	public void setLeftSprite(){
		sprite.setTexture(servanster);
	}
	
	public void setRightSprite(){
		sprite.setTexture(lookingrightIMG);
	}		
	
	public void jump() {
		if(jumpReady){
			setSpeed(new Vector2(getSpeed().add(0, 5.5f))); //5.5f
			jumpReady = false;
		}
		
	}

	public void extraGravity(){
		if(falling){
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
		sprite.setX(position.x);
		sprite.setY(position.y);
		return sprite;
	}

}
