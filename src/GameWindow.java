import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GameWindow {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> arrayList = new ArrayList<>();

        String s = "abcd";
        String s1 = "efgh";
        String s2 = "ijk";
        list.add(s);
        list.add(s1);
        list.add(s2);

        char c = ' ';

        System.out.println(list);
        for (String each : list) {
            arrayList.add(each.substring(0,1));
        }
        System.out.println(arrayList);
    }

}