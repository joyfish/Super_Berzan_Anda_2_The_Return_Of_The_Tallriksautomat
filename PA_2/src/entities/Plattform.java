package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Plattform {
	Vector2 offset;
	Vector2 position;	
	Vector2 size;	
	Texture t = new Texture(Gdx.files.internal("Platform.png"));
	Rectangle rectangle;
	
	public Plattform(Vector2 startPosition, Vector2 Size){
		this.position = startPosition;
		this.size = Size;
		this.rectangle = new Rectangle(position.x,position.y,size.x,size.y);
		offset = new Vector2(0, 0);		
	}
	
	public void setOffset(Vector2 Offset){		
		offset = new Vector2(Offset.x + 500,Offset.y + 400);		
	}
	
	public Vector2 getOffset(){
		return offset;
	}
	
	public Rectangle getRectangle(){
		return rectangle;
	}
	
	public Texture getTexture(){
		return t;
	}
	
	public Vector2 getPosition() {		
		return new Vector2(position.x+offset.x, position.y+offset.y);
	}

	public Vector2 getSize() {
		return size;
	}

}
