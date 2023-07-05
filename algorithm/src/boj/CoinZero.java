package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CoinZero {
    public int solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer info = new StringTokenizer(reader.readLine());

        int coinKinds = Integer.parseInt(info.nextToken());
        int targetAmounts = Integer.parseInt(info.nextToken());

        int[] coinAmounts = new int[coinKinds];

        for(int i = 0; i < coinKinds; i++){
            coinAmounts[coinKinds - i - 1] = Integer.parseInt(reader.readLine());
        }

        int coinUsed = 0;

        // 1. 사전적 풀이
        int idx = 0;
        while(targetAmounts > 0){
            if(coinAmounts[idx] > targetAmounts) idx++;
            else {
                targetAmounts -= coinAmounts[idx];
                coinUsed++;
            }
        }

        // 2. 수학적 계산
        for(int i = 0; i < coinKinds; i++){
            coinUsed += targetAmounts / coinAmounts[i];
            targetAmounts %= coinAmounts[i];
        }

        return coinUsed;
    }

    public static void main(String[] args) {

    }
}
