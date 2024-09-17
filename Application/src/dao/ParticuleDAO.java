package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import metier.EnsembleParticules;
import metier.Particule;

/**
 * Permet les échanges avec la table Particules de la base de donnée
 */
public class ParticuleDAO extends DAO<Particule>{
	private ResultSet rs;

    /**
     * Default constructor
     */
    public ParticuleDAO() {
    }

    /**
     * Récupère les Particules d'une image à partir d'un  id
     * @param idImage entier permettant l'identification de l'image
     * @return retourne un EnsembleParticules
     */
    public EnsembleParticules lire(int idImage) {
    	EnsembleParticules ens = new EnsembleParticules();
		String requete = "SELECT * FROM particule WHERE idImage = " + idImage;
		try{
			rs = stmt.executeQuery(requete);
			while(rs.next()) {
				Particule p = new Particule(rs.getInt(1),rs.getDouble(2),rs.getDouble(3),
						rs.getDouble(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7),
						rs.getDouble(8),rs.getDouble(9),rs.getDouble(10),rs.getDouble(11),
						rs.getDouble(12),rs.getInt(13));
				ens.ajouterParticule(p);
			}
			
			rs.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return ens;
    }

    /**
     * Met à jour une Particule
     * @param part instance de Particule qui contient les éléments à changer
     * @return 
     */
    public Particule mettreAJour(Particule part) {
		return part;
    }

    /**
     * Supprime la Particule correspondant à l'objet fournie
     * @param idParticule à supprimer
     */
    public void supprimer(int idParticule) {
    }
    
    /**
     * creer dans la base de données les Particules d'une image
     * @param ens
     * @return ensemble crééer
     */
    public EnsembleParticules creer(int idImage,EnsembleParticules ens) {
    	ArrayList<Particule> l = ens.getListeParticules();
    	String requete = "INSERT INTO particule (surfaceParticulePx, coCoinHautGaucheX,"
    			+ "coCoinHautGaucheY,coCoinBasDroitX,"
    			+ "coCoinBasDroitY,coCentreX,coCentreY,orientation,"
    			+ "longueurAxeMajeur,longueurAxeMineur,diametreEquivalent,idImage) VALUES ";
    	for (Particule p : l) {
			requete += "(" + p.getSurfaceParticulePx() + "," + p.getcoCoinHautGaucheX() + ","
			+ p.getcoCoinHautGaucheY()+ "," + p.getcoCoinBasDroitX()+ "," 
			+p.getcoCoinBasDroitY()+ ","+ p.getCoCentreX()+ ","
			+ p.getCoCentreY()+ ","+ p.getOrientation()+ ","+
			p.getLongueurAxeMajeur()+ "," + p.getLongueurAxeMineur()+ "," + p.getDiametreEquivalent()+ "," + idImage +"),";
		}
    	requete = requete.substring(0, requete.length() - 1);
		try {
			stmt.executeUpdate(requete,Statement.RETURN_GENERATED_KEYS);
			//Les cles auto-générées sont retournées sous forme de ResultSet
			ResultSet cles = stmt.getGeneratedKeys();
			cles.next();
			for (Particule p : l) {
				p.setIdImage(idImage);
				p.setIdParticule(cles.getInt(1));
				cles.next();
			}
			ens.setListeParticules(l);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ens;
    }

	@Override
	public Particule creer(Particule obj) {
		return null;
	}

	@Override
	public void supprimer(Particule obj) {		
	}

	@Override
	public ArrayList<Particule> lire() {
		return null;
	}

}