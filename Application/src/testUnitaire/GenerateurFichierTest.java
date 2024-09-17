package testUnitaire;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import metier.GenerateurFichier;

import java.io.File;
import java.util.concurrent.CountDownLatch;

class GenerateurFichierTest {

    private GenerateurFichier generateur;
    private BarChart<String, Number> histogramme;

    @BeforeEach
    void setUp() throws InterruptedException {
        new JFXPanel();

        final CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            generateur = new GenerateurFichier();
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            histogramme = new BarChart<>(xAxis, yAxis);
            latch.countDown();
        });
        
        // Attend que les opérations runLater() soient effectuées
        latch.await();
    }

    @Test
    void testEnregistrerHistogrammeEnPNG_Success() throws InterruptedException {
        // pour attendre la fin de l'opération runLater
        final CountDownLatch latch = new CountDownLatch(1);

        // Chemin du fichier où l'image sera enregistrée
        String cheminFichier = "test.png";

        Platform.runLater(() -> {
            boolean resultat = generateur.enregistrerHistogrammeEnPNG(histogramme, cheminFichier);
            assertTrue(resultat);
            File file = new File(cheminFichier);
            assertTrue(file.exists());
            assertTrue(file.delete());

            latch.countDown(); 
        });

        // Attendre que le test soit terminé
        latch.await();
    }
}
