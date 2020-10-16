package program.ClassNodes.Mining;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.interactive.GameObject;
import program.ClassNodes.ClassAbstraction;
import program.ClassNodes.Combat.health;

public class MineRock extends ClassAbstraction {
    /**
     * id 11360 == tin// model color 53
     * id 11161 == copper model color = 4645
     * id 11363 == clay // model color 6705
     * id 11365 == Iron model color = 2576
     * id coal 11366 11367 model color = 10508
     */
    private final String oreType;

    public MineRock(program.main main, String oreType) {
        super(main);
        this.oreType = oreType;
    }

    @Override
    public boolean validate() {
        return false;
    }

    //Short ROCK_COLOR  = 4645;
    //GameObject rock = main.getGameObjects().closest(i -> i != null &&
    // i.getName().equals("Rocks") && (Short)i.getModelColors()[0] == ROCK_COLOR);
    @Override
    public int execute() {
        Rock();
        MethodProvider.sleep(Calculations.random(140, 300));
        return 0;
    }

    private void Rock() {
        GameObject rock1 = null;
        GameObject rock2 = null;
        try {
            rock1 = main.getGameObjects().closest(i -> i != null &&
                    i.getName().equals("Rocks") && i.getID() == oreID(oreType) && i.getModelColors()[0] == rockColor(oreType));
        } catch (Exception e) {
            MethodProvider.log("couldn't find a rock " + oreID(oreType) + " " + e.toString() + e.getCause());

        }
        try {
            rock2 = main.getGameObjects().closest(i -> i != null &&
                    i.getName().equals("Rocks") && i.getID() == oreID(oreType) + 1 && i.getModelColors()[0] == rockColor(oreType));
        } catch (Exception e) {
            MethodProvider.log("couldn't find a rock " + oreID(oreType) + " " + e.toString() + e.getCause());

        }
        Tile PlayerTile = main.getLocalPlayer().getTile();
        if (rock1 != null && rock2 != null) {
            if (rock1.getTile().distance(PlayerTile) >= rock2.getTile().distance(PlayerTile)) {
                clickRock(rock1);
                //rock1
            } else if (rock1.distance(PlayerTile) < rock2.getTile().distance(PlayerTile)){
                clickRock(rock2);

                //rock2
            }
            //check for distence
        } else if (rock1 != null) {
            clickRock(rock1);
            //click rock 1
        } else if (rock2 != null) {
            clickRock(rock2);
            //click rock 2
        } else {
            MethodProvider.sleep(Calculations.random(1000, 2200));
            //do nothing
        }
        if (underAttack()) {
            fleeFromAttack();
        }
        MethodProvider.sleep(Calculations.random(100, 250));
        MethodProvider.sleepWhile(() -> main.getLocalPlayer().isMoving(), Calculations.random(300, 500));

        MethodProvider.sleep(1500);

        MethodProvider.sleepUntil(() -> main.getLocalPlayer().getAnimation() == 625, 400);
        while (main.getLocalPlayer().getAnimation() != -1) {
            // check for combat
            if (underAttack()) {
                fleeFromAttack();
            }
            MethodProvider.sleepUntil(() -> main.getLocalPlayer().getAnimation() != -1, 500);
            if (main.getDialogues().inDialogue()) {
                main.getDepositBox().close();
            }

            //       Short  n = rock.getModelColors()[0];
            //        MethodProvider.log(Short.valueOf(n).toString());
        }

    }

    private void clickRock(GameObject rock) {
        main.getMouse().move(rock);
        rock.interact("Mine");
        if (Math.random() > .812) {
            main.getMouse().move(rock);
            rock.interact("Mine");
        }
    }

    public void mineCopperAndTin(int ore) {
        GameObject rock;
        if (main.getTabs().isOpen(Tab.INVENTORY)) {
            main.getTabs().openWithMouse(Tab.INVENTORY);
        }
        //Area treeArea =  new Area(new Tile(3101,3283,0), new Tile(3097,3290,0));
        if (!main.getLocalPlayer().isOnScreen()) {
            main.getCamera().mouseRotateToEntity(main.getLocalPlayer());
        }
        switch (ore) {
            case 0:
                //====== Tin
                rock = main.getGameObjects().closest(i -> i != null &&
                        i.getName().equals("Rocks") && i.getID() == oreID(oreType));
                MethodProvider.sleep(Calculations.random(10, 120));
                MethodProvider.log(rock.getTile().toString());
                main.getMouse().click(rock);
                if (Math.random() > .8) {
                    main.getMouse().click(rock);
                }

                MethodProvider.sleep(2500);
                MethodProvider.sleepUntil(() -> main.getLocalPlayer().getAnimation() == 625, 400);
                while (main.getLocalPlayer().getAnimation() != -1 || main.getLocalPlayer().isAnimating() || main.getLocalPlayer().isInteractedWith()) {
                    MethodProvider.sleepUntil(() -> main.getLocalPlayer().getAnimation() != -1, 500);
                    MethodProvider.sleep(500);

                    //MethodProvider.sleepUntil(() -> main.getLocalPlayer().getAnimation() == 625, 400);
                }
                break;
            case 1:
                //====== Copper
                rock = main.getGameObjects().closest(i -> i != null &&
                        i.getName().equals("Rocks") && i.getID() == 11161);
                MethodProvider.sleep(Calculations.random(200, 1200));
                if (rock != null) {
                    main.getMouse().click(rock);
                    if (Math.random() > .8) {
                        main.getMouse().click(rock);
                    }
                }
                MethodProvider.sleep(2500);
                MethodProvider.sleepUntil(() -> main.getLocalPlayer().getAnimation() == 625, 400);

                while (main.getLocalPlayer().getAnimation() != -1 || main.getLocalPlayer().isAnimating() || main.getLocalPlayer().isInteractedWith()) {
                    MethodProvider.sleepUntil(() -> main.getLocalPlayer().getAnimation() != -1, 500);

                    MethodProvider.sleep(1500);


                }
                break;
        }
    }

    private int oreID(String ore) {

        switch (ore) {
            case "Tin":
                return 11360;
            case "Copper":
                return 11161;
            case "Clay":
                return 11363;
            case "Iron":
                return 11365;
            case "Gold":
                return 11111;
            case "Silver":
                // return 11363;
            case "Coal":
                return 11366;
            default:
                return 0;
        }
    }

    private Short rockColor(String ore) {

        switch (ore) {
            case "Tin":
                return 53;
            case "Copper":
                return 4645;
            case "Clay":
                return 6705;
            case "Iron":
                return 2576;
            case "Gold":
                return 11111;
            case "Silver":
                // return 11363;
            case "Coal":
                return 10508;
            default:
                return 0;
        }
    }

}
