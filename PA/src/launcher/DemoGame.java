package launcher;

import world.GameScreen;

import com.badlogic.gdx.Game;

public class DemoGame extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen());		
	}

}
