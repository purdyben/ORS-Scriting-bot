package program.ClassNodes.Quests;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.tabs.Tab;
import program.ClassNodes.ClassAbstraction;
import program.ClassNodes.GrandExchange.*;


public class CooksAssistant extends ClassAbstraction {


    public CooksAssistant(program.main main) {
        super(main);
    }

    @Override
    public boolean validate() {
        main.log(Integer.toString(main.getPlayerSettings().getConfig(1042)));
        int config = main.getPlayerSettings().getConfig(1042);
        return true;
    }

    @Override
    public int execute() {
       GE exchange = new GE(main);
       exchange.moveToTheGE();

        if(main.getGrandExchange().isOpen()){
            main.getGrandExchange().close();
        }

        if (main.getTabs().isOpen(Tab.INVENTORY)) {
            main.getTabs().openWithMouse(Tab.INVENTORY);
        }

        MethodProvider.log("getGold");
        /// get gold

        main.getBank().close();
        MethodProvider.log("Open GrandExchange");
        if(!main.getGrandExchange().isOpen())
            main.getGrandExchange().open("Exchange");

        MethodProvider.sleep( Calculations.random(600, 1000));

        MethodProvider.log(" searchItem");
        collect();
        if (main.getGrandExchange().isGeneralOpen()) {


            if(!main.getInventory().contains("pot of flour")){

                MethodProvider.log("pot of flour");
                MethodProvider.sleep( Calculations.random(600, 1000));
                main.getGrandExchange().openBuyScreen(1);
                main.getGrandExchange().buyItem("pot of flour",1, 155);
                MethodProvider.sleep( Calculations.random(650, 1000));

                main.getGrandExchange().goBack();
            }


            collect();
            if(!main.getInventory().contains("egg")){

                MethodProvider.log("egg");
                MethodProvider.sleep( Calculations.random(600, 1000));
                MethodProvider.log("pot of flour");
                main.getGrandExchange().openBuyScreen(1);
                main.getGrandExchange().buyItem("egg",1, 50);
                MethodProvider.sleep( Calculations.random(620, 1000));

                main.getGrandExchange().goBack();
            }


            collect();
            if(!main.getInventory().contains("bucket of milk")){

                MethodProvider.log("bucket of milk");
                MethodProvider.sleep( Calculations.random(600, 1000));
                main.getGrandExchange().openBuyScreen(1);
                main.getGrandExchange().buyItem("bucket of milk",1, 40);
                MethodProvider.sleep( Calculations.random(690, 1000));

                main.getGrandExchange().goBack();

            }
            MethodProvider.sleep(Calculations.random(1000, 5000));
            collect();

        }

        MethodProvider.sleep( Calculations.random(300, 500));

        main.getGrandExchange().close();
//        main.log(Integer.toString(main.getPlayerSettings().getConfig(1042)));
        return 0;
    }
    private void collect(){
        if (main.getGrandExchange().isReadyToCollect(1)) {

            main.getGrandExchange().collect();

            main.getGrandExchange().goBack();

            MethodProvider.sleep(Calculations.random(1000, 5000));

        }
    }
}
