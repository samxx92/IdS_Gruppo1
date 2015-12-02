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

	@Override
	public String recoveryPassword(String telefono) throws DAOException {
		String password = null;
		try {
			Statement st = DAOSettings.getStatement();

			String sql = "select password from docentiabilitati where telefono='" + telefono + "';";

			System.out.println(sql);

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				password = rs.getString("password");
			}

			System.out.println(password);

			DAOSettings.closeStatement(st);

		} catch (SQLException sq) {
			throw new DAOException("In recoveryPassword(): " + sq.getMessage());
		}

		return password;

	}

}
