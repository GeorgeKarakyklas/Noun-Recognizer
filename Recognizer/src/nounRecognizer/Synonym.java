package nounRecognizer;

import edu.smu.tspell.wordnet.*;

public class Synonym {
	/**
	 * Main entry point. The command-line arguments are concatenated together
	 * (separated by spaces) and used as the word form to look up.
	 */
	public static void main(String[] args)
	{

	}
	
	public static void findSynonyms(String word) {
		System.setProperty("wordnet.database.dir", "C://Users//sebal//Desktop//to-G+A//dict");
		String wordForm = word;
		//  Get the synsets containing the word form
		WordNetDatabase database = WordNetDatabase.getFileInstance();
		Synset[] synsets = database.getSynsets(wordForm);
		//  Display the word forms and definitions for synsets retrieved
		if (synsets.length > 0)
		{
			System.out.println("Synonyms for '" +
					wordForm + "':");
			for (int i = 0; i < synsets.length; i++)
			{	
				System.out.println("");
				String[] wordForms = synsets[i].getWordForms();
				for (int j = 0; j < wordForms.length; j++)
				{
					System.out.print((j > 0 ? ", " : "") +
							wordForms[j]);
				}
				//System.out.println(": " + synsets[i].getDefinition());
				
			}
			System.out.println("\n");
		}
		else
		{
			System.err.println("No synsets exist that contain " +
					"the word form '" + wordForm + "'");
		}
	}
	
}
