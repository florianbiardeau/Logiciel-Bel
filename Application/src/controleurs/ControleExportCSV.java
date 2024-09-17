package controleurs;

import java.util.*;

import ihm.IHMExport;
import ihm.IHMExportCSV;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import metier.GenerateurFichier;
import metier.GenerateurFichierCSV;
import metier.HistogrammeDiametre;

/**
 * La classe ControleExportCSV gère le processus d'exportation des histogrammes ou des statistiques au format CSV.
 */
public class ControleExportCSV {
    private IHMExportCSV IHM;
	
    /**
     * constructeur de la classe ControleExportCSV
     */
    public ControleExportCSV() 
    {
    	this.IHM = new IHMExportCSV();
    }
	
    /**
     * getter de l'attribut IHM
     * @return IHM de type IHMExportCSV
     */
	public IHMExportCSV getIHM() {
		return IHM;
	}

	/**
	 * setter de l'attribut IHM
	 * @param IHM de type IHMExportCSV
	 */
	public void setIHM(IHMExportCSV IHM) {
		this.IHM = IHM;
	}
	
	
	
}