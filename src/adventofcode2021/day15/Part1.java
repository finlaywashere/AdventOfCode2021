package adventofcode2021.day15;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day15.txt")));
		int[][] risks = null;
		int y = 0;
		while(in.hasNextLine()) {
			String s = in.nextLine();
			if(risks == null) {
				risks = new int[10][s.length()];
			}
			for(int x = 0; x < s.length(); x++) {
				risks[y][x] = Integer.valueOf(""+s.charAt(x));
			}
			y++;
		}
		in.close();
		int x = 0;
		y = 0;
		int risk = 0;
		risks[0][0] = Integer.MAX_VALUE;
		while(y != risks.length-1 && x != risks[0].length-1) {
			int riskU = (y-1 < 0 ? Integer.MAX_VALUE : risks[y-1][x]);
			int riskD = (y+1 > risks.length-1 ? Integer.MAX_VALUE : risks[y+1][x]);
			int riskL = (x-1 < 0 ? Integer.MAX_VALUE : risks[y][x-1]);
			int riskR = (x+1 > risks[0].length-1 ? Integer.MAX_VALUE : risks[y][x+1]);
			
			int min = Math.min(Math.min(riskU, riskD), Math.min(riskL, riskR));
			if(min == riskU) {
				risk += min;
				risks[y-1][x] = Integer.MAX_VALUE;
				y--;
			}else if(min == riskD) {
				risk += min;
				risks[y+1][x] = Integer.MAX_VALUE;
				y++;
			}else if(min == riskL) {
				risk += min;
				risks[y][x-1] = Integer.MAX_VALUE;
				x--;
			}else if(min == riskR) {
				risk += min;
				risks[y][x+1] = Integer.MAX_VALUE;
				x++;
			}
			System.out.println("X: "+x+" Y: "+y);
		}
		System.out.println("Lowest risk: "+risk);
	}
}
