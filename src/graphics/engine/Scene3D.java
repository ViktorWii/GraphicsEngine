package graphics.engine;

import graphics.elements.*;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.shape.Line;

public class Scene3D {
    public View viewMode = View.PERSPECTIVE;

    private ArrayList<Point> vertices = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    public Group viewport = new Group();

    private Controller controller;

    private double translationX = 200;
    private double translationY = 200;


    public Scene3D(Controller controller) {
        this.controller = controller;
        controller.scene3D = this;

        Figure firstPyramid = createPyramid(15);
        controller.activeFigure = firstPyramid;

        updateViewport();
    }


    public void updateViewport() {
        viewport.getChildren().clear();

        drawAxis();
        drawEdges();
    }

    private void drawAxis() {
        Edge X = new Edge(new Point(-200, 0, 0), new Point(200, 0, 0));
        Edge Y = new Edge(new Point(0, -200, 0), new Point(0, 200, 0));
        Edge Z = new Edge(new Point(0, 0, -200), new Point(0, 0, 200));


        Line lineX = drawEdge(X);
        lineX.setStyle("-fx-stroke: red;");
        Line lineY = drawEdge(Y);
        lineY.setStyle("-fx-stroke: green;");
        Line lineZ = drawEdge(Z);
        lineZ.setStyle("-fx-stroke: blue;");
    }


    private void drawEdges() {
        for (Edge edge : edges) {
            drawEdge(edge);
        }
    }

    private Line drawEdge(Edge edge) {
        double[] proj1 = edge.firstPoint.getProjection(viewMode);
        double[] proj2 = edge.secondPoint.getProjection(viewMode);

        Line line = new Line(proj1[0] + translationX, proj1[1] + translationY,
                proj2[0] + translationX, proj2[1] + translationY);
        viewport.getChildren().add(line);

        return line;
    }




    private Figure createPyramid(double edgeLength) {
        Tetrahedron tetrahedron = new Tetrahedron(edgeLength);
        edges.addAll(tetrahedron.edges);
        vertices.addAll(tetrahedron.vertices);

        return tetrahedron;
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
        vertices.add(p0);
        vertices.add(p1);
        vertices.add(p2);
        vertices.add(p3);
        vertices.add(p4);

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
