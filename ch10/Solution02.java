package ch10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

/*
 * Method 1: Using comparator
 * We can check if two strings are anagrams or not by checking
 * if the sorted order of their characters is equivalent. This 
 * can be done using a comparator.
 * Time complexity: O(nlogn * mlogm) because we sort each entry in the
 * 					String array and we traverse through the entire
 * 					array, finally sorting it; where m is the average 
 * 					size of a string in the array
 * Space complexity: O(n*m)
 */

class AnagramComparator implements Comparator<String> {
	public String sortChars(String s) {
		char[] content = s.toCharArray();
		Arrays.sort(content);
		return new String(content);
	}
	
    public int compare(String s1, String s2) {
    	return sortChars(s1).compareTo(sortChars(s2));
    }
}

public class Solution02 {
	
	/*
	 * Method 2: Using HashMap
	 * Another method can be to create a HashMap which
	 * maps from the sorted version of a word to a list of its anagrams.
	 * Once all Strings from the array have been put into their respective
	 * buckets, the HashMap can be read again to insert the words back into
	 * the array. This method saves us the trouble of sorting the entire
	 * String array.
	 * Time complexity: O(n * mlogm)
	 * Space complexity: O(nm) 
	 */
	
	public static String[] groupAnagramsHashMap(String[] array){
		if(array == null || array.length == 1)		// if array is null or contains only
			return array;							// one element
		
		HashMap<String, LinkedList<String>> hmap = new HashMap<String, LinkedList<String>>();	// create an empty hash map
		
		for(String st:array){		// read all strings in array and put them into buckets in hashmap
			char[] ch = st.toCharArray();
			Arrays.sort(ch);
			String key = new String(ch);
			
			if(!hmap.containsKey(key)){
				hmap.put(key, new LinkedList<String>());
			}
			hmap.get(key).add(st);
		}
		
		int indexToInsertAt = 0;
		
		for(String key:hmap.keySet()){		// put the anagrams grouped together into array
			LinkedList<String> temp = hmap.get(key);
			for(String elems:temp){
				array[indexToInsertAt++] = elems;
			}
		}
		
		return array;
	}
	
	public static void main(String[] args) {
		String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
		System.out.println("Original array:" + Arrays.toString(array));
		System.out.println("Using hashmap: " + Arrays.toString(groupAnagramsHashMap(array)));
		Arrays.sort(array, new AnagramComparator());
		System.out.println("Using comparator: " + Arrays.toString(array));
	}

}
