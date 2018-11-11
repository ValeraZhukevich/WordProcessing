package model;

import java.util.List;

public class Symbol implements TextUnit {

    char symbol;

    public Symbol(char symbol){
        this.symbol = symbol;
    }

    @Override
    public void addTextUnit(TextUnit textUnit) {

    }

    public String getText() {
        return String.valueOf(symbol);
    }

    @Override
    public List<TextUnit> getSentences() {
        return null;
    }

    @Override
    public List<TextUnit> getWords() {
        return null;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Symbol)) return false;

        Symbol symbol1 = (Symbol) o;

        return Character.toLowerCase(getSymbol())  == Character.toLowerCase(symbol1.getSymbol());
    }

    @Override
    public int hashCode() {
        return (int) Character.toLowerCase(getSymbol());
    }
}
