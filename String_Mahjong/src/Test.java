import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Test {

    public static void main(String[] args) {
        // 初始化基本数据，包括手牌数组、按钮列表和背景图片
        String[] cardList = {"🀀","🀂","🀃","🀊","🀋","🀎","🀐","🀓","🀓","🀓","🀗","🀠","🀡","🀎","🀠"};
        ArrayList<String> list = new ArrayList<>(Arrays.asList(cardList));
        ArrayList<JButton> buttonList = new ArrayList<>(14);
        ImageIcon remiFlan = new ImageIcon("E:\\anything\\pix_image\\yes.png");

        // 设定一些基本的窗口和字体样式
        Dimension d = new Dimension(65,100);
        Dimension d1 = new Dimension(800,600);
        Font type1 = new Font("Segoe UI Symbol",Font.PLAIN,40);
        Font type2 = new Font("思源黑体",Font.PLAIN,40);

        // 实例化窗口、文本显示区、标签、面板和按钮数组
        JFrame jf = new JFrame("全字符麻将游戏 Java-Version-1.0");  // 窗口
        JTextArea area = new JTextArea(Arrays.toString(cardList));    // 文本显示
        JLabel label = new JLabel(remiFlan);                         // 标签
        JPanel panel = new JPanel();                              // 面板
        JButton[] buttonArr = new JButton[cardList.length];       // 按钮数组


        // 实例化容器对象，
        Container container = jf.getContentPane();

        // 设置容器的布局管理器，这里采用的是网格分布式管理
        container.setLayout(new GridLayout(2,1));

        // 为文本设置字体格式
        area.setFont(type2);
        panel.add(area);

        Banker banker = new Banker();
        banker.owned = list;

        /* 使用 for循环创造多个按钮对象，并设置按钮内文本的字体和颜色及按钮监听器。
         【注意】每个按钮对象是buttonArr[i] 不是 button.
         将每个按钮循环添加至面板，并对每一个按钮设置流式布局管理器。
        */
        for (int i = 0; i < buttonArr.length; i++) {
            buttonArr[i] = new JButton(cardList[i]);
            buttonArr[i].setFont(type1);
            buttonArr[i].setForeground(Color.BLUE);
            String s = Integer.toString(i);
            int num = i;
            panel.add(buttonArr[i],new FlowLayout());
            buttonArr[i].addActionListener(e -> {
                banker.abandonCard(s);
                panel.remove(num);
                panel.updateUI();
                jf.repaint();
            });

        }


        // 设置面板的位置矩形，面板的长宽与上文 Dimension d 的长宽保持一致
        panel.setBounds(0,500,d.width,d.height);


        jf.add(label);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        container.add(panel,BorderLayout.SOUTH);

        jf.setSize(800,600);
        jf.setLocation(100,100);
        jf.setVisible(true);

    }
}
