package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1802 {
    public void solution() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tests = Integer.parseInt(reader.readLine());

        for(int i = 0; i < tests; i++){
            if(foldable(reader.readLine()))
                System.out.println("YES");
            else System.out.println("NO");
        }
    }

    private boolean foldable(String paper){
        // 굴곡이 하나라면 확인할 필요가 없다.
        if(paper.length() > 1){
            int half = paper.length() / 2;
            //
            if(!foldable(paper.substring(0, half))) return false;
            if(!foldable(paper.substring(half + 1))) return false;

            // 좌/우 가 역대칭이 되어야한다.
            for(int i = 1; i < half + 1; i++){
                // 중간 지점에서 +i 떨어진 굴곡과 -i 떨어진 굴곡을 비교
                // 굴골의 모양이 일치하는 경우 조건이 만족되지 않는다.
                if(paper.charAt(half + i) == paper.charAt(half - i)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new Boj1802().solution();
    }
}
