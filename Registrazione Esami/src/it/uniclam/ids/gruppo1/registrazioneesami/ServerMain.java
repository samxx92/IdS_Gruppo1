package it.uniclam.ids.gruppo1.registrazioneesami;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import it.uniclam.ids.gruppo1.registrazioneesami.dao.DAOException;
import it.uniclam.ids.gruppo1.registrazioneesami.dao.DocenteAbilitatoDAOImpl;
import it.uniclam.ids.gruppo1.registrazioneesami.dao.EsameVerbalizzatoDAOImpl;
import it.uniclam.ids.gruppo1.registrazioneesami.entity.EsameVerbalizzato;
import it.uniclam.ids.gruppo1.registrazioneesami.legacy.DatabaseDidatticaMock;
import it.uniclam.ids.gruppo1.registrazioneesami.legacy.Docente;
import it.uniclam.ids.gruppo1.registrazioneesami.legacy.EsamePrenotato;

public class ServerMain {
	public static String OK = "Ok";

	public static String QUERY_LOGIN = "req_query_login";

	public static String QUERY_VERBALIZZA = "req_query_verbalizza";

	public static String QUERY_VISUALIZZA_PRENOTAZIONI = "req_query_visualizza_prenotazioni";

	public static String QUERY_VISUALIZZA_DOCENTI_ABILITATI = "req_query_visualizza_docenti_abilitati";

	public static String QUERY_ABILITA_DOCENTE = "req_query_abilita_docente";

	public static String QUERY_RECUPERA_PASSWORD = "req_query_recupera_password";

	public static String QUERY_VISUALIZZA_VERBALIZZAZIONI = "req_query_visualizza_verbalizzazioni_giornaliere";

	public static String QUERY_VISUALIZZA_VERBALIZZAZIONI_GIORNALIERE_DOCENTE = "req_query_visualizza_verbalizazioni_giornaliere_docente";

	public static String QUERY_VISUALIZZA_ESAMI_PRESIDENTE = "req_query_visualizza_esami_presidente";

	public static String QUERY_CONFERMA_ESAMI = "req_query_conferma_esami";

	public static String QUERY_CANCELLA_ESAMI_SCADUTI = "req_query_cancella_esami_scaduti";

	public static String HOST = "localhost";

	public static int PORT = 5555;

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(PORT);
		String response = null;
		String telefono = null;
		String id_docente = null;

		while (true) {
			response = "Error\n\n";
			// System.out.println("Server in ascolto sulla porta 5555");
			Socket s = ss.accept();

			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);

