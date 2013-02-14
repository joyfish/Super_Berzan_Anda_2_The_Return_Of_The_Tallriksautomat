package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Plattform {
	Vector2 offset;
	Vector2 position;	
	Vector2 size;	
	Texture t = new Texture(Gdx.files.internal("Platform.png"));
	Rectangle rectangle;
	private Sprite sprite;
	
	public Plattform(Vector2 startPosition, Vector2 Size){
		this.position = startPosition;
		this.size = Size;
		this.rectangle = new Rectangle(position.x,position.y,size.x,size.y);
		offset = new Vector2(0, 0);		
		sprite = new Sprite(t);										
		sprite.setSize(Size.x, Size.y);		
	}
	
	public void setOffset(Vector2 Offset){		
		offset = new Vector2(Offset.x,Offset.y);		
	}
	
	public Vector2 getOffset(){
		return offset;
	}
	
	public Rectangle getRectangle(){
		rectangle.x = position.x + offset.x;
		rectangle.y = position.y + offset.y;
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

	public Sprite getSprite() {
		sprite.setX(position.x + offset.x);
		sprite.setY(position.y + offset.y);		
		return sprite;
	}

}
