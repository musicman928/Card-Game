import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class Ability extends Child implements Action {
    String name;
    String abilityText;
    Thread effect;
    boolean passive;
    boolean used = true;

    Ability (String name, String abilityText, Thread effect, boolean passive) {
        this.name = name;
        this.abilityText = abilityText;
        this.effect = effect;
        if (passive) {
            //effect.no

        }
    }

    public void triggerAbility() {
        System.out.println("Abilitying");
    }

    public void targetAbility(Object target) {

    }

    public void onAlert() {
        used = true;
    }

    public Parent drawObject() {
        try {
            File file = new File("fxml/Ability.fxml");
            Parent out = FXMLLoader.load(file.toURI().toURL());
            Label text = (Label) out.lookup("#abilitytext");
            text.setText(abilityText);
            return out;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void performAction() {
        triggerAbility();
    }
}
