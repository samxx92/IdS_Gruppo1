package it.uniclam.ids.gruppo1.registrazioneesami.entity;

import java.sql.Date;

import it.uniclam.ids.gruppo1.registrazioneesami.legacy.EsamePrenotato;

public class EsameVerbalizzato extends EsamePrenotato {
	private String id_docente;
	private Date data_verbalizzazione;
	private int valutazione;
	private String confermato;
	private String scaduto;

	public EsameVerbalizzato() {
	}

	public EsameVerbalizzato(String id_esame, String id_docente, String id_studente, String data_appello,
			String valutazione, String data_verbalizzazione, String scaduto, String confermato) {
		super(id_esame, id_studente, null, data_appello);
		this.id_docente = id_docente;
		this.valutazione = Integer.parseInt(valutazione);
		this.data_verbalizzazione = java.sql.Date.valueOf(data_verbalizzazione);
		if (confermato != null) {
			this.confermato = confermato;
		} else {
			this.confermato = "false";
		}
		if (scaduto != null) {
			this.scaduto = scaduto;
		} else {
			this.scaduto = "false";
		}
	}

	public Date getData_verbalizzazione() {
		return data_verbalizzazione;
	}

	public void setData_verbalizzazione(Date data_verbalizzazione) {
		this.data_verbalizzazione = data_verbalizzazione;
	}

	public int getValutazione() {
		return valutazione;
	}

	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}

	public String getConfermato() {
		return confermato;
	}

	public void setConfermato(String confermato) {
		this.confermato = confermato;
	}

	public String getId_docente() {
		return id_docente;
	}

	public void setId_docente(String id_docente) {
		this.id_docente = id_docente;
	}

	@Override
	public String getId_esame() {
		return super.getId_esame();
	}

	@Override
	public void setId_esame(String id_esame) {
		super.setId_esame(id_esame);
	}

	public String getScaduto() {
		return scaduto;
	}

	public void setScaduto(String scaduto) {
		this.scaduto = scaduto;
	}

}
