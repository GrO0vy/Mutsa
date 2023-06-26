package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class RecursiveDFS {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer : 입력한 문자열을 지정한 문자열 ( delimiters ) 를 기준으로 나누어서
        // 한 단어씩 반환해주는 도구 ( 기본은 공백 )
        StringTokenizer graphTokenizer = new StringTokenizer(reader.readLine());
        // StringTokenizer.nextToken() : 다음 단어 가져오기
        int maxNodes = Integer.parseInt(graphTokenizer.nextToken());    // 8
        int edges = Integer.parseInt(graphTokenizer.nextToken());       // 10

        // 인접행렬
        // 만약 노드가 1 ~ N + 1 까지라면
        // 계산할 때 매번 -1을 해주거나
        // 인접 행렬을 한칸 더 늘리거나
        int[][] adjMatrix = new int[maxNodes][maxNodes];    // 0 ~ 7 까지 표현 가능한 인접 행렬
        for (int i = 0; i < edges; i++) {
            // 다음 줄을 공백 단위로 나눠줌
            StringTokenizer edgeTokenizer = new StringTokenizer(reader.readLine());
            int startNode = Integer.parseInt(edgeTokenizer.nextToken());
            int endNode = Integer.parseInt(edgeTokenizer.nextToken());

            // 유향 그래프의 경우
            adjMatrix[startNode][endNode] = 1;
            // 무향 그래프의 경우 한 줄 더 추가
            adjMatrix[endNode][startNode] = 1;
        }

//        System.out.println(Arrays.deepToString(adjMatrix));

//        for(int[] row : adjMatrix){
//            System.out.println(Arrays.toString(row));
//        }

        boolean[] visited = new boolean[maxNodes];
        List<Integer> visitedOrder = new ArrayList<>();

        recursive(0, maxNodes, adjMatrix, visited, visitedOrder);

        System.out.println(visitedOrder);
    }

    // 목적: DFS를 했을 때 정점 방문 순서 기록
    public void recursive(
            // 다음 (이번 호출)에서 방문 할 곳
            int next,

            int maxNodes,
            // 인접 정점 정보
            int[][] adjMatrix,
            // 여태까지 방분만 정점 정보
            boolean[] visited,

            // 방문 순서 기록을 위한 리스트
            List<Integer> visitOrder
    ) {
        visited[next] = true;
        visitOrder.add(next);

        for (int i = 0; i < maxNodes; i++) {
            if (adjMatrix[next][i] == 1 && !visited[i]) {
                recursive(i, maxNodes, adjMatrix, visited, visitOrder);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new RecursiveDFS().solution();
    }
}

/*
8 10
0 1
0 2
0 3
1 3
1 4
2 5
3 4
4 7
5 6
6 7
 */ // 10개의 줄에 겹처서 간선이 연결한 정점 ( 간선 정보 )
