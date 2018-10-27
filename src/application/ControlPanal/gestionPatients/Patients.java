package application.ControlPanal.gestionPatients;

public class Patients{
	private int id;
	private String firstName;
	private String lastName;
	private String sexe;
	private String blood;
	private String dateOfBirth;
	private String phoneNumber;
	private String dateOfCreation;
	
	public Patients(){		
		
	}
	
	public Patients(String firstName, String lastName) {
		setFirstName(firstName);
		setLastName(lastName);
	}
	public Patients(String firstName, String lastName,String dateOfBirth) {
		setFirstName(firstName);
		setLastName(lastName);
		setDateOfBirth(dateOfBirth);
	}
	public Patients(int id,String firstName, String lastName, String dateOfBirth,String sexe, String phoneNumber,String dateOfCreation) {	
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setDateOfBirth(dateOfBirth);
		setSexe(sexe);
		setPhoneNumber(phoneNumber);
		setDateOfCreation(dateOfCreation);
	}
	public Patients(int id,String firstName, String lastName, String dateOfBirth,String sexe, String phoneNumber) {	
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setDateOfBirth(dateOfBirth);
		setSexe(sexe);
		setPhoneNumber(phoneNumber);
	}
	
	public Patients(int id,String firstName, String lastName, String dateOfBirth,String sexe,String blood, String phoneNumber,String dateOfCreation) {	
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setDateOfBirth(dateOfBirth);
		setSexe(sexe);
		setBlood(blood);
		setPhoneNumber(phoneNumber);
		setDateOfCreation(dateOfCreation);
	}
	
	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id =id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName.toUpperCase();
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName.toUpperCase();
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe.toUpperCase();
	}
	
	public String getSexe() {
		return sexe;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	@Override
	public String toString() {
		return getLastName()+" "+getFirstName();
	}
	

	
 
}
