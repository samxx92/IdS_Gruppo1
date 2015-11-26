package it.uniclam.ids.gruppo1.registrazioneesami.entity;

import java.util.ArrayList;
import java.util.List;

public class Docente {
	private String id_docente;
	private String nome;
	private String cognome;
	private String telefono;
	private String mail;
	private List<String> id_commissione;
	private String password;
	
	public Docente(String id_docente, String nome, String cognome, String telefono,
			String mail, List<String> id_commissione, String password){
		this.id_docente = id_docente;
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.mail = mail;
		this.id_commissione = id_commissione;
		this.password = password;
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<String> getId_commissione() {
		return id_commissione;
	}
	
	public void setId_commissione(List<String> id_commissione) {
		this.id_commissione = id_commissione;
	}
	
	public String getId_commissione(int i) {
		return id_commissione.get(i);
	}
	
	public void setId_commissione(String id_commissione) {
		this.id_commissione.add(id_commissione);
	}
	
	public void deleteId_commissione(String id_commissione){
		this.id_commissione.remove(id_commissione);
	}
	
	
	public static void main(String[] args){
		List<String> id_commissione = new ArrayList<String>();
		id_commissione.add("id1");
		id_commissione.add("id2");
		Docente a = new Docente("01", "n1", "c1", "num1", "mail1", id_commissione, "pw1");
		System.out.print(a.getId_commissione());
		a.deleteId_commissione("id1");
		System.out.print(a.getId_commissione());
	}
	
	
	
}
