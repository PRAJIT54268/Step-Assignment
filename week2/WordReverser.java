package week2;
import java.util.Scanner;

public class WordReverser {

    static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        String[] reversedWords = new String[words.length];

        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder(words[i]);
            reversedWords[i] = sb.reverse().toString();
        }

        return String.join(" ", reversedWords);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String sentence = sc.nextLine();

        System.out.println(reverseEachWord(sentence));
    }
}