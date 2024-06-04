import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class CalculateSentenceScoreTest {

    @Test
    public void testCalculateSentenceScore_basic() {
        Map<String, Double> wordScores = new HashMap<>();
        wordScores.put("cats", 2.0);
        wordScores.put("are", 1.0);
        wordScores.put("cute", 3.0);

        String sentence = "Cats are cute";
        double score = Analyzer.calculateSentenceScore(wordScores, sentence);

        assertEquals(2.0, score, 0.001);
    }

    @Test
    public void testCalculateSentenceScore_ignoreNonLetters() {
        Map<String, Double> wordScores = new HashMap<>();
        wordScores.put("dogs", 2.0);
        wordScores.put("are", 1.0);

        String sentence = "Dogs are ?smart";
        double score = Analyzer.calculateSentenceScore(wordScores, sentence);

        assertEquals(1.5, score, 0.001);
    }

    @Test
    public void testCalculateSentenceScore_wordNotInMap() {
        Map<String, Double> wordScores = new HashMap<>();
        wordScores.put("cats", 2.0);
        wordScores.put("are", 1.0);

        String sentence = "Cats are funny";
        double score = Analyzer.calculateSentenceScore(wordScores, sentence);

        assertEquals(1.0, score, 0.001);
    }

    @Test
    public void testCalculateSentenceScore_emptySentence() {
        Map<String, Double> wordScores = new HashMap<>();
        wordScores.put("cats", 2.0);
        wordScores.put("are", 1.0);

        String sentence = "";
        double score = Analyzer.calculateSentenceScore(wordScores, sentence);

        assertEquals(0.0, score, 0.001);
    }

    @Test
    public void testCalculateSentenceScore_nullSentence() {
        Map<String, Double> wordScores = new HashMap<>();
        wordScores.put("cats", 2.0);
        wordScores.put("are", 1.0);

        double score = Analyzer.calculateSentenceScore(wordScores, null);

        assertEquals(0.0, score, 0.001);
    }
}
