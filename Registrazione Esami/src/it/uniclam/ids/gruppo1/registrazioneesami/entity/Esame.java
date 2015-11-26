package it.uniclam.ids.gruppo1.registrazioneesami.entity;

import java.sql.Date;
import java.util.List;

public class Esame {
	private String id_esame;
	private String nome;
	private List<Date> data_appello;
	private Date data_verbalizzazione;
	private String id_studente;
	private int valutazione;
	private Boolean confermato;
	
	
	public String getId_esame() {
		return id_esame;
	}
	public void setId_esame(String id_esame) {
		this.id_esame = id_esame;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Date> getData_appello() {
		return data_appello;
	}
	public void setData_appello(List<Date> data_appello) {
		this.data_appello = data_appello;
	}
	
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
