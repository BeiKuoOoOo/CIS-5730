import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * Main class for the sentiment analysis program.
 * 
 */
public class Main {
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("no input file");
			return;
		}

		String filename = args[0];

		Set<Sentence> sentences;
		try {
			sentences = Reader.readFile(filename);
		} catch (IllegalArgumentException e) {
			System.out.println("bad input file");
			return;
		}

		Map<String, Double> wordScores = Analyzer.calculateWordScores(sentences);

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Enter a sentence: ");
			String userInput = scanner.nextLine();

			if (userInput.equals("quit")) {
				break;
			}

			double sentenceScore = Analyzer.calculateSentenceScore(wordScores, userInput);
			System.out.println("Sentence score: " + sentenceScore);
		}
		scanner.close();
	}
}
