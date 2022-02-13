package space.cogito.bubble;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BackgroundEnemyService implements Runnable {

    private BufferedImage image;
    private Enemy enemy;

    public BackgroundEnemyService(Enemy enemy) {
        this.enemy = enemy;
        try {
            image = ImageIO.read(new File("image/backgroundMapService.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (enemy.getState() == 0) {
            // 벽 충돌 체크
            // 색상 확인
            Color leftColor = new Color(image.getRGB(enemy.getX() - 10, enemy.getY() + 25));
            Color rightColor = new Color(image.getRGB(enemy.getX() + 50 + 15, enemy.getY() + 25));

            // -2가 아니라는 것은 바닥에 색깔이 없이 흰색
            int bottomColor = image.getRGB(enemy.getX() + 10, enemy.getY() + 50 + 5)
                    + image.getRGB(enemy.getX() + 50 - 10, enemy.getY() + 50 + 5);

            // 바닥 충돌 확인
            if (bottomColor != -2) {
                System.out.println();
                enemy.setDown(false);
            } else { // -2 일 때 실행됨 => 내 바닥 색깔이 하얀색
                if (!enemy.isUp() && !enemy.isDown()) {
                    enemy.down();
                }
            }

            // 외벽 충돌 확인
            if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
                enemy.setLeft(false);
                if (!enemy.isRight()) {
                    enemy.right();
                }
            } else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
                enemy.setRight(false);
                if (!enemy.isLeft()) {
                    enemy.left();
                }
            }

            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
