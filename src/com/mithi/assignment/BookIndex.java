package com.mithi.assignment;

import java.io.*;
import java.util.*;

class BookIndex {
	private Set<String> excludeWords;
	private Map<String, Set<Integer>> index;

	public BookIndex() {
		excludeWords = new HashSet<>();
		index = new TreeMap<>();
	}

	public void loadExcludeWords(String excludeWordsFile) {
		try (BufferedReader reader = new BufferedReader(new FileReader(excludeWordsFile))) {
			String word;
			while ((word = reader.readLine()) != null) {
				excludeWords.add(word.toLowerCase());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void indexBookPages(String[] bookPages) {
		for (int i = 0; i < bookPages.length; i++) {
			String page = bookPages[i];
			String[] words = page.toLowerCase().split("\\W+");

			for (String word : words) {
				if (!excludeWords.contains(word)) {
					Set<Integer> pages = index.getOrDefault(word, new HashSet<>());
					pages.add(i + 1); 
					index.put(word, pages);
				}
			}
		}
	}

	public void saveIndexToFile(String outputFile) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
			writer.println("Word : Page Numbers");
			writer.println("-------------------");

			for (Map.Entry<String, Set<Integer>> entry : index.entrySet()) {
				String word = entry.getKey();
				Set<Integer> pages = entry.getValue();
				StringBuilder sb = new StringBuilder();
				for (int page : pages) {
					sb.append(page).append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
				writer.println(word + " : " + sb.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
