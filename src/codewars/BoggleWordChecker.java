package codewars;

public class BoggleWordChecker {

	private boolean ret = false;
	
	static int[] di = new int[] { 0, 0, 1, -1,  -1, -1, 1,1 };
	static int[] dj = new int[] { 1, -1, 0, 0, 1, -1, 1, -1};
	static int n;
	static int m;
	
	public BoggleWordChecker(final char[][] board, final String word) {
		n = board.length;
		m = board[0].length;
		l: for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				boolean[][] visited = new boolean[n][m];
				if (word.charAt(0) == board[i][j] && dfs(board, visited, i, j, word, 0)) {
					ret = true;
					break l;
				}
			}
		}
	}

	private static boolean dfs(char[][] board, boolean[][] visited, int i, int j, String word, int c) {
		if (c == word.length() - 1) {
			return true;
		}
		visited[i][j] = true;
		for (int k = 0; k < 8; k++) {
			int ni = i + di[k];
			int nj = j + dj[k];
			if (ni >= 0 && nj >= 0 && ni < n && nj < m) {
				if (!visited[ni][nj]) {
					if (word.charAt(c + 1) == board[ni][nj]) {
						boolean ok = dfs(board, visited, ni, nj, word, c + 1);
						if (ok) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean check() {
		return ret;
	}

	public static void main(String[] args) {
		char[][] board = {
		        {'E','A','R','A'},
		        {'N','L','E','C'},
		        {'I','A','I','S'},
		        {'B','Y','O','R'}
		    };
		System.out.println(new BoggleWordChecker(board, "NLECSROYBIAI").check());
	}
}
