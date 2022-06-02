package csulb;/*
package edu.csulb;

import cecs429.documents.DirectoryCorpus;
import cecs429.documents.Document;
import cecs429.documents.DocumentCorpus;
import cecs429.indexes.Index;
import cecs429.indexes.InvertedIndex;
import cecs429.indexes.Posting;
//import cecs429.text.BasicTokenProcessor;
import cecs429.text.EnglishTokenStream;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
//DOESNT WORK UNLESS TOKEN INFERFACE CHANGED
public class InvertedIndexIndexer {

	public static void main(String[] args) {
		// Create a DocumentCorpus to load .txt documents from the project directory.
<<<<<<< Updated upstream
		DocumentCorpus corpus = DirectoryCorpus.loadJsonDirectory(Paths.get("/Users/berry/Desktop/CECS429/Search-Engine/demo/src/main/java/all-nps-sites-extracted").toAbsolutePath(), ".txt");
		//DocumentCorpus corpus = DirectoryCorpus.loadJsonDirectory(Paths.get("/Users/berry/Desktop/CECS429/SearchEngineProject/all-nps-sites-extracted").toAbsolutePath(), ".txt");
=======
		DocumentCorpus corpus = DirectoryCorpus.loadTextDirectory(Paths.get("/Users/benji_official/Downloads/MobyDick10Chapters").toAbsolutePath(), ".txt");
>>>>>>> Stashed changes
		// Index the documents of the corpus.
		long startTime = System.nanoTime();
		Index index = indexCorpus(corpus);
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Corpus indexed in: " + totalTime / 1000000000 + " seconds");
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter search query: ");
		// We aren't ready to use a full query parser; for now, we'll only support single-term queries.
		//String query = "whale"; // hard-coded search for "whale"
		String query = scan.nextLine();
		scan.close();
		for (Posting p : index.getPostings(query)) {
			System.out.println("Document " + corpus.getDocument(p.getDocumentId()).getTitle());
		}
	}
	
	private static Index indexCorpus(DocumentCorpus corpus) {
		//HashSet<String> vocabulary = new HashSet<>();
		BasicTokenProcessor processor = new BasicTokenProcessor();
		
		// First, build the vocabulary hash set.	
		InvertedIndex  index = new InvertedIndex();
		for (Document d : corpus.getDocuments()) {
			EnglishTokenStream stream = new EnglishTokenStream(d.getContent());
			for(String token : stream.getTokens()){
			//get 1 word at a time
			//System.out.println(token);
			String term = processor.processToken(token);
			index.addTerm(term,d.getId()); //required for matrix because must know 
			}
		}
		return index;

	}
}
*/
