package Demo_1;

import inputhandler.Controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import launchers.Credits;
import collision.AreaChecker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;

import entities.Missile;
import entities.Plattform;
import entities.Player;
import entities.Springare;
import entities.Talkzone;
import entities.Thrower;

public class GameScreen implements Screen, InputProcessor {
	Player player;
	Background background;
	Painter painter;
	Controller controller;
	EntityMaster entityMaster;
	boolean UpDown = false, DownDown = false, RightDown = false,
			LeftDown = false;
	AreaChecker areaChecker;
	private ArrayList<Plattform> plattformList;
	private ArrayList<Talkzone> talkzoneList;
	public Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();		
	public Dimension applicationSize;
	public boolean GameOver = false, won = false;;	
	int state = Lists.INTRO;
	Credits credits = new Credits(this);
	
	public GameScreen() {
		super();				
		player = new Player();
		background = new Background(this);				
		painter = new Painter(this);
		controller = new Controller(this);
		areaChecker = new AreaChecker(this);
		entityMaster = new EntityMaster(this);
		addPlattforms();
		addTalkzones();
		initialize();			
		Gdx.input.setInputProcessor(this);
	}

	private void initialize(){		
		background.initialize();
		painter.initialize();
		controller.initialize();
		areaChecker.initialize();
		entityMaster.initialize();
		plattformList = background.getPlattforms();
		talkzoneList = background.getTalkzones();
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}		

	public ArrayList<Plattform> getPlattforms() {
		return background.getPlattforms();
	}	
	
	private void renderScreen(){
		switch (state) {
		case(Lists.INTRO):			
			if(credits.drawIntro()){
				credits.resetTimer();
				state = Lists.GAME;
			}
			break;
		case(Lists.GAME):
			painter.renderSprites();
			break;
		case(Lists.GAMEOVER):
//			credits.drawGameOver();
			break;
		case(Lists.ENDING):
			if(credits.drawEnd()){
				//end game
			}
			break;
		}
	}	
	
	@Override
	public void render(float arg0) {		
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);		
		
		areaChecker.update();
		controller.move(LeftDown, RightDown, DownDown, UpDown);
		renderScreen();		
		player.act();
		entityMaster.act();			
		
	}

	@Override
	public void resize(int width, int height) {
		applicationSize = new Dimension(width, height);
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
		if (arg0 == Keys.LEFT)
			LeftDown = true;
		if (arg0 == Keys.RIGHT)
			RightDown = true;
		if (arg0 == Keys.UP)
			UpDown = true;
		if (arg0 == Keys.DOWN)
			DownDown = true;
		
		//For testing purposes only
		if(arg0 == Keys.A){
			state = Lists.INTRO;
			credits.resetTimer();
		}
		if(arg0 == Keys.B){
			state = Lists.GAME;
			credits.resetTimer();
		}
		if(arg0 == Keys.C){
			state = Lists.GAMEOVER;
			credits.resetTimer();
		}
		if(arg0 == Keys.D){
			state = Lists.ENDING;
			credits.resetTimer();
		}
		if(arg0 == Keys.E){
			Vector2 v = background.getOffset();
			v.x -= 12000;			
		}
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		if (arg0 == Keys.LEFT)
			LeftDown = false;
		if (arg0 == Keys.RIGHT)
			RightDown = false;
		if (arg0 == Keys.UP)
			UpDown = false;
		if (arg0 == Keys.DOWN)
			DownDown = false;
		if (arg0 == Keys.T)
			controller.activateTalkzones(player.getRectangle());
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

	public Player getPlayer() {
		return player;
	}

	public Controller getController() {
		return controller;
	}

	public Background getBackground() {
		return background;
	}

	private void addPlattforms() {
		ArrayList<Plattform> al = Lists.getPlattforms(this);
		for (int i = 0; i < al.size(); i++) {
			background.addPlattform(al.get(i));
		}
	}

	private void addTalkzones() {
		background.addTalkzone(new Vector2(0, 0), "Welcome to zone 1");
		background.addTalkzone(new Vector2(400, 0), "Welcome to zone 2");
	}

	public ArrayList<Talkzone> getTalkzones() {
		return talkzoneList;
	}

	public ArrayList<Thrower> getThrowers() {
		return entityMaster.getThrowers();
	}

	public ArrayList<Springare> getRunners() {
		return entityMaster.getRunners();
	}

	public Painter getPainter() {
		return painter;
	}

}
