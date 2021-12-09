package adventofcode2021.day9;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day9.txt")));
		
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
		List<Integer> basins = new ArrayList<Integer>();
		for(y = 0; y < data.length; y++) {
			for(int x = 0; x < data[y].length; x++) {
				int value = data[y][x];
				
				int left = (x == 0 ? Integer.MAX_VALUE : data[y][x-1]);
				int right = (x == data[y].length-1 ? Integer.MAX_VALUE : data[y][x+1]);
				int up = (y == 0 ? Integer.MAX_VALUE : data[y-1][x]);
				int down = (y == data.length-1 ? Integer.MAX_VALUE : data[y+1][x]);
				
				if(value <= left && value <= right && value <= up && value <= down)
					basins.add(getBasinSize(data, x, y));
			}
		}
		List<Integer> sorted = basins.stream().sorted().collect(Collectors.toList());
		
		int result = 1;
		for(int i = sorted.size()-1; i > sorted.size()-4; i--) {
			result *= sorted.get(i);
		}
		
		System.out.println(result);
		
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
		return getRowSize(result, data, x1, y1, -1);
	}
	private static int getRowSize(boolean[][] data, int[][] oData, int x, int y, int lastVal) {
		if(y < 0 || y >= data.length || x < 0 || x >= data[0].length)
			return 0;
		if(!data[y][x] || (oData[y][x] <= lastVal))
			return 0;
		int count = 1;
		data[y][x] = false;
		int val = oData[y][x];
		count += getRowSize(data, oData, x-1, y,val);
		count += getRowSize(data, oData, x+1, y,val);
		count += getRowSize(data, oData, x, y-1,val);
		count += getRowSize(data, oData, x, y+1,val);
		
		return count;
	}
}
