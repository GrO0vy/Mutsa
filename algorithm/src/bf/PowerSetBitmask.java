package bf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerSetBitmask {
    public static void main(String[] args) {
        int[] set = {2, 3, 5};
        new PowerSetBitmask().powerSet(set);
    }

    public void powerSet(int[] set){
        int n = set.length;
        int subSetCount = 1 << n;

        for(int i = 0; i < subSetCount; i++){
            List<Integer> subset = new ArrayList<>();

            for(int j = 0; j < n; j++){
                if((i & (1 << j)) != 0){
                    subset.add(set[j]);
                }
            }
            System.out.println(subset);;
        }
    }
}
