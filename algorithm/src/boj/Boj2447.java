package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2447 {
    private char[][] starboard;
    public void solution() throws IOException{
        int n = Integer.parseInt(
                new BufferedReader(new InputStreamReader(System.in)).readLine()
        );
        starboard = new char[n][n];

        for(char[] row: starboard){
            Arrays.fill(row,' ');
        }

        setStar(n, 0, 0);
        StringBuilder drawStar = new StringBuilder();
        for(int i = 0; i < n; i++){
            drawStar.append(starboard[i]).append('\n');
        }
        System.out.println(drawStar);
    }

    public void setStar(int n, int x, int y){
        if(n == 3){
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(i == 1 && j == 1) continue;
                    starboard[y + i][x + j] = '*';
                }
            }
        }

        else{
            int offset = n / 3;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(i == 1 && j == 1) continue;
                    setStar(offset, x + offset * i, y + offset * j);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Boj2447().solution();
    }
}
