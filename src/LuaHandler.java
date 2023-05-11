import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class LuaHandler {
    public static void execute(String script) {
        Globals globals = JsePlatform.standardGlobals();

        LuaValue chunk = globals.load(script);

        chunk.call( LuaValue.valueOf(script) );
    }

    public static void executePath(String path) {
        Globals globals = JsePlatform.standardGlobals();

        LuaValue chunk = globals.loadfile(path);

        chunk.call( LuaValue.valueOf(path) );
    }
}
