package com.test.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordOperations {
	public static HashMap<String, Integer> countMostFrequentWords(String[] words) {
		HashMap<String, Integer> wordCount = new HashMap<String, Integer>();

		// Count the frequency of each word
		for (String word : words) {
			if (wordCount.containsKey(word)) {
				wordCount.put(word, wordCount.get(word) + 1);
			} else {
				wordCount.put(word, 1);
			}
		}

		return wordCount;
	}

	public static String[] readAndMapTextFromUrl(URL inputURL, String text)
			throws UnsupportedEncodingException, IOException {
		// Read the text file
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputURL.openStream(), "UTF-8"));
		String line;
		while ((line = reader.readLine()) != null) {
			text += line + " ";
		}
		reader.close();

		// Remove punctuation and convert to lowercase
		text.replaceAll("[^a-zA-Z ]", "")
				.replaceAll("[^a-zA-Z0-9\\s+]", "")
				.toLowerCase();

		// Split the text into an array of words
		String[] words = text.split(" ");
		
		return words;
	}

	public static HashMap<String, Integer> getMostUsedWords(HashMap<String, Integer> wordCount) {
		List<Map.Entry<String, Integer>> list = sortMap(wordCount);

		// Create a new HashMap to store the top 25 most common words
		HashMap<String, Integer> topWords = new HashMap<>();
		int count = 0;
		for (Map.Entry<String, Integer> entry : list) {
			if (count == 50) {
				break;
			}
			topWords.put(entry.getKey(), entry.getValue());
			count++;
		}
		return topWords;
	}

	public static List<Map.Entry<String, Integer>> sortMap(HashMap<String, Integer> wordCount) {
		// Sort the HashMap by value in descending order
		List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCount.entrySet());
		list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
		return list;
	}

	public static Map<String, Integer> removeCommonWords(Map<String, Integer> sortedMap) {
		// Create a set of common words
		Set<String> commonWords = new HashSet<>(Arrays.asList("the", "of", "to", "and", "a", "in", "is", "it", "you",
				"that", "he", "was", "for", "on", "are", "with", "as", "I", "his", "they", "be", "at", "one", "have",
				"this", "from", "or", "had", "by", "not", "word", "but", "what", "some", "we", "can", "out", "other",
				"were", "all", "there", "when", "up", "use", "your", "how", "said", "an", "each", "she"));

		// Remove entries with keys that match the common words
		sortedMap.entrySet().removeIf(entry -> commonWords.contains(entry.getKey()));

		return sortedMap;
	}
}
