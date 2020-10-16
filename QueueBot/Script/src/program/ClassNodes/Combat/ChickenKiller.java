package program.ClassNodes.Combat;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.items.GroundItem;
import program.ClassNodes.Utility.MoveToArea;
import program.ClassNodes.ClassAbstraction;


public class ChickenKiller extends ClassAbstraction {
    private static final Area CHICKEN_FARM = new Area(3014, 3298, 3020, 3282);
    private static final String CHICKEN_NAME = "Chicken";

    public ChickenKiller(program.main main) {
        super(main);
    }

    @Override
    public boolean validate() {
        return CHICKEN_FARM.contains(main.getLocalPlayer());
    }

    @Override
    public int execute() {
        if (!hasGear()) {
            getGear();
        }
        if (!validate()) {
            new MoveToArea(main, CHICKEN_FARM).execute();
        }
        if (!main.getTabs().isOpen(Tab.COMBAT)) {
            main.getTabs().openWithMouse(Tab.COMBAT);
        }
        main.getWidgets().getWidget(593).getChild(8).interact();
        InCombat();


//        main.getWidgets().getWidget(593).getChild(4); // attack
//        MethodProvider.log(" attack");
//        main.getWidgets().getWidget(593).getChild(8).interact();
//        MethodProvider.log(" Strangth");
//        main.getWidgets().getWidget(593).getChild(16).interact();
//        MethodProvider.log(" Defence");
//        main.getWidgets().getWidget(593).getChild(12).interact();
//        MethodProvider.log(" Shared ex");

//        if (main.getPlayerSettings().getConfig(43) != 0) {
//
//
//        }


        return 0;
    }

    private void InCombat() {
        if (main.getSkills().getRealLevel(Skill.STRENGTH) < 11) {
            switchStates("STRENGTH");
            training(Skill.STRENGTH, 10);
        }
        if (main.getSkills().getRealLevel(Skill.ATTACK) < 11) {
            switchStates("ATTACK");
            training(Skill.ATTACK, 10);
        }
        if (main.getSkills().getRealLevel(Skill.DEFENCE) < 10) {
            switchStates("DEFENCE");
            training(Skill.DEFENCE, 5);
        }

    }

    private void training(Skill skill, int level) {
        while (main.getSkills().getRealLevel(skill) < level) {
//        for(int i = 0 ; i < 5 ; i++){
            MethodProvider.log("New chicken - training");

            if (!main.getLocalPlayer().isOnScreen()) {
                main.getCamera().rotateToEntity(main.getLocalPlayer());
            }


            NPC chicken = getNewTarget();

            chicken.interact();
            MethodProvider.sleep(2000);
            while (chicken.isHealthBarVisible()
                    || main.getLocalPlayer().isInCombat() || main.getLocalPlayer().getAnimation() != -1) {
                MethodProvider.log("Second whileloop");
                MethodProvider.sleep(2000);
                MethodProvider.sleepUntil(() -> !main.getLocalPlayer().isHealthBarVisible(), Calculations.random(1000, 1300));
                if (!main.getLocalPlayer().isOnScreen()) {
                    main.getCamera().rotateToEntity(main.getLocalPlayer());
                }

            }
            if (main.getDialogues().inDialogue()) {
                main.getDialogues().clickContinue();
            }
            MethodProvider.sleep(Calculations.random(300, 600));

            GroundItem Bones = main.getGroundItems().closest("Bones");

            if (Bones != null) {
                main.getMouse().move(Bones.getTile());
                Bones.interact("Take");
                MethodProvider.sleepUntil(() -> main.getLocalPlayer().getTile() != Bones.getTile(), Calculations.random(1000, 1300));
                MethodProvider.sleep(Calculations.random(500, 1200));
                while (main.getInventory().contains("Bones")) {
                    main.getInventory().get("Bones").interact();
                    MethodProvider.sleep(Calculations.random(300, 600));

                }

            }

        }
        MethodProvider.log("exiting Chicken");

    }

    private void switchStates(String State) {
        if (!main.getTabs().isOpen(Tab.COMBAT)) {
            main.getTabs().openWithMouse(Tab.COMBAT);
        }
        switch (State) {
            case "ATTACK":
                main.getWidgets().getWidget(593).getChild(7).interact();
                MethodProvider.sleep(Calculations.random(300, 600));
                main.getTabs().openWithMouse(Tab.INVENTORY);

                break;
            case "STRENGTH":
                main.getWidgets().getWidget(593).getChild(8).interact();
                MethodProvider.sleep(Calculations.random(300, 600));
                main.getTabs().openWithMouse(Tab.INVENTORY);
                break;

            case "DEFENCE":
                main.getWidgets().getWidget(593).getChild(16).interact();
                MethodProvider.sleep(Calculations.random(200, 600));
                main.getTabs().openWithMouse(Tab.INVENTORY);
                break;
        }
    }

    private NPC getNewTarget() {
        return main.getNpcs().closest(
                npc -> npc.getName().equals(CHICKEN_NAME) && CHICKEN_FARM.contains(npc) &&
                        !npc.isInCombat() && npc.getInteractingCharacter() == null
        );
    }
    @Override
    public boolean underAttack() {

        if (main.getLocalPlayer().isInCombat()) {
            return true;
        }
        for (NPC possibleAggressor : main.getNpcs().all(CHICKEN_NAME)) {
            if (possibleAggressor.isInteracting(main.getLocalPlayer()) && possibleAggressor.isInCombat()) {
                return true;
            }
        }
        return false;
    }

    private void getGear() {

    }

    private boolean hasGear() {

        return false;
    }
}
