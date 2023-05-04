import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.*;

public class CardGameApplication extends Application {
    public static CardGameApplication singleton;
    static Stage stage;
    static Scene scene;
    static Parent p;
    static PlayerBoard p1;
    static PlayerBoard p2;

    @Override
    public void start(Stage stage) throws Exception {
        // this is a very rough demo, I am very sorry as I realized the scope of this project was a bit too much for me to do in 15 hours. I would be willing to continue work on this project and resubmit it later if possible.

        singleton = this;
        CardGameController controller = new CardGameController();
        p1 = new PlayerBoard();
        CardGameController.setP1(p1);
        p2 = new PlayerBoard();
        CardGameController.setP2(p2);

        controller.setParentOneWay(this);
        CardGameApplication.stage = stage;

        PCard miraidonex = new PCard(
                "Miraidon ex", "SVI", 81, Energy.type.LIGHTNING, null,
                new LinkedList<>(Arrays.asList(CardProperties.BASIC_POKEMON, CardProperties.POKEMON_EX, CardProperties.RULE_BOX)),
                220,
                new ArrayList<>(Action.makeList(new Action[]{
                        new Ability("Tandem Unit", "Once during your turn, you may search your deck for up to 2 basic lightning Pokemon and put them onto your bench. Then, shuffle your deck.", null, false),
                        new Attack("Photon Blaster", "",
                                new Energy.type[]{Energy.type.LIGHTNING, Energy.type.LIGHTNING, Energy.type.COLORLESS}, 220)})),
                Energy.type.FIGHTING, null, 1);

        CardGameController.getP1().setActive(miraidonex);
        CardGameController.getP1().add(miraidonex);
        CardGameController.getP2().setActive((PCard) miraidonex.clone());


        p = controller.drawObject();
        p.setId("#controller");
        Scene scene = new Scene(p);
        CardGameApplication.stage = stage;
        stage.setScene(scene);
        stage.show();

    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return null;
    }

    public static void reDraw() {
        stage.setScene(scene);
    }
}
