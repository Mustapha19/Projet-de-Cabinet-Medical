package application.ControlPanal.GestioDesOrdonnances.viewOdonnances.ordonnanceSelected;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
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

import application.ControlPanal.GestioDesOrdonnances.Ordonnace;
import application.ControlPanal.GestioDesOrdonnances.OrdonnanceTable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class OrdonnanceSelectedController implements Initializable {
	
	@FXML private AnchorPane ordonnance_root;
	@FXML private TableView<Ordonnace> ordonnance;
	@FXML private TableColumn<Ordonnace, String> medecament;
	@FXML private TableColumn<Ordonnace, String> medicament_forme;
	@FXML private TableColumn<Ordonnace, String> medicament_dosage;
	@FXML private TableColumn<Ordonnace, Integer> medicament_quantite;
	@FXML private TableColumn<Ordonnace, String> dureeDeTrait;
	@FXML private TableColumn<Ordonnace, String> comment;
	@FXML private JFXButton impremer;
	@FXML private JFXButton fermer;
	@FXML private Label display_name;
	@FXML private Label display_date;
	@FXML private Label display_age;
	private ObservableList<Ordonnace> list_medicament = FXCollections.observableArrayList();
	int num_Ord;
	
  //======================================================================================= 
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	medecament.setCellValueFactory(new PropertyValueFactory<Ordonnace, String>("medecament"));
		medicament_forme.setCellValueFactory(new PropertyValueFactory<Ordonnace, String>("forme"));
		medicament_dosage.setCellValueFactory(new PropertyValueFactory<Ordonnace, String>("dosage"));
		medicament_quantite.setCellValueFactory(new PropertyValueFactory<Ordonnace, Integer>("quantite"));
		dureeDeTrait.setCellValueFactory(new PropertyValueFactory<Ordonnace, String>("duree_trait"));
		comment.setCellValueFactory(new PropertyValueFactory<Ordonnace, String>("commentaire"));
		MultipleSelectionModel<Ordonnace> selected = ordonnance.getSelectionModel();
		selected.selectedItemProperty().addListener(new ChangeListener<Ordonnace>() {

			@Override
			public void changed(ObservableValue<? extends Ordonnace> observable, Ordonnace oldValue,
					Ordonnace newValue) {
				System.out.println(ordonnance.getSelectionModel().getSelectedItem().getMedecament());
				
			}
		});
	}
    
 //======================================================================================= 
    @FXML
    void fermer(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
    }
 //=======================================================================================   
    public void set_Ordonnanace_Info(int numORD,String nom,String prenom,String age,String date) throws SQLException {
    	num_Ord = numORD;
    	display_name.setText("Nom & Prénom : "+nom+" "+prenom);
    	display_age.setText("Âge : "+age);
    	display_date.setText("Date :"+date);		
		ResultSet ordwithconnt_Med = OrdonnanceTable.select_Join_Ord_with_ConMed(num_Ord);
		while (ordwithconnt_Med.next()) {
			list_medicament.add(new Ordonnace(
								ordwithconnt_Med.getInt(1),
								ordwithconnt_Med.getString(3),
								ordwithconnt_Med.getString(4),
								ordwithconnt_Med.getString(5),
								ordwithconnt_Med.getInt(6),
								ordwithconnt_Med.getString(7),
								ordwithconnt_Med.getString(8)
					));
		}
		ordwithconnt_Med.close();
		ordonnance.setItems(list_medicament);
	} 
  //======================================================================================= 
    public void imprimer() throws DocumentException, IOException {
		
    	Document pdfDocument = new Document(PageSize.A5);
		PdfWriter.getInstance(pdfDocument, new FileOutputStream("Ordonance.pdf"));
		pdfDocument.open();
		pdfDocument.add(new Paragraph("La santé, c'est la jeunesse, car la maladie peut faire un vieillard d'un jeune homme",FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.BOLD)));
		pdfDocument.add(new Paragraph("____________________________________________________"));
		pdfDocument.add(new Paragraph("Ordonnance\n",FontFactory.getFont(FontFactory.TIMES_ROMAN,16,Font.BOLD)));
		pdfDocument.add(new Paragraph(display_name.getText(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.BOLD)));
		pdfDocument.add(new Paragraph(display_age.getText(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.BOLD)));
		pdfDocument.add(new Paragraph(display_date.getText(),FontFactory.getFont(FontFactory.TIMES_ROMAN,8,Font.BOLD)));
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
		pdfDocument.add(signature);
		pdfDocument.close();
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"Ordonance.pdf");
	}
}
