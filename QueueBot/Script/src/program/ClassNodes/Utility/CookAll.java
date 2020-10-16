package program.ClassNodes.Utility;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.interactive.GameObject;
import program.ClassNodes.ClassAbstraction;

public class CookAll extends ClassAbstraction {
    private final String itemName;
    private GameObject fire;
    public CookAll(program.main main, String itemName) {
        super(main);
        this.itemName = itemName;
        fire = main.getGameObjects().closest("Fire");
    }

    @Override
    public boolean validate() {
        return fire != null;
    }

    @Override
    public int execute() {

        if (!main.getLocalPlayer().isOnScreen()) {
            main.getCamera().rotateToEntity(main.getLocalPlayer());
        }
      if(validate()){

          MethodProvider.sleep(Calculations.random(200,400));

          if (!main.getTabs().isOpen(Tab.INVENTORY)) {
              main.getTabs().openWithMouse(Tab.INVENTORY);
          }
          MethodProvider.sleep(Calculations.random(200,400));

          do{
              new MoveToArea(main,fire.getTile().getArea(1));
              main.getInventory().get(itemName).useOn(fire);
              MethodProvider.sleep(Calculations.random(150,300));
              main.getWidgets().getWidgetChild(270, 14).interact();
              MethodProvider.sleep(Calculations.random(700,1117));

              while(fire!=null && main.getInventory().contains(itemName)) {
                  MethodProvider.sleep(Calculations.random(200,400));
                  if (main.getDialogues().inDialogue()) {
                      main.getDialogues().clickContinue();
                      break;
                  }
              }
              MethodProvider.sleep(Calculations.random(400,1000));

              fire = main.getGameObjects().closest("Fire");
          }while(fire != null && main.getInventory().contains(itemName));
      }else{
          MethodProvider.log("Couldn't find a Fire");
      }




        return 0;
    }
}
