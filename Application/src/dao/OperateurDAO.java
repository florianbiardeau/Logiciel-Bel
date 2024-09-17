package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import metier.Image;
import metier.Operateur;

/**
 * Classe servant a effectuer des operations entre l'objet Operateur et son equivalent, la table Operateur, dans la base de donnees.
 */
public class OperateurDAO extends DAO<Operateur> {

    /**
     * Methode de creation d'un operateur dans la base de donnees a partir des attributs de l'objet Operateur.
     * @param operateur Instance de la classe Operateur devant etre inseree dans la base de donnees.
     * @return Instance de la classe Operateur ayant ete inseree dans la base de donnees.
     */
    public Operateur creer(Operateur operateur) {
    	String rechercheOperateur = "SELECT idOperateur FROM operateur "
				+ "WHERE nomOperateur = '"+ operateur.getNomOperateur()+ "'";
    	try {
    		ResultSet rs = stmt.executeQuery(rechercheOperateur);
        	// si nomOperateur n'est pas deja dans la bdd
			if (!rs.next()) {
				// on le cree
				String requete = "INSERT INTO operateur (nomOperateur) "
						+ "VALUES('" + operateur.getNomOperateur()+"')";
				
				try {
					stmt.executeUpdate(requete,Statement.RETURN_GENERATED_KEYS);
					ResultSet cles = stmt.getGeneratedKeys();
					if(cles.next()){
						int idOperateur = cles.getInt(1);
						operateur.setIdOperateur(idOperateur);
					}
				} catch (SQLException e) {
					System.err.println("Erreur requete SQL");
					e.printStackTrace();
				}
			} else {
				// on lui affecte son id situe dans la bdd
				operateur.setIdOperateur(rs.getInt("idOperateur"));
			}
		} catch (SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();
		}
        return operateur;
    }

    /**
     * Methode de lecture d'un operateur dans la base de donnees a partir des attributs de l'objet Operateur.
     * @param idOperateur ID de l'operateur devant etre lu dans la base de donnees.
     * @return Instance de la classe Operateur contenant les donnees lues dans la base de donnees par rapport a l'ID.
     */
    public Operateur lire(int idOperateur) {
    	//creation de la requete SQL
    	String sql = "SELECT * FROM operateur WHERE idOperateur = " + idOperateur;
    	
 		ResultSet rs = null;
 		try {
 			rs = stmt.executeQuery(sql);

 			if (rs.next()) { 		        
		        //Creation de l'objet Operateur
		        Operateur operateur = new Operateur(rs.getString("nomOperateur"));
		        operateur.setIdOperateur(idOperateur);
		        return operateur;
 			}
 		}
 		catch(SQLException e) {
 			System.err.println("Erreur requete SQL");
 			e.printStackTrace();
 		}
        return null;
    }

    /**
     * Methode de mise a jour d'un operateur dans la base de donnees a partir des attributs de l'objet Operateur.
     * @param operateur Instance de la classe Operateur devant etre mise a jour dans la base de donnees.
     * @return Instance de la classe Operateur ayant ete mise a jour dans la base de donnees.
     */
    public Operateur mettreAJour(Operateur operateur) {
    	
    	//recuperation des attributs
    	int idOperateur = operateur.getIdOperateur();
        String nomOperateur = operateur.getNomOperateur();
         
        //creation de la requete SQL
        String sql = "UPDATE operateur SET nomOperateur = '" + nomOperateur + "' WHERE idOperateur = " + idOperateur;
         
        //Execution de la requete
        try {
        	stmt.executeUpdate(sql);
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
        return operateur;
    }

    /**
     * Methode de suppression d'un operateur dans la base de donnees a partir des attributs de l'objet Operateur.
     * @param operateur Instance de la classe Operateur devant etre supprimee dans la base de donnees.
     */
    public void supprimer(Operateur operateur) {
    	//recuperation des attributs
    	int idOperateur = operateur.getIdOperateur();
    	
    	//creation de la requete SQL
    	String sql = "DELETE FROM operateur WHERE idOperateur = " + idOperateur;
    	
    	//Execution de la requete
        try {
        	stmt.executeUpdate(sql);
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
    }

	@Override
	public ArrayList<Operateur> lire() {
    	ArrayList<Operateur> listeOperateur = new ArrayList<>();
    	
   	 	//creation de la requete SQL
    	String sql = "SELECT * FROM operateur";
   	  	
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				
				int idOperateur = rs.getInt("idOperateur");
		        String nomOperateur = rs.getString("nomOperateur");
		        
			    //Creation de l'objet operateur
				Operateur operateur = new Operateur(nomOperateur);
				operateur.setIdOperateur(idOperateur);
				//ajout a la liste
				listeOperateur.add(operateur);
			}
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();
		}
    	
        return listeOperateur;
	}

}