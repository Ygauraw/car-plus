package Store;
import java.sql.*;
import java.util.ArrayList;

import Entita.Localita;
import Entita.Modello_Macchina;
import Entita.Modello_Macchina;
import Utils.*;

public class StoreModello_Macchina{
	public static Modello_Macchina insertModello_Macchina(Modello_Macchina Value){
		String sql = "INSERT INTO modello_macchina(marca,modello,cilindrata,anno,alimentazione,emissioni_co2,num_posti) VALUES('" + Value.getMarca() + "','" + Value.getModello() + "', " + Value.getCilindrata() +","+ Value.getAnno() + ",'" + Value.getAlimentazione() + "'," + Value.getEmissioni_co2() + "," + Value.getNum_posti() + ")";
	    
		Query.doQuery(sql);
		return readModello_Macchina(Value.getId());
	}

	public static Modello_Macchina readModello_Macchina(int id){
		String sql = "SELECT * FROM modello_macchina WHERE id = " + id;
		ResultSet rs = Query.doQueryRS(sql);
		if(rs != null){
			try {
				while(rs.next()){  
					return new Modello_Macchina(rs.getInt("id"), rs.getString("marca"), rs.getString("modello"), rs.getInt("Cilindrata"), rs.getInt("anno"), rs.getString("alimentazione"), rs.getInt("emissioni_co2"), rs.getInt("num_posti"));
				}
			}catch (SQLException e){
			}
		}
		return null;
	}
	
	public static ArrayList<Modello_Macchina> readModello_Macchina_List(ArrayList<String> listaMacchine){
		String sql = "SELECT * FROM modello_macchina WHERE ";
		for (int i=0; i<listaMacchine.size() - 1; i++){
			sql += "id = '" + listaMacchine.get(i)+ "' OR ";
		}
		sql+= "id = '" + listaMacchine.get(listaMacchine.size() - 1)+ "'";
		ResultSet rs = Query.doQueryRS(sql);
		if(rs != null){
			try {
				ArrayList<Modello_Macchina> Macchine_List = new ArrayList<Modello_Macchina>();
				while(rs.next()){
					Macchine_List.add(new Modello_Macchina(rs.getInt("id"), rs.getString("marca"), rs.getString("modello"), rs.getInt("Cilindrata"), rs.getInt("anno"), rs.getString("alimentazione"), rs.getInt("emissioni_co2"), rs.getInt("num_posti")));
				}
				return Macchine_List;
			}catch (SQLException e){
			}
		}
		return null;
	}
	
	public static ArrayList<Modello_Macchina> getModello_Macchina() {
		String sql_query = "SELECT * FROM modello_macchina ORDER BY id";
		ResultSet rs = Query.doQueryRS(sql_query);
		if (rs != null) {
			try {
				ArrayList<Modello_Macchina> list = new ArrayList<Modello_Macchina>();
				while (rs.next()) {
					list.add(new Modello_Macchina (rs.getInt("id"), rs.getString("marca"), rs.getString("modello"), rs.getInt("Cilindrata"), rs.getInt("anno"), rs.getString("alimentazione"), rs.getInt("emissioni_co2"), rs.getInt("num_posti")));
				}
				return list;
			} catch (SQLException e) {
				
			}
		}
		
		return null;
	}
	
}