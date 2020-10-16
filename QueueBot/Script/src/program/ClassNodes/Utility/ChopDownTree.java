package program.ClassNodes.Utility;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.interactive.GameObject;
import program.ClassNodes.ClassAbstraction;
import program.main;

public class ChopDownTree extends ClassAbstraction {
    public ChopDownTree(program.main main) {
        super(main);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        return 0;
    }

    public boolean chopDownAnyTree() {

        GameObject tree = main.getGameObjects().closest("Tree");
        if (tree != null) {
            if (!main.getLocalPlayer().isOnScreen()) {
                main.getCamera().rotateToEntity(main.getLocalPlayer());
            }
            if (!tree.isOnScreen()) {
                main.getWalking().walk(tree);
                MethodProvider.sleepUntil(() -> !main.getLocalPlayer().isMoving(), Calculations.random(2000, 3769));
            }
            ClickTree(tree);

            MethodProvider.sleepWhile(tree::exists, Calculations.random(2000, 3769));

            long time = System.currentTimeMillis() / 1000;
            while (tree.exists() && time + 5 > System.currentTimeMillis() / 1000) {
                levelUp();
                MethodProvider.sleepWhile(tree::exists, Calculations.random(2000, 3769));
            }
            return true;
        }
        return false;
    }

    public void levelUp() {
        //====================== Click to level up
        if (main.getDialogues().inDialogue()) {
            main.getDialogues().clickContinue();
        }
    }

    public void ClickTree(GameObject tree) {
        MethodProvider.log("Chopping tree");
        main.getMouse().click(tree);
        if (Math.random() > .8) {
            main.getMouse().click(tree);
        }
    }
}

