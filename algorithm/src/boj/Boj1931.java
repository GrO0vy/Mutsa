package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj1931 {
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int meetingCount = Integer.parseInt(reader.readLine());
        int[][] meetings = new int[meetingCount][2];

        for(int i = 0; i < meetingCount; i++){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            meetings[i][0] = Integer.parseInt(tokenizer.nextToken());
            meetings[i][1] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(
                meetings,
//                Comparator.comparingInt(o -> o[1])
                (o1, o2) -> {
                    // 종료 시간이 다르면 종료 시간을 비교
                    if(o1[1] != o2[1]) return o1[1] - o2[1];
                    // 종료 시간이 같으면 시작 시간을 비교
                    return o1[0] - o2[0];
                }
        );

        // 회의의 개수
        int answer = 0;
        // 회의의 마지막 종료시간
        int lastEnd = 0;

        for(int i = 0; i < meetingCount; i++){
            if(meetings[i][0] >= lastEnd){
                answer++;
                lastEnd = meetings[i][1];
            }
        }


        return 0;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Boj1931().solution());
    }
}
