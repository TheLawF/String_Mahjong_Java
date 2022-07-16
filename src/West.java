public class West extends South{
    boolean westLoop = false;
    public final String name = "西家";

    @Override
    public void abandonCardAI(){
        this.abandonArea.add(this.owned.get(this.owned.size()-1));
        this.owned.remove(this.owned.size()-1);
        System.out.println("西家的弃牌堆："+this.abandonArea);
    }
}
