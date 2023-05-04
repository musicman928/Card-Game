import java.util.HashMap;
import java.util.LinkedList;

public class DeckBuilder {

    public static GenericDeck<Card> build(HashMap<String, Card> lib, String in) {
        GenericDeck<Card> deck = new GenericDeck<>();
        LinkedList<String> list = DeckBuilder.toList(in);

        while (!list.isEmpty()) {
            deck.addTop(CardBuilder.build(lib, list.getFirst()));
            list.removeFirst();
        }

        return new GenericDeck<Card>();
    }

    public static LinkedList<String> toList(String in) {
        LinkedList<String> list = new LinkedList<>();
        char[] chars = in.toCharArray();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '\n') {
                builder.append(chars[i]);
            }
            else {
                list.add(builder.toString());
                builder = new StringBuilder();
            }
        }
        return list;
    }
}
