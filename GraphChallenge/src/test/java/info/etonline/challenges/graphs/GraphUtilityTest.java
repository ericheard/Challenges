package info.etonline.challenges.graphs;

import info.etonline.challenges.graphs.node.GNode;
import info.etonline.challenges.graphs.node.GraphNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by erich on 10/19/2016.
 */
public class GraphUtilityTest {

    ArrayList<GNode> allCreatedNodes;
    GraphUtility graphUtil = new GraphUtility();
    int numberOfBranches;
    int numberOfNodesWithChildren;

    private GraphNode addNodeToTestList(GraphNode node) {
        if (this.allCreatedNodes == null) {
            this.allCreatedNodes = new ArrayList<GNode>();
        }
        this.numberOfBranches += node.getChildren().length;
        if (node.getChildren().length!=0) {
            this.numberOfNodesWithChildren++;
        }

        this.allCreatedNodes.add(node);
        return node;
    }

    private GraphNode createNodeForTest(String name) {
        return addNodeToTestList(new GraphNode(name));
    }

    private GraphNode createNodeForTest(String name,  GNode[] nodes) {
        return addNodeToTestList(new GraphNode(name, nodes));
    }

    private String createNameFromNumber(int num) {
        String result = "";
        while (num > 0) {
            num--;
            int remainder = num % 26;
            char digit = (char) (remainder + 65);
            result = digit + result;
            num = (num - remainder) / 26;
        }
        return result;
    }

    private GNode buildExampleGraph() {
        GraphNode node = createNodeForTest("A", new GraphNode[] {
                createNodeForTest("B", new GraphNode[] {createNodeForTest("E"), createNodeForTest("F")}),
                createNodeForTest("C", new GraphNode[] {createNodeForTest("G"), createNodeForTest("H"), createNodeForTest("I")}),
                createNodeForTest("D", new GraphNode[] {createNodeForTest("J")})
        });
        return (GNode)node;
    }

    private GNode buildFixedGraph() {
        GraphNode i = createNodeForTest("I");
        GraphNode h = createNodeForTest("H");
        GraphNode g = createNodeForTest("G");
        GraphNode f = createNodeForTest("F");
        GraphNode e = createNodeForTest("E", new GraphNode[] {f, i});
        GraphNode d = createNodeForTest("D", new GraphNode[] {f, g});
        GraphNode c = createNodeForTest("C", new GraphNode[] {h, g});
        GraphNode b = createNodeForTest("B", new GraphNode[] {h, i});
        GraphNode a = createNodeForTest("A", new GraphNode[] {b,c,d,e,f,g,h,i});

        return (GNode)a;
    }

    private GNode buildNewNodeMappedToAllExisting(int num){
        ArrayList<GNode> nodeList = new ArrayList<GNode>();
        GraphNode node = null;
        for (int i = 0; i < num; i++) {
            GNode[] children = getNodesFromArrayList(nodeList);
            node = createNodeForTest(createNameFromNumber(i+1), children);
            nodeList.add(node);
        }
        return node;
    }

    private GNode buildLineGraph(int depth) {
        return buildBalancedGraph(0,depth,0,1);
    }

    private GNode buildBalanceTreeGraph(int depth, int width) {
        return buildBalancedGraph(0, depth, 0, width);
    }

    private GNode[] getNodesFromArrayList(ArrayList<GNode> nodes) {
        return (nodes.isEmpty()) ? null : nodes.toArray(new GNode[nodes.size()]);
    }

    private GNode buildBalancedGraph(int curDepth, int totalDepth, int curWidth, int totalWidth) {
        if (curDepth >= totalDepth) return null;

        ArrayList<GNode> children = new ArrayList<GNode>();
        for (int i=0;i<totalWidth;i++) {
            GNode childNode = buildBalancedGraph(curDepth + 1, totalDepth, i, totalWidth);
            if (childNode != null) {
                children.add(childNode);
            }
        }

        GNode[] childrenNodes = getNodesFromArrayList(children);

        GNode node = createNodeForTest(curDepth + "." + curWidth, childrenNodes);

        return node;
    }

    private void clearBuildCounts() {
        this.allCreatedNodes = new ArrayList<GNode>();
        this.numberOfBranches = 0;
        this.numberOfNodesWithChildren = 0;
    }

    private int getExpectPathCount() {
        int numberOfNodes = this.allCreatedNodes.size();
        int paths = this.numberOfBranches - (this.numberOfNodesWithChildren - 1);
        return (paths < 1) ? 1 : paths;
    }

    @org.junit.Before
    public void setUp() throws Exception {
        clearBuildCounts();
    }

