package sounds;

import sun.audio.AudioStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundPlayer {	
	AudioStream as;
	
	public SoundPlayer(){
		
	}		
	
	public void loopRedRiverRock(){
		Music music = Gdx.audio.newMusic(Gdx.files.internal("src/sounds/RRR.ogg"));
		music.setLooping(true);
		music.setVolume(0f);
		music.play();
	}
				
	
	
}
