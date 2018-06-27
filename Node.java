import java.util.ArrayList;

/**
 * Created by ahmadi on 12/15/17.
 */
public class Node {
    int number;
    ArrayList<Node> nears;
    public Node(int n){
        number = n;
        if(number > 11 || number < 0)
            System.out.println("Number of node is not correct!");
        nears = new ArrayList<Node>();
    }
    public void addNear(Node node){
        nears.add(node);
    }
    public ArrayList<Node> getNears() {
        return nears;
    }
    public int getNumber() {
        return number;
    }
}
