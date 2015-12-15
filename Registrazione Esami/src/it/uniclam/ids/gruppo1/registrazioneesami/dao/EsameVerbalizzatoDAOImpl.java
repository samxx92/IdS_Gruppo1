package it.uniclam.ids.gruppo1.registrazioneesami.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import it.uniclam.ids.gruppo1.registrazioneesami.entity.EsameVerbalizzato;

public class EsameVerbalizzatoDAOImpl implements EsameVerbalizzatoDAO {

	private static int GIORNI_DI_SCADENZA=-60;

	private EsameVerbalizzatoDAOImpl() {
	}

	private static EsameVerbalizzatoDAO dao = null;

	public static EsameVerbalizzatoDAO getInstance() {
		if (dao == null) {
			dao = new EsameVerbalizzatoDAOImpl();
		}
		return dao;
	}

	/**
	 * Il metodo serve a registrare un esame all'interno del DB EsameVerbalizzato
	 * 
	 * @param e
	 * @return verbalizzato
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	@Override
	public boolean verbalizzaEsame(EsameVerbalizzato e, String data) throws DAOException {
		Date date = java.sql.Date.valueOf(data);
		String id_verbalizzazione = e.getId_esame() + e.getId_studente();
		boolean verbalizzato = false;
		try {
			Statement st = DAOSettings.getStatement();

			String sqlsearch = "select * from esamiverbalizzati where id_verbalizzazione ='" + e.getId_esame()
			+ e.getId_studente() + "';";

			ResultSet rs = st.executeQuery(sqlsearch);
			int row = 0;
			while (rs.next()) {
				row++;
			}
			if (row == 0) {
				String sql = "insert into EsamiVerbalizzati (id_esame,id_docente,id_studente,voto,data_appello,data_verbalizzazione, id_verbalizzazione, scaduto, confermato) values ('";
				sql += e.getId_esame() + "','" + e.getId_docente() + "','" + e.getId_studente() + "','"
						+ e.getValutazione() + "','" + e.getData_appello() + "','" + date + "','" + id_verbalizzazione
						+ "','" + e.getScaduto() + "','" + e.getConfermato() +"')";

				st.executeUpdate(sql);
				verbalizzato = true;
			} else {
				verbalizzato = false;
			}
			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In verbalizzaEsame(): " + sq.getMessage());
		}
		return verbalizzato;

	}


	/**
	 * Il metodo serve a visualizzare le verbalizzazioni giornaliere di ogni docente presenti 
	 * all'interno del DB EsameVerbalizzato
	 * 
	 * @param id_docente
	 * @param confermato
	 * @return ev
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	@Override
	public List<EsameVerbalizzato> getAllVerbalizzazioniGiornaliere(String id_docente, String confermato) throws DAOException {
		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
		int currentDay = localCalendar.get(Calendar.DATE);
		int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
		int currentYear = localCalendar.get(Calendar.YEAR);
		String data = currentYear + "-" + currentMonth + "-" + currentDay;
		Date date = java.sql.Date.valueOf(data);
		List<EsameVerbalizzato> ev = new ArrayList<EsameVerbalizzato>();
		try {
			Statement st = DAOSettings.getStatement();

			String sqlsearch = "select * from esamiverbalizzati where data_verbalizzazione ='" + date + "'";
			if (!id_docente.equalsIgnoreCase("")){
				sqlsearch += " and id_docente ='" + id_docente + "'";
			}
			if (!confermato.equalsIgnoreCase("")){
				sqlsearch += " and confermato ='" + confermato + "'";
			}

			ResultSet rs = st.executeQuery(sqlsearch);
			while (rs.next()) {
				EsameVerbalizzato temp = new EsameVerbalizzato(rs.getString("id_esame"), rs.getString("id_docente"),
						rs.getString("id_studente"), rs.getString("data_appello"), rs.getString("voto"), rs.getString("data_verbalizzazione"),
						rs.getString("scaduto"), rs.getString("confermato"));
				ev.add(temp);
			}

			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In getAllVerbalizzazioniGiornaliere(): " + sq.getMessage());
		}
		return ev;
	}

	/**
	 * Il metodo serve a cancellare quegli esami che sono scaduti 
	 * all'interno del DB EsameVerbalizzato
	 * 
	 * @param NONE
	 * @return NONE
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	@Override
	public void deleteEsamiScaduti () throws DAOException{
		try {
			Statement st = DAOSettings.getStatement();

			String sql = "delete from EsamiVerbalizzati where scaduto='true'";
			st.executeUpdate(sql);
			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In getAllVerbalizzazioniGiornaliere(): " + sq.getMessage());
		}
	}

	/**
	 * Il metodo serve a visualizzare nella tabella dell'Admin
	 * la conferma di una esame da parte del presidente
	 * 
	 * @param esami_verbalizzati_s3: parametro di ricerca
	 * @param conferma: parametro di notifica
	 * @return NONE
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	@Override
	public void setConfermaEsame(List<String> esami_verbalizzati_s3, String conferma) throws DAOException {
		String temp;
		try {
			Statement st = DAOSettings.getStatement();
			for (int i = 0; i<esami_verbalizzati_s3.size();i++){
				String sql = "update esamiverbalizzati set confermato='"+conferma+"' where id_verbalizzazione"
						+ "='"+ esami_verbalizzati_s3.get(i)+"'";
				if (conferma.equalsIgnoreCase("admin")){
					temp =" and confermato='false'";
					sql+=temp;
				}
				else if (conferma.equalsIgnoreCase("true")){
					temp =" and confermato='presidente'";
					sql+=temp;
				}
				st.executeUpdate(sql);
			}
			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In getAllVerbalizzazioniGiornaliere(): " + sq.getMessage());
		}
	}

	/**
	 * Il metodo serve a far visualizzare quegli esami che sono 
	 * stati verbalizzati all'interno del DB EsameVerbalizzato
	 * 
	 * @param scaduto
	 * @param confermato
	 * @return ev
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	@Override
	public List<EsameVerbalizzato> getEsamiVerbalizzati (String scaduto, String confermato) throws DAOException{
		List<EsameVerbalizzato> ev = new ArrayList<EsameVerbalizzato>();
		try {
			Statement st = DAOSettings.getStatement();

			String sqlsearch = "select * from esamiverbalizzati";
			if (!scaduto.equalsIgnoreCase("") &&confermato.equalsIgnoreCase("")){
				sqlsearch+=" where scaduto='" + scaduto + "'";
			}
			if (!scaduto.equalsIgnoreCase("") && !confermato.equalsIgnoreCase("")){
				sqlsearch+=" where scaduto='" + scaduto + "' and confermato='" + confermato + "'";
			}
			if (scaduto.equalsIgnoreCase("") && !confermato.equalsIgnoreCase("")){
				sqlsearch+=" where confermato='" + confermato + "'";
			}
			ResultSet rs = st.executeQuery(sqlsearch);
			while (rs.next()) {
				EsameVerbalizzato temp = new EsameVerbalizzato(rs.getString("id_esame"), rs.getString("id_docente"),
						rs.getString("id_studente"), rs.getString("data_appello"), rs.getString("voto"), rs.getString("data_verbalizzazione"),
						rs.getString("scaduto"), rs.getString("confermato"));
				ev.add(temp);
			}

			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In getAllVerbalizzazioniGiornaliere(): " + sq.getMessage());
		}
		return ev;		
	}

	/**
	 * Il metodo serve a controllare che l'esame sia stato confermato
	 * entro i giorni di scadenza prestabiliti
	 * 
	 * @param NONE
	 * @return NONE
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	@Override
	public void setScaduto() throws DAOException {
		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
		localCalendar.add(Calendar.DATE, GIORNI_DI_SCADENZA);
		int currentDay = localCalendar.get(Calendar.DATE);
		int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
		int currentYear = localCalendar.get(Calendar.YEAR);
		String data = currentYear + "-" + currentMonth + "-" + currentDay;
		Date date = java.sql.Date.valueOf(data);
		try {
			Statement st = DAOSettings.getStatement();
			String sqlsearch = "update esamiverbalizzati set scaduto='true' where data_verbalizzazione<'"+
					date + "' and confermato not like 'presidente'";
			st.executeUpdate(sqlsearch);
			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In getAllVerbalizzazioniGiornaliere(): " + sq.getMessage());
		}

	}
	
	/**
	 * Il metodo serve ad ottenere una lista di esami
	 * partendo dall'id di verbalizzazione, accessibile tramite il parametro
	 * 
	 * @param esami_verbalizzati_s3
	 * @return ev
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	@Override
	public List<EsameVerbalizzato> getEsameFromIdVerbalizzazione(List<String> esami_verbalizzati_s3) throws DAOException {
		List<EsameVerbalizzato> ev = new ArrayList<EsameVerbalizzato>();
		try {
			Statement st = DAOSettings.getStatement();
			if(esami_verbalizzati_s3.size()>0){
				String sqlsearch = "select * from esamiverbalizzati where id_verbalizzazione ='" + esami_verbalizzati_s3.get(0) + "'"; 
				if (esami_verbalizzati_s3.size()>1){
					for (int i = 1;i<esami_verbalizzati_s3.size();i++){
						sqlsearch += " or id_verbalizzazione ='" + esami_verbalizzati_s3.get(i)+"'";
					}
				}
				ResultSet rs = st.executeQuery(sqlsearch);
				while (rs.next()) {
					EsameVerbalizzato temp = new EsameVerbalizzato(rs.getString("id_esame"), rs.getString("id_docente"),
							rs.getString("id_studente"), rs.getString("data_appello"), rs.getString("voto"), rs.getString("data_verbalizzazione"),
							rs.getString("scaduto"), rs.getString("confermato"));
					ev.add(temp);
				}
			}

			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In getAllVerbalizzazioniGiornaliere(): " + sq.getMessage());
		}
		return ev;
	}

}
