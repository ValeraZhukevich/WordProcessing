package parcer;

import edu.stanford.nlp.simple.Document;
import model.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parcer {

    TextUnit compositeText = new Text();

    public TextUnit parceFile2TextUnit(String filePath){

        composeText(readFile(filePath));
        return compositeText;
    }

    private void composeText(String text){

        String[] paragraphsText = text.split("[\r\n]+");
        for (String paragraphText : paragraphsText){
            TextUnit paragraph = parce2Paragraphs(paragraphText);
            compositeText.addTextUnit(paragraph);
        }
    }

    private Paragraph parce2Paragraphs(String paragraphText){
        Paragraph paragraph = new Paragraph();
        Document documentNLP = new Document(paragraphText);
        for (edu.stanford.nlp.simple.Sentence sentenceText : documentNLP.sentences()){
            paragraph.addTextUnit(parce2Sentences(sentenceText.toString()));
        }
        return paragraph;
    }

    private Sentence parce2Sentences(String sentenceText){
        Sentence sentence = new Sentence();
        String[] partsOfsentenceText = sentenceText.split(" ");
        for (String partOfsentenceText : partsOfsentenceText){
            parce2WordAndPunctuationMark(partOfsentenceText, sentence);
        }
        return sentence;
    }

    private void parce2WordAndPunctuationMark(String partOfSentance, Sentence sentence){

        boolean containsWord = false;

        Pattern patternWord = Pattern.compile("\\W*\\w+\\W*");
        Matcher matcherWord = patternWord.matcher(partOfSentance);
        if (matcherWord.find()){
            containsWord = true;
        }

        if (containsWord){
            Pattern pattern = Pattern.compile("(^\\W*)((\\w\\S*\\w)|(\\w+))(\\W*$)");
            Matcher matcher = pattern.matcher(partOfSentance);
            if(matcher.find()){
                if (!matcher.group(1).equals("")){
                    sentence.addTextUnit(new PunctuationMark(parce2Symbols(matcher.group(1)),false));
                }

                boolean punctuationMarkAfterWord = false;
                if (!matcher.group(5).equals("")){
                    punctuationMarkAfterWord = true;
                }
                sentence.addTextUnit(new Word(parce2Symbols(matcher.group(2)), punctuationMarkAfterWord));
                if (punctuationMarkAfterWord){
                    sentence.addTextUnit(new PunctuationMark(parce2Symbols(matcher.group(5)),true));
                }
            }
        } else{
            Pattern patternPunctuationMark = Pattern.compile("\\W+");
            Matcher matcherPunctuationMark = patternPunctuationMark.matcher(partOfSentance);
            if (matcherPunctuationMark.find()){
                sentence.addTextUnit(new PunctuationMark(parce2Symbols(matcherPunctuationMark.group()), true));
            }
        }
    }

    private List<Symbol> parce2Symbols(String word){
        List<Symbol> symbols = new ArrayList<Symbol>();

        for (int i = 0; i < word.length(); i++){
            symbols.add(new Symbol(word.charAt(i)));
        }
        return symbols;
    }

    public void write2File(String fileName){

        Path path = Paths.get(fileName);

        try(BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("windows-1251"))){
            writer.write(compositeText.getText());
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private String readFile(String filePath){

        Path path = Paths.get(filePath);
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = Files.newBufferedReader(path, Charset.forName("windows-1251"))){
            String line;
            while ((line = reader.readLine()) != null){
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return sb.toString();
    }

    public Set<TextUnit> getListOfWords(String filePath){
        String text = readFile(filePath);
        Set<TextUnit> setOfWords = new HashSet<>();
        Pattern pattern = Pattern.compile("(^\\W*)((\\w\\S*\\w)|(\\w+))(\\W*$)");
        String[] words = text.split("[\\s\\r\\n]");
        for (String stringWord : words){
            Matcher matcherWord = pattern.matcher(stringWord);
            if (matcherWord.find()){
                if (!matcherWord.group(2).equals("")){
                    TextUnit word = new Word(parce2Symbols(matcherWord.group(2).toLowerCase()), false);
                    setOfWords.add(word);
                }
            }
        }
        return setOfWords;
    }
}
