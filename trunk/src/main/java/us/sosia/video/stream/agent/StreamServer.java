package main.java.us.sosia.video.stream.agent;

import java.awt.Dimension;
import java.net.InetSocketAddress;

import com.github.sarxos.webcam.Webcam;


public class StreamServer {

	/**
	 * @author kerr
	 * @param args
	 */
	public static void main(String[] args) {
		Webcam.setAutoOpenMode(true);
		Webcam webcam = Webcam.getWebcams().get(0);
//		Webcam webcam2 = Webcam.getWebcams().get(1);
		Dimension dimension = new Dimension(640, 480);
		webcam.setViewSize(dimension);

		StreamServerAgent serverAgent = new StreamServerAgent(webcam, dimension);
		serverAgent.start(new InetSocketAddress("150.162.105.84", 20000));
		
//		StreamServerAgent serverAgent2 = new StreamServerAgent(webcam2, dimension);
//		serverAgent2.start(new InetSocketAddress("150.162.105.84", 8080));
	}

}
