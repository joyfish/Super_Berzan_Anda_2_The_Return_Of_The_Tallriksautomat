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

import entities.Plattform;
import entities.Player;



public class GameScreen implements Screen, InputProcessor{
	Player player;
	Background background;
	Painter painter;
	Controller controller;
	boolean UpDown = false, DownDown = false, RightDown = false, LeftDown = false;
	AreaChecker areaChecker;
	private ArrayList<Plattform> plattformList;
	
	public Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	public GameScreen(){
		super();		
		player = new Player();
		background = new Background(this);
		addPlattforms();		
		plattformList = background.getPlattforms();
		painter = new Painter(this, player, background);
		controller = new Controller(this);
		areaChecker = new AreaChecker(this, player);
		
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
		painter.render();	
		background.update(player.getRectangle());
		player.act();
		for(Plattform p : plattformList){
		p.setOffset(background.getOffset());
		}
	}

	@Override
	public void resize(int width, int height) {

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
		if(arg0 == Keys.UP)UpDown = false;
		if(arg0 == Keys.DOWN)DownDown = false;
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
	
	public Background getBackground(){
		return background;
	}

	private void addPlattforms(){ 
		background.addPlattform(new Vector2(101, 101), new Vector2(200,60));
		background.addPlattform(new Vector2(202, 202), new Vector2(200,60));
		background.addPlattform(new Vector2(505, 101), new Vector2(200,60));
		
	}
	
}
