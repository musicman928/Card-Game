

import java.io.Serializable;

public abstract class GameObject extends Parent implements Serializable, Cloneable {
    public abstract javafx.scene.Parent drawObject();

    @Override
    public GameObject clone() {
        try {
            return (GameObject) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
