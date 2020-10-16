package program.ClassNodes.FireMaking;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.tabs.Tab;
import program.ClassNodes.ClassAbstraction;

public class FireStarting extends ClassAbstraction {

    public FireStarting(program.main main) {
        super(main);
    }

    @Override
    public boolean validate() {
        if (main.getInventory().contains("tinderbox") && (main.getInventory().contains("logs") || main.getInventory().contains("Oak logs"))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int execute() {

        if (validate()) {
            MethodProvider.log("running FireStarting");
            //====================== open inventory
            if (!main.getTabs().isOpen(Tab.INVENTORY)) {
                main.getTabs().openWithMouse(Tab.INVENTORY);
            }
            if (main.getSkills().getRealLevel(Skill.FIREMAKING) > 15) {
                Oak();
            }
            logs();

        }
        MethodProvider.log("exiting FireStarting " );
        return 1;
    }

    private void logs() {
        MethodProvider.log("running logs");
        while (main.getInventory().contains("Logs")) {
            MethodProvider.log("Lighting Logs");

            if (!main.getLocalPlayer().isOnScreen()) {
                main.getCamera().rotateToEntity(main.getLocalPlayer());
            }

            main.getInventory().get("Tinderbox").useOn("Logs");
            MethodProvider.sleep(Calculations.random(2000, 2200));
            checkForAnimation(0);

        }
    }

        private void Oak() {
            MethodProvider.log("running Oak");
            while (main.getInventory().contains("Oak logs")) {
                MethodProvider.log("Lighting Oak logs");

                if (!main.getLocalPlayer().isOnScreen()) {
                    main.getCamera().rotateToEntity(main.getLocalPlayer());
                }

                main.getInventory().get("Tinderbox").useOn("Oak logs");
                MethodProvider.sleep(Calculations.random(900, 1800));
                checkForAnimation(1);

            }

        }
        private void checkForAnimation(int n){
            if (main.getLocalPlayer().getAnimation() != 733) {
                main.getWalking().walk(main.getLocalPlayer().getSurroundingArea(2).getRandomTile());
                MethodProvider.sleep(Calculations.random(1200, 1800));
            } else {
                MethodProvider.log("waiting for animation");
              if(n == 0){
                  main.getMouse().move(main.getInventory().slotBounds(main.getInventory().get("Logs").getSlot()));
              }else{
                  main.getMouse().move(main.getInventory().slotBounds(main.getInventory().get("Oak logs").getSlot()));
              }

                while (main.getLocalPlayer().getAnimation() == 733) {
                    MethodProvider.sleepWhile(() -> !main.getLocalPlayer().isAnimating(), 3000);


                }
            }
            if (main.getDialogues().inDialogue()) {
                main.getDialogues().clickContinue();
            }
        }


}