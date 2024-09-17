package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import metier.Image;

/**
 * Classe servant a effectuer des operations entre l'objet Image et son equivalent, la table Image, dans la base de donnees.
 */
public class ImageDAO extends DAO<Image>{
    /**
     * Default constructor
     */
    public ImageDAO() {
    }

    /**
     * Methode de creation d'une image dans la base de donnees a partir des attributs de l'objet Image.
     * @param image Instance de la classe Image devant etre inseree dans la base de donnees.
     * @return Instance de la classe Image ayant ete inseree dans la base de donnees.
     */
    @Override
    public Image creer(Image image) {
    	
    	//recuperation des attributs
        String nomImage = image.getNomImage();
        double largeurPx = image.getLargeurPx();
        double hauteurPx = image.getHauteurPx();
        double grossissement = image.getGrossissement();
        double largeurReelle = image.getLargeurReelle();
        int idOperateur = image.getIdOperateur();
        int idProduit = image.getIdProduit();
                
        //creation de la requete SQL
        String sql = "INSERT INTO image (nomImage, largeurPx, hauteurPx, grossissement, largeurReelle, idOperateur, idProduit) "
        		+ "VALUES ('"+ nomImage +"'," + largeurPx+", " + hauteurPx +", " + grossissement + "," + largeurReelle +", "+idOperateur + ", " + idProduit + ");";
        
        // Execution de la requete
 		try {
 			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
 			ResultSet res = stmt.getGeneratedKeys();
 			
 			if(res.next()) {
 				//ajout de l'id a  image
 				int idImage = res.getInt(1);
 	 			image.setIdImage(idImage);
 			}
 			return image;
 		}
 		catch(SQLException e) {
 			System.err.println("Erreur requete SQL");
 			e.printStackTrace();
 		}
        return null;
    }

    /**
     * Methode de lecture d'une image dans la base de donnees a partir des attributs de l'objet Image.
     * @param idImage ID de l'image devant etre lue dans la base de donnees.
     * @return Instance de la classe Image contenant les donnees lu dans la base de donnees par rapport a l'ID.
     */
    public Image lire(int idImage) {
    	
    	 //creation de la requete SQL
    	String sql = "SELECT * FROM image WHERE idImage = " + idImage;
    	
 		ResultSet rs = null;
 		try {
 			rs = stmt.executeQuery(sql);

 			while(rs.next()) {
 				
		        String nomImage = rs.getString("nomImage");
		        double largeurPx = rs.getInt("largeurPx");
		        double hauteurPx = rs.getInt("hauteurPx");
		        double grossissement = rs.getInt("grossissement");
		        double largeurReelle = rs.getDouble("largeurReelle");
		        int idOperateur = rs.getInt("idOperateur");
		        int idProduit = rs.getInt("idProduit");
 		        
 		     //Creation de l'objet Image
 		      Image newImage = new Image(nomImage,largeurPx, hauteurPx, grossissement, largeurReelle, idOperateur, idProduit);
 		      newImage.setIdImage(idImage);
			return newImage;
 			}
 		}
 		catch(SQLException e) {
 			System.err.println("Erreur requete SQL");
 			e.printStackTrace();
 		}
        return null;
    }

    /**
     * Methode de mise a jour d'une image dans la base de donnees a partir des attributs de l'objet Image.
     * @param image Instance de la classe Image devant etre mise a jour dans la base de donnees.
     * @return Instance de la classe Image ayant ete mise a jour dans la base de donnees.
     */
    @Override
    public Image mettreAJour(Image image) {
    	
    	//recuperation des attributs
    	int idImage = image.getIdImage();
        String nomImage = image.getNomImage();
        double largeurPx = image.getLargeurPx();
        double hauteurPx = image.getHauteurPx();
        double grossissement = image.getGrossissement();
        double largeurReelle = image.getLargeurReelle();
        int idOperateur = image.getIdOperateur();
        int idProduit = image.getIdProduit();
        
         
        //creation de la requete SQL
        String sql = "UPDATE image SET nomFichier = '" + nomImage+ "',";
        sql += "largeurPx = " + largeurPx  + ",";
        sql += "hauteurPx = " +hauteurPx +",";
        sql += "grossissement = "+ grossissement + ",";
        sql += "largeurReelle = "+ largeurReelle + ",";
        sql +=  "idOperateur = " + idOperateur + ",";
        sql += "idProduit = " + idProduit + " WHERE idImage = " + idImage;
         
        //Execution de la requete
        try {
        	stmt.executeUpdate(sql);
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
        return image;
    }

    /**
     * Methode de suppression d'une image dans la base de donnees a partir des attributs de l'objet Image.
     * @param image Instance de la classe Image devant etre supprimee dans la base de donnees.
     */
    @Override
    public void supprimer(Image image) {
    	//recuperation des attributs
    	int idImage = image.getIdImage();
    	
    	//creation de la requete SQL
    	String sql = "DELETE FROM image WHERE idImage =" + idImage;
    	
    	//Execution de la requete
        try {
        	stmt.executeUpdate(sql);
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
    }
    
    /**
     *  Recuperer les images dans la base de donnée en fonction de leur nom
     * @param nomImage
     * @return Une liste contenant des objets image qui sont retournées d'apres la recherche. 
     */
    public ArrayList<Image> lire(String recherche) {
    	
    	//Liste<Image>
    	ArrayList<Image> listeImage = new ArrayList<>();
    	
   	 	//creation de la requete SQL
    	String sql = "SELECT * FROM image WHERE nomImage LIKE  '%"+ recherche +"%'";
   	  	
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				
				int idImage = rs.getInt("idImage");
		        String nomImage = rs.getString("nomImage");
		        int largeurPx = rs.getInt("largeurPx");
		        int hauteurPx = rs.getInt("hauteurPx");
		        int grossissement = rs.getInt("grossissement");
		        double largeurReelle = rs.getDouble("largeurReelle");
		        int idOperateur = rs.getInt("idOperateur");
		        int idProduit = rs.getInt("idProduit");
		        		        
		     //Creation de l'objet image
			Image newImage = new Image(nomImage, largeurPx, hauteurPx, grossissement, largeurReelle, idOperateur, idProduit);
			newImage.setIdImage(idImage);
			//ajout a la liste
			listeImage.add(newImage);
			}
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();
		}
    	
        return listeImage;
    }

	@Override
	public ArrayList<Image> lire() {
    	ArrayList<Image> listeImage = new ArrayList<>();
    	
   	 	//creation de la requete SQL
    	String sql = "SELECT * FROM image";
   	  	
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				
				int idImage = rs.getInt("idImage");
		        String nomImage = rs.getString("nomImage");
		        int largeurPx = rs.getInt("largeurPx");
		        int hauteurPx = rs.getInt("hauteurPx");
		        int grossissement = rs.getInt("grossissement");
		        double largeurReelle = rs.getDouble("largeurReelle");
		        int idOperateur = rs.getInt("idOperateur");
		        int idProduit = rs.getInt("idProduit");
		        		 
			     //Creation de l'objet image
				Image newImage = new Image(nomImage,largeurPx, hauteurPx, grossissement, largeurReelle, idOperateur, idProduit);
				newImage.setIdImage(idImage);
				//ajout a la liste
				listeImage.add(newImage);
			}
		}
		catch(SQLException e) {
			System.err.println("Erreur requete SQL");
			e.printStackTrace();
		}
    	
        return listeImage;
	}

}