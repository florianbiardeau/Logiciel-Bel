package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import metier.Operateur;
import metier.Produit;

/**
 * Classe servant a effectuer des operations entre l'objet Produit et son equivalent, la table Produit, dans la base de donnees.
 */
public class ProduitDAO extends DAO<Produit>{

    /**
     * Methode de creation d'un produit dans la base de donnees a partir des attributs de l'objet Produit.
     * @param produit Instance de la classe Produit devant etre inseree dans la base de donnees.
     * @return Instance de la classe Produit ayant ete inseree dans la base de donnees.
     */
    public Produit creer(Produit produit) {
    	String rechercheProduit = "SELECT * FROM produit "
				+ "WHERE nomProduit = '"+ produit.getNomProduit()+ "'";
    	try {
    		ResultSet rs = stmt.executeQuery(rechercheProduit);
        	// si nomProduit n'est pas deja dans la bdd
			if (!rs.next()) {
				// on le cree
				String requete = "INSERT INTO produit (nomProduit) "
						+ "VALUES('" + produit.getNomProduit()+"')";
				
				try {
					stmt.executeUpdate(requete,Statement.RETURN_GENERATED_KEYS);
					ResultSet cles = stmt.getGeneratedKeys();
					if(cles.next()){
						int idProduit = cles.getInt(1);
						produit.setIdProduit(idProduit);
					}
				} catch (SQLException e) {
					System.err.println("Erreur requete SQL");
					e.printStackTrace();
				}
			} else {
				// on lui affecte son id situe dans la bdd
				produit.setIdProduit(rs.getInt("idProduit"));
			}
		} catch (SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();
		}
        return produit;
    }

    /**
     * Methode de lecture d'un produit dans la base de donnees a partir des attributs de l'objet Produit.
     * @param idProduit ID du produit devant etre lu dans la base de donnees.
     * @return Instance de la classe Produit contenant les donnees lues dans la base de donnees par rapport a l'ID.
     */
    public Produit lire(int idProduit) {
    	//creation de la requete SQL
    	String sql = "SELECT * FROM produit WHERE idProduit = " + idProduit;
    	
 		ResultSet rs = null;
 		try {
 			rs = stmt.executeQuery(sql);

 			if (rs.next()) {
 				//Creation de l'objet Produit
		        Produit produit = new Produit(rs.getString("nomProduit"));
		        produit.setIdProduit(idProduit);
		        return produit;
 			}
 		}
 		catch(SQLException e) {
 			System.err.println("Erreur requete SQL");
 			e.printStackTrace();
 		}
        return null;
    }

    /**
     * Methode de mise a jour d'un produit dans la base de donnees a partir des attributs de l'objet Produit.
     * @param produit Instance de la classe Produit devant etre mise a jour dans la base de donnees.
     * @return Instance de la classe Produit ayant ete mise a jour dans la base de donnees.
     */
    public Produit mettreAJour(Produit produit) {
    	//recuperation des attributs
    	int idProduit = produit.getIdProduit();
        String nomProduit = produit.getNomProduit();
         
        //creation de la requete SQL
        String sql = "UPDATE produit SET nomProduit = '" + nomProduit + "' WHERE idProduit = " + idProduit;
         
        //Execution de la requete
        try {
        	stmt.executeUpdate(sql);
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
        return produit;
    }

    /**
     * Methode de suppression d'un produit dans la base de donnees a partir des attributs de l'objet Produit.
     * @param produit Instance de la classe Produit devant etre supprimee dans la base de donnees.
     */
    public void supprimer(Produit produit) {
    	//recuperation des attributs
    	int idProduit = produit.getIdProduit();
    	
    	//creation de la requete SQL
    	String sql = "DELETE FROM produit WHERE idProduit =" + idProduit;
    	
    	//Execution de la requete
        try {
        	stmt.executeUpdate(sql);
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
    }

	@Override
	public ArrayList<Produit> lire() {
    	ArrayList<Produit> listeProduit = new ArrayList<>();
    	
   	 	//creation de la requete SQL
    	String sql = "SELECT * FROM produit";
   	  	
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				
				int idProduit = rs.getInt("idProduit");
		        String nomProduit = rs.getString("nomProduit");
		        
			    //Creation de l'objet operateur
				Produit produit = new Produit(nomProduit);
				produit.setIdProduit(idProduit);
				//ajout a la liste
				listeProduit.add(produit);
			}
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();
		}
    	
        return listeProduit;
	}

}