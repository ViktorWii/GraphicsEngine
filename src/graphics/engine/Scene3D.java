package graphics.engine;

import graphics.elements.*;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.shape.Line;

public class Scene3D {
    public View viewMode = View.ONE_POINT_PERSPECTIVE;

    private ArrayList<Point> points = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    public Group viewport = new Group();

    private Controller controller;

    private double translationX = 200;
    private double translationY = 200;


    public Scene3D(Controller controller) {
        this.controller = controller;
        controller.scene3D = this;

        Figure firstPyramid = initFirstPyramid();
//        Figure secondPyramid = initSecondPyramid();
        controller.activeFigure = firstPyramid;

        updateViewport();
    }


    public void updateViewport() {
        viewport.getChildren().clear();
        drawEdges();
    }

    private void drawEdges() {
        for (Edge edge : edges) {
            drawEdge(edge);
        }
    }

    private void drawEdge(Edge edge) {
        double[] proj1 = edge.firstPoint.getProjection(viewMode);
        double[] proj2 = edge.secondPoint.getProjection(viewMode);

        Line line = new Line(proj1[0] + translationX, proj1[1] + translationY,
                proj2[0] + translationX, proj2[1] + translationY);
        viewport.getChildren().add(line);
    }




    private Figure initFirstPyramid() {
        Point p0 = new Point(2, 2, 0);
        Point p1 = new Point(1, 1, 0);
        Point p2 = new Point(1, -1, 0);
        Point p3 = new Point(-1, 1, 0);
        Point p4 = new Point(-1, -1, 0);

        addPyramidToBuffer(p0, p1, p2, p3, p4);

        Polygon front = new Polygon(p0, p1, p2);
        Polygon right = new Polygon(p0, p2, p4);
        Polygon back = new Polygon(p0, p4, p3);
        Polygon left = new Polygon(p0, p3, p1);
        Point[] bottomPoints = {p1, p2, p3, p4};
        Polygon bottom = new Polygon(bottomPoints);

        Polygon[] pyramidPolygons = {front, right, back, left, bottom};
        return new Figure(new ArrayList<Polygon>(Arrays.asList(pyramidPolygons)));
    }

    private Figure initSecondPyramid() {
        Point p0 = new Point(100, 100, 5);
        Point p1 = new Point(75, 140, 5);
        Point p2 = new Point(75, 60, 5);
        Point p3 = new Point(50, 140, 6);
        Point p4 = new Point(50, 60, 6);

        addPyramidToBuffer(p0, p1, p2, p3, p4);

        Polygon front = new Polygon(p0, p1, p2);
        Polygon right = new Polygon(p0, p2, p4);
        Polygon back = new Polygon(p0, p4, p3);
        Polygon left = new Polygon(p0, p3, p1);
        Point[] bottomPoints = {p1, p2, p3, p4};
        Polygon bottom = new Polygon(bottomPoints);

        Polygon[] pyramidPolygons = {front, right, back, left, bottom};
        return new Figure(new ArrayList<Polygon>(Arrays.asList(pyramidPolygons)));
    }

    private void addPyramidToBuffer(Point p0, Point p1, Point p2, Point p3, Point p4) {
        points.add(p0);
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);

        edges.add(new Edge(p0, p1));
        edges.add(new Edge(p0, p2));
        edges.add(new Edge(p0, p3));
        edges.add(new Edge(p0, p4));
        edges.add(new Edge(p1, p2));
        edges.add(new Edge(p2, p4));
        edges.add(new Edge(p4, p3));
        edges.add(new Edge(p3, p1));
    }

}
