package graphics.engine.data;

public class TetrahedronData extends FigureData {
    public TetrahedronData() {
        vcount = 4;  // количество вершин
        icount = 4; // количество граней
        size = 30;
        step = 3;

        verticesInPolygons = new double[][][]{
                {{size, 0, 0}, {0}, {0}},
                {{0, 0, size}, {0}, {0}},
                {{size, size, size}, {0}, {0}},
                {{0, size, 0}, {0}, {0}}
        };

        indices = new int[]{
                0, 1, 2,
                0, 2, 3,
                0, 1, 3,
                1, 2, 3
        };
    }
}
