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


    public Figure firstPyramid;
    public Figure secondPyramid;

    public Scene3D(Controller controller) {
        this.controller = controller;
        controller.scene3D = this;

        firstPyramid = createPyramid(15);
        secondPyramid = createPyramid(25, new Point(0, 0, 25 * Math.sqrt(2. / 3)));
        secondPyramid.translate(new double[]{0, 0, -25 * Math.sqrt(2. / 3) * 2/3});
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


    private Figure createPyramid(double edgeLength, Point pivotPoint) {
        Tetrahedron tetrahedron = new Tetrahedron(edgeLength, pivotPoint);
        edges.addAll(tetrahedron.edges);
        vertices.addAll(tetrahedron.vertices);

        return tetrahedron;
    }

    private Figure createPyramid(double edgeLength) {
        Tetrahedron tetrahedron = new Tetrahedron(edgeLength);
        edges.addAll(tetrahedron.edges);
        vertices.addAll(tetrahedron.vertices);

        return tetrahedron;
    }
}
