package main.java.us.sosia.video.stream.agent;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.net.InetSocketAddress;

import javax.swing.JMenuItem;

import main.java.us.sosia.video.stream.agent.ui.SingleVideoDisplayWindow;
import main.java.us.sosia.video.stream.agent.ui.VideoDisplayWindow;
import main.java.us.sosia.video.stream.handler.StreamFrameListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class StreamClient
{
	/**
	 * @author kerr
	 * */
	private final static Dimension dimension = new Dimension(640, 480);
	private  SingleVideoDisplayWindow displayWindow = null;

	protected final  Logger logger = LoggerFactory.getLogger(StreamClient.class);
	protected   String ip = "";
	protected  int port;
	protected JMenuItem menuItem;
	
	
	
	public StreamClient(String ip,  int port, JMenuItem menuItem)
	{
		
		this.ip = ip;
		this.port = port;
		this.menuItem = menuItem;
		start();
		
		
	}
	
	public void start() 
	{
		
			//setup the connection
			logger.info("setup dimension :{}",dimension);
			StreamClientAgent clientAgent = new StreamClientAgent(new StreamFrameListenerIMPL(),dimension);
			clientAgent.connect(new InetSocketAddress(ip, port));
			//setup the videoWindow
			displayWindow = new SingleVideoDisplayWindow("Stream",dimension, clientAgent, menuItem);
			displayWindow.setVisible(true);


		}
	protected  class StreamFrameListenerIMPL implements StreamFrameListener{
		private volatile long count = 0;
		@Override
		public void onFrameReceived(BufferedImage image) {
			logger.info("frame received :{}",count++);
			displayWindow.updateImage(image);

		}
		
	}
	
	public static void main(String [] arg0)
	{
	
	}
}
