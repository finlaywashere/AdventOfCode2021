package adventofcode2021.day2;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day2.txt")));
		int forward = 0;
		int depth = 0;
		while(in.hasNextLine()) {
			String s = in.nextLine();
			String[] split = s.split(" ",2);
			if(split[0].equals("forward")) {
				forward += Integer.valueOf(split[1]);
			}else if(split[0].equals("up")) {
				depth -= Integer.valueOf(split[1]);
			}else if(split[0].equals("down")) {
				depth += Integer.valueOf(split[1]);
			}
		}
		in.close();
		System.out.println("Answer: "+(depth*forward));
	}
}
