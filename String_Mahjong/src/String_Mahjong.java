import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import static java.lang.Thread.sleep;

public class String_Mahjong {
    List wall;
    //String[] all_cards;

    public String_Mahjong(List wall) {
        this.wall = wall;

    }

    public void clickConfirmButton()
    {
        JTextField field = new JTextField(16);
        String content = field.getText();
        if (content.equals(""))
        {
            Object[] options = {"OK","CANCEL"};
            JOptionPane.showOptionDialog(null,"您还没有输入","提示",JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,null,options,options[0]);
        }
    }

    /**
     * 全字符麻将Java窗口主程序：
     *
     */
    public static void main(String[] args) {
        // 调用JFrame和JPanel进行可视化图形编程，
        // 这一部分代码需要放在重开循环外面，防止窗口反复刷新。
        JFrame jf = new JFrame("全字符麻将游戏 Java_Version");
        ImageIcon remiFlan = new ImageIcon("E:\\anything\\pix_image\\yes.png");
        JLabel label = new JLabel(remiFlan);                      // 标签
        JPanel panel = new JPanel();                              // 面板
        JPanel bPanel = new JPanel();
        jf.setLayout(new BorderLayout());
        Box box = Box.createVerticalBox();
        JPanel jp = new JPanel();

        // 实例化容器对象，
        Container container = jf.getContentPane();
        // 设置容器的布局管理器，这里采用的是网格分布式管理
        container.setLayout(new FlowLayout());

        // 设定一些基本的窗口和字体样式
        Dimension d = new Dimension(65, 100);
        Dimension d1 = new Dimension(800, 600);
        Dimension d2 = new Dimension(1080, 640);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        int w = screen.width;
        int h = screen.height;
        int wid = label.getWidth();
        int hei = label.getHeight();

        int scoreNum = 0;

        Font type1 = new Font("Segoe UI Symbol", Font.PLAIN, 38);
        Font type2 = new Font("黑体", Font.BOLD, 40);
        Font type3 = new Font("思源宋体", Font.BOLD, 80);

        File file = new File("fonts\\GL-MahjongTile.ttf");
        Font type = null;

        try {
            FileInputStream fi = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fi);
            type = Font.createFont(Font.TRUETYPE_FONT, bis);
            type.deriveFont(Font.PLAIN, 38);
            GraphicsEnvironment ge = GraphicsEnvironment.
                    getLocalGraphicsEnvironment();
            ge.registerFont(type);
        }catch (Exception e) {
            e.printStackTrace();
        }

        Font type4 = new Font("GL-MahjongTile Regular",Font.PLAIN,45);
        Font type5 = new Font("黑体", Font.BOLD,60);
        Font type6 = new Font("宋体",Font.BOLD,30);

        JButton exitGame = new JButton("退出游戏");
        exitGame.addActionListener(e -> new Thread(() -> {}));
        exitGame.setFont(type5);

        JButton startGame = new JButton("开始游戏");
        JButton settings = new JButton("游戏设置");
        JButton multiGame = new JButton("多人游戏");
        JButton backButton = new JButton("返回主页");

        startGame.addActionListener(e -> new Thread(() -> {}));
        settings.addActionListener(e -> {
            ButtonModel bm = settings.getModel();
            bm.setPressed(false);
        });
        multiGame.addActionListener(e -> {});
        backButton.addActionListener(e -> {
            ButtonModel bm = backButton.getModel();
            bm.setPressed(false);
        });

        startGame.setFont(type5);
        settings.setFont(type5);
        multiGame.setFont(type5);
        backButton.setFont(type2);

        jp.add(label,BorderLayout.NORTH);
        panel.add(startGame,BorderLayout.SOUTH);
        panel.add(multiGame,BorderLayout.SOUTH);
        panel.add(settings,BorderLayout.SOUTH);
        panel.add(exitGame,BorderLayout.SOUTH);

        jf.add(jp);
        jf.add(panel);
        panel.updateUI();
        jf.repaint();

        jf.setSize(w-10,h-10);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocation(10, 10);
        jf.setVisible(true);

        String cardGet = " ";
        boolean zimo = true;
        boolean salvage = false;
        boolean spring = false;
        boolean bloom = false;

        JComboBox<String> musicBox = new JComboBox<>();
        musicBox.setSize(20,30);
        musicBox.addItem("暂时没有音乐哦~");
        musicBox.addItem("暂时没有音乐哦~");
        musicBox.addItem("暂时没有音乐哦~");

        JSlider volume = new JSlider();
        int[] item = {0,1,2,4,6,8,12,16,24,32,64};
        JComboBox<Integer> scoreBox = new JComboBox<>();
        scoreBox.addItem(0);
        scoreBox.addItem(1);
        scoreBox.addItem(2);
        scoreBox.addItem(4);
        scoreBox.addItem(6);
        scoreBox.addItem(8);
        scoreBox.addItem(12);
        scoreBox.addItem(16);
        scoreBox.addItem(24);
        scoreBox.addItem(32);
        scoreBox.addItem(64);

