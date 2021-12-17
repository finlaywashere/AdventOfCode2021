package adventofcode2021.day16;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day16.txt")));
		String s = in.nextLine();
		String b = hexToBinary(s);
		
		LongPoint result = parsePacket(b);
		in.close();
		System.out.println("Result: "+result.x);
		
	}
	private static String hexToBinary(String s) {
		String b = "";
		for(String s1 : s.split("")) {
			String s2 = Integer.toBinaryString(Integer.valueOf(s1,16));
			while(s2.length() % 4 != 0)
				s2 = "0"+s2;
			b += s2;
		}
		return b;
	}
	private static LongPoint parsePacket(String binary) {
		int type = Integer.valueOf(binary.substring(3, 6),2);
		if(type == 4) {
			int l = 0;
			binary = binary.substring(6);
			String data = "";
			while(binary.length() > 0) {
				int f = Integer.valueOf(binary.substring(0, 1));
				data += binary.substring(1,5);
				l++;
				if(f == 0)
					break;
				binary = binary.substring(5);
			}
			long value = Long.valueOf(data, 2);
			if(value < 0)
				System.err.println("Number under/overflow!");
			System.out.println("Value: "+value);
			return new LongPoint(value,6+l*5);
		}else {
			int lType = Integer.valueOf(binary.substring(6, 7),2);
			int fLen = 0;
			List<Long> values = new ArrayList<Long>();
			switch(type) {
			case 0:
				System.out.println("+");
				break;
			case 1:
				System.out.println("*");
				break;
			case 2:
				System.out.println("min");
				break;
			case 3:
				System.out.println("max");
				break;
			case 5:
				System.out.println(">");
				break;
			case 6:
				System.out.println("<");
				break;
			case 7:
				System.out.println("==");
				break;
				
			}
			if(lType == 0) {
				int len = Integer.valueOf(binary.substring(7, 22),2);
				int tLen = 0;
				binary = binary.substring(22);
				while(true) {
					LongPoint p = parsePacket(binary);
					if(p.x < 0)
						System.err.println("Integer overflow!!!");
					tLen += p.y;
					values.add(p.x);
					binary = binary.substring((int) p.y);
					if(Math.abs(tLen-len) < 8)
						break;
				}
				fLen = len+22;
			}else {
				int nPackets = Integer.valueOf(binary.substring(7, 18),2);
				int len = 0;
				binary = binary.substring(18);
				for(int i = 0; i < nPackets; i++) {
					LongPoint p = parsePacket(binary);
					if(p.x < 0)
						System.err.println("Integer overflow!!!");
					values.add(p.x);
					len += p.y;
					binary = binary.substring((int) p.y);
				}
				fLen = len + 18;
			}
			System.out.println("END");
			if(type == 0) {
				// sum
				long sum = 0;
				for(long i : values) {
					sum += i;
				}
				return new LongPoint(sum,fLen);
			}else if(type == 1) {
				// product
				long prod = 1;
				for(long i : values) {
					prod *= i;
				}
				return new LongPoint(prod,fLen);
			}else if(type == 2) {
				// minimum
				long min = Integer.MAX_VALUE;
				for(long i : values) {
					if(i < min)
						min = i;
				}

				return new LongPoint(min,fLen);
			}else if(type == 3) {
				// maximum
				long max = Integer.MIN_VALUE;
				for(long i : values) {
					if(i > max)
						max = i;
				}
				return new LongPoint(max,fLen);
			}else if(type == 5) {
				// greater than
				if(values.get(0).longValue() > values.get(1).longValue())
					return new LongPoint(1,fLen);
				return new LongPoint(0,fLen);
			}else if(type == 6) {
				// less than
				if(values.get(0).longValue() < values.get(1).longValue())
					return new LongPoint(1,fLen);
				return new LongPoint(0,fLen);
			}else if(type == 7) {
				// equal to
				if(values.get(0).longValue() == values.get(1).longValue())
					return new LongPoint(1,fLen);
				return new LongPoint(0,fLen);
			}
		}
		System.err.println("Reached end of method!");
		return null;
	}
}
