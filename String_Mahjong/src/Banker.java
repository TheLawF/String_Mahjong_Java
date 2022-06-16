import java.util.*;
import javax.swing.*;

public class Banker extends Players
{

    static Scanner scanner = new Scanner(System.in);
    boolean loopCalling = true;  // 循环条件，用以决定是否进入循环
    boolean ifCalling = false;
    boolean gangLoop = false;
    public final String name = "庄家";
    public final int unicode = 126976;

    String[] codeArray = {"e","s","w","n","z","f","b","w1","w2","w3","w4","w5","w6","w7","w8","w9","s1","s2","s3","s4","s5","s6","s7","s8","s9",
            "t1","t2","t3","t4","t5","t6","t7","t8","t9"};
    String[] cardArray = {"🀀", "🀁", "🀂", "🀃","🀄", "🀅", "🀆","🀇", "🀈", "🀉", "🀊", "🀋", "🀌", "🀍", "🀎", "🀏","🀐", "🀑", "🀒", "🀓", "🀔", "🀕", "🀖", "🀗", "🀘",
            "🀙", "🀚", "🀛", "🀜", "🀝", "🀞", "🀟", "🀠", "🀡"};
    public final ArrayList<String> codeList = new ArrayList<>(Arrays.asList(codeArray));
    public final ArrayList<String> cardList = new ArrayList<>(Arrays.asList(cardArray));

    ArrayList<String> owned = new ArrayList<>();
    ArrayList<String> peng = new ArrayList<>();
    List<String> gang = new ArrayList<>();
    ArrayList<String> abandonArea = new ArrayList<>();

    int score;
    int doubling;
    boolean isBanker = true;
    boolean playing = true;
    boolean win = false;

    // 这里是玩家摸牌的函数，获取自摸墙上的第一张牌并移除，然后将这张牌添加到玩家手牌
    public void getCard(List<String> walls, List<String> owned)
    {
        String get = walls.get(0);
        walls.remove(walls.get(0));
        owned.add(get);
        System.out.println(Arrays.toString(owned.toArray()));
        System.out.println(peng);
    }


    // 玩家弃牌函数的代码块
    public void abandonCard(String input)
    {

        int number;

        // 这几段是结束游戏，宣告胜利和作弊的代码
        switch (input) {
            case "/end":
                this.playing = false;
                return;

            case "/win":
                this.playing = false;
                this.win = false;
                return;

            case "c":
                cheat(this.owned,input);
                break;
        }

        // 这里使用正则表达式判断输入的数据是否是整型
        /*
        while (!input.matches("\\d+"))
        {
            System.out.print("Illegal input, please input again: ");
            input = scanner.next();
        }



        /* 如果是整型，则来到此处，将字符串数据转化为整型数据作为索引值，将列表手牌拷贝为
         copyOwned数组，并且移除手牌列表和 copyOwned数组索引值相同的元素 */
        this.owned.remove(input);
        this.owned.sort(Comparator.naturalOrder());

        // 将玩家的弃牌放入弃牌堆并打印
        this.abandonArea.add(input);
        System.out.println(name + "的弃牌堆："+this.abandonArea);
    }

    public ArrayList<String> setOwned(ArrayList<String> cardOwned)
    {
        this.owned = cardOwned;
        return this.owned;
    }

    // 获取玩家手牌的列表对象，与摸牌方法的名称可能会产生混淆
    public ArrayList<String> getOwned()
    {
        return this.owned;
    }

    /**
     * 这里是玩家暗杠和加杠的代码，和明杠不同，需要写在玩家鸣牌循环内部。
     */
    public void gangCalling(String abandoned)
    {
        Tools tools = new Tools();
        int result = tools.countListRepeatedStrings(this.owned, abandoned);

        if (result == 4) {
            System.out.print("输入g暗杠，输入其他键跳过：");
            String command = scanner.next();
            if (Objects.equals(command, "g")) {
                String copy = abandoned;
                abandoned += abandoned;
                abandoned += "\uD83C\uDC2B\uD83C\uDC2B";
                this.gang.add(abandoned);
                this.owned.remove(copy);
                this.owned.remove(copy);
                this.owned.remove(copy);
                this.gangLoop = true;
            }
            else
            {
                this.gangLoop = false;
            }
        }
        else
        {
            this.gangLoop = false;
        }
    }

