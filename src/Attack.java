import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Attack extends GameObject implements Action {

    String name;
    String attackText;
    Energy.type[] attackCost;
    int damage;

    Attack(String name, String attackText, Energy.type[] attackCost, int damage) {
        this.name = name;
        this.attackText = attackText;
        this.attackCost = attackCost;
        this.damage = damage;

    }

    @Override
    public void performAction() {
        onAttack();
    }

    public void onAlert() {

    }

    public void onAttack() {
        if (getPCard().canUseAttack(this)) {
                System.out.println(damage);
                getBoard().getOpposite().getActive().attacked(this);
        }
    }

    public ArrayList<Energy.type> getColoredEnergy() {
        ArrayList<Energy.type> energies = new ArrayList<>();

        for (int i = 0; i < attackCost.length; i++) {
            if (attackCost[i] != Energy.type.COLORLESS) {
                energies.add(attackCost[i]);
            }
        }
        return energies;
    }

    @Override
    public Parent drawObject() {
        try {
            File file = new File("fxml/Attack.fxml");
            Parent out = FXMLLoader.load(file.toURI().toURL());

            HBox costbox = (HBox) out.lookup("#costbox");

            if (attackCost.length > 0) {
                for (int i = 0; i < attackCost.length; i++) {
                    ImageView imageView = new ImageView(new Image(String.valueOf(new File("sprites/" + attackCost[i].name().toLowerCase() + "_energy.png").toURI().toURL())));
                    imageView.setFitWidth(12);
                    imageView.setFitHeight(12);
                    costbox.getChildren().add(imageView);
                }
            } else {
                Circle circle = new Circle(20, Color.WHITE);
                costbox.getChildren().add(circle);
            }

            Label name = (Label) out.lookup("#attackname");
            name.setText(this.name);

            Label damage = (Label) out.lookup("#attackdamage");
            damage.setText(String.valueOf(this.damage));

            Label text = (Label) out.lookup("#attacktext");
            text.setText(attackText);

            return out;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
