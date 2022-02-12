package space.cogito.bubble;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

// 1. 윈도우 창이 되었음
// 2. 윈도우 창은 내부에 패널을 하나 가지고 있다.
public class BubbleFrame extends JFrame {

    private JTextField textField;

    public BubbleFrame() {
        setSize(1000, 640);
        getContentPane().setLayout(null);

        JButton newBtn = new JButton("New Button");
        newBtn.setBounds(96, 96, 85, 21);
        getContentPane().add(newBtn);

        textField = new JTextField();
        textField.setText("11111");
        textField.setBounds(376, 251, 96, 19);
        getContentPane().add(textField);
        textField.setColumns(10);

        setVisible(true); // 그림을 그려라
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }
}
