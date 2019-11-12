package graphics.engine;

import graphics.elements.*;
import graphics.engine.data.*;
import javafx.scene.Group;

import java.util.ArrayList;

import javafx.scene.shape.Line;

public class Scene3D {
    public View viewMode = View.PERSPECTIVE;

    private ArrayList<Point> vertices = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    public Group viewport = new Group();

    private Controller controller;

    private double translationX = 200;
    private double translationY = 200;

    public double z0 = 100;

    public boolean drawInvisibleEdges;

    public Figure firstPyramid;
    public Figure secondPyramid;
    public Figure platonicFigure;

    public Scene3D(Controller controller) {
        this.controller = controller;
        controller.scene3D = this;

//        firstPyramid = createPyramid(15);
//        controller.activeFigure = firstPyramid;

//        secondPyramid = createPyramid(25, new Point(0, 0, 25 * Math.sqrt(2. / 3)));
//        secondPyramid.translate(new double[]{0, 0, -25 * Math.sqrt(2. / 3) * 2 / 3});

//        platonicFigure = createPlatonicFigure(new SphereData());
//        platonicFigure = createPlatonicFigure(new TetrahedronData());
//        platonicFigure = createPlatonicFigure(new OctahedronData());
//        platonicFigure = createPlatonicFigure(new CubeData());
//        platonicFigure = createPlatonicFigure(new DodecahedronData());
//        platonicFigure = createPlatonicFigure(new IcosahedronData());
        platonicFigure = createPlatonicFigure(new TorusData());
//        platonicFigure.toggleCheckPlane();

        controller.activeFigure = platonicFigure;

        updateViewport();
    }


    public void updateViewport() {
        viewport.getChildren().clear();

        drawAxis();


        if (drawInvisibleEdges)
            drawEdges(controller.activeFigure.edges);
        else {
            ArrayList<Polygon> allFrontPolygons = getAllFrontFaces(controller.activeFigure);
            drawPolygons(allFrontPolygons);
        }
    }




    private ArrayList<Polygon> getAllFrontFaces(Figure figure) {
        ArrayList<double[]> frontFaces = new ArrayList<>();
        ArrayList<Polygon> frontPolygons = new ArrayList<>();

        double[][] bodyMatrix = controller.activeFigure.getBodyMatrix();


        for (int i = 0; i < bodyMatrix.length; i++) {
            double[] pointOfView = getPointOfView();
            if (viewMode == View.PERSPECTIVE) {
                double[] facePoint = figure.polygons.get(i).vertices.get(0).getWorldLocation();
                pointOfView[0] -= facePoint[0];
                pointOfView[1] -= facePoint[1];
                pointOfView[2] -= facePoint[2];
            }

            if (dotProduct(bodyMatrix[i], pointOfView) > 0) {
                frontFaces.add(bodyMatrix[i]);
                frontPolygons.add(figure.polygons.get(i));
            }
        }

        return frontPolygons;
    }

    private double[] getPointOfView() {
        switch (viewMode) {
            case FRONT: {
                return new double[]{1, 0, 0};
            }
            case RIGHT: {
                return new double[]{0, 1, 0};
            }
            case TOP: {
                return new double[]{0, 0, 1};
            }
            case PERSPECTIVE: {
                return new double[]{0, 0, z0};
            }
        }

        return null;
    }

    public double dotProduct(double[] first, double[] second) {
        return first[0] * second[0] + first[1] * second[1] + first[2] * second[2];
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



    private void drawPolygons(ArrayList<Polygon> polygons) {
        for (Polygon p : polygons) {
            drawEdges(p.edges);
        }
    }


    private void drawEdges(ArrayList<Edge> edges) {
        for (Edge edge : edges) {
            drawEdge(edge);
        }
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

    private Figure createPlatonicFigure(FigureData data) {
        ArrayList<Point> vertices = new ArrayList<>();
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Polygon> polygons = new ArrayList<>();

        for (double[][] polygonPoint : data.verticesInPolygons) {
            double[] pointLocation = polygonPoint[0];
            Point newPoint = new Point(pointLocation[0], pointLocation[1], pointLocation[2]);
            vertices.add(newPoint);
        }

        for (int i = 0; i < data.indices.length; i += data.step) {
            ArrayList<Point> polygonVertices = new ArrayList<>();
            ArrayList<Integer> vertexIndices = new ArrayList<>();
            for (int j = 0; j < data.step; j++) {
                polygonVertices.add(vertices.get(data.indices[i + j]));
                vertexIndices.add(data.indices[i + j]);
            }
            Polygon newPolygon = new Polygon(polygonVertices, vertexIndices);
            polygons.add(newPolygon);
            edges.addAll(newPolygon.edges);
        }

        Figure sphere = new Figure(vertices, edges, polygons);
        return sphere;
    }
}
