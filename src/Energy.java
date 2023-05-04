import javafx.scene.Parent;

import java.util.ArrayList;


public class Energy extends Card{
    private type type;
    private ArrayList<CardProperties> properties;
    public Energy(type type, ArrayList<CardProperties> properties) {
        this.type = type;
        this.properties = properties;
    }

    @Override
    public Parent drawObject() {
        return null;
    }

    @Override
    public void onAlert() {

    }

    public static enum type {
        COLORLESS, FIRE, WATER, GRASS, FIGHTING, PSYCHIC, LIGHTNING, DARKNESS, METAL;
    }

}
