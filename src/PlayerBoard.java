import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class PlayerBoard extends GameObject {
    Deck<Card> deck;
    Deck<Card> discard;
    Deck<Card> lostZone;
    PCard active;
    PCard[] bench;
    LinkedList<Card> hand;
    Card stadium;
    boolean vstarPower;
    boolean supporter;
    boolean energy;
    boolean retreat;

    PlayerBoard(Deck<Card> deck) {
        this.deck = deck;
        discard = new Deck<>(true);
        lostZone = new Deck<>(true);
        active = null;
        bench = new PCard[5];
        hand = new LinkedList<>();
        stadium = null;
        vstarPower = true;
        supporter = true;
        energy = true;
        retreat = true;



    }

    PlayerBoard() {
        this.deck = deck;
        discard = new Deck<>(true);
        lostZone = new Deck<>(true);
        active = null;
        bench = new PCard[5];
        hand = new LinkedList<>();
        stadium = null;
        vstarPower = true;
        supporter = true;
        energy = true;
        retreat = true;



    }

    public void startTurn() {
        supporter = true;
    }

    public PCard getActive() {
        return active;
    }

    public void setActive(PCard in) {
        active = in;
        add(active);
    }

//    public Card search(Deck<Card> from, CardProperties[] properties) {
//        CardGameApplication app = (CardGameApplication) getController().getParentOneWay();
//        //app.
//    }

    public PlayerBoard getOpposite() {
        if (CardGameController.getP1() == this) {
            return CardGameController.getP2();
        } else if (CardGameController.getP2() == this) {
            return CardGameController.getP1();
        } else {
            return null;
        }
    }



    @Override
    public void onAlert() {

    }

    @Override
    public Parent drawObject() {
        Parent root = null;
        File file = new File("fxml/board.fxml");
        try {
            root = FXMLLoader.load(file.toURI().toURL());
            ((AnchorPane)root.lookup("#cardarea")).getChildren().add(active.drawObject());

            ((Button) root.lookup("#energybutton")).setOnAction(e -> {
                active.attachEnergy();

            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
}
