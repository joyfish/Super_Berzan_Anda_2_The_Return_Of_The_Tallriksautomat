package entities;

import Demo_1.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Enemy {

	/* "Lärare som kastar papper" */
	private Vector2 position;
	private boolean isReady = true;
	private Texture texture;
	private Vector2 offset;
	private Missile misil;
	private Sprite sprite;
	
	public Enemy(Vector2 Position) {
		texture = new Texture(Gdx.files.internal("PlayerDemo1.png"));
		position = Position;
		offset = new Vector2(0, 0);
		sprite = new Sprite(texture);

	}

	public void act(GameScreen master) {
		if (isReady) {
			misil = new Missile(new Vector2(position), master.getPlayer().getPosition(), master);
			isReady = false;
		}
		misil.act();

		Vector2 missilePosition = misil.getPosition();
		if (missilePosition.x+offset.x < 0 || missilePosition.x+offset.x > master.applicationSize.width) {
			isReady = true;
		}
	}

	public Texture getTexture() {
		return texture;
	}

	public Missile getMissile() {
		return misil;
	}

	public boolean isReady() {
		return isReady;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getOffset() {
		return offset;
	}

	public void setOffset(Vector2 offset2) {		
		offset = new Vector2(offset.x + 500, offset.y + 400);
	}
	
	public Sprite getSprite(){
		sprite.setX(position.x + offset.x);
		sprite.setY(position.y + offset.y);
		return sprite;
	}
	
}
