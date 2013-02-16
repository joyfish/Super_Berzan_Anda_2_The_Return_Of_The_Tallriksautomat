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
	private float ticker = 0;
	private float startX, startY;	
	private float deltaX;
	private float distanceTraveled;
	
	public Missile(Vector2 startPosition, Vector2 playerPos, Vector2 offset) {
		position = startPosition;
		this.offset = offset;
		startY = position.y + offset.y;
		startX = position.x + offset.x;
		deltaX = playerPos.x - startX;
		distanceTraveled = 0;
		texture = new Texture(Gdx.files.internal("zemisileshue.png"));
		sprite = new Sprite(texture);
	}

	public void setOffset(Vector2 v) {
		offset = v;
	}

	public Vector2 getPosition() {
		return position;
	}
	
	public void act() {
//		position.add(speed);
		ticker += 1;
		distanceTraveled += fx(deltaX);
		position.x += fx(deltaX); //speed
		if(Math.abs(distanceTraveled/deltaX) < 1){
			position.y = startY + Math.abs(fy(ticker));
		} else {
			position.y += -1f; //gravity
		}		
	}

	
	private float fy(float ticker2) {
		float f = 0;
		// y = A * Sin(Bx)
		if(distanceTraveled/deltaX < 1){
		f = (float)(10 * Math.sin(Math.PI * distanceTraveled/deltaX));
		} else {
			f = -2f;
		}
		
		return f;
	}

	private float fx(float deltaX2) {
		if(deltaX2 > 0){
			return 1f;
		} else {
			return -1f;
		}	
	}

	public Rectangle getRectangle() {
		return new Rectangle(position.x + offset.x, position.y + offset.y, texture.getWidth(),
				texture.getHeight());
	}

	public Texture getTexture() {
		return texture;
	}

	public Sprite getSprite() {
		sprite.setX(position.x + offset.x);
		sprite.setY(position.y + offset.y);
		return sprite;
	}

}
