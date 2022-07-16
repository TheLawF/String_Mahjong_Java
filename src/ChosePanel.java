import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChosePanel extends JFrame {
    JPanel buttonPanel;
    JButton submit;
    ButtonGroup option;
    JRadioButton optA;
    JRadioButton optB;
    JRadioButton optC;
    JRadioButton optD;

    public void init(){

        buttonPanel = new JPanel();
        submit = new JButton();
        option = new ButtonGroup();

        optA = new JRadioButton("单人游戏");
        optB = new JRadioButton("双人游戏");
        optC = new JRadioButton("三人游戏");
        optD = new JRadioButton("四人游戏");

        option.add(optA);
        option.add(optB);
        option.add(optC);
        option.add(optD);

        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel.add(optA);
        buttonPanel.add(optB);
        buttonPanel.add(optC);
        buttonPanel.add(optD);
        buttonPanel.add(submit);

        this.setTitle("请选择游戏模式");
        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.repaint();

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (optA.isSelected()){

                }
                else if (optB.isSelected()){

                }
                else if (optC.isSelected()){

                }
                else if (optD.isSelected()){

                }else {

                }
            }
        });
    }
}
