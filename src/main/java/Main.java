import drawing.DrawApi;
import drawing.AwtApi;
import drawing.JavaFxApi;
import graph.EdgesListGraph;
import graph.Graph;
import graph.MatrixGraph;

public class Main {
    public static void main(String[] args) {
        if (args == null || args.length != 3) {
            System.out.println("Invalid arguments");
            System.out.println("Usage : <api> <graph type> <fileName>");
            System.out.println("Supported api: awt, javafx");
            System.out.println("Supported graph types: matrix, edges");
            return;
        }
        DrawApi drawApi = getDrawingApi(args[0]);
        Graph graph = getGraph(drawApi, args[1]);
        graph.readFromFile(args[2]);
        graph.drawGraph();
    }

    private static DrawApi getDrawingApi(String apiType) {
        if (apiType.equals("awt")) {
            return new AwtApi();
        }
        if (apiType.equals("javafx")) {
            return new JavaFxApi();
        }
        throw new IllegalArgumentException("Unsupported api: " + apiType);
    }

    private static Graph getGraph(DrawApi drawApi, String graphType) {
        if (graphType.equals("matrix")) {
            return new MatrixGraph(drawApi);
        }
        if (graphType.equals("edges")) {
            return new EdgesListGraph(drawApi);
        }
        throw new IllegalArgumentException("Unsupported graph type: " + graphType);
    }

}
