package it.uniclam.ids.gruppo1.registrazioneesami.legacy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import it.uniclam.ids.gruppo1.registrazioneesami.dao.DAOException;
import it.uniclam.ids.gruppo1.registrazioneesami.dao.DocenteAbilitatoDAOImpl;
import it.uniclam.ids.gruppo1.registrazioneesami.dao.EsameVerbalizzatoDAOImpl;
import it.uniclam.ids.gruppo1.registrazioneesami.entity.EsameVerbalizzato;

public class DatabaseDidatticaMock {

	private static List<Commissione> commissioni;
	private static List<Docente> docenti;
	private static List<Esame> esami;
	private static List<EsamePrenotato> esami_prenotati;
	private static List<EsameVerbalizzato> esami_verbalizzati_s3;

	public static List<EsameVerbalizzato> getEsami_verbalizzati_temp() {
		List<EsameVerbalizzato> ev = null;
		try {
			ev = EsameVerbalizzatoDAOImpl.getInstance().getAllVerbalizzazioniGiornaliere("", "");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ev;
	}

	static {
		commissioni = new ArrayList<Commissione>();
		List<String> id_docenti = new ArrayList<String>();
		id_docenti.add("id1");
		id_docenti.add("id2");

		Commissione test1 = new Commissione("c1", "e1", id_docenti, "p1");

		List<String> id_docenti2 = new ArrayList<String>();
		id_docenti2.add("id2");
		id_docenti2.add("id3");

		Commissione test2 = new Commissione("c2", "e2", id_docenti2, "p2");

		List<String> id_docenti3 = new ArrayList<String>();
		id_docenti3.add("id3");

		Commissione test3 = new Commissione("c3", "e3", id_docenti3, "p3");

		commissioni.add(test1);
		commissioni.add(test2);
		commissioni.add(test3);

		docenti = new ArrayList<Docente>();

		Docente d1 = new Docente("id1", null, null, "1", null);
		Docente d2 = new Docente("id2", null, null, "2", null);
		Docente d3 = new Docente("id3", null, null, "3", null);
		Docente d4 = new Docente("id4", null, null, "4", null);

		docenti.add(d1);
		docenti.add(d2);
		docenti.add(d3);
		docenti.add(d4);

		esami = new ArrayList<Esame>();

		List<Date> data_appelli_e1 = new ArrayList<Date>();
		List<Date> data_appelli_e2 = new ArrayList<Date>();
		List<Date> data_appelli_e3 = new ArrayList<Date>();

		Date e1_1 = java.sql.Date.valueOf("2016-01-10");
		Date e1_2 = java.sql.Date.valueOf("2016-02-10");
		data_appelli_e1.add(e1_1);
		data_appelli_e1.add(e1_2);

		Date e2_1 = java.sql.Date.valueOf("2016-01-15");
		Date e2_2 = java.sql.Date.valueOf("2016-02-15");
		data_appelli_e2.add(e2_1);
		data_appelli_e2.add(e2_2);

		Date e3_1 = java.sql.Date.valueOf("2016-01-20");
		Date e3_2 = java.sql.Date.valueOf("2016-02-20");
		data_appelli_e3.add(e3_1);
		data_appelli_e3.add(e3_2);

		Esame etest1 = new Esame("e1", null, data_appelli_e1);
		Esame etest2 = new Esame("e2", null, data_appelli_e2);
		Esame etest3 = new Esame("e3", null, data_appelli_e3);

		esami.add(etest1);
		esami.add(etest2);
		esami.add(etest3);

		esami_prenotati = new ArrayList<EsamePrenotato>();
		EsamePrenotato ep1 = new EsamePrenotato("e1", "m1", "2015-12-12", "2016-01-10");
		EsamePrenotato ep2 = new EsamePrenotato("e2", "m1", "2015-12-02", "2016-02-15");
		EsamePrenotato ep3 = new EsamePrenotato("e2", "m2", "2015-11-01", "2016-02-15");
		EsamePrenotato ep4 = new EsamePrenotato("e3", "m2", "2015-10-07", "2016-01-20");

		esami_prenotati.add(ep1);
		esami_prenotati.add(ep2);
		esami_prenotati.add(ep3);
		esami_prenotati.add(ep4);

		esami_verbalizzati_s3 = new ArrayList<EsameVerbalizzato>();
	}

	public static List<Commissione> getCommissioni() {
		return commissioni;
	}

	public static List<Docente> getDocenti() {
		return docenti;
	}

	public static List<Esame> getEsami() {
		return esami;
	}

	public static List<EsamePrenotato> getEsami_prenotati() {
		return esami_prenotati;
	}

	public static List<EsameVerbalizzato> getEsami_verbalizzati_s3() {
		return esami_verbalizzati_s3;
	}

	/**
	 * Questo metodo riceve in ingresso una lista di tutti gli esami che sono
	 * stati definitivamente salvati dall'amministratore, li inserisce in una
	 * lista e li salva in un file txt.
	 *
	 * @param list
	 *            esami_verbalizzati_s3: Parametro per la ricerca
	 * @return NONE
	 * @throws IOException
	 */
	public static void createListEsami_verbalizzati_s3(List<String> esami_verbalizzati_s3) throws IOException {
		List<EsameVerbalizzato> temp = new ArrayList<EsameVerbalizzato>();
		try {
			temp = EsameVerbalizzatoDAOImpl.getInstance().getEsameFromIdVerbalizzazione(esami_verbalizzati_s3);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter writer = new PrintWriter("esami_confermati.txt", "UTF-8");
		writer.println("Id Esame\tId Docente\tId Studente\tVoto\tData Appello");
		for (int i = 0; i < temp.size(); i++) {
			DatabaseDidatticaMock.esami_verbalizzati_s3.add(temp.get(i));
			writer.println(temp.get(i).getId_esame() + "\t" + "\t" + temp.get(i).getId_docente() + "\t" + "\t"
					+ temp.get(i).getId_studente() + "\t" + "\t" + temp.get(i).getValutazione() + "\t"
					+ temp.get(i).getData_appello() + "\n");
		}
		writer.close();
	}

	/**
	 * Questo metodo riceve il parametro telefono e lo converte in id_docente,
	 * grazie alla lista dei docenti
	 *
	 * @param telefono:
	 *            Parametro per la ricerca
	 * @return id_docente: ID del docente
	 */
	public static String getId_docentefromtelefono(String telefono) {
		String id_docente = null;
		for (int i = 0; i < docenti.size(); i++) {
			if (docenti.get(i).getTelefono().equals(telefono)) {
				id_docente = docenti.get(i).getId_docente();
			}
		}
		return id_docente;
	}

	/**
	 * Questo metodo verifica che il docente si parte effettiva della commisione
	 * di cui vuole verbalizzare l'esame
	 *
	 * @param telefono:
	 *            Parametro per la ricerca
	 * @param id_esame:
	 *            codice dell'esame
	 * @return commissione: codice della commissione
	 */
	public static String isInCommissione(String telefono, String id_esame) {
		String id_docente = getId_docentefromtelefono(telefono);
		List<String> docenti_commissione = new ArrayList<String>();
		String commissione = "false";
		for (int k = 0; k < commissioni.size(); k++) {
			if (commissioni.get(k).getId_esame().equals(id_esame)) {
				docenti_commissione = commissioni.get(k).getId_docenti();
			}
		}
		for (int j = 0; j < docenti_commissione.size(); j++) {
			if (docenti_commissione.get(j).equals(id_docente)) {
				commissione = "true";
			}
		}
		return commissione;

	}

	/**
	 * Questo metodo, tramite il parametro telefono, risale al docente e prende
	 * tutte le prenotazioni degli esami relative ad egli
	 *
	 * @param telefono:
	 *            parametro per la ricerca
	 * @return
	 */
	public static List<String> getPrenotazioniEsamiDocente(String telefono) {
		List<String> esami_docente = new ArrayList<String>();
		List<String> commissioni_docente = new ArrayList<String>();
		String id_docente = getId_docentefromtelefono(telefono);
		boolean trovato = false;
		for (int k = 0; k < commissioni.size(); k++) {
			int j = 0;
			trovato = false;
			while (!trovato && j < commissioni.get(k).getId_docenti().size()) {
				if (commissioni.get(k).getId_docenti().get(j).equals(id_docente)) {
					commissioni_docente.add(commissioni.get(k).getId_commissione());
					esami_docente.add(commissioni.get(k).getId_esame());
					trovato = true;
				} else {
					j++;
				}
			}

		}
		return esami_docente;
	}

	/**
	 * Questo metodo tramite i parametri di ricerca verifica se un esame del
	 * sistema sia stato prenotato o meno
	 *
	 * @param e:
	 *            parametro per la ricerca
	 * @param telefono:
	 *            parametro per la ricerca
	 * @return trovato: parmetro booleano, true se la ricerca è andata a buon
	 *         fine, false viceversa
	 */
	public static boolean isEsamePrenotato(EsameVerbalizzato e, String telefono) {
		String verifica1 = e.getId_esame() + " " + e.getId_studente() + " " + e.getData_appello();
		List<String> esami_docente = getPrenotazioniEsamiDocente(telefono);
		String verifica2 = null;
		boolean trovato = false;
		for (int i = 0; i < esami_docente.size(); i++) {
			for (int k = 0; k < esami_prenotati.size(); k++) {
				if (esami_docente.get(i).equals(esami_prenotati.get(k).getId_esame())) {
					verifica2 = esami_prenotati.get(k).getId_esame() + " " + esami_prenotati.get(k).getId_studente()
							+ " " + esami_prenotati.get(k).getData_appello();
					if (verifica1.equalsIgnoreCase(verifica2)) {
						trovato = true;
					}
				}
			}
		}
		return trovato;
	}

	/**
	 * Questo metodo riceve in ingresso la lista dei docenti abilitati e
	 * restituisce le informazioni che sono riferite a loro tramite il parametro
	 * TODO
	 *
	 * @param docenti_abilitati:
	 *            lista dei docenti presenti nel database
	 * @return info_docenti_abilitati
	 */
	public static List<Docente> getAllInfoDocente(List<String> docenti_abilitati) {
		List<Docente> info_docenti_abilitati = new ArrayList<Docente>();
		for (int i = 0; i < docenti_abilitati.size(); i++) {
			for (int k = 0; k < docenti.size(); k++) {
				if (docenti_abilitati.get(i).equalsIgnoreCase(docenti.get(k).getTelefono())) {
					Docente e = docenti.get(k);
					info_docenti_abilitati.add(e);
				}
			}
		}
		return info_docenti_abilitati;
	}

	/**
	 * Questo metodo verifica se un docente sia o meno già abilitato, sfrutando
	 * il parametro del telefono
	 *
	 * @param telefono_abilitazione:
	 *            parametro di ricerca
	 * @return trovato: parmetro booleano, true se la ricerca è andata a buon
	 *         fine, false viceversa
	 * @throws DAOException
	 */
	public static boolean isTelefonoInDocentiAndNotInDocentiAbilitati(String telefono_abilitazione)
			throws DAOException {
		boolean in_docenti_abilitati = false;
		boolean trovato = false;
		List<String> docenti_abilitati = DocenteAbilitatoDAOImpl.getInstance().getAllDocentiAbilitati();
		for (int i = 0; i < docenti_abilitati.size(); i++) {
			if (docenti_abilitati.get(i).equalsIgnoreCase(telefono_abilitazione)) {
				in_docenti_abilitati = true;
			}
		}

		if (!in_docenti_abilitati) {
			for (int i = 0; i < docenti.size(); i++) {
				if (docenti.get(i).getTelefono().equalsIgnoreCase(telefono_abilitazione)) {
					trovato = true;
				}
			}
		}
		return trovato;
	}

}
