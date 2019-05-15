package nounRecognizer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class Noun{
	
	public static void main(String[] args, String content) {
	
}
	
	public static void findTerms(String content) {
		try {
	        InputStream modelIn = new FileInputStream("en-ner-location.bin");
	        TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
	        NameFinderME nameFinder = new NameFinderME(model);
	        String[] sentence = content.split(" ");

	            Span nameSpans[] = nameFinder.find(sentence);
	            for(Span s: nameSpans) {
	                //System.out.println("Name Entity : "+s.toString());
	                for(int i=s.getStart(); i<s.getEnd(); i++) {
	               // System.out.println(sentence[i]);
	                Synonym.findSynonyms(sentence[i]);
	                }
	            }
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
}
