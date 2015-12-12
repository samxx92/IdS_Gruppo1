package it.uniclam.ids.gruppo1.registrazioneesami.dao;

import java.util.List;

import it.uniclam.ids.gruppo1.registrazioneesami.entity.EsameVerbalizzato;

public interface EsameVerbalizzatoDAO {
	public boolean verbalizzaEsame(EsameVerbalizzato e, String data) throws DAOException;

	public List<EsameVerbalizzato> getAllVerbalizzazioniGiornaliere(String id_docente, String confermato) throws DAOException;

	public void deleteEsame (List<EsameVerbalizzato> esami_verbalizzati_s3) throws DAOException;

	public void setConfermaEsame (List<String> esami_verbalizzati_s3, String conferma) throws DAOException;

	public List<EsameVerbalizzato> getEsamiVerbalizzati (List<String> id_verbalizzazione, String conferma) throws DAOException;
}
