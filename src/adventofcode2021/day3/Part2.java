package adventofcode2021.day3;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day3.txt")));
		List<String> strings = new ArrayList<String>();
		while(in.hasNextLine()) {
			String s = in.nextLine();
			strings.add(s);
		}
		int o = filterNumbers(strings, 0, false);
		int c = filterNumbers(strings, 0, true);
		int answer = o * c;
		System.out.println("Answer: "+answer);
	}
	private static int filterNumbers(List<String> numbers, int start, boolean invert) {
		float[] commonality = null;
		int count = 0;
		for(String s : numbers) {
			if(commonality == null)
				commonality = new float[s.length()];
			for(int i = 0; i < s.length(); i++) {
				commonality[i] += Integer.valueOf(s.substring(i,i+1));
			}
			count++;
		}
		int[] ints = new int[commonality.length];
		for(int i = 0; i < commonality.length; i++) {
			commonality[i] /= count;
			ints[i] = getBit(commonality[i]);
			if(invert)
				ints[i] = 1 - ints[i];
		}
		List<String> newNumbers = new ArrayList<String>();
		for(String s : numbers) {
			int bit = Integer.valueOf(s.substring(start, start+1));
			if(bit == ints[start])
				newNumbers.add(s);
		}
		if(newNumbers.size() == 1) {
			return Integer.valueOf(newNumbers.get(0), 2);
		}else if(newNumbers.size() == 0) {
			System.err.println("Error occurred!");
			return 0;
		}else {
			return filterNumbers(newNumbers, start+1,invert);
		}
	}
	private static int getBit(float f) {
		if(f >= 0.5)
			return 1;
		return 0;
	}
}
