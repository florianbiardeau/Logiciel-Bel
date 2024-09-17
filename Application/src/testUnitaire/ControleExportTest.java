package testUnitaire;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controleurs.ControleExport;
import ihm.IHMExport;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import metier.GenerateurFichier;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class ControleExportTest {

    private ControleExport controleExport;
    private GenerateurFichierStub generateurFichier;
    private IHMExport ihmExport;
    private BarChart<String, Number> histogramme;


    @BeforeEach
    void setUp()  throws InterruptedException{
    	new JFXPanel();
        generateurFichier = new GenerateurFichierStub();
        ihmExport = new IHMExport(controleExport);
        controleExport = new ControleExport(ihmExport, generateurFichier);
        final CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            histogramme = new BarChart<>(xAxis, yAxis);
            latch.countDown();
        });
        
        latch.await();
    }

    @Test
    void testExporterHistogrammeAvecSucces () throws InterruptedException{

        final CountDownLatch latch = new CountDownLatch(0);
        String cheminFichier = "test.png";
        Platform.runLater(() -> {
        	 generateurFichier.setEnregistrementReussi(true);
             int resultat = controleExport.exporterHistogramme(histogramme, cheminFichier, ihmExport);
             assertEquals(1, resultat); 
             latch.countDown();
        });
        assertTrue(latch.await(5, TimeUnit.SECONDS));
    }

    @Test
    void testExporterHistogrammeEchec() throws InterruptedException{
    	
    	final CountDownLatch latch = new CountDownLatch(1);
        String cheminFichier = "test.png";
        Platform.runLater(() -> {
       	 generateurFichier.setEnregistrementReussi(false);      
            int resultat = controleExport.exporterHistogramme(histogramme, cheminFichier, ihmExport);
            assertEquals(0, resultat); 
            latch.countDown();
       });
        assertTrue(latch.await(5, TimeUnit.SECONDS));
    }


    class GenerateurFichierStub extends GenerateurFichier {
        private boolean enregistrementReussi;

        void setEnregistrementReussi(boolean valeur) {
            enregistrementReussi = valeur;
        }

        @Override
        public boolean enregistrerHistogrammeEnPNG(BarChart<?, ?> histogramme, String cheminFichier) {
            return enregistrementReussi;
        }
    }
}