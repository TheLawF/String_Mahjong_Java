import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Tools {

    String key = "";
    int result = 0;
    File file;
    Font type;

    public Tools(){

    }

    public Font setTypeFont(){
        file = new File("GL-MahjongTile.ttf");

        try {
            FileInputStream fi = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fi);
            this.type = Font.createFont(Font.TRUETYPE_FONT, bis);
            this.type.deriveFont(Font.PLAIN, 38);
            GraphicsEnvironment ge = GraphicsEnvironment.
                    getLocalGraphicsEnvironment();
            ge.registerFont(this.type);
        }catch (Exception e) {
        }
        return this.type;
    }

    // 这个方法用于判断传入的列表 list中是否存在着多个重复的字符串元素 s
    public int countListRepeatedStrings (List<String> list,String element)
    {
        String[] allCards = (new String[list.size()]);
        list.toArray(allCards);
        int count = 0;
        for (String allCard : allCards) {
            if (Objects.equals(element, allCard)) {
                count++;
            }
        }

        return count;
    }

    public <E> List<E> getDuplicateElements(List<E> list)
    {
        return list.stream()                              // list 对应的 Stream
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum)) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
                .entrySet()
                .stream()                              // 所有 entry 对应的 Stream
                .filter(e -> e.getValue() > 1)         // 过滤出元素出现次数大于 1 (重复元素）的 entry
                .map(Map.Entry::getKey)                // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList());         // 转化为 List
    }


    /**判断一个元素是否在列表中，并返回它重复的次数。
     * <p>Compare whether an element is in the list, and return its apppearing
     * number if the element is repeated.
     * @return count
     */
    public int countElements(List<String> list, String element)
    {
        this.key = element;
        int count = 0;
        Map<String,Integer> map = new HashMap<>();

        for (String str:list)
        {
            int i = 1;
            if (map.get(str) != null) {
                i = map.get(str) + 1;
            }
            map.put(str,i);
        }

        boolean contains = list.contains(this.key);
        if (contains) {
            for (String s : map.keySet()) {
                s = this.key;
                count = map.get(s);
            }
        }
        else
        {
            return 0;
        }
        map.clear();
        return count;

    }

    public int countElements(Collection<Object> collection, Object obj){

        return 0;
    }
}
