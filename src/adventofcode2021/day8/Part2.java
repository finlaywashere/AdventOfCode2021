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
		
		int count = 0;
		
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
			char A = findDifference(split3[one], split3[seven]).iterator().next();
			
			Set<Character> diff1 = findDifference(split3[fiveLong[0]], split3[fiveLong[1]]);
			Set<Character> diff2 = findDifference(split3[fiveLong[1]], split3[fiveLong[2]]);
			Set<Character> diff3 = findDifference(split3[fiveLong[0]], split3[fiveLong[2]]);
			
			Map<Character,Integer> difference = new HashMap<Character,Integer>();
			for(char c = 'a'; c <= 'z'; c++) {
				difference.put(c, 0);
			}
			for(Character c : diff1) {
				difference.put(c, difference.get(c)+1);
			}
			for(Character c : diff2) {
				difference.put(c, difference.get(c)+1);
			}
			for(Character c : diff3) {
				difference.put(c, difference.get(c)+1);
			}
			char[] CE = {0,0};
			for(Character c : difference.keySet()) {
				if(difference.get(c) > 1) {
					if(CE[0] != 0)
						CE[1] = c;
					else
						CE[0] = c;
				}
			}
			char C1 = findDifference(CE[0]+""+CE[1], split3[one]).iterator().next();
			char C = (C1 == CE[0] ? CE[1] : CE[0]);
			char E = (C == CE[0] ? CE[1] : CE[0]);
			char[] chars = split3[one].toCharArray();
			char F = (C == chars[0] ? chars[1] : chars[0]);
			
			char[] BD = {0,0};
			Set<Character> BDDiff = findDifference(split3[four], split3[one]);
			
			for(Character c : BDDiff) {
				if(BD[0] != 0)
					BD[1] = c;
				else
					BD[0] = c;
			}
			int five = 0;
			char D = 0;
			char B = 0;
			for(int i = 0; i < split3.length; i++) {
				String s1 = split3[i];
				if(s1.length() == 5) {
					boolean oneB = s1.contains(""+BD[0]);
					boolean twoB = s1.contains(""+BD[1]);
					if(oneB && twoB) {
						five = i;
					}else if(oneB || twoB) {
						if(oneB)
							D = BD[0];
						else
							D = BD[1];
						
					}
				}
			}
			if(split3[five].contains(""+BD[0]))
				B = BD[0];
			else
				B = BD[1];
			
			String[] split2 = split[1].trim().split(" ");
			for(int i = 0; i < 4; i++) {
				String s1 = split2[i];
				if(s1.length() == 2) {
					count++;
				}else if(s1.length() == 3) {
					count++;
				}else if(s1.length() == 4) {
					count++;
				}else if(s1.length() == 7) {
					count++;
				}
			}
			char G = findDifference(A+""+B+""+C+""+D+""+E+""+F, split3[eight]).iterator().next();
			char[] mapping = {A,C,B,D,F,E,G};
			String output = "";
			for(String s1 : split2) {
				int value = getNumber(mapping, s1);
				output += value;
			}
			System.out.println(output);
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
		if(input.contains(""+mappings[getValue('a')])) {
			if(input.contains(""+mappings[getValue('b')])) {
				if(input.contains(""+mappings[getValue('c')])) {
					if(input.contains(""+mappings[getValue('d')])) {
						if(input.contains(""+mappings[getValue('e')])) {
							return 9;
						}else {
							return 8;
						}
					}else {
						return 0;
					}
				}else {
					if(input.contains(""+mappings[getValue('e')])) {
						return 6;
					}else {
						return 5;
					}
				}
			}else if(input.contains(""+mappings[getValue('c')])) {
				if(input.contains(""+mappings[getValue('f')])) {
					return 3;
				}else if(input.contains(""+mappings[getValue('e')])){
					return 2;
				}else {
					return 7;
				}
			}
		}else {
			if(input.contains(""+mappings[getValue('b')])) {
				return 4;
			}else {
				return 1;
			}
		}
		return -1;
	}
}
