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
	boolean UpDown = false, DownDown = false, RightDown = false,
			LeftDown = false;
	AreaChecker areaChecker;
	private ArrayList<Plattform> plattformList;
	private ArrayList<Talkzone> talkzoneList;
	public Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private ArrayList<Thrower> enemyList;
	private ArrayList<Springare> springareList;
	public Dimension applicationSize;
	public boolean GameOver = false, won = false;;	
	int state = Lists.INTRO;
	Credits credits = new Credits(this);
	
	public GameScreen() {
		super();
		talkzoneList = new ArrayList<Talkzone>();
		enemyList = new ArrayList<Thrower>();
		springareList = new ArrayList<Springare>();
		player = new Player();
		background = new Background(this);
		addPlattforms();
		plattformList = background.getPlattforms();
		painter = new Painter(this, player, background);
		controller = new Controller(this);
		areaChecker = new AreaChecker(this, player);
		addTalkzones();

		enemyList.add(new Thrower(new Vector2(500, 400)));
		springareList.add(new Springare(new Vector2(400, 400), new Vector2(600,
				400), 1));
		Gdx.input.setInputProcessor(this);
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
			credits.drawEnd();
			break;
		}
	}
	
	private void setOffset(){
		for (Plattform p : plattformList) {
			p.setOffset(background.getOffset());
		}

		for (Thrower e : enemyList) {
			e.setOffset(background.getOffset());
			e.act(this);
			Missile m = e.getMissile();
			m.setOffset(background.getOffset());
		}

		for (Springare s : springareList) {
			s.setOffset(background.getOffset());
			s.act();
		}

	}
	
	@Override
	public void render(float arg0) {		
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);		
		
		areaChecker.update();
		controller.move(LeftDown, RightDown, DownDown, UpDown);
		renderScreen();
		background.update(player.getRectangle());
		player.act();
		setOffset();		
		
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
		
		if(arg0 == Keys.A){
			state = Lists.INTRO;			
		}
		if(arg0 == Keys.B){
			state = Lists.GAME;						
		}
		if(arg0 == Keys.C){
			state = Lists.GAMEOVER;
		}
		if(arg0 == Keys.D){
			state = Lists.ENDING;
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

	public ArrayList<Thrower> getEnemies() {
		return enemyList;
	}

	public ArrayList<Springare> getSpringare() {
		return springareList;
	}

	public Painter getPainter() {
		return painter;
	}

}
