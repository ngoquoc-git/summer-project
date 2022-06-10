package cecs429.text;

import java.util.ArrayList;

/**
 * A BasicTokenProcessor creates terms from tokens by removing all non-alphanumeric characters from the token, and
 * converting it to all lowercase.
 */
public class BasicTokenProcessor implements TokenProcessor {
	@Override
	public ArrayList<String> processToken(String token) {
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add(token.replaceAll("\\W", "").toLowerCase());
		return list1;
	}
}
