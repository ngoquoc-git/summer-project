package cecs429.text;
import java.util.ArrayList;
import org.tartarus.snowball.ext.EnglishStemmer;

public class StandardTokenProcessor implements TokenProcessor {

    @Override
    public ArrayList<String> processToken(String token) {
        EnglishStemmer stem = new EnglishStemmer();
        ArrayList<String> lister = new ArrayList<String>();
        token = token.replaceAll("^\\W+|\\W+$", "");
        token = token.replaceAll("'","").toLowerCase();
        token = token.replaceAll("\"","");
        if (token.contains("-")){
            String[] Listtoken = token.split("-");
            for(String Lt : Listtoken){
                if(Lt.length() > 0){
                    lister.add(processToken(Lt).get(0));
                }
            }
            token = token.replaceAll("-","");
        }
        lister.add(token);
        for (int j = 0; j < lister.size();j++) {
            stem.setCurrent(lister.get(j));
            stem.stem();
            lister.set(j, stem.getCurrent());
            lister.set(j, (lister.get(j)));
        }

        return lister;
    }

}
