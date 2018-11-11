package model;

import java.util.ArrayList;
import java.util.List;

public class Text implements TextUnit {

    private List<TextUnit> paragraphs = new ArrayList<TextUnit>();

    public void addTextUnit(TextUnit paragraph) {
        paragraphs.add(paragraph);
    }

    public String getText() {

        StringBuilder sb = new StringBuilder();
        for (TextUnit paragraph : paragraphs){
            sb.append(paragraph.getText());
        }
        return sb.toString();
    }

    @Override
    public List<TextUnit> getSentences() {
        List<TextUnit> sentences = new ArrayList<>();
        paragraphs.stream().forEach(paragraph -> sentences.addAll(paragraph.getSentences()));
        return sentences;
    }

    @Override
    public List<TextUnit> getWords() {

        List<TextUnit> words = new ArrayList<>();
        paragraphs.stream().forEach(paragraph -> words.addAll(paragraph.getWords()));
        return words;
    }


}
