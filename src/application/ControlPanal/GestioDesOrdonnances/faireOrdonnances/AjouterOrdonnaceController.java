package application.ControlPanal.GestioDesOrdonnances.faireOrdonnances;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import application.ControlPanal.NotificationControler;
import application.ControlPanal.GestioDesOrdonnances.Ordonnace;
import application.ControlPanal.GestioDesOrdonnances.OrdonnanceTable;
import application.ControlPanal.gestionPatients.PatientTable;
import application.ControlPanal.gestionPatients.Patients;
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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class AjouterOrdonnaceController implements Initializable {

	@FXML private AnchorPane ordonnance_root;
	@FXML private JFXTextField nom_medicament;
    @FXML private JFXComboBox<String> dosage;
    @FXML private JFXComboBox<Integer> quant;
    @FXML private JFXComboBox<String> forme;
    @FXML private TableView<Ordonnace> ordonnance;
    @FXML private TableColumn<Ordonnace, String> medecament;
    @FXML private TableColumn<Ordonnace, String> medicament_forme;
    @FXML private TableColumn<Ordonnace, String> medicament_dosage;
    @FXML private TableColumn<Ordonnace, String> medicament_quantite;
    @FXML private TableColumn<Ordonnace, String> dureeDeTrait;
    @FXML private TableColumn<Ordonnace, String> comment;
    @FXML private JFXTextField duree;
    @FXML private JFXTextArea commentaire;
    @FXML private JFXTextField nom;
    @FXML private JFXDatePicker dateofbirth;
    @FXML private JFXButton souvegarde;
    @FXML private JFXButton impremer;
    @FXML private JFXButton fermer;
    private ObservableList<Ordonnace>list_medicaent = FXCollections.observableArrayList();
    private ObservableList<String>formeItems = FXCollections.observableArrayList();
    private ObservableList<String>list_dosages = FXCollections.observableArrayList();
    private ObservableList<Integer>list_quantite = FXCollections.observableArrayList();
    String lastName ;
	String firstName;
	String date;
	Patients person;
	int num_Ord;
	int med_ID;
	LocalDate birthdate;
	LocalDate now ;
	long age;
	Set<Patients> possibleName = new HashSet<>();
	@SuppressWarnings("unused")
	private AutoCompletionBinding<Patients> name_Autocomplete;
	Set<String> possibleMedicament = new HashSet<>();
	@SuppressWarnings("unused")
	private AutoCompletionBinding<String> medicament_Autocomplete;
	private String insertMessage= "Ordonnance a été bien Sauvegarder";
	NotificationControler notification;
//=======================================================================================================================================
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		JFXDepthManager.setDepth(commentaire, 4);
		JFXDepthManager.setDepth(dosage, 4);
		JFXDepthManager.setDepth(duree, 4);
		JFXDepthManager.setDepth(fermer, 4);
		JFXDepthManager.setDepth(forme, 4);
		JFXDepthManager.setDepth(impremer, 4);
		JFXDepthManager.setDepth(nom_medicament, 4);
		JFXDepthManager.setDepth(nom, 4);
		JFXDepthManager.setDepth(ordonnance, 4);
		JFXDepthManager.setDepth(ordonnance_root, 4);
		JFXDepthManager.setDepth(quant, 4);
		JFXDepthManager.setDepth(souvegarde, 4);
		JFXDepthManager.setDepth(dateofbirth, 4);
		
		ajoute_medicament_to_list();
		remplir_AutoCmplete_List_Patients();
		remplir_AutoCmplete_List_Medicaments();
		remplir_forme_par_medicament();
		remplir_quantite_comboBox();
		remplir_Dosage_comboBox();

		medecament.setCellValueFactory(new PropertyValueFactory<Ordonnace, String>("medecament"));
		medicament_forme.setCellValueFactory(new PropertyValueFactory<Ordonnace, String>("forme"));
		medicament_dosage.setCellValueFactory(new PropertyValueFactory<Ordonnace, String>("dosage"));
		medicament_quantite.setCellValueFactory(new PropertyValueFactory<Ordonnace, String>("quantite"));
		dureeDeTrait.setCellValueFactory(new PropertyValueFactory<Ordonnace, String>("duree_trait"));
		comment.setCellValueFactory(new PropertyValueFactory<Ordonnace, String>("commentaire"));
		ordonnance.setItems(list_medicaent);
		MultipleSelectionModel<Ordonnace> selected = ordonnance.getSelectionModel();
		selected.selectedItemProperty().addListener(new ChangeListener<Ordonnace>() {

			@Override
			public void changed(ObservableValue<? extends Ordonnace> observable, Ordonnace oldValue,Ordonnace newValue) {
				forme.setDisable(false);
				dosage.setDisable(false);
				nom_medicament.setText(ordonnance.getSelectionModel().getSelectedItem().getMedecament());
				forme.setValue(ordonnance.getSelectionModel().getSelectedItem().getForme());
				dosage.setValue(ordonnance.getSelectionModel().getSelectedItem().getDosage());
				quant.setValue(ordonnance.getSelectionModel().getSelectedItem().getQuantite());
				duree.setText(ordonnance.getSelectionModel().getSelectedItem().getDuree_trait());
				commentaire.setText(ordonnance.getSelectionModel().getSelectedItem().getCommentaire());
				Ordonnace oldMedicament = new Ordonnace(
								ordonnance.getSelectionModel().getSelectedItem().getMedecament(),
								ordonnance.getSelectionModel().getSelectedItem().getForme(),
								ordonnance.getSelectionModel().getSelectedItem().getDosage(),
								ordonnance.getSelectionModel().getSelectedItem().getQuantite(),
								ordonnance.getSelectionModel().getSelectedItem().getDuree_trait(),
								ordonnance.getSelectionModel().getSelectedItem().getCommentaire());
				
				commentaire.setOnKeyPressed(evevt->{
					if (evevt.getCode()==KeyCode.ENTER){
						Ordonnace newMedicament = new Ordonnace(
								nom_medicament.getText(),
								forme.getValue(),
								dosage.getValue(),
								quant.getValue(),
								duree.getText(),
								commentaire.getText());
						for (Ordonnace med : list_medicaent) {
							if (med.getMedecament().equals(oldMedicament.getMedecament())&& med.getForme().equals(oldMedicament.getForme())&&
									med.getDosage().equals(oldMedicament.getDosage())) {
								list_medicaent.remove(med);
								list_medicaent.add(newMedicament);
								ordonnance.setItems(list_medicaent);
								nom_medicament.setText("");
								forme.setValue("");
								dosage.setValue("");
								quant.setValue(0);
								duree.setText("");
								commentaire.setText("");
								
							}
						}
						
					}
				});
								
			}
		});
	}
