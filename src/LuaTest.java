import org.luaj.vm2.Globals;
import org.luaj.vm2.Lua;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.ast.Chunk;
import org.luaj.vm2.ast.Exp;
import org.luaj.vm2.ast.Visitor;
import org.luaj.vm2.lib.jme.JmePlatform;
import org.luaj.vm2.lib.jse.CoerceLuaToJava;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.luaj.vm2.parser.LuaParser;
import org.luaj.vm2.parser.ParseException;
import org.luaj.vm2.server.LuajClassLoader;
import org.luaj.*;

import java.io.*;
import java.util.stream.Collectors;

public class LuaTest {
    public static void main(String[] args) {
        CardGameController controller = new CardGameController();

        String script = "lua/Test.lua";
        LuaHandler.executePath("lua/Effects.lua");
        LuaHandler.executePath(script);

    }
}
