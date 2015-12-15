package it.uniclam.ids.gruppo1.registrazioneesami.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DocenteAbilitatoDAOImpl implements DocenteAbilitatoDAO {

	private DocenteAbilitatoDAOImpl() {
	}

	private static DocenteAbilitatoDAO dao = null;

	public static DocenteAbilitatoDAO getInstance() {
		if (dao == null) {
			dao = new DocenteAbilitatoDAOImpl();
		}
		return dao;
	}


	/**
	 * Il metodo serve a controllare se un docente sia abilitato
	 * o meno, grazie al parametro di ricerca 
	 * 
	 * @param telefono: parametro di ricerca
	 * @param password
	 * @return abilitato
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	@Override
	public String searchDocenteAbilitato(String telefono, String password) throws DAOException {
		String abilitato = "false";
		int a = 0;
		try {
			Statement st = DAOSettings.getStatement();

			String sql = "select * from DocentiAbilitati where telefono='";
			sql += telefono + "' and password ='" + password + "'";

			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				a++;
			}

			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In searchDocenteAbilitato(): " + sq.getMessage());
		}
		if (a == 1) {
			abilitato = "true";
		}
		return abilitato;

	}

	/**
	 * Il metodo fornisce la lista di tutti i docenti che 
	 * sono abiltati all'utilizzo del sistema CellEx 
	 * 
	 * @param NONE
	 * @return docenti_abilitati
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	@Override
	public List<String> getAllDocentiAbilitati() throws DAOException {
		List<String> docenti_abilitati = new ArrayList<String>();
		try {
			Statement st = DAOSettings.getStatement();

			String sql = "select * from DocentiAbilitati";

			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				docenti_abilitati.add(rs.getString("telefono"));
			}

			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In searchDocenteAbilitato(): " + sq.getMessage());
		}
		return docenti_abilitati;
	}

	/**
	 * Il metodo serve a inserire nel database docentiabilitati
	 * un nuovo docente(che ha richiesto l'abilitazione al sistema)
	 * 
	 * @param telefono: parametro di ricerca
	 * @param password
	 * @return NONE
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	@Override
	public void addDocenteAbilitato(String telefono, String password) throws DAOException {
		try {
			Statement st = DAOSettings.getStatement();

			String sql = "insert into docentiabilitati values ('" + telefono + "','" + password + "');";

			st.executeUpdate(sql);

			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In addDocenteAbilitato(): " + sq.getMessage());
		}
	}

	/**
	 * Il metodo restituisce la password nel caso in cui un docente
	 * l'abbia persa tramite il paramtro di ricerca 
	 * 
	 * @param telefono: parametro di ricerca
	 * @return password
	 * @throws DAOException Questa eccezione è generata quando si verificano problemi nella query 
	 */
	@Override
	public String recoveryPassword(String telefono) throws DAOException {
		String password = null;
		try {
			Statement st = DAOSettings.getStatement();

			String sql = "select password from docentiabilitati where telefono='" + telefono + "';";

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				password = rs.getString("password");
			}

			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In recoveryPassword(): " + sq.getMessage());
		}

		return password;

	}

}
