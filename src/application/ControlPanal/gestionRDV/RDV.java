package application.ControlPanal.gestionRDV;
import java.sql.SQLException;
import application.ControlPanal.gestionPatients.Patients;

public class RDV{
	private int patientID;
	private int employeeID;
	private int numRDV;
	private String firstName;
	private String lastName;
	private String dateRDV;
	private String timeRDV;
	private String commentaire;
	private String dateOfCreation;
	
	
	public RDV(int patientID, int numRDV, String firstName, String lastName, String dateRDV,
			String timeRDV, String commentaire) {
		setPatientID(patientID);
		setNumRDV(numRDV);
		setFirstName(firstName);
		setLastName(lastName);
		setDateRDV(dateRDV);
		setTimeRDV(timeRDV);
		setCommentaire(commentaire);
	}

	public RDV(int employeeID,int patientID,int numRDV,String dateRDV, String timeRDV, String commentaire) throws SQLException {
		//this allows us to get the first name and last name of the patient where his id = patientID
		Patients result = RDVTable.getPatientName_ByID(patientID);
		setEmployeeID(employeeID);
		setPatientID(patientID);
		// So whene the result is returned we extract the first name and last name and 
		// set them to the first name and last name fields
		setFirstName(result.getFirstName());
		setLastName(result.getLastName());
		setNumRDV(numRDV);
		setDateRDV(dateRDV);
		setTimeRDV(timeRDV);
		setCommentaire(commentaire);
		setDateOfCreation("");
	}
	
	public RDV(int patientID,String firstName,String lastName,String dateRDV, String timeRDV, String commentaire) throws SQLException {
		setPatientID(patientID);
		setFirstName(firstName);
		setLastName(lastName);
		setDateRDV(dateRDV);
		setTimeRDV(timeRDV);
		setCommentaire(commentaire);
	
	}
	

	public RDV(String dateRDV, String timeRDV, String commentaire) {
		setDateRDV(dateRDV);
		setTimeRDV(timeRDV);
		setCommentaire(commentaire);
	}

	public RDV() {
			
		}
	
	
	//setters and getters
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
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public int getNumRDV() {
		return numRDV;
	}
	public void setNumRDV(int numRDV) {
		this.numRDV = numRDV;
	}
	public String getDateRDV() {
		return dateRDV;
	}
	public void setDateRDV(String dateRDV) {
		this.dateRDV = dateRDV;
	}
	public String getTimeRDV() {
		return timeRDV;
	}
	public void setTimeRDV(String timeRDV) {
		this.timeRDV = timeRDV;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
}
