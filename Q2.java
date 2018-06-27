import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ahmadi on 12/15/17.
 0: [1, 2],
 1: [0, 2, 3],
 2: [0, 1, 4, 6],
 3: [1, 4, 5],
 4: [2, 3, 7],
 5: [3, 7],
 6: [2, 8],
 7: [4, 5, 8, 9],
 8: [6, 7, 10, 11],
 9: [7, 11],
 10: [8, 11],
 11: [8, 9, 10]
 */
public class Q2 extends Problem{
    String  first;
    Node[] nodes;
    public Q2 (){
        nodes = new Node[12];
        makeGraph();
        first = "010100101010";
    }

    /**
     * jadid
     */

    public ArrayList<String> extend (String point){
        ArrayList<String> first;
        ArrayList<String> second;
        first = yekKamKon(point);
        second = yekZiadKon(point);
        if(first == null && second == null){
            System.err.println("joftesh null shod!");
            return null;
        }
        else if(first == null && second != null)
                return second;
        else if (second == null && first != null)
                    return first;
        else
            for(int i = 0; i < second.size(); i++)
                first.add(second.get(i));
        return first;
    }
    private ArrayList<String> yekKamKon(String name){
        ArrayList<String> names = new ArrayList<String>();
        char[] ch = name.toCharArray();
        int numberOfOnes = 0;
        for(int i = 0; i < 12; i++)
            if (ch[i] == '1')
                numberOfOnes++;
        if(numberOfOnes < 4)
            return null;
        for (int i = 0; i < 12; i++)
            if(ch[i] == '1'){
                ch[i] = '0';
                String nextname = String.valueOf(ch);
                if(!names.contains(nextname)){
                    names.add(nextname);
                    }
                ch[i] = '1';
            }
        return names;
    }
    private ArrayList<String> yekZiadKon(String name){
        ArrayList<String> names = new ArrayList<String>();
        char[] ch = name.toCharArray();
        int numberOfOnes = 0;
        for(int i = 0; i < 12; i++)
            if (ch[i] == '1')
                numberOfOnes++;
        if(numberOfOnes > 9)
            return null;
        for (int i = 0; i < 12; i++)
            if(ch[i] == '0'){
                ch[i] = '1';
                String nextname = String.valueOf(ch);
                if(!names.contains(nextname)){
                    names.add(nextname);
                }
                ch[i] = '0';
            }
        return names;
    }

    /**
     * jadid
     */




    public ArrayList<String> getFirstGeneration() {
        ArrayList<String> firsts = new ArrayList<String>();
        firsts.add(first);
        return firsts;
    }
    public int calculateValue(String str){
        int sum = 0;
        char[] ch = str.toCharArray();
        for(int i = 0; i < 12; i++){
            if(ch[i] == '1'){
                ArrayList<Node> nears = nodes[i].getNears();
                for(int j = 0; j < nears.size(); j++){
                    if(ch[nears.get(j).getNumber()] == '0')
                        sum++;
                }
            }
        }
        return sum;
    }
    private void makeGraph(){

        for(int i = 0; i < 12; i++){
            nodes[i] = new Node(i);
        }
        nodes[0].addNear(nodes[1]);
        nodes[0].addNear(nodes[2]);

        nodes[1].addNear(nodes[0]);
        nodes[1].addNear(nodes[2]);
        nodes[1].addNear(nodes[3]);

        nodes[2].addNear(nodes[0]);
        nodes[2].addNear(nodes[1]);
        nodes[2].addNear(nodes[4]);
        nodes[2].addNear(nodes[6]);

        nodes[3].addNear(nodes[1]);
        nodes[3].addNear(nodes[4]);
        nodes[3].addNear(nodes[5]);

        nodes[4].addNear(nodes[2]);
        nodes[4].addNear(nodes[3]);
        nodes[4].addNear(nodes[7]);


        nodes[5].addNear(nodes[3]);
        nodes[5].addNear(nodes[7]);


        nodes[6].addNear(nodes[2]);
        nodes[6].addNear(nodes[8]);

        nodes[7].addNear(nodes[4]);
        nodes[7].addNear(nodes[5]);
        nodes[7].addNear(nodes[8]);
        nodes[7].addNear(nodes[9]);

        nodes[8].addNear(nodes[6]);
        nodes[8].addNear(nodes[7]);
        nodes[8].addNear(nodes[10]);
        nodes[8].addNear(nodes[11]);


        nodes[9].addNear(nodes[7]);
        nodes[9].addNear(nodes[11]);

        nodes[10].addNear(nodes[8]);
        nodes[10].addNear(nodes[11]);

        nodes[11].addNear(nodes[8]);
        nodes[11].addNear(nodes[9]);
        nodes[11].addNear(nodes[10]);
    }
    public String getRandomPoint(){
        Random rand = new Random();
        String s = "";
        for (int i = 0; i < 12; i++)
            s = s + ((rand.nextInt()%2));
        return s;
    }

}
