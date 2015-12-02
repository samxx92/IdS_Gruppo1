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
import it.uniclam.ids.gruppo1.registrazioneesami.entity.Esame;
import it.uniclam.ids.gruppo1.registrazioneesami.entity.EsamePrenotato;
import it.uniclam.ids.gruppo1.registrazioneesami.entity.EsameVerbalizzato;
import it.uniclam.ids.gruppo1.registrazioneesami.legacy.*;

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


		//		DatabaseDidatticaMock db_didattica = new DatabaseDidatticaMock();
		//		List<Commissione> commissioni = db_didattica.getAllCommissioni();
		//		List<Docente> docenti = db_didattica.getAllDocenti();
		//		List <Esame> esami = db_didattica.getAllEsami();
		//      List <EsamePrenotato> esami_prenotati = db_didattica.getAllExam();


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
				id_docente = DatabaseDidatticaMock.getId_docentefromtelefono(telefono);

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

				//System.out.println(telefono);

				String id_esame = in.readLine().replace("id_esame:", "").replace("\n", "");
				String id_studente = in.readLine().replace("id_studente:", "").replace("\n", "");
				String valutazione = in.readLine().replace("valutazione:", "").replace("\n", "");
				String data_appello = in.readLine().replace("data_appello:", "").replace("\n", "");

				String isincommissione = DatabaseDidatticaMock.isInCommissione(telefono, id_esame);			

				//				System.out.println(id_docente);
				//				System.out.println(isincommissione);

				response = "Ok\n";

				response += isincommissione + "\n";


				if (isincommissione.equalsIgnoreCase("true")){	

					EsameVerbalizzato e = new EsameVerbalizzato(id_esame, id_docente, id_studente, data_appello, valutazione);
					String verifica1 = id_esame + " " + id_studente + " " + data_appello;
					List<String> esami_docente = DatabaseDidatticaMock.getPrenotazioniEsamiDocente(telefono);
					String verifica2 = null;
					boolean trovato = false;
					for (int i = 0;i<esami_docente.size();i++){
						for (int k = 0; k<DatabaseDidatticaMock.esami_prenotati.size();k++){
							if (esami_docente.get(i).equals(DatabaseDidatticaMock.esami_prenotati.get(k).getId_esame())){
								verifica2 =DatabaseDidatticaMock.esami_prenotati.get(k).getId_esame() + " " +
										DatabaseDidatticaMock.esami_prenotati.get(k).getId_studente() + " " +
										DatabaseDidatticaMock.esami_prenotati.get(k).getData_appello();
								if (verifica1.equalsIgnoreCase(verifica2)){
									trovato = true;
								}
							}
						}
					}
					String response_temp = "false";
					if (trovato){
						EsameVerbalizzatoDAOImpl.getInstance().verbalizzaEsame(e);
						response_temp ="true\n";
						
					}
					response += response_temp + "\n";
					
					out.println(response);

				}


			}
			else if(command.equals(QUERY_VISUALIZZA_PRENOTAZIONI)){
				response="OK\n";
				List<String> esami_docente = DatabaseDidatticaMock.getPrenotazioniEsamiDocente(telefono);

				for (int i = 0;i<esami_docente.size();i++){
					for (int k = 0; k<DatabaseDidatticaMock.esami_prenotati.size();k++){
						if (esami_docente.get(i).equals(DatabaseDidatticaMock.esami_prenotati.get(k).getId_esame())){
							response +=" "+ DatabaseDidatticaMock.esami_prenotati.get(k).getId_esame() + " " +
									DatabaseDidatticaMock.esami_prenotati.get(k).getId_studente() + " " +
									DatabaseDidatticaMock.esami_prenotati.get(k).getData_appello() + " " +
									DatabaseDidatticaMock.esami_prenotati.get(k).getData_prenotazione()+"\n";
						}
					}
				}

				response +="\n";
				out.println(response);


			}
		}
	}
}
