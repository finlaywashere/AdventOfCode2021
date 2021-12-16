package adventofcode2021.day16;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day16.txt")));
		String s = in.nextLine();
		String b = hexToBinary(s);
		
		int versions = parsePacket(b).x;
		in.close();
		System.out.println("Version Sum: "+versions);
	}
	private static String hexToBinary(String s) {
		String b = "";
		for(String s1 : s.split("")) {
			//if(s1.equals("0")) {
			//	b += "0000";
			//}else {
				String s2 = Integer.toBinaryString(Integer.valueOf(s1,16));
				while(s2.length() % 4 != 0)
					s2 = "0"+s2;
				b += s2;
			//}
		}
		return b;
	}
	private static Point parsePacket(String binary) {
		int version = Integer.valueOf(binary.substring(0, 3),2);
		int type = Integer.valueOf(binary.substring(3, 6),2);
		if(type == 4) {
			int l = 0;
			binary = binary.substring(6);
			while(binary.length() > 0) {
				int f = Integer.valueOf(binary.substring(0, 1));
				l++;
				if(f == 0)
					break;
				binary = binary.substring(5);
			}
			return new Point(version,6+l*5);
		}else {
			int lType = Integer.valueOf(binary.substring(6, 7),2);
			if(lType == 0) {
				int len = Integer.valueOf(binary.substring(7, 22),2);
				int tLen = 0;
				binary = binary.substring(22);
				while(true) {
					Point p = parsePacket(binary);
					tLen += p.y;
					binary = binary.substring(p.y);
					version += p.x;
					if(Math.abs(tLen-len) < 8)
						break;
				}
				return new Point(version,len+22);
			}else {
				int nPackets = Integer.valueOf(binary.substring(7, 18),2);
				int len = 0;
				binary = binary.substring(18);
				for(int i = 0; i < nPackets; i++) {
					Point p = parsePacket(binary);
					version += p.x;
					len += p.y;
					binary = binary.substring(p.y);
				}
				return new Point(version,len+18);
			}
		}
	}
}
