package Demo_1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import entities.Plattform;

public class Background {
	
	public Vector2 offset;
	public Texture background;	
	private float BORDERLINE = 50f; 
	private ArrayList<Plattform> plattformList;
	private GameScreen master;
	private Dimension Screen;
	
	public Background(GameScreen Master){
		Screen = Toolkit.getDefaultToolkit().getScreenSize();
		master = Master;
		plattformList = new ArrayList<>();
		offset = new Vector2(-500,-400);
		background = new Texture(Gdx.files.internal("BakrundDemo2.1.png"));
	}		
	
	public void update(Rectangle playerRectangle) {		
		if (playerRectangle.x <= BORDERLINE) {
			offset.x += 5; 
		}		
		if (playerRectangle.x + playerRectangle.width >= Screen.width/2 - BORDERLINE) {
			offset.x -= 5;
		}
		
	}	
	
	public ArrayList<Plattform> getPlattforms(){
		return plattformList;
	}
	
	public void addPlattform(Plattform p){
		plattformList.add(p);
	}
	
	public void addPlattform(Vector2 StartPosition, Vector2 Size){
		plattformList.add(new Plattform(StartPosition, Size));
		System.out.println(plattformList);
	}
	
	public void setOffset(Vector2 newOffset){
		offset = newOffset;
	}
	
	public Vector2 getOffset(){
		return offset;
	}
	
	public Texture getTexture(){
		return background;
	}
	
}
