import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<String> Roman = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");

    public static String integerToRoman(int num) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder roman = new StringBuilder();

        for(int i=0;i<values.length;i++) {
            while(num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }

    public static void main(String[] args) throws Exception {
        boolean isRoman = false;
        int number1;
        int number2;
        int result = 0;
        String operator;
        String input;

        System.out.println("Input:");
        Scanner inputReader = new Scanner(System.in);

        input=inputReader.nextLine().replaceAll("\\s+","").toUpperCase();
        operator = input.replaceAll("\\d","");
        String[]numbers = input.split("[\\-+*/]");
        System.out.println(Arrays.toString(numbers));

        if(numbers.length<2)throw new Exception("строка не является математической операцией");
        if(numbers.length>2)throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        if((Roman.contains(numbers[0])) && (Roman.contains(numbers[1]))){
            isRoman=true;
        }

        if(!isRoman){
            if((Arrays.asList(Roman).contains(numbers[0])) || (Arrays.asList(Roman).contains(numbers[1]))){
                throw new Exception("используются одновременно разные системы счисления");
            }
        } else {
            numbers[0]= String.valueOf((Roman.indexOf(numbers[0])+1));
            numbers[1]= String.valueOf((Roman.indexOf(numbers[1])+1));
            operator = input.replaceAll("[^\\+-/*]", "");
        }

        if (!(numbers[0].matches("[0-9]+")) || !(numbers[1].matches("[0-9]+"))){
            throw new Exception("используются одновременно разные системы счисления");
        }

        number1=Integer.parseInt(numbers[0]);
        number2=Integer.parseInt(numbers[1]);

        if((isRoman) && (number1>=number2) && (operator.equals("-"))){
            throw new Exception("в римской системе нет отрицательных чисел");
        }

        if((isRoman) && (number1>number2) && (operator.equals("/"))){
            throw new Exception("в римской системе нет отрицательных чисел");
        }

        switch (operator) {
            case "+" -> result = number1 + number2;
            case "-" -> result = number1 - number2;
            case "*" -> result = number1 * number2;
            case "/" -> result = number1 / number2;
        }

        System.out.println("Output:");
        if(isRoman){
            System.out.println(integerToRoman(result));
        } else {
            System.out.println(result);
        }

    }
}