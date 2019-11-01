package graphics.elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Figure {
    public Point pivotPoint;
    public ArrayList<Polygon> polygons = new ArrayList<>();
    public ArrayList<Edge> edges = new ArrayList<>();
    public Set<Point> vertices = new HashSet<>();



    public Figure(ArrayList<Polygon> polygons) {
        this.polygons = polygons;
        for (Polygon polygon : polygons) {
            Point previousPoint = polygon.vertices.get(0);
            for (Point point : polygon.vertices) {
                point.figure = this;

                Edge edge = new Edge(previousPoint, point);
                edges.add(edge);

                vertices.add(point);

                previousPoint = point;
            }

            Edge closingEdge = new Edge(previousPoint, polygon.vertices.get(0));
            edges.add(closingEdge);
        }
    }




    public void translate(double[] translation) {
        pivotPoint.translate(translation);

        for (Point vertex : vertices) {
            vertex.translate(translation);
        }
    }


    public void rotateWorldX(double angle) {
        pivotPoint.rotateWorldX(angle);

        for (Point vertex : vertices) {
            vertex.rotateWorldX(angle);
        }
    }

    public void rotateWorldY(double angle) {
        pivotPoint.rotateWorldY(angle);

        for (Point vertex : vertices) {
            vertex.rotateWorldY(angle);
        }
    }

    public void rotateWorldZ(double angle) {
        pivotPoint.rotateWorldZ(angle);

        for (Point vertex : vertices) {
            vertex.rotateWorldZ(angle);
        }
    }


    public void rotateLocalX(double angle) {
        for (Point vertex : vertices) {
            vertex.rotateLocalX(angle);
        }
    }

    public void rotateLocalY(double angle) {
        for (Point vertex : vertices) {
            vertex.rotateLocalY(angle);
        }
    }

    public void rotateLocalZ(double angle) {
        for (Point vertex : vertices) {
            vertex.rotateLocalZ(angle);
        }
    }


    public void worldScale(double[] scale) {
        pivotPoint.worldScale(scale);

        for (Point vertex : vertices) {
            vertex.worldScale(scale);
        }
    }

    public void localScale(double[] scale) {
        for (Point vertex : vertices) {
            vertex.localScale(scale);
        }
    }


    public double[][] getBodyMatrix() {
        double[] pointLocation = getPointInCenter();

        double[][] matrix = new double[polygons.size()][4];

        for (int i = 0; i < polygons.size(); i++) {
            double[] planeEquation = polygons.get(i).getPlaneEquation();
            planeEquation = checkPlaneEquation(planeEquation, pointLocation);
            matrix[i] = planeEquation;
        }

        return matrix;
    }


    private double[] getPointInCenter() {
        double[] center = new double[3];
        for (Point vertex : vertices) {
            center[0] += vertex.x;
            center[1] += vertex.y;
            center[2] += vertex.z;
        }
        center[0] = center[0] / vertices.size();
        center[1] = center[1] / vertices.size();
        center[2] = center[2] / vertices.size();

        return center;
    }


    public double[] checkPlaneEquation(double[] planeEquation, double[] pointLocation) {
        if (dotProduct(planeEquation, pointLocation) < 0) {
            planeEquation[0] = -planeEquation[0];
            planeEquation[1] = -planeEquation[1];
            planeEquation[2] = -planeEquation[2];
            planeEquation[3] = -planeEquation[3];
        }

        return planeEquation;
    }


    public double dotProduct(double[] first, double[] second) {
        return first[0] * second[0] + first[1] * second[1] + first[2] * second[2];
    }

}

