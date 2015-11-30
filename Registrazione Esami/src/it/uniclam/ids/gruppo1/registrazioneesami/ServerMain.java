package it.uniclam.ids.gruppo1.registrazioneesami;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

import it.uniclam.ids.gruppo1.registrazioneesami.dao.*;
import it.uniclam.ids.gruppo1.registrazioneesami.entity.Commissione;
import it.uniclam.ids.gruppo1.registrazioneesami.entity.Docente;
import it.uniclam.ids.gruppo1.registrazioneesami.entity.EsamePrenotato;

public class ServerMain {
	public static String OK = "Ok";
	
	public static String QUERY_LOGIN = "req_query_login";
	
	public static String QUERY_VERBALIZZA = "req_query_verbalizza";
	
	public static String QUERY_VISUALIZZA_PRENOTAZIONI = "req_query_visualizza_prenotazioni";
	
	public static String 	HOST = "localhost";
	public static int 		PORT = 5555;
	
	public static void main(String[] args) throws Exception{
		ServerSocket ss = new ServerSocket(PORT);
		String response = null;
		String telefono = null;
		String id_docente = null;
		
		Commissione ctest = new Commissione();
		List<Commissione> commissioni = ctest.getAllCommissioni();
		
		Docente dtest = new Docente();
		List<Docente> docenti = dtest.getAllDocenti();
		
		EsamePrenotato eptest = new EsamePrenotato();
		List <EsamePrenotato> esami_prenotati = eptest.getAllExam();
		
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
				
//				Commissione ctest = new Commissione();
//				List<Commissione> commissioni = ctest.getAllCommissioni();
//				
//				Docente dtest = new Docente();
//				List<Docente> docenti = dtest.getAllDocenti();

				
				String commissione = "false";
				
				
								
				System.out.println(telefono);
				
				String id_esame = in.readLine().replace("id_esame:", "").replace("\n", "");
				String id_studente = in.readLine().replace("id_studente:", "").replace("\n", "");
				String valutazione = in.readLine().replace("valutazione:", "").replace("\n", "");
				String data_appello = in.readLine().replace("data_appello:", "").replace("\n", "");
				
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
						commissione = "true";
					}
				}
				
				System.out.println(commissione);
				
				response = "Ok\n";
				
				response += commissione + "\n";
				
				
				out.println(response);				
				
																
			}
			else if(command.equals(QUERY_VISUALIZZA_PRENOTAZIONI)){
				List<String> commissioni_docente = new ArrayList<String>();
				for (int i=0;i<docenti.size();i++){
					if (docenti.get(i).getTelefono().equals(telefono)){
						id_docente = docenti.get(i).getId_docente();
					}
				}
				
				List <String> esami_docente = new ArrayList<String>();
				
				boolean trovato = false;
				for (int k=0;k<commissioni.size();k++){
					int j = 0;
					trovato = false;
					while (!trovato && j<commissioni.get(k).getId_docenti().size() ){
						if (commissioni.get(k).getId_docenti().get(j).equals(id_docente)){
							commissioni_docente.add(commissioni.get(k).getId_commissione());
							esami_docente.add(commissioni.get(k).getId_esame());
							trovato = true;
						}
						else {
							j++;
						}
					}
					
				}
				
				int m = 0;
				while (m<commissioni_docente.size()){
					System.out.println(commissioni_docente.get(m));
					System.out.println(esami_docente.get(m));
					m++;
				}
				response="OK\n";
				for (int i = 0;i<esami_docente.size();i++){
					for (int k = 0; k<esami_prenotati.size();k++){
						if (esami_docente.get(i).equals(esami_prenotati.get(k).getId_esame())){
							response +=" "+ esami_prenotati.get(k).getId_esame() + " " +
									esami_prenotati.get(k).getMatricola() + " " +
									esami_prenotati.get(k).getData_prenotazione() + " " +
									esami_prenotati.get(k).getData_prenotazione()+"\n";
						}
					}
				}
				
				response +="\n";
				out.println(response);
				
				
				
				
				
			}
		}
	}
}
