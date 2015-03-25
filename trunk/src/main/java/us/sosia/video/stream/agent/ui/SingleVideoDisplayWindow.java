package main.java.us.sosia.video.stream.agent.ui;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import main.java.us.sosia.video.stream.agent.StreamClientAgent;

public class SingleVideoDisplayWindow{ //implements WindowListener {
	protected final VideoPanel videoPannel;
	protected final JFrame window;
	protected StreamClientAgent clientAgent;
	protected JMenuItem menuItem;
	
	public SingleVideoDisplayWindow(String name,Dimension dimension, StreamClientAgent clientAgent, JMenuItem menuItem) {
		super();
		this.menuItem = menuItem;
		this.window = new JFrame(name);
		this.videoPannel = new VideoPanel();
		this.clientAgent = clientAgent;
		this.videoPannel.setPreferredSize(dimension);
		this.window.add(videoPannel);
		this.window.pack();
		this.window.addWindowListener(new WindowAdapter(){
			 public void windowClosing(WindowEvent e)
		        {
		            System.out.println("Window Closing.........");
		            close();
		        }
		});
//		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setVisible(boolean visible) {
		this.window.setVisible(visible);
	}

	public void updateImage(BufferedImage image) {
		videoPannel.updateImage(image);
	}
	
	public void close()
	{
		window.dispose();
		clientAgent.stop();
		videoPannel.close();
		menuItem.setEnabled(true);
	}
	
//	@Override
//	public void windowClosed(WindowEvent e)
//	{
//		close();	
//	}
//	@Override
//	public void windowClosing(WindowEvent e) {
//		// TODO Auto-generated method stub
//		close();
//	}
//	@Override
//	public void windowOpened(WindowEvent e) {
//		// TODO Auto-generated method stub
//		System.out.println("Entrado");
//	}
//
//	@Override
//	public void windowActivated(WindowEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void windowDeactivated(WindowEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void windowDeiconified(WindowEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
}
