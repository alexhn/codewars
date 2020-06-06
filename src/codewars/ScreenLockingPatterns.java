package codewars;

public class ScreenLockingPatterns {

	public int calculateCombinations(char startPosition, int patternLength) {
		char[][] map = { { 'A', 'B', 'C' }, { 'D', 'E', 'F' }, { 'G', 'H', 'I' } };
		int si = -1;
		int sj = -1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[i][j] == startPosition) {
					si = i;
					sj = j;
				}
			}
		}
		if (patternLength == 0) {
			return 0;
		}
		boolean[][] used = new boolean[3][3];
		used[si][sj] = true;
		return rec(si, sj, patternLength, "" + startPosition, map, used);
	}

	private int rec(int si, int sj, int patternLength, String current, char[][] map, boolean[][] used) {
		if (patternLength == current.length()) {
			System.out.println(current);
			return 1;
		}
		int ans = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (canGo(si, sj, i, j, used)) {
					used[i][j] = true;
					ans += rec(i, j, patternLength, current + map[i][j], map, used);
					used[i][j] = false;
				}
			}
		}
		return ans;
	}

	private boolean canGo(int fromI, int fromJ, int i, int j, boolean[][] used) {
		if (used[i][j]) {
			return false;
		}
		if (isCorner(fromI, fromJ) && isCorner(i, j)) {
			if (fromI == i) {
				return used[fromI][1];
			} else if (fromJ == j) {
				return used[1][fromJ];
			} else {
				// middle
				return used[1][1];
			}
		}
		if (fromI == i && Math.abs(fromJ - j) == 2) {
			return used[fromI][1];
		}
		if (fromJ == j && Math.abs(fromI - i) == 2) {
			return used[1][fromJ];
		}
		return true;
	}

	private boolean isCorner(int i, int j) {
		if (i == 0 && j == 0) {
			return true;
		}
		if (i == 0 && j == 2) {
			return true;
		}
		if (i == 2 && j == 0) {
			return true;
		}
		if (i == 2 && j == 2) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(new ScreenLockingPatterns().calculateCombinations('A', 8));
	}
}
