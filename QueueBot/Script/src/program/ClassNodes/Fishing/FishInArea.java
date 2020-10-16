package program.ClassNodes.Fishing;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.filter.impl.NameFilter;
import org.dreambot.api.methods.map.Area;
import program.ClassNodes.ClassAbstraction;
import program.ClassNodes.Fishing.GoFishing;
import program.ClassNodes.Utility.Bank;
import program.ClassNodes.Utility.MoveToArea;
import program.main;

public class FishInArea extends ClassAbstraction {
    /**
     * Fish Areas
     * Area(3090,3224,3086,3232) Draynor Village
     */
    /**
     * ids
     * small fishing net 303
     * Fishing rod 307
     * Fishing bait 313
     * Fly fishing rod 309
     * feather 314
     */
    private final Area fishingArea;
    private final String FishingTool;
    private String Bait = null;
    public FishInArea(program.main main, Area fishingArea, String FishingTool) {
        super(main);
        this.fishingArea = fishingArea;
        this.FishingTool = FishingTool;
    }
    public FishInArea(program.main main, Area fishingArea, String FishingTool, String Bait) {
        super(main);
        this.fishingArea = fishingArea;
        this.FishingTool = FishingTool;
        this.Bait = Bait;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        if(main.getInventory().contains(FishingTool)  &&  (main.getInventory().contains(Bait) || Bait == null)) {
            MethodProvider.log("moving to Fishing area");
             MoveToArea area = new  MoveToArea(main, fishingArea);
             area.execute();
             getFullInventory();
        }
        new Bank(main).NearestBank();
        main.getBank().depositAllExcept(new NameFilter(FishingTool, Bait));
        main.getBank().close();
        MethodProvider.log("Exiting Fishing: Node");
        return 0;
    }

    public int execute(int xp) {
        return 0;
    }

    private void getFullInventory() {
        GoFishing fishing = new GoFishing(main);
        while (!main.getInventory().isFull()) {

            fishing.execute();
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().getAnimation() == 621 , Calculations.random(400, 900));
            //====================== Click to level up
            if (main.getDialogues().inDialogue()) {
                main.getDialogues().clickContinue();
            }
        }
    }

}
