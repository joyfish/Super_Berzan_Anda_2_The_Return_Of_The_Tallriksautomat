package Demo_1;

import inputhandler.Controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import entities.Plattform;
import entities.Player;
import entities.Talkzone;

public class Background {
	
	public Vector2 offset;
	public Texture background;	
	public Controller c;
	public Player p;
	private float BORDERLINE = 200f; 
	private ArrayList<Plattform> plattformList;
	private GameScreen master;
	private Dimension Screen;
	private ArrayList<Talkzone> talkzoneList;
	
	
	public Background(GameScreen Master){
		Screen = Toolkit.getDefaultToolkit().getScreenSize();
		master = Master;
		p=master.getPlayer();
		plattformList = new ArrayList<>();
		offset = new Vector2(-500,-400);
		background = new Texture(Gdx.files.internal("BakrundDemo2.1.png"));
		talkzoneList = Master.getTalkzones();
	}		
	
	
	/*gr�ns f�r bana*/
	
	public void update(Rectangle playerRectangle) {		
		if (playerRectangle.x <= BORDERLINE) {
			
			master.controller.dontmoveL=true;
			offset.x -= p.getSpeed().x;  
		}else{
			master.controller.dontmoveL=false;
		}
		
		
		
		if (playerRectangle.x + playerRectangle.width >= Screen.width/2 - BORDERLINE) {
			master.controller.dontmoveR=true;
			offset.x -= p.getSpeed().x;
		}else{
			master.controller.dontmoveR=false;
		}
		
		
		
		/*talksones*/
		
		for (int i = 0; i < talkzoneList.size(); i++) {
			Talkzone tz = talkzoneList.get(i);
			tz.setOffset(offset);
		}
		
	}	
	
	public ArrayList<Plattform> getPlattforms(){
		return plattformList;
	}
	
	public ArrayList<Talkzone> getTalkzones(){
		return talkzoneList;
	}
	
	public void addTalkzone(Vector2 position, String Message){
		//TODO
		talkzoneList.add(new Talkzone(position, Message));
	}
	
	public void addPlattform(Plattform p){
		plattformList.add(p);
	}
	
	public void addPlattform(Vector2 StartPosition, Vector2 Size){
		plattformList.add(new Plattform(StartPosition, Size));		
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
	
	/**
	 * send in the players rectangle with player.getRectangle() or something like that
	 * @param playerRectangle
	 */
	
	
}
  