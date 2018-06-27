import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ahmadi on 12/15/17.
 */
public class Q1 extends Problem{
    String  first;
    public Q1 (){
        first = "11111111";
    }
    public ArrayList<String> getFirstGeneration() {
        ArrayList<String> firsts = new ArrayList<String>();
        firsts.add(first);
        return firsts;
    }

    /**
     * jadid
     */
    public ArrayList<String> extend (String point){
        ArrayList<String> names = new ArrayList<String>();
        String name = point;
        for (int i = 0; i < 8; i++) {
            char[] ch = name.toCharArray();
            int iom = Character.getNumericValue(ch[i]);
            for (int j = 1; j <= 8; j++){
                if (iom != j) {
                    ch[i] = ((j) + "").charAt(0);
                    if (!names.contains(String.valueOf(ch))) {
                        names.add(String.valueOf(ch));
                    }
                }
            }
        }
        return names;
    }
    /**
     * jadid
     */
    public String getRandomPoint(){
        Random rand = new Random();
        String s = "";
        for (int i = 0; i < 8; i++)
            s = s + ((Math.abs(rand.nextInt()%8)) + 1);
        return s;
    }
    public int calculateValue(String str){
        int sum = 0;
        char[] ch = str.toCharArray();
        if (str.length() != 8)
            System.out.println("Tool chera 8 nist :|");
        for(int i = 7; i >= 0; i--)
            for(int j = 0; j < i; j++){
                if(ch[i] == ch[j])
                    sum++;
                else if(Math.abs(Character.getNumericValue(ch[i]) - Character.getNumericValue(ch[j])) == Math.abs(i - j))
                    sum++;
            }
        return sum;
    }
}
