package controller.parcer;

import model.TextUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ParcerTest {

    Parcer parcer = new Parcer();
    String filePath = "C:\\Users\\Valera\\IdeaProjects\\WordProcessing\\src\\test\\resources\\text.txt";

    @Test
    public void testParceFile2TextUnit() {
        TextUnit text = parcer.parceFile2TextUnit(filePath);
        parcer.write2File("C:\\Users\\Valera\\IdeaProjects\\WordProcessing\\src\\test\\resources\\result1.txt");
        TextUnit text1 = parcer.parceFile2TextUnit(filePath);
        parcer.write2File("C:\\Users\\Valera\\IdeaProjects\\WordProcessing\\src\\test\\resources\\result1.txt");

        String expected = "\tWorld leaders have.\n" +
                "\tWorld leaders have.";
        System.out.println(text.getText());
        System.out.println("aaaa");
        Assert.assertTrue(text.getText().equals(expected));
    }

    @Test
    public void testWrite2File() {
    }

    @Test
    public void testGetListOfWords() {
    }
}