/**
 * 
 */
package com.trollcustom.shared;

import java.io.Serializable;



/**
 * @author Tonyo
 *
 */
public class Troll implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int attaque, degats, esquive, regeneration, vie, competence1, competence2,id;
	String nom, race,url;
	/**
	 * 
	 */
	public Troll() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param attaque
	 * @param degats
	 * @param esquive
	 * @param regeneration
	 * @param vie
	 * @param comptence1
	 * @param comptence2
	 * @param nom
	 * @param race
	 */
	public Troll(int attaque, int degats, int esquive, int regeneration,
			int vie, int comptence1, int comptence2, String nom, String race, String URL) {
		super();
		this.attaque = attaque;
		this.degats = degats;
		this.esquive = esquive;
		this.regeneration = regeneration;
		this.vie = vie;
		this.competence1 = comptence1;
		this.competence2 = comptence2;
		this.nom = nom;
		this.race = race;
		this.url = URL;
	}
	
	
	public Troll(int attaque, int degats, int esquive, int regeneration,
			int vie, int comptence1, int comptence2, String nom, String race, String URL,int id) {
		super();
		this.attaque = attaque;
		this.degats = degats;
		this.esquive = esquive;
		this.regeneration = regeneration;
		this.vie = vie;
		this.competence1 = comptence1;
		this.competence2 = comptence2;
		this.nom = nom;
		this.race = race;
		this.url = URL;
		this.id = id;
	}

	/**
	 * @return the attaque
	 */
	public int getAttaque() {
		return attaque;
	}
	/**
	 * @param attaque the attaque to set
	 */
	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}
	/**
	 * @return the degats
	 */
	public int getDegats() {
		return degats;
	}
	/**
	 * @param degats the degats to set
	 */
	public void setDegats(int degats) {
		this.degats = degats;
	}
	/**
	 * @return the esquive
	 */
	public int getEsquive() {
		return esquive;
	}
	/**
	 * @param esquive the esquive to set
	 */
	public void setEsquive(int esquive) {
		this.esquive = esquive;
	}
	/**
	 * @return the regeneration
	 */
	public int getRegeneration() {
		return regeneration;
	}
	/**
	 * @param regeneration the regeneration to set
	 */
	public void setRegeneration(int regeneration) {
		this.regeneration = regeneration;
	}
	/**
	 * @return the vie
	 */
	public int getVie() {
		return vie;
	}
	/**
	 * @param vie the vie to set
	 */
	public void setVie(int vie) {
		this.vie = vie;
	}
	/**
	 * @return the comptence1
	 */
	public int getComptence1() {
		return competence1;
	}
	/**
	 * @param comptence1 the comptence1 to set
	 */
	public void setComptence1(int comptence1) {
		this.competence1 = comptence1;
	}
	/**
	 * @return the comptence2
	 */
	public int getComptence2() {
		return competence2;
	}
	/**
	 * @param comptence2 the comptence2 to set
	 */
	public void setComptence2(int comptence2) {
		this.competence2 = comptence2;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the race
	 */
	public String getRace() {
		return race;
	}
	/**
	 * @param race the race to set
	 */
	public void setRace(String race) {
		this.race = race;
	}

	/**
	 * @return the uRL
	 */
	public String getURL() {
		return url;
	}

	/**
	 * @param uRL the uRL to set
	 */
	public void setURL(String uRL) {
		url = uRL;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}
