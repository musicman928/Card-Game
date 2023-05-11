import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

public class Effects extends ZeroArgFunction {
    static Effects singleton;

    private Effects() {

    }

    private static void draw(int amount, GenericDeck<Card> source, GenericDeck<Card> destination) {
        if (amount > 1) {
            destination.addTop(source.draw(amount));
        } else if (amount == 0) {
            CardGameController c = (CardGameController) source.getRoot();
            if (c.getP1().getChildren().equals(source) || c.getP2().getChildren().equals(source)) {
                if (c.getP1().getChildren().equals(source)) {
                    c.declareWinner(1);
                }
                else {
                    c.declareWinner(2);
                }
            }
        } else {
            destination.addTop(source.draw());
        }
    }

    private static void addDamageCounter(Card source, int amount, PCard target) {
        if(!target.hasCondition) {
            target.getController().addDamageCounter(source, target);
        }
    }


    @Override
    public LuaValue call() {
        return CoerceJavaToLua.coerce(singleton);
    }

    public static LuaValue staticcall() {
        singleton = new Effects();
        return singleton.call();
    }
}
