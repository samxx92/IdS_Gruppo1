package it.uniclam.ids.gruppo1.registrazioneesami.dao;

import java.util.List;

public interface DocenteAbilitatoDAO {
	public String searchDocenteAbilitato(String telefono, String password) throws DAOException;

	public List<String> getAllDocentiAbilitati() throws DAOException;

	void addDocenteAbilitato(String telefono, String password) throws DAOException;

	public String recoveryPassword(String telefono) throws DAOException;

}
