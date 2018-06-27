import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ahmadi on 12/15/17.
 */
public class GeneticAlgorithm {
    ArrayList<String> chromosomes;
    int crossoverRate, mutationRate, parametersLengthInChromosome, tarafeTasavi;
    Random rand = new Random();
    Problem problem;
    int numberOfGenerations;
    public GeneticAlgorithm(Problem problem, int crossoverRate, int mutationRate, int parametersLengthInChromosome, int tarafeTasavi ){
        this.problem = problem;
        this.chromosomes = problem.getFirstGeneration();
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.parametersLengthInChromosome = parametersLengthInChromosome;
        this.tarafeTasavi = tarafeTasavi;
        numberOfGenerations = 1;
        go();
    }
    private void go(){
        ArrayList<Double> fitness = new ArrayList<Double>();
        ArrayList<Double> C = new ArrayList<Double>();
        ArrayList<String> newChromosomes = new ArrayList<String>();
        ArrayList<Double> R = new ArrayList<Double>();
        ArrayList<String> parents = new ArrayList<String>();
        ArrayList<String> notParents = new ArrayList<String>();
        ArrayList<Integer> mutateds = new ArrayList<Integer>();
        // entekhabe aazaye population
        double total = 0;
        for (int i = 0; i < chromosomes.size(); i++){
            fitness.add(((double)1/(double)(1+problem.calculateValue(chromosomes.get(i)))));
            total = total + fitness.get(i);
        }
        double jam = 0;
        for (int i = 0; i < chromosomes.size(); i++){
            jam = jam + fitness.get(i)/total;
            C.add(jam);
        }
        for(int i = 0; i < chromosomes.size(); i++){
            double x = rand.nextDouble();
            for (int j = 0; j < chromosomes.size(); j++){
                if(C.get(j) > x){
                    newChromosomes.add(chromosomes.get(j));
                    break;
                }
            }
        }
        chromosomes = newChromosomes;


        //entekhabe valedein
        for (int i = 0; i < chromosomes.size(); i++){
            R.add(rand.nextDouble());
            if(R.get(i) < crossoverRate)
                parents.add(chromosomes.get(i));
            else
                notParents.add(chromosomes.get(i));
        }
        //crossover
        if(parents.size() == 1)
            notParents.add(crossover(parents.get(0),parents.get(0)));
        if(parents.size() == 2){
            notParents.add(crossover(parents.get(0),parents.get(1)));
            notParents.add(crossover(parents.get(1),parents.get(0)));
        }
        if(parents.size() == 3){
            notParents.add(crossover(parents.get(0),parents.get(1)));
            notParents.add(crossover(parents.get(1),parents.get(2)));
            notParents.add(crossover(parents.get(2),parents.get(0)));
        }
        if(parents.size() == 4){
            notParents.add(crossover(parents.get(0),parents.get(1)));
            notParents.add(crossover(parents.get(1),parents.get(2)));
            notParents.add(crossover(parents.get(2),parents.get(3)));
            notParents.add(crossover(parents.get(3),parents.get(0)));
        }
        if(parents.size() == 5){
            notParents.add(crossover(parents.get(0),parents.get(1)));
            notParents.add(crossover(parents.get(1),parents.get(2)));
            notParents.add(crossover(parents.get(2),parents.get(3)));
            notParents.add(crossover(parents.get(3),parents.get(4)));
            notParents.add(crossover(parents.get(4),parents.get(0)));
        }
        if(parents.size() == 6){
            notParents.add(crossover(parents.get(0),parents.get(1)));
            notParents.add(crossover(parents.get(1),parents.get(2)));
            notParents.add(crossover(parents.get(2),parents.get(3)));
            notParents.add(crossover(parents.get(3),parents.get(4)));
            notParents.add(crossover(parents.get(4),parents.get(5)));
            notParents.add(crossover(parents.get(5),parents.get(0)));
        }
        chromosomes = notParents;
        //mutation
        int numberOfGens = chromosomes.get(0).length() / parametersLengthInChromosome;
        int mutations = mutationRate * numberOfGens * chromosomes.size() / 100;
        for (int i = 0; i < mutations; i++)
            mutateds.add(rand.nextInt()%(numberOfGens * chromosomes.size()) + 1);
        int num = 0;
        for (int i = 0; i < chromosomes.size(); i++){
            for (int j = 0; j < numberOfGens; j++){
                for (int k = 0; k < mutateds.size(); k++)
                if (mutateds.get(k) == num){
                    String str = chromosomes.get(i);
                    char[] ch = str.toCharArray();
                    int r = rand.nextInt() % (tarafeTasavi + 1);
                    if(r > 9){
                        ch[2 * j] = (Math.abs(r) + "").toCharArray()[0];
                        ch[2 * j + 1] = (Math.abs(r) + "").toCharArray()[1];
                    }
                    else{
                        ch[2 * j] = '0';
                        ch[2 * j + 1] = (Math.abs(r) + "").toCharArray()[0];
                    }
                    chromosomes.set(i,String.valueOf(ch));
                }
                num++;
            }
        }
        //value testing
        //
        int min = problem.calculateValue(chromosomes.get(0));
        int max = problem.calculateValue(chromosomes.get(0));
        int average = 0;
        System.out.println("    next generation");
        for (int i = 0; i < chromosomes.size(); i++){
            if (problem.calculateValue(chromosomes.get(i)) > max)
                max = problem.calculateValue(chromosomes.get(i));
            else if (problem.calculateValue(chromosomes.get(i)) < min)
                min = problem.calculateValue(chromosomes.get(i));
            average = average + problem.calculateValue(chromosomes.get(i));
        }
        System.out.println("max value in generation: " + max);
        System.out.println("min value in generation: " + min);
        System.out.println("average value in generation: " +(double) average / (double) chromosomes.size());

        for (int i = 0; i < chromosomes.size(); i++){
            if(problem.calculateValue(chromosomes.get(i)) == 0){
                System.out.println("__**__**__**__");
                System.out.println("Goal found!\n" + chromosomes.get(i));
                System.out.println("number of generations so far: " + numberOfGenerations);
                return;
            }
        }
        numberOfGenerations ++;
        go();
    }

    private String crossover(String s1, String s2){
        int numberOfGens = s1.length() / parametersLengthInChromosome;
        int x = rand.nextInt() % (numberOfGens - 2) + 1;
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        char[] resultch = "".toCharArray();
        for(int i = 0; i < numberOfGens; i++){
            if (i < x)
                resultch = (String.valueOf(resultch) + "" + String.valueOf(ch1[2*i]) + String.valueOf(ch1[2*i+1])).toCharArray();
            else
                resultch = (String.valueOf(resultch) + "" + String.valueOf(ch2[2*i]) + String.valueOf(ch2[2*i+1])).toCharArray();
        }

        return String.valueOf(resultch);
    }
}
