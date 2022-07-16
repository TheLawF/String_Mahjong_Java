import java.util.*;

public class Rules {

    String[] colorWan = {"🀇", "🀈", "🀉", "🀊", "🀋", "🀌", "🀍", "🀎", "🀏"};
    String[] colorTiao = {"🀐", "🀑", "🀒", "🀓", "🀔", "🀕", "🀖", "🀗", "🀘"};
    String[] colorTong = {"🀙", "🀚", "🀛", "🀜", "🀝", "🀞", "🀟", "🀠", "🀡"};
    String[] chaWind = {"🀀", "🀁", "🀂", "🀃"};
    String[] chaArrow = {"🀄", "🀅", "🀆"};
    String [] allcards = {"🀀", "🀁", "🀂", "🀃","🀄", "🀅", "🀆","🀇", "🀈", "🀉", "🀊", "🀋", "🀌", "🀍", "🀎", "🀏",
            "🀐", "🀑", "🀒", "🀓", "🀔", "🀕", "🀖", "🀗", "🀘","🀙", "🀚", "🀛", "🀜", "🀝", "🀞", "🀟", "🀠", "🀡"};
    String[] mahjong = {"🀇", "🀈", "🀉", "🀊", "🀋", "🀌", "🀍", "🀎", "🀏","🀙", "🀚", "🀛", "🀜", "🀝", "🀞", "🀟", "🀠", "🀡",
            "🀐", "🀑", "🀒", "🀓", "🀔", "🀕", "🀖", "🀗", "🀘","🀀", "🀁", "🀂", "🀃","🀆","🀅","🀄"};
    /*
    ArrayList<String> wanList = (ArrayList<String>) Arrays.asList(colorWan);
    ArrayList<String> tiaoList = (ArrayList<String>) Arrays.asList(colorTiao);
    ArrayList<String> tongList = (ArrayList<String>) Arrays.asList(colorTong);
    ArrayList<String> windList = (ArrayList<String>) Arrays.asList(chaWind);
    ArrayList<String> arrowList = (ArrayList<String>) Arrays.asList(chaArrow);
    */
    HashMap<Integer, String> map = new HashMap<>();
    ArrayList<Integer> keyList = new ArrayList<>();
    List<String> valueList = Arrays.asList(mahjong);
    String[] result = new String[5];
    String[] triplets = new String[5];
    String[] flushes = new String[5];

    boolean zimo = false;      // 自摸
    boolean bloom = false;     // 杠上花
    boolean toSpring = false;  // 妙手回春
    boolean salvage = false;   // 海底捞
    boolean side = false;      // 边张
    boolean between = false;   // 坎张
    boolean single = false;    // 单钓
    boolean last = false;      // 绝张
    String roundWind = "🀀";    // 圈风
    String seatWind = "🀀";     // 门风

    final String[] alones = {"🀐","🀘","🀇","🀏","🀙","🀡","🀀","🀁","🀂","🀃","🀄","🀅","🀆"};
    final String[][] lantern = {{"🀇", "🀇", "🀇", "🀈", "🀉", "🀊", "🀋", "🀌", "🀍", "🀎", "🀏", "🀏", "🀏"},
            {"🀐","🀐","🀐","🀑","🀒","🀓","🀔","🀕","🀖","🀗","🀘","🀘","🀘"},
            {"🀙","🀙","🀙","🀚","🀛","🀜","🀝","🀞","🀟","🀠","🀡","🀡","🀡"}};
    final String[] green = {"🀑", "🀒", "🀓", "🀕", "🀗", "🀅"};

    int num = 14;

    String[] groups = new String[5];
    Tools tools = new Tools();
    int score = 1;
    boolean win = false;

    public Rules() {

    }

    public int[] mappingHai(ArrayList<String> hai){
        // 将原麻将手牌从 ArrayList 数据类型映射为 int[]数组
        int[] integerHai = new int[14];

        for (int i = 0; i < 34; i++) {
            keyList.add(i);
            map.put(keyList.get(i),valueList.get(i));
        }
        for (Map.Entry<Integer,String> entry : map.entrySet()) {
            Integer k = entry.getKey();
            String v = entry.getValue();
            for (int j = 0; j < hai.size(); j++) {
                if (Objects.equals(hai.get(j), v)){
                    integerHai[j] = k;
                }
            }
        }
        return integerHai;
    }

    public void getAgariret(List<Integer[][]> agariret){
        //将原整型数组化麻将手牌还原为字符串列表，整型数值请见 AgariBacktrack.java文件。
    }

    public String[] mappingStr(List<Integer[][]> intArray){

        keyList.clear();
        map.clear();
        for (int i = 0; i < 34; i++) {
            keyList.add(i);
            map.put(keyList.get(i),valueList.get(i));
        }
        for (Map.Entry<Integer,String> entry : map.entrySet()) {
            Integer k = entry.getKey();
            String v = entry.getValue();
            for (Integer[][] r : intArray) {
                if (Objects.equals(k, r[0][0])){
                    result[0] = v;
                }
                for (int j = 0 ;j < r[1].length; j++) {
                    if (Objects.equals(k,r[1][j])){
                        String s = mahjong[k];
                        String str = s;
                        s += s;
                        s += str;
                        triplets[j] = s;
                    }
                }
                for (int j = 0 ;j < r[2].length; j++) {
                    if (Objects.equals(k,r[2][j])){
                        String s = mahjong[k];
                        s += mahjong[k+1];
                        s += mahjong[k+2];
                        flushes[j] = s;
                    }
                }
            }
        }
        int iterTimes = 0;
        for (int i = 0; i < triplets.length; i++) {
            if (triplets[i] == null){
                break;
            }
            else {
                result[i+1] = triplets[i];
                iterTimes += 1;
            }
        }
        for (int i = 0; i < flushes.length; i++) {
            if (flushes[i] == null){
                break;
            }
            else {
                result[iterTimes + i + 1] = flushes[i];
            }
        }
        return result;
    }

    public boolean isZimo(boolean zimo){
        this.zimo = zimo;
        return this.zimo;
    }

    public boolean isBloomed(boolean bloom){
        this.bloom = bloom;
        return this.bloom;
    }

    public boolean isToSpring(boolean toSpring){
        this.toSpring = toSpring;
        return this.toSpring;
    }

    public boolean isSalvage(boolean salvage){
        this.salvage = salvage;
        return this.salvage;
    }

    public boolean isSide(boolean side){
        this.side = side;
        return this.side;
    }

}
