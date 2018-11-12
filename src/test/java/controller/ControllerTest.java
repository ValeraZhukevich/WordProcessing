package controller;

import model.WordOccurrence;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class ControllerTest {

    Controller controller;

   @BeforeClass
   public void setUp(){
       String wordsPath = "C:\\Users\\Valera\\IdeaProjects\\WordProcessing\\src\\test\\resources\\words.txt";
       String textPath = "C:\\Users\\Valera\\IdeaProjects\\WordProcessing\\src\\test\\resources\\text.txt";
       controller = new Controller(textPath, wordsPath);
   }

    @DataProvider
    public Object[][] data() {
        return new Object[][] { { "leaders", 2, 1, 1, 0}, {"world", 1, 1, 0, 0}, {"french", 1, 1, 0, 0},
                { "donald", 2, 0, 1, 1}, {"president", 3, 1, 2, 0}, {"patriotism", 1, 0, 1, 0}, {"duck", 2, 0, 0, 2} };
    }

    @Test(dataProvider = "data")
    public void testGetWordOccurrences(String word, int occurencesInText, int occurencesInFirstSent,
                                       int occurencesInSecondDent, int occurencesInThirdSent) {

        List<WordOccurrence> listWordOccurrence = controller.getWordOccurrences();
        Assert.assertTrue(listWordOccurrence.size() == 7);
        int[] expectedCount = {occurencesInFirstSent, occurencesInSecondDent, occurencesInThirdSent};
        listWordOccurrence.stream().forEach(wordOccurrence -> {
            if (wordOccurrence.getWord().getText().equals(word)){
                Assert.assertTrue(wordOccurrence.getOccurenceInText() == occurencesInText);
                List<Long> occurencesInSentence =  wordOccurrence.getOccurencesInSentences();
                Assert.assertTrue(occurencesInSentence.size() == 3);
                for (int i = 0; i < occurencesInSentence.size(); i++){

                    Assert.assertTrue(expectedCount[i] == occurencesInSentence.get(i));
                }
            }
        });
    }

    @Test
    public void testSordWordOccurrences() {
        List<WordOccurrence> listWordOccurrence = controller.getWordOccurrences();
        controller.sordWordOccurrences();
        for (int i = 1; i < listWordOccurrence.size(); i++){
            int expectedHigherOccurences = listWordOccurrence.get(i - 1).getOccurenceInText();
            int expectedLowerOccurences = listWordOccurrence.get(i).getOccurenceInText();
            Assert.assertTrue(expectedHigherOccurences >= expectedLowerOccurences);
            if (expectedHigherOccurences == expectedLowerOccurences){
                int compareString = listWordOccurrence.get(i - 1).getWord().getText().compareTo(listWordOccurrence.get(i).getWord().getText());
                Assert.assertTrue(compareString < 0);
            }
        }
    }

    @DataProvider
    public Object[][] data1() {

        List<String> firstList = new ArrayList<>();
        firstList.add("leaders");
        firstList.add("donald");
        firstList.add("president");
        firstList.add("duck");
        firstList.add("world");

        List<String> secondList = new ArrayList<>();
        secondList.add("world");
        secondList.add("french");
        secondList.add("patriotism");
        secondList.add("duck");
        secondList.add("leaders");
        secondList.add("donald");

        return new Object[][] { { 2, 3, firstList}, {1, 2, secondList} };
    }

    @Test(dataProvider = "data1")
    public void testFilterWordPerOccurencies(int minOcc, int maxOcc, List<String> expectedList) {

       controller.filterWordPerOccurencies(minOcc,maxOcc).stream().forEach(wordOccurrence ->
           Assert.assertTrue(expectedList.contains(wordOccurrence.getWord().getText().trim()))
       );
       Assert.assertEquals(expectedList.size(), controller.filterWordPerOccurencies(minOcc,maxOcc).size());
    }
}