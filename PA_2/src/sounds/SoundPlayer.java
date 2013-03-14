package sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundPlayer {		
	Music music;
	
	public SoundPlayer(){
		
	}		
	
	public void loopRedRiverRock(){
		music = Gdx.audio.newMusic(Gdx.files.internal("src/sounds/RRR.ogg"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
	}

	public void dispose() {
		music.dispose();
	}					
	
}