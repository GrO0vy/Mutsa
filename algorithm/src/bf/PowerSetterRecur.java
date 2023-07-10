package bf;

import java.util.Arrays;

public class PowerSetterRecur {
    public static void main(String[] args) {
        int[] set = new int[]{2, 3, 5};

        new PowerSetterRecur().powerSetter(set, 0, new int[set.length]);
    }

    private void powerSetter(
            int[] set,
            int next,
            int[] select
    ){
        if(next == set.length){
            for(int i = 0; i < set.length; i++){
                if(select[i] == 1)
                    System.out.print(set[i] + " ");
            }
            System.out.println(Arrays.toString(select));
            return;
        }

        // 내 거 고르지 않고 다음 거
        select[next] = 0;
        powerSetter(set, next + 1, select);
        // 내거 고르고 다음 거
        select[next] = 1;
        powerSetter(set, next + 1, select);
    }
}
