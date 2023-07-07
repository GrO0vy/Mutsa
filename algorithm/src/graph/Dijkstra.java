package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Dijkstra {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer info = new StringTokenizer(reader.readLine());
        int nodes = Integer.parseInt(info.nextToken());
        int edges = Integer.parseInt(info.nextToken());
        int start = Integer.parseInt(reader.readLine());

        // 인접행렬, 연결되어 있을경우 양수, 없을 경우 (-1)
        int[][] adjMat = new int[nodes][nodes];
        for(int[] row : adjMat){
            Arrays.fill(row, -1);
        }

        // 인접 행렬 초기화
        for(int i = 0; i< edges; i++){
            StringTokenizer edgeToken = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(edgeToken.nextToken());
            int to = Integer.parseInt(edgeToken.nextToken());
            int cost = Integer.parseInt(edgeToken.nextToken());

            adjMat[from][to] = cost;
        }

        // 방문 정보 visited
        boolean[] visited = new boolean[nodes];

        // 현재까지의 최저 거리정보 dist
        int[] dist = new int[nodes];
        // 1. 모든 정점까지 아직 도달할 길이 없으므로, 무한대 ( 또는 최대 ) 로 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 2. 시작 정점까지의 거리는 0
        dist[start] = 0;

        // 다익스트라 시작
        // 반복 기준 : 아직 방문한 점이 남아있을 때 -> 노드 개수만큼 반복
        for(int i = 0; i < nodes; i++){
            // 이번에 방문할 정점을 선택 ( 현재 정점들 까지의 최단경로 정보 중 가장 가까운 정점 )
            // 최솟값 비교용 변수
            int minDist = Integer.MAX_VALUE;
            // 최소 거리 노드 ( index 저장용 )
            int minDistNode = -1;
            // 미방문 정점 중 최단거리 정점 조사
            for(int j = 0; j < nodes; j++){
                if(!visited[j] && dist[j] < minDist){
                    minDist = dist[j];
                    minDistNode = j;
                }
            }

            // 더 이상 도달 가능한 미방문 노드가 없다
            if(minDistNode == -1) break;

            // 최종 선택된 노드 방문처리
            visited[minDistNode] = true;

            for(int j = 0; j < nodes; j++){
                if(adjMat[minDistNode][j] == -1) continue;
                int cost = adjMat[minDistNode][j];

                if(dist[j] > dist[minDistNode] + cost)
                    dist[j] = dist[minDistNode] + cost;
            }
        }

        System.out.println(Arrays.toString(dist));
    }
}

/*
6 8
0
0 1 10
0 2 15
1 3 12
1 5 15
2 4 10
3 4 2
3 5 1
5 4 5
 */

/*
5 6
0 또는 4
4 0 1
0 1 2
0 2 3
1 2 4
1 3 5
2 3 6
 */