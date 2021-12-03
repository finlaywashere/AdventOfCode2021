package adventofcode2021.day1;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day1.txt")));
		int[] last = new int[2];
		int increased = 0;
		int lastTotal = 0;
		while(in.hasNextLine()) {
			int i = Integer.valueOf(in.nextLine());
			if(last[0] == 0 || last[1] == 0) {
				last[0] = last[1];
				last[1] = i;
				continue;
			}
			
			int total = last[0] + last[1] + i;
			
			if(total > lastTotal)
				increased++;
			
			lastTotal = total;
			
			last[0] = last[1];
			last[1] = i;
		}
		increased--;
		in.close();
		System.out.println(increased);
	}
}
