package Demo_1;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import entities.Plattform;
import entities.Springare;
import entities.Talkzone;
import entities.Thrower;

public class Lists {
	public static final int INTRO = 1;
	public static final int GAME = 2;
	public static final int GAMEOVER = 3;
	public static final int ENDING = 4;
	
	public static ArrayList<String> getIntroStrings(){
		ArrayList<String> al = new ArrayList();
		al.add("Hello World!");
		al.add("You are a student at Berzeliusskolan");
		al.add("You are attending your first year and the Berziad has just started");
		al.add("to talk to people stand close to them and press 't'");
		return al;
	}
	
	public static ArrayList<String> getEndingStrings(){
		ArrayList<String> al = new ArrayList();
		al.add("Congratulations!");
		al.add("You have saved berzan from destruction");
		al.add("May the spirit of Berzelius be forever with you");
		return al;
	}
	
	public static ArrayList<Texture> getBackgrounds(){
		ArrayList<Texture> tet = new ArrayList();
		tet.add(new Texture(Gdx.files.internal("BakrundDemo1.png")));				
		return tet;
	}
	
	public static ArrayList<Plattform> getPlattforms(GameScreen master){
		ArrayList<Plattform> alp = new ArrayList();
		Background background = new Background(master);			
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
		background.addPlattform(new Vector2(5250,0), new Vector2(150,200));
		background.addPlattform(new Vector2(5400,140), new Vector2(400,60));
		background.addPlattform(new Vector2(5750,300), new Vector2(200,60));
//		background.addPlattform(new Vector2(5900,0), new Vector2(50,300));
		background.addPlattform(new Vector2(6000,100), new Vector2(50,50));
		background.addPlattform(new Vector2(6200,150), new Vector2(200,60));
		background.addPlattform(new Vector2(6450,50), new Vector2(100,30));
		background.addPlattform(new Vector2(6450,300), new Vector2(100,30));
		background.addPlattform(new Vector2(6650,150), new Vector2(150,60));
		background.addPlattform(new Vector2(6850,0), new Vector2(50,300));
		background.addPlattform(new Vector2(6900,250), new Vector2(100,10));
		background.addPlattform(new Vector2(7100,150), new Vector2(200,60));  
//		background.addPlattform(new Vector2(), new Vector2()); //Classroom zone starts here
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
		background.addPlattform(new Vector2(10500,150), new Vector2(100,30));
		background.addPlattform(new Vector2(10700,250), new Vector2(100,30));
		background.addPlattform(new Vector2(10500,350), new Vector2(100,30));
//		background.addPlattform(new Vector2(10400,325), new Vector2(50,30));
//		background.addPlattform(new Vector2(10275,350), new Vector2(100,30));
//		background.addPlattform(new Vector2(10950,470), new Vector2(100,30)); // plattform reserved for enemy
//		background.addPlattform(new Vector2(10050,400), new Vector2(100,30));
//		background.addPlattform(new Vector2(10275,500), new Vector2(100,30));
//		background.addPlattform(new Vector2(10500,500), new Vector2(100,30));
		background.addPlattform(new Vector2(10850,0), new Vector2(75,500));
		background.addPlattform(new Vector2(10900,470), new Vector2(100,30));
		background.addPlattform(new Vector2(11100,300), new Vector2(100,30));
		background.addPlattform(new Vector2(10900,170), new Vector2(100,30));
		background.addPlattform(new Vector2(11200,200), new Vector2(70,1000));
		background.addPlattform(new Vector2(11500,0), new Vector2(250,100));
		background.addPlattform(new Vector2(11750,0), new Vector2(100,200));
		background.addPlattform(new Vector2(11850,75), new Vector2(75,75));
		background.addPlattform(new Vector2(12100,250),new Vector2(200,60));
		background.addPlattform(new Vector2(12500,200),new Vector2(200,60));
		background.addPlattform(new Vector2(12850,200),new Vector2(100,100));
		background.addPlattform(new Vector2(13100,390),new Vector2(100,60));		
		background.addPlattform(new Vector2(13200,0),new Vector2(60,450));
		background.addPlattform(new Vector2(13260,0),new Vector2(300,500));
		background.addPlattform(new Vector2(13560,400),new Vector2(100,30));
		background.addPlattform(new Vector2(13660,200),new Vector2(100,30));
		background.addPlattform(new Vector2(13760,100),new Vector2(100,30));		
		alp = background.getPlattforms();
		return alp;
	}

	public static ArrayList<Springare> getRunners() {
		ArrayList<Springare> al = new ArrayList();
//		al.add(new Springare(new Vector2(),new Vector2(),5));
		al.add(new Springare(new Vector2(1100,0), new Vector2(1400,0),1));
		al.add(new Springare(new Vector2(1100,0), new Vector2(1400,0),2));
		al.add(new Springare(new Vector2(1100,0), new Vector2(1400,0),3));
		
		al.add(new Springare(new Vector2(5950,0), new Vector2(6300,0),1));
		al.add(new Springare(new Vector2(5950,0), new Vector2(6300,0),2));
		al.add(new Springare(new Vector2(5950,0), new Vector2(6300,0),3));
		
		al.add(new Springare(new Vector2(8000,0),new Vector2(8400,0),1));
		al.add(new Springare(new Vector2(8000,0),new Vector2(8400,0),2));
		al.add(new Springare(new Vector2(8000,0),new Vector2(8400,0),3));
		
		al.add(new Springare(new Vector2(9350,0),new Vector2(9650,0),2));
		al.add(new Springare(new Vector2(9350,0),new Vector2(9650,0),1));
		
		al.add(new Springare(new Vector2(11100,0),new Vector2(11350,0),1));
		al.add(new Springare(new Vector2(11100,0),new Vector2(11350,0),2));
		al.add(new Springare(new Vector2(11100,0),new Vector2(11350,0),3));
		
		return al;
	}

	public static ArrayList<Thrower> getThrowers() {
		ArrayList<Thrower> al = new ArrayList ();
		al.add(new Thrower(new Vector2(3800,0)));
		al.add(new Thrower(new Vector2(6650,210)));
		al.add(new Thrower(new Vector2(10300,0)));
		al.add(new Thrower(new Vector2(11700,100)));
		return al;
	}

	public static ArrayList<Texture> getTextures(){
		ArrayList<Texture> al = new ArrayList<>();
		for (int i =1; i <= 26; i++) {
			Texture t = new Texture(Gdx.files.internal("pic\\huvudlös" + i + ".png"));
			al.add(t);
		}
		return al;
	}
	
	
	public static Talkzone getJacob(){
		Talkzone tz = new Talkzone(new Vector2(14050,0), new Vector2(200,200), "");
//		tz.setTexture(Gdx.files.internal("Jacob.png"));
		return tz;
	}
	
	public static Texture getBackgroundImage() {
		return new Texture(Gdx.files.internal("BakrundDemo2.1.png"));		
	}
	
}