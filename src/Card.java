import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;


public abstract class Card extends GameObject {
    String name;
    LinkedList<CardProperties> properties;
    String set;
    int id;

    public Card() {
        name = "PlaceholderName";
        properties = new LinkedList<>();
    }

    public Card(String name, String set, int id, LinkedList<CardProperties> properties) {
        this.name = name;
        this.set = set;
        this.id = id;
        this.properties = properties;

    }

    public CardGameController getController() {
        Object controller = this.getParent();

        while (controller.getClass() != CardGameController.class) {
            controller = this.getParent();
        }
        return (CardGameController) controller;
    }

    public ArrayList<Action> getActions() {
        return new ArrayList<>();
    }

}
