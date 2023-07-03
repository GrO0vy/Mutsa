package bf;

public class Permutation {
    public static void main(String[] args) {
        // 0 ~ 2 사이의 3개의 숫자가 있다.
        // 겹치지 않도록 3개를 골라 나열한 모든 경우의 수를 출력해보자
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == i) continue;
                for (int k = 0; k < 3; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    System.out.printf("%d %d %d\n", i, j, k);
                }
            }
        }

        System.out.println("-------------------------------");

        // 0 ~ 4 사이의 숫자 중 3개를 골라 나열해보자
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == i) continue;
                for (int k = 0; k < 5; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    System.out.printf("%d %d %d\n", i, j, k);
                }
            }
        }

        System.out.println("-------------------------------");

        // 5개의 숫자를 담은 int[] 에서 3개를 골라 나열해보자
        int[] numbers = new int[]{10, 12, 14, 16, 18};
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if(j == i) continue;
                for (int k = 0; k < numbers.length; k++) {
                    if(k == j || k == i)
                    System.out.printf("%d %d %d\n", numbers[i], numbers[j], numbers[k]);
                }
            }
        }
    }
}
