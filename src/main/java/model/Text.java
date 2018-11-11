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
        for (int i = 0; i < paragraphs.size(); i++){
            sb.append(paragraphs.get(i).getText());
            if (i != paragraphs.size() - 1){
                sb.append(System.getProperty("line.separator"));
            }
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

    @Override
    public void clear() {
        paragraphs.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Text)) return false;

        Text text = (Text) o;

        return paragraphs != null ? paragraphs.equals(text.paragraphs) : text.paragraphs == null;
    }

    @Override
    public int hashCode() {
        return paragraphs != null ? paragraphs.hashCode() : 0;
    }
}
