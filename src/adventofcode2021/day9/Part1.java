package adventofcode2021.day9;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day9.txt")));
		
		int risk = 0;
		int[][] data = null;
		int y = 0;
		
		while(in.hasNextLine()) {
			String s = in.nextLine();
			if(data == null)
				data = new int[100][s.length()];
			String[] split = s.split("");
			for(int i = 0; i < split.length; i++) {
				data[y][i] = Integer.valueOf(split[i]);
			}
			y++;
		}
		for(y = 0; y < data.length; y++) {
			for(int x = 0; x < data[y].length; x++) {
				int value = data[y][x];
				
				if(value == 0) {
					risk++;
					continue;
				}
				
				int left = (x == 0 ? Integer.MAX_VALUE : data[y][x-1]);
				int right = (x == data[y].length-1 ? Integer.MAX_VALUE : data[y][x+1]);
				int up = (y == 0 ? Integer.MAX_VALUE : data[y-1][x]);
				int down = (y == data.length-1 ? Integer.MAX_VALUE : data[y+1][x]);
				
				if(value < left && value < right && value < up && value < down)
					risk += value + 1;
			}
		}
		System.out.println(risk);
		
		in.close();
		
	}
}
