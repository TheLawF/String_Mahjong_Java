import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GameWindow extends JFrame implements ActionListener {
    public static void main(String[] args) {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("6");
        strList.add("3");
        strList.add("1");
        System.out.println(strList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}