package controller;

import helpers.WordOccurrence;
import model.TextUnit;
import parcer.Parcer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Controller {

    public static void main(String[] args) {
        Controller controller = new Controller("C:\\Users\\Valera\\IdeaProjects\\WordProcessing\\src\\test\\resources\\text.txt",
                "C:\\Users\\Valera\\IdeaProjects\\WordProcessing\\src\\test\\resources\\words.txt");
        controller.getWordOccurrences().stream().forEach(wordOccurrence -> System.out.println(wordOccurrence));
    }

    TextUnit compositeText;
    List<WordOccurrence> wordOccurrences;
    Parcer parcer = new Parcer();

    public Controller(String textPath, String wordsPath) {
        wordOccurrences = new ArrayList<>();
        getComposeTextFromFile(textPath);
        fillMap(wordsPath);
    }

    public void getComposeTextFromFile(String textPath){

        compositeText = parcer.parceFile2TextUnit(textPath);
    }

    public List<WordOccurrence> getWordOccurrences() {
        return wordOccurrences;
    }

    public void fillMap(String wordsPath){

        Set<TextUnit> set = parcer.getListOfWords(wordsPath);

        set.stream().forEach(word -> wordOccurrences.add(new WordOccurrence(word)));

        for (WordOccurrence wordOccurrence : wordOccurrences){
            compositeText.getSentences().stream().forEach(sentence -> {
                if (sentence != null){
                    wordOccurrence.add(sentence.getWords().stream().filter(wordOccurrence.getWord() :: equals).count());
                }
            });
        }
    }
}
