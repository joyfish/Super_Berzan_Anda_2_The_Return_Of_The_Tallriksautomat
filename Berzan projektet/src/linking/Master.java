package linking;

import screen.GameScreen;

import com.badlogic.gdx.Game;

public class Master extends Game {
		

	@Override
	public void create() {
		this.setScreen(new GameScreen());		
	}

}
