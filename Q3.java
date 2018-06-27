import java.util.ArrayList;

/**
 * Created by ahmadi on 12/15/17.
 */
public class Q3 extends Problem{
    ArrayList<String> firstGeneration;
    public Q3 (ArrayList<String> firstGeneration){/**
        points = new ArrayList<Point>();
        names = new ArrayList<String>();
        first = new Point(starting, calculateValue(starting));
        points.add(first);
        names.add(starting);*/

        this.firstGeneration = firstGeneration;
    }

    public ArrayList<String> getFirstGeneration() {
        return firstGeneration;
    }

    public int calculateValue(String name){
        char[] ch = name.toCharArray();
        int A = Integer.valueOf(ch[0] + "" + ch[1]);
        int B = Integer.valueOf(ch[2] + "" + ch[3]);
        int C = Integer.valueOf(ch[4] + "" + ch[5]);
        int D = Integer.valueOf(ch[6] + "" + ch[7]);
        return Math.abs(A + 2 * B + 3 * C + 4 * D - 40);
    }
    public String getRandomPoint(){
        return null;
    }
}
