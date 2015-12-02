package it.uniclam.ids.gruppo1.registrazioneesami.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.TimeZone;

import it.uniclam.ids.gruppo1.registrazioneesami.entity.EsameVerbalizzato;

public class EsameVerbalizzatoDAOImpl implements EsameVerbalizzatoDAO {

	private EsameVerbalizzatoDAOImpl(){}

	private static EsameVerbalizzatoDAO dao = null;

	public static EsameVerbalizzatoDAO getInstance(){
		if (dao == null){
			dao = new EsameVerbalizzatoDAOImpl();
		}
		return dao;
	}

	@Override
	public void verbalizzaEsame(EsameVerbalizzato e) throws DAOException {
		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
		int currentDay = localCalendar.get(Calendar.DATE);
		int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
		int currentYear = localCalendar.get(Calendar.YEAR);
		String data = currentYear +"-"+currentMonth+"-"+currentDay;
		Date date = java.sql.Date.valueOf(data);
		System.out.println(data);
		try{
			Statement st = DAOSettings.getStatement();

			String sql = "insert into EsamiVerbalizzati (id_esame,id_docente,id_studente,voto,data_appello,data_verbalizzazione) values ('";
			sql += e.getId_esame() + "','"+ e.getId_docente() + "','" +
					e.getId_studente() + "','" + e.getValutazione() + "','" + e.getData_appello() + "','" 
					+ date +"');" ;

			System.out.println(sql);

			st.executeUpdate(sql);
			DAOSettings.closeStatement(st);

		} catch (SQLException sq){
			throw new DAOException("In verbalizzaEsame(): " + sq.getMessage());
		}

	}

}
