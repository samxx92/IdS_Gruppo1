package it.uniclam.ids.gruppo1.registrazioneesami.dao;

import java.util.List;

import it.uniclam.ids.gruppo1.registrazioneesami.entity.EsameVerbalizzato;

public interface EsameVerbalizzatoDAO {
	/**
	 * Il metodo serve a registrare un esame all'interno del DB EsameVerbalizzato
	 * 
	 * @param e
	 * @return verbalizzato
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	public boolean verbalizzaEsame(EsameVerbalizzato e, String data) throws DAOException;

	/**
	 * Il metodo serve a visualizzare le verbalizzazioni giornaliere di ogni docente presenti 
	 * all'interno del DB EsameVerbalizzato
	 * 
	 * @param id_docente
	 * @param confermato
	 * @return ev
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	public List<EsameVerbalizzato> getAllVerbalizzazioniGiornaliere(String id_docente, String confermato) throws DAOException;

	/**
	 * Il metodo serve a cancellare quegli esami che sono scaduti 
	 * all'interno del DB EsameVerbalizzato
	 * 
	 * @param NONE
	 * @return NONE
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	public void deleteEsamiScaduti () throws DAOException;

	/**
	 * Il metodo serve a visualizzare nella tabella dell'Admin
	 * la conferma di una esame da parte del presidente
	 * 
	 * @param esami_verbalizzati_s3: parametro di ricerca
	 * @param conferma: parametro di notifica
	 * @return NONE
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	public void setConfermaEsame (List<String> esami_verbalizzati_s3, String conferma) throws DAOException;

	/**
	 * Il metodo serve a far visualizzare quegli esami che sono 
	 * stati verbalizzati all'interno del DB EsameVerbalizzato
	 * 
	 * @param scaduto
	 * @param confermato
	 * @return ev
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	public List<EsameVerbalizzato> getEsamiVerbalizzati (String scaduto, String confermato) throws DAOException;

	/**
	 * Il metodo serve a controllare che l'esame sia stato confermato
	 * entro i giorni di scadenza prestabiliti
	 * 
	 * @param NONE
	 * @return NONE
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	public void setScaduto() throws DAOException;
}

