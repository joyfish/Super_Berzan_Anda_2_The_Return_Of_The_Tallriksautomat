package launchers;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Demo_1.ImagePanel;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher implements ActionListener{
	public Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * @param args	
	 */ 
	JFrame frame;
	
	public DesktopLauncher(){
		frame = giveWindow();
		frame.setVisible(true);
		frame.setSize(660, 400);
	}
	
	public JFrame giveWindow(){			
		JFrame jf = new JFrame("Berzan spel");
		jf.setLayout(new BorderLayout());
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout());
		
		JButton b = new JButton("Starta Spelet");
		b.addActionListener(this);
		b.setActionCommand("STARTA");
		bottom.add(b);
		
		JButton b2 = new JButton("Starta på Ljunkan");		
		b2.addActionListener(this);
		b2.setActionCommand("TROLLBOLL");
		bottom.add(b2);
		
		int width = 700;
		int height = 500;
		bottom.setSize(width, height);
		bottom.setLocation(screen.width/2 - width/2, screen.height/2-height/2);
		bottom.setVisible(true);	
		jf.add(bottom, BorderLayout.SOUTH);
		
		ImagePanel ip = new ImagePanel();		
		jf.add(ip,BorderLayout.CENTER);
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
			frame.dispose();
			new LwjglApplication(new DemoGame(), "Vårat Dem0000", screen.width/2 + 200,screen.height/2 + 200,true);
		}
		if(arg0.getActionCommand().equals("TROLLBOLL")){						
			Toolkit.getDefaultToolkit().beep();
			
		}
						
	}

	
}
