package Demo_1;

import inputhandler.Controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import launchers.Credits;
import launchers.DemoGame;
import sounds.SoundPlayer;
import collision.AreaChecker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Intersector;

import entities.Plattform;
import entities.Player;
import entities.Runner;
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
	int endingTimer = 0;
	Credits credits = new Credits(this);
	SoundPlayer sp;
	Talkzone jacobZone = Lists.getJacob();
	DemoGame master;

	public GameScreen(DemoGame dg) {
		super();
		master = dg;
		player = new Player();
		background = new Background(this);
		painter = new Painter(this);
		controller = new Controller(this);
		areaChecker = new AreaChecker(this);
		entityMaster = new EntityMaster(this);
		sp = new SoundPlayer();		
		initialize();
		Gdx.input.setInputProcessor(this);
		// System.out.println("Construction finished");
	}

	private void initialize() {
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
		sp.dispose();
		painter.dispose();
	}

	public ArrayList<Plattform> getPlattforms() {
		return background.getPlattforms();
	}

	private void endGame() {
		master.endGame();
	}

	private void renderScreen() {
		switch (state) {
		case (Lists.INTRO):
			if (credits.drawIntro()) {
				credits.resetTimer();
				state = Lists.GAME;
			}
			break;
		case (Lists.GAME):
			painter.renderSprites();
			break;
		case (Lists.GAMEOVER):
			credits.drawGameOver();
			break;
		case (Lists.ENDING):
			while (credits.drawEnd()) {
				dispose();
			}
			break;
		}
	}

	private boolean loseCondition() {
		if (jacobZone.isInside()) {
			return false;
		}
		if (player.health <= 0) {
			return true;
		} else {
			return false;
		}
	}

	private void winCondition() {
		if (won) {
			if (endingTimer > 500) {
				state = Lists.ENDING;
			}
			endingTimer++;
		}
	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		areaChecker.update();
		controller.move(LeftDown, RightDown, DownDown, UpDown);
		renderScreen();
		entityMaster.act();
		winCondition();
		if (loseCondition()) {
			state = Lists.GAMEOVER;
		}
		jacobZone.setOffset(background.getOffset());
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
		sp.loopRedRiverRock();
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
		if (arg0 == Keys.LEFT)
			LeftDown = true;
		if (arg0 == Keys.RIGHT)
			RightDown = true;
		if (arg0 == Keys.UP)
			UpDown = true;
		if (arg0 == Keys.DOWN)
			DownDown = true;

		if (arg0 == Keys.A) {
			state = Lists.INTRO;
			credits.resetTimer();
		}
		if (arg0 == Keys.B) {
			state = Lists.GAME;
			credits.resetTimer();
		}
		if (arg0 == Keys.C) {
			state = Lists.GAMEOVER;
			credits.resetTimer();
		}
		if (arg0 == Keys.D) {
			state = Lists.ENDING;
			credits.resetTimer();
		}
		if (arg0 == Keys.E) {
			System.out.println("x: "
					+ (player.getPosition().x - background.offset.x));
			System.out.println("y: " + player.getPosition().y);
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
		if (arg0 == Keys.T) {
			controller.activateTalkzones(player.getScreenRextangle());
			if (Intersector.overlapRectangles(player.getScreenRextangle(),
				jacobZone.getZone())){
				won = true;
				painter.drawText(jacobZone.getMessage());
			}
		}
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

	public ArrayList<Talkzone> getTalkzones() {
		return background.getTalkzones();
	}

	public ArrayList<Thrower> getThrowers() {
		return entityMaster.getThrowers();
	}

	public ArrayList<Runner> getRunners() {
		return entityMaster.getRunners();
	}

	public Painter getPainter() {
		return painter;
	}

	public Talkzone getJacob() {
		return jacobZone;
	}

}
