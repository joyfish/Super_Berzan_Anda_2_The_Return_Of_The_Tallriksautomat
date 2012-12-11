package Demo_1;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import entities.Plattform;
import entities.Player;

public class Painter {

	public OrthographicCamera cam;
	public GameScreen gs;

	public Player player;
	public Background background;	
	private ArrayList<Plattform> plattformList;
	public SpriteBatch spritebatch;
	
	public Painter(GameScreen gs,Player Player, Background Background){
		player = Player;
		background = Background;
		spritebatch = new SpriteBatch();
		cam = new OrthographicCamera(20,20);
		cam.position.set(10, 10, 0);
		plattformList = gs.getPlattforms(); 
	}
	
	public void render(){
		spritebatch.begin();
		spritebatch.draw(background.getTexture(), background.getOffset().x, background.getOffset().y);
		spritebatch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
		for(Plattform p : plattformList){
		spritebatch.draw(p.getTexture(), p.getPosition().x,p.getPosition().y ,p.getTexture().getWidth(),p.getTexture().getHeight());
		}
		spritebatch.end();
	}
	
}
