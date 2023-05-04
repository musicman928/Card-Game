import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;


public class DamageCounter extends GameObject{
    private int damage;


    DamageCounter(int damage) {
        this.damage = damage;
    }

    @Override
    public Parent drawObject() {
        File counter = new File("fxml/DamageCounter.fxml");
        BorderPane out = null;

        if (damage > 0) {
            try {
                out = FXMLLoader.load(counter.toURI().toURL());
                Label label = (Label) out.lookup("#damagetext");
                label.setText(String.valueOf(damage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return out;
    }

    @Override
    public void onAlert() {

    }
}
