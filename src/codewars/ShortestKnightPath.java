package codewars;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestKnightPath {

	public static int knight(String start, String finish) {
		int si = start.charAt(0) - 'a';
		int sj = start.charAt(1) - '1';
		int fi = finish.charAt(0) - 'a';
		int fj = finish.charAt(1) - '1';
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(si, sj));
		int[] di = new int[] { -1, -2, -2, -1, 1, 2, 2, 1 };
		int[] dj = new int[] { -2, -1, 1, 2, 2, 1, -1, -2 };
		boolean[][] visited = new boolean[8][8];
		int[][] dist = new int[8][8];
		visited[si][sj] = true;
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			for (int k = 0; k < 8; k++) {
				int ni = cur.i + di[k];
				int nj = cur.j + dj[k];
				if (ni >= 0 && nj >= 0 && ni < 8 && nj < 8) {
					if (!visited[ni][nj]) {
						visited[ni][nj] = true;
						q.add(new Pos(ni, nj));
						dist[ni][nj] = dist[cur.i][cur.j]+ 1; 
					}
				}
			}
		}
		return dist[fi][fj];
	}
	
	static class Pos {
		int i, j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
}
