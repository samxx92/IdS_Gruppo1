package it.uniclam.ids.gruppo1.registrazioneesami.entity;

import java.util.List;

public class Commissione {
	private String id_commissione;
	private String id_esame;
	private List<String> id_docenti;
	private String id_presidente;

	public String getId_presidente() {
		return id_presidente;
	}

	public void setId_presidente(String id_presidente) {
		this.id_presidente = id_presidente;
	}

	public Commissione() {
	}

	public Commissione(String id_commissione, String id_esame, List<String> id_docenti) {
		this.id_commissione = id_commissione;
		this.id_esame = id_esame;
		this.id_docenti = id_docenti;
	}

	public String getId_commissione() {
		return id_commissione;
	}

	public void setId_commissione(String id_commissione) {
		this.id_commissione = id_commissione;
	}

	public String getId_esame() {
		return id_esame;
	}

	public void setId_esame(String id_esame) {
		this.id_esame = id_esame;
	}

	public List<String> getId_docenti() {
		return id_docenti;
	}

	public void setId_docenti(List<String> id_docenti) {
		this.id_docenti = id_docenti;
	}

}
