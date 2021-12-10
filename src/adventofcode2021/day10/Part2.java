package adventofcode2021.day10;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day10.txt")));
		
		List<Long> scores = new ArrayList<Long>();
		
		while(in.hasNextLine()) {
			String s = in.nextLine();
			long count = calcBrackets(s);
			if(count != 0)
				scores.add(count);
		}
		
		in.close();
		
		List<Long> sorted = scores.stream().sorted().collect(Collectors.toList());
		long middle = sorted.get(sorted.size()/2);
		System.out.println("Answer: "+middle);
	}
	private static long calcBrackets(String s) {
		List<String> brackets = new ArrayList<String>();
		for(String s1 : s.split("")) {
			if(s1.equals("(") || s1.equals("[") || s1.equals("{") || s1.equals("<")) {
				brackets.add(s1);
			}else if(s1.equals(")") || s1.equals("}") || s1.equals("]") || s1.equals(">")) {
				String mostRecent = brackets.get(brackets.size()-1);
				brackets.remove(brackets.size()-1);
				if(s1.equals(")") && !mostRecent.equals("(")) {
					return 0;
				}else if(s1.equals("]") && !mostRecent.equals("[")) {
					return 0;
				}else if(s1.equals(">") && !mostRecent.equals("<")) {
					return 0;
				}else if(s1.equals("}") && !mostRecent.equals("{")) {
					return 0;
				}
			}
		}
		long answer = 0;
		for(int i = brackets.size()-1; i >= 0; i--) {
			String s1 = brackets.get(i);
			answer *= 5;
			if(s1.equals("(")) {
				answer += 1;
			}else if(s1.equals("[")) {
				answer += 2;
			}else if(s1.equals("{")) {
				answer += 3;
			}else if(s1.equals("<")) {
				answer += 4;
			}
		}
		return answer;
	}
}
