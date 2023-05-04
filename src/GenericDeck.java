import java.io.Serializable;
import java.util.*;

public class GenericDeck<E> extends Parent implements Serializable {
    CardGameController parent;
    LinkedList<E> data;
    private boolean readable;

    public GenericDeck() {
        data = new LinkedList<>();
        boolean readable = false;
    }

    public GenericDeck(boolean readable) {
        data = new LinkedList<>();
        this.readable = readable;
    }

    public void setParent(CardGameController parent) {
        this.parent = parent;
    }

    private LinkedList<E> getData() {
        return data;
    }

    public LinkedList<E> readData() {
        if (readable) {
            return data;
        }
        else {
            return null;
        }
    }

    public void addTop(E in) {
        data.addFirst(in);
    }

    public void addTop(LinkedList<E> in) {
        data.addAll(0, in);
    }

    public void addTop(GenericDeck<E> in) {
        data.addAll(in.data);
    }

    public void addBottom(E in) {
        data.addLast(in);
    }

    public void addBottom(LinkedList<E> in) {
        data.addAll(in.size(), in);
    }

    public void addBottom(GenericDeck<E> in) {
        data.addAll(in.data.size(), in.data);
    }

    public void read() {
        if (readable) {
            System.out.println(Arrays.toString(data.toArray()));
        }
    }

    public E draw() {
        E temp = data.getFirst();
        data.removeFirst();
        return temp;
    }

    public LinkedList<E> draw(int amount) {
        LinkedList<E> out = new LinkedList<>();

        for (int i = 0; i < amount; i++) {
            out.add(draw());
        }

        return out;
    }

    public void shuffle() {
        List<E> temp = new java.util.ArrayList<>(data);
        GenericDeck<E> tempDeck = new GenericDeck<>();
        Random randy = new Random();

        for (int i = 0; temp.size() > 0; i++) {
            E p = temp.get(randy.nextInt(temp.size()));
            temp.remove(p);
            tempDeck.addTop(p);
            data = tempDeck.getData();
        }
    }

    public void reportUndrawable() {

    }

    @Override
    public void onAlert() {

    }
}
