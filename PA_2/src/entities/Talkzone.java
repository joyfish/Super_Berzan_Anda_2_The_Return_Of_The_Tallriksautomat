package entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Talkzone {
	private Vector2 position;
	private Vector2 size = new Vector2(200,200);
	private Vector2 offset;
	private String message;
	private boolean isInside = false;
	
	public Talkzone(Vector2 Position, String m){
		position = Position;
		message = m;
		offset = new Vector2(-500,-400);
	}
	
	public boolean isInside(){
		return isInside;
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
	
	public String getMessage(){
		return message;
	}

	public void setOffset(Vector2 Offset) {
		offset = new Vector2(Offset.x + 500, Offset.y + 400);		
	}
}
