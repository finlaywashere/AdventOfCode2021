package adventofcode2021.day19;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader(new File("data/day19.txt")));
		
		List<List<XYZPoint>> scanners = new ArrayList<List<XYZPoint>>();
		List<XYZPoint> curr = null;
		while(in.hasNextLine()) {
			String s = in.nextLine();
			if(s.isEmpty())
				continue;
			if(s.startsWith("---")) {
				if(curr != null)
					scanners.add(curr);
				curr = new ArrayList<XYZPoint>();
				continue;
			}
			String[] split = s.split(",");
			int x = Integer.valueOf(split[0]);
			int y = Integer.valueOf(split[1]);
			int z = Integer.valueOf(split[2]);
			
			curr.add(new XYZPoint(x,y,z));
		}
		/*
		 * This calculation is ungodly large
		 * Its something like n^16
		 * Might take a little while
		 */
		//boolean[][][] map = new boolean[2000][2000][2000];
		boolean[] foundB = new boolean[scanners.size()];
		//XYZPoint currOffset = new XYZPoint(0,0,0);
		int count = 0;
		for(int i = 0; i < scanners.size(); i++) {
			if(foundB[i])
				continue;
			List<XYZPoint> sc1 = scanners.get(i);
			List<List<XYZPoint>> sc1P = computePermutations(sc1);
			for(int i1 = 0; i1 < scanners.size(); i1++) {
				if(foundB[i])
					break;
				if(i == i1) continue;
				List<XYZPoint> sc2 = scanners.get(i1);
				List<List<XYZPoint>> sc2P = computePermutations(sc2);
				boolean found = false;
				for(List<XYZPoint> p1 : sc1P) {
					for(List<XYZPoint> p2 : sc2P) {
						XYZPoint offset = overlaps(p1, p2);
						if(offset != null) {
							System.out.println("Found overlapping scanners!\nI: "+i+" I1:"+i1);
							System.out.println("Offset: "+offset);
							//for(XYZPoint p : p1) {
							//	p.transform(currOffset);
							//	map[p.x+1000][p.y+1000][p.z+1000] = true;
							//}
							//currOffset.transform(offset);
							count += offset.x;
							found = true;
							break;
						}
					}
					if(found)
						break;
				}
				//if(found) {
				//	foundB[i] = true;
				//	break;
				//}
			}
		}
		
		in.close();
		System.out.println("Count: "+count);
	}
	/**
	 * This calculates (32 not 24) permutations of the input points
	 * This calculates 32 because I wasn't going to sit here for an hour finding all the valid permutations 
	 **/
	private static List<List<XYZPoint>> computePermutations(List<XYZPoint> input){
		List<List<XYZPoint>> retValue = new ArrayList<List<XYZPoint>>();
		for(int rot = 0; rot < 24; rot++) {
			List<XYZPoint> curr = new ArrayList<XYZPoint>();
			for(XYZPoint p : input) {
				switch(rot) {
				case 0:
					XYZPoint newP = new XYZPoint(p.x,p.y,p.z);
					curr.add(newP);
					break;
				case 1:
					newP = new XYZPoint(p.x,p.y,-p.z);
					curr.add(newP);
					break;
				case 2:
					newP = new XYZPoint(p.x,-p.y,-p.z);
					curr.add(newP);
					break;
				case 3:
					newP = new XYZPoint(p.x,-p.y,p.z);
					curr.add(newP);
					break;
				case 4:
					newP = new XYZPoint(-p.x,p.y,p.z);
					curr.add(newP);
					break;
				case 5:
					newP = new XYZPoint(-p.x,-p.y,p.z);
					curr.add(newP);
					break;
				case 6:
					newP = new XYZPoint(-p.x,-p.y,-p.z);
					curr.add(newP);
					break;
				case 7:
					newP = new XYZPoint(-p.x,p.y,-p.z);
					curr.add(newP);
					break;
				case 8:
					newP = new XYZPoint(p.y,p.x,p.z);
					curr.add(newP);
					break;
				case 9:
					newP = new XYZPoint(p.y,-p.x,p.z);
					curr.add(newP);
					break;
				case 10:
					newP = new XYZPoint(p.y,p.x,-p.z);
					curr.add(newP);
					break;
				case 11:
					newP = new XYZPoint(p.y,-p.x,-p.z);
					curr.add(newP);
					break;
				case 12:
					newP = new XYZPoint(-p.y,p.x,p.z);
					curr.add(newP);
					break;
				case 13:
					newP = new XYZPoint(-p.y,-p.x,p.z);
					curr.add(newP);
					break;
				case 14:
					newP = new XYZPoint(-p.y,p.x,-p.z);
					curr.add(newP);
					break;
				case 15:
					newP = new XYZPoint(-p.y,-p.x,-p.z);
					curr.add(newP);
					break;
				case 16:
					newP = new XYZPoint(p.z,p.y,p.x);
					curr.add(newP);
					break;
				case 17:
					newP = new XYZPoint(p.z,-p.y,p.x);
					curr.add(newP);
					break;
				case 18:
					newP = new XYZPoint(p.z,p.y,-p.x);
					curr.add(newP);
					break;
				case 19:
					newP = new XYZPoint(p.z,-p.y,-p.x);
					curr.add(newP);
					break;
				case 20:
					newP = new XYZPoint(-p.z,p.y,p.x);
					curr.add(newP);
					break;
				case 21:
					newP = new XYZPoint(-p.z,-p.y,p.x);
					curr.add(newP);
					break;
				case 22:
					newP = new XYZPoint(-p.z,p.y,-p.x);
					curr.add(newP);
					break;
				case 23:
					newP = new XYZPoint(-p.z,-p.y,-p.x);
					curr.add(newP);
					break;
				case 24:
					newP = new XYZPoint(p.z,p.x,p.y);
					curr.add(newP);
					break;
				case 25:
					newP = new XYZPoint(p.z,-p.x,p.y);
					curr.add(newP);
					break;
				case 26:
					newP = new XYZPoint(p.z,p.x,-p.y);
					curr.add(newP);
					break;
				case 27:
					newP = new XYZPoint(p.z,-p.x,-p.y);
					curr.add(newP);
					break;
				case 28:
					newP = new XYZPoint(-p.z,p.x,p.y);
					curr.add(newP);
					break;
				case 29:
					newP = new XYZPoint(-p.z,-p.x,p.y);
					curr.add(newP);
					break;
				case 30:
					newP = new XYZPoint(-p.z,p.x,-p.y);
					curr.add(newP);
					break;
				case 31:
					newP = new XYZPoint(-p.z,-p.x,-p.y);
					curr.add(newP);
					break;
				}
			}
			retValue.add(curr);
		}
		return retValue;
	}
	/**
	 * Finds the zero point where both lists of beacons overlap
	 * Returns null if there is no overlap > 12 points
	 * Returns the x,y,z transformations that must be applied to a point in p1 to get the corresponding point in p2
	 */
	private static XYZPoint overlaps(List<XYZPoint> p1, List<XYZPoint> p2) {
		for(XYZPoint po : p1) {
			for(XYZPoint po2 : p2) {
				int count = 0;
				for(XYZPoint po3 : p1) {
					for(XYZPoint po4 : p2) {
						XYZPoint shifted = new XYZPoint(po3.x-po.x, po3.y-po.y, po3.z-po.z);
						XYZPoint shifted2 = new XYZPoint(po4.x-po2.x,po4.y-po2.y,po4.z-po2.z);
						if(shifted.equals(shifted2))
							count++;
					}
				}
				if(count >= 12) {
					//XYZPoint ret = new XYZPoint(po2.x-po.x,po2.y-po.y,po2.z-po.z);
					return new XYZPoint(count,0,0);
				}
			}
		}
		return null;
	}
}
