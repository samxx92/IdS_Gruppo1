package it.uniclam.ids.gruppo1.registrazioneesami.entity;

import java.sql.Date;
import java.util.List;

public class Esame {
	private String id_esame;
	private String nome;
	private List<Date> data_appello;
	
	
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


}
