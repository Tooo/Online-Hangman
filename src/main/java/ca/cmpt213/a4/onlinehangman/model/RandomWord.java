package ca.cmpt213.a4.onlinehangman.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * RandomWords takes in the commonWords.txt
 * Picks a random word with getWord()
 */
public class RandomWord {
    private ArrayList<String> commonWords = new ArrayList<>();
    private static RandomWord instance;

    private RandomWord () {
        try {
            generateList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static RandomWord getInstance() {
        if (instance == null) {
            instance = new RandomWord();
        }
        return instance;
    }

    private void generateList() throws FileNotFoundException {
        String newWord;
        File file = new File("src/commonWords.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            newWord = scanner.nextLine();
            commonWords.add(newWord);
        }
    }

    public String getWord() {
        Random random = new Random();
        int wordCount = commonWords.size();
        int randomIndex = random.nextInt(wordCount);

        return commonWords.get(randomIndex);
    }
}
