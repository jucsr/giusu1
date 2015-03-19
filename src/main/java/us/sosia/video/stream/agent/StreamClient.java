package main.java.us.sosia.video.stream.agent;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.InetSocketAddress;

import main.java.us.sosia.video.stream.agent.ui.SingleVideoDisplayWindow;
import main.java.us.sosia.video.stream.agent.ui.VideoDisplayWindow;
import main.java.us.sosia.video.stream.handler.StreamFrameListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.UFSC.GRIMA.visual.ConnectWindow;


public class StreamClient
{
	/**
	 * @author kerr
	 * */
	private final static Dimension dimension = new Dimension(640, 480);
	private static SingleVideoDisplayWindow displayWindow = null;
	private static VideoDisplayWindow displayWindow1 = null;

	protected final static Logger logger = LoggerFactory.getLogger(StreamClient.class);
	protected  String ip = "";
	int  c = 0;
	
	
	
	public StreamClient(String ip)
	{
		
		this.ip = ip;
		start();
		
	}
	
	public void start() 
	{
		
			//setup the connection
			logger.info("setup dimension :{}",dimension);
			StreamClientAgent clientAgent = new StreamClientAgent(new StreamFrameListenerIMPL(),dimension);
			clientAgent.connect(new InetSocketAddress(ip, 20000));
			//setup the videoWindow
			displayWindow = new SingleVideoDisplayWindow("Stream",dimension, clientAgent);
			displayWindow.setVisible(true);
//			displayWindow1 = new VideoDisplayWindow("name", dimension);
//			displayWindow1.setVisible(true);

		}
	protected static class StreamFrameListenerIMPL implements StreamFrameListener{
		private volatile long count = 0;
		@Override
		public void onFrameReceived(BufferedImage image) {
			logger.info("frame received :{}",count++);
			displayWindow.updateImage(image);
//			displayWindow1.updateBigVideo(image);
//			displayWindow1.updateSmallVideo(image);
		}
		
	}
	
	public static void main(String [] args)
	{
		new StreamClient("150.162.105.76");
	}
}
