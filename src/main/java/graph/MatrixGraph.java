package graph;

import drawing.DrawApi;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixGraph extends Graph {
    private int size;
    private List<List<Boolean>> matrix;

    public MatrixGraph(DrawApi drawApi) {
        super(drawApi);
    }

    @Override
    protected void doDrawGraph() {
        for (int v = 0; v < matrix.size(); v++) {
            for (int u = 0; u < matrix.get(v).size(); u++) {
                if (matrix.get(v).get(u)) {
                    drawEdge(new Edge(new Vertex(v), new Vertex(u)));
                }
            }
        }
    }

    @Override
    protected void readGraph(BufferedReader reader) {
        matrix = reader.lines()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .map(line -> Arrays.stream(line.split("\\s"))
                        .map(x -> {
                            if (!x.equals("0") && !x.equals("1"))
                                throw new IllegalArgumentException("Incorrect input");
                            return x.equals("1");
                        })
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        size = matrix.size();
        for (List<Boolean> row : matrix) {
            if (row.size() != size) {
                throw new RuntimeException("Incorrect input");
            }
        }
    }

    @Override
    protected int size() {
        return size;
    }

}
