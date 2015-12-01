package it.uniclam.ids.gruppo1.registrazioneesami.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EsamePrenotato extends Esame{
	private String matricola;
	private Date data_appello;
	private Date data_prenotazione;

	public EsamePrenotato(){}
	
	public EsamePrenotato(String id_esame, String matricola, String data_prenotazione, String data_appello){
		super(id_esame , null ,null);
		this.matricola = matricola;
		this.data_prenotazione = java.sql.Date.valueOf(data_prenotazione);
		this.data_appello = java.sql.Date.valueOf(data_appello);

	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public Date getData_appello() {
		return data_appello;
	}

	public void setData_appello(Date data_appello) {
		this.data_appello = data_appello;
	}

	public Date getData_prenotazione() {
		return data_prenotazione;
	}

	public void setData_prenotazione(Date data_prenotazione) {
		this.data_prenotazione = data_prenotazione;
	}
	


}
