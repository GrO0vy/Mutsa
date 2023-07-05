package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BlackJack {
    public int solution() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // 카드 갯수, 목표 숫자
        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        int cardCount = Integer.parseInt(infoToken.nextToken());
        int target = Integer.parseInt(infoToken.nextToken());

        // 카드 정보
        StringTokenizer cardToken = new StringTokenizer(reader.readLine());
        int[] cards = new int[cardCount];

        for(int i = 0; i < cardCount; i++){
            cards[i] = Integer.parseInt(cardToken.nextToken());
        }

        int max = 0;
        // 3장의 카드를 뽑는다
        for (int i = 0; i < cardCount - 2; i++) {
            for (int j = i + 1; j < cardCount - 1; j++) {
                for (int k = j + 1; k < cardCount; k++) {
                    int sum = cards[i] + cards[j] + cards[k];
                    if(sum <= target && sum > max) max = sum;
                }
            }
        }

        return max;
    }



    public static void main(String[] args) throws IOException {
        new BlackJack().solution();
    }
}
