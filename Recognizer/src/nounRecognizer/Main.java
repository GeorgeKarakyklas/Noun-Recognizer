package nounRecognizer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class Main {
	
	public static void main(String[] args) {
		SentenceDetectorME sdetector = null;
		 String TRAINED_SENTENCE_DATA = "C:/Users/(CHANGE ME)/Desktop/Noun-Recognizer-master/en-sent.bin";
		
		try {
			//load the English language sentence model
			InputStream is = new FileInputStream(TRAINED_SENTENCE_DATA); // EnglishSD.bin
			SentenceModel model = new SentenceModel(is);
			sdetector = new SentenceDetectorME(model);
		} catch(Exception e)
		{
			e.printStackTrace();
		}

		try {   	
			//detect sentences from database input
			Class.forName ("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","username","password");
			
			ArrayList<String> content = Database.getAllContent(conn);
			for (int i=0; i<content.size();i++){
			String inputSentences = content.get(i);
			String sentences[] = sdetector.sentDetect(inputSentences);
			for (int j=0; j< sentences.length; j++ ) {
			Noun.findTerms(sentences[j]);
			}
			}

			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

