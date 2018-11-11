package model;

import java.util.ArrayList;
import java.util.List;

public class Paragraph implements TextUnit{

    private List<TextUnit> sentences = new ArrayList<TextUnit>();

    public void addTextUnit(TextUnit sentence) {
        sentences.add(sentence);
    }

    public String getText() {

        StringBuilder sb = new StringBuilder();

        sb.append("\t");
        for (int i = 0; i < sentences.size(); i++){
            sb.append(sentences.get(i).getText());
            if (i != sentences.size() - 1){
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public List<TextUnit> getSentences() {
        return sentences;
    }

    @Override
    public List<TextUnit> getWords() {

        List<TextUnit> words = new ArrayList<>();
        sentences.stream().forEach(sentence -> words.addAll(sentence.getWords()));
        return words;
    }

    @Override
    public void clear() {
        sentences.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paragraph)) return false;

        Paragraph paragraph = (Paragraph) o;

        return getSentences() != null ? getSentences().equals(paragraph.getSentences()) : paragraph.getSentences() == null;
    }

    @Override
    public int hashCode() {
        return getSentences() != null ? getSentences().hashCode() : 0;
    }
}
