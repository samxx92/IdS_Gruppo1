package it.uniclam.ids.gruppo1.registrazioneesami.entity;

import java.util.ArrayList;
import java.util.List;

public class EsamePrenotato {
	private String id_esame;
	private String matricola;
	private String data_prenotazione;
	private String data_appello;
	
	
	public String getId_esame() {
		return id_esame;
	}
	public void setId_esame(String id_esame) {
		this.id_esame = id_esame;
	}
	public String getMatricola() {
		return matricola;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	public String getData_prenotazione() {
		return data_prenotazione;
	}
	public void setData_prenotazione(String data_prenotazione) {
		this.data_prenotazione = data_prenotazione;
	}
	public String getData_appello() {
		return data_appello;
	}
	public void setData_appello(String data_appello) {
		this.data_appello = data_appello;
	}
	
	public List<EsamePrenotato> getAllExam(){
		List<EsamePrenotato> esami_prenotati = new ArrayList <EsamePrenotato>();
		EsamePrenotato test1 = new EsamePrenotato();
		test1.setId_esame("e1");
		EsamePrenotato test2 = new EsamePrenotato();
		test2.setId_esame("e2");
		esami_prenotati.add(test1);
		esami_prenotati.add(test2);
		
		return esami_prenotati;
	}
	

}
