package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import metier.Statistique;

public class StatistiqueDAO extends DAO<Statistique> {
	private ResultSet rs;

	@Override
	public ArrayList<Statistique> lire() {
		return null;
	}

	@Override
	public Statistique creer(Statistique stat) {
		String requete = "INSERT INTO statistique (nbParticulesTrouvees,ratioSurfaceCouverte,"
				+ "moyenneAires,moyenneDiametresEquivalents,"
				+ "ecartTypeAires,ecartTypeDiametreEquivalent,idImage)"
				+ " VALUES ("+ stat.getNbParticulesTrouvees()+ "," + stat.getRatioSurfaceCouverte()+ ","
				+stat.getMoyenneAires()+ ","+stat.getMoyenneDiametresEquivalents()+ ","
				+stat.getEcartTypeAires()+ ","+stat.getEcartTypeDiametreEquivalent()+ ","
				+stat.getIdImage()+")";
		try {
			stmt.executeUpdate(requete,Statement.RETURN_GENERATED_KEYS);
			ResultSet cles = stmt.getGeneratedKeys();
			if (cles.next()) {
				stat.setIdStatistique(cles.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stat;
	}

	@Override
	public Statistique mettreAJour(Statistique obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimer(Statistique obj) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * lis les stats à partir d'un idImage
	 * @param idImage
	 * @return
	 */
	public Statistique lire(int idImage) {
		Statistique stat = new Statistique();
		String requete = "SELECT * FROM statistique WHERE idImage = " + idImage;
		try{
			rs = stmt.executeQuery(requete);
			if (rs.first()) {
				stat = new Statistique(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getInt(8));
			}
			rs.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return stat;
	}

}
