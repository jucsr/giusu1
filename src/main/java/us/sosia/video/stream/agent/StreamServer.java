package main.java.us.sosia.video.stream.agent;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;

import br.UFSC.GRIMA.visual.ConnectWindow;

import com.github.sarxos.webcam.Webcam;


public class StreamServer extends ConnectWindow implements ActionListener {
	String ip = "";
	StreamServerAgent serverAgent = null;
	public StreamServer()
	{
		this.button1.addActionListener(this);
		
	}
	
	public static void main(String[] args) {
		StreamServer window = new StreamServer();
		window.setVisible(true);
	}
	
	public void start()
	{
		ip = textField1.getText();
		Webcam.setAutoOpenMode(true);
		Webcam webcam = Webcam.getDefault();
//		Webcam webcam2 = Webcam.getWebcams().get(1);
		Dimension dimension = new Dimension(640, 480);
		webcam.setViewSize(dimension);

		serverAgent = new StreamServerAgent(webcam, dimension);
		serverAgent.start(new InetSocketAddress(ip, 20000));
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
			serverAgent.stop();
		}
		
	}

}
