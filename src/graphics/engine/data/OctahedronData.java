package graphics.engine.data;

public class OctahedronData extends FigureData {
    public OctahedronData() {
        vcount = 6;  // количество вершин
        icount = 8; // количество граней
        size = 30;

        verticesInPolygons = new double[][][]{
                {{size / 2., size / 2., 0}, {0}, {0}},
                {{size / 2., 0, size / 2.}, {0}, {0}},
                {{0, size / 2., size / 2.}, {0}, {0}},
                {{size, size / 2., size / 2.}, {0}, {0}},
                {{size / 2., size, size / 2.}, {0}, {0}},
                {{size / 2., size / 2., size}, {0}, {0}},
        };

        indices = new int[]{
                0, 1, 2,
                0, 1, 3,
                0, 2, 4,
                0, 3, 4,
                5, 1, 2,
                5, 1, 3,
                5, 2, 4,
                5, 3, 4
        };
    }
}
