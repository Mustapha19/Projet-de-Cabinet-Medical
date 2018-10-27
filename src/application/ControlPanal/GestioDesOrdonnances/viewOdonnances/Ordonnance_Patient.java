package application.ControlPanal.GestioDesOrdonnances.viewOdonnances;

public class Ordonnance_Patient {
	private int p_ID;
	private int numero_Ord;
	private String nom;
	private String prenom;
	private String date_naissance;
	private String date_Ord;
	
	
	
	
	public Ordonnance_Patient() {
		
	}
	public Ordonnance_Patient(int numero_Ord,String nom, String prenom, String date_naissance,String date_Ord) {
		setNumero_Ord(numero_Ord);
		setNom(nom);
		setPrenom(prenom);
		setDate_naissance(date_naissance);
		setDate_Ord(date_Ord);
		
		}
	
	public Ordonnance_Patient(int p_ID, int numero_Ord, String nom, String prenom, String date_naissance,
			String date_Ord) {
		
		setP_ID(p_ID);
		setNumero_Ord(numero_Ord);
		setNom(nom);
		setPrenom(prenom);
		setDate_naissance(date_naissance);
		setDate_Ord(date_Ord);
		
	}
	public Ordonnance_Patient(int p_ID, String nom, String prenom, String date_naissance,int numero_Ord,String date_Ord) {
		setP_ID(p_ID);
		setNom(nom);
		setPrenom(prenom);
		setDate_naissance(date_naissance);
		setNumero_Ord(numero_Ord);
		setDate_Ord(date_Ord);
		
		}

	public int getP_ID() {
		return p_ID;
	}

	public void setP_ID(int p_ID) {
		this.p_ID = p_ID;
	}

	public int getNumero_Ord() {
		return numero_Ord;
	}

	public void setNumero_Ord(int numero_Ord) {
		this.numero_Ord = numero_Ord;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getDate_Ord() {
		return date_Ord;
	}

	public void setDate_Ord(String date_Ord) {
		this.date_Ord = date_Ord;
	}

	
	
}
