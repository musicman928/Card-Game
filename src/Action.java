import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public interface Action {
    static ArrayList<Action> makeList(Action[] actions) {
        return new ArrayList<>(Arrays.asList(actions));
    }
    public void performAction();
}