    private void assertWalkGraph(ArrayList list) throws Exception {
        //check that the created list match returned list size
        assertEquals(this.allCreatedNodes.size(), list.size());

        //check that return list has no duplicates
        Set<GNode> checkForDuplicates = new HashSet<GNode>(list);
        assertEquals(checkForDuplicates.size(), list.size());

        //check for missed nodes based on known created nodeds
        ArrayList<GNode> checkForMissedNodes = new ArrayList<GNode>(this.allCreatedNodes);
        checkForMissedNodes.removeAll(list);
        assertEquals(0, checkForMissedNodes.size());

        //check for extra nodes
        ArrayList<GNode> checkForExtraNodes = new ArrayList<GNode>(list);
        checkForExtraNodes.removeAll(this.allCreatedNodes);
        assertEquals(0, checkForExtraNodes.size());
    }

    @Test
    public void testWalkGraphWithExampleGraph() throws Exception {
        GNode node = buildExampleGraph();
        ArrayList list = graphUtil.walkGraph(node);
        assertWalkGraph(list);
    }

    @Test
    public void testWalkGraphWithOneNode() throws Exception {
        GNode node = buildLineGraph(1);
        ArrayList list = graphUtil.walkGraph(node);
        assertWalkGraph(list);
    }

    @Test
    public void testWalkGraphWithLineGraph() throws Exception {
        GNode node = buildLineGraph(10);
        ArrayList list = graphUtil.walkGraph(node);
        assertWalkGraph(list);
    }

    @Test
    public void testWalkGraphWithBalancedGraphBinary() throws Exception {
        GNode node = buildBalanceTreeGraph(3, 2);
        ArrayList list = graphUtil.walkGraph(node);
        assertWalkGraph(list);

        clearBuildCounts();

        node = buildBalanceTreeGraph(5, 2);
        list = graphUtil.walkGraph(node);
        assertWalkGraph(list);
    }

    @Test
    public void testWalkGraphWithBalancedGraph() throws Exception {
        GNode node = buildBalanceTreeGraph(4, 3);
        ArrayList list = graphUtil.walkGraph(node);
        assertWalkGraph(list);

        clearBuildCounts();

        node = buildBalanceTreeGraph(5, 4);
        list = graphUtil.walkGraph(node);
        assertWalkGraph(list);
    }

    @Test
    public void testWalkGraphWithFixedGraph() throws Exception {
        GNode node = buildFixedGraph();
        ArrayList list = graphUtil.walkGraph(node);
        assertWalkGraph(list);
    }

    @Test
    public void testWalkGraphWithEachNewNodeMappedToAllExistingNodesGraph() throws Exception {
        GNode node = buildNewNodeMappedToAllExisting(4);
        ArrayList list = graphUtil.walkGraph(node);
        assertWalkGraph(list);
    }

    private void assertPath(ArrayList list) throws Exception {
        //check that the expect of Paths match list size
        Assert.assertEquals(getExpectPathCount(), list.size());

        //check that return list has no duplicates
        Set<String> checkForDuplicates= new HashSet<String>();
        Iterator<ArrayList> pathIterator = list.iterator();
        while (pathIterator.hasNext()) {
            checkForDuplicates.add(pathIterator.next().toString());
        }
        Assert.assertEquals(checkForDuplicates.size(), list.size());
    }

    @Test
    public void testPathWithExampleGraph() throws Exception {
        GNode node = buildExampleGraph();
        ArrayList list = graphUtil.path(node);
        assertPath(list);
    }

    @Test
    public void testPathWithOneNode() throws Exception {
        GNode node = buildLineGraph(1);
        ArrayList list = graphUtil.path(node);
        assertPath(list);
    }

    @Test
    public void testPathWithLineGraph() throws Exception {
        GNode node = buildLineGraph(10);
        ArrayList list = graphUtil.path(node);
        assertPath(list);
    }

    @Test
    public void testPathWithBalancedGraphBinary() throws Exception {
        GNode node = buildBalanceTreeGraph(3, 2);
        ArrayList list = graphUtil.path(node);
        assertPath(list);

        clearBuildCounts();

        node = buildBalanceTreeGraph(5, 2);
        list = graphUtil.path(node);
        assertPath(list);
    }

    @Test
    public void testPathWithBalancedGraph() throws Exception {
        GNode node = buildBalanceTreeGraph(4, 3);
        ArrayList list = graphUtil.path(node);
        assertPath(list);

        clearBuildCounts();

        node = buildBalanceTreeGraph(5, 4);
        list = graphUtil.path(node);
        assertPath(list);
    }

    @Test
    public void testPathWithFixedGraph() throws Exception {
        GNode node = buildFixedGraph();
        ArrayList list = graphUtil.path(node);
        assertPath(list);
    }

    @Test
    public void testPathWithEachNewNodeMappedToAllExistingNodesGraph() throws Exception {
        GNode node = buildNewNodeMappedToAllExisting(4);
        ArrayList list = graphUtil.path(node);
        assertPath(list);
    }
}