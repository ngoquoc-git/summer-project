package cecs429.documents;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Path;


/**
 * Represents a document that is saved as a simple text file in the local file system.
 */
public class JsonFileDocument implements FileDocument {
    private int docID;
    private Path path;
    private String title;

    public JsonFileDocument(int docID, Path path) {
        this.docID = docID;
        this.path = path;
    }

    @Override
    public int getId() {
        return this.docID;
    }

    @Override
    public Path getFilePath() {
        return this.path;
    }

    public String getFileName() {
        return path.getFileName().toString();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Reader getContent() {
        Reader reader;
        Reader Sreader;
        try {
            reader = new FileReader(path.toString());
            JsonObject jsonObject = new JsonParser().parse(reader).getAsJsonObject();
            Sreader = new StringReader(jsonObject.get("body").getAsString());
            return Sreader;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getTitle() {
        Reader reader;
        try {
            reader = new FileReader(path.toString());
            JsonObject jsonObject = new JsonParser().parse(reader).getAsJsonObject();
            title = jsonObject.get("title").getAsString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return title;
    }

    public static FileDocument loadJsonFileDocument(Path absolutePath, int documentId) {
        return new JsonFileDocument(documentId, absolutePath);
}
}
