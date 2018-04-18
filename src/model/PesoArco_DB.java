package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import entity.Nodo;
import entity.PesoArco;

public class PesoArco_DB extends Model{

	public static final String TBL_NAME = "PesoArco";
	public static final String FIELD_ID = "id";
	public static final String FIELD_IDARCO = "idArco"; 
	public static final String FIELD_IDPESO = "idPeso"; 
	public static final String FIELD_VALORE = "valore"; 


	public PesoArco_DB() {

		super();  

	}

	public ArrayList<PesoArco> findPesiByIdArco(int Id){

		ArrayList<PesoArco> pesi = new ArrayList<PesoArco>();
		Peso_DB pdb = new Peso_DB();

		try {

			String query = "select * from "+TBL_NAME+" where "+FIELD_IDARCO+" = "+Id;
			System.out.println(query);

			OpenConnessione();
			ResultSet rs = this.selectQuery(query);

			while(rs.next()) {

				int id = rs.getInt(FIELD_ID);
				int idArco = rs.getInt(FIELD_IDARCO);
				int idPeso = rs.getInt(FIELD_IDPESO);
				int valore = rs.getInt(FIELD_VALORE);

				pesi.add( new PesoArco(id , idArco , pdb.findById(idPeso) , valore));

			}

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return pesi;
	}

	public void delete(int Id){

		try {

			String query = "delete from "+TBL_NAME+" where "+this.FIELD_ID+"="+Id;

			OpenConnessione();
			this.updateQuery(query);

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();	
		}

	}

	public void insertPesoArco(int IDArco, int valore, int IDPeso) {

		String query ="insert into "+TBL_NAME+" ( "+FIELD_IDARCO+","
				+FIELD_VALORE+","
				+FIELD_IDPESO+
				" )"+
				" values( "+IDArco+","
				+valore+","
				+IDPeso+
				" )";
		System.out.println(query);

		try {
			OpenConnessione();

			updateQuery(query);

			CloseConnessione();
			st.close(); 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public boolean controlloEsistenzaPeso(int idPeso, int Idarco) {

		boolean esito = false;

		try {

			String query = "select * from "+TBL_NAME+" where "+FIELD_IDARCO+" = "+Idarco+" and "+FIELD_IDPESO+"="+idPeso;
			System.out.println(query);

			OpenConnessione();
			ResultSet rs = this.selectQuery(query);

			if(rs.next())
				esito = true;

			CloseConnessione();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return esito;

	}

	public void method(){

		//VUOTO

	}
}