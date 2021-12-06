package adventofcode2021.day6;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Part2 {

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day6.txt")));
		
		Map<Integer,Long> count = new HashMap<Integer,Long>();
		
		String s = in.nextLine();
		for(String s1 : s.split(",")) {
			Integer i = Integer.valueOf(s1);
			if(!count.containsKey(i))
				count.put(i, (long) 1);
			else
				count.put(i, count.get(i)+1);
		}
		
		for(int i = 0; i < 256; i++) {
			count = processDay(count);
		}
		
		in.close();
		long countS = 0;
		for(int i : count.keySet()) {
			countS += count.get(i);
		}
		System.out.println(countS);
	}
	private static Map<Integer,Long> processDay(Map<Integer,Long> input){
		Map<Integer,Long> ret = new HashMap<Integer,Long>();
		for(int i : input.keySet()) {
			long count = input.get(i);
			if(i == 0) {
				if(!ret.containsKey(6))
					ret.put(6, count);
				else
					ret.put(6, ret.get(6)+count);
				if(!ret.containsKey(8))
					ret.put(8, count);
				else
					ret.put(8, ret.get(8)+count);
			}else {
				if(!ret.containsKey(i-1))
					ret.put(i-1, count);
				else
					ret.put(i-1, ret.get(i-1)+count);
			}
		}
		return ret;
	}

}
