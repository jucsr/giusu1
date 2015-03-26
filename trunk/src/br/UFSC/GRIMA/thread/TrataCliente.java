package br.UFSC.GRIMA.thread;

import java.io.PrintStream;
import java.util.ArrayList;

import com.github.sarxos.webcam.Webcam;

public class TrataCliente implements Runnable {
	 
		private ArrayList<PrintStream> clientes;
		PrintStream saida;
		Webcam webcans;
		ArrayList<String> listaPadrao = new ArrayList<String>();
 	 
	   public TrataCliente(ArrayList<PrintStream> clientes, Webcam webcans) {
	     this.clientes = clientes;
	     this.webcans = webcans;
	   }
	 
	   public void run() {
		  for(int j = 0 ;  j < webcans.getWebcams().size() ; j++)
		  {
			  listaPadrao.add(webcans.getWebcams().get(j).getName());
		  }
		  listaPadrao.add(webcans.getWebcams().size() + "");
		  for(int i = 0 ; i < clientes.size() ; i++)
			for(int  k = 0 ;  k < listaPadrao.size(); k++)
			{
				saida = new PrintStream(clientes.get(i));
				saida.println(listaPadrao.get(k));
			}
//			   while (teclado.hasNextLine())
//			   {
//				   saida.println(teclado.nextLine());
//			   }
			    saida.close();
//			    try {
//					cliente.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
		   }
		 }