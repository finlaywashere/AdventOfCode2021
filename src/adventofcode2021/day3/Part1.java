package adventofcode2021.day3;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day3.txt")));
		float[] commonality = null;
		int count = 0;
		while(in.hasNextLine()) {
			String s = in.nextLine();
			if(commonality == null)
				commonality = new float[s.length()];
			for(int i = 0; i < s.length(); i++) {
				commonality[i] += Integer.valueOf(s.substring(i,i+1));
			}
			count++;
		}
		
		String gamma = "";
		String epsilon = "";
		for(int i = 0; i < commonality.length; i++) {
			commonality[i] /= count;
			int bit = getBit(commonality[i]);
			gamma += ""+bit;
			epsilon += ""+(1-bit);
		}
		int g = Integer.valueOf(gamma, 2);
		int e = Integer.valueOf(epsilon, 2);
		int answer = g * e;
		System.out.println("Answer: "+answer);
	}
	private static int getBit(float f) {
		if(f >= 0.5)
			return 1;
		return 0;
	}
}
