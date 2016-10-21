package info.etonline.challenges.graphs.node;

/**
 * Created by eric heard on 10/18/2016.
 */
public class GraphNode implements GNode {

    String _name;
    GNode[] _nodes;

    public GraphNode(String name, GNode[] nodes) {
        this(name);
        _nodes = nodes;;
    }

    public GraphNode(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Name can not be null");
        }
        _name = name;
    }

    public String getName() {
        return _name;
    }

    public GNode[] getChildren() {
        return (_nodes == null) ? new GNode[0] : _nodes;
    }

    @Override
    public String toString() {
        return getName();
    }
}
