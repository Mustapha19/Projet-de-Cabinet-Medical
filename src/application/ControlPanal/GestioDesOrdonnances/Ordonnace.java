package application.ControlPanal.GestioDesOrdonnances;

public class Ordonnace{
	private int N_ord;
	private String medecament;
	private String forme;
	private String dosage;
	private int quantite;
	private String duree_trait;
	private String commentaire;

	public Ordonnace(){
		
	}
	
	public Ordonnace(int n_ord, String medecament, String forme, String dosage, int quantite, String duree_trait,
			String commentaire) {
		setN_ord(n_ord);
		setMedecament(medecament);
		setForme(forme);
		setDosage(dosage);
		setQuantite(quantite);
		setDuree_trait(duree_trait);
		setCommentaire(commentaire);
		
	}
	public Ordonnace(String medecament, String forme, String dosage, int quantite, String duree_trait,
			String commentaire) {
		
		setMedecament(medecament);
		setForme(forme);
		setDosage(dosage);
		setQuantite(quantite);
		setDuree_trait(duree_trait);
		setCommentaire(commentaire);
		
	}

	public int getN_ord() {return N_ord;}
	public void setN_ord(int n_ord) {N_ord = n_ord;	}
	public String getMedecament() {return medecament;}
	public void setMedecament(String medecament) {this.medecament = medecament.toUpperCase();}
	public String getForme() {return forme;}
	public void setForme(String forme) {this.forme = forme.toUpperCase();}
	public String getDosage() {	return dosage;}
	public void setDosage(String dosage) {this.dosage = dosage.toUpperCase();}
	public int getQuantite() {return quantite;}
	public void setQuantite(int quantite) {this.quantite = quantite;}
	public String getDuree_trait() {return duree_trait;}
	public void setDuree_trait(String duree_trait) {this.duree_trait = duree_trait.toUpperCase();}
	public String getCommentaire() {return commentaire;	}
	public void setCommentaire(String commentaire) {this.commentaire = commentaire.toUpperCase();}
	

}
