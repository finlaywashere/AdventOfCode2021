package adventofcode2021.day5;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Part1 {

	private static final int SIZE = 1000;
	
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day5.txt")));
		
		int[][] board = new int[SIZE][SIZE];
		
		while(in.hasNextLine()) {
			String s = in.nextLine();
			String[] split = s.split("\\-\\>");
			String[] one = split[0].split(",");
			String[] two = split[1].split(",");
			int x1 = Integer.valueOf(one[0].trim());
			int x2 = Integer.valueOf(two[0].trim());
			int y1 = Integer.valueOf(one[1].trim());
			int y2 = Integer.valueOf(two[1].trim());
			if(x1 == x2) {
				for(int i = y1; i <= y2; i++) {
					board[i][x1]++;
				}
				for(int i = y2; i <= y1; i++) {
					board[i][x1]++;
				}
			}else if(y1 == y2) {
				for(int i = x1; i <= x2; i++) {
					board[y1][i]++;
				}
				for(int i = x2; i <= x1; i++) {
					board[y1][i]++;
				}
			}else {
				int ySign = y1 > y2 ? -1 : 1;
				int xSign = x1 > x2 ? -1 : 1;
				
				for(int i = 0; i <= Math.abs(x1-x2); i++) {
					board[y1 + (i * ySign)][x1 + (i * xSign)]++;
				}
			}
		}
		int count = 0;
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board[x].length; y++) {
				int c = board[x][y];
				if(c >= 2)
					count++;
			}
		}
		System.out.println("Count: "+count);
		in.close();
	}

}
