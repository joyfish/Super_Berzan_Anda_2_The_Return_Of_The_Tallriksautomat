package launchers;


import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher  {
	public Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * @param args	
	 */ 
	public DesktopLauncher(){
		new LwjglApplication(new DemoGame(), "Vårat Dem0000", screen.width/2,screen.height/2,true);
	}
	
	public static void main(String[] args) {
		
		//HELLU!!!! I even added code ^^
		new DesktopLauncher();

	}

	
}
