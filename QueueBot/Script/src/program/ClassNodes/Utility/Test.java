package program.ClassNodes.Utility;

import org.dreambot.api.methods.MethodProvider;
import program.ClassNodes.ClassAbstraction;
import program.main;

public class Test extends ClassAbstraction {
    public Test(program.main main, int num) {
        super(main);
    }

    @Override
    public int execute() {
        try {
            main.getWidgets().getWidgetChild(162, 51).interact();
        }catch (Exception e){
            MethodProvider.log(e + "couldn't findWidget");

        }finally {
            MethodProvider.sleep(1000);
            main.getKeyboard().type("Pot of flour");
        }
        while(main.getKeyboard().isTyping()){
            MethodProvider.sleep(300);
        }
        MethodProvider.sleep(1000);
        //================== select the item
        main.getGrandExchange().addBuyItem("Pot of flour");


        return 1;
    }
}
