import java.util.List;

public class South extends Banker{
    boolean isSouth = true;
    boolean southLoop = false;
    public final String name = "南家";

    @Override
    public void getCard(List<String> walls, List<String> owned) {
        String get = walls.get(0);
        walls.remove(walls.get(0));
        owned.add(get);
    }

    public void abandonCardAI()
    {
        this.abandonArea.add(this.owned.get(this.owned.size()-1));
        this.owned.remove(this.owned.size()-1);
        System.out.println("南家的弃牌堆："+this.abandonArea);
    }

    public void cardCallingAI()
    {
        this.loopCalling = true;
        this.ifCalling = false;
    }

    public void getCardAI(List<String> walls, List<String> owned)
    {
        String get = walls.get(0);
        walls.remove(walls.get(0));
        owned.add(get);
    }

}
