package java.cecs429.queries;

import java.cecs429.indexes.Index;
import java.cecs429.indexes.Posting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An OrQuery composes other QueryComponents and merges their postings with a union-type operation.
 */
public class OrQuery implements QueryComponent {
	// The components of the Or query.
	private List<QueryComponent> mComponents;

	public OrQuery(List<QueryComponent> components){
		mComponents = components;
	}

	@Override
	public List<Posting> getPostings(Index index) {
            List<Posting> results = new ArrayList<Posting>();
            List<Posting> p0 = new ArrayList<>();
            List<Posting> p1 = new ArrayList<>();
            
            p0 = mComponents.get(0).getPostings(index);
            p1 = mComponents.get(1).getPostings(index);
            results = merge(p0, p1);
           
            
            for(int i = mComponents.size() - 1; i > 0; i--){
                int n = 2;
                List<Posting> temp = new ArrayList<>();
                temp = mComponents.get(n).getPostings(index);
                results = merge(results, temp);
                n++;
            }
            return results;
        }
        private List<Posting> merge(List<Posting> firstPostings, List<Posting> secondPostings) {

            List<Posting> result = new ArrayList<Posting>();

            int i = 0;
            int j = 0;

            while (i < firstPostings.size() || j < secondPostings.size()) {
                if (firstPostings.size() == 0) {
                    while (j < secondPostings.size()) {
                        result.add(secondPostings.get(j));
                        j++;
                    }
                } 
                else if (secondPostings.size() == 0) {
                    while (i < firstPostings.size()) {
                        result.add(firstPostings.get(i));
                        i++;
                    }
                } 
                else {
                    if (firstPostings.get(i).getDocumentId() == secondPostings.get(j).getDocumentId()) {
                        result.add(secondPostings.get(j));
                        i++;
                        j++;
                        if (i == firstPostings.size()) {
                            while (j < secondPostings.size()) {
                                result.add(secondPostings.get(j));
                                j++;
                            }
                        } else if (j == secondPostings.size()) {
                            while (i < firstPostings.size()) {
                                result.add(firstPostings.get(i));
                                i++;
                            }
                        }
                    } else if (firstPostings.get(i).getDocumentId() < secondPostings.get(j).getDocumentId()) {
                        result.add(firstPostings.get(i));
                        i++;
                        if (i == firstPostings.size()) {
                            while (j < secondPostings.size()) {
                                result.add(secondPostings.get(j));
                                j++;
                            }
                        }
                    } else {
                        result.add(secondPostings.get(j));
                        j++;
                        if (j == secondPostings.size()) {
                            while (i < firstPostings.size()) {
                                result.add(firstPostings.get(i));
                                i++;
                            }
                        }
                    }
                }
            }
            return result;
        }
        
	@Override
	public String toString() {
		// Returns a string of the form "[SUBQUERY] + [SUBQUERY] + [SUBQUERY]...n"
		return "(" +
		 String.join(" + ", mComponents.stream().map(c -> c.toString()).collect(Collectors.toList()))
		 + " )";
	}
        
        @Override
        public Boolean isPossitive(){
            return true;
        }

}
