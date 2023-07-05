package bf;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Combination {
    public static void main(String[] args) {
        int n = 5;

        // 5개 중 3개를 뽑음
        for(int i = 0; i < n - 2; i++){
            for(int j = i + 1; j < n - 1; j++){
                for (int k = j + 1; k < n; k++) {
                    System.out.printf("%d %d %d\n",i, j, k);
                }
            }
        }

        System.out.println("------------------------------------");

        int[] numbers = new int[]{5, 6, 7, 8, 9};

        for(int i = 0; i < n - 2; i++){
            for(int j = i + 1; j < n - 1; j++){
                for (int k = j + 1; k < n; k++) {
                    System.out.printf("%d %d %d\n",numbers[i], numbers[j], numbers[k]);
                }
            }
        }
        System.out.println("--------------------------------");

        new Combination().combNumber(5,3,0,0,new int[3]);
    }

    private void combNumber(
        // nCr
        int n, int r,
        // 지금까지 고른 원소의 개수 ( 몇 번 째 원소를 고르는 중인지 )
        int k,
        // 현재 조합에 포함시킬지 고려 중인 숫자
        int next,
        // 작성 중인 조합
        int[] comb
    ){
        // 숫자를 r 개 만큼 다 골랐다.
        if(k == r){
            System.out.println(Arrays.toString(comb));
        }
        else if(next == n){
            // 조합 생성 실패 ( 더 이상 고를 숫자가 없다 )
            return;
        }
        else{
            // 조합의 k 번 째에 next( 현재숫자 )를 넣는다.
            comb[k] = next;
            // next를 선택했다면, k + 1 번째 슷자를 선택하러간다.
            // next + 1을 할당할지 말지를 결정하러 재귀호출
            combNumber(n, r, k + 1, next + 1, comb);

            // next를 선택하지 않았다면 k 번째에 next + 1을 할당할지 말지를 결정하러 재귀호출
            // comb[k]의 값이 덮어씌워진다.
            combNumber(n, r, k, next + 1, comb);
        }
    }
}
