package it.uniclam.ids.gruppo1.registrazioneesami.dao;

import it.uniclam.ids.gruppo1.registrazioneesami.entity.EsameVerbalizzato;

public interface EsameVerbalizzatoDAO {
	public boolean verbalizzaEsame (EsameVerbalizzato e) throws DAOException;
}
