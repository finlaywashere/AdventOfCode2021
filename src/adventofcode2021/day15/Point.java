package adventofcode2021.day15;

public class Point {
	private int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Point)) {
			return false;
		}
		Point p = (Point) obj;
		if(p.x == x && p.y == y)
			return true;
		return false;
	}
	@Override
	public String toString() {
		return x+" : "+y;
	}
}
