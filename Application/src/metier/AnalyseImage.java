package metier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Permet l'analyse des images pour en tirer les statistiques
 */
public class AnalyseImage {

    public AnalyseImage() {
    }

    /**
     * permet l'exécution des scripts python qui analysent l'image, met ensuite dans le dossier res le résultat sous la forme d'un fichier CSV et d'une image PNG
     * @param nomImage nom de l'image à analyser (doit être présente dans le dossier scriptsImages)
     * @throws IOException 
     */
    public void executerScripts(String nomImage) throws IOException {
    		//execute le script demo.py avec comme argument le nom de l'image
    		ProcessBuilder builder = new ProcessBuilder("python",
    				System.getProperty("user.dir") + "\\scriptsImages\\demo.py", nomImage);
    		Process process=builder.start();
    		
    		BufferedReader readers = new BufferedReader(new InputStreamReader(process.getErrorStream()));
    		//permet de lire les lignes en cas d'erreur
    		String lines=null;
    		while((lines=readers.readLine())!=null) {
    			System.out.println("Errors: " + lines);
    		}
    }

}