package Maze;

public class PairInt {
	private int x;
	private int y;

	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean equals(Object p) {
        if ( p == null) {
            return false;
        }

        PairInt pairInt = (PairInt) p;
        return (this.x == pairInt.x && this.y == pairInt.y);

    }


	public String toString() {
		return "("+Integer.toString(x)+","+Integer.toString(y)+")";
	}

	public PairInt copy() {
		return new PairInt(x, y);
	}
}