import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class CalculateWordScoresTest {

    @Test
    public void testCalculateWordScores_basic() {
        Set<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(2, "I hate dog but I love cat ."));
        sentences.add(new Sentence(1, "I hope my cat like me ."));

        Map<String, Double> wordScores = Analyzer.calculateWordScores(sentences);

        assertEquals(1.5, wordScores.get("cat"));
        assertEquals(2.0, wordScores.get("dog"));
        assertEquals(1.666, wordScores.get("i"), 0.001);
        assertEquals(10, wordScores.size()); // check for correct number of keys
    }

    @Test
    public void testCalculateWordScores_emptySet() {
        Set<Sentence> sentences = new HashSet<>();
        Map<String, Double> wordScores = Analyzer.calculateWordScores(sentences);

        assertTrue(wordScores.isEmpty());
    }

    @Test
    public void testCalculateWordScores_nullSet() {
        Map<String, Double> wordScores = Analyzer.calculateWordScores(null);

        assertTrue(wordScores.isEmpty());
    }

    @Test
    public void testCalculateWordScores_caseInsensitive() {
        Set<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(2, "Cat is good ."));
        sentences.add(new Sentence(1, "I love cat ."));

        Map<String, Double> wordScores = Analyzer.calculateWordScores(sentences);

        assertEquals(1.5, wordScores.get("cat"));
        assertEquals(5, wordScores.size());
    }

    @Test
    public void testCalculateWordScores_ignoreNonLetters() {
        Set<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(2, "I like cats !"));
        sentences.add(new Sentence(1, "Cats are awesome ?"));

        Map<String, Double> wordScores = Analyzer.calculateWordScores(sentences);

        assertEquals(1.5, wordScores.get("cats"));
        assertEquals(5, wordScores.size());
    }
}
