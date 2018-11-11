package helpers;

import model.TextUnit;
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

    @Override
    public int compareTo(@NotNull WordOccurrence o) {
        int result = Double.compare(this.occurenceInText, o.getOccurenceInText());
        if (result == 0){

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
