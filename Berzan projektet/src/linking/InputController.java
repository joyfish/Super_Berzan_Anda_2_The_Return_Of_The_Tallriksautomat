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
		 * Den h�r klassen �r till f�r att bevara den information man tar in d� anv�ndaren klickar p� en knapp
		 * �n s� l�nge kan den inte g�ra n�got men med en m�star klass som kan l�sa av tangenter s� kan den det
		 * D� kan den s�ga �t, exempelvis, Hero att "v�nster-knappen �r nedtryckt s� du borde g� �t v�nster"		 *
		 * Klassen anv�nds med update metoden och tar emot World s� att den kan n� till alla andra objekt 
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
