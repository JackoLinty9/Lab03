package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtInserito;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtErrori;

    @FXML
    private Label cntErrori;

    @FXML
    private Button btnClearText;
    
    @FXML
    private ComboBox<String> boxLingua;

    @FXML
    private Label txtTempo;

    @FXML
    void doClearText(ActionEvent event) {
    	
    	txtInserito.clear();
    	txtErrori.clear();
    	txtTempo.setText("Time spent on SpellCheck");
    	cntErrori.setText("Number of errors");
    	
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	double start= System.nanoTime();
    	
    	String inserito=txtInserito.getText();
    	String inseritoClean=inserito.replaceAll("[.,\\/#!$%\\*;:{}=\\-_'()\\[\\]\"]" , "");
    	String[] ins=inseritoClean.split(" ");
    	
    	LinkedList<String>listaParole=new LinkedList<String>();
    	for(String s : ins)
    		listaParole.add(s);
    	
    	LinkedList<RichWord>listaErrate=new LinkedList<RichWord>(model.spellCheckText(listaParole));
    	
    	double stop = System.nanoTime();
    	txtTempo.setText("SpellCheck completed in "+ (stop-start) +" ns");

    	String risultato = "";
    	for(RichWord rw : listaErrate) {
    		risultato = risultato +rw.getParola()+"\n";
    	}
    	
    	txtErrori.setText(risultato);    	
    	cntErrori.setText("The text contains "+listaErrate.size()+" wrong words");
    	
    }
    
    @FXML
    void handleLingua(ActionEvent event) {
    	
    	String lingua =boxLingua.getValue();
    	model.loadDictionary(lingua);
    	
    	this.btnClearText.setDisable(false);
		this.btnSpellCheck.setDisable(false); 
		txtInserito.setEditable(true);
		
    }

    @FXML
    void initialize() {
    	assert boxLingua != null : "fx:id=\"boxLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInserito != null : "fx:id=\"txtInserito\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cntErrori != null : "fx:id=\"cntErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Dictionary model) {
		this.model = model;
		boxLingua.getItems().addAll("Italiano","Inglese");
	}

}


