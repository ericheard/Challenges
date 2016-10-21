package info.etonline.challenges.graphs.node;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by erich on 10/18/2016.
 */
public class GraphNodeTest {

    @Test
    public void testValidNameOnlyContruction() throws Exception {
        GraphNode node = new GraphNode("Node A");
        assertEquals("Node A", node.getName());
        assertNotNull(node.getChildren());
        assertEquals(0,(node.getChildren().length));
    }

    @Test(expected = IllegalArgumentException.class)
    public void textNullNameOnlyConstruction() throws Exception {
        GraphNode node = new GraphNode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void textEmptyNameOnlyConstruction() throws Exception {
        GraphNode node = new GraphNode("");
    }

    @Test
    public void testContructionWithNullNode() throws Exception {
        GraphNode node = new GraphNode("Node A", null);
        assertEquals("Node A", node.getName());
        assertNotNull(node.getChildren());
        assertEquals(0,(node.getChildren().length));
    }

    @Test
    public void testConstructorParams() throws Exception {
        GraphNode children[] = {new GraphNode("Child A"),
                new GraphNode("Child B")};
        GraphNode node = new GraphNode("Parent Node", children);
        assertNotNull(node);
        assertEquals("Parent Node", node.getName());

        GNode[] nodes = node.getChildren();
        assertNotNull(nodes);
        assertEquals(2,nodes.length);
        assertEquals("Child A", nodes[0].getName());
        assertEquals(0, nodes[0].getChildren().length);
        assertEquals("Child B", nodes[1].getName());
        assertEquals(0, nodes[1].getChildren().length);
    }
}