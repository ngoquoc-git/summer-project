package cecs429.indexes;

import java.util.ArrayList;

/**
 * A Posting encapulates a document ID associated with a search query component.
 */
public class Posting {
	private int mDocumentId;
	private ArrayList<Integer> Plist;


	
	public Posting(int documentId,ArrayList<Integer> Plist2) {
		Plist = Plist2;
		mDocumentId = documentId;

	}
	
	public int getDocumentId() {
		return mDocumentId;
	}
}

