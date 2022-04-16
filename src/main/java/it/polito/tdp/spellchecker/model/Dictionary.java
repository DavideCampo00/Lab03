package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javafx.beans.property.ReadOnlyIntegerWrapper;

public class Dictionary {
	private List<String> dizionario;
	
	
	public void loadDictionary(String language) {
		
		dizionario=new LinkedList<String>();

		if(language.compareTo("English")==0) {
			
			try {
				FileReader fr = new FileReader("src/main/resources/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					dizionario.add(word.toLowerCase());
				}
				Collections.sort(dizionario);
				br.close();
			} catch (IOException e){
				System.out.println("Errore nella lettura del file");
			}
		}
		else {
			
			try {
				FileReader fr = new FileReader("src/main/resources/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					dizionario.add(word);
				}
				br.close();
			} catch (IOException e){
				System.out.println("Errore nella lettura del file");
			}
			
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> rw= new LinkedList<RichWord>();
		
		for(String s:inputTextList) {
	
			if(dizionario.contains(s.toLowerCase())) {
				rw.add(new RichWord(s,true));		
			}
			else {
				rw.add(new RichWord(s,false));
			}
			
		}
		return rw;
	}
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		List<RichWord> rw= new LinkedList<RichWord>();
		

		for(String s:inputTextList) {
			boolean trovata=false;
			for(String s2:dizionario) {
				if(s.equals(s2)) {
					trovata=true;
					rw.add(new RichWord(s,true));
				}				
			}
			if(trovata==false)
			 rw.add(new RichWord(s,false));
		}
		return rw;
	}
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList) {

		List<RichWord> parole = new LinkedList<RichWord>();
		//List<RichWord> parole= new LinkedList<RichWord>();

		for (String str : inputTextList) {

			
			if (binarySearch(str.toLowerCase()))
				parole.add(new RichWord(str,true));
			else
				parole.add(new RichWord(str,false));
		
		}

		return parole;
	}

	private boolean binarySearch(String stemp) {
		int inizio = 0;
		int fine = dizionario.size();

		while (inizio != fine) {
			int medio = inizio + (fine - inizio) / 2;
			if (stemp.compareToIgnoreCase(dizionario.get(medio)) == 0) {
				return true;
			} else if (stemp.compareToIgnoreCase(dizionario.get(medio)) > 0) {
				inizio = medio + 1;
			} else {
				fine = medio;
			}
		}

		return false;
	}
	
	


	
	public List<String> getDizionario() {
		return dizionario;
	}

	public void setDizionario(List<String> dizionario) {
		this.dizionario = dizionario;
	}

	public void clear() {
		dizionario.clear();
	}
	
	


}
