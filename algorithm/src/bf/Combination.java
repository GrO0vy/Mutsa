package bf;

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
    }
}
