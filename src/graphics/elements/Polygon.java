package graphics.elements;

import java.util.ArrayList;
import java.util.Arrays;

public class Polygon {
    ArrayList<Point> vertices;
    ArrayList<Edge> edges;

    public Polygon(Point p1, Point p2, Point p3) {
        this(new Point[]{p1, p2, p3});
        this.vertices = new ArrayList<Point>();
        vertices.add(p1);
        vertices.add(p2);
        vertices.add(p3);
    }

    public Polygon(ArrayList<Point> vertices) {
        this(vertices.toArray(new Point[vertices.size()]));
    }

    public Polygon(Point[] vertices) {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();

        this.vertices.addAll(Arrays.asList(vertices));

        for (int i = 0; i < vertices.length - 1; i++) {
            Edge curEdge = new Edge(vertices[i], vertices[i + 1]);
            edges.add(curEdge);
        }
        Edge lastEdge = new Edge(vertices[vertices.length - 1], vertices[0]);
        edges.add(lastEdge);
    }



    public double[] getPlaneEquation() {
        double[] planeEquation = new double[4];

        Edge firstEdge = edges.get(0);
        Edge secondEdge = edges.get(1);

        double[] v1 = firstEdge.secondPoint.getWorldLocation();
        double[] v2 = firstEdge.firstPoint.getWorldLocation();
        double[] v3 = secondEdge.secondPoint.getWorldLocation();

        planeEquation[0] = (v2[1] - v1[1]) * (v3[2] - v1[2]) - (v2[2] - v1[2]) * (v3[1] - v1[1]);
        planeEquation[1] = (v2[2] - v1[2]) * (v3[0] - v1[0]) - (v2[0] - v1[0]) * (v3[2] - v3[1]);
        planeEquation[2] = (v2[0] - v1[0]) * (v3[1] - v1[1]) - (v2[1] - v1[1]) * (v3[0] - v1[0]);
        planeEquation[3] = -(planeEquation[0] * v1[0] + planeEquation[1] * v1[1] + planeEquation[2] * v1[2]);


        return planeEquation;
    }

}
