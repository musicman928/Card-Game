import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class CardLibrary implements Serializable {
    HashMap<String, Card> lib;

    CardLibrary() {
            lib = Objects.requireNonNull(readFromFile()).lib;
    }

    CardLibrary(boolean makeNewLib) {
        if (makeNewLib) {
            lib = new HashMap<>();
        }
    }

    public Card get(String name) {
        return lib.get(name);
    }

    public void put(String key, Card value) {
        lib.put(key, value);
    }

    public static void clearFile() {
        try {
            new FileOutputStream("lib/Cards.dt").close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void put(Card value) {
        System.out.println(value.name + " " + value.set + " " + value.id);
        lib.put(value.name + " " + value.set + " " + value.id, value);
    }

    public static void writeToFile(File file, CardLibrary lib) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(lib);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void writeToFile(CardLibrary lib) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("lib/Cards.dt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(lib);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("lib/Cards.dt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CardLibrary readFromFile() {
        try {
            File file = new File("lib/Cards.dt");
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (CardLibrary) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
