package program.ClassNodes.MoneyMaking;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.filter.Filter;
import program.ClassNodes.ClassAbstraction;
import program.ClassNodes.GrandExchange.*;
import program.ClassNodes.Utility.Bank;

public class PizzaBase extends ClassAbstraction {
    private int pizzaNumber;
    private Bank bank = new Bank(main);
    private GE exchange = new GE(main);

    public PizzaBase(program.main main, int pizzaNumber) {
        super(main);
        this.pizzaNumber = pizzaNumber;
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
         //setup
        exchange.moveToTheGE();
        exchange.getGold(pizzaNumber * 20 + pizzaNumber * 160);
        main.getBank().close();
        // buy Items
        main.getGrandExchange().open();
        while (!main.getGrandExchange().isGeneralOpen()) {
            MethodProvider.log("isGeneralOpen");
            MethodProvider.sleep(300);
        }
        MethodProvider.sleep(1000);
        buyItem("Pot of flour", pizzaNumber, 153);
        main.getGrandExchange().collect();

        MethodProvider.sleep(Calculations.random(1000,1500));
        MethodProvider.log("Bucket");
        buyItem("Bucket of water", pizzaNumber, 20);


        MethodProvider.sleep(6000);
        while (!main.getInventory().contains(1934) && !main.getInventory().contains(1930)) {
            main.getGrandExchange().collect();
            MethodProvider.sleep(500);
        }
        MethodProvider.sleep(2000);

        main.getGrandExchange().close();
        MethodProvider.sleep(2000);

        int numberMade = 0;
        while (numberMade < pizzaNumber) {
            numberMade += makePizzaBase();
        }

        sellPizza();
//


        return 0;
    }

    private int buyItem(String name, int num, int price) {
        MethodProvider.log("buying " + name);
        main.getGrandExchange().cancelAll();
        for (int i = 0; i < 3; i++) {
            main.getGrandExchange().openBuyScreen(i);

            MethodProvider.sleep(500);

        }
        while (!main.getGrandExchange().isBuyOpen()) {
            MethodProvider.sleep(300);
            MethodProvider.log("BuyOpen while");
        }
        MethodProvider.sleep(Calculations.random(1500, 2500));

        //================== click Type box and type the name
        try {
            main.getWidgets().getWidgetChild(162, 51).interact();
        } catch (Exception e) {
            MethodProvider.log(e + "couldn't findWidget");

        } finally {
            MethodProvider.sleep(1000);
            main.getKeyboard().type(name);
        }
        while (main.getKeyboard().isTyping()) {
            MethodProvider.sleep(300);
        }
        MethodProvider.sleep(1000);
        //================== select the item
        main.getGrandExchange().addBuyItem(name);

        MethodProvider.log("addBuyItem");
        MethodProvider.sleep(1000);

        //================== setQuantity - setPrice
        main.getGrandExchange().setQuantity(pizzaNumber);
//        MethodProvider.sleep(1000);
        main.getGrandExchange().setPrice(price);
//        MethodProvider.sleep(1000);

        //================== buy button
        main.getWidgets().getWidgetChild(465, 27).interact();

        MethodProvider.log("done buying " + name);
        main.getGrandExchange().goBack();

        return 1;
    }

    private int makePizzaBase() {
        MethodProvider.log("make Pizza");

        bank.NPCBanking();

        if (!main.getInventory().isEmpty()) {
            bank.depositAll();
        }
        MethodProvider.sleep(Calculations.random(1000, 2000));

        if (Math.random() > .64) {
            main.getBank().withdraw("Pot of flour", 9);
            main.getBank().withdraw("Bucket of water", 9);
        } else {
            main.getBank().withdraw("Bucket of water", 9);
            main.getBank().withdraw("Pot of flour", 9);

        }

        main.getBank().close();
        MethodProvider.sleep(Calculations.random(500, 1500));

        main.getInventory().get("Pot of flour").interact();
        main.getInventory().get("Bucket of water").interact();

        MethodProvider.sleep(Calculations.random(700, 1000));

        main.getWidgets().getWidgetChild(270, 16).interact();

        while (main.getInventory().contains("Pot of flour") && main.getInventory().contains("Bucket of water")) {
            MethodProvider.sleep(500);
        }
        int NumberOfPizzaMade = main.getInventory().count("Pizza Base");
        bank.NPCBanking();
        if (!main.getInventory().isEmpty()) {
            bank.depositAll();
        }
        return NumberOfPizzaMade;
    }

    private void sellPizza() {
        bank.NPCBanking();
        main.getWidgets().getWidgetChild(12, 25).interact();
        MethodProvider.sleep(500);
        //================== withdrawAll
        MethodProvider.log("withdrawAll ");
        main.getBank().withdrawAll("Pizza base");
        // main.getBank().withdrawAll("Bucket"); // withdraw buckets
        main.getBank().withdrawAll("pot");
        MethodProvider.log("withdrawAll done");

        main.getBank().close();
        MethodProvider.sleep(Calculations.random(500, 600));

        //================== Open GrandExchange
        main.getGrandExchange().open();
        while (!main.getGrandExchange().isGeneralOpen()) {
            MethodProvider.sleep(250);
        }
        //================== GrandExchange Collect/cancelAll
        main.getGrandExchange().cancelAll();
        readyToCollect();
        //================== Selling all items
        SellItems("Pizza");
        //SellItems("Bucket"); // == sell buckets
        SellItems("Pot");
        MethodProvider.sleep(Calculations.random(1200, 2500));

        readyToCollect();
        main.getGrandExchange().close();
        MethodProvider.log("Finish PizzaBase : " + java.time.LocalDate.now());

    }

    private int readyToCollect() {
        if (main.getGrandExchange().isGeneralOpen() && main.getGrandExchange().isReadyToCollect()) {
            main.getGrandExchange().collect();
            return 1;
        }
        return 0;
    }

    private void SellItems(String item) {
        switch (item) {
            case "Pizza":
                //================== sell Pizza base
                try {
                    MethodProvider.log("sell Pizza base");
                    main.getInventory().get("Pizza base").interact();
                    MethodProvider.sleep(Calculations.random(500, 600));
                    main.getWidgets().getWidgetChild(465, 27).interact();
                } catch (NullPointerException e) {
                    MethodProvider.log("Couldn't find Pizza base : " + e);

                }
                break;
            case "Bucket":
                //================== sell Bucket
                try {
                    main.getInventory().get("Bucket").interact();
                    MethodProvider.sleep(Calculations.random(500, 600));
                    main.getWidgets().getWidgetChild(465, 24, 10).interact();
                    main.getWidgets().getWidgetChild(465, 24, 10).interact();

                    MethodProvider.sleep(Calculations.random(100, 400));
                    main.getWidgets().getWidgetChild(465, 27).interact();

                    MethodProvider.sleep(Calculations.random(500, 700));
                } catch (NullPointerException e) {
                    MethodProvider.log("Couldn't find Bucket : " + e);

                }
                break;
            case "Pot":

                //================== sell pot
                try {
                    main.getInventory().get("Pot").interact();
                    MethodProvider.sleep(Calculations.random(500, 700));
                    main.getWidgets().getWidgetChild(465, 27).interact();
                    MethodProvider.sleep(Calculations.random(90, 300));
                } catch (NullPointerException e) {
                    MethodProvider.log("Couldn't find pot : " + e);

                }
                break;
        }


    }
}
