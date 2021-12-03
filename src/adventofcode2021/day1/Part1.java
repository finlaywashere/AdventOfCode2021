package adventofcode2021.day1;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day1.txt")));
		int last = 0;
		int increased = 0;
		while(in.hasNextLine()) {
			int i = Integer.valueOf(in.nextLine());
			if(i > last)
				increased++;
			last = i;
		}
		increased--;
		in.close();
		System.out.println(increased);
	}
}
