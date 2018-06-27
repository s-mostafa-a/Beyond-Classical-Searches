import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ahmadi on 12/24/17.
 */
public class SAlog extends SimulatedAnnealing{
    public SAlog(Problem problem, double temprature){
        super(problem, temprature);
        go();
    }
    protected void go(){
        ArrayList<String> newpoints;
        if(points.size() == 0){
            System.err.println("Array chera khali shod :|");
            return;
        }
        Random rand = new Random();
        boolean flag = false;
        if(points.size() != 1)
            for (int i = 0; i < points.size(); i++){
                if (problem.calculateValue(way.get(way.size() - 1)) > problem.calculateValue(points.get(i))){
                    boolean darad = false;
                    for(int j = 0; j < way.size(); j++){
                        if (points.get(i).compareTo(way.get(j))==0){
                            darad = true;
                            break;
                        }
                    }
                    if(!darad){
                        way.add(points.get(i));
                        flag = !(false);
                        break;
                    }
                }
                double x = Math.abs(rand.nextDouble());
                double probability;
                if(temprature != 0)
                    probability = Math.exp((double) (- problem.calculateValue(points.get(i)) + problem.calculateValue(way.get(way.size() - 1)))/temprature);
                else
                    probability = 0;
                if(x < probability){
                    boolean darad = false;
                    for(int j = 0; j < way.size(); j++){
                        if (points.get(i).compareTo(way.get(j))==0){
                            darad = (true);
                            break;
                        }
                    }
                    if(!darad){
                        way.add(points.get(i));
                        flag = true;
                        break;
                    }
                }
            }
        else
            flag = true;

        // arzesh gozari
        //way.add(that point);
        if(flag){
            newpoints = problem.extend(way.get(way.size() - 1));
            points = newpoints;
        }
        else{
            System.out.println("gir kardam to minimum maahali!");
            System.out.println(way.get(way.size() - 1));
            System.out.println("injoori: ");
            for (int i = 0; i < way.size(); i++)
                System.out.println(way.get(i));
            System.out.println("ijad shode ha: "+visited);
            System.out.println("expand shode ha: "+way.size());
            System.out.println("arzeshe javab: "+ problem.calculateValue(way.get(way.size() - 1)));
            return;
        }
        if(Math.log(temprature) > 0)
        temprature = Math.log(temprature);//Math.log(adad);
        else
        temprature = 0;
        visited = visited + points.size();
        go();
    }
}
