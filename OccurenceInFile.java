package com.example.MovieRating;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OccurenceInFile {
	
	public static void main(String args[]) throws IOException {
	
	List<Integer> numList = new ArrayList<Integer>();
	numList.add(5);
	numList.add(10);
	numList.add(90);
	numList.add(100);

	Path path = Paths.get("test.txt");

	String ocurWord = "hi";
	int counter = 0;
	List<String> wordList = new ArrayList<String>();
	
	wordList = addWords(path);
	
	Map<String, Integer> occMap = new HashMap<String, Integer>();
	
	try {

		for (String tempWord : wordList) {

			List<String> tempList = getListWithgivenWord(path, tempWord);
			occMap.put(tempWord, (int) tempList.stream().count());

		}

		if (occMap != null) {

			for (Entry<String, Integer> map : occMap.entrySet()) {

				System.out.println("-----Key=" + map.getKey() + "------value=" + map.getValue());

			}

		}
		
		
		maxOcuurence(occMap);
		nthOcuurence(occMap,12);

	} catch (IOException e) {
		e.printStackTrace();
	}
}
	
	
	static void maxOcuurence(Map<String,Integer> map) {
		
		int max=0;
		for(Entry<String,Integer> entry: map.entrySet()) {
			
			if(max<=entry.getValue()) {
				max=entry.getValue();
			}
		}
		
		for(Entry<String,Integer> entry: map.entrySet()) {
			if(max==entry.getValue()) {
				System.out.println("Maximum Entry is for the word ---"+entry.getKey()+"--And The count is--"+entry.getValue());
			}
		}
		
	}
	
   static void nthOcuurence(Map<String,Integer> map,int n) {
		
		
		int count=0;
		for(Entry<String,Integer> entry: map.entrySet()) {
			if(n==entry.getValue()) {
				count=1;
				System.out.println("Nth Entry is for the word ---"+entry.getKey()+"--And The count is--"+entry.getValue());
				break;
			}
		}
		
		if(count==0) {
			System.out.println("No Nth Entry Found");
		}
		
	}

   

	static List<String> getListWithgivenWord(Path path, String word) throws IOException {

		List<String> tempList = new ArrayList<String>();

		Files.lines(path, Charset.forName("UTF-8")).forEach(
				line -> Arrays.stream(line.split(" ")).filter(s -> s.contains(word)).forEach(l -> tempList.add(l)));

		return tempList;
	}
	static List<String> addWords(Path path) throws IOException {

		List<String> wordList = new ArrayList<String>();
		Files.lines(path, Charset.forName("UTF-8"))
				.forEach(line -> Arrays.stream(line.split(" ")).map(word -> word).forEach(word -> wordList.add(word)));
		return wordList;
	}
}