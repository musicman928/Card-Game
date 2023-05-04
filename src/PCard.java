import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;

public class PCard extends Card {
    Energy.type type;
    int maxHP;
    int HP;
    ArrayList<Action> actions;
    Energy.type weakness;
    Energy.type resistance;
    int retreatCost;
    boolean hasCondition;
    String evolutionName;

    ArrayList<Energy.type> energyAttached;

    public PCard(String name, String set, int id, Energy.type type, String evolutionName, LinkedList<CardProperties> properties, int HP, ArrayList<Action> actions, Energy.type weakness, Energy.type resistance, int retreatCost) {
        this.name = name;
        this.set = set;
        this.id = id;
        this.type = type;
        this.evolutionName = evolutionName;
        this.properties = properties;
        this.maxHP = HP;
        this.HP = HP;
        this.actions = actions;
        this.weakness = weakness;
        this.resistance = resistance;
        this.retreatCost = retreatCost;
        energyAttached = new ArrayList<>();

        actions.forEach(e -> {
            add((Child) e);
        });

    }

    @Override
    public ArrayList<Action> getActions() {
        return actions;
    }

    public void attacked(Attack attack) {
        System.out.println(HP);
        HP -= attack.damage;
        System.out.println(HP);
        System.out.println("HP:" +CardGameController.getP1().active.HP);
        System.out.println(CardGameController.getP2().active.HP);
        CardGameApplication.stage.close();
        CardGameApplication.stage.setScene(new Scene(CardGameController.get().drawObject()));
        CardGameApplication.stage.show();


        if (getKOed()) {
            if (getBoard() == CardGameController.getP1()) {
                CardGameController.get().declareWinner(2);
            } else if (getBoard() == CardGameController.getP2()) {
                CardGameController.get().declareWinner(1);
            }


        }

    }

