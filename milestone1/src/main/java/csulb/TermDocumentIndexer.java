package csulb;
/*
import cecs429.documents.DirectoryCorpus;
import cecs429.documents.Document;
import cecs429.documents.DocumentCorpus;
import cecs429.indexes.Index;
import cecs429.indexes.TermDocumentIndex;
import cecs429.indexes.Posting;
//import cecs429.text.BasicTokenProcessor;
import cecs429.text.EnglishTokenStream;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
//DOESNT WORK UNLESS TOKEN INFERFACE CHANGED
public class TermDocumentIndexer {
	public static void main(String[] args) {
		// Create a DocumentCorpus to load .txt documents from the project directory.
		DocumentCorpus corpus = DirectoryCorpus.loadTextDirectory(Paths.get("/Users/berry/Desktop/CECS429/SearchEngineProject/MobyDick10Chapters").toAbsolutePath(), ".txt");
		// Index the documents of the corpus.
		Index index = indexCorpus(corpus) ;
		Scanner scan = new Scanner(System.in);
		// We aren't ready to use a full query parser; for now, we'll only support single-term queries.
		//String query = "whale"; // hard-coded search for "whale"
		System.out.println("Enter a term: ");
		String query = scan.nextLine();
		for (Posting p : index.getPostings(query)) {
			System.out.println("Document " + corpus.getDocument(p.getDocumentId()).getTitle());
		}

		// TODO: fix this application so the user is asked for a term to search.
	}
	
	private static Index indexCorpus(DocumentCorpus corpus) {
		HashSet<String> vocabulary = new HashSet<>();
		
		BasicTokenProcessor processor = new BasicTokenProcessor();
		
		// First, build the vocabulary hash set.
		for (Document d : corpus.getDocuments()) {
			System.out.println("Found document " + d.getTitle());
			EnglishTokenStream stream = new EnglishTokenStream(d.getContent());
			for(String token : stream.getTokens()){
			//get 1 word at a time
				String term = processor.processToken(token);
				vocabulary.add(term); //required for matrix because must know 
			}
		}		
		TermDocumentIndex  index = new TermDocumentIndex(vocabulary, corpus.getCorpusSize());
		for (Document d : corpus.getDocuments()) {
			EnglishTokenStream stream = new EnglishTokenStream(d.getContent());
			for(String token : stream.getTokens()){
			//get 1 word at a time
			String term = processor.processToken(token);
			index.addTerm(term,d.getId()); //required for matrix because must know 
			}
		}
		return index;

			// TODO:
			// Tokenize the document's content by constructing an EnglishTokenStream around the document's content.
			// Iterate through the tokens in the document, processing them using a BasicTokenProcessor,
	//		and adding them to the HashSet vocabulary.
		
		// TODO:
		// Constuct a TermDocumentMatrix once you know the size of the vocabulary.
		// THEN, do the loop again! But instead of inserting into the HashSet, add terms to the index with addPosting.
	
	}

}
*/