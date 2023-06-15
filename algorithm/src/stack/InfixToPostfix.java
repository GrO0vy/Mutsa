package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class InfixToPostfix {

    private boolean isOperator(char token){
        return token == '(' || token == '+' || token == '-' || token == '*' || token == '/';
    }

    // 스택 내부에서의 우선순위
    private int inStackPriority(char operator){
        if(operator =='+' || operator == '-') return 1;
        else if(operator == '*' || operator =='/') return 2;
        else if(operator == '(') return 0;
        else throw new IllegalArgumentException("not allowed operator");
    }
    // 스택 외부에서의 우선순위
    private int inComingPriority(char operator){
        if(operator =='+' || operator == '-') return 1;
        else if(operator == '*' || operator =='/') return 2;
        else if(operator == '(') return 0;
        else throw new IllegalArgumentException("not allowed operator");
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        // 결과를 담아둘 StringBuilder
        StringBuilder answerBuilder = new StringBuilder();
        // 연산자 담는 스택
        Stack<Character> operStack = new Stack<>();

        for(int i = 0; i < input.length(); i++){
            char token = input.charAt(i);
            // 연산자 일 때 ( +, -, *, /, '(' ) 일 때
            if(isOperator(token)){
                // TODO 스택이 비어있다면 push
                if(operStack.empty()){
                    operStack.push(token);
                }
                // TODO 스택이 비지 않았다면
                else{
                    // TODO 스택의 제일 위의 연산자가 나보다 우선순위가 낮을 때까지 pop
                    while(inStackPriority(operStack.peek()) >= inComingPriority(token)) {
                        // TODO 순회 중 스택이 다 빌 경우 반복 중단
                        if(operStack.empty()) break;
                        answerBuilder.append(operStack.pop());
                    }
                    // TODO while 종료 후 스택에 push
                    operStack.push(token);
                }

            }
            else if(token == ')'){
                char top = operStack.peek();
                // TODO 스택에서 여는 괄호가 나올 때 까지 pop
                while(top != '('){
                    answerBuilder.append(top);
                    top = operStack.pop();
                }
            }
            // 숫자의 경우 바로 출력
            else{
                answerBuilder.append(token);
            }
            // TODO 연산자 스택이 빌때까지 pop
            while(!operStack.empty()){
                answerBuilder.append(operStack.pop());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new InfixToPostfix().solution();
    }
}
