package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BFPatternMatching {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String target = reader.readLine();
        String pattern = reader.readLine();

        int tarIdx = 0;
        int patIdx = 0;

        // TODO tarIdx 가 전체 길이보다 작을 동안에 반복
        // TODO 존재하는지만 검사하면 된다 했을 경우 patIdx 가 pattern.length() 보다 작을 동안에 반복한다.
            // TODO target[tadIdx] 가 patter[patIdx] 랑 다를 경우
        while(tarIdx < pattern.length() && patIdx < pattern.length()){
            if(target.charAt(tarIdx) != pattern.charAt(patIdx)) {
                tarIdx -= patIdx;
                patIdx = -1;
            }
            tarIdx++;
            patIdx++;
        }
        if(patIdx == pattern.length()) System.out.println(tarIdx-patIdx);


                // TODO tadIdx를 여태까지 이동한 만큼 되돌린다.
                // TODO patIdx를 -1로 할당한다.

            // TODO 다음 칸으로 이동한다.

        // TODO patIdx == patter.length() 이면 성공, 어디에서 찾았는지 출력
        // TODO 못 찾으면 System.out.println("404 not found")
    }

    public static void main(String[] args) throws IOException{
        new BFPatternMatching().solution();
    }
}
