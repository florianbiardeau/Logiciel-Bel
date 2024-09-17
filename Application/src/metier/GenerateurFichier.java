package metier;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import ihm.IHMExport;
import controleurs.ControleExport;

/**
 * La classe GenerateurFichier est responsable de la génération des fichiers PNG à partir des données d'histogrammes
 */
public class GenerateurFichier 
{
	@FXML 
	private Button boutonExport;
	private static ControleExport controleExport;
	
	
	/**
	 * Constructeur du générateur de fichiers
	 */
	public GenerateurFichier() {

	}
	
	
	/**
	 * Crée le fichier PNG à partir des données d'histogrammes
	 *
	 * @return retourne true si le fichier est bien exporter et false dans le cas contraire
	 */
	
	public boolean enregistrerHistogrammeEnPNG(BarChart<?, ?> histogramme, String cheminFichier) {
	
		try {
            WritableImage image = histogramme.snapshot(new SnapshotParameters(), null);
            File file = new File(cheminFichier);
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
	            return true;
   
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
		    
	    }



    
    
}