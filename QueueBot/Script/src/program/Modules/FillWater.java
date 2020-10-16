package program.Modules;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.interactive.GameObject;
import program.ClassNodes.ClassAbstraction;
import program.ClassNodes.Utility.MoveToArea;
import program.main;

public class FillWater extends ClassAbstraction {
    private Area area = new Area(3224, 3215, 3219, 3223);
    private int buckets = 0;
    public FillWater(program.main main) {
        super(main);
    }

    @Override
    public boolean validate() {
        return super.validate();
    }

    @Override
    public int execute() {

        // move to PVP world

        if (area.getCenter().distance(main.getLocalPlayer().getTile()) > 20) {
            if (!main.getTabs().isOpen(Tab.MAGIC)) {
                main.getTabs().openWithMouse(Tab.MAGIC);
                MethodProvider.sleep(Calculations.random(200, 400));

            }
            MethodProvider.sleep(Calculations.random(200, 400));
            main.getWidgets().getWidgetChild(218, 4).interact();
            MethodProvider.sleep(Calculations.random(20000, 30000));

        }else if(!area.contains(main.getLocalPlayer())){
            new MoveToArea(main, area);
        }

        MethodProvider.sleep(Calculations.random(200, 400));
        if (!main.getTabs().isOpen(Tab.INVENTORY)) {
            main.getTabs().openWithMouse(Tab.INVENTORY);
        }
        accessChest();
        while(buckets > 0){
            if(getBuckets() == 1){
                return 0;
            }
            fillBuckets();
            accessChest();
            depositAll();
            MethodProvider.log(Integer.toString(buckets));
        }

        return 1;
    }
    public int getBuckets(){
        buckets = main.getBank().count("Bucket");
        if(!main.getBank().contains("Bucket")){
            return 1;
        }
        if(main.getBank().isOpen()){
            if(!main.getInventory().isEmpty()){
                main.getBank().depositAllItems();
            }
            main.getBank().withdrawAll("Bucket");
            MethodProvider.sleep(Calculations.random(100, 1200));
            main.getBank().close();
            MethodProvider.sleep(Calculations.random(200, 1300));
        }
        return 0;
    }
    public void fillBuckets(){

        GameObject water = main.getGameObjects().closest(i ->  i != null && i.getName().contains("Fountain"));

        main.log(water.getName());
        if (!main.getTabs().isOpen(Tab.INVENTORY)) {
            main.getTabs().openWithMouse(Tab.INVENTORY);
        }
        if (!water.isOnScreen()) {
            main.getCamera().rotateToEntity(water);
        }
        main.getInventory().get("Bucket").interact();
        MethodProvider.sleep(Calculations.random(200, 400));
        main.getMouse().move(water);
        MethodProvider.sleep(Calculations.random(200, 400));

        water.interact("Use");
        MethodProvider.sleep(Calculations.random(4000, 6000));

       while(main.getInventory().contains("Bucket")){
           MethodProvider.sleep(500);

       }

    }
    public void accessChest(){
        if (!main.getTabs().isOpen(Tab.INVENTORY)) {
            main.getTabs().openWithMouse(Tab.INVENTORY);
        }
        GameObject chest = main.getGameObjects().closest(i ->  i != null && i.getName().contains("Bank chest") && i.hasAction("Use"));

        if (!main.getBank().isOpen() && chest != null) {

            if (!chest.isOnScreen()) {
                main.getCamera().rotateToEntity(chest);
            }

            MethodProvider.log("chest is not null");
            chest.interact("Use");
            MethodProvider.sleepUntil(() -> main.getBank().isOpen(), 2500);
        }
        buckets = main.getBank().count("Bucket");
        MethodProvider.sleep(Calculations.random(500, 1000));

    }
    public void depositAll(){
        if(main.getBank().isOpen()){
            main.getBank().depositAllItems();
        }
        buckets = main.getBank().count("Bucket");

    }
}
