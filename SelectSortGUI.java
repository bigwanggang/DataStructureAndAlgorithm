package DataStructureAndAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by 10183960 on 2017/2/10.
 */
public class SelectSortGUI extends JFrame {
    public static int width = 450;
    public static int height = 300;

    public static Color commonColor = new Color(49, 209, 255);

    public SelectSortGUI() {
        setSize(width, height);
        int[] array = getRandomArray(15);
        Bar[] bars = new Bar[array.length];
        for (int i = 0; i < array.length; i++) {
            bars[i] = new Bar(array[i], commonColor);
        }
        add(new SelectSortPanel(bars));

    }

    public static void main(String[] args) {
        SelectSortGUI frame = new SelectSortGUI();
        frame.setTitle("select sort");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
//        frame.setResizable(false);
    }

    class SelectSortPanel extends JPanel {

        Timer timer = new Timer(200, new TimerListener());
        Bar[] bars;
        double barWidth;
        double scaleY;

        int pos1 = 0;
        int pos2 = 1;

        int min = 100;
        int preMin = 0;

        int control = 0;

        public SelectSortPanel(Bar[] bars) {

            this.bars = bars;
            min = bars[0].getHeight();
            preMin = 0;
            barWidth = (width - 20) / bars.length;
            scaleY = (height - 20) / 100;
            timer.start();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < bars.length; i++) {
                double barHeight = bars[i].getHeight() * scaleY;
                double x = barWidth * i + 10;
                double y = getHeight() - barHeight - 20;
                g.setColor(bars[i].getC());
                g.fillRect((int) x, (int) y, (int) (barWidth - 2), (int) barHeight);
                g.setColor(Color.BLACK);
                g.drawRect((int) x, (int) y, (int) (barWidth - 2), (int) barHeight);

                FontMetrics context = g.getFontMetrics();
                int stringWidth = context.stringWidth(String.valueOf(bars[i].getHeight()));

                g.drawString(String.valueOf(bars[i].getHeight()), (int) (x + barWidth / 2 - stringWidth / 2 - 1), getHeight() - 2);

            }
        }

        class TimerListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (control) {
                    case 0:
                        if (pos1 == bars.length - 1)
                            timer.stop();
                        min = bars[pos2].getHeight() < min ? bars[pos2].getHeight() : min;
                        preMin = bars[pos2].getHeight() < min ? pos2 : min;

                        bars[pos1].setC(Color.RED);
                        bars[pos2].setC(Color.RED);

                        if (pos2 == bars.length ) {
                            pos1++;
                            min = bars[pos1].getHeight();
                            preMin = pos1;
                            pos2 = pos1;
                        }
                        control++;
                        repaint();
                        break;
                    case 1:
                        if (bars[pos1].getHeight() > bars[pos2].getHeight()) {
                            int tmp = bars[pos1].getHeight();
                            bars[pos1].setHeight(bars[pos2].getHeight());
                            bars[pos2].setHeight(tmp);
                            control++;
                            repaint();
                        } else {
                            control++;
                        }

                        break;
                    case 2:
                        bars[pos1].setC(commonColor);
                        bars[pos2].setC(commonColor);
                        if (pos2 == bars.length - 1) {
                            pos1++;
                            min = bars[pos1].getHeight();
                            preMin = pos1;
                            pos2 = pos1;
                        }
                        pos2++;
                        control=0;
                        repaint();
                        break;
//                    case 3:
//                        if (pos2 == bars.length - 1) {
//                            pos1++;
//                            min = bars[pos1].getHeight();
//                            preMin = pos1;
//                            pos2 = pos1;
//                        }
//                        pos2++;
//                        control = 0;
//                        repaint();
//                        break;
                    default:break;
                }
            }
        }
    }

    public static int[] getRandomArray(int a) {
        int[] tmp = new int[a];
        Random random = new Random();
        for (int i = 0; i < a; i++) {
            tmp[i] = random.nextInt(100);
        }
        return tmp;
    }

    class Bar {
        private int height;
        private Color c;

        public Bar(int height, Color c) {
            this.c = c;
            this.height = height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setC(Color c) {
            this.c = c;
        }

        public int getHeight() {
            return height;
        }

        public Color getC() {
            return c;
        }
    }
}