//=======================================================================================================================================
	public void souvgarde() throws SQLException {
		impremer.setDisable(false);
		 ajouter_Ordonnance();
		 ObservableList<Ordonnace> contenu= ordonnance.getItems();
		 for (Ordonnace content : contenu) {
			ResultSet medicament_id =  OrdonnanceTable.get_Medicament_ID(content);
			medicament_id.next();
			med_ID  = medicament_id.getInt(1);
			content.setN_ord(num_Ord);
			System.err.println("======"+med_ID+"=====");
			OrdonnanceTable.insert_Into_Contientmedicament_Table(med_ID,content);
			System.out.println("insert a new row ");
			
		}
		 	nom.setText("");
		 	dateofbirth.setValue(null);
		 	nom_medicament.setText("");
			forme.setValue("");
			dosage.setValue("");
			quant.setValue(0);
			duree.setText("");
			commentaire.setText("");
			notification = new NotificationControler();
			notification.insertion_Notification(insertMessage);
		 
	}

//=======================================================================================================================================	
	public void ajouter_Ordonnance(){
		
		lastName = nom.getText();
		
		person = new Patients();
		date = dateofbirth.getValue().toString();

		for (Patients patients2 : possibleName) {
			if (patients2.toString().equals(lastName)&& patients2.getDateOfBirth().equals(date)) {
				person = patients2;
			}
		}
		now = LocalDate.now();
		birthdate = dateofbirth.getValue();
		age  = ChronoUnit.YEARS.between(birthdate, now);
		try {
			int p_id = PatientTable.getPatientID_for_Ordonnance(person,date);
			OrdonnanceTable.insert_Into_Ordonnance_Table(p_id);
			ResultSet list;
			list = OrdonnanceTable.get_Ordonnance_Num();
			if (list.next()) 
				num_Ord = list.getInt(1);
		} catch (SQLException e) {e.printStackTrace();}
	}
