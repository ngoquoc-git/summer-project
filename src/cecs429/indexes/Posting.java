package java.cecs429.indexes;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A Posting encapulates a document ID associated with a search query component.
 */
public class Posting { //Posting consists of document if with a list of positions
	private int mDocumentId;
	private ArrayList<Integer> mPositions; 
	
	public Posting(int documentId, ArrayList<Integer> position) {
		mDocumentId = documentId;
		mPositions = position;
	}
        
	public Posting(int documentId) {

		mDocumentId = documentId;
		mPositions = new ArrayList<>();
	}
        
        public Posting(int docID, int pos) {
            mDocumentId = docID;
            mPositions = new ArrayList<>();
            mPositions.add(pos);
        }
        
	public int getDocumentId() {
		return mDocumentId;
        }
        
	public ArrayList<Integer> getPositions(){
		return mPositions;
	}
        
	public void addPosition(int position){ 
            mPositions.add(position);
            Collections.sort(mPositions);
			
	}
}
