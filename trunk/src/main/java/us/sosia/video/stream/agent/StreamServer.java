package main.java.us.sosia.video.stream.agent;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import br.UFSC.GRIMA.thread.MyIp;
import br.UFSC.GRIMA.thread.TrataCliente;
import br.UFSC.GRIMA.visual.ConnectWindow;

import com.github.sarxos.webcam.Webcam;


public class StreamServer extends ConnectWindow implements ActionListener {
	static String ip = "";
	int port;
	int size;
	static StreamServerAgent serverAgent = null;
	ArrayList<StreamServerAgent> streamList = new ArrayList<StreamServerAgent>();
	private ArrayList<PrintStream> clientes = new ArrayList<PrintStream>();
	static Webcam webcam;
	public StreamServer()
	{
		ip = MyIp.getMyIp();
		this.button1.addActionListener(this);
		this.textPane1.setText("Localhost: " + ip);
		this.textPane1.setText("\nCams List: " + ip);
		for(int i = 0; i < webcam.getWebcams().size(); i++)
		{
			this.textPane1.setText(this.textPane1.getText() + "\n" + (i + 1) + " - " + webcam.getWebcams().get(i));
		}
		this.textField1.setText(ip);
		this.setDefaultCloseOperation(StreamServer.EXIT_ON_CLOSE);
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
		size = webcam.getWebcams().size();
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
			worker.execute();
			
		}
		else if(!button1.isSelected())
		{
			button1.setName("Start Server!");
			stop();
			worker.cancel(true);
				
		}
		
	}
	SwingWorker worker = new SwingWorker()  // Atualizador em Tempo Real!
	{
		@Override
		protected Object doInBackground() throws Exception 
		{
			ServerSocket servidor = new ServerSocket(12345);
			   System.out.println("Porta 12345 aberta!");
			   while(true)
			   {
			    	 Socket cliente = servidor.accept();
			         System.out.println("Nova conex�o com o cliente " + cliente.getInetAddress().getHostAddress());
			         PrintStream ps = new PrintStream(cliente.getOutputStream());
			         clientes.add(ps);
			         TrataCliente tc = new TrataCliente(clientes, webcam);
			         new Thread(tc).start();
			         System.out.println("Thread Ligada!");
			   }
		}
		protected void done()
		{
			System.out.println("Saiu Server");
		}
	};
}
