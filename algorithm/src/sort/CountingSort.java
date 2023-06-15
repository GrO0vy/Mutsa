package sort;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {0, 1, 4, 4, 3, 0, 5, 2, 5, 1};
        int n = arr.length;

        int max = 5;
        int min = 0;
        int k = max - min + 1;

        // 자료가 몇번 등장했는지 기록
        int[] counts = new int[k];

        // counts 배열 채우기
        for(int data : arr){
            counts[data]++;
        }
        System.out.println(Arrays.toString(counts));

        // counts 누적 합
        for(int i = 0; i < k - 1; i++){
            counts[i+1] += counts[i];
        }
        System.out.println(Arrays.toString(counts));

        // 결과 저장용 배열
        int[] output = new int[n];

        for(int i = n - 1; i >=0; i--){
            counts[arr[i]]--;
            int position = counts[arr[i]];

            output[position] = arr[i];
        }
        System.out.println(Arrays.toString(output));
    }
}
