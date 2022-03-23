package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dictionary {
	private Set<String> dizionario;
	
	
	public void loadDictionary(String language) {
		
		dizionario=new HashSet<String>();

		if(language.compareTo("English")==0) {
			
			try {
				FileReader fr = new FileReader("src/main/resources/English.txt");
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
		List<RichWord> rw= new ArrayList<RichWord>();
		
		for(String s:inputTextList) {
			RichWord r;
			if(dizionario.contains(s)) {
				r=new RichWord(s,true);			
			}
			else {
				r=new RichWord(s,false);
			}
			rw.add(r);
		}
		return rw;
	}

	
	
	public Set<String> getDizionario() {
		return dizionario;
	}

	public void setDizionario(Set<String> dizionario) {
		this.dizionario = dizionario;
	}

	public void clear() {
		dizionario.clear();
	}
	
	


}
