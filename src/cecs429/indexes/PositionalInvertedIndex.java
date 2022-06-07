package java.cecs429.indexes;

import java.cecs429.text.AdvancedTokenProcessor;

import java.util.*;

public class PositionalInvertedIndex implements Index{
    /**
	 * Constructs an empty index with with given vocabulary set and corpus size.
	 * @param vocabulary a collection of all terms in the corpus vocabulary.
	 * @param corpuseSize the number of documents in the corpus.
	 */
    private HashMap<String, List<Posting>>map = new HashMap<String, List<Posting>>();

    @Override
    public List<Posting> getPostings(String term){
   
        if(map.containsKey(term)){

			AdvancedTokenProcessor processor = new AdvancedTokenProcessor();
			String stemmed = processor.stemToken(term);
			return map.get(stemmed);
        }
        else{
            return new ArrayList<Posting>();
        }
    }
    @Override
    public List<String> getVocabulary(){
      
        Set<String> keys = map.keySet();
        List<String> vocabulary = new ArrayList<String>();
        for(String s: keys){
            vocabulary.add(s);      
        }
        Collections.sort(vocabulary);
        return Collections.unmodifiableList(vocabulary);
    }
    public boolean isStringAlphabetic(String s){
        for(int i = 0; i < s.length(); i++){
            if(!Character.isLetter(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
    public void addTerm(List<String> terms, int id, int position) {

		for (String term : terms) {

			List<Posting> postings = map.get(term);

			if (postings == null) {

				postings = createPosting(id, position);
				map.put(term, postings);

			} else {

				int prevDocId = postings.get(postings.size()-1).getDocumentId();
				if (id > prevDocId) {
					Posting posting = new Posting(id);
					posting.addPosition(position);
					postings.add(posting);

				} else if (id == prevDocId) {
					postings.get(postings.size()-1).addPosition(position);
				}

			}

		}

	}
    /**
	 * Create a new posting list object for the index
	 * @param id document id associated with the new posting
	 * @param position term position to store in the new posting
	 * @return a new posting list object
	 */
	private List<Posting> createPosting(int id, int position) {
		List<Posting> postings = new ArrayList<>();
		Posting posting = new Posting(id);//create a new posting
		posting.addPosition(position);
		postings.add(posting);
		return postings;
	}
    @Override
	public List<Posting> getPostingsPositions(String token) {
		AdvancedTokenProcessor processor = new AdvancedTokenProcessor();
		String stemmed = processor.stemToken(token);
		return map.get(stemmed);//index
	}
}