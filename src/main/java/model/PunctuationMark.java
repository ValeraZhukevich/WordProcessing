package model;

import java.util.ArrayList;
import java.util.List;

public class PunctuationMark implements TextUnit {

    private List<TextUnit> symbols = new ArrayList<>();
    private boolean afterWord;

    public PunctuationMark(List<Symbol> symbols, boolean afterWord){
        this.symbols.addAll(symbols);
        this.afterWord = afterWord;
    }

    @Override
    public void addTextUnit(TextUnit textUnit) {
        symbols.add(textUnit);
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        symbols.stream().forEach(symbol -> sb.append(symbol.getText()));
        if (afterWord){
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
        if (!(o instanceof PunctuationMark)) return false;

        PunctuationMark that = (PunctuationMark) o;

        if (afterWord != that.afterWord) return false;
        return getSymbols() != null ? getSymbols().equals(that.getSymbols()) : that.getSymbols() == null;
    }

    @Override
    public int hashCode() {
        int result = getSymbols() != null ? getSymbols().hashCode() : 0;
        result = 31 * result + (afterWord ? 1 : 0);
        return result;
    }
    @Override
    public void clear() {
        symbols.clear();
    }
}
