import java.util.*;

public class Analyzer {


	/**
	 * This method calculates the weighted average for each word in all the Sentences.
	 * This method is case-insensitive and all words should be stored in the Map using
	 * only lowercase letters.
	 *
	 * @param sentences Set containing Sentence objects with words to score
	 * @return Map of each word to its weighted average; or an empty Map if the Set of
	 * Sentences is empty or null.
	 */
	public static Map<String, Double> calculateWordScores(Set<Sentence> sentences) {
		/*
		 * Implement this method in Part 2
		 */
		if (sentences == null || sentences.isEmpty()) {
			return new HashMap<>();
		}

		Map<String, Integer> wordCounts = new HashMap<>();
		Map<String, Integer> wordScores = new HashMap<>();

		for (Sentence sentence : sentences) {
			String text = sentence.getText();
			int score = sentence.getScore();

			if (text == null || text.isEmpty()) {
				continue;
			}

			StringTokenizer tokenizer = new StringTokenizer(text);
			while (tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken().toLowerCase();
				if (!Character.isLetter(token.charAt(0))) {
					continue;
				}

				wordCounts.put(token, wordCounts.getOrDefault(token, 0) + 1);
				wordScores.put(token, wordScores.getOrDefault(token, 0) + score);
			}
		}

		Map<String, Double> wordAverages = new HashMap<>();
		for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
			String word = entry.getKey();
			int count = entry.getValue();
			int totalScore = wordScores.get(word);
			double averageScore = (double) totalScore / count;
			wordAverages.put(word, averageScore);
		}

		return wordAverages;
	}


	/**
	 * This method determines the sentiment of the input sentence using the average of the
	 * scores of the individual words, as stored in the Map.
	 * This method is case-insensitive and all words in the input sentence should be
	 * converted to lowercase before searching for them in the Map.
	 *
	 * @param wordScores Map of words to their weighted averages
	 * @param sentence Text for which the method calculates the sentiment
	 * @return Weighted average scores of all words in input sentence; or 0 if any error occurs
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		/*
		 * Implement this method in Part 3
		 */
		if (wordScores == null || wordScores.isEmpty() || sentence == null || sentence.isEmpty()) {
			return 0;
		}

		StringTokenizer tokenizer = new StringTokenizer(sentence);
		double totalScore = 0;
		int wordCount = 0;

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken().toLowerCase();
			if (!Character.isLetter(token.charAt(0))) {
				continue;
			}

			double wordScore = wordScores.getOrDefault(token, 0.0);
			totalScore += wordScore;
			wordCount++;
		}

		return wordCount == 0 ? 0 : totalScore / wordCount;
	}

}
