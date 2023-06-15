package stack;

public class MyStack {
    // 스택에 데이터가 저장되는 곳
    private final int[] arr =  new int[16];

    // 현재 스택의 최고점을 파악하기 위한 top
    private int top = -1;

    public MyStack(){}

    // push : 데이터를 스택의 제일 위에 넣는 메서드
    public void push(int data){
        // 1. arr이 가득 찼는지를 확인한다.
        if(arr.length -1 == top){
            throw new RuntimeException("Stack is full");
        }
        // 2. top을 하나 증가시킨다.
        top++;
        // 3. arr[top]에 data를 할당한다.
        arr[top] = data;

        // arr[++top];
    }

    // pop : 제일 위의 데이터를 회수하는 메서드
    public int pop(){
        // 1. arr이 비어있는지를 확인한다.
        if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        // 2. 맨위의 데이터를 반환한다.
        // 2-1. top의 값을 임시 저장한다.
        int temp = arr[top];

        // 3. top을 하나 감소시킨다.
        top--;

        // 2-2. top의 값을 반환한다.
        return temp;


//        return arr[top--];
    }

    // peek : 스택의 top에 있는 데이터를 제거없이 반환한다.
    public int peek(){
        // 1. 스택이 비어있는지를 확인한다.
        if(isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        // 2. top의 값을 반환한다.
        return arr[top];
    }

    // isEmpty : 스택이 비어있는지를 확인한다.
    public boolean isEmpty(){
        // top이 -1 이면 true를 반환
        return top == -1;
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        myStack.push(3);
        myStack.push(5);
        myStack.push(7);

        System.out.println(myStack.peek());

        myStack.pop();

        System.out.println(myStack.peek());

        myStack.pop();

        System.out.println(myStack.peek());

        myStack.pop();

        System.out.println(myStack.isEmpty());
        System.out.println(myStack.peek());
    }
}
