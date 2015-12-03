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
	 * Il metodo serve a ... 
	 * 
	 * @param e
	 * @return 
	 * @throws DAOException Questa eccezione è generata quando ... 
	 */
	public boolean verbalizzaEsame(EsameVerbalizzato e) throws DAOException {
		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
		int currentDay = localCalendar.get(Calendar.DATE);
		int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
		int currentYear = localCalendar.get(Calendar.YEAR);
		String data = currentYear + "-" + currentMonth + "-" + currentDay;
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
				String sql = "insert into EsamiVerbalizzati (id_esame,id_docente,id_studente,voto,data_appello,data_verbalizzazione, id_verbalizzazione) values ('";
				sql += e.getId_esame() + "','" + e.getId_docente() + "','" + e.getId_studente() + "','"
						+ e.getValutazione() + "','" + e.getData_appello() + "','" + date + "','" + id_verbalizzazione
						+ "');";

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

	@Override
	public List<EsameVerbalizzato> getAllVerbalizzazioniGiornaliere(String id_docente) throws DAOException {
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

			ResultSet rs = st.executeQuery(sqlsearch);
			while (rs.next()) {
				EsameVerbalizzato temp = new EsameVerbalizzato(rs.getString("id_esame"), rs.getString("id_docente"),
						rs.getString("id_studente"), rs.getString("data_appello"), rs.getString("voto"));
				ev.add(temp);
			}

			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In getAllVerbalizzazioniGiornaliere(): " + sq.getMessage());
		}
		return ev;
	}

}
