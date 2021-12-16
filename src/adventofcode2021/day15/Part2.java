package adventofcode2021.day15;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day15.txt")));
		int[][] risks = null;
		int y = 0;
		while(in.hasNextLine()) {
			String s = in.nextLine();
			if(risks == null) {
				risks = new int[100][s.length()];
			}
			for(int x = 0; x < s.length(); x++) {
				risks[y][x] = Integer.valueOf(""+s.charAt(x));
			}
			y++;
		}
		in.close();
		int[][] fullRisk = new int[risks.length*5][risks[0].length*5];
		for(y = 0; y < risks.length; y++) {
			for(int x = 0; x < risks[0].length; x++) {
				for(int i = 0; i < 25; i++) {
					int y2 = i / 5;
					int x2 = i % 5;
					int dist = x2 + y2;
					int val = risks[y][x] + dist;
					while(val > 9) val -= 9;
					fullRisk[y+(y2*risks.length)][x+(x2*risks.length)] = val;
				}
			}
		}
		int min = dijkstra(fullRisk);
		System.out.println("Min: "+min);
	}
	private static void round(int[][] dist, int[][] risks) {
		for(int y = 0; y < dist.length; y++) {
			for(int x = 0; x < dist[y].length; x++) {
				int d = dist[y][x];
				for(int y2 = Math.max(y-1, 0); y2 < Math.min(y+2, dist.length); y2++) {
					for(int x2 = Math.max(x-1, 0); x2 < Math.min(x+2, dist[y2].length); x2++) {
						if(x2 != x && y2 != y)
							continue;
						if(d != Integer.MAX_VALUE && dist[y2][x2] > d + risks[y2][x2])
							dist[y2][x2] = d + risks[y2][x2];
					}
				}
			}
		}
	}
	private static int dijkstra(int[][] risks) {
		int[][] dist = new int[risks.length][risks[0].length];
		for(int y = 0; y < dist.length; y++) {
			for(int x = 0; x < dist[y].length; x++) {
				dist[y][x] = Integer.MAX_VALUE;
			}
		}
		dist[0][0] = 0;
		// Approach best possible path
		for(int i = 0; i < 100; i++) {
			round(dist,risks);
		}
		
		for(int y = 0; y < dist.length; y++) {
			String s = "";
			for(int x = 0; x < dist[y].length; x++) {
				s += dist[y][x] + " ";
			}
			System.out.println(s);
		}
		return dist[dist.length-1][dist[0].length-1];
	}
}
