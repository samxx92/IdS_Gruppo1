package it.uniclam.ids.gruppo1.registrazioneesami.dao;

import it.uniclam.ids.gruppo1.registrazioneesami.entity.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DocenteAbilitatoDAOImpl implements DocenteAbilitatoDAO{
	
	private DocenteAbilitatoDAOImpl(){}
	
	private static DocenteAbilitatoDAO dao = null;
	
	public static DocenteAbilitatoDAO getInstance(){
		if (dao == null){
			dao = new DocenteAbilitatoDAOImpl();
		}
		return dao;
	}

	@Override
	public String searchDocenteAbilitato(String telefono, String password) throws DAOException {
		String abilitato = "false";
		int a = 0;
		try{
			Statement st = DAOSettings.getStatement();
			
			String sql = "select * from DocentiAbilitati where telefono='";
			sql += telefono + "' and password ='" + password +"'";
			
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				a++;
			}
				
			DAOSettings.closeStatement(st);
			
			} catch (SQLException sq){
				throw new DAOException("In getAllAmici(): " + sq.getMessage());
			}
		if (a==1){
			abilitato = "true";
		}
		return abilitato;	
		
	}
			
}
