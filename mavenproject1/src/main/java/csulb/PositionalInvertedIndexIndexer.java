package csulb;

import cecs429.documents.DirectoryCorpus;
import cecs429.documents.Document;
import cecs429.documents.DocumentCorpus;
import cecs429.indexes.Index;
import cecs429.indexes.PositionalInvertedIndex;
import cecs429.indexes.Posting;
import cecs429.queries.BooleanQueryParser;
import cecs429.text.AdvancedTokenProcessor;
import cecs429.text.EnglishTokenStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
    
public class PositionalInvertedIndexIndexer {
        
    public static void main(String[] args) throws IOException {
        Index index;
        Scanner scan = new Scanner(System.in);
		System.out.println("What is the path of the directory you would like to index: ");
		String s = scan.nextLine();
        //Path
        //"/Users/berry/Desktop/CECS429/all-nps-sites-extracted"
        //"/Users/berry/Desktop/CECS429/mlb-articles-4000/1"
        // /Users/berry/Desktop/CECS429/testCorpus
        //scan.close();
        // Create a DocumentCorpus to load .txt documents from the project directory.
        DocumentCorpus corpus = DirectoryCorpus.loadJsonDirectory(Paths.get(s).toAbsolutePath(), ".txt");
        index = buildIndex(corpus, s);
        System.out.print("Enter search query: ");
        //String query = "whale"; // hard-coded search for "whale"
        String query = scan.nextLine();
        //
        // MENU
        //
        switch(query){
            case "q":
                System.out.println("Shut down...");
                scan.close();
                return;//end program 
            case "stem":
                AdvancedTokenProcessor processor = new AdvancedTokenProcessor();
                System.out.println(processor.processToken(scan.next()));
                break;
            case "vocab":
                List<String> vocabList = index.getVocabulary(); //make a temp vocab list from vocab
                if(vocabList.size() >= 1000){ //check if vocab has at least 1000 words
                    for(int i = 0; i< 1000; i++){
                        System.out.println(vocabList.get(i)); //output the list
                    }
                }
                else{
                    for(int i = 0; i < vocabList.size(); i++){
                        System.out.println(vocabList.get(i));//output  the list if less than 100 words
                    }
                }
                System.out.println("Total vocabular words: "+ vocabList.size());
                break;
            case "index":
                index = buildIndex(corpus,s);
                
            default:
                //System.out.print("Enter optional query to AND: ");
                //query += scan.nextLine(); //The query becomes the the line entered
                BooleanQueryParser parser = new BooleanQueryParser(); //boolean for terms
                int docCount = 0; //doc counter
                //get the postings of the query after parsing  using index
                for(Posting p: parser.parseQuery(query).getPostings(index)){
                    System.out.println(p.getDocumentId() + ". " + corpus.getDocument(p.getDocumentId()).getTitle());
                    docCount++;
                    System.out.println(p.getPostions());
                }
                System.out.println("Number of Documents: " + docCount);

                //Get document contents the user wants
                System.out.println("Enter Document ID number to view contents or -1 to continue: ");
                int docID = scan.nextInt();
                if(docID>=0){
                    //Get contents of Document user asked for
                    BufferedReader bufferedReader = new BufferedReader(corpus.getDocument(docID).getContent());
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    //use bufferedReader to read each single character in line
					while ((line = bufferedReader.readLine()) != null) {
						stringBuilder.append(line); //building the string
					}
                    String str = stringBuilder.toString(); //the string results
                    System.out.println(str); //display
                    bufferedReader.close(); //close reader
                }
                break;
        }
            // for (Posting p : index.getPostings(query)) {
            //     System.out.println("Document " + corpus.getDocument(p.getDocumentId()).getTitle());
            // }
            scan.close();
        }
        public static Index buildIndex(DocumentCorpus corpus, String path){
            long startTime = System.nanoTime();
            Index index = indexCorpus(corpus);
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            // Create a DocumentCorpus to load .txt documents from the project directory.
            System.out.println("Corpus indexed in: " + totalTime / 1000000000 + " seconds");
            return index;
        }
        private static Index indexCorpus(DocumentCorpus corpus) {
            //HashSet<String> vocabulary = new HashSet<>();
            AdvancedTokenProcessor processor = new AdvancedTokenProcessor();	
            PositionalInvertedIndex  index = new PositionalInvertedIndex();
            // Get all the documents in the corpus by calling GetDocuments().
            Iterable<Document> documents = corpus.getDocuments();
            List<String> wordList = new ArrayList<String>();
            int position = 0;
            for (Document d : documents) {
                EnglishTokenStream stream = new EnglishTokenStream(d.getContent());
                Iterable<String> tokens = stream.getTokens();//convert read data into tokens
                for(String token : tokens){
                    //String term = processor.processToken(token); //get 1 token at a time
                    wordList = processor.processToken(token);
                    //for (int i = 0; i < words.size(); i++) {
                    index.addTerm(wordList,d.getId(),position); //required because must know 
                    position++;
                }
                try{
                    stream.close(); //close stream
                }catch(IOException exe){
                    exe.printStackTrace(); //in case of backlash
                    }
            position = 0; //reset position
            }
            return index;
        }
    }
    
