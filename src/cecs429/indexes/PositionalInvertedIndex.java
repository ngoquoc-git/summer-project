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
    private ArrayList<Integer> position;
    public PositionalInvertedIndex(HashMap<String,List<Posting>> aindex, ArrayList<Integer> pos){
        anindex = aindex;
        position = pos;

    }
    public void addTerm(String term, int docId,ArrayList<Integer> pos ){


    }
    @Override
    public List<Posting> getPostings(String term) {
        return null;
    }

    @Override
    public List<String> getVocabulary() {
        return null;
    }
}
