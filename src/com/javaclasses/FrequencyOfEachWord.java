package com.javaclasses;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrequencyOfEachWord {

	HashMap<String, Integer> mapping = new HashMap<String, Integer>();

	public static void main(String[] args) {

		FrequencyOfEachWord project = new FrequencyOfEachWord();

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("Text_File/Protein.txt")).useDelimiter(" ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner INPUT_TEXT = scanner;
		project.analyse(INPUT_TEXT); 
		project.showResults();
	}

	public void analyse(Scanner scanner) {

		String pattern = "[a-zA-Z'-]+";
		Pattern reg = Pattern.compile(pattern);

		while (scanner.hasNext()) {
			// read next word
			String Stringcandidate = scanner.next();

			// see if pattern matches (boolean find)
			Matcher matcher = reg.matcher(Stringcandidate);
			if (matcher.find()) {
				String matchedWord = matcher.group();

				if (mapping.containsKey(matchedWord)) {
					// increment occurrence
					int occurrence = mapping.get(matchedWord);
					occurrence++;
					mapping.put(matchedWord, occurrence);
				} else {
					// add word and set occurrence to 1
					mapping.put(matchedWord, 1);
				}
			}
		}
		scanner.close();
	}

	public void showResults() {
		System.out.println(String.format("%30s %25s  %25s", "Words", "|", "Frequency", "|"));
		System.out.println(String.format("%s",
				"----------------------------------------------------------------------------------------------------------------"));
		for (HashMap.Entry<String, Integer> matchedWord : mapping.entrySet()) {
			int occurrence = matchedWord.getValue();
			System.out.println(String.format("%30s %25s  %25s", matchedWord.getKey(), "|", occurrence, "|"));
		}
	}
}
