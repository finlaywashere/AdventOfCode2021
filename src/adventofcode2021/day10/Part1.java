package adventofcode2021.day10;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day10.txt")));
		
		int answer = 0;
		
		while(in.hasNextLine()) {
			String s = in.nextLine();
			int count = firstIllegalItem(s);
			answer += count;
		}
		
		in.close();
		
		System.out.println("Answer: "+answer);
	}
	private static int firstIllegalItem(String s) {
		List<String> brackets = new ArrayList<String>();
		for(String s1 : s.split("")) {
			if(s1.equals("(") || s1.equals("[") || s1.equals("{") || s1.equals("<")) {
				brackets.add(s1);
			}else if(s1.equals(")") || s1.equals("}") || s1.equals("]") || s1.equals(">")) {
				String mostRecent = brackets.get(brackets.size()-1);
				brackets.remove(brackets.size()-1);
				if(s1.equals(")") && !mostRecent.equals("(")) {
					return 3;
				}else if(s1.equals("]") && !mostRecent.equals("[")) {
					return 57;
				}else if(s1.equals(">") && !mostRecent.equals("<")) {
					return 25137;
				}else if(s1.equals("}") && !mostRecent.equals("{")) {
					return 1197;
				}
			}
		}
		return 0;
	}
}
