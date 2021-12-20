package adventofcode2021.day18;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day18.txt")));
		List<String> possibilities = new ArrayList<String>();
		while(in.hasNextLine()) {
			String s = in.nextLine();
			possibilities.add(s);
		}
		int highest = Integer.MIN_VALUE;
		
		for(int i = 0; i < possibilities.size(); i++) {
			String s = possibilities.get(i);
			for(int i1 = 0; i1 < possibilities.size(); i1++) {
				if(i == i1) continue;
				String s1 = possibilities.get(i1);
				int mag = magnitude(add(s, s1));
				int mag2 = magnitude(add(s1,s));
				if(mag > highest)
					highest = mag;
				if(mag2 > highest)
					highest = mag;
			}
		}
		
		System.out.println("Magnitude: "+highest);
	}
	private static int magnitude(String s) {
		s = s.substring(1, s.length()-1);
		String[] split = split(s);
		int l,r;
		if(split[0].startsWith("[")) {
			l = magnitude(split[0]);
		}else {
			l = Integer.valueOf(split[0]);
		}
		if(split[1].startsWith("[")) {
			r = magnitude(split[1]);
		}else {
			r = Integer.valueOf(split[1]);
		}
		return 3*l+2*r;
	}
	private static String add(String s1, String s2) {
		String s = "["+s1.trim()+","+s2.trim()+"]";
		
		while(true) {
			String s3 = explode(s);
			if(s3 != null) {
				s = s3;
				continue;
			}
			s3 = splitOperation(s);
			if(s3 != null) {
				s = s3;
				continue;
			}
			break;
		}
		return s;
	}
	private static String explode(String s) {
		int count = 0;
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c == '[') {
				count++;
				if(count == 4) {
					String sec = getSection(s,i);
					String before = s.substring(0, i+1);
					String after = s.substring(i+sec.length()+1);
					String[] split = split(sec);
					String result = "";
					if(split[0].startsWith("[")) {
						String[] split2 = split[0].substring(1,split[0].length()-1).split(",");
						int l = Integer.valueOf(split2[0]);
						int r = Integer.valueOf(split2[1]);
						String[] rightOf = split[1].split(",");
						int numStart = 0;
						int numEnd = 0;
						for(int i1 = i; i1 >= 0; i1--) {
							char c1 = before.charAt(i1);
							String s1 = ""+c1;
							if(s1.matches("\\d") && numEnd == 0) {
								numEnd = i1 + 1;
							}else if(!s1.matches("\\d") && numEnd != 0) {
								numStart = i1 + 1;
								break;
							}
						}
						if(numEnd != 0) {
							int num = Integer.valueOf(before.substring(numStart, numEnd)) + l;
							before = before.substring(0, numStart)+num+before.substring(numEnd);
						}
						if(rightOf.length == 1) {
							result = "0,"+(r+Integer.valueOf(rightOf[0]));
						}else {
							result = "0,["+(r+Integer.valueOf(rightOf[0].substring(1)))+","+rightOf[1];
						}
					}else if(split[1].startsWith("[")) {
						String[] split2 = split[1].substring(1,split[1].length()-1).split(",");
						int l = Integer.valueOf(split2[0]);
						int r = Integer.valueOf(split2[1]);
						String[] leftOf = split[0].split(",");
						int numStart = 0;
						int numEnd = 0;
						for(int i1 = 0; i1 < after.length(); i1++) {
							char c1 = after.charAt(i1);
							String s1 = ""+c1;
							if(s1.matches("\\d") && numStart == 0) {
								numStart = i1;
							}else if(!s1.matches("\\d") && numStart != 0) {
								numEnd = i1;
								break;
							}
						}
						if(numEnd != 0) {
							int num = Integer.valueOf(after.substring(numStart, numEnd)) + r;
							after = after.substring(0, numStart)+num+after.substring(numEnd);
						}
						if(leftOf.length == 1) {
							result = (l+Integer.valueOf(leftOf[0]))+",0";
						}else {
							result = leftOf[0] +","+(Integer.valueOf(leftOf[1])+l)+"],0";
						}
					}
					if(!result.isEmpty())
						return before + result + after;
				}
			}else if(c == ']') {
				count--;
			}
		}
		return null;
	}
	private static String splitOperation(String s) {
		s = s.substring(1, s.length()-1);
		String[] split = split(s);
		if(split[0].startsWith("[")) {
			String s1 = splitOperation(split[0]);
			if(s1 != null)
				return "["+s1+","+split[1]+"]";
		}else {
			int num = Integer.valueOf(split[0]);
			if(num >= 10) {
				String result = "[["+(num/2)+","+(num / 2 + num % 2)+"],"+split[1]+"]";
				return result;
			}
		}
		if(split[1].startsWith("[")) {
			String s1 = splitOperation(split[1]);
			if(s1 != null)
				return "["+split[0]+","+s1+"]";
		}else {
			int num = Integer.valueOf(split[1]);
			if(num >= 10) {
				String result = "["+split[0]+",["+(num/2)+","+(num / 2 + num % 2)+"]]";
				return result;
			}
		}
		return null;
	}
	private static String getSection(String s, int i) {
		int count = 0;
		for(int i2 = i; i2 < s.length(); i2++) {
			char c = s.charAt(i2);
			if(c == '[') {
				count++;
			}else if(c == ']') {
				count--;
			}
			if(count == 0) {
				return s.substring(i+1, i2);
			}
		}
		return null;
	}
	private static String[] split(String s) {
		int count = 0;
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c == '[') {
				count++;
			}else if(c == ']') {
				count--;
			}else if(c == ',' && count == 0) {
				return new String[] {s.substring(0, i),s.substring(i+1)};
			}
		}
		return null;
	}
}
