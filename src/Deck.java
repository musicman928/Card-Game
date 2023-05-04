import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;


public class Deck<Card> extends GameObject {
    CardGameController parent;
    LinkedList<Card> data;
    private boolean readable;

    public Deck() {
        data = new LinkedList<>();
        boolean readable = false;
    }

    public Deck(boolean readable) {
        data = new LinkedList<>();
        this.readable = readable;
    }

    public void setParent(CardGameController parent) {
        this.parent = parent;
    }

    private LinkedList<Card> getData() {
        return data;
    }

    public LinkedList<Card> readData() {
        if (readable) {
            return data;
        }
        else {
            return null;
        }
    }

    public void addTop(Card in) {
        data.addFirst(in);
    }

    public void addTop(LinkedList<Card> in) {
        data.addAll(0, in);
    }

    public void addTop(Deck<Card> in) {
        data.addAll(in.data);
    }

    public void addBottom(Card in) {
        data.addLast(in);
    }

    public void addBottom(LinkedList<Card> in) {
        data.addAll(in.size(), in);
    }

    public void addBottom(Deck<Card> in) {
        data.addAll(in.data.size(), in.data);
    }

    public void read() {
        if (readable) {
            System.out.println(Arrays.toString(data.toArray()));
        }
    }

    public Card draw() {
        Card temp = data.getFirst();
        data.removeFirst();
        return temp;
    }

    public LinkedList<Card> draw(int amount) {
        LinkedList<Card> out = new LinkedList<>();

        for (int i = 0; i < amount; i++) {
            out.add(draw());
        }

        return out;
    }

    public void shuffle() {
        List<Card> temp = new ArrayList<>(data);
        Deck<Card> tempDeck = new Deck<>();
        Random randy = new Random();

        for (int i = 0; temp.size() > 0; i++) {
            Card p = temp.get(randy.nextInt(temp.size()));
            temp.remove(p);
            tempDeck.addTop(p);
            data = tempDeck.getData();
        }
    }

    public void reportUndrawable() {

    }

    @Override
    public Parent drawObject() {

        Rectangle rectangle = new Rectangle(250, 350, Color.LIGHTBLUE);
        AnchorPane pane = new AnchorPane(rectangle);
        pane.setPrefWidth(250);
        pane.setPrefHeight(350);

        return pane;
    }

    @Override
    public void onAlert() {

    }
}
