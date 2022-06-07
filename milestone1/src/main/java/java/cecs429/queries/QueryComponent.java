package java.cecs429.queries;

import java.cecs429.indexes.Index;
import java.cecs429.indexes.Posting;

import java.util.List;

/**
 * A QueryComponent is one piece of a larger query, whether that piece is a literal string or represents a merging of
 * other components. All nodes in a query parse tree are QueryComponent objects.
 */
public interface QueryComponent {
    /**
     * Retrieves a list of postings for the query component, using an Index as the source.
     */
//    List<Posting> getPostingsPositions(Index index);
    List<Posting> getPostings(Index index);
}