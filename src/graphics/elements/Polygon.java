package graphics.elements;

import java.util.ArrayList;

public class Polygon {
    ArrayList<Point> vertices;

    public Polygon(Point p1, Point p2, Point p3) {
        this.vertices = new ArrayList<Point>();
        vertices.add(p1);
        vertices.add(p2);
        vertices.add(p3);
    }

    public Polygon(Point[] vertices) {
        this.vertices = new ArrayList<Point>();
        for (Point vertex : vertices)
            this.vertices.add(vertex);
    }

    public Polygon(ArrayList<Point> vertices) {
        this.vertices = vertices;
    }




    public void translate(double[] translation) {
        for (Point vertex : vertices) {
            vertex.translate(translation);
        }
    }


    public void rotateX(double angle) {
        for (Point vertex : vertices) {
            vertex.rotateWorldX(angle);
        }
    }

    public void rotateY(double angle) {
        for (Point vertex : vertices) {
            vertex.rotateWorldY(angle);
        }
    }

    public void rotateZ(double angle) {
        for (Point vertex : vertices) {
            vertex.rotateWorldZ(angle);
        }
    }

    public void scale(double[] scale) {
        for (Point vertex : vertices) {
            vertex.scale(scale);
        }
    }
}
