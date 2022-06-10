package cecs429.indexes;

import java.util.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
/**
 * Implements an Index using an Positional Inverted Index. Requires knowing the full corpus vocabulary,number of documents,and Positions where each term is found in each document.
 * prior to construction.
 */
public class PositionalInvertedIndex implements Index{
    private HashMap<String, List<Posting>> anindex = new HashMap<String, List<Posting>>();
   // private ArrayList<Integer> position;
    public PositionalInvertedIndex(HashMap<String,List<Posting>> aindex){
        anindex = aindex;
      //  position = pos;

    }
    public void addTerm(String term, int docId,int pos ){
        List<Posting> postList = anindex.get(term);
        if(anindex.containsKey(term)){
            if(postList.get(postList.size()-1).getDocumentId() != docId) {
                List<Posting> postingList1 = getPostings(term);
                ArrayList<Integer> pList = new ArrayList<Integer>();
                pList.add(pos);
                postingList1.add(new Posting(docId,pList));
                anindex.put(term, postingList1);


            }else{
                List<Posting>
            }
        }


    }
    @Override
    public List<Posting> getPostings(String term) {
        if(anindex.containsKey(term)){
            return anindex.get(term);
        }
        return null;
    }

    @Override
    public List<String> getVocabulary() {
    Set<String> appendix = anindex.keySet();
    List<String> vocabulary = new ArrayList<String>();
    for (String Lt : appendix){
        vocabulary.add(Lt);
    }
    Collections.sort(vocabulary);
    return Collections.unmodifiableList(vocabulary);

     }
}
