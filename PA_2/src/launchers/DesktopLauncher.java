package launchers;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Demo_1.ImagePanel;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher implements ActionListener{
	public Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * @param args	
	 */ 
	JFrame frame, options;
	Dimension preferredSize;
	Rectangle size;
	Dimension optionsResolution;
	
	public DesktopLauncher(){
		frame = giveWindow();
		frame.setVisible(true);
		size= new Rectangle(screen.width/2 - preferredSize.width/2 , screen.height/2-preferredSize.height/2, preferredSize.width,preferredSize.height);
		frame.setBounds(size);
	}
	
	public JFrame optionsWindow(){
		JFrame jf = new JFrame("Options");
		jf.setLayout(new BorderLayout());				
		
		JPanel buttonpanel = new JPanel();
		JButton close = new JButton("Close");
		JButton apply = new JButton("Apply");
		close.addActionListener(this);
		apply.addActionListener(this);
		close.setActionCommand("CLOSEOPTIONS");
		apply.setActionCommand("APPLYOPTIONS");
		buttonpanel.add(close);
		buttonpanel.add(apply);
		jf.add(buttonpanel,BorderLayout.SOUTH);
		
		
		//Top
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());		
		top.add(new JLabel("Välj din egen stil:"));
		jf.add(top, BorderLayout.NORTH);
		
		//Center
		JPanel center = new JPanel();		
		center.setLayout(new GridLayout(4,4,10,10));
		
		for(int i = 0; i < 16; i++){
			center.add(new ImageButton());
		}
//		
//		String[] screenResolutions = {"Default", "1337x666", "640x400"};
//		JComboBox jcb = new JComboBox<>(screenResolutions);		
//		
//		center.add(jcb);
		
		jf.add(center,BorderLayout.CENTER);
		
		return jf;
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
		
		JButton opt = new JButton("Options");
		opt.addActionListener(this);
		opt.setActionCommand("OPTIONS");
		bottom.add(opt);
		
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
		preferredSize = ip.getPreferredSize();
		return jf;
	}
	
	public static void main(String[] args) {
		
		//HELLU!!!! I even added code ^^
		new DesktopLauncher();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = arg0.getActionCommand();
		if(s.equals("STARTA")){
			frame.removeAll();
			frame.dispose();
			new LwjglApplication(new DemoGame(), "Vårat Dem0000",size.width + 200,size.height + 200,true);
		}
		if(s.equals("TROLLBOLL")){						
			Toolkit.getDefaultToolkit().beep();			
		}
		if(s.equals("OPTIONS")){
			startOptions();
		}
		if(s.equals("CLOSEOPTIONS")){
			options.dispose();
		}
		if(s.equals("RESOLUTION")){
			System.out.println(s);
		}
						
	}

	private void startOptions() { 
		options = optionsWindow();
		options.setVisible(true);
		options.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		options.setBounds(size);
	}	

	
}
