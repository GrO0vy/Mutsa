package queue;

import java.util.LinkedList;
import java.util.Queue;

public class JavaQueue {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        // enQueue 에 해당하는 메서드
        // offer, add
        // Queue 가 가득 차 있다면
        // offer - false 를 반환, add - 예외 발생
        queue.offer(1);
        queue.add(2);

        // deQueue 에 해당하는 메서드
        // remove, poll
        // Queue 가 비어있다면
        // poll - null 반환, remove - 예외 발생
        System.out.println(queue.remove());
        System.out.println(queue.poll());

        // peek 에 해당하는 메서드
        // peek, element
        // Queue 가 비어있다면
        // peek - null 반환, element - 예외 발생
        System.out.println(queue.peek());
        System.out.println(queue.element());
    }
}
