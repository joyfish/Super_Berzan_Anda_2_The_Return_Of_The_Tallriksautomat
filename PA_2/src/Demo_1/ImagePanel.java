package Demo_1;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	Image ii;
	public ImagePanel(){
		
		try {
			ii = ImageIO.read(new File("C:\\Users\\CatEars\\git\\Super_Berzan_Anda_2_The_Return_Of_The_Tallriksautomat\\PA_2\\src\\StartScreen.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(ii,0,0,null);
	}
	
}
