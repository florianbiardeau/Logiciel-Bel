package testUnitaire;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.OperateurDAO;
import metier.Operateur;

class TestOperateurDAO {
	
	private OperateurDAO opDAO;
	private Operateur op1;
	private Operateur op2;
	
	@BeforeEach
	void setUp() throws Exception {
		opDAO = new OperateurDAO();
		op1 = new Operateur("Tom");
		op2 = new Operateur("Bastien");
		Operateur opTest1 = opDAO.creer(op1);
		Operateur opTest2 = opDAO.creer(op2);
	}

	@Test
	void testCreerLire() {
		Operateur opAttendu = opDAO.lire(op1.getIdOperateur());
		// On veut que op1 et opAttendu aient les memes attributs pour verifier que l'operateur soit bien enregistree 
		// dans la base de donnees avec les bons attributs
		assertEquals(op1.getIdOperateur(), opAttendu.getIdOperateur());
		assertEquals(op1.getNomOperateur(), opAttendu.getNomOperateur());
		// Maintenant on vérifie que les attributs soient les memes partout et mis a jour lorsqu'on demande de creer
		// a nouveau un operateur deja dans la base de donnees,
		// On remet d'abord l'idOperateur de op1 a 0
		op1.setIdOperateur(0);
		Operateur opTestBis = opDAO.creer(op1);
		Operateur opAttenduBis = opDAO.lire(opTestBis.getIdOperateur());
		assertEquals(op1.getIdOperateur(), opAttenduBis.getIdOperateur());
		assertEquals(op1.getNomOperateur(), opAttenduBis.getNomOperateur());
		// Si on essaie de lire un operateur qui n'est pas dans la base de donnees
		Operateur opAttenduTer = opDAO.lire(-1);
		assertNull(opAttenduTer);
	}
	
	@Test
	void testMettreAJour() {
		String nomOperateur = "Raphaël";
		op2.setNomOperateur(nomOperateur);
		opDAO.mettreAJour(op2);
		Operateur opAttendu = opDAO.lire(op2.getIdOperateur());
		assertEquals(opAttendu.getNomOperateur(), nomOperateur);
	}
	
	@Test
	void testSupprimer() {
		opDAO.supprimer(op1);
		assertNull(opDAO.lire(op1.getIdOperateur()));
		opDAO.supprimer(op2);
		assertNull(opDAO.lire(op2.getIdOperateur()));
	}
	
	@Test
	void testLireListe() {
		ArrayList<Operateur> listeOp = opDAO.lire();
		listeOp.stream().forEach(System.out::println);
		int tailleListeOp = listeOp.size();
		assertTrue(tailleListeOp > 2);
		Operateur opAttendu1 = listeOp.get(tailleListeOp-2);
		Operateur opAttendu2 = listeOp.get(tailleListeOp-1);
		assertEquals(op1.getIdOperateur(), opAttendu1.getIdOperateur());
		assertEquals(op2.getIdOperateur(), opAttendu2.getIdOperateur());
		assertEquals(op1.getNomOperateur(), opAttendu1.getNomOperateur());
		assertEquals(op2.getNomOperateur(), opAttendu2.getNomOperateur());
	}
	
	@AfterEach
	void nettoyer() {
		opDAO.supprimer(op1);
		opDAO.supprimer(op2);
	}
}
