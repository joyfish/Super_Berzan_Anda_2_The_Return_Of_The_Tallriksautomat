package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
	
	/*"Lärare som kastar papper" */
	private Vector2 position;
	private boolean isReady = false;
	private Texture texture;
	private Vector2 offset;
	private Missile misil;
	public Enemy(Vector2 Position){
		texture = new Texture(Gdx.files.internal("PlayerDemo1.png"));
		position = Position;
		offset = new Vector2(0,0);
		misil = new Missile(new Vector2(Position));
	}
			
	public void act(){
		if(isReady){
			misil=new Missile(position);		
		isReady=false;	
		}
		misil.act();
	}
	
	public Texture getTexture(){
		return texture;
	}
	public Missile getMissile(){
		return misil;
	}
	public boolean isReady(){
		return isReady;
	}
	
	public Vector2 getPosition(){
		return position;
	}

	public Vector2 getOffset(){
		return offset;
	}
	
	public void setOffset(Vector2 offset2) {
		offset = offset2;
		
	}
	
	
	
}
