import Path.Spline;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

public class CustomPlotter extends Frame {

    Graphics g;
    Spline spline;

    int startingPoint;
    int endingPoint;
    boolean points = true;

    public CustomPlotter(Spline spline, boolean points) {
        this.spline = spline;
        this.points = points;
        setTitle("Spline Plotter");

        startingPoint = (int) spline.points[0].x;
        endingPoint = 366;

        setLayout(new FlowLayout());
        setSize(244,366);
        setVisible(true);

        g = getGraphics();
        paint(g);
    }

    public void paint(Graphics g) {

        int prevX =  startingPoint;
        int prevY = 366 - (int) evaluateFunction(prevX);

        g.setColor(Color.WHITE);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("/Users/maudieguez/IdeaProjects/SplinePrototype/src/main/resources/field2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g2.drawImage(image, 0,0, null);

        for (int current_x = prevX + 1; current_x < endingPoint; current_x++) {
            int x = current_x;
            int y = 366 - (int) evaluateFunction(x);

            g2.draw(new Line2D.Float(prevX, prevY, x, y));

            prevX = x;
            prevY = y;
        }

        if (points) {
            g2.setColor(new Color(49, 100, 212));
            for (int currentPoint = 0; currentPoint < spline.points.length; currentPoint++) {
                g2.fillOval((int) spline.points[currentPoint].x - 4, (366 - 4) - (int) spline.points[currentPoint].y, 9, 9);
            }
        }

    }

    public double evaluateFunction(double x) {
        return spline.getY(x);
    }
}
