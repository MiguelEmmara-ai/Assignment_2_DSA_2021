package Question1;

import java.util.Set;

/**
 * @author Miguel Emmara - 18022146
 */
public class Main {
    public static void main(String[] args) {
        LinkedHashMapWithChaining<Integer , String> Lhmc = new LinkedHashMapWithChaining<>();

        Lhmc.put(1, "Captain America");
        Lhmc.put(2, "Iron Man");
        Lhmc.put(3, "Thor");
        Lhmc.put(4, "Hulk");
        Lhmc.put(5, "Black Panther");

        //System.out.println(Lhmc.toString());

        Set<Integer> keys = Lhmc.keySet();

        for (Integer key : keys) {
            System.out.println(key + " --> " + Lhmc.get(key));
        }
    }
}
