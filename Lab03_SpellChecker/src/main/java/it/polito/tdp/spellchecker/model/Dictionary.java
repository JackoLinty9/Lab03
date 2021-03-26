package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	LinkedList<String>dictionary=new LinkedList<String>();
	
	public void loadDictionary(String language) {
		//se è italiano
		if(language.toLowerCase().equals("italiano")) {
		try {
			FileReader fr= new FileReader("src/main/resources/Italian.txt");
			BufferedReader br= new BufferedReader(fr);
			String word;
			while((word=br.readLine())!=null) {
				dictionary.add(word);
			}
			br.close();
			fr.close();
		}
		catch (IOException ioe) {
			System.out.println("Errore nella lettura del file");
		}
		}
		//se è inglese
		if(language.toLowerCase().equals("inglese")) {
		try {
			FileReader fr= new FileReader("src/main/resources/English.txt");
			BufferedReader br= new BufferedReader(fr);
			String word;
			while((word=br.readLine())!=null) {
				dictionary.add(word);
			}
			br.close();
			fr.close();
		}
		catch (IOException ioe) {
			System.out.println("Errore nella lettura del file");
		}
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		LinkedList<RichWord>errate=new LinkedList<RichWord>();
		
		for( String parola:inputTextList)
				if(!dictionary.contains(parola.toLowerCase())) {
					RichWord daAgg=new RichWord(parola,false);
					errate.add(daAgg);
				}	
	
		return errate;
	}
}
