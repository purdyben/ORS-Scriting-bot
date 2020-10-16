package program.ClassNodes.Utility;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.interactive.NPC;
import program.ClassNodes.ClassAbstraction;

public class Bank{
    private program.main main;
    public Bank(program.main main) {
        this.main = main;
    }
    public boolean validate() {
        BankLocation bank = BankLocation.getNearest(main.getLocalPlayer());
        Area bankArea = bank.getArea(7);

        if (bankArea.contains(main.getLocalPlayer())) {
            return true;
        }
        return false;
    }

    /**
     * walks to the closets bank and intereacts with the closest NPC banker.
     */
    public int NearestBank(){
        // ====================== Closest Bank
        BankLocation bank = BankLocation.getNearest(main.getLocalPlayer());
        main.getWalking().walk(bank.getArea(1).getRandomTile());
        MethodProvider.log(bank.toString());


        while(!bank.getArea(1).contains(main.getLocalPlayer())){
            MethodProvider.log("banking");
            main.getWalking().walk(bank.getArea(1).getRandomTile());
            MethodProvider.sleepUntil(() -> bank.getArea(1).contains(main.getLocalPlayer()), Calculations.random(300, 500));
            while (main.getLocalPlayer().isMoving()) {
                MethodProvider.sleepUntil(() -> main.getLocalPlayer().isMoving(), Calculations.random(200, 500));
            }

        }
        MethodProvider.sleep(Calculations.random(200, 800));

        NPCBanking();
        return 1;
    }
    public void NPCBanking(){
        MethodProvider.sleep(Calculations.random(300, 900));

        // ====================== NPC Banker interaction. deposit and withdraw is done within your script
        NPC banker = main.getNpcs().closest(npc -> npc != null && npc.hasAction("Bank"));
        if(banker != null && banker.interact("Bank")) {
            main.getMouse().move(banker);
            main.sleep(500);
            banker.interact("Bank");
            if (main.getTabs().isOpen(Tab.INVENTORY)) {
                main.getTabs().openWithMouse(Tab.INVENTORY);
            }
            MethodProvider.sleep(Calculations.random(500, 1000));

        }
    }
    public void depositAll(){
        main.getBank().depositAllItems();
    }
}
