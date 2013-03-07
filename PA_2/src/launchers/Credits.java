package launchers;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import Demo_1.GameScreen;
import Demo_1.Lists;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Credits {
	SpriteBatch spritebatch;
	String eol = System.getProperty("line.separator");			
	ArrayList<String> introStrings;
	ArrayList<String> endingStrings;
	String ending = "Congrats! You won!";
	BitmapFont bmf = new BitmapFont();
	int ticker = 0;
	GameScreen master;
	Dimension applicationSize;
	
	public Credits(GameScreen gameScreen){
		spritebatch = new SpriteBatch();
		introStrings = Lists.getIntroStrings();
		endingStrings = Lists.getEndingStrings();
		master = gameScreen;
		applicationSize = Toolkit.getDefaultToolkit().getScreenSize();
		applicationSize.height = applicationSize.height/2 + 200;
		applicationSize.width = applicationSize.width/2 + 200;
	}
		

	public boolean drawIntro() {
		spritebatch.begin();		
		for (int i = 0; i < introStrings.size(); i++) {
			String intro = introStrings.get(i);
			int length = intro.length() * 7;
			bmf.draw(spritebatch, intro, applicationSize.width/2 - length/2, 2*ticker/3 - i*100);			
		}					
		spritebatch.end();
		ticker++;
		if(2*ticker/3 > applicationSize.height + introStrings.size()*100){
			return true;
		} else {
			return false;
		}
	}

	public boolean drawGameOver() {
		spritebatch.begin();
		
		bmf.draw(spritebatch, "game over", 300, 300);
		spritebatch.end();
		ticker++;
		return true;
	}

	public boolean drawEnd() {
		spritebatch.begin();		
		for (int i = 0; i < endingStrings.size(); i++) {
			String intro = endingStrings.get(i);
			int length = intro.length() * 7;
			bmf.draw(spritebatch, intro, applicationSize.width/2 - length/2, 2*ticker/3 - i*100);			
		}					
		spritebatch.end();
		ticker++;
		if(2*ticker/3 > applicationSize.height + introStrings.size()*100){
			return true;
		} else {
			return false;
		}
	}
	
	public void resetTimer(){
		ticker = 0;
	}
}

