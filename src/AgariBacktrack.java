import java.io.*;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class AgariBacktrack {

    // Rules.java里面的 mappingHai()方法将会把玩家的手牌映射为如下的整型静态常量，以方便
    // 这里的代码进行判断。
    static final int MAN = 0;
    static final int MAN1 = 0;
    static final int MAN2 = 1;
    static final int MAN3 = 2;
    static final int MAN4 = 3;
    static final int MAN5 = 4;
    static final int MAN6 = 5;
    static final int MAN7 = 6;
    static final int MAN8 = 7;
    static final int MAN9 = 8;
    static final int PIN = 9;
    static final int PIN1 = 9;
    static final int PIN2 = 10;
    static final int PIN3 = 11;
    static final int PIN4 = 12;
    static final int PIN5 = 13;
    static final int PIN6 = 14;
    static final int PIN7 = 15;
    static final int PIN8 = 16;
    static final int PIN9 = 17;
    static final int SOU = 18;
    static final int SOU1 =18;
    static final int SOU2 =19;
    static final int SOU3 =20;
    static final int SOU4 =21;
    static final int SOU5 =22;
    static final int SOU6 =23;
    static final int SOU7 =24;
    static final int SOU8 =25;
    static final int SOU9 =26;
    static final int TON = 27;
    static final int NAN = 28;
    static final int SHA = 29;
    static final int PEI = 30;
    static final int HAK = 31;
    static final int HAT = 32;
    static final int CHU = 33;
    static final int HARU = 34;
    static final int NATSU = 35;
    static final int AKI = 36;
    static final int FUYU = 37;
    static final int BAMBOO = 38;

    String[] owned = new String[5];
    int winNum = 0;

    static final int[] n_zero;
    static {
        n_zero = new int[34];
        Arrays.fill(n_zero, 0);
    }

    // 牌の種類ごとの個数を求める
    public int[] analyse(int[] hai) {
        int[] n = n_zero.clone();

        for (int i : hai) {
            n[i]++;
        }
        return n;
    }

    // バックトラック法で雀頭と面子の組み合わせを求める
    public List<Integer[][]> agari(int[] n) {
        List<Integer[][]> ret = new ArrayList<Integer[][]>();

        for (int i = 0; i < 34; i++) {
            for (int kotsu_first = 0; kotsu_first < 2; kotsu_first++) {
                Integer[] janto = new Integer[1];
                ArrayList<Integer> kotsu = new ArrayList<Integer>();
                ArrayList<Integer> shuntsu = new ArrayList<Integer>();

                int[] t = n.clone();
                if (t[i] >= 2) {
                    // 雀頭をはじめに取り出す
                    t[i] -= 2;
                    janto[0] = i;

                    if (kotsu_first == 0) {
                        // 刻子を先に取り出す
                        for (int j = 0; j < 34; j++) {
                            if (t[j] >= 3) {
                                t[j] -= 3;
                                kotsu.add(j);
                            }
                        }
                        // 順子を後に取り出す
                        for (int a = 0; a < 3; a++) {
                            for (int b = 0; b < 7;) {
                                if (t[9*a+b] >= 1 &&
                                        t[9*a+b+1] >= 1 &&
                                        t[9*a+b+2] >= 1) {
                                    t[9*a+b]--;
                                    t[9*a+b+1]--;
                                    t[9*a+b+2]--;
                                    shuntsu.add(9*a+b);
                                } else {
                                    b++;
                                }
                            }
                        }
                    } else {
                        // 順子を先に取り出す
                        for (int a = 0; a < 3; a++) {
                            for (int b = 0; b < 7;) {
                                if (t[9*a+b] >= 1 &&
                                        t[9*a+b+1] >= 1 &&
                                        t[9*a+b+2] >= 1) {
                                    t[9*a+b]--;
                                    t[9*a+b+1]--;
                                    t[9*a+b+2]--;
                                    shuntsu.add(9*a+b);
                                } else {
                                    b++;
                                }
                            }
                        }
                        // 刻子を後に取り出す
                        for (int j = 0; j < 34; j++) {
                            if (t[j] >= 3) {
                                t[j] -= 3;
                                kotsu.add(j);
                            }
                        }
                    }

                    // 和了の形か調べる
                    if (Arrays.equals(t, n_zero)) {
                        ret.add(new Integer[][] {janto, kotsu.toArray(new Integer[0]), shuntsu.toArray(new Integer[0])});
                    }
                }
            }
        }
        return ret;
    }

    // 注意：这个 String card 如果是点和，那么要让 banker.get等于点和的那张牌
    public void normal(int[] hai, ArrayList<String> strHai, ArrayList<String> strPeng,
                       ArrayList<String> strgang, String card, boolean ziMo,
                       boolean strSalve, boolean strSpring, boolean strBloom) {

        /*hai = new int[]{MAN1, MAN1, MAN1,
                MAN2, MAN3, MAN4,
                MAN6, MAN7, MAN8,
                TON, TON, TON,
                SHA, SHA};
         */

        int[] n = null;
        List<Integer[][]> ret = null;

        long time = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            n = analyse(hai);
            ret = agari(n);
        }

        Rules rules = new Rules();
        String[] result = rules.mappingStr(ret);
        String[] triplets = rules.triplets;
        String[] flushes = rules.flushes;

        boolean isSided = rules.side;
        boolean isSingle = rules.single;
        boolean isBetween = rules.between;

        String roundWind = rules.roundWind;
        String seatWind = rules.seatWind;
        System.out.println(Arrays.toString(result));

        ArrayList<String> list = new ArrayList<>(Arrays.asList(result));
        if (Objects.equals(card,result[0])) {
            isSingle = true;
        }
        for (int i = 0; i < result.length; i++) {
            if (i > 0 && Objects.equals(card, result[i].substring(0, 2)) &&
                    !Objects.equals(result[i].substring(0,2),result[i].substring(2,4))) {
                isSided = true;
            }
            else if (i > 0 && Objects.equals(card, result[i].substring(2, 4)) &&
                    !Objects.equals(result[i].substring(0,2),result[i].substring(2,4))) {
                isBetween = true;
            }
            else if (i > 0 && Objects.equals(card, result[i].substring(4, 6)) &&
                    !Objects.equals(result[i].substring(0,2),result[i].substring(2,4))) {
                isSided = true;
            }
            else {
                break;
            }
        }

        String[] chi = new String[4];
        ArrayList<String> peng = new ArrayList<>(strPeng);
        ArrayList<String> gang = new ArrayList<>(strgang);

        String dataZimo;
        String dataSalve;
        String dataSpring;
        String dataBloom;
        String dataSide;
        String dataSingle;
        String dataBetween;

        if (ziMo) {
            dataZimo = "True\n";
        }
        else {
            dataZimo = "False\n";
        }
        if (strSalve){
            dataSalve = "True\n";
        }
        else {
            dataSalve = "False\n";
        }
        if (strSpring) {
            dataSpring = "True\n";
        }
        else {
            dataSpring = "False\n";
        }
        if (strBloom){
            dataBloom = "True\n";
        }
        else {
            dataBloom = "False\n";
        }
        if (isSided){
            dataSide = "True\n";
        }
        else {
            dataSide = "False\n";
        }
        if (isSingle){
            dataSingle = "True\n";
        }
        else {
            dataSingle = "False\n";
        }
        if (isBetween){
            dataBetween = "True\n";
        }
        else {
            dataBetween = "False\n";
        }

        try {
            // 在这里将玩家手牌写入文本文档 mahjong_data.txt
            BufferedWriter txt = new BufferedWriter(new FileWriter("mahjong_data.txt"));
            txt.write("[");
            for (int i = 0; i < strHai.size();i++){
                if (i < strHai.size()-1) {
                    txt.write("\""+strHai.get(i)+"\",");
                }
                else {
                    txt.write("\""+strHai.get(i)+"\"");
                }
            }
            txt.write("]\n");

            // 在这里将玩家的手牌组合写入文本文档
            txt.write("[");
            for (int i = 0; i < result.length; i++){
                if (i < result.length-1) {
                    txt.write("\""+result[i]+"\",");
                }
                else {
                    txt.write("\""+result[i]+"\"");
                }
            }
            txt.write("]\n");

            // 在这里将玩家的碰牌写入文本文档
            txt.write("[");
            for (int i = 0; i < peng.size(); i++) {
                if (i < peng.size()-1) {
                    txt.write("\""+peng.get(i)+"\",");
                }
                else {
                    txt.write("\""+peng.get(i)+"\"");
                }
            }
            txt.write("]\n");

            // 在这里将玩家的杠牌写入文本文档
            txt.write("[");
            for (int i = 0; i < gang.size(); i++) {
                if (i < peng.size()-1) {
                    txt.write("\""+gang.get(i)+"\",");
                }
                else {
                    txt.write("\""+gang.get(i)+"\"");
                }
            }
            txt.write("]\n");

            txt.write(dataZimo);
            txt.write(dataSalve);
            txt.write(dataSpring);
            txt.write(dataBloom);

            txt.write(dataSide);
            txt.write(dataBetween);
            txt.write(dataSingle);

            txt.write("\"" + roundWind + "\"\n");
            txt.write("\"" + seatWind + "\"\n");
            txt.close();

            // 将每次的和牌追加写入"mahjong_text.txt"的末尾。
            BufferedWriter chars = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("mahjong_text.txt",true)));

            chars.write(MessageFormat.format("{0}; ", String.valueOf(strHai)));
            chars.write(Arrays.toString(result) + "; ");
            chars.write("碰="+peng + "; ");
            chars.write("杠="+gang + "; ");

            chars.write("自摸=" + ziMo + "; ");
            chars.write("海底捞月=" + strSalve + "; ");
            chars.write("妙手回春=" + strSpring + "; ");
            chars.write("杠上开花=" + strBloom + "; ");

            chars.write("边张=" + isSided + "-" + card + "; ");
            chars.write("坎张=" + isBetween + "-" + card + "; ");
            chars.write("单钓=" + isSingle + "-" + card + "; ");

            chars.write("圈风=" + roundWind + "; ");
            chars.write("门风=" + seatWind + "; \n\n");
            chars.close();

        }catch (IOException ignored){
        }

        for (Integer[][] r : ret) {
            System.out.print("雀頭=");
            System.out.println(result[0]);
            winNum += 1;
            for (int j = 0 ;j < r[1].length; j++) {
                System.out.print("刻子=");
                System.out.println(triplets[j]);
                winNum += 1;
            }
            for (int j = 0 ;j < r[2].length; j++) {
                System.out.print("顺子=");
                System.out.println(flushes[j]);
                winNum += 1;
            }
        }
        System.out.println(winNum);
    }
}
