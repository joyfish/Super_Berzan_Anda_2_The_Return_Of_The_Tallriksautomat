package launchers;

import Demo_1.GameScreen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Credits {
	SpriteBatch spritebatch;
	String intro = "Hello World!";
	String gameover = "You died!";
	String ending = "Congrats! You won!";
	BitmapFont bmf = new BitmapFont();
	int ticker = 0;
	GameScreen master;
	
	public Credits(GameScreen gameScreen){
		spritebatch = new SpriteBatch();
		master = gameScreen;
	}
		

	public void drawIntro() {
		// TODO Auto-generated method stub
		spritebatch.begin();		
		bmf.draw(spritebatch, intro, 300, 300);		
		System.out.println("Drawing");	
		spritebatch.end();
	}

	public void drawGameOver() {
		// TODO Auto-generated method stub
		spritebatch.begin();
		bmf.draw(spritebatch, gameover, 300, 300);
		spritebatch.end();
	}

	public void drawEnd() {
		// TODO Auto-generated method stub
		spritebatch.begin();
		bmf.draw(spritebatch, ending, 300, 300);
		spritebatch.end();
	}
	
}
