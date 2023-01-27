package graph;

import drawing.DrawApi;
import drawing.Point;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static drawing.DrawingUtils.MARGIN;
import static drawing.DrawingUtils.VERTEX_RADIUS;

public abstract class Graph {
    private final DrawApi api;
    private final Map<Vertex, Point> vertexPointMap = new HashMap<>();
    private final Set<Edge> edges = new HashSet<>();
    private EllipsPointsFactory ellipsPointsFactory;

    public Graph(DrawApi api) {
        this.api = api;
    }

    public void drawGraph() {
        Point center = new Point(
                api.getDrawingAreaWidth() / 2.,
                api.getDrawingAreaHeight() / 2.
        );

        ellipsPointsFactory = new EllipsPointsFactory(
                size(),
                center,
                center.getX() - MARGIN,
                center.getY() - MARGIN);

        doDrawGraph();
        api.visualize();
    }

    protected Point drawVertex(Vertex vertex) {
        if (vertexPointMap.containsKey(vertex)) {
            return vertexPointMap.get(vertex);
        }

        Point point = ellipsPointsFactory.nextPoint();
        api.drawCircle(point, VERTEX_RADIUS);
        vertexPointMap.put(vertex, point);
        return point;
    }

    protected void drawEdge(Edge edge) {
        if (edges.contains(edge)) {
            return;
        }

        Point a = drawVertex(edge.from());
        Point b = drawVertex(edge.to());
        api.drawLine(a, b);
        edges.add(edge);
    }

    public void readFromFile(String fileName) {
        Path file;
        try {
            file = Paths.get(Objects.requireNonNull(getClass().getResource(fileName)).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        try (BufferedReader reader =
                new BufferedReader(
                new InputStreamReader(
                new FileInputStream(file.toFile())))) {
            readGraph(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    protected abstract void doDrawGraph();

    protected abstract void readGraph(BufferedReader reader);

    protected abstract int size();
}
