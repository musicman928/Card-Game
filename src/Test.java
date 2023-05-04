import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {
        PCard miraidonex = new PCard(
                "Miraidon ex", "SVI", 81, Energy.type.LIGHTNING, null,
                new LinkedList<>(Arrays.asList(CardProperties.BASIC_POKEMON, CardProperties.POKEMON_EX, CardProperties.RULE_BOX)),
                220,
                new ArrayList<>(Action.makeList(new Action[]{
                                        new Ability("Tandem Unit", "Once during your turn, you may search your deck for up to 2 basic lightning Pokemon and put them onto your bench. Then, shuffle your deck.", null, false),
                                        new Attack("Photon Blaster", "During your next turn, this Pokemon cannot attack",
                                                new Energy.type[]{Energy.type.LIGHTNING, Energy.type.LIGHTNING, Energy.type.COLORLESS}, 220)})),
                Energy.type.FIGHTING, null, 1);
        CardLibrary lib = new CardLibrary(true);
        lib.put(miraidonex);
        CardLibrary.writeToFile(lib);




    }

    public void doAThing(){
        System.out.println("Hello!");
    }
}
