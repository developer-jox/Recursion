/* 
 * Copyright (C) 2015 developer-jox <developer.jox@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package recursion;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author developer-jox <developer.jox@gmail.com>
 */
public class Recursion extends JPanel {

    Point p0, p1;
    int maxLevel = 13;
    int counter = 1;
    boolean init = true;
    double v1Min = 0.3;

    public Recursion() {
        Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(res.width, res.height - 79));
//        setPreferredSize(new Dimension(700, 700));
        setSize(res.width, res.height - 79);
//        setSize(700, 700);
        JButton but = new JButton("New fractal");
        add("North", but);
        but.setBackground(Color.white);
        setDoubleBuffered(true);
        but.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                counter += 2;
            }
        });
    }

    void run() {
        p0 = new Point(getWidth() / 2, getHeight() + 10);
        p1 = new Point(getWidth() / 2, getHeight() / 2 + 350);
    }

    private void draw(Point p0, Point p1, int level, Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        if (level > maxLevel - 1) {
            v1Min = 0.42;
        }
        if (level == maxLevel) {
            g.setColor(new Color(0, 223, 0, 20));
        } else {
            g.setColor(Color.black);
        }
        g.setStroke(new BasicStroke(Math.max(0.5f, maxLevel - level) / 6f));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        drawLine(g, p0, p1, level);

//        System.out.println("distance: " + p0.distance(p1));
        if (level >= maxLevel) {
            return;
        }
        double V = v1Min + 0.1 * (Math.random() - 0.6);
        if (level == 0) {
            V = 0.2;
        }
        Point a = P(p0, p1, 1 + Math.random(), V);
//        g.setColor(Color.blue);
//        g.drawOval(a.x - 1, a.y - 1, 2, 2);
//        g.setColor(Color.black);
        draw(p1, a, level + 1, g);

        Point b = P(p0, p1, 1 + Math.random(), -V);
//        Point c = P(p0, p1, 0.5, -0.4);
//        g.setColor(Color.red);
//        g.drawOval(b.x - 1, b.y - 1, 2, 2);
//        g.setColor(Color.black);
        draw(p1, b, level + 1, g);

        //        Point c = P(p0, p1, 1.1 + Math.random(), 0.1*(Math.random() - 0.5));
//        g.setColor(Color.green);
//        g.drawOval(c.x - 1, c.y - 1, 2, 2);
//        g.setColor(Color.black);
//        draw(p1, c, level + 1, g);
//        draw(a, c, g);
    }

    public static Point P(Point p0, Point p1, double u, double v) {
        int dx = p1.x - p0.x;
        int dy = p1.y - p0.y;

        return new Point((int) ((p0.x + u * dx - v * dy)), (int) ((p0.y + u * dy + v * dx)));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setStroke(new BasicStroke(0.5f));

//        g2.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
//        g2.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight() / 2 + -100);
        if (counter % 2 == 0) {
            draw(p0, p1, 0, g);
        }
        if (init) {
            init = false;
            counter++;
        }
    }

    private void drawLine(Graphics g, Point p0, Point p1, int level) {
        int x0 = p0.x, x1 = p0.x + 3, x2 = p0.x + 5, x3 = p0.x + 7, x4 = p0.x + 5, x5 = p0.x + 3;
        int y0 = p0.y, y1 = p0.y - 2, y2 = p0.y - 2, y3 = p0.y, y4 = p0.y + 2, y5 = p0.y + 2;
        Random rnd = new Random();
        int test = rnd.nextInt(2);
        if (test == 0) {
            x1 -= 4;
            x2 -= 8;
            x3 -= 10;
            x4 -= 8;
            x5 -= 4;
        } else if (test == 1) {
            test = rnd.nextInt(10);
            y1 += test;
            y2 += test;
            y4 -= test;
            y5 -= test;
        }
        if (level == maxLevel) {
            int xPoints[] = {x0, x1, x2, x3, x4, x5, x0};
            int yPoints[] = {y0, y1, y2, y3, y4, y5, y0};
            Color col = g.getColor();
            g.fillPolygon(xPoints, yPoints, xPoints.length);
            g.setColor(new Color(0, 0, 0, 20));
            g.drawLine(x0, y0, x3, y3);
            g.setColor(col);
//            g.drawOval(p0.x - 2, p0.y - 2, 6, 9);
        } else {
            g.drawLine(p0.x, p0.y, p1.x, p1.y);
        }
    }

}
