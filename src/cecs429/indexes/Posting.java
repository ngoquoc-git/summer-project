package cecs429.indexes;

import java.util.*;
/**
 * A Posting encapulates a document ID and a Positions List associated with a search query component.
 */
public class Posting {
	private int mDocumentId;
	private ArrayList<Integer> mPosting;

	/**
	 * Constructs an empty posting with with given document ID and Posting List.
	 * @param documentId document number.
	 * @param post the position of a token in a document.
	 */
	public Posting(int documentId,ArrayList<Integer> post) {
		mPosting = post;
		mDocumentId = documentId;

	}
	/**
	 * returns the postion of a token in a document.
	 * @return mPosting - position of a token in a document.
	 */
	public ArrayList<Integer> getPosition(){
		return mPosting;
	}
	
	public int getDocumentId() {
		return mDocumentId;
	}
	/**
	 * adds the position of a token into the postings list
	 * sorts the list
	 */
	public void addPost(int post){
		mPosting.add(post);
		Collections.sort(mPosting);

	}
}

