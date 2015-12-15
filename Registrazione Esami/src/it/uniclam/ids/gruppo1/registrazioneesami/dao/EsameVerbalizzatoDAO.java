package it.uniclam.ids.gruppo1.registrazioneesami.dao;

import java.util.List;

import it.uniclam.ids.gruppo1.registrazioneesami.entity.EsameVerbalizzato;

public interface EsameVerbalizzatoDAO {
	public boolean verbalizzaEsame(EsameVerbalizzato e, String data) throws DAOException;

	public List<EsameVerbalizzato> getAllVerbalizzazioniGiornaliere(String id_docente, String confermato) throws DAOException;

	public void deleteEsamiScaduti () throws DAOException;

	public void setConfermaEsame (List<String> esami_verbalizzati_s3, String conferma) throws DAOException;

	public List<EsameVerbalizzato> getEsamiVerbalizzati (String scaduto, String confermato) throws DAOException;

	public void setScaduto() throws DAOException;

	public List<EsameVerbalizzato> getEsameFromIdVerbalizzazione(List<String> esami_verbalizzati_s3) throws DAOException;
}

