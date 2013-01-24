package launchers;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher implements ActionListener{
	public Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * @param args	
	 */ 
	JFrame frame;
	
	public DesktopLauncher(){
		frame = giveWindow();
	}
	
	public JFrame giveWindow(){
		JFrame jf = new JFrame("Berzan spel");
		jf.setLayout(new GridLayout(1, 2, 50, 50));
		
		JButton b = new JButton("Starta Spelet");
		b.addActionListener(this);
		b.setActionCommand("STARTA");
		jf.add(b);
		
		JButton b2 = new JButton("Starta på Ljunkan");		
		b2.addActionListener(this);
		b2.setActionCommand("TROLLBOLL");
		jf.add(b2);
		
		int width = 700;
		int height = 500;
		jf.setSize(width, height);
		jf.setLocation(screen.width/2 - width/2, screen.height/2-height/2);
		jf.setVisible(true);		
		return jf;
	}
	
	public static void main(String[] args) {
		
		//HELLU!!!! I even added code ^^
		new DesktopLauncher();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("STARTA")){
			frame.removeAll();
			new LwjglApplication(new DemoGame(), "Vårat Dem0000", screen.width/2 + 200,screen.height/2 + 200,true);
		}
		if(arg0.getActionCommand().equals("TROLLBOLL")){						
			Toolkit.getDefaultToolkit().beep();
			
		}
						
	}

	
}
