package adventofcode2021.day12;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day12.txt")));
		List<String[]> connections = new ArrayList<String[]>();
		while(in.hasNextLine()) {
			String s = in.nextLine();
			String[] split = s.split("\\-");
			connections.add(split);
		}
		in.close();
		List<String> moves = getMoves("start", connections, null, true);
		for(String m : moves) {
			System.out.println(m);
		}
		System.out.println("Answer: "+moves.size());
	}
	private static List<String> getMoves(String start, List<String[]> connections, Map<String,Integer> counts, boolean top){
		List<String> ret = new ArrayList<String>();
		List<String> c = getConnections(start, connections);
		for(String s : c) {
			Map<String,Integer> counts2 = new HashMap<String,Integer>();
			if(counts != null) {
				for(String key : counts.keySet()) {
					counts2.put(key, counts.get(key));
				}
			}
			boolean twoFound = false;
			for(String s1 : counts2.keySet()) {
				if(counts2.get(s1) > 1)
					twoFound = true;
			}
			if(top) {
				counts2.put(s, (counts2.containsKey(s) ? counts2.get(s) + 1 : 1));
				List<String> moves = getMoves(s, connections, counts2, false);
				for(String s1 : moves) {
					ret.add(start+"-"+s+"-"+s1);
				}
			}else {
				if(s.equals("end")) {
					ret.add(s);
					continue;
				}
				if(s.equals("start")) {
					continue;
				}
				if(s.toCharArray()[0] >= 'a') {
					if((counts2.containsKey(s) && counts2.get(s) > (twoFound ? 0 : 1))) {
						continue;
					}else {
						counts2.put(s, (counts2.containsKey(s) ? counts2.get(s) + 1 : 1));
						List<String> moves = getMoves(s, connections, counts2, false);
						for(String s1 : moves) {
							ret.add(s+"-"+s1);
						}
					}
				}else {
					List<String> moves = getMoves(s, connections, counts2, false);
					for(String s1 : moves) {
						ret.add(s+"-"+s1);
					}
				}
			}
		}
		return ret;
	}
	private static List<String> getConnections(String start, List<String[]> connections){
		List<String> c = new ArrayList<String>();
		for(String[] s : connections) {
			if(s[0].equals(start)) {
				c.add(s[1]);
			}else if(s[1].equals(start)) {
				c.add(s[0]);
			}
		}
		return c;
	}
}
