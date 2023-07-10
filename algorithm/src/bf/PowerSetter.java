package bf;

public class PowerSetter {
    public static void main(String[] args) {
        int[] set = new int[]{2, 3, 5};
        // 선택 여부 저장
        int[] select = new int[3];


        for(int i = 0; i < 2; i++){
            select[0] = i;
            for(int j = 0; j < 2; j++){
                select[1] = j;
                for (int k = 0; k < 2; k++) {
                    select[2] = k;
                    for(int n = 0; n < 3; n++){
                        if(select[n] == 1)
                            System.out.print(set[n]+" ");
                    }
                    System.out.println();
                }
            }
        }
    }
}
