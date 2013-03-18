package launchers;

import java.awt.Dimension;
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
			ii = ImageIO.read(new File("src\\StartScreen.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Dimension getPreferredSize(){
		Dimension dim = new Dimension();
		dim.height = ii.getHeight(null);
		dim.width = ii.getWidth(null);
		return dim;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(ii,0,0,null);
	}
	
}
