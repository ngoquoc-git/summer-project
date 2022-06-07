package java.cecs429.queries;

import java.cecs429.indexes.Index;
import java.cecs429.indexes.Posting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An AndQuery composes other QueryComponents and merges their postings in an intersection-like operation.
 */
public class AndQuery implements QueryComponent {
	private List<QueryComponent> mComponents;

	
	public AndQuery(List<QueryComponent> components) {
		mComponents = components;
	}
	
	@Override
	public List<Posting> getPostings(Index index) {
		
            List<Posting> results = new ArrayList<>();
            List<Posting> p0 = new ArrayList<>();
            List<Posting> p1 = new ArrayList<>();
           
            int count = mComponents.size() - 1;

            //get postings for first 2 components and check if they are positive or negative
            p0 = mComponents.get(0).getPostings(index);
            p1 = mComponents.get(1).getPostings(index);
            if (mComponents.get(0).isPossitive() == true && mComponents.get(1).isPossitive() == true) {
                results = merge(p0, p1);
            } else {
                if (mComponents.get(0).isPossitive() == false) {
                    results = mergeRev(p1, p0);
                } else {
                    results = mergeRev(p0, p1);
                }
            }

            count = count - 1;
            int k = 2;
            //get next posting and merge it with previous result.
            while (count > 0) {
                List<Posting> p2 = new ArrayList<>();
                p2 = mComponents.get(k).getPostings(index);
                if (mComponents.get(k).isPossitive() == true) {
                    results = merge(results, p2);
                } else {
                    results = mergeRev(results, p2);

                }
                k++;
                count--;
        }

        mComponents.clear();

        return results;
	}

	//Merge two postings using AND operation
	private List<Posting> merge(List<Posting> firstPostings, List<Posting> secondPostings) {

		List<Posting> results = new ArrayList<Posting>();
                
		//starting indices for both postings lists
		int i = 0;
		int j = 0;

		while (i < firstPostings.size() && j < secondPostings.size()) {
			if (firstPostings.get(i).getDocumentId() == secondPostings.get(j).getDocumentId()) {
				results.add(firstPostings.get(i));
				i++;
				j++;
			} 
                        else if (firstPostings.get(i).getDocumentId() < secondPostings.get(j).getDocumentId()) i++;
			else j++;
		}

		return results;
	}
        
        private List<Posting> mergeRev(List<Posting> firstPostings, List<Posting> secondPostings){
            List<Posting> results = new ArrayList<Posting>();
                
            //starting indices for both postings lists
            int i = 0;
            int j = 0;
            
            while (i < firstPostings.size()) {
                if (firstPostings.get(i).getDocumentId() == secondPostings.get(j).getDocumentId()) {
                    i++;
                    j++;
                    if (j == secondPostings.size()) {
                        while (i < firstPostings.size()) {
                            results.add(firstPostings.get(i));
                            i++;
                        }
                    }
                } 
                else if (firstPostings.get(i).getDocumentId() < secondPostings.get(j).getDocumentId()) {
                    results.add(firstPostings.get(i));
                    i++;

                } 
                else {
                    j++;
                    if (j == secondPostings.size()) {
                        while (i < firstPostings.size()) {
                            results.add(firstPostings.get(i));
                            i++;
                        }
                    }
                } 
            }
            return results;
        }
	
	@Override
	public String toString() {
		return
		 String.join(" ", mComponents.stream().map(c -> c.toString()).collect(Collectors.toList()));
	}
        
        @Override
        public Boolean isPossitive(){
            return true;
        }
	
}
