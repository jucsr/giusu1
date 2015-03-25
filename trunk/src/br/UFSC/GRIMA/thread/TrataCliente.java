package br.UFSC.GRIMA.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class TrataCliente implements Runnable {
	 
		private ArrayList<PrintStream> clientes;
		PrintStream saida;
		int webcans;
	 
	   public TrataCliente(ArrayList<PrintStream> clientes, int webcans) {
	     this.clientes = clientes;
	     this.webcans = webcans;
	   }
	 
	   public void run() {
		  int teclado = webcans;
		  for(int i = 0 ; i < clientes.size() ; i++)
			saida = new PrintStream(clientes.get(i));
			saida.println(teclado);
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