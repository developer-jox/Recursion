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
import javax.swing.JPanel;

/**
 *
 * @author joaort192
 */
public class Recursion extends JPanel {

    Point p0, p1;

    public Recursion() {
//        Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
//        setPreferredSize(new Dimension(res.width, res.height - 79));
        setPreferredSize(new Dimension(700, 700));
//        setSize(res.width, res.height - 79);
        setSize(700, 700);
        setDoubleBuffered(true);
    }

    void run() {
        p0 = new Point(getWidth() / 2, getHeight() - 50);
        p1 = new Point(getWidth() / 2, getHeight() / 2 + 50);
    }

    private void draw(Point p0, Point p1, int level, Graphics g) {
        drawLine(g, p0, p1);
        System.out.println("distance: " + p0.distance(p1));
        if (level >= 7) {
            return;
        }

        Point a = P(p0, p1, 1 + Math.random(), 0.3 + 0.1*(Math.random() - 0.5));
        g.setColor(Color.blue);
        g.drawOval(a.x - 1, a.y - 1, 2, 2);
        draw(p1, a, level + 1, g);

        Point b = P(p0, p1, 1 + Math.random(), -0.3 + 0.1*(Math.random() - 0.5));
//        Point c = P(p0, p1, 0.5, -0.4);
        g.setColor(Color.red);
        g.drawOval(b.x - 1, b.y - 1, 2, 2);
        g.setColor(Color.black);
        draw(p1, b, level + 1, g);

        Point c = P(p0, p1, 1.1 + Math.random(), 0.1*(Math.random() - 0.5));
        g.setColor(Color.green);
        g.drawOval(c.x - 1, c.y - 1, 2, 2);
        g.setColor(Color.black);
        draw(p1, c, level + 1, g);


//        draw(a, c, g);
    }

    public static Point P(Point p0, Point p1, double u, double v) {
        int dx = p1.x - p0.x;
        int dy = p1.y - p0.y;

        return new Point((int) (p0.x + u * dx - v * dy),
                (int) (p0.y + u * dy + v * dx));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(0.5f));

//        g2.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
//        g2.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight() / 2 + -100);
        draw(p0, p1, 0, g);
//        repaint();
//        try {
//            Thread.sleep(10);
//        } catch (Exception e) {
//        }
    }

    private void drawLine(Graphics g, Point p0, Point p1) {
        g.drawLine(p0.x, p0.y, p1.x, p1.y);
    }

}
