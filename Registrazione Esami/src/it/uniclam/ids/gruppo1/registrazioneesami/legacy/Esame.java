package it.uniclam.ids.gruppo1.registrazioneesami.legacy;

import java.sql.Date;
import java.util.List;

public class Esame {
	private String id_esame;
	private String nome;
	private List<Date> data_appelli;

	public Esame() {
	}

	public Esame(String id_esame, String nome, List<Date> data_appelli) {
		this.id_esame = id_esame;
		this.nome = nome;
		this.data_appelli = data_appelli;
	}

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

	public List<Date> getData_appelli() {
		return data_appelli;
	}

	public void setData_appelli(List<Date> data_appelli) {
		this.data_appelli = data_appelli;
	}

}
