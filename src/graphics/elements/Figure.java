package graphics.elements;

import java.util.ArrayList;

public class Figure {
    Point relativeLocation;
    Point worldLocation;
    ArrayList<Polygon> polygons;


    public Figure(ArrayList<Polygon> polygons) {
        this.polygons = polygons;
    }




    public void translate(double[] translation) {
        for (Polygon polygon : polygons) {
            polygon.translate(translation);
        }
    }


    public void rotateX(double angle) {
        for (Polygon polygon : polygons) {
            polygon.rotateX(angle);
        }
    }

    public void rotateY(double angle) {
        for (Polygon polygon : polygons) {
            polygon.rotateY(angle);
        }
    }

    public void rotateZ(double angle) {
        for (Polygon polygon : polygons) {
            polygon.rotateZ(angle);
        }
    }

    public void scale(double[] scale) {
        for (Polygon polygon : polygons) {
            polygon.scale(scale);
        }
    }
}
