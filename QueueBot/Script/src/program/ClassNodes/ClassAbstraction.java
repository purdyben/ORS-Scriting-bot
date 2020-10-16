package program.ClassNodes;


import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import program.ClassNodes.Utility.MoveToArea;
import program.main;

public abstract class ClassAbstraction<T> implements Interface {
    protected main main;

    public ClassAbstraction(main main) {
        this.main = main;
    }

    public boolean validate() {
        return false;
    }

    public int execute() {
        return 0;
    }

    public boolean underAttack() {
        if (main.getLocalPlayer().isHealthBarVisible()) {
            return true;
        }
        return false;
    }

    public void fleeFromAttack() {
        Tile tile = main.getLocalPlayer().getTile();
        main.getWalking().walk(new Tile(tile.getGridX() + flee(), tile.getGridY() + 12 + flee()));
    }

    public void fleeFromAttack(Tile tile) {
        main.getWalking().walk(tile);
    }

    public void fleeFromAttack(Area area) {
        new MoveToArea(main, area);
    }

    private int flee() {
        double n = Math.random();
        if (n < .25) {
            return 12;
        } else if (n > .24 && n < .5) {
            return 0;
        } else {
            return -24;
        }
    }

}
