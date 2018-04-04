package files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordsSorter {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		
		//read the file
		File file = new File("war_peace.txt");
		
		//get all words from the file
		try(Scanner sc = new Scanner(file, "UTF-8")) {
			
			TreeMap<Integer, TreeSet<String>> allWords = new TreeMap<>();
			
			//put each word in corresponding collection based on its length
			while(sc.hasNextLine()) {
				
				String line = sc.nextLine();
				line = line.replaceAll("[^a-zA-Z`\\s+]", " ");
				String[] words = line.split("\\s+");
				for(String word : words) {
					if(!allWords.containsKey(word.length())) {
						allWords.put(word.length(), new TreeSet<>());
					}
					allWords.get(word.length()).add(word);
				}
			}
			
			//for each existing length create a file
			for(Map.Entry<Integer, TreeSet<String>> e : allWords.entrySet()) {
				File f = new File(e.getKey() + "_letters.txt");
				f.createNewFile();
				//put all words with this length in the corresponding file
				PrintStream ps = new PrintStream(f);
				try {
					for(String word : e.getValue()) {
						ps.println(word);
					}
				}
				finally {
					ps.close();
				}
			}
		}
		catch(IOException e) {
			System.out.println("error : " + e.getMessage());
		}
		long duration = System.currentTimeMillis() - start;
		System.out.println(duration);
	}
}
