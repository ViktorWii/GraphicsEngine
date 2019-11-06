package graphics.engine.data;

public class DodecahedronData extends FigureData {
    public DodecahedronData() {
        vcount = 20;  // количество вершин
        icount = 12; // количество граней
        size = 30;
        step = 5;

        verticesInPolygons = new double[][][]{
                {{0.607f * size, 0.000f * size, 0.795f * size}, {0}, {0}},
                {{0.188f * size, 0.577f * size, 0.795f * size}, {0}, {0}},
                {{-0.491f * size, 0.357f * size, 0.795f * size}, {0}, {0}},
                {{-0.491f * size, -0.357f * size, 0.795f * size}, {0}, {0}},
                {{0.188f * size, -0.577f * size, 0.795f * size}, {0}, {0}},
                {{0.982f * size, 0.000f * size, 0.188f * size}, {0}, {0}},
                {{0.304f * size, 0.934f * size, 0.188f * size}, {0}, {0}},
                {{-0.795f * size, 0.577f * size, 0.188f * size}, {0}, {0}},
                {{-0.795f * size, -0.577f * size, 0.188f * size}, {0}, {0}},
                {{0.304f * size, -0.934f * size, 0.188f * size}, {0}, {0}},
                {{0.795f * size, 0.577f * size, -0.188f * size}, {0}, {0}},
                {{-0.304f * size, 0.934f * size, -0.188f * size}, {0}, {0}},
                {{-0.982f * size, 0.000f * size, -0.188f * size}, {0}, {0}},
                {{-0.304f * size, -0.934f * size, -0.188f * size}, {0}, {0}},
                {{0.795f * size, -0.577f * size, -0.188f * size}, {0}, {0}},
                {{0.491f * size, 0.357f * size, -0.795f * size}, {0}, {0}},
                {{-0.188f * size, 0.577f * size, -0.795f * size}, {0}, {0}},
                {{-0.607f * size, 0.000f * size, -0.795f * size}, {0}, {0}},
                {{-0.188f * size, -0.577f * size, -0.795f * size}, {0}, {0}},
                {{0.491f * size, -0.357f * size, -0.795f * size}, {0}, {0}}
        };

        indices = new int[]{
                0, 1, 2, 3, 4,
                0, 1, 6, 10, 5,
                1, 2, 7, 11, 6,
                2, 3, 8, 12, 7,
                3, 4, 9, 13, 8,
                4, 0, 5, 14, 9,
                15, 16, 17, 18, 19,
                18, 19, 14, 9, 13,
                17, 18, 13, 8, 12,
                16, 17, 12, 7, 11,
                15, 16, 11, 6, 10,
                19, 15, 10, 5, 14
        };

    }
}
