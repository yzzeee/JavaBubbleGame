package space.cogito.bubble;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

// 1. 윈도우 창이 되었음
// 2. 윈도우 창은 내부에 패널을 하나 가지고 있다.
public class BubbleFrame extends JFrame {

    private JLabel backgroundMap;
    private Player player;

    public BubbleFrame() {
        initObject();
        initSetting();
        setVisible(true); // 그림을 그려라
    }

    private void initObject() {
        backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
        setContentPane(backgroundMap); // JPanel을 JLabel로 대체함
        player = new Player();
        add(player);
//        backgroundMap.setSize(1000, 600);
//        backgroundMap.setLocation(300, 300);
//        add(backgroundMap); // JFrame에 JLabel이 그려진다.
    }

    private void initSetting() {
        setSize(1000, 640);
        setLayout(null); // absolute 레이아웃 (자유롭게 그림을 그릴 수 있다.)
        setLocationRelativeTo(null); // JFrame 가운데 배치하기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X 버튼으로 창 끌 때 JVM 같이 종료하기
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }
}
