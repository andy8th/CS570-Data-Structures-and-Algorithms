package hw6;


import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 
 * @author JinceShi
 *
 */
public class Anagrams {

	/*
	 * array of first 26 primes.
	 */
	
	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
	Map<Character, Integer> letterTable;
	Map<Long, ArrayList<String>> anagramTable;

	/*
	 * Constructor initializes class variables
	 */
	
	public Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long, ArrayList<String>>();
	}

	/*
	 * Constructs letterTable for hashing
	 */
	private void buildLetterTable() {
		letterTable = new HashMap<Character, Integer>();
		for (char start = 'a'; start <= 'z'; start++) {
			letterTable.put(start, primes[Character.getNumericValue(start)-10]);
		}
	}

	/*
	 * compute hash code and add word
	 * @param s add a word
	 * @throws IllegalArgumentException
	 */
	
	 private void addWord(String s) throws IllegalArgumentException {	
			if(s == null || s.length() == 0) 
				throw new IllegalArgumentException();
			Long hCode = myHashCode(s);
			if(anagramTable.containsKey(hCode))
				anagramTable.get(hCode).add(s);
			else {
				ArrayList<String> b = new ArrayList<String>();
				b.add(s);
				anagramTable.put(hCode, b);
			}
	}

	/*
	 * Generates hashcode for a string 
	 * @param string to generate hashcode
	 * @return value of hashcode
	 */
	 private Long myHashCode(String s) throws IllegalArgumentException {
			long hCode=1;
			try {
			for(int j=0; j<s.length(); j++) 
				hCode= hCode*(long)letterTable.get(s.charAt(j));
			}
			catch(NullPointerException e) {
				throw new IllegalArgumentException("Lowercase letters only are allowed in the string");
			}
			return hCode;
		}

	/*
	 * Processes the file in the folder
	 * @param string name to be parsed
	 * @throws IOException
	 */
	private void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close();
	}

	/*
	 *Gets the largest number of anagram
	 *@return the map of the key and the list
     */ 
	private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {
		int size = 0;
		ArrayList<Map.Entry<Long,ArrayList<String>>> mList = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		for (Map.Entry<Long,ArrayList<String>> pair : anagramTable.entrySet()) {
			int entrySize = pair.getValue().size();
			if(entrySize > size) {
				mList.clear();
				mList.add(pair);
				size = entrySize;
			} else if(entrySize == size) {
				mList.add(pair);
			}
		}
		return mList;
	}

	/*
	 *  main statement to test this code
	 *  @param args
	 */
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams();
		final long startTime = System.nanoTime();
		try {
			a.processFile ("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries ();
		long key = maxEntries.get(0).getKey();
		int length = maxEntries.get(0).getValue().size();
		final long estimatedTime = System.nanoTime() - startTime ;
		final double seconds = ((double)estimatedTime/1000000000);
		System.out.println("Elapsed Time : "+ seconds);
		System.out.println("Key of max anagrams: "+ key);
		System.out.println("List of max anagrams: " + maxEntries.get(0).getValue());
		System.out.println("Length of list of max anagrams : "+ length);
	}
}