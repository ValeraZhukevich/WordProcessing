package model;


import java.util.ArrayList;
import java.util.List;

public class Sentence implements TextUnit{

    private List<TextUnit> wordsAndPunctuationMarks = new ArrayList<TextUnit>();

    public void addTextUnit(TextUnit wordAndPunctuationMark) {
        wordsAndPunctuationMarks.add(wordAndPunctuationMark);
    }

    public String getText() {

        StringBuilder sb = new StringBuilder();
        wordsAndPunctuationMarks.stream().forEach(textUnit -> sb.append(textUnit.getText()));
        return sb.toString().trim();
    }

    @Override
    public List<TextUnit> getSentences() {
        return null;
    }

    @Override
    public List<TextUnit> getWords() {

        List<TextUnit> words = new ArrayList<>();
        wordsAndPunctuationMarks.stream().forEach(wordAndPunctuationMark -> {
            if (wordAndPunctuationMark instanceof Word){
                words.add(wordAndPunctuationMark);
            }
        });
        return words;
    }


}
