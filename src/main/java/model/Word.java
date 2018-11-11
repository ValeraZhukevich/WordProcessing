package model;

import java.util.ArrayList;
import java.util.List;

public class Word implements TextUnit {

    private List<TextUnit> symbols = new ArrayList<>();
    private boolean hasPunctuationMarkAfter;

    public Word(List<Symbol> symbols, boolean hasPunctuationMarkAfter){
        this.symbols.addAll(symbols);
        this.hasPunctuationMarkAfter = hasPunctuationMarkAfter;
    }

    public void addTextUnit(TextUnit textUnit) {
        symbols.add(textUnit);
    }

    public String getText() {

        StringBuilder sb = new StringBuilder();

        symbols.stream().forEach(symbol -> sb.append(symbol.getText()));

        if (!hasPunctuationMarkAfter){
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public List<TextUnit> getSentences() {
        return null;
    }

    @Override
    public List<TextUnit> getWords() {
        return null;
    }

    public List<TextUnit> getSymbols() {
        return symbols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;

        Word word = (Word) o;

        return getSymbols() != null ? getSymbols().equals(word.getSymbols()) : word.getSymbols() == null;
    }

    @Override
    public int hashCode() {
        return getSymbols() != null ? getSymbols().hashCode() : 0;
    }

    @Override
    public String toString() {
        return getText();
    }
}
