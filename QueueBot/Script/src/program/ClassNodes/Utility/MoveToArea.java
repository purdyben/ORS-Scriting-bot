package program.ClassNodes.Utility;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import program.ClassNodes.ClassAbstraction;

public class MoveToArea extends ClassAbstraction {

    private Area area = null;
    private Tile tile = null;
    public MoveToArea(program.main main) {
        super(main);
    }
    public MoveToArea(program.main main, Area area) {
        super(main);
        this.area = area;

    }
    public MoveToArea(program.main main, Tile tile) {
        super(main);
        this.area = tile.getArea(0);

    }

    /**
     * is the player with in the area
     *
     * @return
     */
    @Override
    public boolean validate() {
        return area.contains(main.getLocalPlayer());
    }

    /**
     * Walk to Area area before returning
     *
     * @return
     */
    @Override
    public int execute() {
        MethodProvider.log("Walking to area MoveToArea : Node");
        while (!validate()) {
            Tile tile = area.getCenter().getArea(1).getRandomTile();
            main.getWalking().walk(tile);
            if (!main.getLocalPlayer().isOnScreen()) {
                main.getCamera().mouseRotateToEntity(main.getLocalPlayer());
            }

            while(main.getLocalPlayer().isMoving() && !validate()){
                MethodProvider.sleepUntil(() -> main.getLocalPlayer().isMoving(), Calculations.random(100, 300));
            }

        }
        MethodProvider.log("Exit MoveToArea : Node");
        return 1;
    }
    public void setArea(Area area){
        this.area = area;
    }
    public void moveToNewArea(Area area){
        setArea(area);
        this.execute();
    }

}
