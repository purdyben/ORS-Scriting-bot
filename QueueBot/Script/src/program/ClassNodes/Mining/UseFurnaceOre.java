package program.ClassNodes.Mining;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.interactive.GameObject;
import program.ClassNodes.ClassAbstraction;
import program.ClassNodes.Utility.MoveToArea;
import program.main;

public class UseFurnaceOre extends ClassAbstraction {
    //new Area(3106,3498,3108,3500) -- edgeville furnece
    private String itemToMake;
    private int itemID;
    private  int number;
    public UseFurnaceOre(program.main main) {
        super(main);
    }
    public UseFurnaceOre(program.main main, String itemToMake) {
        super(main);
        this.itemToMake = itemToMake;
    }
    public UseFurnaceOre(program.main main, String itemToMake, int number) {
        super(main);
        this.itemToMake = itemToMake;
        this.number = number;
    }
    public UseFurnaceOre(program.main main, String itemToMake, int itemID, int number) {
        super(main);
        this.itemToMake = itemToMake;
        this.itemID = itemID;
        this.number = number;
    }


    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        moveToSmelter();
        if (main.getTabs().isOpen(Tab.INVENTORY)) {
            main.getTabs().openWithMouse(Tab.INVENTORY);
        }

        GameObject Furnace = main.getGameObjects().closest("Furnace");
        MethodProvider.sleep(Calculations.random(450,550));

        if (!main.getLocalPlayer().isOnScreen()) {
            main.getCamera().rotateToEntity(main.getLocalPlayer());
        }
        main.getMouse().click(Furnace);

        MethodProvider.sleep(Calculations.random(900,1100));
        while(main.getLocalPlayer().isMoving()){
            MethodProvider.sleep(Calculations.random(900,1100));
        }

        oreWidgetInteract(itemToMake);
        MethodProvider.sleep(Calculations.random(900,1100));
        while(main.getLocalPlayer().isAnimating()){
            if (main.getDialogues().inDialogue()) {
                main.getDialogues().clickContinue();
            }
        }


        return 0;
    }
    public void moveToSmelter(){
        new MoveToArea(main, new Area(3106,3498,3108,3500)).execute();
    }
    private void oreWidgetInteract(String oreName){
        MethodProvider.sleep(Calculations.random(500,600));
        switch (oreName){
            case "bronze":
                main.getMouse().click(main.getWidgets().getWidgetChild(270,14).getRectangle());
                break;
            case "iron":
                main.getMouse().click(main.getWidgets().getWidgetChild(270, 15).getRectangle());
                break;
            case "silver":
                main.getMouse().click(main.getWidgets().getWidgetChild(270, 16).getRectangle());
                break;
            case "steel":
                main.getMouse().click(main.getWidgets().getWidgetChild(270, 17).getRectangle());
                break;
            case "gold":
                main.getMouse().click(main.getWidgets().getWidgetChild(270, 18).getRectangle());
                break;
            case "mithril":
                main.getMouse().click(main.getWidgets().getWidgetChild(270, 19).getRectangle());
                break;
            case "adamant":
                main.getMouse().click(main.getWidgets().getWidgetChild(270, 20).getRectangle());
                break;
            case "runite":
                main.getMouse().click(main.getWidgets().getWidgetChild(270, 21).getRectangle());
                break;
            default:
                break;

        }

    }

}
