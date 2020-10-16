package script.ClassNodes;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.GameObject;

class combat extends AbstractScript  {
    protected enum State{

    }

    @Override
    public int onLoop() {
        GameObject stall = null;
        return Calculations.random(500, 600);
    }
}
