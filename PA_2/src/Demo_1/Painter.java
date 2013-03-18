package Demo_1;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import entities.Missile;
import entities.Plattform;
import entities.Player;
import entities.Runner;
import entities.Talkzone;
import entities.Thrower;

public class Painter {

	private boolean drawText = false;
	private String message = "";
	public OrthographicCamera cam;
	public GameScreen master;
	public int messageTicker = 0;
	public Player player;
	public Background background;
	private ArrayList<Plattform> plattformList;
	public SpriteBatch spritebatch;
	private ArrayList<Talkzone> talkzoneList;
	private ArrayList<Thrower> enemyList;
	private ArrayList<Runner> springareList;
	private ShapeRenderer shaperenderer;
	private ArrayList<Texture> allTextures;
	private Texture currentBackground, nextBackground;
	private boolean showNextBackground = false;
	private Sprite heart;
	
	public Painter(GameScreen gs) {
		master = gs;
		spritebatch = new SpriteBatch();
		cam = new OrthographicCamera(20, 20);
		cam.position.set(10, 10, 0);
		shaperenderer = new ShapeRenderer();
		heart = new Sprite(new Texture(Gdx.files.internal("heart-icon.png")));
	}

	public void initialize() {
		player = master.getPlayer();
		background = master.getBackground();
		plattformList = master.getPlattforms();
		talkzoneList = master.getTalkzones();
		enemyList = master.getThrowers();
		springareList = master.getRunners();
	}

	public void renderDebug() {
		shaperenderer.begin(ShapeType.Rectangle);
		Rectangle r;
		messageTicker++;
		for (Thrower e : enemyList) {
			r = e.getRectangle();
			shaperenderer.setColor(Color.ORANGE);
			shaperenderer.rect(r.x, r.y, r.width, r.height);
			if (e.isReady() == false) {
				r = e.getMissile().getRectangle();
				shaperenderer.setColor(Color.MAGENTA);
				shaperenderer.rect(r.x, r.y, r.width, r.height);				
			}
		}
		for (Talkzone tz : talkzoneList) {
			r = tz.getZone();
			shaperenderer.setColor(Color.PINK);
			shaperenderer.rect(r.x, r.y, r.width, r.height);
		}
		for (Runner sr : springareList) {
			r = sr.getRectangle();
			shaperenderer.setColor(Color.BLUE);
			shaperenderer.rect(r.x, r.y, r.width, r.height);
		}
		for (Plattform p : plattformList) {
			shaperenderer.setColor(Color.GREEN);
			r = p.getRectangle();
			shaperenderer.rect(r.x, r.y, r.width, r.height);
		}
		shaperenderer.setColor(Color.RED);
		r = new Rectangle(player.getPosition().x, player.getPosition().y,
				player.getSprite().getWidth(), player.getSprite().getHeight());
		shaperenderer.rect(r.x, r.y, r.width, r.height);

		shaperenderer.end();
		spritebatch.begin();
		if (drawText) {
			BitmapFont bmf = new BitmapFont();
			bmf.draw(spritebatch, message, 10, 300);
		}
		spritebatch.end();
		if (messageTicker > 500) {
			drawText = false;
		}

	}

	public void renderSprites() {
		messageTicker++;
		spritebatch.begin();
		Sprite s;
		// Background		
		spritebatch.draw(background.getTexture(), background.getOffset().x,
				background.getOffset().y);
		// Talkzones
		for (Talkzone t : talkzoneList) {
			s = t.getZoneSprite();
			s.draw(spritebatch);
		}
		s = master.getJacob().getZoneSprite();
		s.draw(spritebatch);
		// Throwers and their missiles
		for (Thrower e : enemyList) {
			if (e.isReady() == false) {
				Missile m = e.getMissile();
				s = m.getSprite();
				s.rotate(1.5f);
				s.draw(spritebatch);
			}
			s = e.getSprite();
			s.draw(spritebatch);
		}
		// Runners
		for (Runner spr : springareList) {
			s = spr.getSprite();
			s.draw(spritebatch);
		}
		// Player
		s = player.getSprite();
		s.draw(spritebatch);
		// Plattforms
		for (Plattform p : plattformList) {
			s = p.getSprite();
			s.draw(spritebatch);
		}
		// Text message (talkzone messages)
		if (drawText) {
			BitmapFont bmf = new BitmapFont();
			bmf.draw(spritebatch, message, 10, 300);
		}
		int hearts = player.getHealth();
		for (int i = 0; i < hearts; i++) {
			int x = 10 + i*70;
			int y = 530;			
			heart.setX(x);
			heart.setY(y);
			heart.draw(spritebatch);
		}		
		 
		spritebatch.end();
		
		if (messageTicker > 500) {
			drawText = false;
		}
	}
	
	public void dispose(){
		spritebatch.dispose();
		shaperenderer.dispose();
	}
	
	/**
	 * Call this method if you have a message to convey to the the one playing
	 * the game
	 * 
	 * @param m
	 */
	public void drawText(String m) {
		drawText = true;
		message = m;
		messageTicker = 0;
	}

}
