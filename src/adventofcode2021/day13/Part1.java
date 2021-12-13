package adventofcode2021.day13;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day13.txt")));
		List<String> points = new ArrayList<String>();
		List<String> instructions = new ArrayList<String>();
		while(in.hasNextLine()) {
			String s = in.nextLine();
			if(s.startsWith("fold")) {
				instructions.add(s.split(" ")[2]);
			}else if(!s.isBlank()){
				points.add(s);
			}
			
		}
		int maxX = 0, maxY = 0;
		for(String s : points) {
			String[] split = s.split(",");
			int x = Integer.valueOf(split[0]);
			int y = Integer.valueOf(split[1]);
			if(x > maxX)
				maxX = x;
			if(y > maxY)
				maxY = y;
		}
		boolean[][] data = new boolean[maxY+1][maxX+1];
		for(String s : points) {
			String[] split = s.split(",");
			int x = Integer.valueOf(split[0]);
			int y = Integer.valueOf(split[1]);
			data[y][x] = true;
		}
		for(int i = 0; i < instructions.size(); i++) {
			if(i == 1) break;
			String[] split = instructions.get(i).split("\\=");
			String axis = split[0];
			int value = Integer.valueOf(split[1]);
			data = fold(data, value, axis.equals("y"));
		}
		in.close();
		int count = 0;
		for(int y = 0; y < data.length; y++) {
			for(int x = 0; x < data[y].length; x++) {
				if(data[y][x])
					count++;
			}
		}
		System.out.println("Answer: "+count);
	}
	private static boolean[][] fold(boolean[][] input, int fold, boolean yAxis){
		if(yAxis) {
			boolean[][] ret = new boolean[input.length-fold][input[0].length];
			
			for(int y = 0; y < ret.length; y++) {
				for(int x = 0; x < ret[y].length; x++) {
					ret[y][x] = input[y][x];
				}
			}
			
			for(int y = fold + 1; y < input.length; y++) {
				for(int x = 0; x < input[y].length; x++) {
					if(input[y][x]) {
						int newY = ret.length-(y-fold)-1;
						if(newY < 0 || newY >= ret.length) continue;
						ret[newY][x] |= input[y][x];
					}
				}
			}
			
			return ret;
		}else {
			boolean[][] ret = new boolean[input.length][input[0].length-fold];
			
			for(int y = 0; y < ret.length; y++) {
				for(int x = 0; x < ret[y].length; x++) {
					ret[y][x] = input[y][x];
				}
			}
			
			for(int y = 0; y < input.length; y++) {
				for(int x =  fold + 1; x < input[y].length; x++) {
					if(input[y][x]) {
						int newX = ret[0].length-(x-fold)-1;
						if(newX < 0 || newX > ret[y].length) continue;
						ret[y][newX] |= input[y][x];
					}
				}
			}
			
			return ret;
		}
	}
}
