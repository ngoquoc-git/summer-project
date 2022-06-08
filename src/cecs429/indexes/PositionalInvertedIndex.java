package cecs429.indexes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PositionalInvertedIndex implements Index{
    private HashMap<String, List<Posting>> anindex = new HashMap<>();
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
