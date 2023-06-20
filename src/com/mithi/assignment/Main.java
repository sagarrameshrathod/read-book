package com.mithi.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		String[] bookPages = {
				readFile("Page1.txt"),
				readFile("Page2.txt"),
				readFile("Page3.txt")
		};

		String excludeWordsFile = "exclude-words.txt";
		String outputFile = "index.txt";

		BookIndex bookIndex = new BookIndex();
		bookIndex.loadExcludeWords(excludeWordsFile);
		bookIndex.indexBookPages(bookPages);
		bookIndex.saveIndexToFile(outputFile);

		System.out.println("Indexing completed. Index saved to " + outputFile);
	}

	private static String readFile(String filePath) {
		StringBuilder content = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append(" ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}
}