    public Parent drawObject() {
        Parent root = null;
        try {
            File pcard = new File("fxml/PCard.fxml");
            root = FXMLLoader.load(pcard.toURI().toURL());
            ImageView type = (ImageView) root.lookup("#type");

            Label hp = (Label) root.lookup("#hp");
            hp.setText(HP + " HP");



            Label stage = (Label) root.lookup("#stage");
            if (properties.contains(CardProperties.BASIC_POKEMON)) {
                stage.setText("Basic");
            } else if (properties.contains(CardProperties.STAGE_1)) {
                stage.setText("Stage 1");
            } else if (properties.contains(CardProperties.STAGE_2)) {
                stage.setText("Stage 2");
            }

            Label name = (Label) root.lookup("#name");
            name.setText(this.name);


            ImageView weak = (ImageView) root.lookup("#weakness");
            if (weakness != null) {
                weak.setImage(new Image(String.valueOf(new File("sprites/" + weakness.name().toLowerCase() + "_energy.png").toURI().toURL())));
            } else {
                weak.setImage(null);
            }

            ImageView res = (ImageView) root.lookup("#resistance");
            if (resistance != null) {
                res.setImage(new Image(String.valueOf(new File("sprites/" + resistance.name().toLowerCase() + "_energy.png").toURI().toURL())));
            } else {
                res.setImage(null);
            }

            switch (retreatCost) {
                case 0:
                    root.lookup("#retreat1").setVisible(false);
                    root.lookup("#retreat2").setVisible(false);
                    root.lookup("#retreat3").setVisible(false);
                    root.lookup("#retreat4").setVisible(false);
                    break;
                case 1:
                    root.lookup("#retreat2").setVisible(false);
                    root.lookup("#retreat3").setVisible(false);
                    root.lookup("#retreat4").setVisible(false);
                    break;
                case 2:
                    root.lookup("#retreat3").setVisible(false);
                    root.lookup("#retreat4").setVisible(false);
                    break;
                case 3:
                    root.lookup("#retreat4").setVisible(false);
                    break;
            }

            if (properties.contains(CardProperties.RULE_BOX)) {
                Label text = (Label) root.lookup("#ruleboxtext");
                text.setVisible(true);
                root.lookup("#rulebox").setVisible(true);
                if (properties.contains(CardProperties.POKEMON_V)) {
                    if (properties.contains(CardProperties.POKEMON_VMAX)) {
                        text.setText("VMAX rule: When your pokemon VMAX is knocked out, your opponent takes 3 prize cards");
                    }
                    text.setText("V rule: When your pokemon V is knocked out, your opponent takes 2 prize cards");
                }

                if (properties.contains(CardProperties.POKEMON_EX)) {
                    text.setText("ex rule: When your pokemon ex is knocked out, your opponent takes 2 prize cards");
                }
            }

            File energy = new File("sprites/" + this.type.name().toLowerCase() + "_energy.png");
            Image image = new Image(String.valueOf(energy.toURI().toURL()));
            type.setImage(image);

            Rectangle background = (Rectangle) root.lookup("#background");
            switch (this.type) {
                case FIRE:
                    background.setFill(Color.color(1,0,0,0.3));
                    break;
                case GRASS:
                    background.setFill(Color.color(0,1,0,0.3));
                    break;
                case METAL:
                    background.setFill(Color.color(0,0,0,0.3));
                    break;
                case WATER:
                    background.setFill(Color.color(0,0,1,0.2));
                    break;
                case PSYCHIC:
                    background.setFill(Color.color(0.9098039216,0,1,0.2));
                    break;
                case DARKNESS:
                    background.setFill(Color.color(0,0,0,0.5));
                    break;
                case FIGHTING:
                    background.setFill(Color.color(0.6549019608,0.3490196078,0.1568627451,0.2));
                    break;
                case LIGHTNING:
                    background.setFill(Color.color(1,1,0,0.2));
                    break;
                case COLORLESS:
                    background.setFill(Color.color(0,0,0,0.1));
                    break;

            }
            Rectangle opacitylayer = (Rectangle) root.lookup("#opacitylayer");


            root.setOnMouseExited(event -> opacitylayer.setOpacity(opacitylayer.getOpacity() - 0.3));
            root.setOnMouseEntered(e -> opacitylayer.setOpacity(opacitylayer.getOpacity() + 0.3));

            BorderPane borderpane = (BorderPane) root.lookup("#damagepane");

            borderpane.setCenter(new DamageCounter(maxHP - HP).drawObject());

            VBox actionbox = (VBox) root.lookup("#actionbox");

            if (getActions().size() > 0) {
                for (int i = 0; i < getActions().size(); i++) {

                    if (getActions().get(i).getClass() == Ability.class) {
                        Parent ability = ((Ability) getActions().get(i)).drawObject();
                        actionbox.getChildren().add(ability);
                        int finalI = i;
                        assert ability != null;
                        ability.setOnMouseClicked(e -> getActions().get(finalI).performAction());


                    } else if (getActions().get(i).getClass() == Attack.class) {
                        Parent attack = ((Attack) getActions().get(i)).drawObject();

                        actionbox.getChildren().add(attack);
                        int finalI = i;

                        attack.setOnMouseClicked(e -> {
                            System.out.println(((Attack)getActions().get(finalI)).damage);
                            ((Attack)getActions().get(finalI)).getBoard().getOpposite().getActive().attacked(((Attack)getActions().get(finalI)));
                            getActions().get(finalI).performAction();

                        });

                    }
                }
            }

            for (int i = 0; i < energyAttached.size(); i++) {
                ImageView energies = new ImageView(new Image(String.valueOf(new File("sprites/" + energyAttached.get(i).name().toLowerCase() + "_energy.png").toURI().toURL())));
                energies.setFitWidth(20);
                energies.setFitHeight(20);
                ((HBox) root.lookup("#energypane")).getChildren().add(energies);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        return root;
    }


    public void attachEnergy() {
        energyAttached.add(Energy.type.LIGHTNING);
        if (getBoard() == CardGameController.getP1()) {
            energyAttached.forEach(e -> {
                try {
                    AnchorPane pane = (AnchorPane) drawObject();
                    ImageView imageView = new ImageView(new Image(String.valueOf(new File("sprites/" + e.name().toLowerCase() + "_energy.png").toURI().toURL())));
                    ((HBox)pane.lookup("#energypane")).getChildren().add(imageView);

                    CardGameApplication.stage.setScene(new Scene(CardGameController.get().drawObject()));


                    CardGameApplication.stage.show();
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
            });


        }
    }


    public Parent drawDamageCounter() {
        DamageCounter damageCounter = new DamageCounter(maxHP - HP);

        return damageCounter.drawObject();
    }

    public boolean canUseAttack(Attack in) {
        System.out.println("Canuseattack");
        if (in.attackCost.length <= energyAttached.size()) {
            in.getColoredEnergy();
            ArrayList<Energy.type> temp = new ArrayList<>(energyAttached);
            System.out.println(Arrays.toString(temp.toArray()));
            for (int i = 0; i < in.getColoredEnergy().size(); i++) {
                temp.remove(in.getColoredEnergy().get(i));
            }
            System.out.println(temp.size());
            return temp.size() == 0;
        }

        return false;
    }

    public boolean getKOed() {
        return HP <= 0;
    }


    @Override
    public void onAlert() {

    }
}
