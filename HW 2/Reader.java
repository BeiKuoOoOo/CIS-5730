import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.io.FileReader;


public class Reader {
	/**
	 * This method reads sentences from the input file, creates a Sentence object
	 * for each, and returns a Set of the Sentences.
	 * 
	 * @param filename Name of the input file to be read
	 * @return Set containing one Sentence object per sentence in the input file
	 * @throws IllegalArgumentException if filename is null
	 */
	public static Set<Sentence> readFile(String filename) {
		if (filename == null) {
			throw new IllegalArgumentException("Filename cannot be null");
		}

		Set<Sentence> sentences = new HashSet<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(" ", 2);
				if (parts.length != 2) {
					continue; // Ignore lines without a space
				}

				try {
					int score = Integer.parseInt(parts[0]);
					if (score < -2 || score > 2) {
						continue; // Ignore scores out of range
					}
					String text = parts[1];
					sentences.add(new Sentence(score, text));
				} catch (NumberFormatException e) {
					// Ignore lines where the score is not a valid integer
				}
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot open file for reading", e);
		}

		return sentences;
	}
}
