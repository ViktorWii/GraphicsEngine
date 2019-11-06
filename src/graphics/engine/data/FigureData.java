package graphics.engine.data;

public class FigureData {
    public int vcount;  // количество вершин
    public int icount; // количество граней
    public int size;
    public int step = 3;

    public double[][][] verticesInPolygons;
    public int[] indices;
}
