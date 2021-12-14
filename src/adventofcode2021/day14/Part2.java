package adventofcode2021.day14;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day14.txt")));
		String start = in.nextLine();
		List<String[]> pairs = new ArrayList<String[]>();
		while(in.hasNextLine()) {
			String s = in.nextLine();
			if(s.isEmpty()) continue;
			String[] split = s.split(" \\-\\> ");
			pairs.add(split);
		}
		in.close();
		Map<String,Long> data = new HashMap<String,Long>();
		for(int i = 0; i < start.length()-1; i++) {
			String s = start.substring(i, i+2);
			if(data.containsKey(s))
				data.put(s, data.get(s)+1);
			else
				data.put(s, 1l);
		}
		for(int round = 0; round < 40; round++) {
			Map<String,Long> tmp = new HashMap<String,Long>();
			for(String s : data.keySet()) {
				boolean found = false;
				for(String[] split : pairs) {
					String s1 = split[0];
					String s2 = split[1];
					if(s.equals(s1)) {
						char[] sC = s.toCharArray();
						char[] s1C = s2.toCharArray();
						long value = data.get(s)+1;
						value += (tmp.containsKey(s) ? tmp.get(s) : 0);
						tmp.put(""+sC[0]+s1C[0], value);
						tmp.put(""+s1C[0]+sC[1], value);
						
						found = true;
						break;
					}
				}
				if(!found) {
					tmp.put(s, data.get(s));
				}
			}
			data = tmp;
		}
		long[] counts = new long[26];
		for(String s : data.keySet()) {
			char[] c = s.toCharArray();
			counts[c[0]-'A'] += data.get(s);
			counts[c[1]-'A'] += data.get(s);
		}
		char last = start.toCharArray()[start.length()-1];
		for(int i = 0; i < counts.length; i++) {
			counts[i] /= 2;
		}
		counts[last-'A']--;
		Arrays.sort(counts);
		long lowest = 0;
		for(long i : counts) {
			if(i != 0) {
				lowest = i;
				break;
			}
		}
		long answer = counts[counts.length-1]-lowest;
		System.out.println("Answer: "+answer);
	}
}
