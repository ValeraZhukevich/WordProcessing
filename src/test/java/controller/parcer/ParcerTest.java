package controller.parcer;

import model.Symbol;
import model.TextUnit;
import model.Word;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class ParcerTest {

    Parcer parcer = new Parcer();

    @Test
    public void testParceFile2TextUnit() {
        String filePath = "C:\\Users\\Valera\\IdeaProjects\\WordProcessing\\src\\test\\resources\\test1.txt";
        TextUnit text = parcer.parceFile2TextUnit(filePath);
        String expected = "\tMr. and Mrs. Dursley, of number four, Privet Drive, were proud to say that" +
                " they were perfectly normal. World leaders and you have e-mail with @silly@ name.";
        Assert.assertTrue(text.getText().equals(expected));

    }

    @Test
    public void testWrite2File() {
        String filePath = "C:\\Users\\Valera\\IdeaProjects\\WordProcessing\\src\\test\\resources\\test2.txt";
        TextUnit text = parcer.parceFile2TextUnit(filePath);
        parcer.write2File("C:\\Users\\Valera\\IdeaProjects\\WordProcessing\\src\\test\\resources\\result2.txt");
        TextUnit text1 = parcer.parceFile2TextUnit(filePath);
        Assert.assertEquals(text, text1);
    }

    @Test
    public void testGetListOfWords() {

        String filePath = "C:\\Users\\Valera\\IdeaProjects\\WordProcessing\\src\\test\\resources\\test3.txt";

        List<String> expectedWords = new ArrayList<>();
        expectedWords.add("leaders");
        expectedWords.add("donald");
        expectedWords.add("world");
        expectedWords.add("french");
        expectedWords.add("president");
        expectedWords.add("patriotism");

        Set<TextUnit> resultSet = parcer.getListOfWords(filePath);

        Assert.assertEquals(expectedWords.size(), resultSet.size());

        resultSet.stream().forEach(word -> {
            Assert.assertTrue(expectedWords.contains(word.getText().toLowerCase().trim()));
        });
    }
}