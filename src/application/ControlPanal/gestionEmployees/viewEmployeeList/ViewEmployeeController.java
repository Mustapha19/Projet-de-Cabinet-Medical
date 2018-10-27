
package application.ControlPanal.gestionEmployees.viewEmployeeList;

import java.awt.Color;
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
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import application.ControlPanal.gestionEmployees.Employee;
import application.ControlPanal.gestionEmployees.EmployeeTable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ViewEmployeeController implements Initializable {
	
	@FXML AnchorPane view;
	@FXML JFXTextField search;
	//To display the content in the table,
	// the name of columns must be the same with the fields name in the Patient class to display the 
	@FXML private TableView<Employee> userTable;
    @FXML private TableColumn<Employee, Integer> user_ID;
    @FXML private TableColumn<Employee,String> nom;
    @FXML private TableColumn<Employee,String> prenom;
    @FXML private TableColumn<Employee,String> date_naissance;
    @FXML private TableColumn<Employee,String> gender;
    @FXML private TableColumn<Employee,String> debut_travaille;
    @FXML private TableColumn<Employee,String> salaire;
    @FXML private TableColumn<Employee,String> groupage;
    @FXML private TableColumn<Employee,String> telephone;
    @FXML  JFXButton close;
    @FXML  JFXButton imprimer;
    @FXML  JFXButton chercher;
   
    ObservableList<Employee>user_List = FXCollections.observableArrayList();
    private String input_search;
    Set<Employee> possibleWords = new HashSet<>();
	@SuppressWarnings("unused")
	private AutoCompletionBinding<Employee> autocomplet;
//=======================================================================================================================
    @Override
	public void initialize(URL location, ResourceBundle resources) {	
		JFXDepthManager.setDepth(userTable, 4);
		JFXDepthManager.setDepth(search, 4);
		JFXDepthManager.setDepth(imprimer, 4);
		JFXDepthManager.setDepth(close, 4);
		JFXDepthManager.setDepth(chercher, 4);
		
		show_Employee_table();
		user_ID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("user_ID"));
		nom.setCellValueFactory(new PropertyValueFactory<Employee, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Employee, String>("prenom"));
		date_naissance.setCellValueFactory(new PropertyValueFactory<Employee, String>("date_naissance"));
		gender.setCellValueFactory(new PropertyValueFactory<Employee, String>("gender"));
		groupage.setCellValueFactory(new PropertyValueFactory<Employee, String>("groupage"));
		salaire.setCellValueFactory(new PropertyValueFactory<Employee, String>("salaire"));
		debut_travaille.setCellValueFactory(new PropertyValueFactory<Employee, String>("debut_travaille"));
		telephone.setCellValueFactory(new PropertyValueFactory<Employee, String>("telephone"));
		MultipleSelectionModel<Employee> selected = userTable.getSelectionModel();
		selected.selectedItemProperty().addListener(new ChangeListener<Employee>() {
			@Override
			public void changed(ObservableValue<? extends Employee> observable, Employee oldValue, Employee newValue) {
				search.setText(userTable.getSelectionModel().getSelectedItem().getNom()+" "+userTable.getSelectionModel().getSelectedItem().getPrenom());
			}
		});
	}
//=======================================================================================================================================
    public void show_Employee_table() {
		user_List.clear();
		try {
			ResultSet listset= EmployeeTable.show_Employee_Table();
			while (listset.next()) {
				Employee employee = new Employee(
						listset.getInt(1),
						listset.getString(2),
						listset.getString(3),
						listset.getString(4),
						listset.getString(5),
						listset.getString(6),
						listset.getString(7),
						listset.getString(8),
						listset.getString(9)
						);
				user_List.add(employee);
				possibleWords.add(employee);
				
			}
			listset.close();
		} catch (SQLException e) {e.printStackTrace();}
		userTable.setItems(user_List);
		autocomplet = TextFields.bindAutoCompletion(search, possibleWords);
	}
 //======================================================================================================================================
    public void search_for_Emplpyee() throws SQLException {
		input_search = search.getText();
		Employee person = new Employee();
		for (Employee employee : possibleWords) {
			if (employee.toString().equals(input_search)||employee.getNom().equalsIgnoreCase(input_search)||employee.getPrenom().equalsIgnoreCase(input_search)) {
				person = employee;
			}
		}
		ResultSet suggestion_list = EmployeeTable.show_suggestion(person);
		user_List.clear();
		while (suggestion_list.next()) {
			user_List.add(new Employee(
								suggestion_list.getInt(1),
								suggestion_list.getString(2),
								suggestion_list.getString(3),
								suggestion_list.getString(4),
								suggestion_list.getString(5),
								suggestion_list.getString(6),
								suggestion_list.getString(7),
								suggestion_list.getString(8),
								suggestion_list.getString(9)));
		}
		userTable.setItems(user_List);
	}
    public void close(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	public void imprimer() throws DocumentException, IOException{
		BaseFont bf = BaseFont.createFont(
                BaseFont.TIMES_ROMAN,
                BaseFont.CP1252,
                BaseFont.EMBEDDED);
		Font fontheader = new Font(bf, 7);
		Font title = new Font(bf, 16);
        Font font = new Font(bf, 5);
		Document pdfDocument = new Document(PageSize.A4);
		PdfWriter.getInstance(pdfDocument, new FileOutputStream("Employee.pdf"));
		pdfDocument.open();
		Paragraph documentTitle = new Paragraph("Liste des Employées ",title);
		documentTitle.setAlignment(Element.ALIGN_CENTER);

		pdfDocument.add(new Paragraph(documentTitle));
		pdfDocument.add(new Paragraph("   "));
		pdfDocument.add(new Paragraph("______________________________________________________________________________"));
		// PdfPTable tableName = new PdfPTable(int NumberofColumns);
		PdfPTable table = new PdfPTable(9);
		
		pdfDocument.add(new Paragraph());
		pdfDocument.add(new Paragraph("                                        "));
		PdfPCell cell = new PdfPCell(new Paragraph("Employées"));
		
		cell.setColspan(18);
		cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.decode("#5C6BC0"));
		table.addCell(cell);
		table.setWidthPercentage(100);
		table.addCell(new Phrase("Employee ID", fontheader));
		table.addCell(new Phrase("Nom", fontheader));
		table.addCell(new Phrase("Prénom", fontheader));
		table.addCell(new Phrase("Date de naissance", fontheader));
		table.addCell(new Phrase("Sexe", fontheader));
		table.addCell(new Phrase("Groupage", fontheader));
		table.addCell(new Phrase("Salaire", fontheader));
		table.addCell(new Phrase("Debut_Travaille", fontheader));
		table.addCell(new Phrase("Téléphone", fontheader));
		ObservableList<Employee> contenu= userTable.getItems();
		 for (Employee content : contenu) {
			 	table.addCell(new Phrase(content.getUser_ID()+"", font));
				table.addCell(new Phrase(content.getNom(), font));		
				table.addCell(new Phrase(content.getPrenom(), font));
				table.addCell(new Phrase(content.getDate_naissance(), font));
				table.addCell(new Phrase(content.getGender()+"", font));		
				table.addCell(new Phrase(content.getGroupage(), font));
				table.addCell(new Phrase(content.getSalaire(), font));
				table.addCell(new Phrase(content.getDebut_travaille(), font));
				table.addCell(new Phrase(content.getTelephone(), font));
		}
		pdfDocument.add(table);
		pdfDocument.close();
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"Employee.pdf");

		
	}

}
