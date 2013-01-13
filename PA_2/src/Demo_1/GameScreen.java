package Demo_1;

import inputhandler.Controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import collision.AreaChecker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;

import entities.Enemy;
import entities.Plattform;
import entities.Player;
import entities.Talkzone;



public class GameScreen implements Screen, InputProcessor{
	Player player;
	Background background;
	Painter painter;
	Controller controller;
	boolean UpDown = false, DownDown = false, RightDown = false, LeftDown = false;
	AreaChecker areaChecker;
	private ArrayList<Plattform> plattformList;
	private ArrayList<Talkzone> talkzoneList;
	public Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private ArrayList<Enemy> enemyList;
	public Dimension applicationSize;
	
	public GameScreen(){
		super();		
		talkzoneList = new ArrayList<Talkzone>();
		enemyList = new ArrayList<Enemy>();
		player = new Player();
		background = new Background(this);
		addPlattforms();		
		plattformList = background.getPlattforms();
		painter = new Painter(this, player, background);
		controller = new Controller(this);		
		areaChecker = new AreaChecker(this, player);
		addTalkzones();
		
		enemyList.add(new Enemy(new Vector2(500,400)));		
		Gdx.input.setInputProcessor(this);		
	}
	
	

	@Override
	public void dispose() {
		// TODO Auto-generated method stub		
	}	
	
	public ArrayList<Plattform> getPlattforms(){
		return background.getPlattforms();
	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		areaChecker.update();
		controller.move(LeftDown,RightDown,DownDown,UpDown);		
		painter.renderSprites();	
		background.update(player.getRectangle());
		player.act();
		for(Plattform p : plattformList){
		p.setOffset(background.getOffset());
		}
		for(Enemy e : enemyList){
			e.setOffset(background.getOffset());
			e.act(this);
		}
	}

	@Override
	public void resize(int width, int height) {
		applicationSize = new Dimension(width,height);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		if(arg0 == Keys.LEFT) LeftDown = true;
		if(arg0 == Keys.RIGHT) RightDown = true;
		if(arg0 == Keys.UP)UpDown = true;
		if(arg0 == Keys.DOWN)DownDown = true;
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		if(arg0 == Keys.LEFT) LeftDown = false;
		if(arg0 == Keys.RIGHT) RightDown = false;
		if(arg0 == Keys.UP) UpDown = false;
		if(arg0 == Keys.DOWN) DownDown = false;
		if(arg0 == Keys.T) background.activateTalkzones(player.getRectangle());
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Player getPlayer(){
		return player;
	}
	public Controller getController(){
		return controller;
	}
	
	public Background getBackground(){
		return background;
	}		
	
	private void addPlattforms(){ 
		background.addPlattform(new Vector2(), new Vector2());		
		background.addPlattform(new Vector2(300,0), new Vector2(100,150));
		background.addPlattform(new Vector2(600,100), new Vector2(200,60));
		background.addPlattform(new Vector2(900,0), new Vector2(150,200));
		background.addPlattform(new Vector2(1500,150), new Vector2(200,30));
		background.addPlattform(new Vector2(1800,50), new Vector2(200,60));
		background.addPlattform(new Vector2(1800,250), new Vector2(200,60));
		background.addPlattform(new Vector2(2100,0), new Vector2(150,300));
		background.addPlattform(new Vector2(2600,0), new Vector2(100,100)); //Special guy
		background.addPlattform(new Vector2(2750,200), new Vector2(200,60));
		background.addPlattform(new Vector2(3000,0), new Vector2(200,200)); //Change background at crossing 3000
		background.addPlattform(new Vector2(3700,100), new Vector2(200,60));
		background.addPlattform(new Vector2(3950,0), new Vector2(100,150));
		background.addPlattform(new Vector2(4250,100), new Vector2(50,50));
		background.addPlattform(new Vector2(4350,0), new Vector2(100,250));
		background.addPlattform(new Vector2(5050,0), new Vector2(75,75));
		background.addPlattform(new Vector2(5250,0), new Vector2(200,200));
		background.addPlattform(new Vector2(5500,200), new Vector2(200,60));
		background.addPlattform(new Vector2(5750,300), new Vector2(200,60));
		background.addPlattform(new Vector2(5900,0), new Vector2(50,300));
		background.addPlattform(new Vector2(6000,100), new Vector2(50,50));
		background.addPlattform(new Vector2(6200,150), new Vector2(200,60));
		background.addPlattform(new Vector2(6450,50), new Vector2(100,30));
		background.addPlattform(new Vector2(6450,300), new Vector2(100,30));
		background.addPlattform(new Vector2(6650,150), new Vector2(200,60));
		background.addPlattform(new Vector2(6850,0), new Vector2(50,300));
		background.addPlattform(new Vector2(6900,250), new Vector2(100,10));
		background.addPlattform(new Vector2(7100,150), new Vector2(200,60));  
		background.addPlattform(new Vector2(), new Vector2()); //Classroom zone starts here
		background.addPlattform(new Vector2(7500,0), new Vector2(100,100));
		background.addPlattform(new Vector2(7600,175), new Vector2(100,30));		
		background.addPlattform(new Vector2(7750,0), new Vector2(100,175));
		background.addPlattform(new Vector2(7750,300), new Vector2(200,30));
		background.addPlattform(new Vector2(8000,175), new Vector2(200,60));
		background.addPlattform(new Vector2(8500,0), new Vector2(100,100));
		background.addPlattform(new Vector2(8700,0), new Vector2(100,200));
		background.addPlattform(new Vector2(8900,200), new Vector2(200,60));
		background.addPlattform(new Vector2(9200,0), new Vector2(100,150));
		background.addPlattform(new Vector2(9500,150), new Vector2(100,500));
		background.addPlattform(new Vector2(9800,0), new Vector2(100,100));
		background.addPlattform(new Vector2(10000,200), new Vector2(100,30));
		background.addPlattform(new Vector2(10100,0), new Vector2(100,100));
		background.addPlattform(new Vector2(10275,150), new Vector2(100,30));
	}

	private void addTalkzones(){
		background.addTalkzone(new Vector2(0,0), "Welcome to zone 1");
		background.addTalkzone(new Vector2(400,0), "Welcome to zone 2");
	}
	
	public ArrayList<Talkzone> getTalkzones() {
		return talkzoneList;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemyList;
	}



	
	
}
