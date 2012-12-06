package linking;

import java.util.HashMap;
import java.util.Map;

public class InputController {

	enum Keys{
		LEFT, JUMP, RIGHT, DOWN, SPACE
	}
	static Map<Keys,Boolean> keys = new HashMap<Keys, Boolean>();
	
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.SPACE, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.DOWN, false);
	}
	
	public InputController(){
		/*
		 * Den här klassen är till för att bevara den information man tar in då användaren klickar på en knapp
		 * Än så länge kan den inte göra något men med en mästar klass som kan läsa av tangenter så kan den det
		 * Då kan den säga åt, exempelvis, Hero att "vänster-knappen är nedtryckt så du borde gå åt vänster"		 *
		 * Klassen används med update metoden och tar emot World så att den kan nå till alla andra objekt 
		 */		
	}
	
	public void update(World world){
		//TODO hantera knapptryck
	}
	
	//Sets keys to true, if pressed
	public void spacePressed(){
		keys.put(Keys.SPACE,true);
	}
	public void leftPressed(){
		keys.put(Keys.LEFT,true);
	}
	public void rightPressed(){
		keys.put(Keys.RIGHT,true);
	}
	public void jumpPressed(){
		keys.put(Keys.JUMP,true);
	}
	public void downPressed(){
		keys.put(Keys.SPACE,true);
	}
	//Sets keys to false if released
	public void spaceReleased(){
		keys.put(Keys.SPACE,false);
	}
	public void leftReleased(){
		keys.put(Keys.LEFT,false);
	}
	public void rightReleased(){
		keys.put(Keys.RIGHT,false);
	}
	public void jumpReleased(){
		keys.put(Keys.JUMP,false);
	}
	public void downReleased(){
		keys.put(Keys.DOWN,false);
	}
}
