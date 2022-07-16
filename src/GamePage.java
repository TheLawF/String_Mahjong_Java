import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePage extends JPanel {
    ArrayList<String> list;
    ArrayList<JButton> buttonList = new ArrayList<>(14);

    public GamePage(ArrayList<String> list)
    {
        this.list = list;
        /*
        Font type1 = new Font("Segoe UI Symbol",Font.PLAIN,40);
        Dimension d = new Dimension(65,100);

        for (int i = 0; i < buttonList.size(); i++) {
            JButton button = new JButton(list.get(i));
            button.setEnabled(false);
            button.setFont(type1);
            button.setPreferredSize(d);
            this.buttonList.add(button);
        }*/

    }

    @Override
    public void paint(Graphics g){
        String cards = "";

        g.setFont(new Font("Segoe UI Symbol",Font.PLAIN,35));
        g.setColor(new Color(0,160,0));
        g.drawString(this.list.toString(), 100, 400);

    }
}
