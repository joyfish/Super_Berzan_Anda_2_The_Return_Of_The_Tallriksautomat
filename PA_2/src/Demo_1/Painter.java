package Demo_1;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import entities.Missile;
import entities.Plattform;
import entities.Player;
import entities.Springare;
import entities.Talkzone;
import entities.Thrower;

public class Painter {

	private boolean drawText = false;
	private String message = "";
	public OrthographicCamera cam;
	public GameScreen gs;
	public int ticker = 0;
	public Player player;
	public Background background;	
	private ArrayList<Plattform> plattformList;
	public SpriteBatch spritebatch;
	private ArrayList<Talkzone> talkzoneList;
	private ArrayList<Thrower> enemyList;
	private ArrayList<Springare> springareList;
	private ShapeRenderer shaperenderer;
	
	public Painter(GameScreen gs,Player Player, Background Background){
		player = Player;
		background = Background;
		spritebatch = new SpriteBatch();
		cam = new OrthographicCamera(20,20);
		cam.position.set(10, 10, 0);
		plattformList = gs.getPlattforms(); 
		talkzoneList = gs.getTalkzones();
		enemyList = gs.getEnemies();
		springareList = gs.getSpringare();
		
		shaperenderer = new ShapeRenderer();
	}
	
	
	
	public void renderSprites(){
		ticker++;
		spritebatch.begin();
		Sprite s;
		spritebatch.draw(background.getTexture(), background.getOffset().x, background.getOffset().y);
		
		s = player.getSprite();
		s.draw(spritebatch);
		
		for(Thrower e : enemyList){
			if(e.isReady() == false){
				Missile m = e.getMissile();
				s = m.getSprite();
				s.draw(spritebatch);
			}
			s = e.getSprite();
			s.draw(spritebatch);
		}
		
		for(Springare e : springareList){
			s = e.getSprite();
			s.draw(spritebatch);
		}
		
		for(Plattform p: plattformList){
			s = p.getSprite();
			s.draw(spritebatch);
		}				
		
		spritebatch.end();
		shaperenderer.begin(ShapeType.Rectangle);					
		shaperenderer.setColor(.7f, 0, .9f, .9f);
		for(Talkzone t : talkzoneList){
			Rectangle r = t.getZone();
			shaperenderer.rect(r.x, r.y, r.width, r.height);						
		}
		shaperenderer.end();
		if(drawText){
			
		BitmapFont bmf = new BitmapFont();
		spritebatch.begin();		
		bmf.draw(spritebatch, message, 10, 300);
		spritebatch.end();
		}
		if(ticker > 500){
			drawText = false;
		}
	}		
	
	public void render(){
		spritebatch.begin();
		spritebatch.draw(background.getTexture(), background.getOffset().x, background.getOffset().y);
		spritebatch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
		
		
		
		for(Thrower e : enemyList){
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



	public void drawText(String m) {
		drawText = true;
		message = m;
		ticker = 0;
	}



	public void renderEnd() {
		// TODO Auto-generated method stub
		
	}
	
}
