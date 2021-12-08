package adventofcode2021.day8;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day8.txt")));
		
		int count = 0;
		
		while(in.hasNextLine()) {
			String s = in.nextLine();
			String[] split = s.split("\\|");
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
}
