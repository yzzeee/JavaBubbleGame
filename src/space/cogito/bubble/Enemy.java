package space.cogito.bubble;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enemy extends JLabel implements Moveable {

    private BubbleFrame mContext;

    // 위치 상태
    private int x;
    private int y;

    // 적군의 방향
    private EnemyWay enemyWay;

    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    private int state; // 0(살아있는 상태), 1(갇힌 상태);

    // 적군 속도
    private final int SPEED = 3;
    private final int JUMPSPEED = 1; // up, down

    // 벽에 충돌한 상태
    private boolean leftWallCrash;
    private boolean rightWallCrash;

    private ImageIcon enemyR, enemyL;

    public Enemy(BubbleFrame mContext) {
        this.mContext = mContext;
        initObject();
        initSetting();
        initBackgroundEnemyService();
    }


    private void initObject() {
        enemyR = new ImageIcon("image/enemyR.png");
        enemyL = new ImageIcon("image/enemyL.png");
    }

    private void initSetting() {
        x = 480;
        y = 178;

        enemyWay = EnemyWay.RIGHT;

        left = false;
        right = false;
        up = false;
        down = false;

        setIcon(enemyR);
        setSize(50, 50);
        setLocation(x, y);

        leftWallCrash = false;
        rightWallCrash = false;

    }

    private void initBackgroundEnemyService() {
//        new Thread(new BackgroundEnemyService(this)).start();
    }

    // 이벤트 핸들러
    @Override
    public void left() {
        enemyWay = EnemyWay.LEFT;
        left = true;
        new Thread(() -> {
            while (left) {
                setIcon(enemyL);
                x = x - SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void right() {
        enemyWay = EnemyWay.RIGHT;
        right = true;
        new Thread(() -> {
            while (right) {
                setIcon(enemyR);
                x = x + SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // left + up, right + up
    @Override
    public void up() {
        up = true;
        new Thread(() -> {
            for (int i = 0; i < 130 / JUMPSPEED; i++) {
                y = y - JUMPSPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            up = false;
            down();

        }).start();
    }

    @Override
    public void down() {
        down = true;
        new Thread(() -> {
            while (down) {
                y = y + JUMPSPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            down = false;
        }).start();
    }

    @Override
    public void attack() {
        new Thread(() -> {
            Bubble bubble = new Bubble(mContext);
            mContext.add(bubble);
            if (enemyWay == EnemyWay.LEFT) {
                bubble.left();
            } else {
                bubble.right();
            }
        }).start();
    }
}
