package application.ControlPanal.gestionEmployees;


public class Employee{
   private int	user_ID;
   private String nom;
   private String prenom;
   private String date_naissance;
   private String gender;
   private String debut_travaille;
   private String salaire;
   private String groupage;
   private String telephone;
	public Employee() {
	
	}
	public Employee(int user_ID, String nom, String prenom, String date_naissance, String gender,String groupage,String salaire,
					String debut_travaille,String telephone) {
		setUser_ID(user_ID);
		setNom(nom);
		setPrenom(prenom);
		setDate_naissance(date_naissance);
		setGender(gender);
		setGroupage(groupage);
		setSalaire(salaire);
		setDebut_travaille(debut_travaille);
		setTelephone(telephone);
	
	}
	public Employee(String nom, String prenom, String date_naissance, String gender,String groupage,String salaire,String debut_travaille,
					String telephone) {
		
		setNom(nom);
		setPrenom(prenom);
		setDate_naissance(date_naissance);
		setGender(gender);
		setGroupage(groupage);
		setSalaire(salaire);
		setDebut_travaille(debut_travaille);
		setTelephone(telephone);
	
	}
	public Employee(String nom, String prenom, String date_naissance) {
		setNom(nom);
		setPrenom(prenom);
		setDate_naissance(date_naissance);
	}
	public int getUser_ID() {return user_ID;}
	public void setUser_ID(int user_ID) {this.user_ID = user_ID;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom.toUpperCase();}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom.toUpperCase();}
	public String getDate_naissance() {return date_naissance;}
	public void setDate_naissance(String date_naissance) {this.date_naissance = date_naissance;}
	public String getGender() {return gender;}
	public void setGender(String gender) {this.gender = gender.toUpperCase();}
	public String getDebut_travaille() {return debut_travaille;}
	public void setDebut_travaille(String debut_travaille) {this.debut_travaille = debut_travaille.toUpperCase();}
	public String getSalaire() {return salaire;}
	public void setSalaire(String salaire) {this.salaire = salaire.toUpperCase();}
	public String getGroupage() {return groupage;}
	public void setGroupage(String groupage) {this.groupage = groupage.toUpperCase();}
	public String getTelephone() {return telephone;}
	public void setTelephone(String telephone) {this.telephone = telephone.toUpperCase();}
	@Override
	public String toString() {return getNom()+" "+getPrenom();}
		
	
	

	
 
}
