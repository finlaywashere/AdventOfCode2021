package adventofcode2021.day8;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day8.txt")));
		
		int count = 0;
		
		while(in.hasNextLine()) {
			String s = in.nextLine();
			String[] split = s.split("\\|");
			
			char[] mappings = new char[26];
			for(int i = 0; i < 7; i++) {
				int[] tmpCount = new int[7];
				for(String s1 : split[0].split(" ")) {
					if(s1.length() == 2) {
						// 1
						if(i == 1 || i == 4) {
							for(char c : s1.toCharArray()) {
								tmpCount[getValue(c)] += 1;
							}
						}
					}else if(s1.length() == 3) {
						if(i == 1 || i == 4 || i == 0) {
							for(char c : s1.toCharArray()) {
								tmpCount[getValue(c)] += 1;
							}
						}
					}else if(s1.length() == 4) {
						if(i == 1 || i == 4 || i == 2 || i == 3) {
							for(char c : s1.toCharArray()) {
								tmpCount[getValue(c)] += 1;
							}
						}
					}else if(s1.length() == 7) {
						for(char c : s1.toCharArray()) {
							tmpCount[getValue(c)] += 1;
						}
					}
				}
				for(int i1 : tmpCount) {
					System.out.println(i1+"");
				}
			}
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
}
