package program.ClassNodes.Combat;

import program.ClassNodes.ClassAbstraction;

public class health extends ClassAbstraction {
    public health(program.main main){
        super(main);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        return 0;
    }

}
