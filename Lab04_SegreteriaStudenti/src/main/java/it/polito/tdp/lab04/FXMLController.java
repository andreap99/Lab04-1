package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> combobox;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;
    
    @FXML
    private Button btnCompletamento;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnReset;

    @FXML
    void cercaCorsi(ActionEvent event) {
    	this.txtRisultato.clear();
    	List<Corso> corsi = new ArrayList<>();
    	try {
    		String matr = this.txtMatricola.getText();
    		if(matr == null) {
    			this.txtRisultato.appendText("Inserire un numero di matricola");
    			return;
    		}
    		int matricola = Integer.parseInt(matr);
    		Studente s = model.getStudenteByMatricola(matricola);
    		corsi = model.getCorsiByStudente(matricola);
    	}catch(NumberFormatException nfe) {
    		this.txtRisultato.appendText("Inserire un numero di matricola");
    	}catch(NullPointerException npe) {
    		this.txtRisultato.appendText("Questa matricola non esiste");
    	}
    	
    	if(this.combobox.getValue()==null) {
    		for(Corso c : corsi) {
    			this.txtRisultato.appendText(c.toString()+"\n");
    		}
    	}else {
    		if(corsi.contains(this.combobox.getValue())) {
    			this.txtRisultato.appendText("Studente iscritto a questo corso");
    		}else {
    			this.txtRisultato.appendText("Studente non iscritto a questo corso");
    		}
    	}

    }
    
    @FXML
    void cercaMatricola(ActionEvent event) {
    	this.txtRisultato.clear();
    	this.txtNome.clear();
    	this.txtCognome.clear();
    	try{
    		int matricola = Integer.parseInt(this.txtMatricola.getText());
    		Studente s = model.getStudenteByMatricola(matricola);
    		if(s==null) {
    			this.txtRisultato.appendText("Questa matricola non esiste");
    			return;
    		}
    		this.txtNome.setText(s.getNome());
    		this.txtCognome.setText(s.getCognome());
    	}catch(NumberFormatException e) {
    		this.txtRisultato.appendText("Inserire un numero di matricola");
    	}catch(RuntimeException re) {
    		this.txtRisultato.appendText("Errore connessione al database");
    		System.out.println(re.getMessage());
    	}
    	
    }

    @FXML
    void cercaIscritti(ActionEvent event) {
    	this.txtRisultato.clear();
    	Corso c = this.combobox.getValue();
    	if(c==null) {
    		this.txtRisultato.appendText("Nessun corso selezionato");
    		return;
    	}
    	for(Studente s : model.getStudentiIscritti(c)) {
    		this.txtRisultato.appendText(s.toString()+"\n");
    	}
    }

    @FXML
    void iscriviStudente(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {
    	this.txtRisultato.clear();
    	this.txtNome.clear();
    	this.txtCognome.clear();
    	this.txtMatricola.clear();
    }

    @FXML
    void initialize() {
    	assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert combobox != null : "fx:id=\"combobox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCompletamento != null : "fx:id=\"btnCompletamento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		this.combobox.getItems().addAll(model.getTuttiCorsi());
		this.combobox.getItems().add(null);
	}
}
