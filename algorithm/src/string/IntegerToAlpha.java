package string;

public class IntegerToAlpha {
    public String itoa(int value){
        StringBuilder answerBuilder = new StringBuilder();

        // TODO 음수인지 확인 후
        // TODO 숫자 부분을 따로 추려내기 위해서 -1 곱함
        boolean isNegative = value < 0;
        value *= isNegative ? -1 : 1;

        // TODO value가 0보다 큰 동안 자릿수 추출해서 문자열에 추가
        while(value > 0){
            answerBuilder.append(value % 10);
            value /= 10;
        }

        // TODO 음수이면 문자열 맨 끝에 -를 더해준다
        if(isNegative) answerBuilder.append('-');

        // TODO 뒷자리부터 추가되었기 때문에 뒤집어서 리턴
        return answerBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        IntegerToAlpha integerToAlpha = new IntegerToAlpha();
        System.out.println(integerToAlpha.itoa(1234) + integerToAlpha.itoa(-56789));
    }
}
