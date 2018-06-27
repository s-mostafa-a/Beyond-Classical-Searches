import java.util.ArrayList;

/**
 * Created by ahmadi on 12/24/17.
 */
public class HCsimple extends Hill_Climbing{
    public HCsimple(Problem problem){
        super(problem);
        go();
    }
    protected void go(){

        ArrayList<String> newpoints;
        //System.out.println("bia");
        //for (int i = 0; i < points.size(); i++)
        //    System.out.println(points.get(i));
        int min;
        int index = -1;
        min = problem.calculateValue(way.get(way.size() - 1));
        boolean brek = false;
        if(points.size() != 1){
        for(int i = 0; i < points.size(); i++) {
            for (int j = 0; j < way.size(); j++)
                if (way.get(j).compareTo(points.get(i))==0){
                    brek = (true);
                    break;
                }
            if(brek)
                brek = false;
            else if (min > problem.calculateValue(points.get(i))) {
                min = problem.calculateValue(points.get(i));
                index = i;
            }
        }
        if(index == -1) {
            System.out.printf("too minimum mahali gir kardam: ");
            System.out.println(way.get(way.size() - 1));
            System.out.println("injooori besh residam:");
            for(int i = 0; i < way.size(); i++)
                System.out.println(way.get(i));
            System.out.println("ijad shode ha: "+visited);
            System.out.println("expand shode ha: "+way.size());
            System.out.println("arzeshe javab: "+ problem.calculateValue(way.get(way.size() - 1)));
            return;
        }
        else
            way.add(points.get(index));
        }
        else
            index = 0;
        if(min == 0){
            System.out.println("javab peyda shod: " + points.get(index));
            System.out.println("injooori besh residam:");
            for(int i = 0; i < way.size(); i++)
                System.out.println(way.get(i));
            System.out.println("ijad shode ha: "+visited);
            System.out.println("expand shode ha: "+way.size());
            System.out.println("arzeshe javab: "+ problem.calculateValue(way.get(way.size() - 1)));
            return;
        }
        // we should extend index
        newpoints = problem.extend(points.get(index));
        points = newpoints;
        visited = visited + points.size();
        go();
    }
}
