package space.cogito.bubble;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
        initListener();
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

    private void initListener() {
        addKeyListener(new KeyAdapter() {
            // 키보드 클릭 이벤트 핸들러
            @Override
            public void keyPressed(KeyEvent e) {
//                System.out.println(e.getKeyCode());

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (!player.isLeft()) {
                            player.left();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!player.isRight()) {
                            player.right();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (!player.isUp() && !player.isDown()) {
                            player.up();
                        }
                        break;
//                    case KeyEvent.VK_DOWN: // 보글 보글에서 떨어지는 건 있어도 벽을 뚫고 내려가는 건 없음
//                        player.down();
//                        break;
                }
            }

            // 키보드 해제 이벤트 핸들러
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        player.setLeft(false);
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.setRight(false);
                        break;
                }
                super.keyReleased(e);
            }
        });
    }


    public static void main(String[] args) {
        new BubbleFrame();
    }
}
