package program.ClassNodes.GrandExchange;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import program.ClassNodes.ClassAbstraction;
import program.ClassNodes.Utility.Bank;
import program.ClassNodes.Utility.MoveToArea;

public class GE extends ClassAbstraction {
    private String item = null;
    private int amount = 1;
    private static final Area GE_Location = new Area(3163, 3485, 3170, 3490);

    public GE(program.main main) {
        super(main);
        MethodProvider.log("GE");


    }
    public GE(program.main main, String item) {
        super(main);
        this.item = item;
    }

    public GE(program.main main, String item, int amount) {
        super(main);
        this.item = item;
        this.amount = amount;
    }


    public void openGE() {
        main.getGrandExchange().open();

    }

    public void searchGE(String i) {
        main.getGrandExchange().searchItem(i);


    }

    public void buyItem() {
        if (item != null) {
            if (main.getGrandExchange().isOpen()) {
                main.getGrandExchange().buyItem("Pot of flour", main.getGrandExchange().getCurrentPrice(), amount);
                MethodProvider.sleep(Calculations.random(150, 300));
            }
        }
//        MethodProvider.log("buying " + name);
//        MethodProvider.sleep(1000);
//
//        for (int i = 0; i < 3; i++) {
//            main.getGrandExchange().openBuyScreen(i);
//            if (!main.getGrandExchange().isBuyOpen() && i == 2) {
//                return 0;
//            }
//            MethodProvider.sleep(200);
//
//        }
    }

    public void sellGE() {
//        MethodProvider.log("buying ");
//        MethodProvider.sleep(1000);
//
//        for (int i = 0; i < 3; i++) {
//            main.getGrandExchange().openBuyScreen(i);
//            if (!main.getGrandExchange().isBuyOpen() && i == 2) {
//                return 0;
//            }
//            MethodProvider.sleep(200);
//
//        }
    }

    public boolean inTheGE() {
        return GE_Location.contains(main.getLocalPlayer());
    }

    public int moveToTheGE() {
        MethodProvider.log("Move to GE");

        if (!inTheGE()) {
            MoveToArea geArea = new MoveToArea(main, GE_Location);
            geArea.execute();
            return 1;
        }
        return 0;
    }


    public int getGold(int gold) {
        MethodProvider.log("getGold");
        int inventoryGold;
        if (!main.getInventory().contains("Coins") || main.getInventory().count("Coins") < gold) {
            new Bank(main).NearestBank();
            inventoryGold = main.getInventory().count("Coins");

            if (inventoryGold + main.getBank().count("Coins") < gold) {
                allCoins();
            } else if (inventoryGold < gold) {
                new Bank(main).NPCBanking();
                main.getBank().withdraw(main.getBank().get("Coins").getID(), gold);
            }

        }
        return main.getInventory().count("Coins");
    }

    private boolean allCoins() {
        if (main.getBank().contains("Coins")) {
            new Bank(main).NPCBanking();
            main.getBank().withdrawAll("Coins");
            return true;
        }
        return false;

    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setItem(String item) {
        this.item = item;
    }
}


