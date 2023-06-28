package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Kruskal {
    int[] parent;

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer neTokenizer = new StringTokenizer(reader.readLine());
        int nodeCount = Integer.parseInt(neTokenizer.nextToken());
        int edgeCount = Integer.parseInt(neTokenizer.nextToken());

        //  Kruskal 알고리즘은 서로소 집합의 개념을 이용해 서로다른 두 정점을 연결했을 때
        //  사이클이 발생하는지 안하는지 구분한다.
        //  배열로 표현한 트리 만들기
        parent = new int[nodeCount];
        for(int i = 0; i < nodeCount; i++){
            parent[i] = i;
        }

        //  간선 정보 해독 ( 시작, 끝, 가중치 )
        int[][] edges = new int[edgeCount][3];
        for(int i = 0; i < edgeCount; i++){
            StringTokenizer edgeTokenizer = new StringTokenizer(reader.readLine());
            edges[i][0] = Integer.parseInt(edgeTokenizer.nextToken());
            edges[i][1] = Integer.parseInt(edgeTokenizer.nextToken());
            edges[i][2] = Integer.parseInt(edgeTokenizer.nextToken());
        }
        //  준비 단계 끝

        //  1. 간선들을 가중치 기준에서 정렬
        Arrays.sort(edges, Comparator.comparingInt(edge -> edge[2]));

        //  고른 간선 수 기록
        int picked = 0;
        // 총 가중치
        int totalWeight = 0;
        // 고른 간선 기록
        List<String> pickedEdges = new ArrayList<>();

        //  2. 간선들을 가중치 순서대로 순회하면서 고른다.
        for (int i = 0; i < edgeCount; i++) {
            int start = edges[i][0];
            int end = edges[i][1];

            //  start 와 end 를 연결하면 사이클이 생기지 않는지
            //  findSet, union
            if(findSet(start) != findSet(end)){
                union(start, end);
                picked++;
                totalWeight += edges[i][2];
                pickedEdges.add(Arrays.toString(edges[i]));
            }

            //  3. 간선의 개수가 N-1 개가 되면 멈춘다.
            if(picked == nodeCount -1) break;
        }

        System.out.println(totalWeight);
        System.out.println(pickedEdges);
    }

    // findSet : 내 부무가 나 일 때까지 재귀호출
    public int findSet(int node){
        if(parent[node] == node) return node;
        else return findSet(parent[node]);
    }

    // union
    public void union(int x, int y){
        parent[findSet(y)] = findSet(x);
    }

    public static void main(String[] args) throws IOException {
        new Kruskal().solution();
    }
}

/*
8 11
0 1 41
0 2 14
0 3 13
1 4 27
2 5 21
3 5 33
3 7 22
4 6 11
4 7 17
5 6 35
6 7 19

 */