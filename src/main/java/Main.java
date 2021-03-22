import Path.CubicFunction;
import Path.Point;
import Path.Spline;
import Path.SplineGenerator;
import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.List;

public class Main {


    // TODO: Primera accion es acomodarse al heading inicial, luego empezar a contar
    public static void main(String[] args) {

        // Primer punto es donde se empieza
        //Point[] points = new Point[] {new Point(2,2), new Point(4,6), new Point(8,6), new Point(10,10)};
        //Spline spline = new Spline(functions, points);
        //Spline spline = new Spline(points, 1, 1);

        Point[] first = new Point[] {
                new Point(166,56),
                new Point(175, 89),
                new Point(177, 95),
                new Point(210, 150),
                new Point(220,170)
        };

        Spline spline = new Spline(first, 5,5);
        double heading = spline.getHeading(6);
        System.out.println(heading);
        Plotter plot = new Plotter(spline.functions, spline.points);
        CustomPlotter splot = new CustomPlotter(spline,true);
        CustomPlotter splot2 = new CustomPlotter(spline,false);
    }

        /*
        SimpleMatrix coeff = SplineGenerator.getSplineFunctions(points, 1, 1);
        CubicFunction f1 = new CubicFunction(coeff.get(0), coeff.get(1), coeff.get(2), coeff.get(3));
        CubicFunction f2 = new CubicFunction(coeff.get(4),coeff.get(5), coeff.get(6), coeff.get(7));
        CubicFunction f3 = new CubicFunction(coeff.get(8),coeff.get(9), coeff.get(10), coeff.get(11));
        //CubicFunction f4 = new CubicFunction(coeff.get(12), coeff.get(13), coeff.get(14), coeff.get(15));
        // CubicFunction[] functions = new CubicFunction[] {f1,f2,f3, f4};
        List<CubicFunction> functions = new ArrayList<>();
        functions.add(f1);
        functions.add(f2);
        functions.add(f3);
        //functions.add(f4);
        */
}
