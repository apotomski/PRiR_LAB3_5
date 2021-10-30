# PRiR_LAB3_5
PRiR lab 3 projekt 5. Tworzenie negatywu obrazu, tworzenie negatywu jest podzielone na wątki aby przyśpieszyć działanie programu.

przed


Opis kodu

Klasa Negatyw 

Konstruktor przypisuje wartości

    public Negatyw(BufferedImage obraz, int xStart, int yStart, int xStop, int yStop){
        this.xStart = xStart;
        this.yStart = yStart;
        this.xStop = xStop;
        this.yStop = yStop;
        this.obraz = obraz;
    }
    
metoda run podmienia wartości kolorystyczne pixeli dla wydzielonego obszaru obrazu

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
    
Main

tworzy obiekty klasy Negatyw uruchamia na nich pracę na wątkach czeka aż wszystkie skończą i następnie zapisuje negatyw do nowego pliku.

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
