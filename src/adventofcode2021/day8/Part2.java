package adventofcode2021.day8;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Part2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day8.txt")));
		
		long count = 0;
		while(in.hasNextLine()) {
			String s = in.nextLine();
			String[] split = s.split("\\|");
			String[] split3 = split[0].split(" ");
			
			int one = 0,four = 0,seven = 0,eight = 0;
			int[] fiveLong = {-1,-1,-1};
			for(int i = 0; i < split3.length; i++) {
				String s1 = split3[i];
				if(s1.length() == 2) {
					one = i;
				}else if(s1.length() == 3) {
					seven = i;
				}else if(s1.length() == 4) {
					four = i;
				}else if(s1.length() == 7) {
					eight = i;
				}else if(s1.length() == 5) {
					for(int i1 = 0; i1 < fiveLong.length; i1++) {
						if(fiveLong[i1] != -1)
							continue;
						fiveLong[i1] = i;
						break;
					}
				}
			}
			Set<Character> fourDiff = findDifference(split3[one], split3[four]);
			char A = findDifference(split3[one], split3[seven]).iterator().next();
			
			Set<Character> diff1 = findDifference(split3[fiveLong[0]], split3[fiveLong[1]]);
			Set<Character> diff2 = findDifference(split3[fiveLong[1]], split3[fiveLong[2]]);
			Set<Character> diff3 = findDifference(split3[fiveLong[0]], split3[fiveLong[2]]);
			
			Map<Character,Integer> difference = new HashMap<Character,Integer>();
			for(char c = 'a'; c <= 'z'; c++) {
				difference.put(c, 0);
			}
			
			int three = 0;
			if(diff1.size() == 4)
				three = fiveLong[2];
			if(diff2.size() == 4)
				three = fiveLong[0];
			if(diff3.size() == 4)
				three = fiveLong[1];
			int[] possiblyFive = new int[2];
			int index = 0;
			for(int i : fiveLong) {
				if(i == three)
					continue;
				possiblyFive[index] = i;
				index++;
			}
			int[] counts = new int[26];
			for(int i1 : fiveLong) {
				for(char c1 : split3[i1].toCharArray())
					counts[c1-'a']++;
			}
			char F = 0;
			int five = 0;
			for(int i = 0; i < counts.length; i++) {
				if(counts[i] == 2) {
					for(int i1 = 0; i1 < fiveLong.length; i1++) {
						String s1 = split3[fiveLong[i1]];
						char c1 = (char) ('a'+i);
						if(!s1.contains(""+c1))
							continue;
						boolean found = true;
						for(char c : fourDiff) {
							if(s1.contains(""+c) && found) {
								found = true;
							}else {
								found = false;
							}
						}
						if(found) {
							F = (char) ('a'+i);
							five = fiveLong[i1];
							break;
						}
					}
				}
			}
			int two = (possiblyFive[0] == five ? possiblyFive[1] : possiblyFive[0]);
			char E = 0;
			for(int i = 0; i < counts.length; i++) {
				if(counts[i] == 1) {
					// Possible E
					if(split3[two].contains(""+((char) ('a'+i)))) {
						E = (char) ('a' + i);
						break;
					}
				}
			}
			char[] BD = {0,0};
			Set<Character> BDDiff = findDifference(split3[four], split3[one]);
			
			for(Character c : BDDiff) {
				if(BD[0] != 0)
					BD[1] = c;
				else
					BD[0] = c;
			}
			String BDS = BD[0]+""+BD[1];
			Set<Character> DS = findDifference(BDS, split3[two]);
			char B = DS.iterator().next();
			char D = (BD[0] == B ? BD[1] : BD[0]);
			Set<Character> CPossible = singleDifference(split3[three], split3[five]);
			char C = 0;
			for(Character c : CPossible) {
				if(c != B)
					C = c;
			}
			
			String[] split2 = split[1].trim().split(" ");
			
			char G = findDifference(A+""+B+""+C+""+D+""+E+""+F, split3[eight]).iterator().next();
			char[] mapping = {A,C,B,D,F,E,G};
			long output = 0;
			for(String s1 : split2) {
				int value = getNumber(mapping, s1);
				output *= 10;
				output += value;
			}
			System.out.println(output);
			count += output;
		}
		
		in.close();
		System.out.println("Answer: "+count);
	}
	private static int getValue(char c) {
		if(c == 'a') {
			return 0;
		}else if(c == 'b') {
			return 2;
		}else if(c == 'c') {
			return 1;
		}else if(c == 'd') {
			return 3;
		}else if(c == 'e') {
			return 5;
		}else if(c == 'f') {
			return 4;
		}else if(c == 'g') {
			return 6;
		}
		return 0;
	}
	private static Set<Character> singleDifference(String s1, String s2) {
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		Set<Character> diff = new LinkedHashSet<Character>();
		for(char c : c1) {
			boolean found = false;
			for(char c3 : c2) {
				if(c3 == c) {
					found = true;
					break;
				}
			}
			if(!found)
				diff.add(c);
		}
		return diff;
	}
	private static Set<Character> findDifference(String s1, String s2) {
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		Set<Character> diff = new LinkedHashSet<Character>();
		for(char c : c1) {
			boolean found = false;
			for(char c3 : c2) {
				if(c3 == c) {
					found = true;
					break;
				}
			}
			if(!found)
				diff.add(c);
		}
		for(char c : c2) {
			boolean found = false;
			for(char c3 : c1) {
				if(c3 == c) {
					found = true;
					break;
				}
			}
			if(!found)
				diff.add(c);
		}
		return diff;
	}
	private static int getNumber(char[] mappings, String input) {
		if(input.length() == 3)
			return 7;
		if(input.length() == 2)
			return 1;
		if(input.length() == 7)
			return 8;
		if(input.length() == 4)
			return 4;
		if(input.length() == 6) {
			if(input.contains(""+mappings[getValue('e')]) && !input.contains(""+mappings[getValue('c')])) {
				return 6;
			}else if(!input.contains(""+mappings[getValue('d')])){
				return 0;
			}else if(input.contains(""+mappings[getValue('f')])){
				return 9;
			}
		}
		if(input.length() == 5) {
			if(input.contains(""+mappings[getValue('b')])) {
				return 5;
			}else {
				if(input.contains(""+mappings[getValue('e')])) {
					return 2;
				}else if(input.contains(""+mappings[getValue('f')])){
					return 3;
				}
			}
		}
		return -1;
	}
}
