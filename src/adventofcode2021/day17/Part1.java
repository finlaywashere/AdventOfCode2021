package adventofcode2021.day17;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day17.txt")));
		String[] split = in.nextLine().split(" ",3)[2].split("\\, ");
		in.close();
		String[] xSplit = split[0].substring(2).split("\\.\\.");
		String[] ySplit = split[1].substring(2).split("\\.\\.");
		long x1 = Integer.valueOf(xSplit[0]), x2 = Integer.valueOf(xSplit[1]), y1 = Integer.valueOf(ySplit[0]), y2 = Integer.valueOf(ySplit[1]);
		long max = 0;
		for(long x = 0; x < 100; x++) {
			for(long y = -100; y < 10000; y++) {
				long xVel = x;
				long yVel = y;
				long yVal = 0;
				long xVal = 0;
				long localMax = 0;
				boolean hits = false;
				for(long i = 0; i < 1000; i++) {
					xVal += xVel;
					yVal += yVel;
					if(yVal > localMax)
						localMax = yVal;
					if(xVal >= x1 && xVal <= x2 && yVal >= y1 && yVal <= y2) {
						hits = true;
						System.out.println("X: "+x+"Y: "+y+" Local Max: "+localMax);
						break;
					}
					if(xVel < 0) {
						xVel++;
					}else if(xVel > 0) {
						xVel--;
					}
					yVel--;
				}
				if(hits && localMax > max) {
					max = localMax;
				}
			}
		}
		System.out.println("Max: "+max);
	}
}
