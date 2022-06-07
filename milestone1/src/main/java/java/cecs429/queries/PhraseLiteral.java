package java.cecs429.queries;

import java.cecs429.indexes.Index;
import java.cecs429.indexes.Posting;
import java.cecs429.text.AdvancedTokenProcessor;
import java.cecs429.text.TokenProcessor;

import java.util.ArrayList;
import java.util.List;
import javax.management.Query;

public class PhraseLiteral implements QueryComponent {

    private List<String> mTerms = new ArrayList<>();
    private int k;

    public PhraseLiteral(List<String> terms) {
            mTerms.addAll(terms);
    }

    @Override  
    public List<Posting> getPostings(Index index) {
        TokenProcessor tokenProcessor = new AdvancedTokenProcessor();
        List<String> querry = new ArrayList<>();

        for(String item: mTerms) {
                querry.add(tokenProcessor.processToken(item).get(0));
        }

        List<Posting> result = new ArrayList<>();
        List<Posting> p1 = new ArrayList<>();
        List<Posting> p2 = new ArrayList<>();
        int loop = querry.size() - 1;
        int count = 1;
        Boolean check = true;

        if(querry.size() == 1) result = index.getPostingsPositions(querry.get(0));

        while(loop > 0) {
                p1 = index.getPostingsPositions(querry.get(count - 1));
                p1 = index.getPostingsPositions(querry.get(count));

                if (!check) {
                        result = merge(result, p2, result.size(), p2.size(), k);
                } 
                else {
                        result = merge(p1, p2, p1.size(), p2.size(), k);
                        check = false;
                }
                count++;
                loop--;
        }
        return result;
    }
        
    public List<Posting> merge(List<Posting> p1, List<Posting> p2, int p1_length, int p2_length, int k) {
        List<Posting> results = new ArrayList<>();
        List<Integer> positions1 = new ArrayList<>();
        List<Integer> positions2 = new ArrayList<>();

        int index1 = 0;
        int index2 = 0;

        while (index2 < p2_length && index1 < p1_length) {

            positions1 = p1.get(index1).getPositions();
            positions2 = p2.get(index2).getPositions();

            int pos1 = 0;
            int pos2 = 0;

            if (p1.get(index1).getDocumentId() == p2.get(index2).getDocumentId()) {
                while (pos1 < positions1.size() && pos2 < positions2.size()) {
                    if (positions1.get(pos1) + k == positions2.get(pos2)) {
                        if (results.isEmpty()) {
                            Posting p = new Posting(p2.get(index2).getDocumentId(), positions2.get(pos2));
                            results.add(p);
                        }
                        else if (results.get(results.size() - 1).getDocumentId() == p2.get(index2).getDocumentId()) {
                            Posting p = results.get(results.size() - 1);
                            p.addPosition(positions2.get(pos2));
                        }
                        else {
                            Posting p = new Posting(p2.get(index2).getDocumentId(), positions2.get(pos2));
                            results.add(p);
                        }
                        pos1++;
                        pos2++;
                        if (pos1 == positions1.size() || pos2 == positions2.size()) break;                        
                    } 
                    else if (positions1.get(pos1) < positions2.get(pos2)) {
                        pos1++;
                        if (pos1 == positions1.size()) break;                        
                    } 
                    else if (positions2.get(pos2) < positions1.get(pos1)) {
                        pos2++;
                        if (pos2 == positions2.size()) break;                       
                    }
                    else {
                        pos1++;
                        pos2++;
                        if (pos1 == positions1.size() || pos2 == positions2.size()) break;
                        
                    }

                }
                
                index1++;
                index2++;

            } else if (p1.get(index1).getDocumentId() < p2.get(index2).getDocumentId()) {
                index1++;
            } else if (p1.get(index1).getDocumentId() > p2.get(index2).getDocumentId()) {
                index2++;
            }
        }
        return results;
    }
        

	
	@Override
	public String toString() {
            return "\"" + String.join(" ", mTerms) + "\"";
	}
        
        @Override
        public Boolean isPossitive(){
            return true;
        }
	
}
