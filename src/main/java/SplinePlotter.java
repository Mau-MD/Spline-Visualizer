/*
 *  =====================================================================
 *  Plotter.java : A very simple Java plotter
 *
 *  Written By : David Chancogne (doc@isr.umd.edu)         September 1997
 *  =====================================================================
 */

import Path.CubicFunction;
import Path.Spline;

import java.awt.*;
import java.lang.*;
import java.util.List;
import java.util.Random;

class SplinePlotter extends Frame {

    Button QuitButton;
    Graphics g;
    Spline spline;

    /*
    public static void main(String args[]){
        System.out.println("Simple Plotter");

        // The main method just create an instance of the class
        Plotter p = new Plotter();
    }
    */

    // The class constructor
    // Called upon creation of the object Plotter

    public SplinePlotter(Spline spline) {

        this.spline = spline;
        setTitle("Simple Plotter");

        setLayout(new FlowLayout());
        QuitButton = new Button("Quit");
        add(QuitButton);

        resize(600,300);
        show();

        // Call paint() method to do plotting

        g = getGraphics();
        paint(g);
    }

    /*
     *  =======================================================
     *  paint() method : x will go from 0 to SCALE_X
     *                   y will go from SCALE_Y/2 to -SCALE_Y/2
     *  =======================================================
     */

    public void paint(Graphics g) {
        int SCALE_X = 600; // Maximum value of x coordinate.
        int SCALE_Y = 1000; // Range of y-coordinate values.
        int MIN_X   = 10; // Parameters used to calculate coordinates in the window
        int MAX_X   = 510;
        int DELTA_X = MAX_X-MIN_X;
        int MIN_Y   = 60;
        int MAX_Y   = 260;
        int DELTA_Y = MAX_Y-MIN_Y;

        int prev_x_pt=0;
        int prev_y_pt=0;

        // Draw axis in black

        g.setColor(Color.black);
        g.drawLine( MIN_X, MIN_Y+DELTA_Y/2, MAX_X, MIN_Y+DELTA_Y/2 );
        g.drawLine( MIN_X, MIN_Y, MIN_X, MAX_Y);

        // Change color to red for plotting


        // Calculate the original point in pixel system

        double y_org = FunctionToPlot(0);
        prev_x_pt = MIN_X;
        prev_y_pt = (int) (MIN_Y + (DELTA_Y) / 2 - (DELTA_Y) * y_org / SCALE_Y);

        // Loop on x
        // The loop is done on x calculated in pixel system.
        // Then x is converted in a value in 0 to SCALE_X
        // The corresponding y is evaluated (call to FunctionToPlot())
        // Y is converted back in pixel system.
        // We then can draw a line between previous and current point.

        for (int x_pt = MIN_X; x_pt < MAX_X + 1; x_pt++) {
            double x = (double) ((double) (x_pt - MIN_X) * (double) SCALE_X / (double) (DELTA_X));
            double y = FunctionToPlot(x);
            int y_pt = (int) (MIN_Y + (DELTA_Y) / 2 - (DELTA_Y) * y / SCALE_Y);


            if (y != Math.PI)g.drawLine(prev_x_pt, prev_y_pt, x_pt, y_pt);

            //g.drawOval((int)points[i].x,  SCALE_Y - (int) points[i].y, 5,5);
            prev_x_pt = x_pt;
            prev_y_pt = y_pt;
        }

        int pointX = (int) ((int) spline.points[0].x * (1.75 * 300.0 / SCALE_X));
        int pointY = (int) (MIN_Y + (DELTA_Y) / 2 - (DELTA_Y) * spline.points[0].y / SCALE_Y);
        int finalPointY = (int)(pointY * 0.97);
        g.fillOval(pointX,finalPointY,5,5);
        g.drawString(spline.points[0].x + " , " + spline.points[0].y, pointX, finalPointY + 50);
    }

    // The event handler (jdk1.0) For the "Quit" button

    public  boolean action (Event event, Object object) {
        if (event.target == QuitButton) {
            System.exit(0);
        }
        return true;
    }

    // The function to plot

    public double FunctionToPlot(double x) {
        double X_INT = 10;
        double V_INT = 10;
        double W = Math.sqrt(10);

        return spline.getY(x);
        //return (double)(X_INT*Math.cos(W*x)+V_INT*Math.sin(W*x)/W);
    }
}