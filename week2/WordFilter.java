package week2;

import java.util.*;

public class WordFilter {
    static void printFilteredWordFrequency(String feedback) {
        feedback = feedback.toLowerCase();
        feedback = feedback.replace(".", "");
        feedback = feedback.replace(",", "");
        String[] words = feedback.split("\\s+");
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};
        String[] uniqueWords = new String[words.length];
        int[] counts = new int[words.length];

        int uniqueCount = 0;

        for (int i = 0; i < words.length; i++) {
            boolean isStopWord = false;
            for (int j = 0; j < stopWords.length; j++) {
                if (words[i].equals(stopWords[j])) {
                    isStopWord = true;
                    break;
                }
            }
            if (isStopWord) {
                continue;
            }
            int index = -1;
            for (int j = 0; j < uniqueCount; j++) {
                if (uniqueWords[j].equals(words[i])) {
                    index = j;
                    break;
                }
            }
            if (index != -1) {
                counts[index]++;
            } else {
                uniqueWords[uniqueCount] = words[i];
                counts[uniqueCount] = 1;
                uniqueCount++;
            }
        }

        for (int i = 0; i < uniqueCount - 1; i++) {
            for (int j = i + 1; j < uniqueCount; j++) {
                if (counts[j] > counts[i]) {
                    int tempCount = counts[i];
                    counts[i] = counts[j];
                    counts[j] = tempCount;
                    String tempWord = uniqueWords[i];
                    uniqueWords[i] = uniqueWords[j];
                    uniqueWords[j] = tempWord;
                }
            }
        }
        for (int i = 0; i < uniqueCount; i++) {
            System.out.println(uniqueWords[i] + ": " + counts[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String feedback = sc.nextLine();
        printFilteredWordFrequency(feedback);
    }
}