    /** 碰牌和明杠的判定代码，需传入其它玩家弃牌堆中最后一个字符串元素。利用 map对象
     * 判断玩家手牌中的重复元素个数，如果个数超过2，且和传入的弃牌堆元素相同则中断
     * 进程，向玩家发出是否鸣牌的提示。
    */
    public void buttonListening(String abandoned,JButton[] commandButtons, boolean buttonCalling){
        Tools tools = new Tools();
        int result = tools.countListRepeatedStrings(this.owned, abandoned);
        String txt;

        // 酌情去掉该循环
        while (buttonCalling & result >= 2) {
            if (result == 2) {
                //System.out.print("输入p碰牌，输入其他键跳过：");
                for (JButton b : commandButtons) {

                    if (b.getModel().isArmed()) {
                        txt = "p";
                        buttonCalling = false;
                        result = 0;
                    }
                }
            } else if (result == 3) {
                //System.out.print("输入p碰牌，输入g杠牌，输入其他键跳过：");
                for (JButton b : commandButtons) {
                    if (b.getActionCommand().equals("碰")) {
                        txt = "p";
                        buttonCalling = false;
                        break;
                    } else if (b.getActionCommand().equals("杠")) {
                        txt = "g";
                        buttonCalling = false;
                        break;
                    } else if (b.getActionCommand().equals("跳过")) {
                        buttonCalling = false;
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }

    public void cardCalling(String abandoned,String command)
    {
        Tools tools = new Tools();
        int result = tools.countListRepeatedStrings(this.owned,abandoned);

        if (result == 2)
        {
            System.out.println(this.owned);
            System.out.println("输入p碰牌，输入其他键跳过：");

            if (Objects.equals(command, "p"))
            {
                String copy = abandoned;
                abandoned += abandoned;
                abandoned += copy;
                this.peng.add(abandoned); // String abandoned
                this.owned.remove(copy);
                this.owned.remove(copy);
                this.ifCalling = true;
                this.loopCalling = false;
            }
            else
            {
                this.loopCalling = true;
                this.ifCalling = false;
            }
        }
        else if (result == 3)
        {
            System.out.print("输入p碰牌，输入g杠牌，输入其他键跳过：");
            if (Objects.equals(command, "p"))
            {
                String copy = abandoned;
                abandoned += abandoned;
                abandoned += copy;
                this.gang.add(abandoned); // String abandoned
                this.owned.remove(copy);
                this.owned.remove(copy);
                this.ifCalling = true;
                this.loopCalling = false;
            }
            else if (Objects.equals(command,"g"))
            {
                String copy = abandoned;
                abandoned += abandoned;
                abandoned += abandoned;
                this.peng.add(abandoned);
                this.owned.remove(copy);
                this.owned.remove(copy);
                this.owned.remove(copy);
                this.ifCalling = true;
                this.loopCalling = true;
            }
            else
            {
                this.loopCalling = true;
                this.ifCalling = false;
            }
        }
    }

    /**
     * 这里是静态的作弊代码，需传入玩家手牌、玩家内置的伪代码和实际的麻将字符串列表。
     * 作弊函数有两种情况：
     * <p>1. 单张替换，如果测试者在输入“c”进入作弊函数之后，直接输入codeList中的伪代
     * 码，程序将会提示替换哪一张手牌，输入索引值以替换相应的手牌；
     * <p>2. 整体替换，如果测试者在输入“c”进入作弊函数之后，输入“l”进入整体替换代码块，
     * 程序将会提示让测试者输入一个由codeList内的伪代码组成的字符串，以英文逗号分隔，
     * 之后字符串将以英文逗号为分隔变成列表去替换玩家原来的手牌。
     *
     */
    public void cheat(ArrayList<String> owned, String keycode)
    {
        this.owned = owned;
        ArrayList<String> result = new ArrayList<>(this.owned.size());
        int num = 0;
        String[] arr = keycode.split(",");
        System.out.println(Arrays.toString(arr));
        /*
        if (Objects.equals(txt,"l"))
        {
            for (String each : arr) {
                if(codeList.contains(each)){
                    num += 1;
                }
                else
                {
                    try {
                        throw new ElementNotInListException(){};
                    } catch (ElementNotInListException e) {
                        e.printStackTrace();
                        System.out.println("Element" + each + "not in list \""+codeList+"\"");
                    }
                }
            }
        }
         */

        /* 将输入的字符串input转化为数组arr，再用arr的每个元素映射codeList的每个元素
        如果映射成功则为result列表内添加cardList相应的索引所对应的元素
         */
        for (String s : arr) {
            for (int j = 0; j < codeList.size(); j++) {
                if (Objects.equals(s, codeArray[j])) {
                    result.add(cardArray[j]);
                }
            }
        }
        this.owned = result;
    }
}



