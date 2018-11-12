package model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WordOccurrence implements Comparable<WordOccurrence>{

    private TextUnit word;
    private List<Long> occurencesInSentences = new ArrayList<>();
    private int occurenceInText;

    public WordOccurrence(TextUnit word) {
        this.word = word;
    }

    public int getOccurenceInText() {
        return occurenceInText;
    }

    public void add(long occurenceInCurrentSetnence){
        occurencesInSentences.add(occurenceInCurrentSetnence);
        occurenceInText += occurenceInCurrentSetnence;
    }

    public TextUnit getWord() {
        return word;
    }

    public List<Long> getOccurencesInSentences() {
        return occurencesInSentences;
    }

    @Override
    public int compareTo(@NotNull WordOccurrence o) {
        int result = Double.compare(this.occurenceInText, o.getOccurenceInText()) * -1;
        if (result == 0){
            return word.toString().compareTo(o.toString());
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(word + " " + "total occurences: " + occurenceInText);
        return sb.toString();
    }
}
