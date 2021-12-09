package adventofcode2021.day9;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day9.txt")));
		
		int risk = 0;
		int[][] data = null;
		int y = 0;
		
		while(in.hasNextLine()) {
			String s = in.nextLine();
			if(data == null)
				data = new int[5][s.length()];
			String[] split = s.split("");
			for(int i = 0; i < split.length; i++) {
				data[y][i] = Integer.valueOf(split[i]);
			}
			y++;
		}
		List<Integer> basins = new ArrayList<Integer>();
		for(y = 0; y < data.length; y++) {
			for(int x = 0; x < data[y].length; x++) {
				int value = data[y][x];
				
				int left = (x == 0 ? Integer.MAX_VALUE : data[y][x-1]);
				int right = (x == data[y].length-1 ? Integer.MAX_VALUE : data[y][x+1]);
				int up = (y == 0 ? Integer.MAX_VALUE : data[y-1][x]);
				int down = (y == data.length-1 ? Integer.MAX_VALUE : data[y+1][x]);
				
				if(value < left && value < right && value < up && value < down)
					basins.add(getBasinSize(data, x, y));
			}
		}
		System.out.println(risk);
		
		in.close();
		
	}
	private static int getBasinSize(int[][] data, int x1, int y1) {
		boolean[][] result = new boolean[data.length][data[0].length];
		
		for(int y = 0; y < data.length; y++) {
			for(int x = 0; x < data[y].length; x++) {
				int left = (x == 0 ? Integer.MAX_VALUE : data[y][x-1]);
				int right = (x == data[y].length-1 ? Integer.MAX_VALUE : data[y][x+1]);
				int up = (y == 0 ? Integer.MAX_VALUE : data[y-1][x]);
				int down = (y == data.length-1 ? Integer.MAX_VALUE : data[y+1][x]);
				
				int value = data[y][x];
				
				if(value == 9)
					continue;
				
				if(left > value || right > value || up > value || down > value) {
					result[y][x] = true;
				}
			}
		}
		return getRowSize(result, x1, y1);
	}
	private static int getRowSize(boolean[][] data, int x, int y) {
		int count = 0;
		for(int x1 = x; x1 >= 0; x1--) {
			boolean value = data[y][x1];
			if(value)
				count++;
			else
				break;
			if(value && y+1 < data.length && data[y+1][x1]) {
				count += getRowSize(data, x1, y+1);
			}
		}
		for(int x1 = x + 1; x1 < data[y].length; x1++) {
			boolean value = data[y][x1];
			if(value)
				count++;
			else
				break;
			if(value && y+1 < data.length && data[y+1][x1]) {
				count += getRowSize(data, x1, y+1);
			}
		}
		return count;
	}
}
