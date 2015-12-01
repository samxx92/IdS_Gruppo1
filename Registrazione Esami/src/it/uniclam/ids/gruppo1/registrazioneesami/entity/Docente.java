package it.uniclam.ids.gruppo1.registrazioneesami.entity;

import java.util.ArrayList;
import java.util.List;

public class Docente {
	private String id_docente;
	private String nome;
	private String cognome;
	private String telefono;
	private String mail;

	public Docente() {}

	public Docente(String id_docente, String nome, String cognome, String telefono, String mail){
		this.id_docente = id_docente;
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.mail = mail;
	}


	public String getId_docente() {
		return id_docente;
	}

	public void setId_docente(String id_docente) {
		this.id_docente = id_docente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
