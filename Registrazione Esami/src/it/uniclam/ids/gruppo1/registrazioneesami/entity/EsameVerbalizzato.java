package it.uniclam.ids.gruppo1.registrazioneesami.entity;

import java.sql.Date;

public class EsameVerbalizzato extends Esame{
	private String id_docente;
	private Date data_verbalizzazione;
	private Date data_appello;
	private String id_studente;
	private int valutazione;
	private Boolean confermato;

	public EsameVerbalizzato(){}

	public EsameVerbalizzato(String id_esame, String id_docente, String id_studente,
			String data_appello, String valutazione){
		super(id_esame,null,null);
		this.setId_docente(id_docente);
		this.id_studente = id_studente;
		this.data_appello = java.sql.Date.valueOf(data_appello);
		this.valutazione = Integer.parseInt(valutazione);
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
	public Date getData_appello() {
		return data_appello;
	}
	public void setData_appello(Date data_appello) {
		this.data_appello = data_appello;
	}

	public String getId_docente() {
		return id_docente;
	}

	public void setId_docente(String id_docente) {
		this.id_docente = id_docente;
	}

}
