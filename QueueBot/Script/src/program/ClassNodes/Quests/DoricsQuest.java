package program.ClassNodes.Quests;

import org.dreambot.api.methods.MethodProvider;
import program.ClassNodes.ClassAbstraction;

public class DoricsQuest extends ClassAbstraction {
    public DoricsQuest(program.main main) {
        super(main);
    }

    @Override
    public boolean validate() {
        return (main.getPlayerSettings().getConfig(31) & 0b1100100) == 0b1100100;
    }

    @Override
    public int execute() {
        MethodProvider.log(Boolean.toString(validate()));
        if(!validate()){

        }

        return 0;
    }
}
