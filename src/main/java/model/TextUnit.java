package model;

import java.util.List;

public interface TextUnit {

    void addTextUnit(TextUnit textUnit);

    String getText();

    List<TextUnit> getSentences();

    List<TextUnit> getWords();

}
