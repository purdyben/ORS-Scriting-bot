package program.ClassNodes;
import org.dreambot.api.methods.map.Area;
import program.main;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.Calculations;

public class movmentNode extends ClassAbstraction {
  private Area area;
   public movmentNode(main main, Area area){
       super(main);
       this.area = area;
   }
    @Override
    public boolean validate(){
        return area.contains(main.getLocalPlayer());
    }
    @Override
    public int execute(){
      while(validate()){

      }
        main.getWalking().walk(main.getLocalPlayer().getSurroundingArea(5).getRandomTile());
        MethodProvider.sleepUntil(() -> !main.getLocalPlayer().isMoving(), Calculations.random(2000, 4000));
        System.out.println("walking");
        return 1000;
    }
}
