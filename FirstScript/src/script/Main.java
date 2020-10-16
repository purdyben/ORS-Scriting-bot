package script;

import script.ClassNodes.*;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.Category;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;

@ScriptManifest(category = Category.MINING, name = "Miner.01", author = "Andrew", version = .01)
public class Main extends AbstractScript {
    private Queue q = new Queue();
    private static java.time.LocalDate StartTime = java.time.LocalDate.now();


    public void onStart() {
        System.out.println("Starting script");
        sleepUntil(() -> !getLocalPlayer().isMoving(), Calculations.random(2000, 3000));
        q.add(new Node(new movmentNode(this), "Walk"));
        q.add(new Node(new movmentNode(this), "Walk"));
        q.add(new Node(new movmentNode(this), "Walk"));

    }

    public void onExit() {
        System.out.println("Exit");

    }

    @Override
    public int onLoop() {
        while (q.peek()) {
//            Node run = q.pop();
//            run.getData().execute();
            sleep(Calculations.random(1000, 2000));
        }
        onExit();

        return 1000;
    }
}




