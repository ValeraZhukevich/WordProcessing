package controller;

import model.WordOccurrence;
import model.TextUnit;
import controller.parcer.Parcer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Controller {

    TextUnit compositeText;
    List<WordOccurrence> wordOccurrences;
    Parcer parcer = new Parcer();

    public Controller(String textPath, String wordsPath) {
        wordOccurrences = new ArrayList<>();
        getComposeTextFromFile(textPath);
        fillWordOccurrences(wordsPath);
    }

    private void getComposeTextFromFile(String textPath){

        compositeText = parcer.parceFile2TextUnit(textPath);
    }

    public List<WordOccurrence> getWordOccurrences() {
        return wordOccurrences;
    }

    public void sordWordOccurrences(){
        Collections.sort(wordOccurrences);
    }

    private void fillWordOccurrences(String wordsPath){

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

    public void filterWordPerOccurencies(int minOccurencies, int maxOccurencies){

        sordWordOccurrences();
        List<WordOccurrence> result = wordOccurrences.stream().filter(wordOccurrence ->
            wordOccurrence.getOccurenceInText() >= minOccurencies && wordOccurrence.getOccurenceInText() <= maxOccurencies
        ).collect(Collectors.toList());
        result.stream().forEach(wordOccurrence -> System.out.println(wordOccurrence));
    }
}
