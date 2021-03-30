package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
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
	
//	public List<RichWord> spellCheckText(List<String> inputTextList){
//		
//		LinkedList<RichWord>errate=new LinkedList<RichWord>();
//		
//		for( String parola:inputTextList)
//				if(!dictionary.contains(parola.toLowerCase())) {
//					RichWord daAgg=new RichWord(parola,false);
//					errate.add(daAgg);
//				}	
//	
//		return errate;
//	}
	
//  public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
//	
//		LinkedList<RichWord>errate=new LinkedList<RichWord>();
//		
//		for( String parola:inputTextList) { //data una parola della lista in output
//			for( String parolaD:dictionary) 
//				if(parolaD.toLowerCase().equals(parola.toLowerCase())) { //confronto ogni parola del dictionary con la parola estratta dalla lista
//					
	                //se sono uguali aggiungo parola alla lista errate
//					RichWord rw=new RichWord(parola,false);
//					errate.add(rw);
//				}
//		}
	
//		return errate;
//  }
 
    public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){
    	
    	LinkedList<RichWord>errate=new LinkedList<RichWord>();
        RichWord parola;
        int index;

	    for(String s : inputTextList) {
	    	parola = new RichWord(s,false);
		    index = Collections.binarySearch(dictionary, s.toLowerCase()); // Cerca se nella lista è presente la stringa,
		                                                                   // se sì, restituisce l'indice in cui l'ha trovata
		                                                                   // se no, restituisce indice negativo
		    if(index>=0) //solo se è stata trovata la parola
		    	parola.setCorretta();

		    if(!parola.isCorretta())
		    	errate.add(parola);
	    }
	    
	    return errate;
}

}
