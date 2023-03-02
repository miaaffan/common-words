package com.test.io;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CommonWordsApplication {
	public static void main(String[] args) throws Exception {
		URL inputURL = new URL("https://www.gutenberg.org/files/2701/2701-0.txt");
		String text = "";

		String[] words = WordOperations.readAndMapTextFromUrl(inputURL, text);

		HashMap<String, Integer> wordCount = WordOperations.countMostFrequentWords(words);
		
		// 2. get 50 most used words
		HashMap<String, Integer> topWords = WordOperations.getMostUsedWords(wordCount);

		// 3/ remove commonly used words List
		Map<String, Integer> updatedWordsMap = WordOperations.removeCommonWords(topWords);

		// Print the top most common words and their frequencies
		for (Map.Entry<String, Integer> entry : updatedWordsMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

}
