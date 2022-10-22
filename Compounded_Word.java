import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;



public class LongestCompoundWord {

	public static void main(String[] args)  {
		
		
		File file = new File("w1.txt");
		Trie tr = new Trie();
		LinkedList<Pair<String>> lk = new LinkedList<Pair<String>>();
		
	
		HashSet<String> cW = new HashSet<String>();
		
	
		Scanner sc = new Scanner(file);

		String wd;				
		List<Integer> sufIndices;	
	
		while (sc.hasNext()) {
			wd = sc.next();		
			sufIndices = tr.getSuffixesStartIndices(wd);
		
			for (int i : sufIndices) {
				if (i >= wd.length())		
					break;					
										
				lk.add(new Pair<String>(wd, wd.substring(i)));
			}
	
			tr.insert(wd);
		}
		
		Pair<String> p;			
		int maxLength = 0;			
			
		String longest = "";	
		String sec_longest = "";	

		while (!lk.isEmpty()) {
			p = lk.removeFirst();
			wd = p.second();
			
			sufIndices = tr.getSuffixesStartIndices(wd);
			
		
			if (sufIndices.isEmpty()) {
				continue;
			}
			
			
			for (int i : sufIndices) {
				if (i > wd.length()) { 
					break;
				}
				
				if (i == wd.length()) { 
					if (p.first().length() > maxLength) {
					
						sec_longest = longest;
						maxLength = p.first().length();
						longest = p.first();
					}
			
					cW.add(p.first());	
					
				} else {
					lk.add(new Pair<String>(p.first(), wd.substring(i)));
				}
			}
		}
	
		System.out.println("Longest Compound Word: " + longest);
		System.out.println("Second Longest Compound Word: " + sec_longest);
		System.out.println("Total Number of Compound Words: " + cW.size());
	}
}
