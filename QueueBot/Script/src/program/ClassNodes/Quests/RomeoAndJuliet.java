package program.ClassNodes.Quests;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import program.ClassNodes.ClassAbstraction;
import program.ClassNodes.Utility.MoveToArea;
import program.main;



public class RomeoAndJuliet extends ClassAbstraction {

    public RomeoAndJuliet(program.main main) {
        super(main);
    }

    @Override
    public boolean validate() {
        return main.getPlayerSettings().getConfig(144) < 100;
    }
    private void dialogue(String[] OPTIONS){
        //final String[] OPTIONS = {"Hey, you look worried.", "How can I help?", "Yes."};
        while (main.getDialogues().inDialogue()){
            if (!main.getDialogues().continueDialogue()) {
                MethodProvider.sleep(Calculations.random(110,300));
                for(int i = 0 ; i < OPTIONS.length; i++){
                    if (main.getDialogues().chooseOption(OPTIONS[i])) {
                        break;
                    }
                }
            }else {
                main.getDialogues().continueDialogue();
            }
        }
    }
    @Override
    public int execute() {

        MoveToArea moveToArea = new MoveToArea(main);
        MethodProvider.log("Starting RomeoAndJuliet");
        if(validate()){
            if(main.getPlayerSettings().getConfig(144) < 10){
                // Talk to Romeo
                moveToArea.moveToNewArea(getAreas("Romeo"));
                NPC Romeo = main.getNpcs().closest("Romeo");
                main.getMouse().click(Romeo);
                dialogue(new String[]{"Yes, I have seen her", "I think it was her. Blond, stressed",
                        "Yes, I will tell her", "err, yes. Ok. That's.... nice."});

            }
            MethodProvider.sleep(200);

            if(main.getPlayerSettings().getConfig(144) < 20){
                MethodProvider.log("Juliet 1");

                // Talk to Juliet
                moveToArea.moveToNewArea(getAreas("Juliet house"));

                GameObject Staircase = main.getGameObjects().closest("Staircase");

                if (!Staircase.isOnScreen()) {
                    main.getCamera().mouseRotateToEntity(Staircase);
                }

                Staircase.interact("Climb-up");
                MethodProvider.sleepUntil(() -> main.getLocalPlayer().isInteractedWith(), Calculations.random(400,1100));

                MethodProvider.sleep(Calculations.random(4000,5000));


                moveToArea.moveToNewArea(getAreas("Juliet"));
                MethodProvider.sleep(Calculations.random(500,800));
                NPC Juliet =  main.getNpcs().closest("Juliet");
                if (!Juliet.isOnScreen()) {
                    main.getCamera().mouseRotateToEntity(Juliet);
                }
                Juliet.interact();

                MethodProvider.sleepUntil(() -> main.getLocalPlayer().isInteractedWith(), Calculations.random(400,1100));

                MethodProvider.sleep(3000);

                dialogue(new String[]{"Juliet, I come from Romeo", "He begs me tell you he cares still",
                        "Certinly, I will deliver your message straight away"});
                MethodProvider.sleep(Calculations.random(100,623));
//
//                Staircase = main.getGameObjects().closest("Staircase");
//                  Staircase.interact("Climb-up");
//                MethodProvider.sleepUntil(() -> main.getLocalPlayer().isInteractedWith(), Calculations.random(400,1100));
//
//                MethodProvider.sleep(Calculations.random(2000,2222));
            }

            MethodProvider.sleep(1202);

            if(main.getPlayerSettings().getConfig(144) < 30){
//                MethodProvider.log("Romeo 2");
//                // Talk to Romeo
//                moveToArea.moveToNewArea(getAreas("Romeo"));
//                NPC Romeo = main.getNpcs().closest("Romeo");
//                main.getMouse().click(Romeo);
//                dialogue(new String[]{"Romeo, I have a message from Juliet"});
//                MethodProvider.sleep(Calculations.random(1300,2000));

            }
            MethodProvider.sleep(982);
            if(main.getPlayerSettings().getConfig(144) < 40){
//                MethodProvider.log("Father Lawrence");
//                // talk to Father Lawrence
//                moveToArea.moveToNewArea(getAreas("Father"));
//                MethodProvider.sleep(Calculations.random(1300,2000));
//                NPC FatherLawrence = main.getNpcs().closest("Father Lawrence");
//                main.getMouse().click(FatherLawrence);
//                MethodProvider.sleep(Calculations.random(100,623));
//                dialogue(new String[]{"Romeo sent me. Hey says you can help", "Juliet must be rescued from her fathers control"});
//                MethodProvider.sleep(Calculations.random(100,623));
            }
            MethodProvider.sleep(982);
            if(main.getPlayerSettings().getConfig(144) < 50){
                MethodProvider.log("Apothecary");
                // talk to cadava berries
                // talk to Apothecary with Cadava berries
            }
            MethodProvider.sleep(982);
            if(main.getPlayerSettings().getConfig(144) < 60){
                MethodProvider.log("Juliet 2");
                // talk to Juliet with potion
            }
            MethodProvider.sleep(982);
            if(main.getPlayerSettings().getConfig(144) < 100){
                MethodProvider.log("Romeo 3");
                // talk to Romeo and finish

            }
        }else{
            MethodProvider.log("DidntRun RomeoAndJuliet");
        }
        MethodProvider.log("Ending RomeoAndJuliet");
        return 0;
    }
    private Area getAreas(String name){
        switch (name){
//            case "Varrock Square":
//                return  new Area(3207,3426,3216,3431);
            case "Romeo":
                return  new Area(3207,3426,3216,3431);
            case "Juliet house":
                return new Area(3162,3434, 3160,3426);
            case "Juliet":
                return new Tile(3159,3426, 1).getArea(0);
            case "Stairs down":
                return  new Area(new Tile(3155,3455));
            case "Father":
                return  new Area(3253,3482,3255,3479);
            case "Apothecary":
                return  new Area(new Tile(3193,3403));
                default:
                return new Area();
        }
    }


}
