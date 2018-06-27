import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ahmadi on 12/24/17.
 */
public class HCrandom extends Hill_Climbing{
    public HCrandom(Problem problem){
        super(problem);
        go();
    }
    protected void go(){
        ArrayList<String> newpoints;
        ArrayList<String> nozool = new ArrayList<String>();
        //System.out.println("bia");
        //for (int i = 0; i < points.size(); i++)
        //    System.out.println(points.get(i));
        int min;
        int index = -1;
        min = problem.calculateValue(way.get(way.size() - 1));
        if(min == 0){
            System.out.println("javab peyda shod: " + way.get(way.size() - 1));
            System.out.println("injooori besh residam:");
            for(int i = 0; i < way.size(); i++)
                System.out.println(way.get(i));
            System.out.println("ijad shode ha: "+visited);
            System.out.println("expand shode ha: "+way.size());
            System.out.println("arzeshe javab: "+ problem.calculateValue(way.get(way.size() - 1)));
            return;
        }
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
                    nozool.add(points.get(i));
                    index = i;
                }
            }
            if(index == -1) {
                System.out.printf("too minimum mahali gir kardam: ");
                System.out.println(way.get(way.size() - 1));
                System.out.println("injooori besh residaam:");
                for(int i = 0; i < way.size(); i++)
                    System.out.println(way.get(i));
                System.out.println("ijad shode ha: "+visited);
                System.out.println("expand shode ha: "+way.size());
                System.out.println("arzeshe javab: "+ problem.calculateValue(way.get(way.size() - 1)));
                return;
            }
            else{
                Random random = new Random();
                int rand = Math.abs(random.nextInt()%nozool.size());
                way.add(nozool.get(rand));

            }
        }
        else
            index = 0;

        // we should extend index
        newpoints = problem.extend(points.get(index));
        points = newpoints;
        visited = visited + points.size();
        go();
    }
}
