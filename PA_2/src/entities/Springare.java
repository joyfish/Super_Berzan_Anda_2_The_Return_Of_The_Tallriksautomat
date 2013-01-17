package entities;

import Demo_1.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Springare {
	private Vector2 position;
	private Vector2 patrolPoint1, patrolPoint2;
	private float patrolerSpeed;
	private Texture texture;
	private Vector2 offset;
	private Sprite sprite;
	private boolean isGoingRight = true;

	public Springare(Vector2 start, Vector2 end, float speed) {
		texture = new Texture(Gdx.files.internal("gubbeconsept3.png"));

		patrolPoint1 = start;
		patrolPoint2 = end;
		position =  new Vector2(patrolPoint1);
		
		patrolerSpeed = speed;

		offset = new Vector2(0, 0);
		sprite = new Sprite(texture);

	}

	public Vector2 getPosition() {
		return position;
	}

	public void act() {
		if (position.x < patrolPoint1.x) {
			isGoingRight = true;
			
		}
		if (position.x > patrolPoint2.x) {
			isGoingRight = false;
		}
		
		if(isGoingRight){
			position.x += patrolerSpeed;
		} else {
			position.x -= patrolerSpeed;
		}
	}

	public Sprite getSprite() {
		sprite.setX(position.x + offset.x);
		sprite.setY(position.y + offset.y);
		return sprite;
	}

	public void setOffset(Vector2 offset2) {
		offset = offset2;
		
	}
}
