/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private Dictionary dic;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbLingue"
    private ComboBox<String> cmbLingue; // Value injected by FXMLLoader

    @FXML // fx:id="txtErrori"
    private TextField txtErrori; // Value injected by FXMLLoader

    @FXML // fx:id="txtInput"
    private TextArea txtInput; // Value injected by FXMLLoader

    @FXML // fx:id="txtOutput"
    private TextArea txtOutput; // Value injected by FXMLLoader

    @FXML // fx:id="txtTempo"
    private TextField txtTempo; // Value injected by FXMLLoader

    @FXML
    void doClearText(ActionEvent event) {
    	dic.clear();
    	txtInput.clear();
    	txtOutput.clear();
    	txtErrori.clear();
    	txtTempo.clear();

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	long start,end;
    	start=System.currentTimeMillis();
    	Dictionary dic=new Dictionary();
    	txtErrori.clear();
    	txtOutput.clear();
    	String input=txtInput.getText();
    	String arrayParole[];
    	String lingua=cmbLingue.getValue();
    	dic.loadDictionary(lingua);
    	input.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]","");
    	input.toLowerCase();
    	arrayParole=input.split(" ");
    	List<String> l=new ArrayList<String>();
    	List<RichWord> res=new ArrayList<RichWord>();
    	
    	for(String s:arrayParole) {
    		l.add(s);
    	}
    	res=dic.spellCheckText(l);
    	int cont=0;
    	for(RichWord r:res) {
    		if(r.isCorretto()==false) {
    			txtOutput.appendText(r.toString()+"\n");
    			cont++;
    		}
    		
    	}
    	end=System.currentTimeMillis();
    	txtErrori.setText("Ci sono "+ cont+ " errori");
    	txtTempo.setText("Spell check completed in "+ (end-start)*0.001 +" s" );
    }
    
     
    public void setModel(Dictionary dic) {
    	this.dic=dic;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbLingue != null : "fx:id=\"cmbLingue\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'Scene.fxml'.";

        cmbLingue.getItems().clear();
        cmbLingue.getItems().add("Italian");
        cmbLingue.getItems().add("English");
        		
    }
   

}



