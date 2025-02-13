import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите выражение");
        String s = sc.nextLine();
        System.out.println(calc(s));
    }
    public static String String1(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!(Character.isDigit(s.charAt(i))||s.charAt(i)=='+'||s.charAt(i)=='-'||s.charAt(i)=='*'||s.charAt(i)=='/'||s.charAt(i)=='I'||s.charAt(i)=='V'||s.charAt(i)=='X')) {
                s = s.replace(s.charAt(i), ' ');
            }
        }
        s = s.replaceAll(" ", "");
        return s;
    }
    public static String Roma (String s) {
        int a = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.startsWith("IV", i)) {
                a = a + 4;
                break;
            }
            if (s.startsWith("IX", i)) {
                a = a + 9;
                break;
            }
            if ((s.charAt(i) == 'V')) {
                a = a + 5;
            }
            if ((s.charAt(i) == 'I')) {
                a = a + 1;
            }
            if ((s.charAt(i) == 'X')) {
                a = a + 10;
            }
            if ((!Character.isLetter(s.charAt(i)))) {
                throw new RuntimeException("Неправильный формат строки");
            }
        }
        return Integer.toString(a);
    }
    public static String Roma2 (String s) {
        int a = 0; int b, e, d = 0; boolean flag1 = true; boolean flag2 = false; char c = ' ';
        String num1 = "", num2 = "";
        for (int i = 0; i < s.length(); i++) {
            if ((Character.isLetter(s.charAt(i)))&&flag1) {
                a = i;
                flag1 = false;
            }
            if (!(Character.isLetter(s.charAt(i)))&&!flag1) {
                b = i;
                flag2 = true;
                num1 = Roma(s.substring(a, b));
                c = s.charAt(i);
            }
            if ((Character.isLetter(s.charAt(i)))&&flag2) {
                d = i;
                flag2 = false;
            }
            if (Character.isDigit(s.charAt(i))) {
                throw new RuntimeException("Неправильный формат строки");
            }
            if (i == s.length()-1) {
                e = i;
                num2 = Roma(s.substring(d, e+1));
            }
        }
        if (d == a) {
            throw new RuntimeException("Неправильный формат строки");
        }
        return num1 + c + num2;
    }
    public static String arabicToRoma(int num) throws IllegalArgumentException {
        try {
        int a1 = num / 100, a2 = ((num - a1 * 100) / 10), a3 = num - a1 * 100 - a2 * 10;
        String a = "", b = "", c = "";
        if (a1 < 4) {
            a = "C".repeat(a1);
        }
        if (a1 == 4) {
            a = "CD";
        }
        if (a1 > 4 && a1 < 9) {
            a = "D" + "C".repeat(a1 - 5);
        }
        if (a1 == 9) {
            a = "CM";
        }
        if (a2 < 4) {
            b = "X".repeat(a2);
        }
        if (a2 == 4) {
            b = "XL";
        }
        if (a2 > 4 && a2 < 9) {
            b = "L" + "X".repeat(a2 - 5);
        }
        if (a2 == 9) {
            b = "XC";
        }
        if (a3 < 4) {
            c = "I".repeat(a3);
        }
        if (a3 == 4) {
            c = "IV";
        }
        if (a3 > 4 && a3 < 9) {
            c = "V" + "I".repeat(a3 - 5);
        }
        if (a3 == 9) {
            c = "IX";
        }
        return (a + b + c);} catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("В римской системе нет отрицательных чисел");
        }
    }
    public static String calc(String s) {
        int a = 0, b, d = 0; boolean flag1 = true; boolean flag2 = false; char c = ' ';
        int num1 = 0, num2 = 0, num3; String str = "";
        s = String1(s);
        if (Character.isLetter(s.charAt(0)))
            str = Roma2(s);
        if (Character.isDigit(s.charAt(0)))
            str = s;
        for (int i = 0; i < str.length(); i++) {
            if ((Character.isDigit(str.charAt(i))) && flag1) {
                a = i;
                flag1 = false;
            }
            try {
                if (!(Character.isDigit(str.charAt(i))) && !flag1) {
                    b = i;
                    num1 = Integer.parseInt(str.substring(a, b));
                    if ((num1>10)||(num1<1)){
                        throw new RuntimeException("Принимаются числа от 1 до 10");
                    }
                    flag2 = true;
                    c = str.charAt(i);
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("Неправильный формат строки");
            }
            if ((Character.isDigit(str.charAt(i))) && flag2) {
                d = i;
                flag2 = false;
            }
            try {
                if (i == str.length() - 1) {
                    b = i;
                    num2 = Integer.parseInt(str.substring(d, b + 1));
                    if ((num2>10)||(num2<1)){
                        throw new RuntimeException("Принимаются числа от 1 до 10");
                    }
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Неправильный формат строки");
            }
        }
        num3 = switch (c) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> throw new RuntimeException("Неправильный формат строки");
        };
        if (Character.isDigit(s.charAt(0))) {
            return Integer.toString(num3);
        }
        if (Character.isLetter(s.charAt(0))) {
            return arabicToRoma(num3);
        }
        return("");
    }
}