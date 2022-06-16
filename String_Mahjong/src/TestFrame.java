import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TestFrame extends JFrame {
    JLabel label;
    private static TestFrame tf;
    JButton b1;
    JButton b2;
    private static String[] cardList = {"🀀","🀂", "🀃", "🀊", "🀋", "🀎", "🀐", "🀓", "🀓", "🀓", "🀗", "🀠", "🀡", "🀎", "🀠"};;

    public TestFrame(String[] cardList)
    {
        super("TestFrame");
        TestFrame.cardList = cardList;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800,600);
        setVisible(true);

        Dimension d = new Dimension(65,100);
        Font type1 = new Font("Segoe UI Symbol",Font.PLAIN,40);

        setLayout(new FlowLayout());
        label = new JLabel(Arrays.toString(TestFrame.cardList));
        ArrayList<JButton> buttons = new ArrayList<>(14);
        for (String s : cardList) {
            JButton b = new JButton(s);
            b.setFont(type1);
            buttons.add(b);
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                tf = new TestFrame(cardList);
            }
        });
    }
}
