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
}
