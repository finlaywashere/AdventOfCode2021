package adventofcode2021.day11;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day11.txt")));
		int[][] input = new int[10][10];
		int y = 0;
		while(in.hasNextLine()) {
			String s = in.nextLine();
			
			int x = 0;
			for(String s1 : s.split("")) {
				input[y][x] = Integer.valueOf(s1);
				x++;
			}
			
			y++;
		}
		in.close();
		int i = 1;
		while(true) {
			for(y = 0; y < input.length; y++) {
				for(int x = 0; x < input[y].length; x++) {
					input[y][x]++;
				}
			}
			int flashes = recursiveFlash(input, new boolean[input.length][input[0].length], true);
			if(flashes == 100)
				break;
			i++;
		}
		System.out.println("Round: "+i);
	}
	private static int recursiveFlash(int[][] input, boolean[][] flashed, boolean top) {
		int count = 0;
		for(int y = 0; y < input.length; y++) {
			for(int x = 0; x < input[y].length; x++) {
				if(input[y][x] > 9 && !flashed[y][x]) {
					flashed[y][x] = true;
					count++;
					for(int i = -1; i <= 1; i++) {
						for(int i1 = -1; i1 <= 1; i1++) {
							if(y + i < 0)
								continue;
							if(y + i >= input.length)
								continue;
							if(x + i1 < 0)
								continue;
							if(x + i1 >= input[y+i].length)
								continue;
							input[y+i][x+i1]++;
						}
					}
				}
			}
		}
		if(count != 0) {
			count += recursiveFlash(input, flashed, false);
		}
		if(top) {
			for(int y = 0; y < flashed.length; y++) {
				for(int x = 0; x < flashed[y].length; x++) {
					if(flashed[y][x])
						input[y][x] = 0;
				}
			}
		}
		return count;
	}
}
