import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(category = Category.MISC, name = "scriptTest", author = "test", version = 1.6, description = "For testing")
public class main extends AbstractScript {
    @Override
    public void onStart(){

    }

    @Override
    public void onExit() {
        super.onExit();
    }

    @Override
    public int onLoop() {
        sleep(2000,4000);
        return 0;
    }
}

