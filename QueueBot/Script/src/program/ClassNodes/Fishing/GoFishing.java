package program.ClassNodes.Fishing;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.interactive.NPC;
import program.ClassNodes.ClassAbstraction;
import program.main;

public class GoFishing extends ClassAbstraction {
    public GoFishing(program.main main) {
        super(main);
    }

    @Override
    public boolean validate() {


        return false;
    }

    @Override
    public int execute() {
        MethodProvider.log("Starting GoFishing: Node");

        if (!main.getTabs().isOpen(Tab.INVENTORY)) {
            main.getTabs().openWithMouse(Tab.INVENTORY);
            MethodProvider.sleep(Calculations.random(200, 300));
        }
        NPC fishingSpot = main.getNpcs().closest(1525);
        if (!fishingSpot.isOnScreen()) {
            main.getCamera().mouseRotateToEntity(fishingSpot);
        }
        fishingSpot.interact("Small Net");
        MethodProvider.sleep(1000);
        MethodProvider.sleepUntil(() -> main.getLocalPlayer().isMoving(), Calculations.random(800, 1400));
        MethodProvider.log("Done moving");
        while(main.getLocalPlayer().getAnimation() == 621) {
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().getAnimation() == 621, Calculations.random(400, 900));
            if (main.getDialogues().inDialogue()) {
                main.getDialogues().clickContinue();
            }
        }


        MethodProvider.log("Exit GoFishing: Node");
        return 0;
    }
}
