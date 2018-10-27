package application.ControlPanal.gestionMedicament;

public class Medicament {
	int numero;
	String code;
	String nom_Internat;
	String nom_de_marque;
	String forme;
	String dosage;
	String commentaire;
	
	public Medicament() {
			
		}
	
	
	
	
	public Medicament(String nom_Internat, String nom_de_marque, String forme, String dosage) {
		
		setNom_Internat(nom_Internat);
		setNom_de_marque(nom_de_marque);
		setForme(forme);
		setDosage(dosage);
		
	}




	//===============================================================================
	//Getters and Setters

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code.toUpperCase();
	}

	public String getNom_Internat() {
		return nom_Internat;
	}

	public void setNom_Internat(String nom_Internat) {
		this.nom_Internat = nom_Internat.toUpperCase();
	}

	public String getNom_de_marque() {
		return nom_de_marque;
	}

	public void setNom_de_marque(String nom_de_marque) {
		this.nom_de_marque = nom_de_marque.toUpperCase();
	}

	public String getForme() {
		return forme;
	}

	public void setForme(String forme) {
		this.forme = forme.toUpperCase();
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		if (!dosage.equals(null)) {
			this.dosage = dosage.toUpperCase();			
		}
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire.toUpperCase();
	}
	
	

	

}
