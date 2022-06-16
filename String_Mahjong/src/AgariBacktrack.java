import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class AgariBacktrack {

    // 问题在于保证风牌->箭牌->万字->条字->筒字，而这里显然不对
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


    public void normal(int[] hai, ArrayList<String> strHai) {

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

        boolean isZimo = rules.zimo;
        boolean isSalvage = rules.salvage;
        boolean isToSping = rules.toSpring;
        boolean isBloomed = rules.bloom;

        String mahjongData_1 = "";
        String mahjongData_2 = "";
        String mahjongData_3 = "";
        String mahjongData_4 = "";

        if (isZimo) {
            mahjongData_1 = "True\n";
        }
        else {
            mahjongData_1 = "False\n";
        }
        if (isSalvage){
            mahjongData_2 = "True\n";
        }
        else {
            mahjongData_2 = "False\n";
        }
        if (isToSping) {
            mahjongData_3 = "True\n";
        }
        else {
            mahjongData_3 = "False\n";
        }
        if (isBloomed){
            mahjongData_4 = "True\n";
        }
        else {
            mahjongData_4 = "False\n";
        }

        try {
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

            txt.write(mahjongData_1);
            txt.write(mahjongData_2);
            txt.write(mahjongData_3);
            txt.write(mahjongData_4);
            txt.close();

            BufferedWriter chars = new BufferedWriter(new FileWriter("mahjong_text.txt"));

            chars.write(String.valueOf(strHai));
            chars.write(Arrays.toString(result));
            chars.write("自摸=" + isZimo);
            chars.write("海底捞月=" + isSalvage);
            chars.write("妙手回春" + isToSping);
            chars.write("杠上开花" + isBloomed);

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
