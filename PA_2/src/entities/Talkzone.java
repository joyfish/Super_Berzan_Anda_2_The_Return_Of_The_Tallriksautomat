package entities;

import java.util.ArrayList;

import Demo_1.Lists;

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
	private ArrayList<Texture> textures;
	private int currentIndex = 0;
	
	public Talkzone(Vector2 Position, String m){
		position = Position;
		message = m;
		offset = new Vector2(0,0);
		talkperson = new Sprite(new Texture(Gdx.files.internal("pic\\jhonny7.png")));
		textures = Lists.getJhonny();
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
	
	private void nextAnimation(){
		if(currentIndex >= 3){
			currentIndex = 0;
		}
		talkperson.setTexture(textures.get(currentIndex));
		currentIndex += 1;
	}
	
	public void act(){
		nextAnimation();
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
		offset = new Vector2(Offset.x, Offset.y);		
	}
}
