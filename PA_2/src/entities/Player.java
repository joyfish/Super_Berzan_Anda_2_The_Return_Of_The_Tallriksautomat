package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {

	public Vector2 position;
	public Vector2 speed;
	public static float maxSpeed = 5f;
	public static float acceleration = 0.15f;
	public static float deAcceleration = 0.98f;
	public static float stopSpeed = 1.5f;
	public Texture image;
	public Rectangle rectangle;
	public Vector2 Gravity=new Vector2 (0,-0.05f);
	
	/**
	 * faller figuren? true eller false
	 */
	public Boolean falling;
	public static float maxJumpSpeed = 7f;
	
	public Player(){
		speed = new Vector2(0,0);
		position = new Vector2(120,40);
		image = new Texture(Gdx.files.internal("PlayerDemo2.png"));
	}
	
	public void gravity(boolean isStanding){
		if (isStanding==true){
			speed.y=0;
		}else{
			speed.add(Gravity);
		}
	}
	
	public Rectangle getRectangle(){
		rectangle = new Rectangle(position.x,position.y,image.getWidth(),image.getHeight());
		return rectangle;
	}
	
	public void setSpeed(Vector2 newSpeed){
		speed = newSpeed;
	}
	
	public void setPosition(Vector2 newPosition){
		position = newPosition;
	}		
	
	public Vector2 getPosition(){
		return position;
	}
	
	public Vector2 getSpeed(){
		return speed;
	}
	
	public float getMaxJumpSpeed(){
		return maxJumpSpeed;
	}
	
	public float getMaxSpeed(){
		return maxSpeed;
	}
	
	public float getAcceleration(){
		return acceleration;
	}
	
	public float getDeceleration(){
		return deAcceleration;
	}
	
	public Texture getTexture(){
		return image;
	}
	
	public float getStopSpeed(){
		return stopSpeed;
	}

	/**
	 * om figuren inte faller (falling == false) så får figuren hastighet uppåt
	 */
	public void jump() {	
		if(falling == false){
			setSpeed(new Vector2(getSpeed().add(0, 7)));			
		}
	}
	
	public boolean isFalling(){
		return falling;
	}
	
}
