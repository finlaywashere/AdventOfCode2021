package adventofcode2021.day16;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day16.txt")));
		String s = in.nextLine();
		String b = Long.toBinaryString(Long.valueOf(s, 16));
		int versions = parsePackets(b,-1);
		in.close();
		System.out.println("Version Sum: "+versions);
	}
	private static int parsePackets(String b, int count) {
		int versions = 0;
		if(count == -1)
			count = Integer.MAX_VALUE;
		int c = 0;
		while(b.length() != 0 && c < count) {
			versions += Integer.valueOf(b.substring(0, 3), 2);
			int type = Integer.valueOf(b.substring(3,6),2);
			if(type == 4) {
				// Literal value
				b = b.substring(6);
				int c1 = 0;
				while(b.length() != 0) {
					int o = Integer.valueOf(b.substring(0, 1),2);
					if(o == 0)
						break;
					c1++;
				}
				b = b.substring(c1*5);
			}else{
				// Operator
				int lType = Integer.valueOf(b.substring(6, 7),2);
				
				if(lType == 0) {
					// 15 bits containing length in binary
					int len = Integer.valueOf(b.substring(7, 22),2);
					b = b.substring(18);
				}else {
					int nPackets = Integer.valueOf(b.substring(7,18),2);
					b = b.substring(22);
					//versions += parsePackets(b, nPackets);
				}
			}
			c++;
		}
		return versions;
	}
}
