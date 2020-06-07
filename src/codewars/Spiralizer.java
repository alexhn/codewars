package codewars;

public class Spiralizer {

	public static int[][] spiralize(int size) {
		int[][] ret = new int[size][size];
		int[] di = {0, 1, 0, -1};
		int[] dj = {1, 0, -1, 0};
		int ci = 0;
		int cj = 0;
		int direction = 0;
		ret[ci][cj] = 1;
		while (true) {
			ret[ci][cj] = 1;
			int ni = ci + di[direction];
			int nj = cj + dj[direction];
			if (canGo(ret, ni, nj, ci, cj)) {
				ci = ni;
				cj = nj;
			} else {
				direction = (direction + 1) % 4;
				ni = ci + di[direction];
				nj = cj + dj[direction];
				if (!canGo(ret, ni, nj, ci, cj)) {
					break;
				}
			}
		}
        return ret;
    }
	
	static boolean canGo(int[][] a, int i, int j, int ci, int cj) {
		if (i >= 0 && j >= 0 && i < a.length && j < a.length) {
			for (int di = -1; di <= 1; di++) {
				for (int dj = -1; dj <= 1; dj++) {
					if (di * dj == 0) {
						if (i + di >= 0 && j + dj >=0 && i + di < a.length && j + dj < a.length) {
							if (a[i + di][j + dj] == 1 && (i + di != ci || j + dj != cj)) {
								return false;
							}
						}
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		int[][] x = spiralize(15);
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				System.out.print(x[i][j]);
			}
			System.out.println();
		}
	}
	
}
