package Demo_1;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import entities.Enemy;
import entities.Missile;
import entities.Plattform;
import entities.Player;
import entities.Talkzone;

public class Painter {

	public OrthographicCamera cam;
	public GameScreen gs;

	public Player player;
	public Background background;	
	private ArrayList<Plattform> plattformList;
	public SpriteBatch spritebatch;
	private ArrayList<Talkzone> talkzoneList;
	private ArrayList<Enemy> enemyList;
	
	public Painter(GameScreen gs,Player Player, Background Background){
		player = Player;
		background = Background;
		spritebatch = new SpriteBatch();
		cam = new OrthographicCamera(20,20);		
		plattformList = gs.getPlattforms(); 
		talkzoneList = gs.getTalkzones();
		enemyList = gs.getEnemies();			
	}
	
	public void renderSprites(){
		spritebatch.begin();
		Sprite s;
		spritebatch.draw(background.getTexture(), background.getOffset().x, background.getOffset().y);
		
		s = player.getSprite();
		s.draw(spritebatch);
		
		for(Enemy e : enemyList){
			if(e.isReady() == false){
				Missile m = e.getMissile();
				s = m.getSprite();
				s.draw(spritebatch);
			}
			s = e.getSprite();
			s.draw(spritebatch);
		}
		
		for(Plattform p: plattformList){
			s = p.getSprite();
			s.draw(spritebatch);
		}
		
		for(Talkzone t : talkzoneList){
			//s = t.getSprite();
			//s.draw(spritebatch);
		}
		
		spritebatch.end();
	}
	
	public void render(){
		spritebatch.begin();
		spritebatch.draw(background.getTexture(), background.getOffset().x, background.getOffset().y);
		spritebatch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
		
		
		
		for(Enemy e : enemyList){
			if(e.isReady() == false){
			Missile m = e.getMissile();
			spritebatch.draw(m.getTexture(),m.getPosition().x + e.getOffset().x, m.getPosition().y + e.getOffset().y);
			}
			spritebatch.draw(e.getTexture(),e.getPosition().x + e.getOffset().x,e.getPosition().y + e.getOffset().y);
		}
		for(Plattform p : plattformList){
		spritebatch.draw(p.getTexture(), p.getPosition().x,p.getPosition().y ,p.getTexture().getWidth(),p.getTexture().getHeight());
		}
		
		
		
		for (int i = 0; i < talkzoneList.size(); i++) {
			//Draw talkzones		
		}
		spritebatch.end();
	}
	
}