//======================================================================================================================================
	public void fermer(ActionEvent event){
		((Node)event.getSource()).getScene().getWindow().hide();  
	}
	
//======================================================================================================================================		
	public void remplir_Dosage_comboBox(){
		forme.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
		            try {
		            	if (!forme.getValue().equals("")) {
		            		dosage.setDisable(false);
		            		ResultSet result_list = OrdonnanceTable.select_Dosage(nom_medicament.getText(),
		            															  forme.getValue());
		            		list_dosages.clear();
		        			while (result_list.next()) {list_dosages.add(result_list.getString(1));}
		        			result_list.close();
		        			dosage.setItems(list_dosages);
						}
					} catch (SQLException e) {e.printStackTrace();}
		        }
			}
		});
	}
//======================================================================================================================================		
	public void remplir_quantite_comboBox(){
		for (int i = 1; i <=10; i++) {list_quantite.add(i);}
		quant.setItems(list_quantite);
	}

//======================================================================================================================================		
	public void ajoute_medicament_to_list(){
		commentaire.setOnKeyPressed(event->{
			if (event.getCode()==KeyCode.ENTER) {
				list_medicaent.add(new Ordonnace(
										nom_medicament.getText(),
										forme.getValue(),
										dosage.getValue(),
										quant.getValue(),
										duree.getText(),
										commentaire.getText()));
				nom_medicament.setText("");
				forme.setValue("");
				dosage.setValue("");
				quant.setValue(0);
				duree.setText("");
				commentaire.setText("");
				souvegarde.setDisable(false);
			}
			
		});
	}
//======================================================================================================================================
	public void remplir_liste_des_formes(){
		ResultSet result_list;
		try {
			result_list = OrdonnanceTable.select_Forme();
			while (result_list.next()) {
				formeItems.add(result_list.getString(1));
			}
			result_list.close();
		} catch (SQLException e1) {e1.printStackTrace();}
		forme.setItems(formeItems);
	}
//======================================================================================================================================		
	public void remplir_AutoCmplete_List_Medicaments(){
		ResultSet by_name_result;
		try {
			by_name_result = OrdonnanceTable.select_scientific_name();
			while (by_name_result.next()) {possibleMedicament.add(by_name_result.getString(1));}
			by_name_result.close();
		} catch (SQLException e) {e.printStackTrace();}
		
		medicament_Autocomplete = TextFields.bindAutoCompletion(nom_medicament, possibleMedicament);
	}
