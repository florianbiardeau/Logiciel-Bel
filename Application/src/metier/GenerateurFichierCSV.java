package metier;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.stage.FileChooser;

/**
 * La classe GenerateurFichierCSV est responsable de la génération des fichiers CSV à partir des données d'histogrammes
 */
public class GenerateurFichierCSV {
	private final String[] nomColonnesStatistiques = {"nom de l'image", "grossissement", "nombre de particules trouvees",
			"ratio de surface couverte", "moyenne des aires px",  "moyenne diametres equivalents px",
			"ecart-type des aires px", "ecart-type des diametres equivalents px", "moyenne des aires", 
			"moyenne diametres equivalents", "ecart-type des aires", "ecart-type des diametres equivalents"};
	
	/**
     * Constructeur du générateur de fichiers
     */
    public GenerateurFichierCSV() {
    }

    
    /**
	 * Methode permettant de choisir le dossier de destination des histogrammes ou des statistiques à exporter
	 * @return retourne le chemin du dossier de destination
	 */
    public File choisirDossierDeDestination() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un dossier de destination");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setInitialFileName("donnees");
        return fileChooser.showSaveDialog(null); 
    }

    /**
     * Methode permettant de creer un fichier csv qui represente un histogramme
     * @param intervalles parametre qui represente les intervalles d'un histogramme
     * @param pourcentParticule parametre qui represente les pourcentage de particule
     * @param nom parametre qui represente le nom du fichier CSV obtenu a la fin de la methode
     * @param colonne parametre qui represente le nom de la seconde colonne du fichier CSV
     */
    public void creerFichierHistoCSV(ArrayList<String> intervalles, ArrayList<Integer> pourcentParticule, String nom, String colonne) {
        String[] Intervalles = intervalles.toArray(new String[0]);
        int[] PourcentParticule = pourcentParticule.stream().mapToInt(Integer::intValue).toArray();

        File selectedDirectory = choisirDossierDeDestination();

        if (selectedDirectory != null) {
            String nomFichier = selectedDirectory.getAbsolutePath() + nom;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
                // En-têtes du fichier CSV
                writer.write("intervalles;" + colonne + "\n");

                // Écrire les données dans le fichier CSV
                for (int i = 0; i < Intervalles.length; i++) {
                    writer.write(Intervalles[i] + ";" + PourcentParticule[i] + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    
    
    /**
     * Methode permettant de creer un fichier csv qui represente les statistiques d'une image
     * @param nomImage parametre de type ArrayList<String> qui represente les noms des images selectionnees
     * @param grossissement parametre de type ArrayList<Double> qui represente le grossissement
     * @param nbParticuleTrouve parametre de type ArrayList<Integer> qui represente le nombre de particules
     * @param ratioSurfaceCouverte parametre de type ArrayList<Double> qui represente le ratio de la surface couverte
     * @param moyenneAiresPx parametre de type ArrayList<Double> qui represente la moyenne des aires en des particules en pixels
     * @param moyenneDiametresEquivalentsPx parametre de type ArrayList<Double> qui represente la moyenne des diametres equivalents des particules en pixels
     * @param ecartTypeAiresPx parametre de type ArrayList<Double> qui represente l'ecart type des aires des particules en pixels
     * @param ecartTypeDiametreEquivalentPx parametre de type ArrayList<Double> qui represente l'ecart type des diametres equivalents des particules en pixels
     * @param moyenneAires parametre de type ArrayList<Double> qui represente la moyenne des aires en des particules
     * @param moyenneDiametresEquivalents parametre de type ArrayList<Double> qui represente la moyenne des diametres equivalents des particules
     * @param ecartTypeAires parametre de type ArrayList<Double> qui represente l'ecart type des diametres equivalents des particules
     * @param ecartTypeDiametreEquivalent parametre de type ArrayList<Double> qui represente l'ecart type des diametres equivalents des particules
     */
    public void creerFichierStatistiquesCSV(ArrayList<String> nomImage,
    								ArrayList<Double> grossissement,
    								ArrayList<Integer> nbParticuleTrouve,
    								ArrayList<Double> ratioSurfaceCouverte,
    								ArrayList<Double> moyenneAiresPx,
    								ArrayList<Double> moyenneDiametresEquivalentsPx,
    								ArrayList<Double> ecartTypeAiresPx,
    								ArrayList<Double> ecartTypeDiametreEquivalentPx,
    								ArrayList<Double> moyenneAires,
    								ArrayList<Double> moyenneDiametresEquivalents,
    								ArrayList<Double> ecartTypeAires,
    								ArrayList<Double> ecartTypeDiametreEquivalent) {
    	String nom = "Images.csv";
    	String[] NomImage = nomImage.toArray(new String[0]);
    	double[] Grossissement = grossissement.stream().mapToDouble(Double::doubleValue).toArray();
    	int[] NbParticuleTrouve = nbParticuleTrouve.stream().mapToInt(Integer::intValue).toArray();
    	double[] RatioSurfaceCouverte = ratioSurfaceCouverte.stream().mapToDouble(Double::doubleValue).toArray();
    	double[] MoyenneAiresPx = moyenneAiresPx.stream().mapToDouble(Double::doubleValue).toArray();
    	double[] MoyenneDiametresEquivalentsPx = moyenneDiametresEquivalentsPx.stream().mapToDouble(Double::doubleValue).toArray();
    	double[] EcartTypeAiresPx = ecartTypeAiresPx.stream().mapToDouble(Double::doubleValue).toArray();
    	double[] EcartTypeDiametreEquivalentPx = ecartTypeDiametreEquivalentPx.stream().mapToDouble(Double::doubleValue).toArray();
    	double[] MoyenneAires = moyenneAires.stream().mapToDouble(Double::doubleValue).toArray();
    	double[] MoyenneDiametresEquivalents = moyenneDiametresEquivalents.stream().mapToDouble(Double::doubleValue).toArray();
    	double[] EcartTypeAires = ecartTypeAires.stream().mapToDouble(Double::doubleValue).toArray();
    	double[] EcartTypeDiametreEquivalent = ecartTypeDiametreEquivalent.stream().mapToDouble(Double::doubleValue).toArray();
    	
    	
    	File selectedDirectory = choisirDossierDeDestination();

        if (selectedDirectory != null) {
            String nomFichier = selectedDirectory.getAbsolutePath() + nom;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            	for(int i = 0; i < this.nomColonnesStatistiques.length; i++) {
            		writer.write(this.nomColonnesStatistiques[i] + ";");
            	}
            	writer.write("\n");
            	for (int i = 0; i < NomImage.length; i++) {
            		writer.write(NomImage[i] + ";" + Grossissement[i] + ";" + NbParticuleTrouve[i] 
                				+ ";" + RatioSurfaceCouverte[i] + ";" + MoyenneAiresPx[i] + ";" +
                				MoyenneDiametresEquivalentsPx[i] + ";" + EcartTypeAiresPx[i] + ";" +
                				EcartTypeDiametreEquivalentPx[i] + ";" + MoyenneAires[i] + ";" +
                				MoyenneDiametresEquivalents[i] + ";" + EcartTypeAires[i] +  
                				";" + EcartTypeDiametreEquivalent[i] + "\n");
            	}
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }
    }
}