import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int oneDigit, twoDigit;
    static char operator;
    static int result;

    public static void main(String[] args) throws AntException {
        System.out.println("Введите выражение в одну строку римскими или арабскими символами и нажмите Enter:");
        String userInput = scanner.nextLine();
        calc(userInput);
    }

    public static void calc(String input) throws AntException {

        char[] massChar = new char[10];
        if (input.length() < 2) {
            throw new AntException("throws Exception //т.к. Строка не является математической операцией");
        }
        for (int i = 0; i < input.length(); i++) {
            massChar[i] = input.charAt(i);
            if (massChar[i] == '+') {
                operator = '+';
            } else if (massChar[i] == '-') {
                operator = '-';
            } else if (massChar[i] == '*') {
                operator = '*';
            } else if (massChar[i] == '/') {
                operator = '/';
            }
        }
        //Защита оператора
        if (operator != '+' && operator != '-' && operator != '*' && operator != '/') {
            throw new AntException("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        //Разбиваем на массив слов по значению оператора
        String[] operatorLeave;
        operatorLeave = input.split("[+-/*]");
        //Забираем слева и справа от знаков и убираем пробелы
        if (operatorLeave.length > 2) {
            throw new AntException("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        String oneDigitString = operatorLeave[0].trim().toUpperCase();
        String twoDigitString = operatorLeave[1];
        String threeDigitString = twoDigitString.trim().toUpperCase();

        equalsDigit(oneDigitString, threeDigitString);

        oneDigit = RimToDigit(oneDigitString);
        twoDigit = RimToDigit(threeDigitString);
        if (oneDigit < 0 && twoDigit < 0) {
            result = 0;
        } else {
            result = calculationOperation(oneDigit, twoDigit, operator);
            System.out.println("Для римских");
            String resultRim = convertDigitToRim(result);
            System.out.println(oneDigitString + " " + operator + " " + threeDigitString + " = " + resultRim);
            return;
        }
        oneDigit = Integer.parseInt(oneDigitString);
        twoDigit = Integer.parseInt(threeDigitString);
        result = calculationOperation(oneDigit, twoDigit, operator);
        System.out.println("Для арабских");
        System.out.println(oneDigit + " " + operator + " " + twoDigit + " = " + result);
    }

    private static void equalsDigit(String num1, String num2) throws AntException {
        String[] Rim = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int sovpad_rim1 = 0, sovpad_rim2 = 0;

        for (int i = 0; i < 10; i++) {
            if (num1.equals(Rim[i])) sovpad_rim1 = 1;
            if (num2.equals(Rim[i])) sovpad_rim2 = 1;
        }
        if (((sovpad_rim1 == 1) && (sovpad_rim2 == 0)) || ((sovpad_rim1 == 0) && (sovpad_rim2 == 1)))
            throw new AntException("throws Exception //т.к. используются одновременно разные системы счисления");
    }

    private static String convertDigitToRim(int result) throws AntException {
        if (result < 0) {
            throw new AntException("throws Exception //т.к. в римской системе нет отрицательных чисел");
        }
        String[] Rim = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        if (Objects.equals(Rim[result], "O")) {
            throw new AntException("throws Exception //т.к. результат работы меньше единицы");
        }
        return Rim[result];
    }

    private static int RimToDigit(String Rim) {
        try {
            switch (Rim) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
            }
        } catch (InputMismatchException a) {
            throw new InputMismatchException("Число больше того, что в ТЗ");
        }
        return -1;
    }

    private static int calculationOperation(int oneDigit, int twoDigit, char operator) throws AntException {
        int result;

        if ((oneDigit < 1 || oneDigit > 10) || (twoDigit < 1 || twoDigit > 10)) {
            throw new AntException("throws Exception //т.к. на вход идут числа от 1 до 10 не более(По ТЗ)");
        } else {
            switch (operator) {
                case '+':
                    result = oneDigit + twoDigit;
                    break;
                case '-':
                    result = oneDigit - twoDigit;
                    break;
                case '*':
                    result = oneDigit * twoDigit;
                    break;
                case '/':
                    result = oneDigit / twoDigit;
                    break;
                default:
                    throw new IllegalArgumentException("Не верный знак операции");
            }
        }
        return result;
    }
}