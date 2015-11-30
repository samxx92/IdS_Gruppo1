package it.uniclam.ids.gruppo1.registrazioneesami.entity;

import java.util.ArrayList;
import java.util.List;

public class Docente {
	private String id_docente;
	private String nome;
	private String cognome;
	private String telefono;
	private String mail;

	public Docente() {
		// TODO Auto-generated constructor stub
	}

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

	public List<Docente> getAllDocenti(){
		List<Docente> docenti = new ArrayList<Docente>();

		Docente d1 = new Docente ();
		Docente d2 = new Docente ();
		Docente d3 = new Docente ();

		d1.setId_docente("id1");
		d2.setId_docente("id2");
		d3.setId_docente("id3");
		d1.setTelefono("1");
		d2.setTelefono("2");
		d3.setTelefono("3");

		docenti.add(d1);
		docenti.add(d2);
		docenti.add(d3);

		return docenti;
	}

}
