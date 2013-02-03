package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Talkzone {
	private Vector2 position;
	private Vector2 size = new Vector2(100,100);
	private Vector2 offset;
	private String message;
	private boolean isInside = false;
	private Sprite	talkperson;
	
	public Talkzone(Vector2 Position, String m){
		position = Position;
		message = m;
		offset = new Vector2(-500,-400);
		talkperson = new Sprite(new Texture(Gdx.files.internal("Talk.png")));
	}
	
	public Talkzone(Vector2 Position, Vector2 Size, String m){		
		this(Position,m);
		size = Size;
	}
	
	public boolean isInside(){
		return isInside;
	}
	
	public void setSize(Vector2 v){
		size = v;
	}
	
	public void entered(){
		isInside = true;
	}
	
	public void exited(){
		isInside = false;
	}
	
	public void setMessage(String s){
		message = s;
	}
	
	public void setPosition(Vector2 newPosition){
		position = newPosition;
	}
	
	public Rectangle getZone(){
		return new Rectangle(position.x+offset.x, position.y+offset.y, size.x, size.y);
	}
	
	public Sprite getZoneSprite(){
		talkperson.setX(position.x + offset.x + size.x/2);
		talkperson.setY(position.y + offset.y);
		return talkperson;
	}
	
	public String getMessage(){
		return message;
	}

	public void setOffset(Vector2 Offset) {
		offset = new Vector2(Offset.x + 500, Offset.y + 400);		
	}
}
