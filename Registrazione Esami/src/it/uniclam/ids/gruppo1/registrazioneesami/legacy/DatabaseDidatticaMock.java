package it.uniclam.ids.gruppo1.registrazioneesami.legacy;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import it.uniclam.ids.gruppo1.registrazioneesami.entity.*;

public class DatabaseDidatticaMock {

	public static List<Commissione> commissioni = getAllCommissioni();
	public static List<Docente> docenti = getAllDocenti();
	public static List<Esame> esami = getAllEsami();
	public static List<EsamePrenotato> esami_prenotati = getAllEsamiPrenotati();

	public static List<Commissione> getAllCommissioni(){

		List<Commissione> commissioni = new ArrayList<Commissione>();
		List<String> id_docenti = new ArrayList<String>();
		id_docenti.add("id1");
		id_docenti.add("id2");

		Commissione test1 = new Commissione("c1","e1",id_docenti);


		List<String> id_docenti2 = new ArrayList<String>();
		id_docenti2.add("id2");
		id_docenti2.add("id3");

		Commissione test2 = new Commissione("c2","e2",id_docenti2);


		List<String> id_docenti3 = new ArrayList<String>();
		id_docenti3.add("id3");

		Commissione test3 = new Commissione("c3","e3",id_docenti3);

		commissioni.add(test1);
		commissioni.add(test2);
		commissioni.add(test3);

		return commissioni;

	}

	public static List<Docente> getAllDocenti() {

		List<Docente> docenti = new ArrayList<Docente>();

		Docente d1 = new Docente ("id1",null,null,"1",null);
		Docente d2 = new Docente ("id2",null,null,"2",null);
		Docente d3 = new Docente ("id3",null,null,"3",null);


		docenti.add(d1);
		docenti.add(d2);
		docenti.add(d3);

		return docenti;
	}

	public static List<Esame> getAllEsami() {
		List<Esame> esami = new ArrayList <Esame>();

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

		Esame test1 = new Esame("e1",null,data_appelli_e1);
		Esame test2 = new Esame("e2",null,data_appelli_e2);
		Esame test3= new Esame("e3",null,data_appelli_e3);

		esami.add(test1);
		esami.add(test2);
		esami.add(test3);

		return esami;
	}



	public static List<EsamePrenotato> getAllEsamiPrenotati(){
		List<EsamePrenotato> esami_prenotati = new ArrayList<EsamePrenotato>();
		EsamePrenotato ep1 = new EsamePrenotato("e1","m1","2015-12-12","2016-01-10");
		EsamePrenotato ep2 = new EsamePrenotato("e2","m1","2015-12-01","2016-02-10");
		esami_prenotati.add(ep1);
		esami_prenotati.add(ep2);

		return esami_prenotati;

	}


	public static String getId_docentefromtelefono (String telefono){
		String id_docente = null;
		for (int i=0;i<docenti.size();i++){
			if (docenti.get(i).getTelefono().equals(telefono)){
				id_docente = docenti.get(i).getId_docente();
			}
		}
		return id_docente;
	}

	public static String isInCommissione (String telefono, String id_esame){
		String id_docente = getId_docentefromtelefono(telefono);
		List <String> docenti_commissione = new ArrayList<String>();
		String commissione = "false";
		for (int k=0;k<DatabaseDidatticaMock.commissioni.size();k++){
			if (commissioni.get(k).getId_esame().equals(id_esame)){
				//id_commissione = DatabaseDidatticaMock.commissioni.get(k).getId_commissione();
				docenti_commissione = DatabaseDidatticaMock.commissioni.get(k).getId_docenti();
			}
		}
		for (int j=0;j<docenti_commissione.size();j++){
			if (docenti_commissione.get(j).equals(id_docente)){
				commissione = "true";
			}
		}
		return commissione;

	}

	public static List<String> getPrenotazioniEsamiDocente (String telefono){
		List <String> esami_docente = new ArrayList<String>();
		List <String> commissioni_docente = new ArrayList<String>();
		String id_docente = getId_docentefromtelefono(telefono);
		boolean trovato = false;
		for (int k=0;k<DatabaseDidatticaMock.commissioni.size();k++){
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

		for (int k = 0;k<commissioni_docente.size();k++){
			System.out.println(commissioni_docente.get(k));
			System.out.println(esami_docente.get(k));
		}
		return esami_docente;
	}



}
