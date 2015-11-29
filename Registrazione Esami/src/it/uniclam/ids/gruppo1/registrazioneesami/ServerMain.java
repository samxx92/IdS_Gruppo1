package it.uniclam.ids.gruppo1.registrazioneesami;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import it.uniclam.ids.gruppo1.registrazioneesami.dao.*;
import it.uniclam.ids.gruppo1.registrazioneesami.entity.Docente;

public class ServerMain {
	public static String OK = "Ok";
	
	public static String QUERY_LOGIN = "req_query_login";
	
	public static String 	HOST = "localhost";
	public static int 		PORT = 5555;
	
	public static void main(String[] args) throws Exception{
		ServerSocket ss = new ServerSocket(PORT);
		String response = null;
		while(true){
			response = "Error\n\n";
			System.out.println("Server in ascolto sulla porta 5555");
			Socket s = ss.accept();
	
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			
			String command = in.readLine();
			if (command.equals(QUERY_LOGIN)){
				String telefono = in.readLine().replace("telefono:", "").replace("\n", "");
				String password = in.readLine().replace("password:", "").replace("\n", "");

			try{		
				int row = DocenteAbilitatoDAOImpl.getInstance().searchDocenteAbilitato(telefono, password);
				
				System.out.println(row);
				String abilitato = null;
				if (row==0){
					abilitato = "false";
				}else if (row == 1){
					abilitato = "true";
				}
				
				System.out.println(abilitato);				
				
				
				response = "Ok\n";
				
				response += abilitato + "\n";
				
				
				out.println(response);
				
				
			} catch (DAOException daoe){
				System.out.println("Exception in connection");
				out.println(response);
			}
			//String response = "OK\nc1, n1, t1\nc2, n2, t2\n\n";
			
			s.close();
			}
		}
	}
}
