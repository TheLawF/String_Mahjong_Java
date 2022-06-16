public class North extends South{
    boolean northLoop = false;
    public final String name = "北家";

    @Override
    public void abandonCardAI(){
        this.abandonArea.add(this.owned.get(this.owned.size()-1));
        this.owned.remove(this.owned.size()-1);
        System.out.println("北家的弃牌堆："+this.abandonArea);
    }

}
