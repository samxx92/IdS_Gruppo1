package it.uniclam.ids.gruppo1.registrazioneesami.entity;

import java.sql.Date;

public class EsameVerbalizzato {
	private Date data_verbalizzazione;
	private String id_studente;
	private int valutazione;
	private Boolean confermato;
	
	
	public Date getData_verbalizzazione() {
		return data_verbalizzazione;
	}
	public void setData_verbalizzazione(Date data_verbalizzazione) {
		this.data_verbalizzazione = data_verbalizzazione;
	}
	public String getId_studente() {
		return id_studente;
	}
	public void setId_studente(String id_studente) {
		this.id_studente = id_studente;
	}
	public int getValutazione() {
		return valutazione;
	}
	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}
	public Boolean getConfermato() {
		return confermato;
	}
	public void setConfermato(Boolean confermato) {
		this.confermato = confermato;
	}
	
	

}
