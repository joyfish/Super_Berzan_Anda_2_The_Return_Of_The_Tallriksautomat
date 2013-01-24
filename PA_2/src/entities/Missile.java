package entities;

import Demo_1.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Missile {
	private Texture texture;
	private Vector2 position;	
	private Vector2 speed;	
	private Sprite sprite;
	private Vector2 offset;
	
	public Missile(Vector2 startPosition, Vector2 playerPos, GameScreen master){
		position = startPosition;
		if(playerPos.x  > startPosition.x + master.getBackground().getOffset().x ){
			speed = new Vector2(2,0);
			position.add(32,0);
		} else {
			speed = new Vector2(-2,0);
		}
		offset = new Vector2(0,0);
		texture = new Texture(Gdx.files.internal("zemisileshue.png"));
		sprite = new Sprite(texture);
	}
	
	public void setOffset(Vector2 v){
		offset = v;
	}
	public Vector2 getPosition(){		
		return position;
	}
	
	public void act(){
		position.add(speed);		
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
	}
	
	public Texture getTexture(){
		return texture;
	}
	public Sprite getSprite() {
		sprite.setX(position.x + offset.x);
		sprite.setY(position.y + offset.y);
		return sprite;
	}
	

}