        JTextField scoring = new JTextField(20);

        JLabel musicInfo = new JLabel("音乐选择：");
        JLabel volumeConfig = new JLabel("音量调节：");
        JLabel scoreInfo = new JLabel("设置起和番：");
        musicInfo.setFont(type6);
        volumeConfig.setFont(type6);
        scoreInfo.setFont(type6);
        boolean game = false;

        //游戏最大的循环——重新开始游戏的循环在代码主体最前端进行。
        //The largest loop -- restart loop begins here.
        homePage : while (true){
            System.out.print("");

            ButtonModel settingsModel = settings.getModel();
            ButtonModel backModel = backButton.getModel();

            if (startGame.getModel().isArmed()) {
                panel.removeAll();
                jf.repaint();
                game = true;
            }
            else if (multiGame.getModel().isArmed()) {
                JOptionPane.showMessageDialog(null,"你感到异常孤独",
                        "多人游戏还未实现", JOptionPane.INFORMATION_MESSAGE,null);
            }
            else if (exitGame.getModel().isArmed()) {
                System.exit(1919810);
            }
            else if (settings.getModel().isPressed()) {
                jp.removeAll();
                panel.removeAll();
                jp.add(musicInfo);
                jp.add(musicBox);
                box.add(jp);

                panel.add(volumeConfig);
                panel.add(volume);
                box.add(panel);

                bPanel.add(scoreInfo);
                bPanel.add(scoreBox);
                bPanel.add(scoring);
                box.add(bPanel);

                box.add(backButton);
                startGame.setFont(type2);
                box.add(startGame);
                jf.add(box);

                jp.updateUI();
                panel.updateUI();
                bPanel.updateUI();
                jf.repaint();

                String input = scoring.getText();
                System.out.println(input);
                settingsModel.setPressed(false);

                while (true){
                    // 请不要删除下面这段打印的代码，只有在这段代码存在的情况下才能正常跳出本循环
                    System.out.print("");

                    if (input.matches("[0-88]")) {
                        scoreNum = Integer.parseInt(input);
                        System.out.println(input);
                    }
                    else if (scoreBox.getSelectedItem() != null) {
                        int index = scoreBox.getSelectedIndex();
                        for (int each : item) {
                            if (each == item[index]) {
                                scoreNum = each;
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"输入有误，请确认输入值",
                                "出了个问题", JOptionPane.INFORMATION_MESSAGE,null);

                    }
                    if (backButton.getModel().isPressed()) {
                        panel.removeAll();
                        bPanel.removeAll();
                        jp.removeAll();
                        box.removeAll();

                        jp.add(label,BorderLayout.NORTH);
                        panel.add(startGame,BorderLayout.SOUTH);
                        panel.add(multiGame,BorderLayout.SOUTH);
                        panel.add(settings,BorderLayout.SOUTH);
                        panel.add(exitGame,BorderLayout.SOUTH);

                        jf.add(jp);
                        jf.add(panel);
                        panel.updateUI();
                        jf.repaint();
                        backModel.setPressed(false);
                        break;
                    }
                    if (startGame.getModel().isPressed()){
                        panel.removeAll();
                        bPanel.removeAll();
                        jp.removeAll();
                        box.removeAll();
                        break;
                    }
                }

            }

            restartGame :  while (game) {
                String[] numWan = {"🀇", "🀈", "🀉", "🀊", "🀋", "🀌", "🀍", "🀎", "🀏", "🀇", "🀈", "🀉", "🀊", "🀋", "🀌", "🀍", "🀎", "🀏",
                        "🀇", "🀈", "🀉", "🀊", "🀋", "🀌", "🀍", "🀎", "🀏", "🀇", "🀈", "🀉", "🀊", "🀋", "🀌", "🀍", "🀎", "🀏"};
                String[] numSuo = {"🀐", "🀑", "🀒", "🀓", "🀔", "🀕", "🀖", "🀗", "🀘", "🀐", "🀑", "🀒", "🀓", "🀔", "🀕", "🀖", "🀗", "🀘",
                        "🀐", "🀑", "🀒", "🀓", "🀔", "🀕", "🀖", "🀗", "🀘", "🀐", "🀑", "🀒", "🀓", "🀔", "🀕", "🀖", "🀗", "🀘"};
                String[] numTong = {"🀙", "🀚", "🀛", "🀜", "🀝", "🀞", "🀟", "🀠", "🀡", "🀙", "🀚", "🀛", "🀜", "🀝", "🀞", "🀟", "🀠", "🀡",
                        "🀙", "🀚", "🀛", "🀜", "🀝", "🀞", "🀟", "🀠", "🀡", "🀙", "🀚", "🀛", "🀜", "🀝", "🀞", "🀟", "🀠", "🀡"};
                String[] wind = {"🀀", "🀁", "🀂", "🀃", "🀀", "🀁", "🀂", "🀃", "🀀", "🀁", "🀂", "🀃", "🀀", "🀁", "🀂", "🀃"};
                String[] arrow = {"🀄", "🀅", "🀆", "🀄", "🀅", "🀆", "🀄", "🀅", "🀆", "🀄", "🀅", "🀆"};

                int round = -1; // 麻将的回合数 The rounds of the mahjong game.

                /* wall是麻将的自摸墙
                  The variable "wall" is all the shuffled mahjong cards for players
                  to get and form theirs arrangements.
                 */
                List<String> wall = new ArrayList<>();
                wall.addAll(Arrays.asList(numWan));
                wall.addAll(Arrays.asList(numSuo));
                wall.addAll(Arrays.asList(numTong));
                wall.addAll(Arrays.asList(wind));
                wall.addAll(Arrays.asList(arrow));

                /* 在这里洗牌和实例化玩家对象。
                Shuffle the mahjong list and instantiate the player object here.
                */
                Collections.shuffle(wall);
                Banker banker = new Banker();
                South south = new South();
                West west = new West();
                North north = new North();

                /* 从这里到游戏主循环之前是为玩家配牌的代码，先为自摸墙创建四个sublist，然后将玩家类中的手牌数组转化为列表后对其进行赋值
                将sublist的值赋给手牌列表。
                From here to the main loop of the game is the cardGet allocation for
                each player. First create 4 sublists from wall, and assign the
                value of each sublist to the player.owned lists.
                */
                ArrayList<String> walls1 = new ArrayList<>(wall.subList(0, 13));
                ArrayList<String> walls2 = new ArrayList<>(wall.subList(13, 26));
                ArrayList<String> walls3 = new ArrayList<>(wall.subList(26, 39));
                ArrayList<String> walls4 = new ArrayList<>(wall.subList(39, 52));
                wall.subList(0, 52).clear();

                banker.owned = walls1;
                south.owned = walls2;
                west.owned = walls3;
                north.owned = walls4;
                banker.owned.sort(Comparator.naturalOrder());
                south.owned.sort(Comparator.naturalOrder());
                // String name = JOptionPane.showInputDialog("请输入一个昵称，然后开始游戏：");

                // 初始化设定游戏重开的按钮和结束游戏的按钮
                JButton restart = new JButton("再来一局");
                restart.setFont(type2);
                restart.setBackground(Color.black);
                restart.setForeground(Color.yellow);

                restart.addActionListener(e -> new Thread(() ->
                {
                    panel.removeAll();
                    panel.updateUI();
                    jf.repaint();
                    System.out.println("?");
                }));

                /* 这里的两段是把列表变成数组的方法
                String[] all_cards = (new String[wall.size()]);
                wall.toArray(all_cards);

                游戏主循环庄家回合开始，摸得一张牌后输入0~13即可将对应位置的手牌打出。
                这个循环的最外部用于初始化玩家鸣牌循环和摸切循环的条件，初始均为真值，以确保
                各自的循环能正常进行。这个主循环也可以叫做按钮创建循环或字符串更新循环。
                The main loop of the game and the round of the banker begins here.
                Program will print the banker.owned list and a tip reads "input
                please: " on the command table . Player can input the number from
                0 to 13 to access the index of this "banker.owned" list to abandon
                the cardGet through this index. This loop can also be called as the loop
                of button-creating loop or string-updating loop.
                 */

                while (wall.size() != 0 && banker.playing) {
                    String ref = String.valueOf(new AtomicReference<Object>());
                    AtomicReference<String> command = new AtomicReference<>();
                    String txt;
                    round += 1;
                    System.out.printf("第%d轮，自摸墙上剩余%d张\r\n", round, wall.size());

                    String cardText = "";
                    for (String each : banker.owned) {
                        cardText += each;
                    }
                    JTextArea area = new JTextArea(cardText);    // 文本显示
                    ArrayList<String> list = banker.owned;

                    area.setFont(type4);
                    area.setForeground(Color.BLUE);
                    jp.add(label,BorderLayout.NORTH);
                    panel.add(area);

                    JButton pengButton = new JButton("碰");
                    JButton gangButton = new JButton("杠");
                    JButton winButton = new JButton("和");
                    JButton passButton = new JButton("跳过");
                    JButton cheatButton = new JButton("开挂");
                    JButton confirm = new JButton("确认");

                    String[] commandArr = {"碰", "杠", "和", "跳过", "开挂", "确认"};
                    List<String> cmdList = Arrays.asList(commandArr);
                    jf.validate();

                    winButton.setFont(type2);
                    winButton.setForeground(new Color(250, 210, 0));
                    winButton.setBackground(new Color(240, 0, 0));
                    winButton.setSize(d);

                    pengButton.setFont(type2);
                    pengButton.setForeground(Color.white);
                    pengButton.setBackground(new Color(0, 190, 0));
                    pengButton.setSize(d);

                    gangButton.setFont(type2);
                    gangButton.setForeground(Color.yellow);
                    gangButton.setBackground(new Color(200, 0, 255));
                    gangButton.setSize(d);

                    passButton.setFont(type2);
                    passButton.setForeground(new Color(170, 170, 170));
                    passButton.setBackground(Color.BLACK);
                    passButton.setSize(d);

                    cheatButton.setFont(type2);
                    cheatButton.setForeground(new Color(0, 220, 220));
                    cheatButton.setBackground(Color.magenta);
                    cheatButton.setSize(d);

                    // 这里是按钮初始化的部分，按钮响应事件(289-304行的部分)应该写在这里
                    // 按钮按下-调用Banker.buttonListening()函数-得到一个值-将这个值带入游戏主循
                    // 环的Banker.cardCalling()函数-碰杠完成

                    ArrayList<JButton> commandButtons = new ArrayList<>();
                    commandButtons.add(winButton);
                    commandButtons.add(pengButton);
                    commandButtons.add(gangButton);
                    commandButtons.add(passButton);
                    commandButtons.add(cheatButton);

                    jf.repaint();

                    boolean buttonCalling = true;

                    // JButton没有实例化就添加了按钮监听事件会导致空指针异常
                    // 没被new过的对象如果为null。那么他的所有方法都不可用。

                    winButton.addActionListener(e -> {
                        command.set("/win");
                        bPanel.removeAll();
                        bPanel.updateUI();
                        jf.repaint();

                    });

                    pengButton.addActionListener(e -> {
                        bPanel.removeAll();
                        panel.updateUI();
                        jf.repaint();

                    });
                    gangButton.addActionListener(e -> {
                        bPanel.removeAll();
                        panel.updateUI();
                        jf.repaint();
                    });
                    passButton.addActionListener(e -> {
                        bPanel.removeAll();
                        panel.updateUI();
                        jf.repaint();
                    });
                    cheatButton.addActionListener(e -> {

                        String c = "/c";
                        String cheatCode = JOptionPane.showInputDialog("请输入麻将伪代码，以英文逗号分隔");
                        System.out.println(cheatCode);
                        banker.cheat(banker.owned, cheatCode);
                        panel.updateUI();
                        jf.repaint();
                    });

                    get : while (banker.loopCalling && wall.size() > 1) {
                        /* 鸣牌循环内嵌套的while循环叫做“玩家暗杠循环”，在这个循环中将会循环
                        判定玩家的摸牌和暗杠，如果玩家暗杠则continue到暗杠循环开头部分再摸一张，
                        否则跳出暗杠循环，进入别家鸣牌判定代码块。
                        【玩家暗杠由玩家内部的 gangLoop 变量进行判断】
                        */
                        banker.getCard(wall, banker.owned);
                        cardGet = banker.getName(wall, banker.owned);

                        Tools tools = new Tools();
                        int result;
                        boolean isPlus = false;
                        for (String each : banker.owned) {
                            result = tools.countListRepeatedStrings(banker.owned, each);

                            for (String str : banker.peng) {
                                if (Objects.equals(str.substring(0, 1), each)) {
                                    isPlus = true;
                                    panel.updateUI();
                                    break;
                                }
                            }

                            if (result == 4) {
                                int jop = JOptionPane.showConfirmDialog(null,"是否暗杠："+each,
                                        "杠牌提示",JOptionPane.YES_NO_CANCEL_OPTION);
                                if (jop == 0) {
                                    banker.anGangCalling(each);
                                    panel.updateUI();
                                    continue get;
                                }
                                else {
                                    break;
                                }
                            }
                            if (isPlus) {
                                int jop = JOptionPane.showConfirmDialog(null,"是否加杠："+each,
                                        "杠牌提示",JOptionPane.YES_NO_CANCEL_OPTION);
                                if (jop == 0) {
                                    banker.gangPlus(each);
                                    panel.updateUI();
                                    continue get;
                                }
                                else {
                                    panel.updateUI();
                                    break;
                                }
                            }
                        }
                        panel.updateUI();

                        if (!banker.gangLoop) {
                            // System.out.println("gangloop?");
                            break;
                        }

                    }
                    banker.loopCalling = true;

                    JButton[] buttonArr = new JButton[banker.owned.size()];     // 按钮数组
                    for (int i = 0; i < banker.owned.size(); i++) {
                        buttonArr[i] = new JButton(banker.owned.get(i));
                        buttonArr[i].setText(banker.owned.get(i));
                        buttonArr[i].setFont(type4);
                        buttonArr[i].setForeground(Color.BLUE);
                        int num = i;
                        buttonArr[i].setEnabled(true);
                        panel.add(buttonArr[i]);

                        buttonArr[i].addActionListener(e -> new Thread(() -> {
                            buttonArr[num].setEnabled(false);
                            area.setText(banker.owned.toString());

                            panel.add(buttonArr[num]);
                            buttonArr[num].setEnabled(true);

                            panel.updateUI();
                            jf.repaint();
                            e.setSource(num);
                            command.set(buttonArr[num].getText());
                        }));
                    }

                    bPanel.add(winButton);
                    bPanel.add(pengButton);
                    bPanel.add(gangButton);
                    bPanel.add(passButton);
                    bPanel.add(cheatButton);

                    box.add(jp);
                    box.add(panel);
                    box.add(bPanel);
                    jf.add(box);
                    panel.updateUI();
                    jf.repaint();

                    /*
                     * 如果余家的弃牌堆列表的长度不为零，则调用本家的鸣牌判定函数，传入余家
                     * 弃牌堆最后一张手牌，然后再在下方获取结果。*/
                    if (south.abandonArea.size() != 0) {
                        String result;
                    }
                     /* 庄家弃牌，然后调用其他玩家的鸣牌判定，如果他家鸣牌判定成立，则将他家
                     的摸切循环和鸣牌循环的条件设置为真。除了这一家，余家的摸切循环和鸣牌循环
                     的条件将被设置为假。这一段代码位于暗杠循环外部。
                     */
                    if (!south.ifCalling && !west.ifCalling && !north.ifCalling) {
                        System.out.print(banker.name + "请出牌：");

                        //banker.abandonedCardUI();
                        // 创建按钮的 for循环的内部按钮监听类只关心传入弃牌函数的字符串
                        // 按钮被点击之后，必须立即删除那个按钮，更新显示在屏幕上的字符串，并根据这个
                        // 字符串创建一个新的按钮列表【更新 UI】.可以创建一个大的无限循环叫做 “按钮
                        // 创建循环 ”。
                        //for (int i = 0; i < buttonArr.length; i++) {

                        // }
                        boolean ifButtonPressed = true;

                        do {

                            System.out.print("");  // 此处同上，需要这样一个打印到控制台的代码才能跳出循环
                            panel.updateUI();
                            jf.repaint();
                            /*
                            在这里检测玩家回合内的一切操作，分为五种情况——>
                            1.点击手牌，打出该牌【从按钮数组和玩家手牌中删除该变量】，删除所有组件并刷新；
                            2.点击跳过，跳过出牌阶段，删除所有组件并刷新；
                            3.点击碰牌，从按钮数组、玩家手牌、以及弃牌区中删除对应数量的元素；
                            4.点击杠牌
                            5.点击和牌，直接使 banker.playing等于 false跳出游戏主循环。
                            */
                            for (JButton jButton : buttonArr) {

                                if (jButton.getModel().isArmed()) {
                                    ref = jButton.getText();
                                    bPanel.removeAll();
                                    panel.removeAll();
                                    panel.updateUI();
                                    jf.repaint();
                                    ifButtonPressed = false;
                                    jButton.setSelected(false);
                                    break;
                                }else if (pengButton.getModel().isArmed()) {
                                    panel.updateUI();
                                    jf.repaint();
                                    banker.pengCalling(south.abandonArea.get(south.abandonArea.size()-1));
                                    ifButtonPressed = false;
                                }
                                else if (passButton.getModel().isArmed()) {
                                    panel.removeAll();
                                    panel.updateUI();
                                    jf.repaint();
                                    ifButtonPressed = false;
                                    break;
                                } else if (winButton.getModel().isArmed()) {
                                    bPanel.removeAll();
                                    panel.removeAll();
                                    panel.updateUI();
                                    jf.repaint();
                                    ifButtonPressed = false;
                                    banker.playing = false;

                                    south.loopCalling = false;
                                    south.ifCalling = true;
                                    west.loopCalling = false;
                                    west.ifCalling = true;
                                    north.loopCalling = false;
                                    north.ifCalling = false;
                                    break;
                                }
                            }


                        } while (ifButtonPressed);

                        jf.repaint();
                        banker.abandonCard(ref);
                        /* 此为余家鸣牌的情况，如果余家只是碰牌，即 loopCalling == false 的时候
                        那么代码不经过南家的暗杠循环，直接进入南家的弃牌及别家鸣牌判定的代码块；如果
                        南家杠牌，那么代码需经过南家暗杠循环。即明杠时，loopCalling == true，进入
                        暗杠循环摸牌，如果这张摸牌没有暗杠，那么跳出暗杠循环，进入弃牌代码；碰牌时，
                        loopCalling == false，不进入暗杠循环，直接询问丢弃哪张手牌。

                        ps: 如果余家是AI，则需要调用 cardCallingAI()方法。

                        south.cardCalling(banker.abandonArea.get(banker.abandonArea.size() - 1));
                        west.cardCalling(banker.abandonArea.get(banker.abandonArea.size() - 1));
                        north.cardCalling(banker.abandonArea.get(banker.abandonArea.size() - 1));
                        */
                        south.cardCallingAI();
                        west.cardCallingAI();
                        north.cardCallingAI();
                        System.out.println("庄家已出牌");
                    }

                    // 南家的鸣牌循环
                    while (south.loopCalling) {
                        // 庄家摸牌，并进行暗杠和加杠判定，判定有效则继续玩家鸣牌循环。

                        south.getCard(wall, south.owned);

                        if (!south.gangLoop) {
                            break;
                        }
                    }
                    south.loopCalling = true;

                    txt = " ";
                    if (!banker.ifCalling && !west.ifCalling && !north.ifCalling && wall.size() > 0) {
                        // 南家弃牌
                        south.abandonCardAI();
                        String s_abandoned = south.abandonArea.get(south.abandonArea.size() - 1);

                        /* 按钮鸣牌循环及其余几家的鸣牌判定，包括庄家在内。如果南家弃牌和庄家手里的牌
                        组成碰杠牌牌型，么向庄家发出碰杠牌提示，循环遍历命令按钮列表，获取按钮命令，
                        如果玩家按下了碰按钮，则调用 cardCalling 函数完成碰牌，杠按钮的情况相同。如
                        果玩家按下跳过按钮，则跳出按钮鸣牌循环。
                        */

                        Tools tools = new Tools();
                        int result = tools.countListRepeatedStrings(banker.owned, s_abandoned);
                        if (result == 2) {
                            jf.repaint();

                            int jop = JOptionPane.showConfirmDialog(null,"是否碰牌："+s_abandoned,"碰牌提示",
                                    JOptionPane.YES_NO_CANCEL_OPTION);
                            if (jop == 0){
                                panel.updateUI();
                                banker.pengCalling(s_abandoned);
                                banker.loopCalling = false;

                                west.loopCalling = false;
                                west.ifCalling = true;
                                north.loopCalling = false;
                                north.ifCalling = true;
                            }

                        }
                        else if (result > 2 && banker.owned.contains(s_abandoned))
                        {
                            Object[] options = {"碰","杠","和","跳过"};
                            int jop = JOptionPane.showOptionDialog(null,"是否鸣牌："+s_abandoned,
                                    "碰杠和提示", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
                                    null,options, options[0]);

                            if (jop == 0){
                                panel.updateUI();
                                banker.pengCalling(s_abandoned);
                                banker.loopCalling = false;

                                west.loopCalling = false;
                                west.ifCalling = true;
                                north.loopCalling = false;
                                north.ifCalling = true;
                            }
                            else if (jop == 1) {
                                banker.gangCalling(s_abandoned);
                                panel.updateUI();
                                west.loopCalling = false;
                                west.ifCalling = true;
                                north.loopCalling = false;
                                north.ifCalling = true;
                            }
                            else if (jop == 2) {
                                zimo = false;
                                banker.playing = false;
                                west.loopCalling = false;
                                west.ifCalling = true;
                                north.loopCalling = false;
                                north.ifCalling = true;
                            }
                            else {
                                panel.updateUI();
                            }
                        }

                    }
                    south.ifCalling = false;

                    // 西家的鸣牌循环
                    while (west.loopCalling && wall.size() > 0) {
                        west.getCardAI(wall, west.owned);

                        if (!west.gangLoop) {
                            break;
                        }
                    }
                    west.loopCalling = true;

                    if (!west.ifCalling) {
                        west.abandonCardAI();
                        String w_abandoned = west.abandonArea.get(west.abandonArea.size() - 1);

                        Tools tools = new Tools();
                        int result = tools.countListRepeatedStrings(banker.owned, w_abandoned);
                        if (result == 2) {
                            boolean ifButtonPressed = true;
                            String ref_w;
                            int jop = JOptionPane.showConfirmDialog(null,"是否碰牌："+w_abandoned,"碰牌提示",
                                    JOptionPane.YES_NO_CANCEL_OPTION);
                            if (jop == 0){
                                panel.updateUI();
                                banker.pengCalling(w_abandoned);
                                banker.loopCalling = false;

                                north.loopCalling = false;
                                north.ifCalling = true;
                            }

                        }else if (result > 2 && banker.owned.contains(w_abandoned))
                        {
                            Object[] options = {"碰","杠","和","跳过"};
                            int jop = JOptionPane.showOptionDialog(null,"是否鸣牌："+w_abandoned,
                                    "碰杠和提示", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
                                    null,options, options[0]);

                            if (jop == 0){
                                panel.updateUI();
                                banker.pengCalling(w_abandoned);
                                banker.loopCalling = false;

                                north.loopCalling = false;
                                north.ifCalling = true;
                            }
                            else if (jop == 1) {
                                banker.gangCalling(w_abandoned);
                                panel.updateUI();
                                north.loopCalling = false;
                                north.ifCalling = true;
                            }
                            else if (jop == 2) {
                                zimo = false;
                                banker.playing = false;
                                north.loopCalling = false;
                                north.ifCalling = true;
                            }
                            else {
                                panel.updateUI();
                            }
                        }

                    }
                    west.ifCalling = false;

                    // 北家的鸣牌循环
                    while (north.loopCalling && wall.size() > 0) {
                        north.getCard(wall, north.owned);

                        if (!north.gangLoop) {
                            break;
                        }
                    }
                    north.loopCalling = true;

                    if (!north.ifCalling) {
                        north.abandonCardAI();
                        String n_abandoned = north.abandonArea.get(north.abandonArea.size()-1);
                        Tools tools = new Tools();
                        int result = tools.countListRepeatedStrings(banker.owned, n_abandoned);

                        if (result == 2){
                            boolean ifButtonPressed = true;
                            String ref_n;
                            int jop = JOptionPane.showConfirmDialog(null,"是否碰牌："+n_abandoned,"碰牌提示",
                                    JOptionPane.YES_NO_CANCEL_OPTION);
                            if (jop == 0){
                                banker.pengCalling(n_abandoned);
                                banker.loopCalling = false;
                                panel.updateUI();
                            }
                            else {
                                panel.updateUI();
                            }

                        }
                        else if (result > 2 && banker.owned.contains(n_abandoned))
                        {
                            Object[] options = {"碰","杠","和","跳过"};
                            int jop = JOptionPane.showOptionDialog(null,"是否鸣牌："+n_abandoned,
                                    "碰杠和提示", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
                                    null,options, options[0]);

                            if (jop == 0){
                                panel.updateUI();
                                banker.pengCalling(n_abandoned);
                                banker.loopCalling = false;

                            }
                            else if (jop == 1) {
                                banker.gangCalling(n_abandoned);
                                panel.updateUI();
                            }
                            else if (jop == 2) {
                                zimo = false;
                                banker.playing = false;
                            }
                            else {
                                panel.updateUI();
                            }
                        }
                    }
                    north.ifCalling = false;
                }

                // 游戏循环结束，进入游戏结算画面
                panel.updateUI();
                jf.repaint();
                AgariBacktrack agari = new AgariBacktrack();
                Rules rules = new Rules();

                //int[] testhai = {7, 8, 9, 10, 11, 12, 17, 18, 19, 22, 22, 25, 26, 27};
                // 如果玩家有吃碰，那么在这里建立一个列表，把玩家的碰杠和手牌放入至此
                ArrayList<String> hai = new ArrayList<>(banker.owned);
                if (banker.peng.size() > 0) {
                    for (String each : banker.peng) {
                        hai.add(each.substring(0, 2));
                        hai.add(each.substring(0, 2));
                        hai.add(each.substring(0, 2));
                    }
                }
                if (banker.gang.size() > 0){
                    for (String each : banker.gang) {
                        hai.add(each.substring(2,4));
                        hai.add(each.substring(2,4));
                        hai.add(each.substring(2,4));
                    }
                }

                if (wall.size() == 0 && zimo) {
                    salvage = true;
                }
                else if (wall.size() == 0 && !zimo) {
                    spring = true;
                }

                // 获取重新整合后的手牌，然后对其进行映射
                System.out.println(hai);
                int[] cards = rules.mappingHai(hai);
                agari.normal(cards,banker.owned, banker.peng, banker.gang,
                        cardGet,zimo,salvage,spring,bloom);

                Process pro;
                try {
                    pro = Runtime.getRuntime().exec("mahjongDoublets.exe");
                    BufferedReader bfr = new BufferedReader(new InputStreamReader(
                            pro.getInputStream()
                    ));
                    String line;
                    while ((line = bfr.readLine()) != null){
                        System.out.println(line);
                    }
                    bfr.close();
                    pro.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                List<String> dataList = new ArrayList<>();
                String[] dataArray;

                File data = new File("mahjong_data.txt");
                try {
                    FileInputStream fis = new FileInputStream(data);
                    InputStreamReader isr = new InputStreamReader(fis);
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    Scanner scanner = new Scanner(data);
                    String txt;

                    while (scanner.hasNextLine()){
                        txt = scanner.nextLine();
                        dataList.add(txt);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                String fanString = "";
                List<String> fanList = dataList.subList(13,dataList.size());
                ArrayList<Integer> scoreList = new ArrayList<>();
                String[] fanArray;
                for (String s : fanList) {
                    fanString += s;
                    fanString += ",";
                    System.out.println(s);
                }
                fanArray = fanString.split(",");
                fanList = Arrays.asList(fanArray);

                for (int i = 0; i < fanList.size(); i++) {
                    if (i % 2 != 0) {
                        scoreList.add(Integer.parseInt(fanList.get(i)));
                    }
                }
                int total = 0;
                for (int num : scoreList) {
                    total += num;
                }

                jp.updateUI();
                panel.updateUI();
                bPanel.updateUI();
                jf.repaint();

                System.out.println(total);

                if (agari.winNum != 0 && agari.winNum % 5 == 0 && total >= scoreNum) {
                    System.out.println("和！");
                    panel.removeAll();
                    panel.updateUI();
                    jf.repaint();

                    JLabel jl = new JLabel();
                    jl.setText("你和了！");
                    jl.setFont(type3);
                    jl.setForeground(new Color(0, 190, 0));

                    JButton exitGame1 = new JButton("退出游戏");

                    exitGame1.setFont(type2);
                    exitGame1.setBackground(Color.RED);
                    exitGame1.setForeground(Color.WHITE);

                    backButton.setForeground(Color.WHITE);
                    backButton.setBackground(new Color(0, 190, 0));

                    panel.add(jl, BorderLayout.CENTER);
                    panel.add(backButton,BorderLayout.SOUTH);
                    panel.add(restart,BorderLayout.SOUTH);
                    panel.add(exitGame1,BorderLayout.SOUTH);
                    panel.updateUI();
                    jf.repaint();

                    do {
                        System.out.print("");
                        if (restart.getModel().isPressed()) {
                            game = true;
                            panel.removeAll();
                            panel.updateUI();
                            jf.repaint();
                            continue restartGame;
                        }
                        else if (backButton.getModel().isPressed()){
                            exitGame.removeAll();
                            panel.removeAll();
                            panel.updateUI();
                            jf.repaint();

                            jp.add(label,BorderLayout.NORTH);
                            panel.add(startGame,BorderLayout.SOUTH);
                            panel.add(multiGame,BorderLayout.SOUTH);
                            panel.add(settings,BorderLayout.SOUTH);
                            panel.add(exitGame,BorderLayout.SOUTH);

                            jf.add(jp);
                            jf.add(panel);
                            panel.updateUI();
                            jf.repaint();

                            game = false;
                            continue homePage;
                        }
                        else if (exitGame1.getModel().isPressed()) {
                            System.exit(114514);
                        }

                    }while (true);
                }
                /* else if () {}
                */
                else {
                    System.out.println("你在做甚麽");
                    panel.removeAll();
                    panel.updateUI();
                    jf.repaint();

                    JLabel jl = new JLabel();
                    jl.setText("你没有和牌！");
                    jl.setFont(type3);
                    jl.setForeground(Color.red);

                    JButton exitGame1 = new JButton("退出游戏");

                    exitGame1.setFont(type2);
                    exitGame1.setBackground(Color.RED);
                    exitGame1.setForeground(Color.WHITE);

                    backButton.setForeground(Color.WHITE);
                    backButton.setBackground(new Color(0, 190, 0));

                    panel.add(jl, BorderLayout.CENTER);
                    panel.add(backButton,BorderLayout.SOUTH);
                    panel.add(restart,BorderLayout.SOUTH);
                    panel.add(exitGame1,BorderLayout.SOUTH);
                    panel.updateUI();
                    jf.repaint();

                    do {
                        System.out.print("");
                        if (restart.getModel().isArmed()) {
                            game = true;
                            panel.removeAll();
                            panel.updateUI();
                            jf.repaint();
                            continue restartGame;
                        }
                        else if (backButton.getModel().isPressed()){
                            exitGame.removeAll();
                            panel.removeAll();
                            panel.updateUI();
                            jf.repaint();

                            jp.add(label,BorderLayout.NORTH);
                            panel.add(startGame,BorderLayout.SOUTH);
                            panel.add(multiGame,BorderLayout.SOUTH);
                            panel.add(settings,BorderLayout.SOUTH);
                            panel.add(exitGame,BorderLayout.SOUTH);

                            jf.add(jp);
                            jf.add(panel);
                            jp.updateUI();
                            panel.updateUI();
                            jf.repaint();

                            game = false;
                            continue homePage;
                        }
                        else if (exitGame1.getModel().isPressed()) {
                            System.exit(1919810);
                        }

                    }while (true);
                }

            }
        }

    }
}
