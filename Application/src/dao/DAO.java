package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class DAO<T> {
	
	protected Connection connect;
	protected Statement stmt;
	
	public DAO() {
		open();
	}
	/**
     * Ouvre une connexion à la base de données
     */
	public void open() {
		try {
			 // Obtient une instance unique de connexion à la base de données
			connect=  SingleConnection.getInstance();
			stmt = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

		} catch (Exception e) {
			// En cas d'erreur, affiche un message
			System.out.println(" === ERREUR OPEN DAO === ");
			e.printStackTrace();
		}
		
	}
	/**
     * Ferme la connexion à la base de données.
     */
	public void close(){
		// Ferme la connexion à la base de données en utilisant la classe SingleConnection
		try {
			SingleConnection.close();
		} catch (Exception e) {
			 // En cas d'erreur, affiche un message
			System.out.println(" === ERREUR CLOSE DAO === ");
			e.printStackTrace();
		}
	}
	
	/**
	 * Permet de récupérer les objets
	 * @return
	 */
	public abstract ArrayList<T> lire();
	
	/**
	 * Permet de créer une entrée dans la base de données
	 * par rapport à un objet
	 * @param obj
	 */
	public abstract T creer(T obj);
	
	/**
	 * Permet de mettre à jour les données d'une entrée dans la base 
	 * @param obj
	 */
	public abstract T mettreAJour(T obj);
	
	/**
	 * Permet la suppression d'une entrée de la base
	 * @param obj
	 */
	public abstract void supprimer(T obj);

}
