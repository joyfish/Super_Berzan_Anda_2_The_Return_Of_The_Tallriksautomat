package Demo_1;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

import entities.Missile;
import entities.Plattform;
import entities.Player;
import entities.Springare;
import entities.Talkzone;
import entities.Thrower;

public class EntityMaster {
	private GameScreen master;
	private Background background;
	private Player player;
	private ArrayList<Talkzone> talkzones;	
	public ArrayList<Springare> runners;
	public ArrayList<Thrower> throwers;
	
	/**
	 * Lets all entities act and sets their offset
	 * make sure to call the .act() method inside the render() method of the game 
	 * @param superGame 
	 */
	public EntityMaster(GameScreen superGame){
		master = superGame;
		runners = new ArrayList();
		throwers = new ArrayList();
		for(Springare spr : Lists.getRunners()){
			runners.add(spr);
		}
		for(Thrower t : Lists.getThrowers()){
			throwers.add(t);
		}		
	}
	
	/**
	 * Call this after all objects have been constructed to bond all classes together
	 * This class have references to all entities:
	 * (background, player, talkzones, runners, throwers)
	 */
	public void initialize(){
		background = master.getBackground();
		player = master.getPlayer();
		talkzones = master.getTalkzones();
		
	}
	
	/**
	 * Sets the offset of the entities
	 * Lets all entities act a single tick
	 */
	public void act(){
		setEntityOffsets();
		letEntitiesAct();
	}
	
	/**
	 * Lets every single moving entity act
	 */
	private void letEntitiesAct() {
		player.act();
		
		for(Springare sr : runners){
			sr.act();
		}
		for(Thrower tr : throwers){
			tr.act(master);
			Missile e = tr.getMissile();
			if(e != null){
				e.act();
			}			
		}
		
		
	}

	/**
	 * Sets the offset of all entities in the game
	 */
	private void setEntityOffsets(){
		Vector2 tempoffset = background.getOffset();
		player.setOffset(tempoffset);
		for(Talkzone tz: talkzones){
			tz.setOffset(tempoffset);
		}
		for(Springare sr : runners){
			sr.setOffset(tempoffset);
		}
		for(Thrower tr : throwers){
			tr.setOffset(tempoffset);
		}
		for(Plattform p : background.getPlattforms()){
			p.setOffset(tempoffset);
		}		
	}

	/**
	 * Returns all the teachers (Throwers)
	 * @return
	 */
	public ArrayList<Thrower> getThrowers(){
		return throwers;
	}
	
	/**
	 * Returns all the runners (kids) 
	 * @return
	 */
	public ArrayList<Springare> getRunners() {
		return runners;
	}
	
	
}
