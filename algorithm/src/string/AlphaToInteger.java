package string;

public class AlphaToInteger {
    // 숫자로만 이루어진 value 문자열에 대해서
    // 각 글자를 숫자 데이터로 해석한 뒤
    // -48 하면 숫자가 된다.

    public int atoi(String value){
        int number = 0;
        // TODO 첫번째 문자를 사전에 확인
        boolean isNegative = value.charAt(0) == '-';
        int i = isNegative ? 1 : 0;

        // TODO 문자열을 한 글자 ( 한 자리 )씩 확인
        for( ; i < value.length(); i++){
            // TODO 자릿수 변환 후 글자를 숫자로 변환하여 더하기
            number = number * 10 + (int) (value.charAt(i) - '0');
        }

        if(isNegative) number *= -1;
        return number;
    }

    public static void main(String[] args) {
        AlphaToInteger alphaToInteger = new AlphaToInteger();

        System.out.println(alphaToInteger.atoi("-123124") + alphaToInteger.atoi("123124"));
    }
}
