package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListComparison {
    // ArrayList와 LinkedList 성능비교

    public static void main(String[] args) {
        List<Object> arrayList = new ArrayList<>();
        List<Object> linkedList = new LinkedList<>();

        rearInsert(arrayList);
        getElements(arrayList);
        frontInsert(arrayList);
        System.out.println("------------------------------------");
        rearInsert(linkedList);
        getElements(linkedList);
        frontInsert(linkedList);
    }

    // 1. 10000개의 데이터를 리스트에 순차적으로 삽입
    public static void rearInsert(List<Object> list){
        long start = System.nanoTime();
        // 실제로 작업하기
        for(int i = 0; i < 10000; i++){
            list.add(i);
        }
        long end = System.nanoTime();

        System.out.println(String.format("순차적 추가 소요시간 : %15d ns",end - start));
    }

    // 2. 10000개의 데이터를 리스트의 앞에 삽입
    public static void frontInsert(List<Object> list){
        long start = System.nanoTime();
        // 실제로 작업하기
        for(int i = 0; i < 10000; i++){
            list.add(0, i);
        }

        long end = System.nanoTime();

        System.out.println(String.format("앞쪽에 추가 소요시간 : %15d ns",end - start));
    }

    // 3. 리스트의 모든 원소를 순서를 바탕으로 조회
    public static void getElements(List<Object> list){
        long start = System.nanoTime();
        // 실제로 작업하기
        for(int i = 0; i < 10000; i++){
            list.get(i);
        }
        long end = System.nanoTime();

        System.out.println(String.format("아이템 조회 소요시간 : %15d ns",end - start));
    }
}