//======================================================================================================================================
	public void remplir_AutoCmplete_List_Patients(){
		 try {
				ResultSet result_list = PatientTable.show_Patient_Table();
				while (result_list.next()) { 
					Patients patients = new Patients(
							  result_list.getInt(1),
							  result_list.getString(2),
							  result_list.getString(3),
							  result_list.getString(4),
							  result_list.getString(5),
							  result_list.getString(6),
							  result_list.getString(7),
							  result_list.getString(8));
					possibleName.add(patients);
				}
			} catch (SQLException e) {e.printStackTrace();}
		
		name_Autocomplete = TextFields.bindAutoCompletion(nom, possibleName);
		
	}
	//======================================================================================================================================
		public void remplir_forme_par_medicament(){
			nom_medicament.focusedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if(!newValue) {
			            try {
			            	if (!nom_medicament.getText().equals("")) {
			            		forme.setDisable(false);
			            		ResultSet list_selected = OrdonnanceTable.select_Forme(nom_medicament.getText());
			            		formeItems.clear();
			            		while (list_selected.next()) {
									formeItems.add(list_selected.getString(1));
								}
			            		list_selected.close();
			            		forme.setItems(formeItems);
			            		
							}
			            	
						} catch (SQLException e) {e.printStackTrace();}
			        }
					
				}
			});
		}
//======================================================================================= 
    public void imprimer() throws DocumentException, IOException {
		souvegarde.setDisable(true);
    	Document pdfDocument = new Document(PageSize.A5);
		PdfWriter.getInstance(pdfDocument, new FileOutputStream("Ordonance.pdf"));
		pdfDocument.open();
		pdfDocument.add(new Paragraph("La santé, c'est la jeunesse, car la maladie peut faire un vieillard d'un jeune homme",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.BOLD)));
		pdfDocument.add(new Paragraph("____________________________________________________"));
		pdfDocument.add(new Paragraph("Ordonnance\n",FontFactory.getFont(FontFactory.TIMES_ROMAN,16,Font.BOLD)));
		pdfDocument.add(new Paragraph("Nom : "+lastName,FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.BOLD)));
		pdfDocument.add(new Paragraph("Âge : "+age+"",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.BOLD)));
		pdfDocument.add(new Paragraph("Date : "+now.toString(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.BOLD)));
		pdfDocument.add(new Paragraph("____________________________________________________"));
		// PdfPTable tableName = new PdfPTable(int NumberofColumns);
		PdfPTable table = new PdfPTable(6);
		
		
		pdfDocument.add(new Paragraph("                                        "));
		BaseFont bf = BaseFont.createFont(
                BaseFont.TIMES_ROMAN,
                BaseFont.CP1252,
                BaseFont.EMBEDDED);
		Font fontheader = new Font(bf, 8);
        Font font = new Font(bf, 5);
        PdfPCell cell = new PdfPCell(new Phrase("List des médicament",fontheader));
		
		cell.setColspan(12);
		cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
		cell.setVerticalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.decode("#5C6BC0"));
		table.addCell(cell);
		table.setWidthPercentage(100);
		table.addCell(new Phrase("Medicament", fontheader));
		table.addCell(new Phrase("Forme", fontheader));
		table.addCell(new Phrase("Dosage", fontheader));
		table.addCell(new Phrase("Quantité", fontheader));
		table.addCell(new Phrase("Durée de Traitement", fontheader));
		table.addCell(new Phrase("Commentaire", fontheader));
	
		
		
		
		ObservableList<Ordonnace> contenu= ordonnance.getItems();
		 for (Ordonnace content : contenu) {
			 	table.addCell(new Phrase(content.getMedecament()+"", font));
				table.addCell(new Phrase(content.getForme(), font));		
				table.addCell(new Phrase(content.getDosage(), font));
				table.addCell(new Phrase(content.getQuantite()+"", font));
				table.addCell(new Phrase(content.getDuree_trait()+"", font));		
				table.addCell(new Phrase(content.getCommentaire(), font));
			
			
		}
		
		pdfDocument.add(table);
		pdfDocument.add(new Paragraph("                           "));
		Paragraph signature = new Paragraph("Signature\n",fontheader);
		signature.setAlignment(Element.ALIGN_RIGHT);
		pdfDocument.add(signature);		pdfDocument.close();
		
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"Ordonance.pdf");
		
		
    	
    	
	}

}
