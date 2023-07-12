package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1992 {
    // 0 과 1 로 구성된 이미지
    private char[][] image;
    // 결과를 저장하기 위한 StringBuilder
    private StringBuilder quadTreeBuilder;

    private void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        image = new char[n][];

        for(int i = 0; i < n; i++){
            image[i] = reader.readLine().toCharArray();
        }

        quadTreeBuilder = new StringBuilder();
        compressQuad(n, 0, 0);

        System.out.println(quadTreeBuilder.toString());
    }

    private void compressQuad(
            // n: 정사각형의 한변의 길이
            int n,
            // x: 정사각형의 시작 x index
            int x,
            // y: 정사각형의 시작 y index
            int y
    ){
        boolean success = true;

        // success 를 모든 요소가 같으면 true, 같지 않으면 false
        char init = image[x][y];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(image[x + i][y + j] != init){
                    success = false;
                    break;
                }
            }
            if(!success) break;
        }

        if(!success){
            quadTreeBuilder.append('(');

            int half = n / 2;

            for(int i = 0; i < 2; i++){
                for(int j = 0; j < 2; j++){
                    compressQuad(half, x + half * i, y + half * j);
                }
            }
            quadTreeBuilder.append(')');
        }

        else{
            quadTreeBuilder.append(init);
        }
    }

    public static void main(String[] args) throws IOException {
        new Boj1992().solution();
    }
}
