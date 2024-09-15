import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[] romanAll = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX",
            "*****", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
            "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX",
            "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII",
            "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static void main(String[] args) throws Exception {


        System.out.println("Введите выражение: ");
        String input = scanner.nextLine();
        String result = calc(input);
        System.out.print("Результат выражения: " + result);
    }

    public static String calc(String expression) throws Exception {

        String[] symbols = expression.split(" ");

        if (symbols.length != 3) {
            throw new Exception();
        }

        String operator = symbols[1];

        String operand1 = symbols[0];
        String operand2 = symbols[2];

        if ((isArab(operand2) && !isArab(operand1)) || (!isArab(operand2) && isArab(operand1))) {
            throw new Exception();
        }

        int first, second;

        boolean isArabic = isArab(operand2) && isArab(operand1);

        if (isArabic) {
            first = Integer.parseInt(operand1);
            second = Integer.parseInt(operand2);
        } else {
            first = romanToArab(operand1);
            second = romanToArab(operand2);
        }

        if (first > 10 || second > 10 || first < 1 || second < 1) {
            throw new Exception();
        }

        int otvet = calculate(first, second, operator);
        String result = isArabic ? String.valueOf(otvet) : (otvet != 0 ? arabToRoman(otvet) : null);
        if (result == null) {throw new Exception();}
        return result;
    }

    public static int calculate(int q, int w, String operation) throws Exception {
        return switch (operation) {//кайфовые формалы
            case "+":
                yield q + w;
            case "-":
                yield q - w;
            case "*":
                yield q * w;
            case "/":
                yield q / w;
            default: throw new Exception("Unexpected value: " + operation);
        };
    }

    public static Integer romanToArab(String arabNumeral) throws Exception {
        int i;
        for (i = 1; i < 11; i++) {
            if (arabNumeral.equals(romanAll[i])) {
                return i;
            }
        }
        throw new Exception();
    }

    public static String arabToRoman(int arabNumeral) {
        return romanAll[arabNumeral];
    }

    public static boolean isArab(String a) {
        Pattern p = Pattern.compile("\\d+");
        return p.matcher(a).matches();
    }
}