package graphics.elements;

import java.util.ArrayList;
import java.util.Arrays;

public class Tetrahedron extends Figure {
    public Tetrahedron(ArrayList<Polygon> polygons) {
        super(polygons);
    }


    public Tetrahedron(double edgeLength, Point pivotPoint) {
        this(edgeLength);
        this.pivotPoint = pivotPoint;
    }

    public Tetrahedron(double edgeLength) {
        super(new ArrayList<Polygon>());

        double height = edgeLength * Math.sqrt(2. / 3);
        double R = edgeLength * Math.sqrt(6) / 4;

        pivotPoint = new Point(0, 0, height / 3);

        Point topVertex = new Point(0, 0, height - pivotPoint.z);
        Point frontVertex = new Point(edgeLength, 0, -pivotPoint.z);
        Point leftVertex = new Point(0, -edgeLength, -pivotPoint.z);
        Point rightVertex = new Point(0, edgeLength, -pivotPoint.z);

        Point[] points = new Point[]{topVertex, frontVertex, leftVertex, rightVertex};
        for (Point point : points)
            point.figure = this;

        vertices.addAll(Arrays.asList(points));


        Polygon leftPolygon = new Polygon(topVertex, leftVertex, frontVertex);
        Polygon rightPolygon = new Polygon(topVertex, frontVertex, rightVertex);
        Polygon backPolygon = new Polygon(topVertex, rightVertex, leftVertex);
        Polygon bottomPolygon = new Polygon(frontVertex, leftVertex, rightVertex);
        polygons.addAll(Arrays.asList(leftPolygon, rightPolygon, backPolygon, bottomPolygon));


        Edge frontEdge = new Edge(topVertex, frontVertex);
        Edge leftBackEdge = new Edge(topVertex, leftVertex);
        Edge rightBackEdge = new Edge(topVertex, rightVertex);
        Edge backBottomEdge = new Edge(leftVertex, rightVertex);
        Edge leftBottomEdge = new Edge(frontVertex, leftVertex);
        Edge rightBottomEdge = new Edge(frontVertex, rightVertex);
        edges.addAll(Arrays.asList(frontEdge, leftBackEdge, rightBackEdge, backBottomEdge, leftBottomEdge, rightBottomEdge));
    }
}
