package graph;

import drawing.DrawApi;

import java.io.BufferedReader;
import java.util.*;
import java.util.stream.Collectors;

public class EdgesListGraph extends Graph {
    private List<Edge> edges;

    public EdgesListGraph(DrawApi drawApi) {
        super(drawApi);
    }

    @Override
    protected void doDrawGraph() {
        edges.forEach(this::drawEdge);
    }

    @Override
    public void readGraph(BufferedReader reader) {
        edges = reader.lines()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .map(line -> Arrays.stream(line.split("\\s"))
                        .map(x -> new Vertex(Integer.parseInt(x)))
                        .collect(Collectors.toList()))
                .map(list -> {
                    if (list.size() != 2) {
                        throw new IllegalArgumentException("Incorrect input");
                    }
                    return new Edge(list.get(0), list.get(1));
                })
                .collect(Collectors.toList());
    }

    @Override
    protected int size() {
        Set<Integer> ids = new HashSet<>();
        for (Edge edge : edges) {
            ids.add(edge.from().id());
            ids.add(edge.to().id());
        }
        return ids.size();
    }

}
