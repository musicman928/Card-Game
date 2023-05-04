import java.util.HashMap;

public class CardBuilder {
    private HashMap<String, Card> library;

    public void add(String key, Card value) {
        library.put(key, value);
    }

    public Card build(String name) {
        return library.get(name);
    }

    public static Card build(HashMap<String, Card> lib, String name) {
        return lib.get(name);
    }



}
