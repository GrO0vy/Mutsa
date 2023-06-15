package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ParTest {
    public boolean solution() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();

        Stack<Character> charStack = new Stack<>();

        for(int i = 0; i < input.length(); i++){
            char next = input.charAt(i);
            if(next == '(') charStack.push(next);
            else{
                if(charStack.empty()) return false;
                charStack.pop();
            }
        }
        return charStack.empty();
    }

    public static void main(String[] args) throws IOException {
        System.out.println((new ParTest()).solution());
    }
}
