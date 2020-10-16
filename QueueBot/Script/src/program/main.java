package program;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.Category;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import program.ClassNodes.Combat.ChickenKiller;
import program.ClassNodes.Fishing.FishInArea;
import program.ClassNodes.Mining.*;
import program.ClassNodes.MoneyMaking.PizzaBase;
import program.ClassNodes.Utility.Test;
import program.Modules.FillWater;
import program.Modules.TreeChoppingUnder35;


@ScriptManifest(category = Category.MISC, name = "QueueBot_FirstScript", author = "program.test", version = 1.221, description = "For testing")
public class main extends AbstractScript {
    private Queue q = new Queue();
    private static java.time.LocalDate StartTime = java.time.LocalDate.now();

    //new Area(3162,3453,3151,3458)
    @Override
    public void onStart() {
        System.out.println("Starting script");
        sleepUntil(() -> !getLocalPlayer().isMoving(), Calculations.random(3000, 5000));
        q.add(new Node(new FillWater(this), "script"));
        //q.add(new Node(new PizzaBase(this,225),"Pizza"));
        //q.add(new Node(new ChickenKiller(this),"Cook All"));
        //q.add(new Node(new RomeoAndJuliet(this),"RomeoAndJuliet"));
//        q.add(new Node(new MineInventory(this, "Coal", new Area(3084, 3417, 3080, 3423)), "Cook All"));
//        q.add(new Node(new MineInventory(this,"Coal", new Area(3084,3417,3080,3423)),"Cook All"));
//        q.add(new Node(new MineInventory(this,"Coal", new Area(3084,3417,3080,3423)),"Cook All"));
//        q.add(new Node(new MineInventory(this,"Coal", new Area(3084,3417,3080,3423)),"Cook All"));
//        q.add(new Node(new MineInventory(this,"Coal", new Area(3084,3417,3080,3423)),"Cook All"));

//        q.add(new Node(new FishInArea(this, new Area(3090,3224,3086,3232), "Small fishing net"),"Cook All"));
//        q.add(new Node(new FishInArea(this, new Area(3090,3224,3086,3232), "Small fishing net"),"Cook All"));

        // q.add(new Node(new RomeoAndJuliet(this),"RomeoAndJuliet"));
        //q.add(new Node(new TreeChoppingUnder35(this, new Area(3090,3288,3100,3283), "TREE"),"TreeChop : Area"));
       // q.add(new Node(new TreeChoppingUnder35(this, new Area(3147,3465,3167,3450), "TREE"),"TreeChop : Area"));

        // q.add(new Node(new CooksAssistant(this), "CookCheck"));

    }

    @Override
    public void onExit() {

        System.out.println("Exit");

    }

    @Override
    public int onLoop() {
        while (q.hasNode()) {
            try {
                Node run = q.pop();
                log("Running New Node : " + run.getid());
                run.getData().execute();

            } catch (NullPointerException e) {
                log("Queue is empty : " + e);
            } finally {
                //danger/rebalence nodes/ new task
            }
        }

        log("Queue is empty");
       // getTabs().logout();
        return 1000;
    }
}


