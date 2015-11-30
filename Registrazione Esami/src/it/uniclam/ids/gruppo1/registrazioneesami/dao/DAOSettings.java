package it.uniclam.ids.gruppo1.registrazioneesami.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOSettings {
	public final static String DRIVERNAME = "com.mysql.jdbc.Driver";
	public final static String HOST = "localhost";
	public final static String USERNAME = "root";
	public final static String PWD = "root";
	public final static String SCHEMA = "cellex";

	static{
		try {
			Class.forName(DRIVERNAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static Statement getStatement() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://" + HOST  + "/" + SCHEMA, USERNAME, PWD).createStatement();
	}

	public static void closeStatement(Statement st) throws SQLException{
		st.getConnection().close();
		st.close();
	}


}
