package adventofcode2021.day19;

public class XYZPoint {
	int x,y,z;

	public XYZPoint(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof XYZPoint))
			return false;
		XYZPoint p = (XYZPoint) obj;
		if(p.x != x || p.y != y || p.z != z)
			return false;
		return true;
	}
	public void transform(XYZPoint p) {
		x += p.x;
		y += p.y;
		z += p.z;
	}
	@Override
	public String toString() {
		return x+","+y+","+z;
	}
	@Override
	public int hashCode() {
		return x + y*5000 + z * 5000000;
	}
}
