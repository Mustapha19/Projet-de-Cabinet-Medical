
package application.ControlPanal.gestionPatients.viewPatients;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import application.ControlPanal.gestionPatients.PatientTable;
import application.ControlPanal.gestionPatients.Patients;
import application.ControlPanal.gestionPatients.updateOrDelete.UpdateOrDelController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewPatientsController implements Initializable {
	
	private String input_search;
	@FXML private AnchorPane view;
	@FXML private JFXTextField search;
	@FXML JFXButton close;
	@FXML JFXButton print;
	@FXML JFXButton chercher;
	@FXML private TableView<Patients> patientTable;
	
	//To display the content in the table,
	// the name of columns must be the same with the Column id in the Patient class to display the 
	@FXML private TableColumn<Patients, Integer> id;
	@FXML private TableColumn<Patients, String> firstName;
	@FXML private TableColumn<Patients, String> lastName;
	@FXML private TableColumn<Patients, String> dateOfBirth;
	@FXML private TableColumn<Patients, String> sexe;
	@FXML private TableColumn<Patients, String> blood;
	@FXML private TableColumn<Patients, String> phoneNumber;
	@FXML private TableColumn<Patients, String> dateOfCreation;
	private ObservableList<Patients> list  = FXCollections.observableArrayList();
	private Patients person;
	int patientId;
	String patient_FirstName;
	String patient_LastName;
	String patient_date_of_birth;
	String patient_gender;
	String patient_phone_number;
	KeyCombination keyComb1; 
	Set<Patients> possibleWords = new HashSet<>();
	@SuppressWarnings("unused")
	private AutoCompletionBinding<Patients> autocomplet;
 //=======================================================================================================================================
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		JFXDepthManager.setDepth(patientTable, 4);
		JFXDepthManager.setDepth(search, 4);
		JFXDepthManager.setDepth(close, 4);
		JFXDepthManager.setDepth(chercher, 4);
		JFXDepthManager.setDepth(print, 4);
		keyComb1 = new KeyCodeCombination(KeyCode.SPACE,
                KeyCombination.CONTROL_DOWN);
		search.addEventHandler(javafx.scene.input.KeyEvent.KEY_PRESSED,new EventHandler<javafx.scene.input.KeyEvent>() {
			@Override
			public void handle(javafx.scene.input.KeyEvent event) {
				if (keyComb1.match(event)) {
                    try {search_for_patients();}
                    catch (SQLException e) {e.printStackTrace();}
                }
			}
		});
		show_Patient_table();
		id.setCellValueFactory(new PropertyValueFactory<Patients, Integer>("id"));
		firstName.setCellValueFactory(new PropertyValueFactory<Patients, String>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<Patients, String>("lastName"));
		dateOfBirth.setCellValueFactory(new PropertyValueFactory<Patients, String>("dateOfBirth"));
		sexe.setCellValueFactory(new PropertyValueFactory<Patients, String>("sexe"));
		blood.setCellValueFactory(new PropertyValueFactory<Patients, String>("blood"));
		phoneNumber.setCellValueFactory(new PropertyValueFactory<Patients, String>("phoneNumber"));
		dateOfCreation.setCellValueFactory(new PropertyValueFactory<Patients, String>("dateOfCreation"));
		dateOfCreation.setEditable(true);
		MultipleSelectionModel<Patients> selected = patientTable.getSelectionModel();
		selected.selectedItemProperty().addListener(new ChangeListener<Patients>() {
			@Override
			public void changed(ObservableValue<? extends Patients> observable, Patients oldValue,Patients newValue) {
				search.setText(patientTable.getSelectionModel().getSelectedItem().getLastName()+" "+
						patientTable.getSelectionModel().getSelectedItem().getFirstName());
				 person = new Patients(
						patientTable.getSelectionModel().getSelectedItem().getId(),
						patientTable.getSelectionModel().getSelectedItem().getFirstName(),
						patientTable.getSelectionModel().getSelectedItem().getLastName(),
						patientTable.getSelectionModel().getSelectedItem().getDateOfBirth(),
						patientTable.getSelectionModel().getSelectedItem().getSexe()+"",
						patientTable.getSelectionModel().getSelectedItem().getBlood(),
						patientTable.getSelectionModel().getSelectedItem().getPhoneNumber(),
						patientTable.getSelectionModel().getSelectedItem().getDateOfCreation()
						);
				patientTable.setOnKeyPressed(event->{
					if(event.getCode()==KeyCode.U){
				    	try {
							Stage upd_del_stage = new Stage();
							FXMLLoader loader = new FXMLLoader();
							Pane root_UpdDEL;
							root_UpdDEL = loader.load(getClass().getResource("/application/ControlPanal/gestionPatients/updateOrDelete/UpdateOrDelete.fxml").openStream());
							Scene scene = new Scene(root_UpdDEL,1200,680);
							scene.getStylesheets().add(getClass().getResource("/application/ControlPanal/gestionPatients/updateOrDelete/UpdateOrDelete.css").toExternalForm());
							upd_del_stage.setScene(scene);
							UpdateOrDelController switch_To_Update_Window = (UpdateOrDelController)loader.getController();
							switch_To_Update_Window.patient_Selected_FromPatientList(person);
							upd_del_stage.setTitle("UPDATE/DELETE");
							upd_del_stage.setResizable(false);
							upd_del_stage.show();
							((Node)event.getSource()).getScene().getWindow().hide();
						}catch (IOException e) {e.printStackTrace();}
							}
						});
			}
		});
		
	}
 //=======================================================================================================================================
	public void search_for_patients() throws SQLException {
		input_search = search.getText();
		Patients person = new Patients();
		for (Patients patients2 : possibleWords) {
			if (patients2.toString().equals(input_search)||patients2.getLastName().equalsIgnoreCase(input_search)||patients2.getFirstName().equalsIgnoreCase(input_search)) {
				person = patients2;
			}
		}
		ResultSet suggestion_list = PatientTable.show_suggestion(person);
		list.clear();
		while (suggestion_list.next()) {
			list.add(new Patients(
									suggestion_list.getInt(1),
									suggestion_list.getString(2),
									suggestion_list.getString(3),
									suggestion_list.getString(4),
									suggestion_list.getString(5),
									suggestion_list.getString(6),
									suggestion_list.getString(7),
									suggestion_list.getString(8)));
		}
		patientTable.setItems(list);
	}
	
	//=======================================================================================================================================
		 public void show_Patient_table() {
			 list.clear();
			 try {
					ResultSet result_list = PatientTable.show_Patient_Table();
					while (result_list.next()) { 
						Patients patients = new Patients(result_list.getInt(1),
								  result_list.getString(2),
								  result_list.getString(3),
								  result_list.getString(4),
								  result_list.getString(5),
								  result_list.getString(6),
								  result_list.getString(7),
								  result_list.getString(8));
						list.add(patients);
						possibleWords.add(patients);
					}
				} catch (SQLException e) {e.printStackTrace();}
			 patientTable.setItems(list);
			 autocomplet = TextFields.bindAutoCompletion(search, possibleWords);
		 }
