package adventofcode2021.day2;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day2.txt")));
		int forward = 0;
		int depth = 0;
		int aim = 0;
		while(in.hasNextLine()) {
			String s = in.nextLine();
			String[] split = s.split(" ",2);
			if(split[0].equals("forward")) {
				int i = Integer.valueOf(split[1]);
				forward += i;
				depth += aim * i;
			}else if(split[0].equals("up")) {
				int i = Integer.valueOf(split[1]);
				aim -= i;
			}else if(split[0].equals("down")) {
				int i = Integer.valueOf(split[1]);
				aim += i;
			}
		}
		in.close();
		System.out.println("Answer: "+(depth*forward));
	}
}
