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
	public static float BORDERLINE = 200f; 
	private ArrayList<Plattform> plattformList;
	private GameScreen master;
	private Dimension Screen;
	private ArrayList<Talkzone> talkzoneList;
	private ArrayList<Texture> allTextures;
	/**
	 * Keeps track of all the plattforms in the game.
	 * Keeps track of the offset of everything tied to the background
	 * @param Master
	 */
	public Background(GameScreen Master){
		Screen = Toolkit.getDefaultToolkit().getScreenSize();
		master = Master;			
		plattformList = Lists.getPlattforms(master);
		talkzoneList = Lists.getTalkzone();
		offset = new Vector2(0,0);	
		background = Lists.getBackgroundImage();
	}		
	
	/**
	 * Call this after all objects have been constructed to bond all classes together 
	 */
	public void initialize(){
		
	}		
	
	/**
	 * returns all plattform objects
	 * @return
	 */
	public ArrayList<Plattform> getPlattforms(){
		return plattformList;
	}
	
	/**
	 * returns all talkzone objects
	 * @return
	 */
	public ArrayList<Talkzone> getTalkzones(){
		return talkzoneList;
	}
	
	/**
	 * Adds a talkzone to the location, position, with the message, Message
	 * @param position
	 * @param Message
	 */
	public void addTalkzone(Vector2 position, String Message){		
		talkzoneList.add(new Talkzone(position, Message));
	}
	
	/**
	 * Adds a predefined plattform to the game
	 * @param p
	 */
	public void addPlattform(Plattform p){
		plattformList.add(p);
	}
	/**
	 * Adds a plattform to the location, StartPosition, with the size, Size
	 * @param StartPosition
	 * @param Size
	 */
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
		
	
	
}
  