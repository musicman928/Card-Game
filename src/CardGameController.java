import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.Serializable;
import java.util.*;

public class CardGameController extends GameObject implements Serializable {

    private static PlayerBoard p1;
    private static PlayerBoard p2;

    private static CardGameController singleton;

    CardLibrary lib;
    Random randy;
    PlayerInputHandler inputHandler;
    ArrayList<Action> actions;
    Stack<Runnable> effectqueue;


    CardGameController() {
        singleton = this;
    }

    CardGameController(Deck<Card> deck1, Deck<Card> deck2) {
        p1 = new PlayerBoard(deck1);
        getChildren().add(p1);
        p2 = new PlayerBoard(deck2);
        getChildren().add(p2);

        randy = new Random();
        actions = new ArrayList<>();

        DeckBuilder db = new DeckBuilder();
    }

    public static CardGameController get() {
        return singleton;
    }

    public void start(CardLibrary lib, String deck1, String deck2) {

    }

    public static void setP1(PlayerBoard p1) {
        CardGameController.p1 = p1;
        singleton.add(p1);
    }

    public static PlayerBoard getP1() {
        return p1;
    }

    public static void setP2(PlayerBoard p2) {
        CardGameController.p2 = p2;
        singleton.add(p2);
    }

    public static PlayerBoard getP2() {
        return p2;
    }

    public void start() {

        inputHandler = new PlayerInputHandler(new Scanner(System.in));
        inputHandler.getCoinSide();



    }

    public boolean coinflip() {
        return randy.nextInt(2) == 1;
    }

    public void initActions() {
        LinkedList<Card> data = p1.deck.readData();
        data.forEach(n -> actions.addAll(n.getActions()));

        data = p2.deck.readData();
        data.forEach(n -> actions.addAll(n.getActions()));
    }

    public void runAffect(Action action) {
        //action.run(inputHandler.getTarget())
    }

    public void makePokemon(String name, String set, int id, Energy.type type, String evolutionName, LinkedList<CardProperties> properties, int HP, ArrayList<Action> actions, Energy.type weakness, Energy.type resistance, int retreatCost) {
        PCard card = new PCard(name, set, id, type, evolutionName, properties, HP, actions, weakness, resistance, retreatCost);
    }

    public void writeCard(Card card) {
        lib.put(card);
    }

    public void declareWinner(int winner) {
        switch (winner) {
            case 0: System.out.println("It's a tie!");
            System.exit(0);
            break;
            case 1: System.out.println("Player 1 Wins!");
            break;
            case 2: System.out.println("Player 2 Wins!");

        }
    }

    public void addDamageCounter(Card source, PCard target) {

    }

    public void onAlert() {

    }

    public void attack(Attack source) {
        PCard attacked = source.getBoard().getOpposite().getActive();
        CardGameController.get().attack(source);
        System.out.println("HI");
        if (attacked.getKOed()) {
            //declareWinner(0);
        }
    }


    public PlayerBoard getOppositeBoard(PlayerBoard board) {
        if (board == p1) {
            return p2;
        } else if (board == p2) {
            return p1;
        } else {
            return null;
        }

    }

    @Override
    public Parent drawObject() {
        Parent p1Object = p1.drawObject();
        Parent p2Object = p2.drawObject();
        p1Object.lookup("#root").setId("p1root");
        p2Object.lookup("#root").setId("p2root");
        return new VBox(0, p1Object, p2Object);
    }

}
