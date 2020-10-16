package program.Modules;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.methods.map.Area;
import program.ClassNodes.ClassAbstraction;
import program.ClassNodes.Utility.Bank;
import program.ClassNodes.Utility.MoveToArea;

public class TreeChoppingUnder35 extends ClassAbstraction {
   private Area treeArea;
   private Enum Name = null;
    private enum State {
        TREE, OAK, WILLOW, BANK, WAIT
    }

    public TreeChoppingUnder35(program.main main, Area treeArea) {
        super(main);
        this.treeArea = treeArea;
    }
    public TreeChoppingUnder35(program.main main, Area treeArea, String Name) {
        super(main);
        this.treeArea = treeArea;
        if(Name.equals("TREE"))
            this.Name = State.TREE;
        if(Name.equals("OAK"))
            this.Name = State.OAK;
        if(Name.equals("WILLOW"))
            this.Name = State.WILLOW;
        if(Name.equals("BANK"))
            this.Name = State.BANK;
    }


    @Override
    public boolean validate() {
        return !main.getLocalPlayer().isAnimating();
    }

    @Override
    public int execute() {
        int WoodLevel = main.getSkills().getRealLevel(Skill.WOODCUTTING);
        MethodProvider.log(Integer.toString(WoodLevel));
        this.WookCutterScript();


        return 100;

    }
    private State getState() {
        if (main.getInventory().isFull())
            return State.BANK;
        if (this.Name != null)
            return (State) this.Name;
        if (main.getSkills().getRealLevel(Skill.WOODCUTTING) < 15)
            return State.TREE;
        if (main.getSkills().getRealLevel(Skill.WOODCUTTING) < 30)
            return State.OAK;
        if (main.getSkills().getRealLevel(Skill.WOODCUTTING) < 60)
            return State.WILLOW;
        return State.WAIT;
    }
        public void WookCutterScript() {
//        Area treeArea =  new Area(3101,3283,3097,3290);
            if (main.getTabs().isOpen(Tab.INVENTORY)) {
                main.getTabs().openWithMouse(Tab.INVENTORY);
            }
            //Area treeArea =  new Area(new Tile(3101,3283,0), new Tile(3097,3290,0));
            if (!main.getLocalPlayer().isOnScreen()) {
                main.getCamera().rotateToEntity(main.getLocalPlayer());
            }
//            if(getState() == State.BANK){
//                MethodProvider.log(" Banking");
//                Bank B = new Bank(main);
//                B.NearestBank();
//                main.getBank().depositAllExcept(i -> i.getName().equals("Bronze axe") || i.getName().equals("Steel axe") ||
//                        i.getName().equals("Back axe") || i.getName().equals("Tinderbox"));
//            }


            main.log("walking to tree area");

            while (!treeArea.contains(main.getLocalPlayer()) && !main.getInventory().isFull()) {
                main.getWalking().walk(treeArea.getCenter().getTile());
                MethodProvider.sleepUntil(() -> !main.getLocalPlayer().isMoving(), Calculations.random(4000, 5200));
            }
            GameObject tree = null;

           for(int invNumber = 0; invNumber < 2; invNumber++ ){
//
               switch (getState()) {
                   case TREE:
                       getInventory("Tree");
                       break;
                   case OAK:
                       getInventory("Oak tree");
                       break;
                   case WILLOW:
                       getInventory("Willow tree");
                       break;
                   default:

                       break;
               }
               MethodProvider.log(" Banking");
               Bank B = new Bank(main);
               B.NearestBank();
               main.getBank().depositAllExcept(i -> i.getName().equals("Bronze axe") || i.getName().equals("Steel axe") ||
                       i.getName().equals("Back axe") || i.getName().equals("Tinderbox"));
               main.getBank().close();
           }

//        //main.getWalking().walk(main.getLocalPlayer().getSurroundingArea(5).getRandomTile());
//      //  getDestination()
//
//    }
        }
   private void getInventory(String treeType){
       MethodProvider.log("getInventory");
       while(!main.getInventory().isFull()){

            GameObject tree = main.getGameObjects().closest(treeType);

           if (!main.getLocalPlayer().isOnScreen()) {
               main.getCamera().rotateToEntity(main.getLocalPlayer());
           }
           MethodProvider.log(tree.toString() + "Looking for a tree");

           if (treeArea.contains(main.getLocalPlayer())) {
                if(!tree.isOnScreen()){
                    main.getWalking().walk(tree);
                    MethodProvider.sleepUntil(() -> !main.getLocalPlayer().isMoving(), Calculations.random(2000, 3769));

                }else{
                    main.getMouse().click(tree);
                    if (Math.random() > .8) {
                        main.getMouse().click(tree);
                    }
                    MethodProvider.log("Chopping tree");
                    MethodProvider.sleepWhile(tree::exists, Calculations.random(2000, 3769));
                    long time = System.currentTimeMillis()/1000;
                    while(tree.exists() && time + 6 > System.currentTimeMillis()/1000){
                        //====================== Click to level up
                        if (main.getDialogues().inDialogue()) {
                            main.getDialogues().clickContinue();
                        }
                        MethodProvider.sleepWhile(tree::exists, Calculations.random(2000, 3769));
                    }
                }
            }else if(tree.getName() == null){
               while(tree.getName() == null){
                   MethodProvider.sleep(10000);
               }
           }
           else{
             //  main.getWalking().walk(treeArea.getCenter().getArea(2).getRandomTile());
               new MoveToArea(main, treeArea.getCenter().getArea(2)).execute();

               //main.sleepWhile(() -> !main.getLocalPlayer().isMoving(), 500);
           }
        }
       MethodProvider.log("The end");

   }


}
