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
	 * @return 
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
	@Override
	public void deleteEsame (List<EsameVerbalizzato> esami_verbalizzati_s3) throws DAOException{
		try {
			Statement st = DAOSettings.getStatement();

			String sql = "delete * from EsamiVerbalizzati;";
			st.executeUpdate(sql);
			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In getAllVerbalizzazioniGiornaliere(): " + sq.getMessage());
		}
	}

	@Override
	public void setConfermaEsame(List<String> esami_verbalizzati_s3, String conferma) throws DAOException {
		try {
			Statement st = DAOSettings.getStatement();
			for (int i = 0; i<esami_verbalizzati_s3.size();i++){
				String sql = "update esamiverbalizzati set confermato='"+conferma+"' where id_verbalizzazione"
						+ "='"+ esami_verbalizzati_s3.get(i)+"';";
				st.executeUpdate(sql);
			}
			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In getAllVerbalizzazioniGiornaliere(): " + sq.getMessage());
		}
	}

	@Override
	public List<EsameVerbalizzato> getEsamiVerbalizzati (List<String> id_verbalizzazione, String conferma) throws DAOException{
		List<EsameVerbalizzato> ev = new ArrayList<EsameVerbalizzato>();
		try {
			Statement st = DAOSettings.getStatement();

			String sqlsearch = "select * from esamiverbalizzati where id_verbalizzazione =''";
			for (int i = 0 ;i<id_verbalizzazione.size();i++){
				sqlsearch += "or id_verbalizzazione ='" + id_verbalizzazione.get(i) + "'";
			}
			ResultSet rs = st.executeQuery(sqlsearch);
			while (rs.next()) {
				EsameVerbalizzato temp = new EsameVerbalizzato(rs.getString("id_esame"), rs.getString("id_docente"),
						rs.getString("id_studente"), rs.getString("data_appello"), rs.getString("voto"), rs.getString("data_verbalizzazione"),
						rs.getString("scaduto"), conferma);
				ev.add(temp);
			}

			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In getAllVerbalizzazioniGiornaliere(): " + sq.getMessage());
		}
		return ev;		
	}

}
