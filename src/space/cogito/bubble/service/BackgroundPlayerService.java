package space.cogito.bubble.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import space.cogito.bubble.component.Bubble;
import space.cogito.bubble.component.Player;

// 메인 스레드는 바쁨 - 키보드 이벤트를 처리하느라
// 백그라운드에서계속 관찰
public class BackgroundPlayerService implements Runnable {

    private BufferedImage image;
    private Player player;
    private List<Bubble> bubbleList;

    // 플레이어, 버블
    public BackgroundPlayerService(Player player) {
        this.player = player;
        this.bubbleList = player.getBubbleList();
        try {
            image = ImageIO.read(new File("image/backgroundMapService.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            // 1. 버블 충돌 체크
            for (int i = 0; i < bubbleList.size(); i++) {
                Bubble bubble = bubbleList.get(i);
                if (bubble.getState() == 1) {
                    if (Math.abs(player.getX() - bubble.getX()) < 10 &&
                            (Math.abs(player.getY() - bubble.getY()) > 0 && Math.abs(player.getY() - bubble.getY()) < 50)) {
                        bubble.clearBubbled();
                        bubbleList.remove(i);
                        break;
                    }
                }
            }

            // 2. 벽 충돌 체크
            // 색상 확인
            Color leftColor = new Color(image.getRGB(player.getX() - 10, player.getY() + 25));
            Color rightColor = new Color(image.getRGB(player.getX() + 50 + 15, player.getY() + 25));

            // -2가 아니라는 것은 바닥에 색깔이 없이 흰색
            int bottomColor = image.getRGB(player.getX() + 10, player.getY() + 50 + 5)
                    + image.getRGB(player.getX() + 50 - 10, player.getY() + 50 + 5);

            // 바닥 충돌 확인
            if (bottomColor != -2) {
                System.out.println();
                player.setDown(false);
            } else { // -2 일 때 실행됨 => 내 바닥 색깔이 하얀색
                if (!player.isUp() && !player.isDown()) {
                    player.down();
                }
            }

            // 외벽 충돌 확인
            if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
                player.setLeftWallCrash(true);
                player.setLeft(false);
            } else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
                player.setRightWallCrash(true);
                player.setRight(false);
            } else {
                player.setLeftWallCrash(false);
                player.setRightWallCrash(false);
            }

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
