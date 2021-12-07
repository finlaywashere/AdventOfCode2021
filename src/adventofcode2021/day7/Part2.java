package adventofcode2021.day7;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day7.txt")));
		String line = in.nextLine();
		in.close();
		String[] split = line.split(",");
		int[] data = new int[split.length];
		int max = 0;
		for(int i = 0; i < split.length; i++) {
			data[i] = Integer.valueOf(split[i]);
			if(data[i] > max)
				max = data[i];
		}
		int[] options = new int[max+1];
		for(int i = 0; i < options.length; i++) {
			int diff = 0;
			for(int i2 = 0; i2 < data.length; i2++) {
				diff += calculateCost(Math.abs(data[i2]-i));
			}
			options[i] = diff; 
		}
		int min = Integer.MAX_VALUE;
		for(int i : options) {
			if(i < min) {
				if(i == 0)
					System.out.println(i);
				min = i;
			}
		}
		System.out.println("Answer: "+min);
	}
	private static int calculateCost(int diff) {
		int ret = 0;
		for(int i = 1; i <= diff; i++) {
			ret += i;
		}
		return ret;
	}
}
