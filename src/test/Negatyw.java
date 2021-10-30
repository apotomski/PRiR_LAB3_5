package test;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Negatyw extends Thread{
    BufferedImage obraz;
    int xStart, yStart, xStop, yStop;

    public Negatyw(BufferedImage obraz, int xStart, int yStart, int xStop, int yStop){
        this.xStart = xStart;
        this.yStart = yStart;
        this.xStop = xStop;
        this.yStop = yStop;
        this.obraz = obraz;
    }

    @Override
    public void run() {
        for(int i = xStart; i < xStop; i++){
            for(int j = yStart; j < yStop; j++) {
                Color c = new Color(obraz.getRGB(i, j));
                int r = c.getRed();
                int g= c.getGreen();
                int b = c.getBlue();

                int R, G, B;

                R = 255 - r;
                G = 255 - g;
                B = 255 - b;

                Color newColor = new Color(R, G, B);
                obraz.setRGB(i, j, newColor.getRGB());
            }
        }
    }

}
