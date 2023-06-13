import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ParTest2 {
    public boolean solution() throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // 괄호 입력받기
        String input = bf.readLine();

        // 데이터를 저장할 스택 만들기
        Stack<Character> charStack = new Stack<>();

        // 괄호 개수가 일치하는지 검사
        for(int i = 0; i < input.length(); i++){
            char next = input.charAt(i);
            // 여는 괄호이면 스택에 push
            if(next == '(' || next == '{' || next == '[') {
                charStack.push(next);
            }
            // 닫는 괄호일 때 스택이 비어있거나 스택의 top과 같지 않으면 false
            else if(next == ')' || next == '}' || next ==']'){
                if(charStack.isEmpty()) return false;
                char top = charStack.pop();
                if (next == ')' && top !=  '(') return false;
                else if(next == '}' && top !=  '{') return false;
                else if(next == ']' && top !=  '[') return false;
            }
        }
        // 입력이 끝나고 스택이 비어있으면 괄호 개수가 맞기 때문에  true
        return charStack.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new ParTest2().solution());
    }
}
