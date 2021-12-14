package adventofcode2021.day14;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part1 {
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
		for(int round = 0; round < 40; round++) {
			String curr = "";
			int last = 0;
			char[] startC = start.toCharArray();
			for(int i = 0; i < start.length()-1; i++) {
				for(int pair = 0; pair < pairs.size(); pair++) {
					String s1 = pairs.get(pair)[0];
					String s2 = pairs.get(pair)[1];
					char[] s1C = s1.toCharArray();
					if(startC[i] == s1C[0] && startC[i+1] == s1C[1]) {
						for(int i1 = last; i1 < i+1; i1++) {
							curr += startC[i1];
						}
						curr += s2;
						last = i+1;
					}
				}
			}
			for(int i = last; i < start.length(); i++) {
				curr += startC[i];
			}
			start = curr;
		}
		long[] counts = new long[26];
		for(int i = 0; i < 26; i++) {
			counts[i] = count((char)(i+'A'), start);
		}
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
	private static int count(char c, String s) {
		int count = 0;
		for(char c1 : s.toCharArray()) {
			if(c == c1)
				count++;
		}
		return count;
	}
}