			String command = in.readLine();
			if (command.equals(QUERY_LOGIN)) {
				telefono = in.readLine().replace("telefono:", "").replace("\n", "");
				String password = in.readLine().replace("password:", "").replace("\n", "");
				id_docente = DatabaseDidatticaMock.getId_docentefromtelefono(telefono);

				try {
					String abilitato = DocenteAbilitatoDAOImpl.getInstance().searchDocenteAbilitato(telefono, password);

					response = abilitato + "\n";

					out.println(response);

				} catch (DAOException daoe) {
					System.out.println("Exception in connection");
					out.println(response);
				}

				s.close();
			} else if (command.equals(QUERY_VERBALIZZA)) {

				String id_esame = in.readLine().replace("id_esame:", "").replace("\n", "");
				String id_studente = in.readLine().replace("id_studente:", "").replace("\n", "");
				String valutazione = in.readLine().replace("valutazione:", "").replace("\n", "");
				String data_appello = in.readLine().replace("data_appello:", "").replace("\n", "");

				String isincommissione = DatabaseDidatticaMock.isInCommissione(telefono, id_esame);
				response = isincommissione + "\n";

				if (isincommissione.equalsIgnoreCase("true")) {
					Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
					int currentDay = localCalendar.get(Calendar.DATE);
					int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
					int currentYear = localCalendar.get(Calendar.YEAR);
					String data = currentYear + "-" + currentMonth + "-" + currentDay;

					EsameVerbalizzato e = new EsameVerbalizzato(id_esame, id_docente, id_studente, data_appello,
							valutazione, data, null, null);
					boolean trovato = DatabaseDidatticaMock.isEsamePrenotato(e, telefono);
					String response_temp = "false";
					boolean verbalizzato = false;
					if (trovato) {
						response_temp = "true\n";
						verbalizzato = EsameVerbalizzatoDAOImpl.getInstance().verbalizzaEsame(e, data);
						if (verbalizzato) {
							response_temp += "true\n";
						}
					}
					response += response_temp + "\n";

				}
				out.println(response);
			} else if (command.equals(QUERY_VISUALIZZA_PRENOTAZIONI)) {
				List<String> esami_docente = DatabaseDidatticaMock.getPrenotazioniEsamiDocente(telefono);
				List<EsamePrenotato> esami_prenotati = DatabaseDidatticaMock.getEsami_prenotati();
				response = "";
				for (int i = 0; i < esami_docente.size(); i++) {
					for (int k = 0; k < esami_prenotati.size(); k++) {
						if (esami_docente.get(i).equals(esami_prenotati.get(k).getId_esame())) {
							response += esami_prenotati.get(k).getId_esame() + ";"
									+ esami_prenotati.get(k).getId_studente() + ";"
									+ esami_prenotati.get(k).getData_appello() + ";"
									+ esami_prenotati.get(k).getData_prenotazione() + "\n";
						}
					}
				}

				response += "\n";
				out.println(response);

			} else if (command.equals(QUERY_VISUALIZZA_DOCENTI_ABILITATI)) {
				response = "";
				List<String> docenti_abilitati = DocenteAbilitatoDAOImpl.getInstance().getAllDocentiAbilitati();
				List<Docente> info_docenti_abilitati = DatabaseDidatticaMock.getAllInfoDocente(docenti_abilitati);
				if (info_docenti_abilitati.size() > 0) {
					for (int k = 0; k < info_docenti_abilitati.size(); k++) {
						response += info_docenti_abilitati.get(k).getId_docente() + " "
								+ info_docenti_abilitati.get(k).getTelefono() + "\n";
					}
				}
				response += "\n";
				out.println(response);

			} else if (command.equals(QUERY_ABILITA_DOCENTE)) {
				String telefono_abilitazione = in.readLine().replace("telefono:", "").replace("\n", "");
				String password = in.readLine().replace("password:", "").replace("\n", "");
				boolean trovato = false;
				List<Docente> docenti = DatabaseDidatticaMock.getDocenti();
				trovato = DatabaseDidatticaMock.isTelefonoInDocentiAndNotInDocentiAbilitati(telefono_abilitazione);
				if (trovato) {
					DocenteAbilitatoDAOImpl.getInstance().addDocenteAbilitato(telefono_abilitazione, password);
					Docente da = new Docente();
					for (int i = 0; i < docenti.size(); i++) {
						if (docenti.get(i).getTelefono().equalsIgnoreCase(telefono_abilitazione)) {
							da = docenti.get(i);
						}
					}

					response = da.getId_docente() + " " + da.getTelefono() + "\n";

				} else {
					response = "gia_abilitato\n";
				}

				response += "\n";
				out.println(response);

			} else if (command.equals(QUERY_RECUPERA_PASSWORD)) {
				String password;
				String telefono_abilitazione = in.readLine().replace("\n", "");
				password = DocenteAbilitatoDAOImpl.getInstance().recoveryPassword(telefono_abilitazione);
				response = password + "\n";
				response += "\n";
				out.println(response);

			} else if (command.equals(QUERY_VISUALIZZA_VERBALIZZAZIONI_GIORNALIERE_DOCENTE)) {
				response = "";
				String empty = "";
				List<EsameVerbalizzato> ev = new ArrayList<EsameVerbalizzato>();
				ev = EsameVerbalizzatoDAOImpl.getInstance().getAllVerbalizzazioniGiornaliere(id_docente, empty);
				for (int i = 0; i < ev.size(); i++) {
					response += ev.get(i).getId_esame() + ";" + ev.get(i).getId_studente() + ";"
							+ ev.get(i).getValutazione() + ";" + ev.get(i).getData_appello() + ";\n";
				}

				response += "\n";
				out.println(response);
			} else if (command.equals(QUERY_VISUALIZZA_VERBALIZZAZIONI)) {
				response = "";
				EsameVerbalizzatoDAOImpl.getInstance().setScaduto();
				List<EsameVerbalizzato> ev = EsameVerbalizzatoDAOImpl.getInstance().getEsamiVerbalizzati("", "");
				for (int i = 0; i < ev.size(); i++) {
					if (!ev.get(i).getConfermato().equalsIgnoreCase("true")) {
						response += ev.get(i).getId_esame() + ";" + ev.get(i).getId_docente() + ";"
								+ ev.get(i).getId_studente() + ";" + ev.get(i).getValutazione() + ";"
								+ ev.get(i).getData_appello() + ";" + ev.get(i).getData_verbalizzazione() + ";"
								+ ev.get(i).getConfermato() + ";" + ev.get(i).getScaduto() + "\n";
					}
				}

				response += "\n";
				out.println(response);
			} else if (command.equals(QUERY_VISUALIZZA_ESAMI_PRESIDENTE)) {
				EsameVerbalizzatoDAOImpl.getInstance().setScaduto();
				String line = in.readLine();
				String codiceEsame = null;
				for (int i = 0; i < DatabaseDidatticaMock.getCommissioni().size(); i++) {
					if (DatabaseDidatticaMock.getCommissioni().get(i).getId_presidente().equalsIgnoreCase(line)) {
						codiceEsame = DatabaseDidatticaMock.getCommissioni().get(i).getId_esame();
					}
				}
				response = "";
				List<EsameVerbalizzato> esami_verbalizzati_s3 = EsameVerbalizzatoDAOImpl.getInstance()
						.getEsamiVerbalizzati("false", "admin");
				if (esami_verbalizzati_s3.size() > 0) {
					for (int k = 0; k < esami_verbalizzati_s3.size(); k++) {
						if (esami_verbalizzati_s3.get(k).getId_esame().equalsIgnoreCase(codiceEsame)) {
							response += esami_verbalizzati_s3.get(k).getId_esame() + ";"
									+ esami_verbalizzati_s3.get(k).getId_docente() + ";"
									+ esami_verbalizzati_s3.get(k).getId_studente() + ";"
									+ esami_verbalizzati_s3.get(k).getValutazione() + ";"
									+ esami_verbalizzati_s3.get(k).getData_appello() + ";"
									+ esami_verbalizzati_s3.get(k).getData_verbalizzazione() + "\n";
						}
					}
				}
				response += "\n";
				out.println(response);

			} else if (command.equals(QUERY_CONFERMA_ESAMI)) {
				String conferma = in.readLine();
				response = "";
				List<String> esami_verbalizzati_s3 = new ArrayList<String>();
				String line = in.readLine();
				while (!line.isEmpty()) {
					if (conferma.equalsIgnoreCase("admin")) {
						if (line.equalsIgnoreCase("true")) {
							line = in.readLine();
							response += "l'esame " + line + " � scaduto e non pu� essere confermato!\n";
						} else if (line.equalsIgnoreCase("false")) {
							line = in.readLine();
							esami_verbalizzati_s3.add(line);
							response += "l'esame " + line + " � stato confermato!\n";
						}
						line = in.readLine();
					} else {

						esami_verbalizzati_s3.add(line);
						line = in.readLine();
						response += "OK\n";
					}
				}
				if (esami_verbalizzati_s3.size() > 0) {
					EsameVerbalizzatoDAOImpl.getInstance().setConfermaEsame(esami_verbalizzati_s3, conferma);
				}
				if (conferma.equalsIgnoreCase("true")) {
					DatabaseDidatticaMock.createListEsami_verbalizzati_s3(esami_verbalizzati_s3);
				}
				out.println(response);

			} else if (command.equals(QUERY_CANCELLA_ESAMI_SCADUTI)) {
				response = "OK\n";
				EsameVerbalizzatoDAOImpl.getInstance().deleteEsamiScaduti();
				out.println(response);
			}

		}
	}
}
