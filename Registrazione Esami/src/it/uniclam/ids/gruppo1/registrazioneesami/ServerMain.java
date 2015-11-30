package it.uniclam.ids.gruppo1.registrazioneesami;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import it.uniclam.ids.gruppo1.registrazioneesami.dao.*;
import it.uniclam.ids.gruppo1.registrazioneesami.entity.Commissione;
import it.uniclam.ids.gruppo1.registrazioneesami.entity.Docente;

public class ServerMain {
	public static String OK = "Ok";
	
	public static String QUERY_LOGIN = "req_query_login";
	
	public static String QUERY_VERBALIZZA = "req_query_verbalizza";
	
	public static String 	HOST = "localhost";
	public static int 		PORT = 5555;
	
	public static void main(String[] args) throws Exception{
		ServerSocket ss = new ServerSocket(PORT);
		String response = null;
		String telefono = null;
		while(true){
			response = "Error\n\n";
			System.out.println("Server in ascolto sulla porta 5555");
			Socket s = ss.accept();
	
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			
			String command = in.readLine();
			if (command.equals(QUERY_LOGIN)){
				telefono = in.readLine().replace("telefono:", "").replace("\n", "");
				String password = in.readLine().replace("password:", "").replace("\n", "");

			try{		
				String abilitato = DocenteAbilitatoDAOImpl.getInstance().searchDocenteAbilitato(telefono, password);
				
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
			
			else if (command.equals(QUERY_VERBALIZZA)){
				
				List<Commissione> commissioni = new ArrayList<Commissione>();
				
				Commissione test1 = new Commissione();
				test1.setId_commissione("c1");
				
				List<String> id_docenti = new ArrayList<String>();
								
				id_docenti.add("id1");
				id_docenti.add("id2");
				
				test1.setId_docenti(id_docenti);
				test1.setId_esame("e1");
				
				Commissione test2 = new Commissione();
				test2.setId_commissione("c1");
				
				List<String> id_docenti2 = new ArrayList<String>();
				id_docenti2.add("id3");
				
				test2.setId_docenti(id_docenti2);
				test2.setId_esame("e2");
				
				commissioni.add(test1);
				commissioni.add(test2);
				
				
				List<Docente> docenti = new ArrayList<Docente>();
				
				Docente d1 = new Docente ();
				Docente d2 = new Docente ();
				Docente d3 = new Docente ();
				
				d1.setId_docente("id1");
				d2.setId_docente("id2");
				d3.setId_docente("id3");
				d1.setTelefono("1");
				d2.setTelefono("2");
				d3.setTelefono("3");
				
				docenti.add(d1);
				docenti.add(d2);
				docenti.add(d3);
				
				boolean commissione = false;
				
				
								
				System.out.println(telefono);
				
				String id_esame = in.readLine().replace("id_esame:", "").replace("\n", "");
				String id_studente = in.readLine().replace("id_studente:", "").replace("\n", "");
				String valutazione = in.readLine().replace("valutazione:", "").replace("\n", "");
				String data_appello = in.readLine().replace("data_appello:", "").replace("\n", "");
				
				String id_docente = null;
				
				for (int i=0;i<docenti.size();i++){
					if (docenti.get(i).getTelefono().equals(telefono)){
						id_docente = docenti.get(i).getId_docente();
					}
				}
				
				System.out.println(id_docente);
				
				String id_commissione = null;
				
				List<String> docenti_commissione = new ArrayList<String>();
				
				for (int k=0;k<commissioni.size();k++){
					if (commissioni.get(k).getId_esame().equals(id_esame)){
						id_commissione = commissioni.get(k).getId_commissione();
						docenti_commissione = commissioni.get(k).getId_docenti();
					}
				}
				
				
				for (int j=0;j<docenti_commissione.size();j++){
					if (docenti_commissione.get(j).equals(id_docente)){
						commissione = true;
					}
				}
				
				System.out.println(commissione);
				
				
																
			}
		}
	}
}
