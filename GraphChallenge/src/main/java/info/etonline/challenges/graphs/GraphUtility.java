package info.etonline.challenges.graphs;

import info.etonline.challenges.graphs.node.GNode;
import info.etonline.challenges.graphs.node.GraphNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by erich on 10/18/2016.
 */
public class GraphUtility {

    public ArrayList walkGraph(GNode node) {
        Set<GNode> mySet = new HashSet<GNode>();
        mySet.add(node);

        for (GNode child : node.getChildren()) {
            mySet.addAll(walkGraph(child));
        }
        return new ArrayList<GNode>(mySet);
    }

    private void addCurrentNodeToPaths(GNode node, ArrayList path, ArrayList allPaths) {
        path.add(0, node);
        allPaths.add(path);
    }

    public ArrayList path(GNode node) {
        ArrayList<ArrayList> allPaths = new ArrayList<ArrayList>();
        if (node.getChildren().length==0) {
            addCurrentNodeToPaths(node, new ArrayList<GNode>(), allPaths);
        } else {
            for (GNode child : node.getChildren()) {
                Iterator<ArrayList> pathIterator = path(child).iterator();
                while (pathIterator.hasNext()) {
                    addCurrentNodeToPaths(node, pathIterator.next(), allPaths);
                }
            }
        }
        return allPaths;
    }

    public static void main(String args[]) {
        GraphNode node = new GraphNode("A", new GraphNode[] {
                new GraphNode("B", new GraphNode[] {new GraphNode("E"), new GraphNode("F")}),
                new GraphNode("C", new GraphNode[] {new GraphNode("G"), new GraphNode("H"), new GraphNode("I")}),
                new GraphNode("D", new GraphNode[] {new GraphNode("J")})
        });

        GraphUtility graphUtility = new GraphUtility();

        System.out.print("WalkGraph: ");
        ArrayList list = graphUtility.walkGraph(node);
        System.out.println(list);

        System.out.print("Path: ");
        list = graphUtility.path(node);
        System.out.println(list);
    }
}

