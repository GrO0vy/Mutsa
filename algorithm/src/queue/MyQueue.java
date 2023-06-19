package queue;

public class MyQueue {
    private int[] arr = new int[4];

    // Queue에서 데이터 꺼내는 위치
    private int front = -1;
    // Queue에 데이터 추가하는 위치
    private int rear = -1;

    public MyQueue(){}

    // 데이터 추가
    public void enQueue(int x){
        if(rear == arr.length - 1){
            throw new RuntimeException("queue is full");
        }
        arr[++rear] = x;
    }

    // 데이터 회수
    public int deQueue(){
        if(front == rear){
            throw new RuntimeException("queue is empty");
        }
        return arr[++front];
    }

    // 큐가 비어있는지 확인
    public boolean isEmpty(){
        return front == rear;
    }

    // 다음에 나올 데이터가 무엇인지 확인
    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("queue is empty");
        }
        return arr[front + 1];
    }

    public static void main(String[] args) {
        MyQueue intQueue = new MyQueue();
        intQueue.enQueue(1);
        intQueue.enQueue(2);
        intQueue.enQueue(3);
        intQueue.enQueue(4);

        System.out.println(intQueue.deQueue());
        System.out.println(intQueue.deQueue());
        System.out.println(intQueue.deQueue());
        System.out.println(intQueue.peek());
        System.out.println(intQueue.deQueue());
    }
}
