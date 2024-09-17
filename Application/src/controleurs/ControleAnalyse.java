package controleurs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import dao.ParticuleDAO;
import dao.StatistiqueDAO;
import ihm.IHMStatistiques;
import metier.AnalyseImage;
import metier.EnsembleParticules;
import metier.Image;
import metier.Particule;
import metier.Statistique;

/**
 * Controleur permettant l'échange entre la vue IHMStatistiques et les différents models
 */
public class ControleAnalyse {
	private EnsembleParticules ensemble;
	private EnsembleParticules ensembleTemporaire;
	private IHMStatistiques ihm;

    /**
     * Initialise un ensemble vide
     */
    public ControleAnalyse() {
    	ensemble = new EnsembleParticules();
    	ensembleTemporaire = new EnsembleParticules();
    	ihm = new IHMStatistiques();
    }

    /**
     * Permet l'affichage des particules des images fournis en paramètres
     * @param images liste des images choisis pour afficher leurs particules
     */
    public void afficher(ArrayList<Image> images) {
    	AnalyseImage analyseure = new AnalyseImage();
        for (Image image : images) {
        	//check si l'image à des particules et donc des stats
        	Statistique stat = new Statistique();
        	ParticuleDAO DBpart = new ParticuleDAO();
        	StatistiqueDAO DBstat = new StatistiqueDAO();
        	ensembleTemporaire = DBpart.lire(image.getIdImage());
			if (ensembleTemporaire.estVide()) {
				//analyse les images et les ajoutes à la base de donnée
				try {
					analyseure.executerScripts(image.getNomImage());
					lecteurCSV();
					ensembleTemporaire = DBpart.creer(image.getIdImage(), ensembleTemporaire);
					//générer les stats de ensemble temporaire et les ajouter à la base
					stat = DBstat.creer(new Statistique(ensembleTemporaire));
					
				} catch (IOException e) {
					System.out.println("l'analyse de l'image: " + image + "a échoué");
					e.printStackTrace();
				}
			}else {
				stat = DBstat.lire(image.getIdImage());
			}
			ihm.alimenterTableau(stat, image);//
			//alimente l'ensemble général avec les particules de ensembleTemporaire
			ensemble.ajouterParticules(ensembleTemporaire);
		}
        if (ihm.histoDPresent()) ihm.alimenterHistoD(ensemble);
        if (ihm.histoSPresent()) ihm.alimenterHistoS(ensemble);
        ensemble.effacer();
    }
    
    /**
     * permet de lire le fichier CSV donné par le script et de mettre ses données dans des objets Particule qui seront ensuite stockés dans EnsembleParticules
     * @throws IOException 
     */
    public void lecteurCSV() throws IOException {
    	//on supprime les valeurs de ensemble temporaire
    	ensembleTemporaire.effacer();
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\scriptsImages\\res\\out_data.csv"));
		String line;
		//premiere ligne n'est pas à stocker
		br.readLine();
		//lecture des lignes
		while((line = br.readLine()) != null) {
			Particule part = new Particule(line.split(","));
			this.ensembleTemporaire.ajouterParticule(part);
		}
		br.close();
		try {
            Files.delete(Paths.get(System.getProperty("user.dir") + "\\scriptsImages\\res\\out_data.csv"));
        } catch (IOException e) {
            System.err.println("Une erreure est survenue en supprimant les fichiers: " + e.getMessage());
        }
    }

	public EnsembleParticules getEnsemble() {
		return ensemble;
	}

	public void setEnsemble(EnsembleParticules ensemble) {
		this.ensemble = ensemble;
	}

	public EnsembleParticules getEnsembleTemporaire() {
		return ensembleTemporaire;
	}

	public void setEnsembleTemporaire(EnsembleParticules ensembleTemporaire) {
		this.ensembleTemporaire = ensembleTemporaire;
	}

	public IHMStatistiques getIhm() {
		return ihm;
	}

	public void setIhm(IHMStatistiques ihm) {
		this.ihm = ihm;
	}
    
    

}