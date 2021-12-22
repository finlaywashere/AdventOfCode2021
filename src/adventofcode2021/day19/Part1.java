package adventofcode2021.day19;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
		scanners.add(curr);
		/*
		 * This calculation is ungodly large
		 * Its something like n^16
		 * Might take a little while
		 */
		Set<XYZPoint> map = new LinkedHashSet<XYZPoint>();
		XYZPoint[][] offsets = new XYZPoint[scanners.size()][scanners.size()];
		int[][] rotations = new int[scanners.size()][scanners.size()];
		for(int i = 0; i < rotations.length; i++) {
			for(int i1 = 0; i1 < rotations[i].length; i1++) {
				rotations[i][i1] = -1;
			}
		}
		for(int i = 0; i < scanners.size(); i++) {
			List<XYZPoint> sc1 = scanners.get(i);
			List<List<XYZPoint>> sc1P = computePermutations(sc1);
			for(int i1 = 0; i1 < scanners.size(); i1++) {
				if(i == i1) continue;
				List<XYZPoint> sc2 = scanners.get(i1);
				List<List<XYZPoint>> sc2P = computePermutations(sc2);
				boolean found = false;
				for(List<XYZPoint> p1 : sc1P) {
					for(int i2 = 0; i2 < sc2P.size(); i2++) {
						List<XYZPoint> p2 = sc2P.get(i2);
						XYZPoint offset2 = overlaps(p1, p2);
						if(offset2 != null) {
							System.out.println("Relative offset: "+offset2);
							System.out.println("Found overlapping scanners!\nI: "+i+" I1:"+i1);
							System.out.println("Offset: "+offset2);
							offsets[i][i1] = offset2;
							rotations[i][i1] = i2;
							found = true;
							break;
						}
					}
					if(found)
						break;
				}
			}
		}
		
		in.close();
		for(int i = 0; i < scanners.size(); i++) {
			XYZPoint offset = getOffset(i, offsets);
			List<XYZPoint> start = scanners.get(i);
			int rotation = 0;
			for(int r : rotations[i]) {
				if(r != -1) {
					rotation = r;
					break;
				}
			}
			for(XYZPoint p : start) {
				XYZPoint point = permutations(rotation, p);
				point.transform(offset);
				map.add(point);
			}
			
		}
		Iterator<XYZPoint> iterator = map.iterator();
		for(int i = 0; i < map.size(); i++) {
			System.out.println(i+" : "+iterator.next());
		}
		System.out.println("Count: "+map.size());
	}
	private static XYZPoint getOffset(int start, XYZPoint[][] offsets) {
		if(start == 0)
			return new XYZPoint(0,0,0);
		for(int i = 0; i < offsets[start].length; i++) {
			if(offsets[start][i] != null) {
				XYZPoint p = getOffset(i, offsets);
				p.transform(offsets[start][i]);
				return p;
			}
		}
		return null;
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
				curr.add(permutations(rot, p));
			}
			retValue.add(curr);
		}
		return retValue;
	}
	private static XYZPoint permutations(int rot, XYZPoint p){
		switch(rot) {
		case 0:
			XYZPoint newP = new XYZPoint(p.x,p.y,p.z);
			return newP;
		case 1:
			newP = new XYZPoint(p.x,p.y,-p.z);
			return newP;
		case 2:
			newP = new XYZPoint(p.x,-p.y,-p.z);
			return newP;
		case 3:
			newP = new XYZPoint(p.x,-p.y,p.z);
			return newP;
		case 4:
			newP = new XYZPoint(-p.x,p.y,p.z);
			return newP;
		case 5:
			newP = new XYZPoint(-p.x,-p.y,p.z);
			return newP;
		case 6:
			newP = new XYZPoint(-p.x,-p.y,-p.z);
			return newP;
		case 7:
			newP = new XYZPoint(-p.x,p.y,-p.z);
			return newP;
		case 8:
			newP = new XYZPoint(p.y,p.x,p.z);
			return newP;
		case 9:
			newP = new XYZPoint(p.y,-p.x,p.z);
			return newP;
		case 10:
			newP = new XYZPoint(p.y,p.x,-p.z);
			return newP;
		case 11:
			newP = new XYZPoint(p.y,-p.x,-p.z);
			return newP;
		case 12:
			newP = new XYZPoint(-p.y,p.x,p.z);
			return newP;
		case 13:
			newP = new XYZPoint(-p.y,-p.x,p.z);
			return newP;
		case 14:
			newP = new XYZPoint(-p.y,p.x,-p.z);
			return newP;
		case 15:
			newP = new XYZPoint(-p.y,-p.x,-p.z);
			return newP;
		case 16:
			newP = new XYZPoint(p.z,p.y,p.x);
			return newP;
		case 17:
			newP = new XYZPoint(p.z,-p.y,p.x);
			return newP;
		case 18:
			newP = new XYZPoint(p.z,p.y,-p.x);
			return newP;
		case 19:
			newP = new XYZPoint(p.z,-p.y,-p.x);
			return newP;
		case 20:
			newP = new XYZPoint(-p.z,p.y,p.x);
			return newP;
		case 21:
			newP = new XYZPoint(-p.z,-p.y,p.x);
			return newP;
		case 22:
			newP = new XYZPoint(-p.z,p.y,-p.x);
			return newP;
		case 23:
			newP = new XYZPoint(-p.z,-p.y,-p.x);
			return newP;
		case 24:
			newP = new XYZPoint(p.z,p.x,p.y);
			return newP;
		case 25:
			newP = new XYZPoint(p.z,-p.x,p.y);
			return newP;
		case 26:
			newP = new XYZPoint(p.z,p.x,-p.y);
			return newP;
		case 27:
			newP = new XYZPoint(p.z,-p.x,-p.y);
			return newP;
		case 28:
			newP = new XYZPoint(-p.z,p.x,p.y);
			return newP;
		case 29:
			newP = new XYZPoint(-p.z,-p.x,p.y);
			return newP;
		case 30:
			newP = new XYZPoint(-p.z,p.x,-p.y);
			return newP;
		case 31:
			newP = new XYZPoint(-p.z,-p.x,-p.y);
			return newP;
		case 32:
			newP = new XYZPoint(p.x,p.z,p.y);
			return newP;
		case 33:
			newP = new XYZPoint(-p.x,p.z,p.y);
			return newP;
		case 34:
			newP = new XYZPoint(p.x,-p.z,p.y);
			return newP;
		case 35:
			newP = new XYZPoint(p.x,p.z,-p.y);
			return newP;
		case 36:
			newP = new XYZPoint(-p.x,-p.z,p.y);
			return newP;
		case 37:
			newP = new XYZPoint(-p.x,-p.z,-p.y);
			return newP;
		case 38:
			newP = new XYZPoint(p.x,-p.z,-p.y);
			return newP;
		case 39:
			newP = new XYZPoint(-p.x,p.z,-p.y);
			return newP;
		case 40:
			newP = new XYZPoint(p.y,p.z,p.x);
			return newP;
		case 41:
			newP = new XYZPoint(-p.y,p.z,p.x);
			return newP;
		case 42:
			newP = new XYZPoint(p.y,-p.z,p.x);
			return newP;
		case 43:
			newP = new XYZPoint(p.y,p.z,-p.x);
			return newP;
		case 44:
			newP = new XYZPoint(-p.y,-p.z,p.x);
			return newP;
		case 45:
			newP = new XYZPoint(-p.y,-p.z,-p.x);
			return newP;
		case 46:
			newP = new XYZPoint(p.y,-p.z,-p.x);
			return newP;
		case 47:
			newP = new XYZPoint(-p.y,p.z,-p.x);
			return newP;
		}
		return null;
	}
	/**
	 * Finds the zero point where both lists of beacons overlap
	 * Returns null if there is no overlap > 12 points
	 * Returns the x,y,z transformations that must be applied to a point in p2 to get the corresponding point in p1
	 */
	private static XYZPoint overlaps(List<XYZPoint> p1, List<XYZPoint> p2) {
		for(XYZPoint po : p1) {
			for(XYZPoint po2 : p2) {
				int count = 0;
				for(XYZPoint po3 : p1) {
					for(XYZPoint po4 : p2) {
						XYZPoint shifted = new XYZPoint(po3.x-po.x, po3.y-po.y, po3.z-po.z);
						XYZPoint shifted2 = new XYZPoint(po4.x-po2.x,po4.y-po2.y,po4.z-po2.z);
						if(shifted.equals(shifted2)) {
							count++;
							break;
						}
					}
				}
				if(count >= 12) {
					XYZPoint ret = new XYZPoint(po.x-po2.x,po.y-po2.y,po.z-po2.z);
					
					return ret;
				}
			}
		}
		return null;
	}
}
