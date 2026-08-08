package week2;
import java.util.Scanner;

public class ISBN_Validator {

    static String normalizeCode(String raw) {
        raw = raw.trim();

        String publisher = raw.substring(0, 3).toUpperCase();
        String rest = raw.substring(3);

        return publisher + rest;
    }

    static String validateAndFormat(String code) {

        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        String publisher = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(publisher);
        sb.append("] YEAR: ");
        sb.append(year);
        sb.append(" | CATALOG: ");
        sb.append(catalog);

        return sb.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String raw = sc.nextLine();

        String normalized = normalizeCode(raw);

        System.out.println(validateAndFormat(normalized));
    }
}