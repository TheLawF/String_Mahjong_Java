import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.List;

public class TestFrame {
    List wall;
    //String[] all_cards;

    public TestFrame(List wall) {
        this.wall = wall;
        //this.all_cards = all_cards;
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
        JFrame jf = new JFrame("全字符麻将游戏 Java_Version");
        ImageIcon remiFlan = new ImageIcon("E:\\anything\\pix_image\\yes.png");
        JLabel label = new JLabel(remiFlan);                      // 标签
        JPanel panel = new JPanel();                              // 面板

        // 实例化容器对象，
        Container container = jf.getContentPane();
        // 设置容器的布局管理器，这里采用的是网格分布式管理
        container.setLayout(new BorderLayout());

        // 设定一些基本的窗口和字体样式
        Dimension d = new Dimension(65, 100);
        Dimension d1 = new Dimension(800, 600);
        Dimension d2 = new Dimension(1080, 640);
        Tools t = new Tools();
        File file = new File("GL-MahjongTile.ttf");
        Font type = null;

        Font type2 = new Font("Serif.",Font.BOLD,50);

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

        jf.setSize(d1);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocation(100, 100);
        jf.setVisible(true);

        JTextField jtf = new JTextField("🀇🀈🀉🀊🀋🀌🀍🀎🀏");
        JLabel jLabel = new JLabel("这是一段中文abd🀇🀈🀉🀊🀋");
        //jLabel.setFont(type2);
        //jtf.setFont(type4);
        panel.add(jtf);
        panel.add(jLabel);
        panel.updateUI();

        jf.add(panel);
        jf.repaint();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] f = ge.getAllFonts();
        for (Font font : f) {
            System.out.println(font);
        }

    }

}
