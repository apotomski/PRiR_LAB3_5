package test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedImage obraz;


        File input = new File("pszczola.jpg");
        obraz = ImageIO.read(input);
        int szer = obraz.getWidth();
        int wys = obraz.getHeight();
        int polSzer = szer / 2;
        int polWys = wys / 2;

        Negatyw n1, n2, n3, n4;
        n1 = new Negatyw(obraz, 0, 0, polSzer, polWys);
        n2 = new Negatyw(obraz, polSzer, 0, szer, polWys);
        n3 = new Negatyw(obraz, 0, polWys, polSzer, wys);
        n4 = new Negatyw(obraz, polSzer, polWys, szer, wys);

        n1.start();
        n2.start();
        n3.start();
        n4.start();

        try {
            n1.join();
            n2.join();
            n3.join();
            n4.join();
        } catch (Exception e) {

        }

        File ouptut = new File("pszczolaNegatyw.jpg");
        ImageIO.write(obraz, "jpg", ouptut);

    }
}
