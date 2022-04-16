/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.LinkedList;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private Dictionary dic=new Dictionary();
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbLingue"
    private ComboBox<String> cmbLingue; // Value injected by FXMLLoader


    @FXML // fx:id="lblErrori"
    private Label lblErrori; // Value injected by FXMLLoader

    @FXML // fx:id="lblTempo"
    private Label lblTempo; // Value injected by FXMLLoader

    @FXML // fx:id="txtInput"
    private TextArea txtInput; // Value injected by FXMLLoader

    @FXML // fx:id="txtOutput"
    private TextArea txtOutput; // Value injected by FXMLLoader



    @FXML
    void doClearText(ActionEvent event) {
    	dic.clear();
    	txtInput.clear();
    	txtOutput.clear();
    	lblErrori.setText("");
    	lblTempo.setText("");

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	long start,end;
    	
 	
    	
    	lblErrori.setText("");
    	
    	txtOutput.clear();
    	start=System.currentTimeMillis();
    	
    	String input=txtInput.getText().toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]","");
    	String arrayParole[];
    	String lingua=cmbLingue.getValue();
    	
    	dic.loadDictionary(lingua);
    	
    	
    	
    	arrayParole=input.split(" ");
    	List<String> l=new LinkedList<String>();
    	List<RichWord> res=new LinkedList<RichWord>();
    	
    	for(String s:arrayParole) {
    		l.add(s);
    	}
    	
    	res=dic.spellCheckTextDichotomic(l);
    	end=System.currentTimeMillis();
    	int cont=0;
    	for(RichWord r:res) {
    		if(r.isCorretto()==false) {
    			txtOutput.appendText(r.toString()+"\n");
    			cont++;
    		}
    		
    	}
    	
    	
    	lblErrori.setText("Ci sono "+ cont+ " errori");
    	
    	lblTempo.setText("Spell check completed in "+ (end-start)*0.001 +" s" );
    }
    
     
    public void setModel(Dictionary dic) {
    	this.dic=dic;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbLingue != null : "fx:id=\"cmbLingue\" was not injected: check your FXML file 'Scene.fxml'.";      
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErrori != null : "fx:id=\"lblErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTempo != null : "fx:id=\"lblTempo\" was not injected: check your FXML file 'Scene.fxml'.";

        cmbLingue.getItems().clear();
        cmbLingue.getItems().add("Italian");
        cmbLingue.getItems().add("English");
        
        
        		
    }
   

}



