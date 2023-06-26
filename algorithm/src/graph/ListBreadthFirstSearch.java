package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ListBreadthFirstSearch {
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

        // BFS
        // 다음 방문 대상 기록용 Queue
        Queue<Integer> toVisit = new LinkedList<>();
        // 방문 순서 기록용 List
        List<Integer> visitedOrder = new ArrayList<>();
        // 방문 기록용 boolean[]
        boolean[] visited = new boolean[maxNodes];

        int next = 0;
        toVisit.offer(next);
        // 큐가 비어있지 않은 동안 반복
        while (!toVisit.isEmpty()) {
            // 다음 방문 정점 회수
            next = toVisit.poll();

            // 이미 방문 했다면 다음 반복으로
            if (visited[next]) continue;

            // 방문했다 표시
            visited[next] = true;
            // 방문한 순서 기록
            visitedOrder.add(next);


            // adjList.get(next)에 담겨있는 미방문 요소들을 차례대로 다 넣는다.
            List<Integer> canVisitList = adjList.get(next);
            for(Integer canVisit : canVisitList){
                if(!visited[canVisit]){
                    toVisit.offer(canVisit);
                }
            }
        }

        // 출력
        System.out.println(visitedOrder);
    }

    public static void main(String[] args) throws IOException {
        new ListBreadthFirstSearch().solution();
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