//=======================================================================================================================================
	 public void close(ActionEvent event) {
			((Node)event.getSource()).getScene().getWindow().hide();
		}
		

//=======================================================================================================================================
	public void print() throws FileNotFoundException, DocumentException, IOException {
		Document pdfDocument = new Document(PageSize.A4);
		PdfWriter.getInstance(pdfDocument, new FileOutputStream("PatientList.pdf"));
		pdfDocument.open();
		pdfDocument.add(new Paragraph("Liste Des Patients \n",
									  FontFactory.getFont(FontFactory.TIMES,16,Font.BOLD)));
		pdfDocument.add(new Paragraph("Patient Report"));
		pdfDocument.add(new Paragraph("______________________________________________________________________________"));
		// PdfPTable tableName = new PdfPTable(int NumberofColumns);
		PdfPTable table = new PdfPTable(8);
		
		pdfDocument.add(new Paragraph("                                        "));
		PdfPCell cell = new PdfPCell(new Paragraph("Patients"));
		BaseFont bf = BaseFont.createFont(
                BaseFont.TIMES_ROMAN,
                BaseFont.CP1252,
                BaseFont.EMBEDDED);
        Font font = new Font(bf, 7);
		cell.setColspan(16);
		cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.decode("#5C6BC0"));
		table.addCell(cell);
		table.setWidthPercentage(100);
		table.addCell(new Phrase("Patient ID", font));
		table.addCell(new Phrase("Nom", font));
		table.addCell(new Phrase("Prénom", font));
		table.addCell(new Phrase("Date de naissance", font));
		table.addCell(new Phrase("Sexe", font));
		table.addCell(new Phrase("Groupage", font));
		table.addCell(new Phrase("Téléphone", font));
		table.addCell(new Phrase("Date de création", font));
		ObservableList<Patients> contenu= patientTable.getItems();
		 for (Patients content : contenu) {
			 	table.addCell(new Phrase(content.getId()+"", font));
				table.addCell(new Phrase(content.getLastName(), font));		
				table.addCell(new Phrase(content.getFirstName(), font));
				table.addCell(new Phrase(content.getDateOfBirth(), font));
				table.addCell(new Phrase(content.getSexe()+"", font));		
				table.addCell(new Phrase(content.getBlood(), font));
				table.addCell(new Phrase(content.getPhoneNumber(), font));
				table.addCell(new Phrase(content.getDateOfCreation(), font));
		}
		pdfDocument.add(table);
		pdfDocument.close();
		
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"PatientList.pdf");
	}
//=======================================================================================================================================
	
}
