package script.ClassNodes;

import script.Main;

public class melee extends ClassAbstraction {
    protected enum State {

    }

    public melee(Main main) {
        super(main);
    }

    @Override
    public boolean validate() {

        return false;
    }

    @Override
    public int execute() {
        System.out.println("hello");
        return 0;
    }
}