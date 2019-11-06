package graphics.engine.data;

public class CubeData extends FigureData {
    public CubeData() {
        vcount = 8;  // количество вершин
        icount = 6; // количество граней
        size = 30;
        step = 4;

        verticesInPolygons = new double[][][]{
                {{0, 0, 0}, {0}, {0}},
                {{size, 0, 0}, {0}, {0}},
                {{0, size, 0}, {0}, {0}},
                {{0, 0, size}, {0}, {0}},
                {{size, size, 0}, {0}, {0}},
                {{size, 0, size}, {0}, {0}},
                {{0, size, size}, {0}, {0}},
                {{size, size, size}, {0}, {0}},
        };

        indices = new int[]{
                0, 1, 5, 3,
                0, 1, 4, 2,
                0, 2, 6, 3,
                7, 5, 3, 6,
                7, 6, 2, 4,
                7, 4, 1, 5
        };
    }

}
