package program.ClassNodes.Mining;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.filter.impl.NameFilter;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.items.Item;
import program.ClassNodes.ClassAbstraction;
import program.ClassNodes.Utility.Bank;
import program.ClassNodes.Utility.MoveToArea;
import org.dreambot.api.methods.map.Area;

import program.main;

public class MineInventory extends ClassAbstraction {
    private String oreType = null;
    private Area area = null;

    public MineInventory(program.main main) {
        super(main);
    }

    public MineInventory(program.main main, String oreType) {
        super(main);
        this.oreType = oreType;
    }

    public MineInventory(program.main main, String oreType, Area area) {
        super(main);
        this.oreType = oreType;
        this.area = area;
    }

    @Override
    public boolean validate() {

        return false;
    }

    @Override
    public int execute() {
        MethodProvider.log("Walking to area MiningInv : Node");
        new MoveToArea(main, area).execute();
        MineRock Mine = new MineRock(main, oreType);
        MethodProvider.sleep(Calculations.random(140,300));

        if (!main.getTabs().isOpen(Tab.INVENTORY)) {
            main.getTabs().openWithMouse(Tab.INVENTORY);
            MethodProvider.sleep(500);

        }
        main.getCamera().mouseRotateToYaw(187);
        main.getCamera().mouseRotateToPitch(338);
        main.getCamera().rotateToEvent(6925, 6272);

        while (!main.getInventory().isFull()) {
//             for(int i = 0 ; i < 4; i++){ // testing
            MethodProvider.log("in while loop");

            MethodProvider.log("in the while loop");
            // new MineRock(main, ore).execute();
            if(underAttack()){
                fleeFromAttack();
            }
            Mine.execute();
            MethodProvider.sleep(2000);
            new MoveToArea(main, area).execute();
        }
        MethodProvider.log("Broke out of while loop");

        if (main.getInventory().isFull()) {
            Bank bank = new Bank(main);
            bank.NearestBank();
            // String id = main.getInventory().get(item -> item.getName().contains("pickaxe")).getName();
            //new NameFilter("Pickaxe",id)
            main.getBank().depositAllExcept(i -> i.getName().contains("pickaxe"));
            main.getBank().close();
        }
        MethodProvider.log("Walking to area MiningInv : Node");
        return 0;
    }

}
