package adventofcode2021.day4;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day4.txt")));
		String[] numbers = in.nextLine().split(",");
		in.nextLine();
		List<Integer[][]> boards = new ArrayList<Integer[][]>();
		while(in.hasNextLine()) {
			Integer[][] board = new Integer[5][5];
			for(int i = 0; i < 5; i++) {
				String s = in.nextLine();
				board[i] = getRow(s);
			}
			boards.add(board);
			if(in.hasNextLine())
				in.nextLine();
		}
		in.close();
		List<Integer[][]> newBoards = new ArrayList<Integer[][]>();
		
		for(String s : numbers) {
			int num = Integer.valueOf(s);
			for(int i = 0; i < boards.size(); i++) {
				Integer[][] board = boards.get(i);
				board = removeNumber(board, num);
				if(!boardSolved(board)) {
					newBoards.add(board);
				}
				if(boards.size() == 1 && boardSolved(board)) {
					int sum = boardValue(board);
					System.out.println("Answer: "+(sum*num));
				}
			}
			boards = newBoards;
			newBoards = new ArrayList<Integer[][]>();
		}
	}
	private static Integer[] getRow(String s) {
		Integer[] ret = new Integer[5];
		for(int i = 0; i < 5; i++) {
			String s1 = s.substring(i*3, i*3+2);
			ret[i] = Integer.valueOf(s1.trim());
		}
		return ret;
	}
	private static Integer[][] removeNumber(Integer[][] board, int number){
		for(int x = 0; x < 5; x++) {
			for(int y = 0; y < 5; y++) {
				if(board[x][y] == number)
					board[x][y] = -1;
			}
		}
		return board;
	}
	private static boolean boardSolved(Integer[][] board) {
		for(int i = 0; i < 5; i++) {
			boolean failure1 = false;
			boolean failure2 = false;
			for(int i2 = 0; i2 < 5; i2++) {
				if(board[i][i2] != -1) {
					failure1 = true;
				}
				if(board[i2][i] != -1) {
					failure2 = true;
				}
			}
			if(!failure1 || !failure2)
				return true;
		}
		return false;
	}
	private static int boardValue(Integer[][] board) {
		int sum = 0;
		for(int x = 0; x < 5; x++) {
			for(int y = 0; y < 5; y++) {
				int val = board[x][y];
				if(val == -1)
					continue;
				sum += val;
			}
		}
		return sum;
	}
}
