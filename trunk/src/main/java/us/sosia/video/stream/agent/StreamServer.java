package main.java.us.sosia.video.stream.agent;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import br.UFSC.GRIMA.visual.ConnectWindow;

import com.github.sarxos.webcam.Webcam;


public class StreamServer extends ConnectWindow implements ActionListener {
	static String ip = "";
	int port;
	static StreamServerAgent serverAgent = null;
	ArrayList<StreamServerAgent> streamList = new ArrayList<StreamServerAgent>();
	static Webcam webcam;
	public StreamServer()
	{
		this.button1.addActionListener(this);
		
	}
	
	public static void main(String[] args) {
		StreamServer window = new StreamServer();
		window.setVisible(true);
		System.out.println(webcam.getWebcams());
		
	}
	
	public void start()
	{
		ip = textField1.getText();
		port = Integer.parseInt(textField3.getText());
		for(int i = 0 ; i< webcam.getWebcams().size() ; i++)
		{
			webcam.setAutoOpenMode(true);
			Dimension dimension = new Dimension(640, 480);
			webcam.getWebcams().get(i).setViewSize(dimension);
			serverAgent = new StreamServerAgent(webcam.getWebcams().get(i), dimension);
			serverAgent.start(new InetSocketAddress(ip, port));
			streamList.add(serverAgent);
			port = port - 10;
		}
		
	}
	public void stop()
	{
		
		for(int i = 0 ; i< webcam.getWebcams().size() ; i++)
		{
			webcam.getWebcams().get(i).close();
			streamList.get(i).stop();
		}
		
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (button1.isSelected())
		{
			button1.setName("Stop Server!");
			start();
			
		}
		else if(!button1.isSelected())
		{
			button1.setName("Start Server!");
			stop();
			
				
		}
		
	}

}
