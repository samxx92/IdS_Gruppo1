package it.uniclam.ids.gruppo1.registrazioneesami.entity;

import it.uniclam.ids.gruppo1.registrazioneesami.legacy.Docente;

public class DocenteAbilitato extends Docente {
	private String Password;

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
}
