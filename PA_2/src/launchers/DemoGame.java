package launchers;

import Demo_1.GameScreen;

import com.badlogic.gdx.Game;

public class DemoGame extends Game {
	DesktopLauncher master;
	public DemoGame(DesktopLauncher dl){		
		super();		
		master = dl;
	}
	public void endGame(){
		master.endGame();
	}
	
	@Override
	public void create() {
		setScreen(new GameScreen(this));		
	}
	
	

}
