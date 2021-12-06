package adventofcode2021.day6;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day6.txt")));
		
		List<Integer> input = new ArrayList<Integer>();
		
		String s = in.nextLine();
		for(String s1 : s.split(",")) {
			Integer i = Integer.valueOf(s1);
			input.add(i);
		}
		
		for(int i = 0; i < 80; i++) {
			input = processDay(input);
		}
		
		in.close();
		System.out.println(input.size());
	}
	private static List<Integer> processDay(List<Integer> input){
		List<Integer> ret = new ArrayList<Integer>();
		for(int i = 0; i < input.size(); i++) {
			Integer value = input.get(i);
			if(value == 0) {
				ret.add(6);
				ret.add(8);
			}else {
				ret.add(value-1);
			}
		}
		return ret;
	}

}
