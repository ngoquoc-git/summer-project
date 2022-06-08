package cecs429.indexes;

import java.util.*;


public class InvertedIndex implements Index {
    private HashMap<String, List<Posting>> mindex = new HashMap<>();

    public void addTerm(String term, int docId) {
        List<Posting> Plist = mindex.get(term);
        //check if the index contains the term already.

        if (mindex.containsKey(term)) {
            //If it does! Check the last term.
            if (Plist.get(Plist.size() - 1).getDocumentId() != docId) {
                List<Posting> Plist2 = getPostings(term);

                Posting newTerm = new Posting(docId, new ArrayList<Integer>());

                Plist2.add(newTerm);
                mindex.put(term, Plist2);

            }else{
                List<Posting> Plist2 = getPostings(term);
                Posting p = Plist2.get(Plist2.size()-1);
                //Add position p

            }


        }else{
            //If the map does not contain the term.
            //Then we add it.
            List<Posting> Plist2 = new ArrayList<Posting>();
            Posting newTerm = new Posting(docId, new ArrayList<Integer>());
            Plist2.add(newTerm);
            mindex.put(term, Plist2);


    }

    }

    @Override
    public List<Posting> getPostings(String term) {
        //Check if the index contains the term
        // if it does! return the postings for the term
        if(mindex.containsKey(term)){
            return mindex.get(term);
        }else{
            // if not! return an empty postings.
            return new ArrayList<Posting>();
        }

    }

    @Override
    public List<String> getVocabulary() {
     Set<String> Keys = mindex.keySet();
     List<String> vocab = new ArrayList<String>();
     for (String words : Keys){
         vocab.add(words);
         Collections.sort(vocab);

     }
    return vocab;

}

}



