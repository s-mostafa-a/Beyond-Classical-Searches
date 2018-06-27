import java.util.ArrayList;

/**
 * Created by ahmadi on 12/16/17.
 */
public class Hill_Climbing {
    Problem problem;
    ArrayList<String> points;
    ArrayList<String> way;
    int visited = 1;
    public Hill_Climbing(Problem problem){
        this.problem = problem;
        points = problem.getFirstGeneration();
        way = new ArrayList<String>();
        if(points.size() != 1){
            System.err.println("Ozve Avvalie bayad ye doone bashe!");
            return;
        }
        way.add(points.get(0));
    }
    protected void go(){
    }
}
