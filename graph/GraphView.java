package DataStructureAndAlgorithm.graph;

/**
 * Created by 10183960 on 2017/1/26.
 */
public class GraphView extends javax.swing.JPanel {
    private Graph<? extends Displayable> graph;

    public GraphView(Graph<? extends Displayable> graph) {
        this.graph = graph;
    }

    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        java.util.List<? extends Displayable> vertices = graph.getVertices();
        for (int i = 0; i < graph.getSize(); i++) {
            int x = vertices.get(i).getX();
            int y = vertices.get(i).getY();
            String name = vertices.get(i).getName();

            g.fillOval(x - 8, y - 8, 16, 16);
            g.drawString(name, x - 12, y - 12);
        }
        for (int i = 0; i < graph.getSize(); i++) {
            java.util.List<Integer> neighbors = graph.getNeighbors(i);
            for (int j = 0; j < neighbors.size(); j++) {
                int v = neighbors.get(j);
                int x1 = graph.getVertex(i).getX();
                int y1 = graph.getVertex(i).getY();
                int x2 = graph.getVertex(v).getX();
                int y2 = graph.getVertex(v).getY();

                g.drawLine(x1, y1, x2, y2);
            }
        }
    }
}
