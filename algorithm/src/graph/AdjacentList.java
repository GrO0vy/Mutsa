package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AdjacentList {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer : 입력한 문자열을 지정한 문자열 ( delimiters ) 를 기준으로 나누어서
        // 한 단어씩 반환해주는 도구 ( 기본은 공백 )
        StringTokenizer graphTokenizer = new StringTokenizer(reader.readLine());
        // StringTokenizer.nextToken() : 다음 단어 가져오기
        int maxNodes = Integer.parseInt(graphTokenizer.nextToken());    // 8
        int edges = Integer.parseInt(graphTokenizer.nextToken());       // 10

        List<List<Integer>> adjList = new ArrayList<>();
        // 리스트 초기화
        for(int i = 0; i  < maxNodes; i++){
            adjList.add(new ArrayList<>());
        }

        for(int i =0; i < edges; i++){
            // 다음 줄을 공백 단위로 나눠줌
            StringTokenizer edgeTokenizer = new StringTokenizer(reader.readLine());
            int startNode = Integer.parseInt(edgeTokenizer.nextToken());
            int endNode = Integer.parseInt(edgeTokenizer.nextToken());

            // adjList 의 startNode 번 째 리스트에 endNode 를 첨부
            // 유향 그래프의 경우 아래 줄만
            adjList.get(startNode).add(endNode);
            // 무향 그래프의 경우 한 줄 추가
            adjList.get(endNode).add(startNode);
        }
        // 선택사항 : DFS / BFS 할 때 이왕이면 방문순서를 작은 숫자부터와 같은 조건을 붙이고 싶다면
        // 정렬해야한다.
        for(List<Integer> adjRow : adjList){
            Collections.sort(adjRow);
        }

        for(List<Integer> adjRow : adjList){
            System.out.println(adjRow);
        }
    }

    public static void main(String[] args) throws IOException {
        new AdjacentList().solution();
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
