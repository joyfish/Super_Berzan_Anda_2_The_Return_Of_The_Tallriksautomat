package Demo_1;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

import entities.Plattform;

public class Lists {

	
	public static ArrayList<Plattform> getPlattforms(GameScreen master){
		ArrayList<Plattform> alp = new ArrayList();
		Background background = new Background(master);
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
		background.addPlattform(new Vector2(5400,200), new Vector2(400,60));
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
		background.addPlattform(new Vector2(10750,250), new Vector2(100,30));
		background.addPlattform(new Vector2(10500,350), new Vector2(100,30));
		background.addPlattform(new Vector2(10275,350), new Vector2(100,30));
//		background.addPlattform(new Vector2(10950,470), new Vector2(100,30)); plattform reserved for enemy
		background.addPlattform(new Vector2(10050,400), new Vector2(100,30));
		background.addPlattform(new Vector2(10275,500), new Vector2(100,30));
		background.addPlattform(new Vector2(10500,500), new Vector2(100,30));
		background.addPlattform(new Vector2(10850,0), new Vector2(75,500));
		background.addPlattform(new Vector2(10900,470), new Vector2(100,30));
		background.addPlattform(new Vector2(11100,300), new Vector2(100,30));
		background.addPlattform(new Vector2(10900,170), new Vector2(100,30));
		background.addPlattform(new Vector2(11200,200), new Vector2(70,1000));
		background.addPlattform(new Vector2(11500,0), new Vector2(250,100));
		background.addPlattform(new Vector2(11750,0), new Vector2(100,200));
		background.addPlattform(new Vector2(11850,100), new Vector2(75,75));		
		alp = background.getPlattforms();
		return alp;
	}
	
}
