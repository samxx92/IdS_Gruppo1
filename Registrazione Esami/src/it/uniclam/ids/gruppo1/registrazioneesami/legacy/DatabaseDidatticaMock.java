package it.uniclam.ids.gruppo1.registrazioneesami.legacy;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import it.uniclam.ids.gruppo1.registrazioneesami.dao.DAOException;
import it.uniclam.ids.gruppo1.registrazioneesami.dao.DocenteAbilitatoDAOImpl;
import it.uniclam.ids.gruppo1.registrazioneesami.entity.Commissione;
import it.uniclam.ids.gruppo1.registrazioneesami.entity.Docente;
import it.uniclam.ids.gruppo1.registrazioneesami.entity.Esame;
import it.uniclam.ids.gruppo1.registrazioneesami.entity.EsamePrenotato;
import it.uniclam.ids.gruppo1.registrazioneesami.entity.EsameVerbalizzato;

public class DatabaseDidatticaMock {

	private static List<Commissione> commissioni;
	private static List<Docente> docenti;
	private static List<Esame> esami;
	private static List<EsamePrenotato> esami_prenotati;
	private static List<EsameVerbalizzato> esami_verbalizzati_s3;


	static{
		commissioni = new ArrayList<Commissione>();
		List<String> id_docenti = new ArrayList<String>();
		id_docenti.add("id1");
		id_docenti.add("id2");

		Commissione test1 = new Commissione("c1", "e1", id_docenti);

		List<String> id_docenti2 = new ArrayList<String>();
		id_docenti2.add("id2");
		id_docenti2.add("id3");

		Commissione test2 = new Commissione("c2", "e2", id_docenti2);

		List<String> id_docenti3 = new ArrayList<String>();
		id_docenti3.add("id3");

		Commissione test3 = new Commissione("c3", "e3", id_docenti3);

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

		Date e2_1 = java.sql.Date.valueOf("2016-01-10");
		Date e2_2 = java.sql.Date.valueOf("2016-02-15");
		data_appelli_e2.add(e2_1);
		data_appelli_e2.add(e2_2);

		Date e3_1 = java.sql.Date.valueOf("2016-01-18");
		Date e3_2 = java.sql.Date.valueOf("2016-02-29");
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
		EsamePrenotato ep2 = new EsamePrenotato("e2", "m1", "2015-12-01", "2016-02-10");
		esami_prenotati.add(ep1);
		esami_prenotati.add(ep2);
		
		
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

	public static void createListEsami_verbalizzati_s3(List<String> esami_verbalizzati_s3){
		EsameVerbalizzato ev = new EsameVerbalizzato();
		String [] temp=null;
		Date data_appello = null;
		Date data_verbalizzazione = null;
		for (int i = 0; i<esami_verbalizzati_s3.size(); i++){
			temp = esami_verbalizzati_s3.get(i).split(";");
			ev.setId_esame(temp[0]);
			ev.setId_docente(temp[1]);
			ev.setId_studente(temp[2]);
			ev.setValutazione(Integer.parseInt(temp[3]));
			data_appello = java.sql.Date.valueOf(temp[4]);
			ev.setData_appello(data_appello);
			data_verbalizzazione = java.sql.Date.valueOf(temp[5]);
			ev.setData_verbalizzazione(data_verbalizzazione);
			DatabaseDidatticaMock.esami_verbalizzati_s3.add(ev);
			ev=null;
		}
	}


	/**
	 * Questo metodo ricever... 
	 * @param telefono Parametro per la ricerca
	 * @return	
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



	public static String isInCommissione(String telefono, String id_esame) {
		String id_docente = getId_docentefromtelefono(telefono);
		List<String> docenti_commissione = new ArrayList<String>();
		String commissione = "false";
		for (int k = 0; k < commissioni.size(); k++) {
			if (commissioni.get(k).getId_esame().equals(id_esame)) {
				// id_commissione =
				// DatabaseDidatticaMock.commissioni.get(k).getId_commissione();
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
