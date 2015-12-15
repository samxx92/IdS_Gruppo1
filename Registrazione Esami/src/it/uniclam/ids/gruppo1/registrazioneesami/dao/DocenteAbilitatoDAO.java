package it.uniclam.ids.gruppo1.registrazioneesami.dao;

import java.util.List;

public interface DocenteAbilitatoDAO {
	/**
	 * Il metodo serve a controllare se un docente sia abilitato
	 * o meno, grazie al parametro di ricerca 
	 * 
	 * @param telefono: parametro di ricerca
	 * @param password
	 * @return abilitato
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	public String searchDocenteAbilitato(String telefono, String password) throws DAOException;

	/**
	 * Il metodo fornisce la lista di tutti i docenti che 
	 * sono abiltati all'utilizzo del sistema CellEx 
	 * 
	 * @param NONE
	 * @return docenti_abilitati
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	public List<String> getAllDocentiAbilitati() throws DAOException;

	/**
	 * Il metodo serve a inserire nel database docentiabilitati
	 * un nuovo docente(che ha richiesto l'abilitazione al sistema)
	 * 
	 * @param telefono: parametro di ricerca
	 * @param password
	 * @return NONE
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	void addDocenteAbilitato(String telefono, String password) throws DAOException;

	/**
	 * Il metodo restituisce la password nel caso in cui un docente
	 * l'abbia persa tramite il paramtro di ricerca 
	 * 
	 * @param telefono: parametro di ricerca
	 * @return password
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	public String recoveryPassword(String telefono) throws DAOException;

}
